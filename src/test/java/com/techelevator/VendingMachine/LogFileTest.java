package com.techelevator.VendingMachine;


import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LogFileTest {

        LogFile sut;
        File logFile;
        BigDecimal bd1;
        BigDecimal bd2;
        BigDecimal bd3;
        BigDecimal bd4;
        BigDecimal bdZero;
        Scanner scanner;

        @Before
        public void setUp() throws Exception {
            logFile = new File("Log.txt");
            bd1 = new BigDecimal("5.00");
            bd2 = new BigDecimal("10.00");
            bd3 = new BigDecimal("7.21");
            bd4 = new BigDecimal("12.14");
            bdZero = new BigDecimal("0.00");
            sut = new LogFile();
            if (logFile.exists()) {
                logFile.delete();
            }

        }

        @Test
        public void testLogCreateAppendFeedPurchaseInvalidChange() throws FileNotFoundException {
            Items item = new Gum("Triplemint", new BigDecimal(".75"));
            sut.logFeed(bd1, bdZero);
            sut.logFeed(bd1, bd1);
            sut.logPurchase("D4", item, bd2);
            sut.logOutOfStock(new BigDecimal("9.25"));
            sut.logChange(new BigDecimal("9.25"));
            assertTrue(logFile.exists());

            scanner = new Scanner(logFile);

            String line = scanner.nextLine();
            String expectedString = (new SimpleDateFormat("MM/dd/YYYY hh:mm:ss a").format(new java.util.Date()))
                    + "  FEED $5.00                       $0.00   $5.00";
            assertEquals(expectedString, line);

            String line2 = scanner.nextLine();
            String expectedString2 = (new SimpleDateFormat("MM/dd/YYYY hh:mm:ss a").format(new java.util.Date()))
                    + "  FEED $5.00                       $5.00  $10.00";
            assertEquals(expectedString2, line2);

            String line3 = scanner.nextLine();
            String expectedString3 = (new SimpleDateFormat("MM/dd/YYYY hh:mm:ss a").format(new java.util.Date()))
                    + "  Triplemint  D4                  $10.00   $9.25";
            assertEquals(expectedString3, line3);

            String line4 = scanner.nextLine();
            String expectedString4 = (new SimpleDateFormat("MM/dd/YYYY hh:mm:ss a").format(new java.util.Date()))
                    + "  Item unavailable.                $9.25   $9.25";
            assertEquals(expectedString4, line4);

            String line5 = scanner.nextLine();
            String expectedString5 = (new SimpleDateFormat("MM/dd/YYYY hh:mm:ss a").format(new java.util.Date()))
                    + "  DISPENSED CHANGE $9.25           $9.25   $0.00";
            assertEquals(expectedString5, line5);

            scanner.close();
        }

        @After
        public void tearDown() throws Exception {
            logFile.delete();
            scanner.close();

        }

    }