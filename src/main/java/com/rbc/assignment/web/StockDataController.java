package com.rbc.assignment.web;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.rbc.assignment.StockDataException;
import com.rbc.assignment.domain.StockData;
import com.rbc.assignment.domain.StockDataRequest;
import com.rbc.assignment.service.StockDataService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/stockdata")
public class StockDataController {

	@Autowired
	private StockDataService service;

	@PostMapping("/task/upload")
	public ResponseEntity<String> uploadDataSet(@RequestParam("file") MultipartFile file) {
		String filename = file.getOriginalFilename();
		validateFileExtension(filename);
		List<StockDataRequest> dataset = parseFile(file);
		service.processDataSet(dataset);
		return ResponseEntity.ok("DataSet imported successfully");
	}

	@GetMapping("/{stockTicker}")
	public ResponseEntity<List<StockData>> getStockDataByTicker(@PathVariable("stockTicker") String stockTicker) {
		List<StockData> dataSet = service.getStockDataByTicker(stockTicker);
		return ResponseEntity.ok(dataSet);
	}

	@PostMapping
	public ResponseEntity<StockData> createStockData(@RequestBody StockDataRequest stockDataRequest) {
		StockData stockData = service.createStockData(stockDataRequest);
		return ResponseEntity.ok(stockData);
	}

	private static void validateFileExtension(String filename) {
		Optional.ofNullable(filename).filter(f -> f.contains(".")).map(f -> f.substring(filename.lastIndexOf(".") + 1))
				.filter(extension -> "data".equals(extension))
				.orElseThrow(() -> new StockDataException(HttpStatus.BAD_REQUEST, "Invalid File Format"));

	}

	private static List<StockDataRequest> parseFile(MultipartFile file) {
		try (Reader reader = new InputStreamReader(file.getInputStream())) {
			CsvToBean<StockDataRequest> cb = new CsvToBeanBuilder<StockDataRequest>(reader)
					.withType(StockDataRequest.class).withSkipLines(1).build();
			return cb.parse();
		} catch (IOException e) {
			log.error("Error importing dataset", e);
			throw new StockDataException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}

}
