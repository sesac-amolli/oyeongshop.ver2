package com.amolli.oyeongshop.ver2.board.s3;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amolli.oyeongshop.ver2.board.dto.AddReviewDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class AwsS3Controller {
    private final AwsS3Service awsS3Service;

    @ResponseBody
    @PostMapping(value = "/board/review-write", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadFile(@RequestParam("image1")MultipartFile file, AddReviewDTO addReviewDTO) {

        System.out.println("Controller addReviewDTO:: " + addReviewDTO);

        System.out.println("Controller file:: " + file);
        String imagepath = awsS3Service.uploadPhoto(file);
        System.out.println("imageurl@!!!!"+imagepath);
        return "board/pwd-check";

    }
}
