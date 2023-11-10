package com.amolli.oyeongshop.ver2.order.service;

import com.amolli.oyeongshop.ver2.order.dto.OrderDeliveryDTO;
import com.amolli.oyeongshop.ver2.order.dto.OrderDetailsDTO;
import com.amolli.oyeongshop.ver2.order.dto.OrderItemDTO;
import com.amolli.oyeongshop.ver2.order.model.Order;
import com.amolli.oyeongshop.ver2.order.model.OrderDetail;
import com.amolli.oyeongshop.ver2.order.repository.OrderDetailRepository;
import com.amolli.oyeongshop.ver2.order.repository.OrderRepository;
import com.amolli.oyeongshop.ver2.product.model.ProductOption;
import com.amolli.oyeongshop.ver2.product.repository.ProductOptionRepository;
import com.amolli.oyeongshop.ver2.user.model.User;
import com.amolli.oyeongshop.ver2.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
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

    public Long order(OrderDetailsDTO orderDetailsDTO, OrderDeliveryDTO orderDeliveryDTO, String userId){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        List<OrderDetail> orderDetailList = new ArrayList<>();
        for(OrderItemDTO itemDTO : orderDetailsDTO.getOrderDetails()){
            ProductOption productOption = productOptionRepository.findById(itemDTO.getProductOption().getProdOptId())
                    .orElseThrow(() -> new EntityNotFoundException("Item not found"));
            OrderDetail orderDetail = OrderDetail.createOrderDetail(productOption, itemDTO.getOrdeDetailAmount());
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
