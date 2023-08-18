package org.spring.dev_project_personal2.repository;

import org.spring.dev_project_personal2.entity.ProductEntity;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecification {

    // DB의 productDisplay 값이 true 일 때만 반환하는 쿼리
    public static Specification<ProductEntity> isDisplayTrue(){
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("productDisplay"),true);
    }

    // DB에 저장된 productName의 값 중, searchKeyword에 입력된 값이 포함되어 있는 경우에만 반환하는 쿼리
    public static Specification<ProductEntity> productNameContains(String searchKeyword){
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("productName"), "%" + searchKeyword + "%");
    }

    // DB에 저장된 productDescription의 값 중, searchKeyword에 입력된 값이 포함되어 있는 경우에만 반환하는 쿼리
    public static Specification<ProductEntity> productDescriptionContains(String searchKeyword){
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("productDescription"), "%" + searchKeyword + "%");
    }




}
