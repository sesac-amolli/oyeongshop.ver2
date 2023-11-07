package com.amolli.oyeongshop.ver2.board.s3;

import com.amolli.oyeongshop.ver2.board.dto.ReviewDTO;
import com.amolli.oyeongshop.ver2.board.dto.ReviewImgDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AwsS3Controller {
    private final AwsS3Service awsS3Service;

    @ResponseBody
    @PostMapping(value = "/board/review-write", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String uploadFile(@RequestParam("image1") List<MultipartFile> file, ReviewDTO reviewDTO, ReviewImgDTO reviewImgDTO) {

        System.out.println("Controller addReviewDTO:: " + reviewDTO);
        System.out.println("Controller file:: " + file);

        List<String> imagepath = awsS3Service.uploadS3(file);

        awsS3Service.uploadDB(imagepath, reviewDTO, reviewImgDTO);

//        System.out.println("imageurl@!!!!"+imagepath);


        return "redirect:/board/review-list";

    }
}
