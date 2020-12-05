package br.com.henricolazuroz.isbntools;

public class ValidateISBN {

    public boolean checkISBN(String isbn) {
        int total = 0;
        for (int i = 0; i < 10; i++) {
            total += (int) isbn.charAt(i) * (10 - i);
        }

        if (total % 11 == 0)
            return true;

        return false;
    }
}