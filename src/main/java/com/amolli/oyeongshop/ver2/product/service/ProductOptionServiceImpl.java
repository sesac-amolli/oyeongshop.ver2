package com.amolli.oyeongshop.ver2.product.service;

import com.amolli.oyeongshop.ver2.product.model.ProductOption;
import com.amolli.oyeongshop.ver2.product.repository.ProductOptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
// Impl"은 "implementation"의 줄임말로 사용되며, 일반적으로 어떤 인터페이스(interface)나
// 추상 클래스(abstract class)를 구체적으로 구현한 클래스를 가리킬 때 사용
public class ProductOptionServiceImpl implements ProductOptionService {
    private final ProductOptionRepository productOptionRepository;

    // ProductRepository에서 얻은 제품 목록을 반환
    @Override
    public List<ProductOption> findProductOptionAll() {
        List<ProductOption> productOptions = productOptionRepository.findAll();
        return productOptions;
    }

}
