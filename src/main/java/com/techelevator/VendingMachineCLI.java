package com.techelevator;

import com.techelevator.view.Menu;
import com.techelevator.VendingMachine.VendingFunctions;
import com.techelevator.VendingMachine.Inventory;

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

	private VendingFunctions vendingMachine = new VendingFunctions();

	private Menu menu;
	BigDecimal accumulatedBalance =  new BigDecimal("0.00");

	public VendingMachineCLI(Menu menu) {

		this.menu = menu;
	}

	public void run() {
		vendingMachine.getMyInventory().loadInventory();
		while (true) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				// display vending machine items
				vendingMachine.getMyInventory().displayInventory();
			}
			else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				// do purchase
				System.out.println("purchasing section");
				String purchaseSelection = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);

				if (purchaseSelection.equals(PURCHASE_MENU_OPTION_FEED_MONEY)){
					System.out.println("Feed Cash");
					final String FEED_MONEY_1 = "Feed 1 dollars";
					final String FEED_MONEY_2 = "Feed 2 dollars";
					final String FEED_MONEY_5 = "Feed 5 dollars";
					final String FEED_MONEY_10 = "Feed 10 dollars";
					final String FEED_MONEY_DONE = "Done";
					final String[] FEED_MONEY_MENU_OPTIONS = {FEED_MONEY_1, FEED_MONEY_2, FEED_MONEY_5, FEED_MONEY_10, FEED_MONEY_DONE};


					boolean feedMoneyLoop = true;

					while (feedMoneyLoop) {
						String feedSelection = (String) menu.getChoiceFromOptions(FEED_MONEY_MENU_OPTIONS);

						if (purchaseSelection.equals("Done")){
							feedMoneyLoop = false;
						}
						else {
							BigDecimal amountAdd = vendingMachine.feedMoney(feedSelection, accumulatedBalance);
							accumulatedBalance = accumulatedBalance.add(amountAdd);
						}
					}
					System.out.println("The accumulated balance: " + accumulatedBalance);
					}
				else if (purchaseSelection.equals(PURCHASE_MENU_OPTION_SELECT_PRODUCT)){
					vendingMachine.getMyInventory().displayInventory();
					System.out.println("Enter Product: ");
					String userInput = input.nextLine();
					accumulatedBalance = vendingMachine.getMyInventory(userInput, accumulatedBalance);

				}
				else if (purchaseSelection.equals(PURCHASE_MENU_OPTION_FINISH_TRANSACTION)){
					vendingMachine.finishTransaction(accumulatedBalance);
					accumulatedBalance = BigDecimal.ZERO;
				}
			}
			else if (choice.equals(MAIN_MENU_OPTION_Exit)){
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
