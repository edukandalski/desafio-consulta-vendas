package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.projections.SaleSummaryMinProjection;

public class SaleSummaryMinDTO {

    private String sellerName;
    private Double total;

    public SaleSummaryMinDTO(String sellerName, Double total) {
        this.sellerName = sellerName;
        this.total = total;
    }

    public SaleSummaryMinDTO(SaleSummaryMinProjection projection) {
        sellerName = projection.getSellerName();
        total = projection.getTotal();
    }

    public String getSellerName() {
        return sellerName;
    }

    public Double getTotal() {
        return total;
    }
}
