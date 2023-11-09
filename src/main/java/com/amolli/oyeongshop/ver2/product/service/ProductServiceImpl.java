package com.amolli.oyeongshop.ver2.product.service;

import com.amolli.oyeongshop.ver2.product.dto.ProductResponse;
import com.amolli.oyeongshop.ver2.product.model.Product;
import com.amolli.oyeongshop.ver2.product.model.ProductOption;
import com.amolli.oyeongshop.ver2.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

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

//    public Product removeDuplicateOptions(Product product) {
//        List<ProductOption> productOptions = product.getProductOptions();
//        Set<String> uniqueOptions = new HashSet<>();
//        List<ProductOption> uniqueProductOptions = new ArrayList<>();
//
//        for (ProductOption option : productOptions) {
//            // 색상과 사이즈를 결합하여 고유한 옵션으로 간주
//            String uniqueOption = option.getProdOptColor() + option.getProdOptSize();
//
//            if (uniqueOptions.add(uniqueOption)) {
//                uniqueProductOptions.add(option);
//            }
//        }
//
//        product.setProductOptions(uniqueProductOptions);
//        return product;
//    }

//    public Product removeDuplicateOptions(Product product) {
//        List<ProductOption> productOptions = product.getProductOptions();
//        Set<String> uniqueColors = new HashSet<>();
//        Set<String> uniqueSizes = new HashSet<>();
//        List<ProductOption> uniqueProductColors = new ArrayList<>();
//        List<ProductOption> uniqueProductSizes = new ArrayList<>();
//
//        for (ProductOption option : productOptions) {
//            if (uniqueColors.add(option.getProdOptColor())) {
//                uniqueProductColors.add(option);
//            }
//            if (uniqueSizes.add(option.getProdOptSize())) {
//                uniqueProductSizes.add(option);
//            }
//        }
//
//        product.setProductOptions(uniqueProductColors);
//        product.setProductOptions(uniqueProductSizes);
//        return product;
//    }

//    public Product removeDuplicateOptions(Product product) {
//        List<ProductOption> productOptions = product.getProductOptions();
//        Set<String> uniqueOptions = new HashSet<>();
//        List<ProductOption> uniqueProductOptions = new ArrayList<>();
//
//        for (ProductOption option : productOptions) {
//            // 색상과 사이즈를 결합하여 고유한 옵션으로 간주
//            String uniqueOption = option.getProdOptColor() + option.getProdOptSize();
//
//            if (uniqueOptions.add(uniqueOption)) {
//                uniqueProductOptions.add(option);
//            }
//        }
//
//        product.setProductOptions(uniqueProductOptions);
//        return product;
//    }

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
