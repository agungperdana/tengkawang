package com.kratonsolution.belian.tengkawang.controller.integration;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.kratonsolution.belian.tengkawang.model.Invoice;

import lombok.NonNull;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com
 * @version 0.0.1
 */
public class Xendit {

	private final String XENDIT_INVOICE_URL = "https://api.xendit.co/v2/invoices";

	private RestTemplate template = new RestTemplate();
	
	private HttpHeaders header = new HttpHeaders();
	
	Xendit() {
	}
	
	public static Xendit init(@NonNull String authorization) {
		
		Xendit xendit = new Xendit();
		xendit.header.setContentType(MediaType.APPLICATION_JSON);
		xendit.header.set("Authorization", "Basic "+authorization);
	
		return xendit;
	}
	
	public String getXenditURL(@NonNull Invoice invoice) {
		
		Map<String, String> map = new HashMap<>();
		map.put("external_id", "INV "+invoice.getNumber());
		map.put("amount", invoice.getTotalDevice().multiply(invoice.getUnitPrice()).toPlainString());
		map.put("payer_email", "urusan.tak.penting@gmail.com");
		map.put("description", "Invoice");
		map.put("currency", "IDR");
		map.put("should_send_email", "true");
		map.put("invoice_duration ", "432000");
		
		HttpEntity<Map<String, String>> request = new HttpEntity<>(map, header);
		
		ResponseEntity<XenditResponse> json = template.exchange(XENDIT_INVOICE_URL, HttpMethod.POST, request, XenditResponse.class);
		
		return json.getBody().getInvoice_url();
	}
}
