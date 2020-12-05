package br.com.henricolazuroz.isbntools;

public class StockManager {

    private IExternalISBNDataService webService;
    private IExternalISBNDataService databaseService;

    public StockManager() {
        //
    }

    public StockManager(IExternalISBNDataService webService, IExternalISBNDataService databaseService) {
        this.webService = webService;
        this.databaseService = databaseService;
    }

    public String getLocatorCode(String isbn) {
        Book book = databaseService.lookup(isbn);

        if (book == null)
            book = webService.lookup(isbn);

        StringBuilder locatorCode = new StringBuilder();

        locatorCode.append(isbn.substring(isbn.length() - 4));
        locatorCode.append(book.getAuthor().substring(0, 1));
        locatorCode.append(book.getTitle().split(" ").length);

        return locatorCode.toString();
    }

    public void setWebService(IExternalISBNDataService webService) {
        this.webService = webService;
    }

    public void setDatabaseService(IExternalISBNDataService databaseService) {
        this.databaseService = databaseService;
    }
}
