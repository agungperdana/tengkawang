package com.kratonsolution.belian.tengkawang.service;

import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.kratonsolution.belian.tengkawang.auth.Constant;
import com.kratonsolution.belian.tengkawang.model.Invoice;
import com.kratonsolution.belian.tengkawang.model.InvoiceStatus;
import com.kratonsolution.belian.tengkawang.repository.InvoiceRepository;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com
 * @version 0.0.1
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class InvoiceService {
	
	public static final String XENDIT_INVOICE_URL = "https://api.xendit.co/v2/invoices";
	
	private RestTemplate template = new RestTemplate();

	@Autowired
	private InvoiceRepository repo;

	public List<Invoice> getAll() {
		return repo.findAll();
	}
	
	public List<Invoice> getAll(List<String> organizations) {
		return repo.findAllByOrganizationIn(organizations);
	}
	
	public List<Invoice> getAll(@NonNull InvoiceStatus status, List<String> organizations) {
		return repo.findAllByStatusAndOrganizationIn(status, organizations);
	}
	
	public Optional<Invoice> getById(@NonNull String id) {
		return repo.findById(id);
	}
	
	public Optional<Invoice> getByNumber(@NonNull String number) {
		return repo.findOneByNumber(number);
	}
	
	public void add(@NonNull Invoice invoice) {
		
		Optional<Invoice> opt = getByNumber(invoice.getNumber());
		if(opt.isEmpty()) {
			
			repo.save(invoice);
			log.info("Creating new invoice {}", invoice.getNumber());
			createXenditInvoice(invoice);
		}
		else {
			log.info("Creating new invoice canceled, data already exist {}", invoice.getNumber());
		}
	}
	
	public void update(@NonNull Invoice invoice) {
		
		Optional<Invoice> opt = getByNumber(invoice.getNumber());
		if(opt.isPresent()) {
			
			repo.save(invoice);
			log.info("Updating invoice {}", invoice.getNumber());
		}
	}
	
	public void delete(@NonNull String id) {
		repo.deleteById(id);
		log.info("Deleting invoice {}", id);
	}
	
	private void createXenditInvoice(@NonNull Invoice invoice) {
		
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		header.set("Authorization", Constant.XENDIT_KEY);
		
		Map<String, String> map = new HashMap<>();
		map.put("external_id", "INV "+invoice.getNumber());
		map.put("amount", invoice.getTotalDevice().multiply(invoice.getUnitPrice()).toPlainString());
		map.put("payer_email", "urusan.tak.penting@gmail.com");
		map.put("description", "");
		map.put("currency", "IDR");
		map.put("should_send_email", "true");
		map.put("invoice_duration ", "432000");
		
		HttpEntity<Map<String, String>> request = new HttpEntity<>(map, header);
		
		ResponseEntity<String> json = template.exchange(XENDIT_INVOICE_URL, HttpMethod.POST, request, String.class);
		
		log.info("Creating new xendit invoice {}", json.getBody());
	}
	
	public static void main(String[] args) {
		System.out.println(Base64.getEncoder().encodeToString("xnd_development_fABUo7n1o8i43UZPpN2q54HvS5NRqcfwRqw8e410sSaytm2oAOZNj9cw08OdXO5:".getBytes()));
	}
}
