package com.techelevator.VendingMachine;

import com.techelevator.VendingMachine.VendingFunctions;
import com.techelevator.VendingMachine.Inventory;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

public class vendingMachineFunctionsTest {
    VendingFunctions vendingFunctionsTest = new VendingFunctions();

    @Test
    public void feedingOneDollar() {
       // Assert.assertEquals(new BigDecimal("1"), vendingFunctionsTest.feedMoney("FEED $1.00 ", BigDecimal.ZERO));
    }

}
