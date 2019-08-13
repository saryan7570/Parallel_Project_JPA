package com.bank.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.bank.entity.BankDetails;
import com.bank.entity.TransactionDetails;

public class BankDAO implements BankDAOInterface {

	EntityManager entity = UtilJava.getEntityManager();

	@Override
	public BankDetails getAccountById(int id) {
		BankDetails bank = entity.find(BankDetails.class, id);
		return bank;
	}

	@Override
	public void CreateAccount(BankDetails bank) {
		entity.persist(bank);
	}

	@Override
	public void ShowBalance(BankDetails bank) {
		// TODO Auto-generated method stub
	}

	@Override
	public void Deposit(BankDetails bank) {
		entity.merge(bank);
	}

	@Override
	public void Withdraw(BankDetails bank) {
		entity.merge(bank);
	}

	public void PrintTransactions(int id) {
		Query q = entity.createQuery("select t from TransactionDetails t where accId=:tid");
		q.setParameter("tid", id);
		List<TransactionDetails> l = q.getResultList();
		System.out.println("TransactionId		Account Id		TrasactionType		Amount");
		System.out.println("------------------------------------------------------------------");
		for (TransactionDetails tdetails : l) {
			System.out.println(tdetails.getTransactionId() + "			" + tdetails.getAccId() + "			"
					+ tdetails.getTransactionType() + "		" + tdetails.getAmount());
		}
	}

	@Override
	public void commitTransaction() {
		entity.getTransaction().commit();
	}

	@Override
	public void beginTransaction() {
		entity.getTransaction().begin();
	}

	public void addTransaction(TransactionDetails trans) {
		entity.persist(trans);
	}
}