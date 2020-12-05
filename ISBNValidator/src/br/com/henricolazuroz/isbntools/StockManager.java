package br.com.henricolazuroz.isbntools;

public class StockManager {

    private IExternalISBNDataService service;

    public String getLocatorCode(String isbn) {
        Book book = service.lookup(isbn);
        StringBuilder locatorCode = new StringBuilder();
        locatorCode.append(isbn.substring(isbn.length() - 4));
        locatorCode.append(book.getAuthor().substring(0, 1));
        locatorCode.append(book.getTitle().split(" ").length);
        return locatorCode.toString();
    }

    public void setService(IExternalISBNDataService service) {
        this.service = service;
    }
}
