package com.techelevator.VendingMachine;

import java.math.BigDecimal;

public class Change {

    BigDecimal totalChange;


    public String calculateChange(BigDecimal difference) {

        BigDecimal quarter = new BigDecimal("0.25");
        BigDecimal dime = new BigDecimal("0.10");
        BigDecimal nickle = new BigDecimal("0.05");
        BigDecimal numQuarters = BigDecimal.ZERO;
        BigDecimal numDimes = BigDecimal.ZERO;
        BigDecimal numNickles = BigDecimal.ZERO;


        while (difference.compareTo(BigDecimal.ZERO) > 0){
            if (difference.doubleValue() >= quarter.doubleValue()){
                difference = difference.subtract(quarter);
                numQuarters = numQuarters.add(new BigDecimal(1));


            } else if (difference.doubleValue() >= dime.doubleValue()){
                difference = difference.subtract(dime);
                numDimes = numDimes.add(new BigDecimal(1));

            } else {
                difference = difference.subtract(nickle);
                numNickles = numNickles.add(new BigDecimal(1));

            }
        }


        totalChange = BigDecimal.ZERO;
        totalChange = totalChange.add(nickle.multiply(numNickles));
        totalChange = totalChange.add(quarter.multiply(numQuarters));
        totalChange = totalChange.add(dime.multiply(numDimes));


        return "Your total change is: $" + totalChange.toString() + ": Quarters:" + numQuarters + ", Dimes:" + numDimes + ",  Nickes:" + numNickles;
    }



}