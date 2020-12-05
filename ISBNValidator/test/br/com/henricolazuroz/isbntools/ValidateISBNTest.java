package br.com.henricolazuroz.isbntools;

import org.junit.Test;

import java.text.NumberFormat;

import static org.junit.Assert.*;

public class ValidateISBNTest {

    @Test
    public void checkAValid10DigitISBN() {
        ValidateISBN validator = new ValidateISBN();
        boolean result = validator.checkISBN("0140449116");
        assertTrue("first result", result);
        result = validator.checkISBN("0140177396");
        assertTrue("second result", result);
    }

    @Test
    public void checkAValid13DigitISBN() {
        ValidateISBN validator = new ValidateISBN();
        boolean result = validator.checkISBN("9788560280940");
        assertTrue("first result", result);
        result = validator.checkISBN("9788576573135");
        assertTrue("second result", result);
    }

    @Test
    public void checkAnInvalid13DigitISBN() {
        ValidateISBN validator = new ValidateISBN();
        boolean result = validator.checkISBN("9788560280941");
        assertFalse(result);
    }

    @Test
    public void TenDigitISBNNumbersEndingInAnXAreValid() {
        ValidateISBN validator = new ValidateISBN();
        boolean result = validator.checkISBN("012000030X");
        assertTrue(result);
    }

    @Test
    public void checkAnInvalid10DigitISBN() {
        ValidateISBN validator = new ValidateISBN();
        boolean result = validator.checkISBN("0140449117");
        assertFalse(result);
    }

    @Test(expected = NumberFormatException.class)
    public void nineDigitISBNsAreNotAllowed() {
        ValidateISBN validator = new ValidateISBN();
        validator.checkISBN("123456789");

        /*
         JUnit 5 ->
         assertThrows(NumberFormatException.class, () -> {validator.checkISBN("123456789")})
         */
    }

    @Test(expected = NumberFormatException.class)
    public void nonNumericISBNsAreNotAllowed() {
        ValidateISBN validator = new ValidateISBN();
        validator.checkISBN("helloworld");
    }
}
