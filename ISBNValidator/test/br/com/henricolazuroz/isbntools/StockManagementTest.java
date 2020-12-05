package br.com.henricolazuroz.isbntools;

import org.junit.Test;

import static org.junit.Assert.*;

public class StockManagementTest {

    @Test
    public void testCanGetACorrectLocatorCode() {
        String isbn = "0140449116";

        StockManager stockManager = new StockManager();
        // Stub -> substitute some dependency of our business logic
        stockManager.setService(new FakeExternalISBNDataService());

        String locatorCode = stockManager.getLocatorCode(isbn);

        assertEquals("9116H1", locatorCode);
    }
}
