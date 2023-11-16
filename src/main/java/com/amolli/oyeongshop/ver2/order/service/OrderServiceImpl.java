package com.amolli.oyeongshop.ver2.order.service;

import com.amolli.oyeongshop.ver2.order.dto.*;
import com.amolli.oyeongshop.ver2.order.model.Order;
import com.amolli.oyeongshop.ver2.order.model.OrderDetail;
import com.amolli.oyeongshop.ver2.order.repository.OrderDetailRepository;
import com.amolli.oyeongshop.ver2.order.repository.OrderRepository;
import com.amolli.oyeongshop.ver2.product.model.Product;
import com.amolli.oyeongshop.ver2.product.model.ProductOption;
import com.amolli.oyeongshop.ver2.product.repository.ProductOptionRepository;
import com.amolli.oyeongshop.ver2.product.service.ProductService;
import com.amolli.oyeongshop.ver2.user.model.User;
import com.amolli.oyeongshop.ver2.user.model.UserAddr;
import com.amolli.oyeongshop.ver2.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

    public Long order(OrderDetailsDto orderDetailsDTO, OrderDeliveryDto orderDeliveryDTO, String userId){

        for(OrderItemDto od : orderDetailsDTO.getOrderDetails()){
//            OrderEntity order = od.toEntity();
//            orderRep.save(order);
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        List<OrderDetail> orderDetailList = new ArrayList<>();
        for(OrderItemDto itemDTO : orderDetailsDTO.getOrderDetails()){
            ProductOption productOption = productOptionRepository.findById(itemDTO.getProdOptId())
                    .orElseThrow(() -> new EntityNotFoundException("Item not found"));
            OrderDetail orderDetail = OrderDetail.createOrderDetail(productOption, itemDTO.getQuantity(), itemDTO.getProdSalesPrice());
            orderDetailList.add(orderDetail);
        }

        Order order = Order.createOrder(user, orderDetailList, orderDeliveryDTO);

        orderRepository.save(order);

        return order.getOrderId();
    }

    @Override
    public OrderUserDto setOrderUserDto(String userId) {

        User user = userRepository.findByIdWithUserAddrs(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with id : " + userId));

        OrderUserDto orderUserDto = new OrderUserDto(user);

        return orderUserDto;
    }


    @Override
    public OrderDto setPreparedOrderDto(OrderItemDto orderItemDto) {

        OrderDto preparedOrderDto = new OrderDto();
        Product product = productService.findById(orderItemDto.getProdId());
        preparedOrderDto.setProdOptId(productOptionRepository.findProdOptIdByProdIdAndProdOptColorAndProdOptSize(orderItemDto.getProdId(), orderItemDto.getColor(), orderItemDto.getSize()));
        preparedOrderDto.setProductName(product.getProdName());
        preparedOrderDto.setProdSalesPrice(orderItemDto.getProdSalesPrice());
        preparedOrderDto.setQuantity(orderItemDto.getQuantity());
        preparedOrderDto.setColor(orderItemDto.getColor());
        preparedOrderDto.setSize(orderItemDto.getSize());
        preparedOrderDto.setProdMainImgPath(product.getProdMainImgPath());
        preparedOrderDto.setProdOriginPrice(product.getProdOriginPrice());
        preparedOrderDto.setItemAmount(product.getProdOriginPrice()*orderItemDto.getQuantity());
        preparedOrderDto.setTotalAmount(product.getProdOriginPrice()*orderItemDto.getQuantity());
        preparedOrderDto.setDiscountAmount((product.getProdOriginPrice()-product.getProdSalesPrice())*orderItemDto.getQuantity());
        preparedOrderDto.setTotalPaymentAmount(preparedOrderDto.getTotalAmount()-preparedOrderDto.getDiscountAmount());

        return preparedOrderDto;
    }


}
