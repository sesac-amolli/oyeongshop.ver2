package com.amolli.oyeongshop.ver2.order.service;

import com.amolli.oyeongshop.ver2.order.dto.OrderDeliveryDto;
import com.amolli.oyeongshop.ver2.order.dto.OrderDetailsDto;
import com.amolli.oyeongshop.ver2.order.dto.OrderItemDto;
import com.amolli.oyeongshop.ver2.order.model.Order;
import com.amolli.oyeongshop.ver2.order.model.OrderDetail;
import com.amolli.oyeongshop.ver2.order.repository.OrderDetailRepository;
import com.amolli.oyeongshop.ver2.order.repository.OrderRepository;
import com.amolli.oyeongshop.ver2.product.model.ProductOption;
import com.amolli.oyeongshop.ver2.product.repository.ProductOptionRepository;
import com.amolli.oyeongshop.ver2.security.config.auth.PrincipalDetails;
import com.amolli.oyeongshop.ver2.user.model.User;
import com.amolli.oyeongshop.ver2.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    private final OrderDetailRepository orderDetailRepository;

    public Long order(OrderDetailsDto orderDetailsDTO, OrderDeliveryDto orderDeliveryDTO, String userId){

        for(OrderItemDto od : orderDetailsDTO.getOrderDetails()){
//            OrderEntity order = od.toEntity();
//            orderRep.save(order);
        }


        System.out.println(userId);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        List<OrderDetail> orderDetailList = new ArrayList<>();
        for(OrderItemDto itemDTO : orderDetailsDTO.getOrderDetails()){
            ProductOption productOption = productOptionRepository.findById(itemDTO.getProductOptId())
                    .orElseThrow(() -> new EntityNotFoundException("Item not found"));
            OrderDetail orderDetail = OrderDetail.createOrderDetail(productOption, itemDTO.getQuantity(), itemDTO.getPrice());
            orderDetailList.add(orderDetail);
        }

        Order order = Order.createOrder(user, orderDetailList, orderDeliveryDTO);

        orderRepository.save(order);

        return order.getOrderId();
    }


    @Override
    public Order save(Order order) {
        System.out.println("Service : " + order);
        return orderRepository.save(order);
    }
}
