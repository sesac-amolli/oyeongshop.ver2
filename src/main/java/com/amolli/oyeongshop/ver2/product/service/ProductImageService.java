package com.amolli.oyeongshop.ver2.product.service;

import com.amolli.oyeongshop.ver2.product.dto.ProductDTO;
import com.amolli.oyeongshop.ver2.security.config.auth.PrincipalDetails;

import java.util.List;

public interface ProductImageService {
    void uploadDB(List<String> imageUrls, ProductDTO productDTO, Long prodId, PrincipalDetails userDetails);
}
