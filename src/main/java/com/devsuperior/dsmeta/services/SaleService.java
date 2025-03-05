package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import com.devsuperior.dsmeta.dto.SaleSummaryMinDTO;
import com.devsuperior.dsmeta.dto.SalesReportMinDTO;
import com.devsuperior.dsmeta.projections.SaleSummaryMinProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;
	
	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}

	public List<SaleSummaryMinDTO> searchSummary(String minDateStr, String maxDateStr) {
		LocalDate minDate;
		LocalDate maxDate;

		if (maxDateStr == null || maxDateStr.trim().isEmpty()) {
			maxDate = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		} else {
			maxDate = LocalDate.parse(maxDateStr);
		}

		if (minDateStr == null || minDateStr.trim().isEmpty()) {
			minDate = maxDate.minusYears(1L);
		} else {
			minDate = LocalDate.parse(minDateStr);
		}

		List<SaleSummaryMinProjection> saleSummaryProj = repository.searchSummary(minDate, maxDate);
		return saleSummaryProj.stream().map(SaleSummaryMinDTO::new).toList();
	}

	public Page<SalesReportMinDTO> searchReport(String minDateStr, String maxDateStr, String name, Pageable pageable) {
		LocalDate minDate;
		LocalDate maxDate;

		if (maxDateStr == null || maxDateStr.trim().isEmpty()) {
			maxDate = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
		} else {
			maxDate = LocalDate.parse(maxDateStr);
		}

		if (minDateStr == null || minDateStr.trim().isEmpty()) {
			minDate = maxDate.minusYears(1L);
		} else {
			minDate = LocalDate.parse(minDateStr);
		}

		Page<Sale> result = repository.searchReport(minDate, maxDate, name, pageable);
		return result.map(SalesReportMinDTO::new);
	}
}
