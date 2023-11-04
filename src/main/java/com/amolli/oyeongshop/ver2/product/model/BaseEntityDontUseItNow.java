//package com.amolli.oyeongshop.ver2.product.model;
//
//import lombok.AccessLevel;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.*;
//
//@Getter
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@MappedSuperclass
//public class BaseEntityDontUseItNow {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    public BaseEntityDontUseItNow(Long id) {
//        this.id = id;
//    }
//
//    public boolean isNew() {
//        return this.id == null;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//}
