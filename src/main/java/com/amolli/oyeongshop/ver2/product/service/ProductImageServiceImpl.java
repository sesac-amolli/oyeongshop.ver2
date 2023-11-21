package com.amolli.oyeongshop.ver2.product.service;

import com.amolli.oyeongshop.ver2.product.dto.ProductDTO;
import com.amolli.oyeongshop.ver2.product.model.Product;
import com.amolli.oyeongshop.ver2.product.model.ProductImage;
import com.amolli.oyeongshop.ver2.product.model.ProductOption;
import com.amolli.oyeongshop.ver2.product.repository.ProductImageRepository;
import com.amolli.oyeongshop.ver2.product.repository.ProductRepository;
import com.amolli.oyeongshop.ver2.security.config.auth.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductImageServiceImpl implements ProductImageService {
    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;


    @Override
    public List<ProductImage> findByProduct_ProdId(Long prodId) {
        return productImageRepository.findByProduct_ProdId(prodId);
    }
    @Override
    public void uploadDB(List<String> imageUrls, ProductDTO productDTO, Long prodId, PrincipalDetails userDetails) {
        // CrudRepository에서 findById는 return 타입이 Optional이다.
        // 아래를 productRepository.findById(prodId).orElse()Repository 한줄로도 가능
        // prodId 받아서 findById 해서 Product 객체에 넣어줌
        Optional<Product> optionalProduct = productRepository.findById(prodId);

        // optionalProduct가 존재하지 않으면 RuntimeException 던지기
        if (!optionalProduct.isPresent()) {
            throw new RuntimeException("Prod id: " + prodId + " can not found!!");
        }

        // ReviewDto를 Entity로 바꿔주고 review엔티티에 넣어줌.
        Product product = productDTO.toEntity();


        if(!CollectionUtils.isEmpty(imageUrls)) {
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
//    @Override
//    public List<Product> findAll() {
//        return productRepository.findAll();
//    }
//
//    @Override
//    public List<Product> findByProdId(Long prodId) {
//        return productRepository.findByProduct_ProdId(prodId);
//    }
//
//    @Override
//    public void deleteMyReview(Long reviewId) {
//        productRepository.deleteById(reviewId);
//    }

}
