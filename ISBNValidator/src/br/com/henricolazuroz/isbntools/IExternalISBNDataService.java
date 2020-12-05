package br.com.henricolazuroz.isbntools;

public interface IExternalISBNDataService {
    public Book lookup(String isbn);
}
