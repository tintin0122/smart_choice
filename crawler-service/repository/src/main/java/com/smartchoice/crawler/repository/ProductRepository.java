package com.smartchoice.crawler.repository;

import com.smartchoice.crawler.repository.model.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductModel, Long> {

    @Query("SELECT p FROM ProductModel p WHERE lower(p.productName) like %:productName%")
    List<ProductModel> searchProductByName(@Param("productName") String productName);
}
