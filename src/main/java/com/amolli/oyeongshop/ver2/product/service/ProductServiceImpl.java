package com.amolli.oyeongshop.ver2.product.service;

import com.amolli.oyeongshop.ver2.board.dto.ReviewDTO;
import com.amolli.oyeongshop.ver2.board.model.Review;
import com.amolli.oyeongshop.ver2.board.model.ReviewImg;
import com.amolli.oyeongshop.ver2.order.dto.OrderDeliveryDTO;
import com.amolli.oyeongshop.ver2.order.dto.OrderItemDTO;
import com.amolli.oyeongshop.ver2.order.dto.OrderItemsDTO;
import com.amolli.oyeongshop.ver2.order.dto.OrderPriceDTO;
import com.amolli.oyeongshop.ver2.order.model.Order;
import com.amolli.oyeongshop.ver2.order.model.OrderDetail;
import com.amolli.oyeongshop.ver2.product.dto.ProductDTO;
import com.amolli.oyeongshop.ver2.product.dto.ProductOptionsDTO;
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
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
// Impl"은 "implementation"의 줄임말로 사용되며, 일반적으로 어떤 인터페이스(interface)나
// 추상 클래스(abstract class)를 구체적으로 구현한 클래스를 가리킬 때 사용
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;
    private final UserRepository userRepository;

    // [상품 등록] - 상품 정보를 저장
    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    // [상품
    @Override
    public List<Product> findByProdId(Long prodId) {
        return productRepository.findByProdId(prodId);
    }

    // [상품 목록] best100 상품 조회
    @Override
    public List<ProductResponse> findProduct100(String sortValue) {
        List<Object[]> products = productRepository.findByTopProdJPQL(PageRequest.of(0, 100));
        return products.stream()
                .map(product -> {
                    ProductResponse dto = new ProductResponse();
                    dto.setProdId((Long) product[0]);
                    dto.setProdName((String) product[1]);
                    dto.setProdCategory((String) product[2]);
                    dto.setProdOriginPrice((Long) product[3]);
                    dto.setProdSalesPrice((Long) product[4]);
                    dto.setProdMainImgPath((String) product[5]);
                    return dto;
                })
                .collect(Collectors.toList());
    }

    // [상품 목록] 신상품 조회
    @Override
    public List<ProductResponse> findByNewProdJPQL() {
        Sort sort = Sort.by(Sort.Direction.DESC, "prodRegDate");
        List<Product> products = productRepository.findByProdJPQL(PageRequest.of(0, 18, sort));
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

    // [상품 목록] 할인 상품 조회
    @Override
    public List<ProductResponse> findBySaleProd(String sortValue) {
        List<Product> products;
        if (sortValue.equals("pricelow")) {
            Sort sort = Sort.by(Sort.Direction.ASC, "prodSalesPrice");
            products = productRepository.findSaleProducts(sort);
        } else if (sortValue.equals("pricehigh")) {
            Sort sort = Sort.by(Sort.Direction.DESC, "prodSalesPrice");
            products = productRepository.findSaleProducts(sort);
        } else {
            Sort sort = Sort.by(Sort.Direction.DESC, "prodRegDate");
            products = productRepository.findSaleProducts(sort);
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

    // [상품 목록] 전체 상품(all)을 정렬
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

    // [상품 목록] 상품을 카테고리(all 외)별로 분류 및 정렬
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

    // [상품 상세 정보] 선택된 상품의 상세 정보 보기
    public Product findById(Long prodId) {
        Optional<Product> OptionalProduct = productRepository.findById(prodId);

        if (OptionalProduct.isPresent()) {
            return OptionalProduct.get();
        } else {
            return null;
        }
    }

    // [상품 상세 정보] 상품 옵션의 중복 제거
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

    // [상품 관리] 상품 목록을 페이징 하여 조회
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

    // [상품 관리] 상품판매구분 YES, NO 토글
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

    // [상품 관리] 전체 상품의 수 조회(count 함수)
    public int getTotalProductCount() {
        return productRepository.findAll().size();
    }


    // [상품 이미지 업로드]
    public void uploadDBForProduct(List<String> imageUrls, Product product) {

        if (!CollectionUtils.isEmpty(imageUrls)) {
            String firstImageUrl = imageUrls.get(0);
            Long prodId = product.getProdId();
            productRepository.updateMainImagePath(firstImageUrl, prodId);// 옵션

            // ProductImage에 url을 하나씩 prodServerFilePath에 세팅
            for (String url : imageUrls) {
                ProductImage productImage = new ProductImage();
                productImage.setProdServerFilePath(url);
                product.addProductImage(productImage);
            }
        }
        // ProductImage 테이블에 업로드된 사진을 insert
        productRepository.save(product);
    }
}
