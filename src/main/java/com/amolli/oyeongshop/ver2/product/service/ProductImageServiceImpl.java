package com.amolli.oyeongshop.ver2.product.service;

import com.amolli.oyeongshop.ver2.product.model.ProductImage;
import com.amolli.oyeongshop.ver2.product.repository.ProductImageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

@Service
public class ProductImageServiceImpl {
    private static final Logger logger = LoggerFactory.getLogger(ProductImageServiceImpl.class);

    private final Path rootLocation;

    public ProductImageServiceImpl(String uploadPath) {
        logger.info("PATH :: " + uploadPath);
        this.rootLocation = Paths.get(uploadPath);
    }

    @Autowired
    ProductImageRepository productImageRepository;

    public Stream<Long> loadAll() {
        List<ProductImage> files = productImageRepository.findAll();
        return files.stream().map(file -> file.getProdDetailImgId());
    }

    public ProductImage load(Long fileId) {
        //productImageRepository.findOne(fileId);
        return productImageRepository.findById(fileId).orElse(null);
    }

    public Resource loadAsResource(String fileName) throws Exception {
        try {
            if (fileName.toCharArray()[0] == '/') {
                fileName = fileName.substring(1);
            }

            Path file = loadPath(fileName);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new Exception("Could not read file: " + fileName);
            }
        } catch (Exception e) {
            throw new Exception("Could not read file: " + fileName);
        }
    }

    private Path loadPath(String fileName) {
        return rootLocation.resolve(fileName);
    }

    public ProductImage store(MultipartFile file) throws Exception {
        try {
            if (file.isEmpty()) {
                throw new Exception("Failed to store empty file " + file.getOriginalFilename());
            }

            String prodImgFileName = ProductImageUtil.fileSave(rootLocation.toString(), file);

            if (prodImgFileName.toCharArray()[0] == '/') {
                prodImgFileName = prodImgFileName.substring(1);
            }

            Resource resource = loadAsResource(prodImgFileName);

            ProductImage saveFile = new ProductImage();
            saveFile.setSaveFileName(prodImgFileName);
            saveFile.setFileName(file.getOriginalFilename());
            saveFile.setProdImgFileName(prodImgFileName);
            saveFile.setProdDetailImgPath(rootLocation.toString().replace(File.separatorChar, '/') + File.separator + prodImgFileName);
            saveFile = productImageRepository.save(saveFile);

            return saveFile;
        } catch (IOException e) {
            throw new Exception("Failed to store file " + file.getOriginalFilename(), e);
        }
    }
}
