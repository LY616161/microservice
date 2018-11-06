package com.itmuch.cloud.study.repository;

import com.itmuch.cloud.study.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Transactional(readOnly=true)
    @Query("select u from Product u where (u.productName like CONCAT('%',?1,'%') or ?1=null) and (u.productType = ?2 or ?2=null) and (u.productStatus = ?3 or ?3=null)")
    List<Product> search(String searchName,String searchType,String searchStatus);

    Page<Product> findAll(Pageable pageable);

    @Transactional(readOnly=true)
    @Query("select u from Product u where (u.productName like CONCAT('%',?1,'%') or ?1=null) and (u.productType = ?2 or ?2=null) and (u.productStatus = ?3 or ?3=null)")
    Page<Product> searchPage(String searchName,String searchType,String searchStatus,Pageable pageable);
}
