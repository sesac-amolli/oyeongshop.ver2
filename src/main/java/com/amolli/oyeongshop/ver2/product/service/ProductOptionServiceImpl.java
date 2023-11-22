package com.amolli.oyeongshop.ver2.product.service;

import com.amolli.oyeongshop.ver2.product.dto.ProductOptionDTO;
import com.amolli.oyeongshop.ver2.product.model.ProductOption;
import com.amolli.oyeongshop.ver2.product.repository.ProductOptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
@Transactional
@RequiredArgsConstructor
public class ProductOptionServiceImpl implements ProductOptionService {
    private final ProductOptionRepository productOptionRepository;

    public ProductOption saveProductOption(ProductOption productOption) {
        return productOptionRepository.save(productOption);
    }

    // [상품 옵션 등록] 상품 옵션을 저장하는 코드
    public void saveProductOption(ProductOptionDTO productOption) {
       List<ProductOption> productOptionList = productOption.toProductOption();

        System.out.println("productOptionList = " + productOptionList);

         for(ProductOption  poption : productOptionList) {
             System.out.println("poption.getProduct().getProdId() = "+poption.getProduct().getProdId());
             productOptionRepository.save(poption);
         }
    }

    // [상품 옵션 수정] 상품 옵션 수정 시 where 조건에 prodOptId가 아닌 ProdId를 부여하는 코드
    public List<ProductOption> findByProduct_ProdId(Long prodId) {
        return productOptionRepository.findByProduct_ProdId(prodId);
    }
}
