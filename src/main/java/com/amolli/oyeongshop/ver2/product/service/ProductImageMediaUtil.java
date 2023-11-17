package com.amolli.oyeongshop.ver2.product.service;

import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.Map;

public class ProductImageMediaUtil {
    private static Map<String, MediaType> mediaMap;

    static {
        mediaMap = new HashMap<>();

        mediaMap.put("JPEG", MediaType.IMAGE_JPEG);
        mediaMap.put("JPG", MediaType.IMAGE_JPEG);
        mediaMap.put("GIF", MediaType.IMAGE_GIF);
        mediaMap.put("PNG", MediaType.IMAGE_PNG);
    }

    public static MediaType getMediaType(String type) {
        return mediaMap.get(type.toUpperCase());
    }

    public static boolean containsImageMediaType(String mediaType) {
        return mediaMap.values().contains(MediaType.valueOf(mediaType));
    }

}
