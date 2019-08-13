package com.bank.dao;

import com.bank.entity.BankDetails;

public interface BankDAOInterface {
	public   BankDetails getAccountById(int id);

	public   void CreateAccount(BankDetails bank);

	public   void ShowBalance(BankDetails bank);

	public  void Deposit(BankDetails bank);
	
	public void Withdraw(BankDetails bank);
	
	public void PrintTransactions(int id);

	public  void commitTransaction();

	public void beginTransaction();

}
