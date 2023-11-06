package com.amolli.oyeongshop.ver2.board.s3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amolli.oyeongshop.ver2.board.model.ReviewImg;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AwsS3Service implements MediaUploadInterface {
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    private final AmazonS3 amazonS3;

    private final PhotoRepository photoRepository; // 가정: MediaRepository가 PhotoRepository로 변경되었다고 가정

    @Override
    public ReviewImg uploadPhoto(MultipartFile photo, String photoPurpose) { // Photo 객체로 변경

        String photoName = createFileName(photo.getOriginalFilename()); // 파일 이름을 생성

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(photo.getSize());
        objectMetadata.setContentType(photo.getContentType());

        System.out.println("Uploading photo: " + photoName);

        try (InputStream inputStream = photo.getInputStream()) {
            // S3에 업로드
            amazonS3.putObject(new PutObjectRequest(bucket, photoName, inputStream, objectMetadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead));
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Photo upload failed.");
        }

        // Accessible URL을 가져온다
        String photoUrl = amazonS3.getUrl(bucket, photoName).toString();

        // 데이터베이스에 사진 정보 저장
        ReviewImg uploadedPhoto = ReviewImg.builder()
                .reviewServerFileName(photoUrl)
                .build();

//        Photo uploadedPhoto = Photo.builder()
//                .originalName(photo.getOriginalFilename())
//                .uniqueName(photoName)
//                .type(photo.getContentType())
//                .url(photoUrl)
//                .purpose(photoPurpose)
//                .build();

        photoRepository.save(uploadedPhoto);

        return uploadedPhoto;
    }

    // S3에 저장되어있는 미디어 파일 삭제
    @Override
    public void deleteFile(String fileName) {
        amazonS3.deleteObject(new DeleteObjectRequest(bucket, fileName));
    }


    // 파일 업로드 시, 파일명을 난수화하기 위해 random으로 돌린다. (현재는 굳이 난수화할 필요가 없어보여 사용하지 않음)
    @Override
    public String createFileName(String fileName) {
        return UUID.randomUUID().toString().concat(getFileExtension(fileName));
    }


    // file 형식이 잘못된 경우를 확인하기 위해 만들어진 로직이며, 파일 타입과 상관없이 업로드할 수 있게 하기 위해 .의 존재 유무만 판단하였습니다.
    @Override
    public String getFileExtension(String fileName) {
        try {
            return fileName.substring(fileName.lastIndexOf("."));
        } catch (StringIndexOutOfBoundsException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "잘못된 형식의 파일(" + fileName + ") 입니다.");
        }
    }
}
