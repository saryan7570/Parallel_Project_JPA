package com.bank.service;

import com.bank.entity.BankDetails;

public interface BankServiceInterface {
	public   BankDetails getAccountById(int id);

	public   void createAccount(BankDetails bank);

	public   void showBalance(BankDetails bank);

	public  void deposit(BankDetails bank);
	
	public void withdraw(BankDetails bank);
	
	public void printTransactions(int id);

	public  void commitTransaction();

	public void beginTransaction();

}
