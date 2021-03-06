package com.kratonsolution.belian.tengkawang.service;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kratonsolution.belian.tengkawang.auth.Constant;
import com.kratonsolution.belian.tengkawang.controller.integration.Xendit;
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
			invoice.setXenditURL(Xendit.init(Constant.XENDIT_KEY).getXenditURL(invoice));
			repo.save(invoice);
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
	
	public static void main(String[] args) {
		System.out.println(Base64.getEncoder().encodeToString("xnd_development_fABUo7n1o8i43UZPpN2q54HvS5NRqcfwRqw8e410sSaytm2oAOZNj9cw08OdXO5:".getBytes()));
	}
}
