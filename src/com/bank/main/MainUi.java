package com.bank.main;

import java.util.Scanner;

public class MainUi {
	@SuppressWarnings("resource")
	public static void main(String[] args) throws InvalidException {

		BankModules bankModules = new BankModules();
		Scanner scanner = new Scanner(System.in);

		do {
			System.out.println("************************************");
			System.out.println("*************XYZ BANK***************");
			System.out.println("************************************");
			System.out.println(
					"\n 1. CreateAccount \n 2. Show Balance \n 3. Deposit \n 4. WithDraw \n 5. Fund Transfer \n 6. Print Transaction \n 7. Exit");
			System.out.println("************************************");
			System.out.print("Enter your choice: ");

			int option = scanner.nextInt();

			switch (option) {
			
			case 1:
				bankModules.createAccount();
				break;
				
			case 2:
				bankModules.showBalance();
				break;

			case 3:
				bankModules.deposit();
				break;
				
			case 4:
				bankModules.withdraw();
				break;
				
			case 5:
				bankModules.fundTransfer();
				break;

			case 6:
				bankModules.printTransactions();
				break;
				
			case 7:
				System.out.println("Thanks for Banking with Us");
				System.exit(0);
			}
		} while (true);
	}
}