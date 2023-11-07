package com.amolli.oyeongshop.ver2.product.model;

import com.amolli.oyeongshop.ver2.board.model.Question;
import com.amolli.oyeongshop.ver2.board.model.Review;
import com.amolli.oyeongshop.ver2.user.model.Wishlist;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "tbl_product")
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long prodId;
    private String prodCode;
    private String prodName;
    private Long prodOriginPrice;
    private Long prodSalesPrice;
    private String prodCategory;
    private String prodCategoryDetail;
    private String prodDesc;
    private String prodMainImgPath;
    private String prodSalesDist;

    @CreationTimestamp
    private LocalDate prodRegdate;

    @UpdateTimestamp
    private LocalDate prodEditdate;

    @OneToMany(mappedBy = "product")
    private List<ProductImage> productImages = new ArrayList<ProductImage>();
    @OneToMany(mappedBy = "product")
    private List<ProductOption> productOptions = new ArrayList<ProductOption>();
    @OneToMany(mappedBy = "product")
    private List<Question> questions = new ArrayList<Question>();

    @OneToMany(mappedBy = "product")
    private List<Review> reviews = new ArrayList<Review>();

    @OneToMany(mappedBy = "product")
    private List<Wishlist> wishLists = new ArrayList<Wishlist>();

}
