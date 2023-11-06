package com.amolli.oyeongshop.ver2.board.s3;

import com.amolli.oyeongshop.ver2.board.model.ReviewImg;
import org.springframework.web.multipart.MultipartFile;

import javax.print.attribute.standard.Media;

public interface MediaUploadInterface {

    ReviewImg uploadPhoto(MultipartFile photo, String photoPurpose);

    void deleteFile(String fileName);

    String createFileName(String fileName);

    String getFileExtension(String fileName);
}
