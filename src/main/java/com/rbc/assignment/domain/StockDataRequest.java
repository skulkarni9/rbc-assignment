package com.rbc.assignment.domain;

import java.math.BigDecimal;
import java.math.BigInteger;

import com.opencsv.bean.CsvBindByPosition;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StockDataRequest {
	
	@CsvBindByPosition(position = 0)
	private Integer quarter;
	
	@CsvBindByPosition(position = 1)
	private String stock;
	
	@CsvBindByPosition(position = 2)
	private String date;
	
	@CsvBindByPosition(position = 3)
	private String open;
	
	@CsvBindByPosition(position = 4)
	private String high;
	
	@CsvBindByPosition(position = 5)
	private String low;
	
	@CsvBindByPosition(position = 6)
	private String close;
	
	@CsvBindByPosition(position = 7)
	private BigInteger volume;
	
	@CsvBindByPosition(position = 8)
	private BigDecimal percentChangePrice;
	
	@CsvBindByPosition(position = 9)
	private BigDecimal percentChangeVolumeOverLastWk;
	
	@CsvBindByPosition(position = 10)
	private BigInteger previousWeeksVolume;
	
	@CsvBindByPosition(position = 11)
	private String nextWeeksOpen;
	
	@CsvBindByPosition(position = 12)
	private String nextWeeksClose;
	
	@CsvBindByPosition(position = 13)
	private BigDecimal percentChangeNextWeeksPrice;
	
	@CsvBindByPosition(position = 14)
	private Integer daysToNextDividend;
	
	@CsvBindByPosition(position = 15)
	private BigDecimal percentReturnNextDividend;

}
