package com.cg_152638.pwp3.service;

import com.cg_152638.pwp3.beans.Customer;
import com.cg_152638.pwp3.exception.InsufficientBalanceException;

public interface IPaymentService {

	public Customer getCustomerDetails(String mobileNumber);

	public boolean addCustomer(Customer newCustomer);

	public void depositMoney(Customer customer, double depositableAmount);

	public void withdrawMoney(Customer customer, double withdrawableAmount) throws InsufficientBalanceException;

	public String printTransaction(String mobileNumber);

	public void fundTransfer(Customer customer, Customer receiverCustomer, double transferableAmount)
			throws InsufficientBalanceException;

}
