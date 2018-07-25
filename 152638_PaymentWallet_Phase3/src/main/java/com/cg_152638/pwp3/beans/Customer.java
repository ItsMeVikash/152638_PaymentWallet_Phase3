package com.cg_152638.pwp3.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "customerjpa")
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(length = 10)
	private String mobileNumber;
	@Column(length = 50)
	private String name;
	@Lob
	private String transaction;
	private double balance;

	public Customer() {
		// TODO Auto-generated constructor stub
	}

	public Customer(String mobileNumber, String name, String transaction, double balance) {
		super();
		this.mobileNumber = mobileNumber;
		this.name = name;
		this.transaction = transaction;
		this.balance = balance;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getTransaction() {
		return transaction;
	}

	public void setTransaction(String string) {
		this.transaction = string;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
