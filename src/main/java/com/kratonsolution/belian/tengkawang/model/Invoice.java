package com.kratonsolution.belian.tengkawang.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com
 * @version 0.0.1
 */
@Getter
@Setter
@Entity
@Table(name = "invoice")
public class Invoice {
	
	@Id
	private String id = UUID.randomUUID().toString();
	
	@Column(name = "number")
	private String number;

	@Column(name = "issued_date")
	private LocalDate issuedDate;
	
	@Column(name = "due_date")
	private LocalDate dueDate;

	@Column(name = "payment_date")
	private LocalDateTime paymentDate;
	
	@Column(name = "total_device")
	private BigDecimal totalDevice;
	
	@Column(name = "unit_price")
	private BigDecimal unitPrice;
	
	@Column(name = "organization")
	private String organization;
	
	@Column(name = "invoice_status")
	@Enumerated(EnumType.STRING)
	private InvoiceStatus status = InvoiceStatus.UNPAID;
	
	@Version
	private Long version;
}
