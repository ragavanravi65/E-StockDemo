package com.stock.service.eStockService.controller;


import com.stock.service.eStockService.model.DTO.CompanyStockResponse;
import com.stock.service.eStockService.model.DTO.Stock;
import com.stock.service.eStockService.model.DTO.StockData;
import com.stock.service.eStockService.service.StockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1.0/market/stock")
@Slf4j
@CrossOrigin
public class StockController {

	@Autowired
	private StockService stockService;

	@PostMapping("/add/{companycode}")
	public ResponseEntity<Stock> addCompanyStock(@PathVariable("companycode") @NotBlank
													 @Size(min = 1) String companycode ,
												 @RequestHeader(value="stock-price",defaultValue="0.00",
														 required = true) Double StockPrice) {
		return ResponseEntity.ok().body(stockService.addStock(companycode,StockPrice));
	}
	
	@GetMapping("/get/{companycode}/{startdate}/{enddate}")
	public ResponseEntity<StockData> getStockList(@PathVariable("companycode") @NotBlank
								   @Size(min = 1) String companycode,
														@PathVariable("startdate") @DateTimeFormat(pattern="yyyy-MM-dd") Date fromDate,
														@PathVariable("enddate") @DateTimeFormat(pattern="yyyy-MM-dd") Date endDate) {
		StockData resultSet = stockService.getStockFromDate(companycode, fromDate, endDate);
		 return ResponseEntity.ok().body(resultSet);
	}


	@GetMapping(value="/getLatest")
	public ResponseEntity<CompanyStockResponse> getLatestStockDetail(@RequestHeader("companyCode")
														  @NotBlank
														  @Size(min = 1) String companycode ) {
		Stock resultSet = stockService.getLatestStockDetail(companycode);
		if(resultSet!=null)
		return ResponseEntity.ok().body(CompanyStockResponse.builder().stocks(Arrays.asList(resultSet)).error(null).build());

		return ResponseEntity.noContent().build();
	}

	@DeleteMapping(value="/deleteAll")
	public ResponseEntity<CompanyStockResponse> deleteAllCompanyData(@RequestHeader("companyCode")
													  @NotBlank
													  @Size(min = 1) String companycode ) {
		List<Stock> resultSet = stockService.deleteCompanyData(companycode);

		if(resultSet!=null)
			return ResponseEntity.ok().body(CompanyStockResponse.builder().stocks(resultSet).error(null).build());
		return new ResponseEntity<>(CompanyStockResponse.builder().error("Data doesn't exist to delete anymore").build(),
				HttpStatus.ALREADY_REPORTED);
	}
}
