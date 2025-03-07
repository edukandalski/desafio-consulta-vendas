package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.projections.SaleSummaryMinProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.dsmeta.entities.Sale;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query(nativeQuery = true, value = "SELECT seller.name as sellerName, SUM(sale.amount) as total " +
            "FROM tb_sales as sale " +
            "INNER JOIN tb_seller as seller ON seller.id = sale.seller_id " +
            "WHERE sale.date BETWEEN :minDate AND :maxDate " +
            "GROUP BY seller.name " +
            "ORDER BY seller.name")
    List<SaleSummaryMinProjection> searchSummary(LocalDate minDate, LocalDate maxDate);

    @Query(value = "SELECT obj FROM Sale obj " +
                    "JOIN FETCH obj.seller " +
                    "WHERE obj.date BETWEEN :minDate AND :maxDate " +
                    "AND UPPER(obj.seller.name) LIKE UPPER(CONCAT('%', :name, '%'))",
            countQuery = "SELECT COUNT(obj) FROM Sale obj JOIN obj.seller " +
                            "WHERE obj.date BETWEEN :minDate AND :maxDate " +
                            "AND UPPER(obj.seller.name) LIKE UPPER(CONCAT('%', :name, '%'))")
    Page<Sale> searchReport(LocalDate minDate, LocalDate maxDate, String name, Pageable pageable);
}
