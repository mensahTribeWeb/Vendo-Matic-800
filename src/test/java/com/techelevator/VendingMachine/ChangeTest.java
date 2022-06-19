package com.techelevator.VendingMachine;
import static org.junit.Assert.*;
import com.techelevator.VendingMachine.Change;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;



public class ChangeTest {
    Change test;
    BigDecimal diff = new BigDecimal("0.10");
    @Before
    public void setUp() throws Exception {
        test = new Change();

    }

    @Test
    public void testCalculateChange() {
        assertEquals("Your total change is: $0.10: Quarters:0, Dimes:1,  Nickes:0", test.calculateChange(diff));

    }

}