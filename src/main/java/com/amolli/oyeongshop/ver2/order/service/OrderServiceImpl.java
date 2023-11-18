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
import com.amolli.oyeongshop.ver2.user.model.User;
import com.amolli.oyeongshop.ver2.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductOptionRepository productOptionRepository;
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
        return null;
    }


//    @Override
//    public OrderDTO setPreparedOrderDto(OrderItemDTO orderItemDto) {
//
//        OrderDTO preparedOrderDto = new OrderDTO();
//        Product product = productService.findById(orderItemDto.getProdId());
//        preparedOrderDto.setProdOptId(productOptionRepository.findProdOptIdByProdIdAndProdOptColorAndProdOptSize(orderItemDto.getProdId(), orderItemDto.getColor(), orderItemDto.getSize()));
//        preparedOrderDto.setProductName(product.getProdName());
//        preparedOrderDto.setProdOriginPrice(product.getProdOriginPrice());
//        preparedOrderDto.setProdSalesPrice(orderItemDto.getProdSalesPrice());
//        preparedOrderDto.setQuantity(orderItemDto.getQuantity());
//        preparedOrderDto.setColor(orderItemDto.getColor());
//        preparedOrderDto.setSize(orderItemDto.getSize());
//        preparedOrderDto.setProdMainImgPath(product.getProdMainImgPath());
//        preparedOrderDto.setItemAmount(orderItemDto.getProdSalesPrice()*orderItemDto.getQuantity());
//        preparedOrderDto.setOrderTotalOriginPrice(); //주문할 전체 제품의 원가 가격
//        preparedOrderDto.setDiscountAmount(); //주문할 전체 제품의 원가 가격 - 주문할 전체 제품의 세일 금액
//        preparedOrderDto.setTotalOrderPayment(); //주문할 전체 제품의 결제 예상 금액
//
//        return preparedOrderDto;
//    }


}
