package com.cg_152638.pwp3.repo;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.EntityManager;
import com.cg_152638.pwp3.beans.Customer;
import com.cg_152638.pwp3.util.JPAUtil;

public class PaymentWalletRepoImpl implements IPaymentRepo {

	private EntityManager entityManager = null;

	public PaymentWalletRepoImpl() {
		entityManager = JPAUtil.getEntityManager();
	}

	@Override
	public Customer getCustomerDetails(String mobileNumber) {
		Customer customer = entityManager.find(Customer.class, mobileNumber);
		return customer;
	}

	@Override
	public boolean addCustomer(Customer newCustomer) {
		boolean result = false;
		entityManager.getTransaction().begin();
		if (!entityManager.contains(newCustomer)) {
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MMMM-yyyy hh:mm a");
			String strDate = sdf.format(date);
			String trans = "Account Created on \t" + strDate + "\nAmount\tType\t\t\tDate\t\tRemaining Balance"
					+ "\n---------------------------------------------------------------------------------------\n";
			newCustomer.setTransaction(trans);
			entityManager.persist(newCustomer);
			entityManager.getTransaction().commit();
			result = true;
		}
		return result;
	}

	@Override
	public void depositMoney(Customer customer, double depositableAmount) {
		entityManager.getTransaction().begin();
		double newBalance = customer.getBalance() + depositableAmount;
		customer.setBalance(newBalance);
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMMM-yyyy hh:mm a");
		String strDate = sdf.format(date);
		String statement = customer.getTransaction().toString() + "\n\u20B9" + depositableAmount + "\tDeposited\t"
				+ strDate + "\t\u20B9" + customer.getBalance();
		customer.setTransaction(statement);
		entityManager.merge(customer);
		entityManager.getTransaction().commit();

	}

	@Override
	public void withdrawMoney(Customer customer, double withdrawableAmount) {
		entityManager.getTransaction().begin();
		double newBalance = customer.getBalance() - withdrawableAmount;
		customer.setBalance(newBalance);
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMMM-yyyy hh:mm a");
		String strDate = sdf.format(date);
		String statement = customer.getTransaction().toString() + "\n\u20B9" + withdrawableAmount + "\tWithdrawn\t"
				+ strDate + "\t\u20B9" + customer.getBalance();
		customer.setTransaction(statement);
		entityManager.merge(customer);
		entityManager.getTransaction().commit();
	}

	@Override
	public String printTransaction(String mobileNumber) {
		Customer customer = entityManager.find(Customer.class, mobileNumber);
		return customer.getTransaction();
	}

	@Override
	public void fundTransfer(Customer customer, Customer receiverCustomer, double transferableAmount) {
		entityManager.getTransaction().begin();
		double customernewBalance = customer.getBalance() - transferableAmount;
		customer.setBalance(customernewBalance);
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMMM-yyyy hh:mm a");
		String strDate = sdf.format(date);
		String statement = customer.getTransaction().toString() + "\n\u20B9" + transferableAmount + "\tTransfered To\t"
				+ strDate + "\t\u20B9" + customer.getBalance();
		customer.setTransaction(statement);
		entityManager.merge(customer);
		double receiverCustomernewBalance = receiverCustomer.getBalance() + transferableAmount;
		receiverCustomer.setBalance(receiverCustomernewBalance);
		String statement2 = receiverCustomer.getTransaction().toString() + "\n\u20B9" + transferableAmount
				+ "\tTransfered from\t" + strDate + "\t\u20B9" + receiverCustomer.getBalance();
		receiverCustomer.setTransaction(statement2);
		entityManager.merge(receiverCustomer);
		entityManager.getTransaction().commit();

	}

}
