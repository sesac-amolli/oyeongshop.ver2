package com.amolli.oyeongshop.ver2.product.model;

import com.amolli.oyeongshop.ver2.board.model.Question;
import com.amolli.oyeongshop.ver2.board.model.Review;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "tblProducts")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long prodId;

    private String prodCode;

    private String prodName;

    private Long prodOriginPrice;

    private Long prodDiscount;

    private String prodCategory;

    private String prodCategoryDetail;

    private String prodDesc;

    private String prodMainImgName;

    private LocalDate prodRegdate;

    private LocalDate prodEditdate;

    @OneToMany(mappedBy = "tblProduct")
    private List<ProductImage> productImages = new ArrayList<ProductImage>();

    @OneToMany(mappedBy = "tblProduct")
    private List<ProductOption> productOptions = new ArrayList<ProductOption>();

    @OneToMany(mappedBy = "tblProduct")
    private List<Question> questions = new ArrayList<Question>();

    @OneToMany(mappedBy = "tblProduct")
    private List<Review> reviews = new ArrayList<Review>();

//    @OneToMany(mappedBy = "tblProduct")
//    private List<WishList> wishLists = new ArrayList<WishList>();

}
