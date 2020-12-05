package br.com.henricolazuroz.isbntools;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class StockManagementTest {

    IExternalISBNDataService testWebService;
    IExternalISBNDataService testDatabaseService;
    StockManager stockManager;

    final String ISBN = "0140449116";

    @Before
    public void setup() {
        testWebService = mock(IExternalISBNDataService.class);
        testDatabaseService = mock(IExternalISBNDataService.class);
        stockManager = new StockManager(testWebService, testDatabaseService);
    }

    @Test
    public void testCanGetACorrectLocatorCode() {
        when(testDatabaseService.lookup(ISBN)).thenReturn(null);
        when(testWebService.lookup(ISBN)).thenReturn(new Book(ISBN, "Odyssey", "Homero"));

        String locatorCode = stockManager.getLocatorCode(ISBN);

        assertEquals("9116H1", locatorCode);
    }

    @Test
    public void databaseIsUsedIfDataIsPresent() {
        // Stub
        when(testDatabaseService.lookup(ISBN)).thenReturn(new Book(ISBN, "abc", "abc"));

        final String locatorCode = stockManager.getLocatorCode(ISBN);

        // Mock
        verify(testDatabaseService, times(1)).lookup(ISBN);
        verify(testWebService, never()).lookup(ISBN);
    }

    @Test
    public void webserviceIsUsedIfDataIsNotPresentInDatabase() {
        when(testDatabaseService.lookup(ISBN)).thenReturn(null);
        when(testWebService.lookup(ISBN)).thenReturn(new Book(ISBN, "abc", "abc"));

        final String locatorCode = stockManager.getLocatorCode(ISBN);

        // time(1) is default
        verify(testDatabaseService).lookup(ISBN);
        // could change isbn to anyString() or any(Object.class)
        verify(testWebService).lookup(ISBN);
    }

    /*
     * Stubs -> used to test DATA
     * Mocks -> used to test BEAHVIOUR
     * Fakes or Dummies -> override without returning data/receiving implementation (No tests)
     * Tautology -> when the test code is equal than what you're testing
     */
}
