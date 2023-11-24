package com.amolli.oyeongshop.ver2.order.service;

import com.amolli.oyeongshop.ver2.order.dto.*;
import com.amolli.oyeongshop.ver2.order.model.Order;
import com.amolli.oyeongshop.ver2.order.model.OrderDetail;
import com.amolli.oyeongshop.ver2.order.model.Payment;
import com.amolli.oyeongshop.ver2.order.repository.OrderDetailRepository;
import com.amolli.oyeongshop.ver2.order.repository.OrderRepository;
import com.amolli.oyeongshop.ver2.order.repository.PaymentRepository;
import com.amolli.oyeongshop.ver2.product.model.Product;
import com.amolli.oyeongshop.ver2.product.model.ProductOption;
import com.amolli.oyeongshop.ver2.product.repository.ProductOptionRepository;
import com.amolli.oyeongshop.ver2.product.service.ProductService;
import com.amolli.oyeongshop.ver2.security.config.auth.PrincipalDetails;
import com.amolli.oyeongshop.ver2.user.model.CartItem;
import com.amolli.oyeongshop.ver2.user.model.User;
import com.amolli.oyeongshop.ver2.user.repository.CartItemRepository;
import com.amolli.oyeongshop.ver2.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final UserRepository userRepository;
    private final ProductOptionRepository productOptionRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductService productService;
    private final PaymentRepository paymentRepository;

    @Override
    public Map<String, String> order(List<OrderItemDTO> orderItemsDTO, OrderDeliveryDTO orderDeliveryDTO, OrderPriceDTO orderPriceDTO, String userId){

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        List<OrderDetail> orderDetailList = new ArrayList<>();
        System.out.println(orderItemsDTO);
        for(OrderItemDTO itemDTO : orderItemsDTO){
            ProductOption productOption = productOptionRepository.findById(itemDTO.getProdOptId())
                    .orElseThrow(() -> new EntityNotFoundException("Item not found"));
            OrderDetail orderDetail = OrderDetail.createOrderDetail(productOption, itemDTO.getQuantity(), itemDTO.getProdSalesPrice(), itemDTO.getProdOriginPrice());
            orderDetailList.add(orderDetail);
            System.out.println("주문 : " + orderDetail);
        }

        Order order = Order.createOrder(user, orderDetailList, orderDeliveryDTO, orderPriceDTO);

        orderRepository.save(order);

        Map<String, String> orderInfo = new HashMap<>();
        orderInfo.put("orderId", order.getOrderId()+"");
        orderInfo.put("orderNumber", order.getOrderNumber());

        return orderInfo;
    }
    @Override
    public void payment(PaymentDto paymentDto){
        Payment payment = paymentDto.toEntity();
        Long orderId = Long.valueOf(paymentDto.getOrderId());
        Optional<Order> order = orderRepository.findById(orderId);
        if(order.isPresent()) {
            payment.setOrder(order.get());
        }
        Payment result = paymentRepository.save(payment);
    }

    @Override
    public OrderUserDTO setOrderUserDto(PrincipalDetails userDetails) {

        String userId = userDetails.getUser().getUserId();

        User user = userRepository.findByIdWithUserAddrs(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with id : " + userId));
        System.out.println("user Info : " + user.getUserId());

        OrderUserDTO orderUserDto = new OrderUserDTO(user);

        return orderUserDto;
    }

    @Override
    public OrdersDTO setOrdersDTO(OrderItemDTO orderItemDto) {

        OrdersDTO ordersDTO = new OrdersDTO();

        OrderDTO prepareOrderDTO = new OrderDTO();

        Product product = productService.findById(orderItemDto.getProdId());
        prepareOrderDTO.setProdOptId(productOptionRepository.findProdOptIdByProdIdAndProdOptColorAndProdOptSize(orderItemDto.getProdId(), orderItemDto.getColor(), orderItemDto.getSize()));
        prepareOrderDTO.setProductName(product.getProdName());
        prepareOrderDTO.setProdOriginPrice(product.getProdOriginPrice());
        prepareOrderDTO.setProdSalesPrice(orderItemDto.getProdSalesPrice());
        prepareOrderDTO.setQuantity(orderItemDto.getQuantity());
        prepareOrderDTO.setColor(orderItemDto.getColor());
        prepareOrderDTO.setSize(orderItemDto.getSize());
        prepareOrderDTO.setProdMainImgPath(product.getProdMainImgPath());
        prepareOrderDTO.setItemAmount(orderItemDto.getProdSalesPrice()*orderItemDto.getQuantity());

        ordersDTO.getOrders().add(prepareOrderDTO);

        for(OrderDTO orderDTO : ordersDTO.getOrders()) {
            ordersDTO.setOrderTotalOriginPrice(orderDTO.getProdOriginPrice()*orderDTO.getQuantity());
            ordersDTO.setDiscountAmount(orderDTO.getProdOriginPrice()*orderDTO.getQuantity()-orderDTO.getItemAmount());
            ordersDTO.setTotalOrderPayment(orderDTO.getItemAmount());
        }

        return ordersDTO;
    }

    @Override
    public OrdersDTO setOrdersDTO(List<Long> selectedItems) {
        OrdersDTO ordersDTO = new OrdersDTO();
        Long orderTotalPrice = 0L;
        Long discountAmount = 0L;
        Long totalOrderPayment = 0L;

        for(Long l : selectedItems){
            OrderDTO orderDTO = new OrderDTO();

            Optional<CartItem> cartItem = cartItemRepository.findById(l);
            System.out.println("cartItem.isPresent() : " + cartItem.isPresent());
            if (cartItem.isPresent()) {
                // cartItem이 값이 존재하는 경우
                System.out.println("cartItem.prodOptId : " + cartItem.get().getProductOption().getProdOptId());
                orderDTO.setProdOptId(cartItem.get().getProductOption().getProdOptId());
                orderDTO.setProductName(cartItem.get().getProductOption().getProduct().getProdName());
                orderDTO.setProdOriginPrice(cartItem.get().getProductOption().getProduct().getProdOriginPrice());
                orderDTO.setProdSalesPrice(cartItem.get().getProductOption().getProduct().getProdSalesPrice());
                orderDTO.setQuantity(Long.valueOf(cartItem.get().getCartItemAmount()));
                orderDTO.setColor(cartItem.get().getProductOption().getProdOptColor());
                orderDTO.setSize(cartItem.get().getProductOption().getProdOptSize());
                orderDTO.setProdMainImgPath(cartItem.get().getProductOption().getProduct().getProdMainImgPath());
                orderDTO.setItemAmount(Long.valueOf(cartItem.get().getCartItemAmount())*cartItem.get().getProductOption().getProduct().getProdSalesPrice());

                ordersDTO.getOrders().add(orderDTO);
            } else {
                // cartItem이 값이 없는 경우
                System.out.println("해당 카트 아이템이 존재하지 않습니다.");
            }

        }


        for(OrderDTO orderDTO : ordersDTO.getOrders()) {
            orderTotalPrice += orderDTO.getProdOriginPrice()*orderDTO.getQuantity();
            discountAmount += orderDTO.getProdOriginPrice()*orderDTO.getQuantity()-orderDTO.getItemAmount();
            totalOrderPayment += orderDTO.getProdSalesPrice()*orderDTO.getQuantity();
        }

        ordersDTO.setOrderTotalOriginPrice(orderTotalPrice);
        ordersDTO.setDiscountAmount(discountAmount);
        ordersDTO.setTotalOrderPayment(totalOrderPayment);

        return ordersDTO;
    }

    @Override
    public OrderResponseDTO setOrderResponseDTO(Long orderId) {

        OrderResponseDTO orderResponseDTO = new OrderResponseDTO();
        Optional<Order> order = orderRepository.findById(orderId);

        orderResponseDTO.setOrderNumber(order.get().getOrderNumber());
        orderResponseDTO.setOrderDate(order.get().getOrderDate());
        orderResponseDTO.setOrderStatus(order.get().getOrderStatus());
        orderResponseDTO.setOrderOriginPrice(order.get().getOrderTotalOriginPrice());
        orderResponseDTO.setOrderOriginPrice(order.get().getOrderTotalPayment());
        orderResponseDTO.setOrderDiscount(order.get().getOrderTotalOriginPrice()-order.get().getOrderTotalPayment());
        orderResponseDTO.setOrderAttnName(order.get().getOrderAttnName());
        orderResponseDTO.setOrderAttnPhone(order.get().getOrderAttnPhone());
        orderResponseDTO.setOrderAttnEmail(order.get().getOrderAttnEmail());
        orderResponseDTO.setOrderAttnPostcode(order.get().getOrderAttnPostcode());
        orderResponseDTO.setOrderAttnAddr1(order.get().getOrderAttnAddr1());
        orderResponseDTO.setOrderAttnAddr2(order.get().getOrderAttnAddr2());
        orderResponseDTO.setOrderAttnDetail(order.get().getOrderAttnDetail());
        orderResponseDTO.setOrderAttnRequest(order.get().getOrderAttnRequest());

        return orderResponseDTO;
    }

    @Override
    public OrderDetailsResponseDTO setOrderDetailResponseDTO(List<Long> orderDetailIDs) {

        OrderDetailsResponseDTO orderDetailsResponseDTO = new OrderDetailsResponseDTO();

        for(Long l : orderDetailIDs){
            OrderDetailResponseDTO orderDetailResponseDTO = new OrderDetailResponseDTO();
            Optional<OrderDetail> orderDetail = orderDetailRepository.findById(l);
            System.out.println(orderDetail);

            if (orderDetail.isPresent()) {
                orderDetailResponseDTO.setProdMainImgPath(orderDetail.get().getProductOption().getProduct().getProdMainImgPath());
                orderDetailResponseDTO.setProdName(orderDetail.get().getProductOption().getProduct().getProdName());
                orderDetailResponseDTO.setProdOriginPrice(orderDetail.get().getOrderDetailOriginPrice());
                orderDetailResponseDTO.setProdSalesPrice(orderDetail.get().getOrderDetailSalesPrice());
                orderDetailResponseDTO.setQuantity(orderDetail.get().getOrderDetailAmount());
                orderDetailResponseDTO.setColor(orderDetail.get().getProductOption().getProdOptColor());
                orderDetailResponseDTO.setSize(orderDetail.get().getProductOption().getProdOptSize());
                orderDetailResponseDTO.setItemAmount(orderDetail.get().getOrderDetailSalesPrice());
                orderDetailResponseDTO.setProdId(orderDetail.get().getProductOption().getProduct().getProdId());
            }else{
                System.out.println("해당하는 OrderDetail이 없습니다.");
            }

            System.out.println("오더 : " + orderDetailResponseDTO);
            orderDetailsResponseDTO.getOrderDetailItems().add(orderDetailResponseDTO);
        }


        return orderDetailsResponseDTO;
    }

    @Override
    public OrderDetailDTO setOrderDetailDTO(Long orderId) {

        OrderDetailDTO orderDetailDTO = new OrderDetailDTO();

        List<Long> orderDetailIds = new ArrayList<>();
        Optional<Order> order = orderRepository.findById(orderId);
        System.out.println("service, order" + order.isPresent());

        if (order.isPresent()) {
            for (OrderDetail orderDetail : order.get().getOrderDetails()){
                orderDetailIds.add(orderDetail.getOrderDetailId());
                System.out.println("service, getOrderDetailID() " + orderDetail.getOrderDetailId());
            }

        }else{
            System.out.println("해당 주문이 존재하지 않습니다.");
        }

        orderDetailDTO.setOrderId(orderId);
        orderDetailDTO.setOrderDetailIds(orderDetailIds);

        return orderDetailDTO;
    }


    @Override
    public OrderDetailsResponseDTO setOrderDetailResponseDTO(Long orderId) {

        OrderDetailsResponseDTO orderDetailsResponseDTO = new OrderDetailsResponseDTO();
        List<Long> orderDetailIDs = orderDetailRepository.findOrderDetailIdsByOrderId(orderId);

        for(Long l : orderDetailIDs){
            OrderDetailResponseDTO orderDetailResponseDTO = new OrderDetailResponseDTO();
            Optional<OrderDetail> orderDetail = orderDetailRepository.findById(l);
            System.out.println(orderDetail);

            if (orderDetail.isPresent()) {
                orderDetailResponseDTO.setProdMainImgPath(orderDetail.get().getProductOption().getProduct().getProdMainImgPath());
                orderDetailResponseDTO.setProdName(orderDetail.get().getProductOption().getProduct().getProdName());
                orderDetailResponseDTO.setProdOriginPrice(orderDetail.get().getOrderDetailOriginPrice());
                orderDetailResponseDTO.setProdSalesPrice(orderDetail.get().getOrderDetailSalesPrice());
                orderDetailResponseDTO.setQuantity(orderDetail.get().getOrderDetailAmount());
                orderDetailResponseDTO.setColor(orderDetail.get().getProductOption().getProdOptColor());
                orderDetailResponseDTO.setSize(orderDetail.get().getProductOption().getProdOptSize());
                orderDetailResponseDTO.setItemAmount(orderDetail.get().getOrderDetailSalesPrice());
                orderDetailResponseDTO.setProdId(orderDetail.get().getProductOption().getProduct().getProdId());
            }else{
                System.out.println("해당하는 OrderDetail이 없습니다.");
            }

            System.out.println("오더 : " + orderDetailResponseDTO);
            orderDetailsResponseDTO.getOrderDetailItems().add(orderDetailResponseDTO);
        }


        return orderDetailsResponseDTO;
    }


    @Override
    public List<OrderListDTO> setOrderListDTOList(String userId) {
        List<Order> orderList = orderRepository.findByUserId(userId);
//        Sort sort = Sort.by(Sort.Direction.DESC, "orderDate");
//        System.out.println("주문조회 페이지 번호 : " + page);
//        List<Order> orderList = orderRepository.findByUserId(userId, PageRequest.of(page, 10, sort));
        List<OrderListDTO> orderListDTOs = new ArrayList<>();

        for(Order order : orderList) {

            OrderListDTO orderListDTO = new OrderListDTO();
            List<OrderListDetailDTO> orderListDetailDTOs = new ArrayList<>();

            orderListDTO.setOrderDate(order.getOrderDate());
            orderListDTO.setOrderNumber(order.getOrderNumber());
            orderListDTO.setOrderId(order.getOrderId());
            for(OrderDetail od : order.getOrderDetails()){
                OrderListDetailDTO orderListDetailDTO = new OrderListDetailDTO();

                orderListDetailDTO.setProdMainImgPath(od.getProductOption().getProduct().getProdMainImgPath());
                orderListDetailDTO.setProdName(od.getProductOption().getProduct().getProdName());
                orderListDetailDTO.setProdId(od.getProductOption().getProduct().getProdId());
                orderListDetailDTO.setProdOptSize(od.getProductOption().getProdOptSize());
                orderListDetailDTO.setProdOptColor(od.getProductOption().getProdOptColor());
                orderListDetailDTO.setOrderDetailAmount(od.getOrderDetailAmount());
                orderListDetailDTO.setOrderDetailSalesPrice(od.getOrderDetailSalesPrice());
                orderListDetailDTO.setOrderStatus(od.getOrder().getOrderStatus());

                orderListDetailDTOs.add(orderListDetailDTO);
            }
            orderListDTO.setOrderDetailList(orderListDetailDTOs);
            orderListDTOs.add(orderListDTO);
        }

        return orderListDTOs;
    }

}
