package com.rbc.assignment.domain;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "stock_data")
public class StockData implements java.io.Serializable {
	
	private static final long serialVersionUID = 2741743113236609180L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;

	private Integer quarter;
	
	private String stock;
	
	private Date date;
	
	private BigDecimal open;
	
	private BigDecimal high;
	
	private BigDecimal low;
	
	private BigDecimal close;
	
	private BigInteger volume;
	
	private BigDecimal percentChangePrice;
	
	private BigDecimal percentChangeVolumeOverLastWk;
	
	private BigInteger previousWeeksVolume;
	
	private BigDecimal nextWeeksOpen;
	
	private BigDecimal nextWeeksClose;
	
	private BigDecimal percentChangeNextWeeksPrice;
	
	private Integer daysToNextDividend;
	
	private BigDecimal percentReturnNextDividend;

}
