package org.example.springsample.repository;

import org.example.springsample.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long>, QueryByExampleExecutor<Product>  {
    Optional<Product> findByCode(Long code);
    @Transactional
    void deleteByCode(Long code);
}
