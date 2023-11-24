package com.amolli.oyeongshop.ver2.product.dto;

import com.amolli.oyeongshop.ver2.product.model.Product;
import com.amolli.oyeongshop.ver2.product.model.ProductOption;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProductOptionDTO {
    private List<Long> prodOptId;
    private List<String> prodOptColor;
    private List<String> prodOptSize;
    private List<Long> prodOptAmount;

    private Long prodId;

    public List<ProductOption> toProductOption(){

         // 엔티티를 담고 있는 ArrayList
        List<ProductOption> productOptionList = new ArrayList<>();

         // DTO를 엔티티로 변환하고 ArrayList에 담는 과정
            for(int i=0; i< prodOptColor.size() ; i++){
                ProductOption productOption=  ProductOption.builder()
                        .prodOptColor(prodOptColor.get(i))
                        .prodOptSize(prodOptSize.get(i))
                        .prodOptAmount(prodOptAmount.get(i))
                        .product(new Product(prodId))
                        .build();

                if(prodOptId !=null &&  i < prodOptId.size() ){
                    productOption.setProdOptId(prodOptId.get(i));
                }

                productOptionList.add(productOption);


                }
        return productOptionList;
    }


}
