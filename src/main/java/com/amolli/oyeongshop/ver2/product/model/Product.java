package com.amolli.oyeongshop.ver2.product.model;

import com.amolli.oyeongshop.ver2.board.model.Question;
import com.amolli.oyeongshop.ver2.board.model.Review;
import com.amolli.oyeongshop.ver2.user.model.Wishlist;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tbl_product")
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@ToString
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
    private LocalDate prodRegDate;

    @UpdateTimestamp
    private LocalDate prodEditDate;

    @OneToMany(mappedBy = "product")
    private List<ProductImage> productImages = new ArrayList<>();
    @OneToMany(mappedBy = "product")
    private List<ProductOption> productOptions = new ArrayList<>();
    @OneToMany(mappedBy = "product")
    private List<Question> questions = new ArrayList<>();
    @OneToMany(mappedBy = "product")
    private List<Review> reviews = new ArrayList<>();
    @OneToMany(mappedBy = "product")
    private List<Wishlist> wishLists = new ArrayList<>();

    @Builder
    public Product(Long prodId, String prodCode, String prodName, Long prodOriginPrice, Long prodSalesPrice, LocalDate prodRegDate, List<ProductOption> productOptions) {
        this.prodId = prodId;
        this.prodCode = prodCode;
        this.prodName = prodName;
        this.prodOriginPrice = prodOriginPrice;
        this.prodSalesPrice = prodSalesPrice;
        this.prodRegDate = prodRegDate;
        this.productOptions = productOptions;
    }

    public void setProdId(Long prodId) {
        this.prodId = prodId;
    }
}
