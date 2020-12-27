package com.kratonsolution.belian.tengkawang.integration.config;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.kratonsolution.belian.tengkawang.model.Invoice;
import com.kratonsolution.belian.tengkawang.model.InvoiceStatus;
import com.kratonsolution.belian.tengkawang.service.DeviceService;
import com.kratonsolution.belian.tengkawang.service.InvoiceService;
import com.kratonsolution.belian.tengkawang.util.CommandCodeGenerator;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com
 * @version 0.0.1
 */
@Slf4j
@Configuration
@EnableScheduling
public class InvoiceConfig {

	@Autowired
	private DeviceService deviceService;
	
	@Autowired
	private InvoiceService invoiceService;
	
	@Autowired
	private CommandCodeGenerator gen;
	
	public InvoiceConfig() {
		log.info("started scheduling config");
	}
	
	@Scheduled(cron = "0 30 15 27 * *")
	public void generateBilling() {
		
		Map<String, BigDecimal> map = new HashMap<String, BigDecimal>();
		
		deviceService.getAll().forEach(dev -> {
			
			if(map.containsKey(dev.getOrganization())) {
				
				map.get(dev.getOrganization()).add(BigDecimal.ONE);
			}
			else {
				map.put(dev.getOrganization(), BigDecimal.ONE);
			}
		});
		
		map.keySet().forEach(org -> {
			
			Invoice invoice = new Invoice();
			invoice.setIssuedDate(LocalDate.now());
			invoice.setDueDate(invoice.getIssuedDate().withDayOfMonth(5));
			invoice.setNumber(gen.generate());
			invoice.setOrganization(org);
			invoice.setStatus(InvoiceStatus.UNPAID);
			invoice.setTotalDevice(map.get(org));
			invoice.setUnitPrice(BigDecimal.valueOf(500000));
			
			invoiceService.add(invoice);
		});
	}
}
