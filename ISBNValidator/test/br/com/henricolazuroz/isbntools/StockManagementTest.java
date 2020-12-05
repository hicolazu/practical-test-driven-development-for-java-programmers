package br.com.henricolazuroz.isbntools;

import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class StockManagementTest {

    @Test
    public void testCanGetACorrectLocatorCode() {

        IExternalISBNDataService testWebService = new IExternalISBNDataService() {

            @Override
            public Book lookup(String isbn) {
                return new Book(isbn, "Odyssey", "Homero");
            }
        };

        IExternalISBNDataService testDatabaseService = new IExternalISBNDataService() {

            @Override
            public Book lookup(String isbn) {
                return null;
            }
        };

        String isbn = "0140449116";

        StockManager stockManager = new StockManager(testWebService, testDatabaseService);
        // Stub -> substitute some dependency of our business logic
        // stockManager.setWebService(testService);

        String locatorCode = stockManager.getLocatorCode(isbn);

        assertEquals("9116H1", locatorCode);
    }

    @Test
    public void databaseIsUsedIfDataIsPresent() {
        final String isbn = "0140449116";

        final IExternalISBNDataService webService = mock(IExternalISBNDataService.class);
        final IExternalISBNDataService databaseService = mock(IExternalISBNDataService.class);

        when(databaseService.lookup(isbn)).thenReturn(new Book(isbn, "abc", "abc"));

        final StockManager stockManager = new StockManager(webService, databaseService);

        final String locatorCode = stockManager.getLocatorCode(isbn);

        verify(databaseService, times(1)).lookup(isbn);
        verify(webService, never()).lookup(isbn);
    }

    @Test
    public void webserviceIsUsedIfDataIsNotPresentInDatabase() {
        final String isbn = "0140449116";

        final IExternalISBNDataService webService = mock(IExternalISBNDataService.class);
        final IExternalISBNDataService databaseService = mock(IExternalISBNDataService.class);

        when(databaseService.lookup(isbn)).thenReturn(null);
        when(webService.lookup(isbn)).thenReturn(new Book(isbn, "abc", "abc"));

        final StockManager stockManager = new StockManager(webService, databaseService);

        final String locatorCode = stockManager.getLocatorCode(isbn);

        // time(1) is default
        verify(databaseService).lookup(isbn);
        // could change isbn to anyString() or any(Object.class)
        verify(webService).lookup(isbn);
    }
}
