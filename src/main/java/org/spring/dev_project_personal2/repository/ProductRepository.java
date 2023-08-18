package org.spring.dev_project_personal2.repository;

import org.spring.dev_project_personal2.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long>, JpaSpecificationExecutor<ProductEntity> {

    // HITS (조회수)
    @Modifying
    @Query(value = "update ProductEntity p set p.productHits=p.productHits+1 where p.id=:id")
    void updateHits(@Param("id") Long id);

    // DELETE (삭제)
    // 실제로는 UPDATE 쿼리문.
    @Modifying
    @Query("update ProductEntity p set p.productDisplay = false where p.id = :id")
    void softDelete(@Param("id") Long id);

}
