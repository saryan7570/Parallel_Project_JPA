package com.bank.service;

public class Validator {

	
	public boolean isNameValid(String name) {
		if(name.matches( "[A-Z][a-z]*" )) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean isBalanceValid(int amount) {
		if(amount > 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean isAccountNumberValid(int accNo) {
		if(accNo>0) {
			return true;
		}
//		BankService service = new BankService();
//		if(service.getAccountById(accNo) != null) {
//			return true;
//		}
		else {
			return false;
		}
	}
}