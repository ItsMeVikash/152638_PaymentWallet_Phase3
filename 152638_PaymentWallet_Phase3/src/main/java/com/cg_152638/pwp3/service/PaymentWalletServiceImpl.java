package com.cg_152638.pwp3.service;

import com.cg_152638.pwp3.beans.Customer;
import com.cg_152638.pwp3.exception.IPaymentWalletException;
import com.cg_152638.pwp3.exception.InsufficientBalanceException;
import com.cg_152638.pwp3.repo.IPaymentRepo;
import com.cg_152638.pwp3.repo.PaymentWalletRepoImpl;

public class PaymentWalletServiceImpl implements IPaymentService {

	private IPaymentRepo repo = null;

	public PaymentWalletServiceImpl() {
		/*
		 * Instantiating DAO Layer
		 */
		repo = new PaymentWalletRepoImpl();
	}

	@Override
	public Customer getCustomerDetails(String mobileNumber) {
		return repo.getCustomerDetails(mobileNumber);
	}

	@Override
	public boolean addCustomer(Customer newCustomer) {
		return repo.addCustomer(newCustomer);
	}

	@Override
	public void depositMoney(Customer customer, double depositableAmount) {
		repo.depositMoney(customer, depositableAmount);
	}

	@Override
	public void withdrawMoney(Customer customer, double withdrawableAmount) throws InsufficientBalanceException {
		/*
		 * Checking Balance if sufficient for Withdrawal or not
		 */

		double newBalance = customer.getBalance() - withdrawableAmount;
		if (newBalance >= 1000) {
			repo.withdrawMoney(customer, withdrawableAmount);
		} else {
			throw new InsufficientBalanceException(IPaymentWalletException.MESSAGE5);
		}
	}

	@Override
	public String printTransaction(String mobileNumber) {
		return repo.printTransaction(mobileNumber);
	}

	@Override
	public void fundTransfer(Customer customer, Customer receiverCustomer, double transferableAmount)
			throws InsufficientBalanceException {
		/*
		 * Checking Balance if sufficient for Withdrawal or not PROJECT BY- VIKASH
		 * KUMAR(EMPID: 152638)
		 */
		double newBalance = customer.getBalance() - transferableAmount;
		if (newBalance >= 1000) {
			repo.fundTransfer(customer, receiverCustomer, transferableAmount);
		} else {
			throw new InsufficientBalanceException(IPaymentWalletException.MESSAGE5);
		}
	}

}
