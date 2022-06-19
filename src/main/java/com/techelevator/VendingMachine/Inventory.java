package com.techelevator.VendingMachine;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Inventory {

    Map<String, List<Items>> vendingMap;
    String[] documentArray;



    //create inventory map from a formatted txt file
    //load items
    public void loadInventory() {
        String document = "";
        String[] documentArray;
        File file = new File("vendingmachine.csv");
        //Reads the file located on the user's computer with the name "vendingmachine.csv".
        //Stores the entire document as a single string.
        try (Scanner input = new Scanner(file)) {
            while (input.hasNextLine()) {
                String line = input.nextLine();
                while (input.hasNextLine()) {
                    document += input.nextLine() + "\n";

                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Was unable to locate file with name \"vendingmachine.csv\".");
            e.printStackTrace();
            System.exit(1);}

            //Splits the entire document string into an array, separated by each new line.
            //Each new line in the csv file should represent invidividual items and their data.
            documentArray = document.split("\n");
            this.documentArray = documentArray;

        }
        public void createMap() {
            Map<String, List<Items>> vendingMap = new LinkedHashMap<>();

            for(String element : documentArray) {

                //Splits each line into an array of length 3 with the first element representing the button (i.e. A1), second element (name of the product), and third element (the price of the item).
                String[] elementArray = element.split("\\|");
                String tempKey = elementArray[0];
                List<Items> tempList = new ArrayList<>();
                BigDecimal bd = new BigDecimal(elementArray[2]);

                //If the item's button starts with A, create a chip object
                if(elementArray[0].startsWith("A")) {

                    Chips chip = new Chips(elementArray[1], bd);
                    for(int i = 0; i < 5; i++) {
                        tempList.add(chip);
                    }
                    vendingMap.put(tempKey, tempList);

                    //If the item's button starts with B, create a candy object
                } else if (elementArray[0].startsWith("B")) {

                    Candy candy = new Candy(elementArray[1], bd);
                    for(int i = 0; i < 5; i++) {
                        tempList.add(candy);
                    }
                    vendingMap.put(tempKey, tempList);

                    //If the item's button starts with C, create a drink object
                } else if (elementArray[0].startsWith("C")) {

                    Drinks drink = new Drinks(elementArray[1], bd);
                    for(int i = 0; i < 5; i++) {
                        tempList.add(drink);
                    }
                    vendingMap.put(tempKey, tempList);

                    //If the item's button starts with D, create a gum object
                } else if (elementArray[0].startsWith("D")) {

                    Gum gum = new Gum(elementArray[1], bd);
                    for(int i = 0; i < 5; i++) {
                        tempList.add(gum);
                    }
                    vendingMap.put(tempKey, tempList);
                }
            }
            this.vendingMap = vendingMap;
        }



        //Retrieves the map generated after reading the csv file and the actual creation of the map.
        public Map<String, List<Items>> stockMachine() {
            loadInventory();
            createMap();
            return vendingMap;
        }
    }

