package com.amolli.oyeongshop.ver2.product.model;

import com.amolli.oyeongshop.ver2.board.model.Question;
import com.amolli.oyeongshop.ver2.board.model.Review;
import com.amolli.oyeongshop.ver2.user.model.Wishlist;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@Entity
@Table(name = "tbl_product")
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
@ToString
@DynamicUpdate
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long prodId;
    private String prodCode;
    private String prodName;
    private Long prodOriginPrice;
    private Long prodSalesPrice;
    private String prodCategory;
    @Column(length = 1500)
    private String prodDesc;
    private String prodMainImgPath;
    private String prodSalesDist ="NO";

    @CreationTimestamp
    private LocalDateTime prodRegDate;

    @UpdateTimestamp
    private LocalDateTime prodEditDate;

    @Builder.Default
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ProductImage> productImages = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE)
    private List<ProductOption> productOptions = new ArrayList<>();

    @OneToMany(mappedBy = "product")
    private List<Question> questions = new ArrayList<>();

    @OneToMany(mappedBy = "product")
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "product")
    private List<Wishlist> wishLists = new ArrayList<>();

    public Product(Long prodId){
        this.prodId=prodId;
    }
    @Builder
    public Product(Long prodId, String prodCode, String prodName, Long prodOriginPrice, Long prodSalesPrice, String prodCategory,
                   LocalDateTime prodRegDate, LocalDateTime prodEditDate, String prodSalesDist, String prodDesc, List<ProductOption> productOptions) {
        this.prodId = prodId;
        this.prodCode = prodCode;
        this.prodName = prodName;
        this.prodOriginPrice = prodOriginPrice;
        this.prodSalesPrice = prodSalesPrice;
        this.prodCategory = prodCategory;
        this.prodDesc = prodDesc;
        this.prodRegDate = prodRegDate;
        this.prodEditDate = prodEditDate;
        this.prodSalesDist = prodSalesDist;
        this.productOptions = productOptions;
    }

    public void setProdId(Long prodId) {
        this.prodId = prodId;
    }

    public void addProductImage(ProductImage productImage){
        productImages.add(productImage);
        productImage.setProduct(this);
    }


}
