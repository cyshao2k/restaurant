package cl.transbank.tech.restaurant.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cl.transbank.tech.restaurant.service.BillManager;
import cl.transbank.tech.restaurant.to.Bill;

@RestController
@RequestMapping("bills")
public class BillsController {
	
	@Autowired
	private BillManager billManager;
	
	@GetMapping
	public List<Bill> listSalesOfDay(@RequestParam("filterByDay") 
			@DateTimeFormat(pattern="yyyyMMdd") Date fromDate) {
		
		return billManager.listSalesOfDay(fromDate);
	}

}
