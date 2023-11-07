package com.amolli.oyeongshop.ver2.board.s3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amolli.oyeongshop.ver2.board.dto.ReviewDTO;
import com.amolli.oyeongshop.ver2.board.dto.ReviewImgDTO;
import com.amolli.oyeongshop.ver2.board.model.Review;
import com.amolli.oyeongshop.ver2.board.model.ReviewImg;
import com.amolli.oyeongshop.ver2.board.repository.ReviewImgRepository;
import com.amolli.oyeongshop.ver2.board.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AwsS3Service {
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    private final AmazonS3 amazonS3;

    private final ReviewRepository reviewRepository;

    private final ReviewImgRepository reviewImgRepository;

    public List<String> uploadS3(List<MultipartFile> multipartlist) {
        // 이미지 url 받아올 리스트
        List<String> imageUrls = new ArrayList<>();

        for (MultipartFile file : multipartlist) {
            String fileName = createFileName(file.getOriginalFilename()); // 파일 이름을 생성
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(file.getSize());
            objectMetadata.setContentType(file.getContentType());

            System.out.println("Uploading photo: " + fileName);

            try (InputStream inputStream = file.getInputStream()) {
                // S3에 업로드
                amazonS3.putObject(new PutObjectRequest(bucket + "/Review", fileName, inputStream, objectMetadata)
                        .withCannedAcl(CannedAccessControlList.PublicRead));
            } catch (IOException e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Photo upload failed.");
            }

            // Accessible URL을 가져온다
            imageUrls.add(amazonS3.getUrl(bucket + "/Review", fileName).toString());

        }return imageUrls;
    }

    public void uploadDB(List<String> imageUrls, ReviewDTO reviewDTO, ReviewImgDTO reviewImgDTO) {
        Review review = Review.builder().reviewDTO(reviewDTO).build();
        System.out.println("Review정보"+review.toString());

        for(String url : imageUrls) {
            ReviewImg img = ReviewImg.builder().reviewServerFileName(url).build();
            review.addReview(img);
        }

        reviewRepository.save(review);
    }



    // S3에 저장되어있는 미디어 파일 삭제
    public void deleteFile(String fileName) {
        amazonS3.deleteObject(new DeleteObjectRequest(bucket, fileName));
    }


    // 파일 업로드 시, 파일명을 난수화하기 위해 random으로 돌린다. (현재는 굳이 난수화할 필요가 없어보여 사용하지 않음)
    public String createFileName(String fileName) {
        return UUID.randomUUID().toString().concat(getFileExtension(fileName));
    }


    // file 형식이 잘못된 경우를 확인하기 위해 만들어진 로직이며, 파일 타입과 상관없이 업로드할 수 있게 하기 위해 .의 존재 유무만 판단하였습니다.
    public String getFileExtension(String fileName) {
        try {
            return fileName.substring(fileName.lastIndexOf("."));
        } catch (StringIndexOutOfBoundsException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "잘못된 형식의 파일(" + fileName + ") 입니다.");
        }
    }
}
