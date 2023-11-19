package com.amolli.oyeongshop.ver2.product.service;

import com.amolli.oyeongshop.ver2.board.model.Review;
import com.amolli.oyeongshop.ver2.board.model.ReviewImg;
import com.amolli.oyeongshop.ver2.product.dto.ProductDTO;
import com.amolli.oyeongshop.ver2.product.dto.ProductRegisterRequest;
import com.amolli.oyeongshop.ver2.product.dto.ProductResponse;
import com.amolli.oyeongshop.ver2.product.model.Product;
import com.amolli.oyeongshop.ver2.product.model.ProductImage;
import com.amolli.oyeongshop.ver2.product.model.ProductOption;
import com.amolli.oyeongshop.ver2.product.repository.ProductImageRepository;
import com.amolli.oyeongshop.ver2.product.repository.ProductRepository;
import com.amolli.oyeongshop.ver2.security.config.auth.PrincipalDetails;
import com.amolli.oyeongshop.ver2.user.model.User;
import com.amolli.oyeongshop.ver2.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
// Impl"은 "implementation"의 줄임말로 사용되며, 일반적으로 어떤 인터페이스(interface)나
// 추상 클래스(abstract class)를 구체적으로 구현한 클래스를 가리킬 때 사용
public class
ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;
    private final UserRepository userRepository;

    // [상품 등록] - 상품 정보를 저장
    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> findByProdId(Long prodId) {
        return productRepository.findByProdId(prodId);
    }

    // [상품 목록] - 전체 상품을 정렬
    public List<ProductResponse> findByProdCategoryJPQL(String sortValue) {
        List<Product> products;
        if (sortValue.equals("pricelow")) {
            Sort sort = Sort.by(Sort.Direction.ASC, "prodSalesPrice");
            products = productRepository.findByProdJPQL(sort);
        } else if (sortValue.equals("pricehigh")) {
            Sort sort = Sort.by(Sort.Direction.DESC, "prodSalesPrice");
            products = productRepository.findByProdJPQL(sort);
        } else {
            Sort sort = Sort.by(Sort.Direction.DESC, "prodRegDate");
            products = productRepository.findByProdJPQL(sort);
        }
        return products.stream()
                .map(product -> {
                    ProductResponse dto = new ProductResponse();
                    dto.setProdId(product.getProdId());
                    dto.setProdName(product.getProdName());
                    dto.setProdCategory(product.getProdCategory());
                    dto.setProdOriginPrice(product.getProdOriginPrice());
                    dto.setProdSalesPrice(product.getProdSalesPrice());
                    dto.setProdMainImgPath(product.getProdMainImgPath());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    // [상품 목록] - 상품을 카테고리별로 분류 및 정렬
    public List<ProductResponse> findByProdCategoryJPQL(String prodCategory, String sortValue) {
        List<Product> products;
        if (sortValue.equals("pricelow")) {
            Sort sort = Sort.by(Sort.Direction.ASC, "prodSalesPrice");
            products = productRepository.findByProdCategoryJPQL(prodCategory, sort);
        } else if (sortValue.equals("pricehigh")) {
            Sort sort = Sort.by(Sort.Direction.DESC, "prodSalesPrice");
            products = productRepository.findByProdCategoryJPQL(prodCategory, sort);
        } else {
            Sort sort = Sort.by(Sort.Direction.DESC, "prodRegDate");
            products = productRepository.findByProdCategoryJPQL(prodCategory, sort);
        }
        return products.stream()
                .map(product -> {
                    ProductResponse dto = new ProductResponse();
                    dto.setProdId(product.getProdId());
                    dto.setProdName(product.getProdName());
                    dto.setProdCategory(product.getProdCategory());
                    dto.setProdOriginPrice(product.getProdOriginPrice());
                    dto.setProdSalesPrice(product.getProdSalesPrice());
                    dto.setProdMainImgPath(product.getProdMainImgPath());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    // [상품 상세 정보] - 선택된 상품의 상세 정보 보기
    public Product findById(Long prodId) {
        Optional<Product> OptionalProduct = productRepository.findById(prodId);

        if (OptionalProduct.isPresent()) {
            return OptionalProduct.get();
        } else {
            return null;
        }
    }

    // [상품 상세 정보] - 상품 옵션의 중복 제거
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

    // [상품 관리] - 상품 목록을 페이징 하여 조회
    public List<ProductResponse> findProductPaged(int page, int itemsPerPage) {
        PageRequest pageRequest = PageRequest.of(page - 1, itemsPerPage);
        Page<Product> productPage = productRepository.findAll(pageRequest);

        return productPage.getContent().stream()
                .map(product -> {
                    ProductResponse dto = new ProductResponse();
                    dto.setProdId(product.getProdId());
                    dto.setProdName(product.getProdName());
                    dto.setProdCode(product.getProdCode());
                    dto.setProdCategory(product.getProdCategory());
                    dto.setProdRegDate(product.getProdRegDate());
                    dto.setProdSalesDist(product.getProdSalesDist());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    // [상품 관리] - 상품판매구분 YES, NO 토글
    @Override
    public String UpdataSalesStatusYesNo(Long prodId) {
        Product product = productRepository.findById(prodId).orElse(null);
        // 현재 상품판매구분 변경에 대한 로직
        if (product != null) {
            String currentSalesDist = product.getProdSalesDist();
            String newSalesDist = currentSalesDist.equals("YES") ? "NO" : "YES";
            // JPA를 사용하여 엔터티 업데이트
            productRepository.updateSalesDist(prodId, newSalesDist);
            return newSalesDist;
        }
        return null;
    }

    // [상품 관리] - 전체 상품의 수 조회(count 함수)
    public int getTotalProductCount() {
        return productRepository.findAll().size();
    }


    // [상품 상세정보 수정]
    public void uploadDBForProduct(List<String> imageUrls, ProductDTO productDTO, Long prodId) {
        // CrudRepository에서 findById는 return 타입이 Optional이다.
        // 아래를 productRepository.findById(prodId).orElse()Repository 한줄로도 가능
        // prodId 받아서 findById 해서 Product 객체에 넣어줌
        Optional<Product> optionalProduct = productRepository.findById(prodId);
        String userId = "kiko139";
        // ReviewDto를 Entity로 바꿔주고 review엔티티에 넣어줌.

        // optionalProduct가 존재하지 않으면 RuntimeException 던지기
        if (!optionalProduct.isPresent()) {
            throw new RuntimeException("Prod id: " + prodId + " can not found!!");
        }

        Product product = productDTO.toEntity();

        // ProductId도 같이 insert 해야돼서 optionalProduct를 다 get해서 review에 set해줌
//        product.setProduct(optionalProduct.get());

        if (!CollectionUtils.isEmpty(imageUrls)) {
            for (String url : imageUrls) {
                ProductImage productImage = new ProductImage();

                // reviewImg에 url 하나씩 serverfilename에 set
                productImage.setProdServerFilePath(url);

                product.addProductImage(productImage);
            }
        }

        // review db에 insert
        productRepository.save(product);
    }
}
