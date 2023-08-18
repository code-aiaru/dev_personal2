package org.spring.dev_project_personal2.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.spring.dev_project_personal2.dto.ProductDTO;

import javax.persistence.*;


@Getter
@Setter
@Entity
@Table(name = "product_table")
public class ProductEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "product_color", nullable = false)
    private String productColor;

    @Column(name = "product_size", nullable = false)
    private String productSize;

    @Column(name = "product_description", length = 500, nullable = false)
    private String productDescription;

    @Column
    private int productHits;

    // 이 부분을 나중에 member_id로 바꾸면 될 것 같습니다.
    @Column(name = "product_writer", length = 20, nullable = false)
    private String productWriter;

    @ColumnDefault("true")
    private Boolean productDisplay;


    public static ProductEntity toEntity(ProductDTO productDTO){
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(productDTO.getId());
        productEntity.setProductName(productDTO.getProductName());
        productEntity.setProductColor(productDTO.getProductColor());
        productEntity.setProductSize(productDTO.getProductSize());
        productEntity.setProductDescription(productDTO.getProductDescription());
        productEntity.setProductHits(productDTO.getProductHits());
        productEntity.setProductWriter(productDTO.getProductWriter());
        productEntity.setProductDisplay(productDTO.getProductDisplay());
        return productEntity;
    }
}
