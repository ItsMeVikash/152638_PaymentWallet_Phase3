package com.cg_152638.pwp3.repo;


import com.cg_152638.pwp3.beans.Customer;

public interface IPaymentRepo {

	public Customer getCustomerDetails(String mobileNumber);

	public boolean addCustomer(Customer newCustomer);

	public void depositMoney(Customer customer, double depositableAmount);

	public void withdrawMoney(Customer customer, double withdrawableAmount);

	public String printTransaction(String mobileNumber);

	public void fundTransfer(Customer customer, Customer receiverCustomer, double transferableAmount);

	

}
