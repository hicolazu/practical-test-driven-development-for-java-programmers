package br.com.henricolazuroz.isbntools;

public class FakeExternalISBNDataService implements IExternalISBNDataService {

    @Override
    public Book lookup(String isbn) {
        return new Book(isbn, "Odyssey", "Homero");
    }
}
