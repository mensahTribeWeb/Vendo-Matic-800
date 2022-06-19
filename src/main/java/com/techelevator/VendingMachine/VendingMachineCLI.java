package com.techelevator.VendingMachine;

import java.math.BigDecimal;
import java.util.Scanner;

public class VendingMachineCLI {
Scanner input =new Scanner(System.in);
	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_Exit = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_Exit };

	private static final String PURCHASE_MENU_OPTION_FEED_MONEY = "Feed Money";
	private static final String PURCHASE_MENU_OPTION_SELECT_PRODUCT = "Select Product";
	private static final String PURCHASE_MENU_OPTION_FINISH_TRANSACTION = "Finish Transaction";
	private static final String[] PURCHASE_MENU_OPTIONS = { PURCHASE_MENU_OPTION_FEED_MONEY, PURCHASE_MENU_OPTION_SELECT_PRODUCT , PURCHASE_MENU_OPTION_FINISH_TRANSACTION };

	private static final String FEED_MENU_OPTION_ONE = "$1.00";
	private static final String FEED_MENU_OPTION_TWO = "$2.00";
	private static final String FEED_MENU_OPTION_FIVE = "$5.00";
	private static final String FEED_MENU_OPTION_TEN = "$10.00";
	private static final String FEED_MENU_OPTION_EXIT = "Go Back";
	private static final String[] FEED_MENU_OPTIONS = { FEED_MENU_OPTION_ONE, FEED_MENU_OPTION_TWO, FEED_MENU_OPTION_FIVE, FEED_MENU_OPTION_TEN, FEED_MENU_OPTION_EXIT };


	private Menu menu;
	private VendingFunctions vm = new VendingFunctions();

	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}

	public void run() {
		LogFile logger = new LogFile();
		vm.refill();
		//Beginning Menu
		while(true) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				// display vending machine items
				System.out.println(vm.displayItems());
			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				// Purchase Menu
				while (true) {
					System.out.println("\nCurrent Balance is $" + vm.getAvailableFunds());
					String purchaseChoice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);

					if (purchaseChoice.equals(PURCHASE_MENU_OPTION_FEED_MONEY)) {
						//Feed Money Menu
						while (true){
							System.out.println("\nCurrent Balance is $" + vm.getAvailableFunds());
							String feedChoice = (String) menu.getChoiceFromOptions(FEED_MENU_OPTIONS);

							if (feedChoice.equals(FEED_MENU_OPTION_EXIT)) {
								break;
							}
							vm.feedMoney(new BigDecimal(feedChoice.replace("$", "")));
						}
					} else if (purchaseChoice.equals(PURCHASE_MENU_OPTION_SELECT_PRODUCT)) {
						System.out.println("\nCurrent Balance is $" + vm.getAvailableFunds());
						System.out.println(vm.displayItems());
						System.out.println("Enter Product: ");
						menu.getChoiceForSpecificItem(vm);
					} else if (purchaseChoice.equals(PURCHASE_MENU_OPTION_FINISH_TRANSACTION)) {
						Change change = new Change();
						logger.logChange(vm.getAvailableFunds());
						System.out.println(change.calculateChange(vm.getAvailableFunds()));
						vm.resetAvailableFunds();
						break;
					}
				}
			} else if (choice.equals(MAIN_MENU_OPTION_Exit)){
				break;
			}
		}
	}

	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}
}
