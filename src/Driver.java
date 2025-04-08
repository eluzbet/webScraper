//https://github.com/eluzbet
//main

public class Driver {
    public static void main(String[] args) {
        String baseUrl = "https://www.microcenter.com/search/search_results.aspx?N=4294966998&prt=clearance&page=";
        int storeId = 185;

        productScraper scraper = new productScraper(baseUrl, storeId);
        for (Product product : scraper.scrapePages()) {
            System.out.println(product);
        }
    }
}
