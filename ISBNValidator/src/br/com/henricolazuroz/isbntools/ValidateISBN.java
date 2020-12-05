package br.com.henricolazuroz.isbntools;

public class ValidateISBN {

    public static final int LONG_ISBN = 13;
    public static final int SHORT_ISBN = 10;
    public static final int LONG_ISBN_MULTIPLIER = 10;
    public static final int SHORT_ISBN_MULTIPLIER = 11;

    public boolean checkISBN(String isbn) {
        if (isbn.length() == LONG_ISBN)
            return isAValidLongISBN(isbn);
        else if (isbn.length() == SHORT_ISBN)
            return isAValidShortISBN(isbn);

        throw new NumberFormatException("ISBN numbers must be " + SHORT_ISBN +  " or " + LONG_ISBN+ " digits long!");
    }

    private boolean isAValidShortISBN(String isbn) {
        if (isbn.length() != SHORT_ISBN)
            throw new NumberFormatException("ISBN numbers must be 10 digits long!");

        int total = 0;
        for (int i = 0; i < SHORT_ISBN; i++) {
            if (!Character.isDigit(isbn.charAt(i))) {
                if (i == 9 && isbn.charAt(i) == 'X')
                    total += 10;
                else
                    throw new NumberFormatException("ISBN numbers can only contains numeric digits!");
            } else {
                total += Character.getNumericValue(isbn.charAt(i)) * (SHORT_ISBN - i);
            }
        }

        return total % SHORT_ISBN_MULTIPLIER == 0;
    }

    private boolean isAValidLongISBN(String isbn) {
        int total = 0;

        for (int i = 0; i < LONG_ISBN; i++) {
            if (i % 2 == 0)
                total += Character.getNumericValue(isbn.charAt(i));
            else
                total += Character.getNumericValue(isbn.charAt(i)) * 3;
        }

        return total % LONG_ISBN_MULTIPLIER == 0;
    }
}