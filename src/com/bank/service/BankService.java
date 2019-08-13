package com.bank.service;

import com.bank.dao.BankDAO;
import com.bank.entity.BankDetails;
import com.bank.entity.TransactionDetails;

public class BankService implements BankServiceInterface {
	BankDAO dao = new BankDAO();

	@Override
	public BankDetails getAccountById(int id) {
		BankDetails bank = dao.getAccountById(id);
		return bank;
	}

	@Override
	public void createAccount(BankDetails bank) {
		dao.beginTransaction();
		dao.CreateAccount(bank);
		dao.commitTransaction();
	}

	@Override
	public void showBalance(BankDetails bank) {
		// TODO Auto-generated method stub
	}

	@Override
	public void deposit(BankDetails bank) {
		dao.beginTransaction();
		dao.Deposit(bank);
		dao.commitTransaction();
	}

	@Override
	public void withdraw(BankDetails bank) {
		dao.beginTransaction();
		dao.Withdraw(bank);
		dao.commitTransaction();
	}

	@Override
	public void printTransactions(int id) {
		dao.PrintTransactions(id);
	}

	@Override
	public void commitTransaction() {
		dao.commitTransaction();
	}

	@Override
	public void beginTransaction() {
		dao.beginTransaction();
	}

	public void addTransaction(TransactionDetails transaction) {
		dao.beginTransaction();
		dao.addTransaction(transaction);
		dao.commitTransaction();
	}
}