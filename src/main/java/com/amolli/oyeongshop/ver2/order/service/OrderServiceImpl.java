package com.amolli.oyeongshop.ver2.order.service;

import com.amolli.oyeongshop.ver2.order.dto.*;
import com.amolli.oyeongshop.ver2.order.model.Order;
import com.amolli.oyeongshop.ver2.order.model.OrderDetail;
import com.amolli.oyeongshop.ver2.order.repository.OrderRepository;
import com.amolli.oyeongshop.ver2.product.model.Product;
import com.amolli.oyeongshop.ver2.product.model.ProductOption;
import com.amolli.oyeongshop.ver2.product.repository.ProductOptionRepository;
import com.amolli.oyeongshop.ver2.product.service.ProductService;
import com.amolli.oyeongshop.ver2.security.config.auth.PrincipalDetails;
import com.amolli.oyeongshop.ver2.user.model.Cart;
import com.amolli.oyeongshop.ver2.user.model.CartItem;
import com.amolli.oyeongshop.ver2.user.model.User;
import com.amolli.oyeongshop.ver2.user.repository.CartItemRepository;
import com.amolli.oyeongshop.ver2.user.repository.CartRepository;
import com.amolli.oyeongshop.ver2.user.repository.UserRepository;
import com.amolli.oyeongshop.ver2.user.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductOptionRepository productOptionRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductService productService;

    public Long order(OrderItemsDTO orderItemsDTO, OrderDeliveryDTO orderDeliveryDTO, OrderPriceDTO orderPriceDTO, String userId){

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        List<OrderDetail> orderDetailList = new ArrayList<>();
        for(OrderItemDTO itemDTO : orderItemsDTO.getOrderItems()){
            ProductOption productOption = productOptionRepository.findById(itemDTO.getProdOptId())
                    .orElseThrow(() -> new EntityNotFoundException("Item not found"));
            OrderDetail orderDetail = OrderDetail.createOrderDetail(productOption, itemDTO.getQuantity(), itemDTO.getProdSalesPrice(), itemDTO.getProdOriginPrice());
            orderDetailList.add(orderDetail);
        }

        Order order = Order.createOrder(user, orderDetailList, orderDeliveryDTO, orderPriceDTO);

        orderRepository.save(order);

        return order.getOrderId();
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

        for(Long l : selectedItems){
            OrderDTO orderDTO = new OrderDTO();

            Optional<CartItem> cartItem = cartItemRepository.findById(l);
            Product product = productService.findById(cartItem.get().getProductOption().getProduct().getProdId());
            System.out.println("cartItem.isPresent() : " + cartItem.isPresent());
            if (cartItem.isPresent()) {
                // cartItem이 값이 존재하는 경우
                System.out.println("cartItem.prodOptId : " + cartItem.get().getProductOption().getProdOptId());
                orderDTO.setProdOptId(cartItem.get().getProductOption().getProdOptId());
                orderDTO.setProductName(product.getProdName());
                orderDTO.setProdOriginPrice(product.getProdOriginPrice());
                orderDTO.setProdSalesPrice(product.getProdSalesPrice());
                orderDTO.setQuantity(Long.valueOf(cartItem.get().getCartItemAmount()));
                orderDTO.setColor(cartItem.get().getProductOption().getProdOptColor());
                orderDTO.setSize(cartItem.get().getProductOption().getProdOptColor());
                orderDTO.setProdMainImgPath(product.getProdMainImgPath());
                orderDTO.setItemAmount(Long.valueOf(cartItem.get().getCartItemAmount()));

                ordersDTO.getOrders().add(orderDTO);
            } else {
                // cartItem이 값이 없는 경우
                System.out.println("해당 카트 아이템이 존재하지 않습니다.");
            }

        }


        for(OrderDTO orderDTO : ordersDTO.getOrders()) {
            ordersDTO.setOrderTotalOriginPrice(orderDTO.getProdOriginPrice()*orderDTO.getQuantity());
            ordersDTO.setDiscountAmount(orderDTO.getProdOriginPrice()*orderDTO.getQuantity()-orderDTO.getItemAmount());
            ordersDTO.setTotalOrderPayment(orderDTO.getItemAmount());
        }

        return ordersDTO;
    }


}
