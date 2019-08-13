package com.bank.main;

import java.util.Scanner;

import com.bank.entity.BankDetails;
import com.bank.entity.TransactionDetails;
import com.bank.service.BankService;
import com.bank.service.Validator;

public class BankModules {
	
	Scanner scanner = new Scanner(System.in);
	BankService service;
	BankDetails bank;
	Validator validator;
	TransactionDetails transaction;
	TransactionDetails transaction2 = new TransactionDetails();

	public void createAccount() throws InvalidException {
		service = new BankService();
		bank = new BankDetails();
		validator = new Validator();
		System.out.print("Enter Account Holder Name: ");
		String name = scanner.next();
		if(validator.isNameValid(name)) {
			bank.setName(name);
			System.out.print("Enter initial amount: ");
			int amount = scanner.nextInt();
			if(validator.isBalanceValid(amount)) {
				bank.setAccBalance(amount);
				System.out.print("Enter Mobile Number: ");
				String mobileNo = scanner.next();
				bank.setMobileno(mobileNo);
				bank.setAccNo();
				service.createAccount(bank);
				System.out.println("Account Created Successfully");
				int id = bank.getAccNo();
				service.getAccountById(id);
				System.out.println("Your Account number is: " + bank.getAccNo());
			}
			else {
				throw new InvalidException("Enter valid amount");
			}
		}
		else {
			throw new InvalidException("Enter valid name");
		}
	}
	
	public void showBalance() throws InvalidException {
		service = new BankService();
		bank = new BankDetails();
		validator = new Validator();
		System.out.print("Enter your account number: ");
		int accNo = scanner.nextInt();
		if (validator.isAccountNumberValid(accNo)) {
			bank = service.getAccountById(accNo);
			System.out.println("Name: " + bank.getName());
			System.out.println("Your Account Balance is: " + bank.getAccBalance());
		}
		else {
			throw new InvalidException("Enter valid Account Number");
		}
	}
	
	public void deposit() throws InvalidException {
		service = new BankService();
		bank = new BankDetails();
		validator = new Validator();
		System.out.print("Enter your Account number: ");
		int accNo = scanner.nextInt();
		if (validator.isAccountNumberValid(accNo)) {
			bank = service.getAccountById(accNo);
			System.out.println("Name: " + bank.getName());
			System.out.println("Your Account Balance is: " + bank.getAccBalance());
			int initBal = bank.getAccBalance();
			System.out.print("Enter the Amount you want to Deposit: ");
			int depAmt = scanner.nextInt();
			if(validator.isBalanceValid(depAmt)) {
				bank.setAccBalance(initBal + depAmt);
				service.deposit(bank);

				// Updating In Transaction Table
				
				transaction2.setTransactionType("Deposit");
				transaction2.setAccId(accNo);
				transaction2.setAmount(depAmt);
				service.addTransaction(transaction2);
				bank.setT(transaction2);
				System.out.println("Your Account Balance after Depositing: " +bank.getAccBalance());
			}
			else {
				throw new InvalidException("Enter valid amount");
			}
		}
		else {
			throw new InvalidException("Enter valid Account number");
		}
	}
	
	public void withdraw() throws InvalidException {
		service = new BankService();
		bank = new BankDetails();
		validator = new Validator();
		System.out.print("Enter your Account number: ");
		int accNo = scanner.nextInt();
		if (validator.isAccountNumberValid(accNo)) {
			bank = service.getAccountById(accNo);
			System.out.println(" Your Account Balance is: " + bank.getAccBalance());
			int initBal = bank.getAccBalance(); // initial balance
			
			System.out.print("Enter the Amount you to Withdraw: ");
			int amount = scanner.nextInt();
			
			if(validator.isBalanceValid(amount) && (initBal>amount)) {
				bank.setAccBalance(initBal - amount);
				service.withdraw(bank);

				// Updating In Transaction Table
				
				transaction2.setTransactionType("Withdraw");
				transaction2.setAccId(accNo);
				transaction2.setAmount(amount);
				service.addTransaction(transaction2);
				bank.setT(transaction2);

				bank = service.getAccountById(accNo);
				System.out.println("Hello " + bank.getName());
				System.out.println("Your Remaining Account Balance after Withdraw: " +bank.getAccBalance());
			}
			else {
				throw new InvalidException("Enter valid Amount");
			}
		}
		else {
			throw new InvalidException("Enter valid Account Number");
		}
	}
	
	public void fundTransfer() throws InvalidException {
		service = new BankService();
		bank = new BankDetails();
		validator = new Validator();
		transaction = new TransactionDetails();
		System.out.print("Enter the Sender Account Number: ");
		int senderAccNo = scanner.nextInt(); // From Account Id (Sender)
		if (validator.isAccountNumberValid(senderAccNo)) {
			bank = service.getAccountById(senderAccNo);
			int senderBalance = bank.getAccBalance(); // Initial Balance
			System.out.println("Enter the amount you want to transfer");
			int amount = scanner.nextInt(); // Amount to be transfered
			if(validator.isBalanceValid(amount)) {
				
				System.out.print("Enter Reciever Account Number: ");
				int recAccNo = scanner.nextInt();
				
				if (validator.isAccountNumberValid(recAccNo)) {
					// Removing the Balance transfered from sender Account
					
					bank = service.getAccountById(senderAccNo);
					int remBalance = senderBalance - amount;
					bank.setAccBalance(remBalance);
					service.deposit(bank);

					// updating the Balance recieved from Sender
					
					bank = service.getAccountById(recAccNo);
					int initBalance = bank.getAccBalance();
					int updateBalance = initBalance + amount;
					bank.setAccBalance(updateBalance);
					service.deposit(bank);

					// updating In transaction Table
					
					transaction.setTransactionType("Transfered to " + recAccNo);
					transaction.setAccId(senderAccNo);
					transaction.setAmount(amount);
					service.addTransaction(transaction);
					bank.setT(transaction);
					int a = transaction.getTransactionId();

					/*
					 * //Updating In transaction Table trans1.setTransactionId(a);
					 * trans1.setTransactionType("Recieved From"+fid); trans1.setAccId(tid);
					 * trans1.setAmount(fund); service.addTransaction(trans1); bank.setT(trans1);
					 * 
					 */

					bank = service.getAccountById(senderAccNo);
					System.out.println("Remaining balance in account " +bank.getAccBalance());
				}
				else {
					throw new InvalidException("Enter valid Account Number");
				}
			}
			else {
				throw new InvalidException("Enter valid Amount");
			}
		}
		else {
			throw new InvalidException("Enter Valid Account Number");
		}
	}
	
	public void printTransactions() throws InvalidException {
		service = new BankService();
		validator = new Validator();
		System.out.print("Enter Account number: ");
		int accNo = scanner.nextInt();
		if(validator.isAccountNumberValid(accNo)) {
			service.printTransactions(accNo);
		}
		else {
			throw new InvalidException("Enter Valid Account Number");
		}
	}
	
}
