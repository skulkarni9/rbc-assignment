package com.rbc.assignment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rbc.assignment.domain.StockData;

public interface StockDataRepository extends JpaRepository<StockData, Long> {
	
	List<StockData> findByStock(String stock);

}
