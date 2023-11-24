package com.amolli.oyeongshop.ver2.product.controller;

import com.amolli.oyeongshop.ver2.product.dto.ProductOptionDTO;
import com.amolli.oyeongshop.ver2.product.dto.ProductOptionResponse;
import com.amolli.oyeongshop.ver2.product.model.Product;
import com.amolli.oyeongshop.ver2.product.model.ProductOption;
import com.amolli.oyeongshop.ver2.product.service.ProductOptionService;
import com.amolli.oyeongshop.ver2.product.service.ProductService;
import com.amolli.oyeongshop.ver2.s3.AwsS3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping("/admin/product")
@RequiredArgsConstructor
public class ProductAdminController {
    private final ProductService productService;
    private final ProductOptionService productOptionService;
    private final AwsS3Service awsS3Service;


    // [상품 상세 정보 수정] 화면으로 랜더링
    @GetMapping("/edit/{prodId}")
    public String initUpdateOwnerForm(@PathVariable Long prodId, Model model) {
        model.addAttribute("product",productService.findById(prodId));
        model.addAttribute("productOption",productOptionService.findByProduct_ProdId(prodId));
        return "admin/product-register";
    }

    // [상품 상세 정보 수정] 상품 수정 화면의 입력 데이터 보내기
    @PostMapping("/edit/{prodId}")
    public String initUpdateForm(@Validated Product product, @Validated ProductOptionDTO productOptionDTO, @PathVariable Long prodId) {
        System.out.println("상품 정보 수정");
        product.setProdId(prodId);
        productService.saveProduct(product);
        System.out.println("productOptionDTO.getProdOptId() = " + productOptionDTO.getProdOptId());
        if (productOptionDTO.getProdOptId() == null) {
            System.out.println("상품 상세정보 수정 완료");
            return "redirect:/admin/product/management";
        }
        productOptionService.saveProductOption(productOptionDTO);
        System.out.println("상품 옵션 수정 완료");
        return "admin/product-register-detail";
    }

    // [상품 관리] 상품판매구분 컬럼 YES, NO 업데이트
    @ResponseBody
    @PostMapping("/editor/{prodId}")
    public void UpdataSalesStatusYesNo(@PathVariable Long prodId) {
        productService.UpdataSalesStatusYesNo(prodId);
        System.out.println("상품 판매등록 변경 완료");
    }

    // [상품 등록] 화면으로 렌더링
    @GetMapping("/register")
    public String productRegister(Model model) {
        model.addAttribute("product", Product.builder().build());
        model.addAttribute("productOption", ProductOption.builder().build());
        return "admin/product-register";
    }

    // [상품 등록] 상품 기본 정보, 상품 옵션, 이미지 등록
    @PostMapping("/register/{prodId}")
    public String processCreationForm(@PathVariable Long prodId, @RequestParam(value = "image1", required = false) List<MultipartFile> files, Product product) {
        // productService를 사용하여 상품 기본 정보를 등록
        productService.saveProduct(product);
        System.out.println("상품 상세 정보 수정 완료");

        // 등록한 이미지를 저장하기 위한 프로세스
        List<String> imagepath = null;

        // 멀티파트파일->S3에 업로드 하고 imageUrls 리스트로 받아옴
        if (!ObjectUtils.isEmpty(files) && !files.get(0).getOriginalFilename().equals("")) {
            imagepath = awsS3Service.uploadS3ForProduct(files);

            System.out.println("files : "+ files);
        }

        // imageUrls를 받아서 DB에 업로드
        productService.uploadDBForProduct(imagepath, product);

        System.out.println("이미지 파일 업로드 완료");
        return "redirect:/admin/product/management";
    }

    // [상품 및 상품 옵션 등록] POST 요청을 처리하여 상품과 상품 옵션을 등록하는 메서드
    @PostMapping("/register")
    public String processCreationForm(@Valid Product product, @Valid ProductOptionDTO productOptionDTO, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "admin/product-register";
        } else {
            // productService를 사용하여 상품을 등록
            Product savedProduct=productService.saveProduct(product);
            System.out.println("상품 기본 정보 등록 완료 = savedProduct = " + savedProduct.getProdId());

            // productOptionService를 사용하여 상품 옵션을 등록
            productOptionDTO.setProdId(savedProduct.getProdId());
            productOptionService.saveProductOption(productOptionDTO);

            System.out.println("상품 옵션 등록 완료");

            System.out.println(redirectAttributes.addAttribute("prodId", product.getProdId()));
            return "admin/product-register-detail";
        }
    }

    // [상품 관리] 상품 관리 리스트 for Paging Navigation
    @GetMapping("/management")
    public String productManagement(Model model, @RequestParam(name = "page", defaultValue = "1") int currentPage) {
        // 페이지당 항목 수
        int itemsPerPage = 10;
        // 전체 상품 수
        int totalItems = productService.getTotalProductCount();
        // 전체 페이지 수 계산
        int totalPages = (int) Math.ceil((double) totalItems / itemsPerPage);

        // 여기에서 currentPage를 모델에 추가
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", currentPage);

        // 현재 페이지의 상품 목록 조회 (페이징된 결과)
        List<ProductOptionResponse> productList = productService.findProductPaged(currentPage, itemsPerPage);
        model.addAttribute("productList", productList);

        return "admin/product-management";
    }

    // [상품 관리] 상품 관리 리스트를 검색
    @GetMapping("/management/search")
    public String productSearchList(@RequestParam String search, Model model) {
        List<ProductOptionResponse> productListSearch = productService.findByProdNameOrCodeManagementJPQL(search);
        model.addAttribute("productListSearch", productListSearch);
        return "admin/product-management-search";
    }
}
