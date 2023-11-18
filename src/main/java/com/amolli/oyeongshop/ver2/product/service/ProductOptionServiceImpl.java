package com.amolli.oyeongshop.ver2.product.service;

import com.amolli.oyeongshop.ver2.product.model.Product;
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

    // [상품 옵션 등록] - 상품 옵션을 저장하는 코드
    public ProductOption saveProductOption(ProductOption productOption) {
        return productOptionRepository.save(productOption);
    }

    // [상품 옵션 수정] -
    public List<ProductOption> findByProduct_ProdId(Long prodId) {
        return productOptionRepository.findByProduct_ProdId(prodId);
    }

    // [상품 관리] - 상품판매구분 YES, NO 토글
//    @Override
//    public String UpdataProdId(Long prodId) {
//        ProductOption productOptionProdId = productOptionRepository.findById(prodId).orElse(null);
//        // 현재 상품판매구분 변경에 대한 로직
//        if (productOptionProdId == null) {
//            // JPA를 사용하여 엔터티 업데이트
//            productOptionRepository.updateProdIdWhereNull(prodId);
//            return null;
//        }
//        return null;
//    }

//    public Long getProdIdForProductOption(Long productOptionId) {
//        ProductOption productOption = productOptionRepository.findById(productOptionId).orElse(null);
//        if (productOption != null) {
//            return productOption.getProdId();
//        } else {
//            return null; // 또는 예외 처리 등을 추가할 수 있습니다.
//        }
//    }
}
