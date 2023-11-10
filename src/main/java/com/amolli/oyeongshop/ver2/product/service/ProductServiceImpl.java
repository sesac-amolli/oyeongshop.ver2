package com.amolli.oyeongshop.ver2.product.service;

import com.amolli.oyeongshop.ver2.product.dto.ProductResponse;
import com.amolli.oyeongshop.ver2.product.model.Product;
import com.amolli.oyeongshop.ver2.product.model.ProductOption;
import com.amolli.oyeongshop.ver2.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
// Impl"은 "implementation"의 줄임말로 사용되며, 일반적으로 어떤 인터페이스(interface)나
// 추상 클래스(abstract class)를 구체적으로 구현한 클래스를 가리킬 때 사용
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    // ProductRepository에서 얻은 제품 목록을 반환
    @Override
    public List<ProductResponse> findProductAll() {
        List<Product> products = productRepository.findAll();
        List<ProductResponse> productList = new ArrayList<>();
        for (Product product : products) {
            productList.add(ProductResponse.from(product));
        }
        return productList;
    }

    @Override
    public Product save(Product product) {
        System.out.println("상품이 등록되었습니다.");
        return productRepository.save(product);
    }


    public Product findById(Long prodId){
        Optional<Product> OptionalProduct = productRepository.findById(prodId);

        if (OptionalProduct.isPresent()) {
            return OptionalProduct.get();
        }
        else {
            return null;
        }
    }

    public List<ProductResponse> getProductsByCategory(String prodCategory) {
        List<Product> products = productRepository.findByProdCategoryJPQL(prodCategory);
        return products.stream()
                .map(product -> {
                    ProductResponse dto = new ProductResponse();
                    dto.setProdId(product.getProdId());
                    dto.setProdName(product.getProdName());
                    dto.setProdCategory(product.getProdCategory());
                    dto.setProdSalesPrice(product.getProdSalesPrice());
                    dto.setProdMainImgPath(product.getProdMainImgPath());
                    // 다른 필드들 설정...
                    return dto;
                })
                .collect(Collectors.toList());
    }

    public Product removeDuplicateOptions(Product product) {
        List<ProductOption> productOptions = product.getProductOptions();
        Set<String> uniqueColors = new HashSet<>();
        Set<String> uniqueSizes = new HashSet<>();
        List<ProductOption> uniqueProductOptions = new ArrayList<>();

        for (ProductOption option : productOptions) {
            // 색상 중복 확인
            if (uniqueColors.add(option.getProdOptColor())) {
                uniqueProductOptions.add(option);
            }
        }

        product.setProductOptions(uniqueProductOptions);
        return product;
    }
}
