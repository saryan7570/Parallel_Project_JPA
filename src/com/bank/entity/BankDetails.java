package com.bank.entity;

import java.util.Random;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "BankInfo")
public class BankDetails {
	
	@Id
	private int accNo;
	
	@Column(length = 20)
	private String name;
	
	@Column(length = 10)
	private String mobileno;
	
//private String address;
	@Column(length = 10)
	private int accBalance;
	
	@OneToOne
	private TransactionDetails t;

	public TransactionDetails getT() {
		return t;
	}

	public void setT(TransactionDetails t) {
		this.t = t;
	}

//Random Function
	public int ran(int min, int max) {
		Random random = new Random();
		return random.nextInt((max - min) + 1) + min;
	}

	public int getAccNo() {
		return accNo;
	}

	public void setAccNo() {
		this.accNo = ran(100000, 999999);
	}

	public String getName() {
		return name;
	}

	public void setName(String accountantName) {
		this.name = accountantName;
	}

	public String getMobileno() {
		return mobileno;
	}

	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}

	public int getAccBalance() {
		return accBalance;
	}

	public void setAccBalance(int accBalance) {
		this.accBalance = accBalance;
	}
}
