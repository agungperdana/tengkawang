package com.kratonsolution.belian.tengkawang.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kratonsolution.belian.tengkawang.model.Invoice;
import com.kratonsolution.belian.tengkawang.model.InvoiceStatus;

import lombok.NonNull;

/**
 * @author Agung Dodi Perdana
 * @email agung.dodi.perdana@gmail.com
 * @version 0.0.1
 */
public interface InvoiceRepository extends JpaRepository<Invoice, String> {

	public Optional<Invoice> findOneByNumber(@NonNull String number);
	
	public List<Invoice> findAllByOrganizationIn(@NonNull List<String> organizations);
	
	public List<Invoice> findAllByStatusAndOrganizationIn(@NonNull InvoiceStatus status, @NonNull List<String> organizations);
}
