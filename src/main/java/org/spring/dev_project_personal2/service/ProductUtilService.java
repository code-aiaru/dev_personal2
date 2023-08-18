package org.spring.dev_project_personal2.service;

import lombok.RequiredArgsConstructor;
import org.spring.dev_project_personal2.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductUtilService {

    private final ProductRepository productRepository;

    // HITS, DETAIL (SELECT)
    @Transactional
    public void updateHits(Long id) {
        // productRepository의 updateHits 메소드를 호출
        productRepository.updateHits(id);
    }
}
