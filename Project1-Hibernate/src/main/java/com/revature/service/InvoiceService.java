package com.revature.service;

import java.util.List;

import com.revature.dao.InvoiceDao;
import com.revature.dao.impl.InvoiceDaoImpl;
import com.revature.model.Invoice;

public class InvoiceService {
	
	private InvoiceDao invoiceDao = new InvoiceDaoImpl();

	public InvoiceService() {
		super();
	}
	
	public List<Invoice> getInvoiceTable(){
		return invoiceDao.getInvoiceTable();
	}
	
	public Invoice getInvoiceById(int userId) {
		return invoiceDao.getInvoiceById(userId);
	}
	
	public String createInvoice(Invoice invoice) {
		int invoicesCreated = invoiceDao.createInvoice(invoice);
		if(invoicesCreated != 0) {
			return "\n Invoice Created! \n";
		}
		return "\n Error! Could not create invoice. \n";
	}

}

