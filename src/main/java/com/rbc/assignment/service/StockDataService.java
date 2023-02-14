package com.rbc.assignment.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rbc.assignment.domain.StockData;
import com.rbc.assignment.domain.StockDataRequest;
import com.rbc.assignment.repository.StockDataRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class StockDataService {
	
	@Autowired
	private StockDataRepository repository;

	public void processDataSet(List<StockDataRequest> dataset) {
		List<StockData> stockDatas = dataset.stream()
				                             .map(StockDataService::transformData)
				                             .collect(Collectors.toList());
		stockDatas = repository.saveAll(stockDatas);
		log.info("Processed {} stockData entries", stockDatas.size());
	}
	
	public List<StockData> getStockDataByTicker(String stock) {
		 return repository.findByStock(stock); 
	}
	
	@Transactional
	public StockData createStockData(StockDataRequest stockDataRequest) {
		StockData stockData = transformData(stockDataRequest);
		return repository.save(stockData);
	}

	private static StockData transformData(StockDataRequest data) {
		StockData stockData = new StockData();
		stockData.setQuarter(data.getQuarter());
		stockData.setStock(data.getStock());
		stockData.setDate(parseDate(data.getDate()));
		stockData.setOpen(parseAmount(data.getOpen()));
		stockData.setHigh(parseAmount(data.getHigh()));
		stockData.setLow(parseAmount(data.getLow()));
		stockData.setClose(parseAmount(data.getClose()));
		stockData.setVolume(data.getVolume());
		stockData.setPercentChangePrice(data.getPercentChangePrice());
		stockData.setPercentChangeVolumeOverLastWk(data.getPercentChangeVolumeOverLastWk());
		stockData.setPreviousWeeksVolume(data.getPreviousWeeksVolume());
		stockData.setNextWeeksOpen(parseAmount(data.getNextWeeksOpen()));
		stockData.setNextWeeksClose(parseAmount(data.getNextWeeksClose()));
		stockData.setPercentChangeNextWeeksPrice(data.getPercentChangeNextWeeksPrice());
		stockData.setDaysToNextDividend(data.getDaysToNextDividend());
		stockData.setPercentReturnNextDividend(data.getPercentReturnNextDividend());
		return stockData;
	}
	
	private static Date parseDate(String date) {
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		return Optional.ofNullable(date).map(d -> {
			try {
				return format.parse(d);
			} catch (ParseException e) {
				log.error("Unbale to parse the input date", e);
				return null;
			}
		}).orElse(null);
	}

	private static BigDecimal parseAmount(String amount) {
		return Optional.ofNullable(amount)
				.filter(a -> a.contains("$"))
				.map(a -> a.substring(a.indexOf("$") + 1))
				.map(BigDecimal::new)
				.orElse(null);
	}
}
