package org.spring.dev_project_personal2.dto;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.spring.dev_project_personal2.entity.ProductEntity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    private Long id;
    private String productName;
    private String productColor;
    private String productSize;
    private String productDescription;
    private int productHits;
    private String productWriter;
    private Boolean productDisplay;
    private LocalDateTime productCreatedTime;
    private LocalDateTime productUpdatedTime;

    public static ProductDTO toDTO(ProductEntity productEntity){
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(productEntity.getId());
        productDTO.setProductName(productEntity.getProductName());
        productDTO.setProductColor(productEntity.getProductColor());
        productDTO.setProductSize(productEntity.getProductSize());
        productDTO.setProductDescription(productEntity.getProductDescription());
        productDTO.setProductHits(productEntity.getProductHits());
        productDTO.setProductWriter(productEntity.getProductWriter());
        productDTO.setProductDisplay(productEntity.getProductDisplay());
        productDTO.setProductCreatedTime(productEntity.getCreatedTime());
        productDTO.setProductUpdatedTime(productEntity.getUpdatedTime());
        return productDTO;
    }

}
