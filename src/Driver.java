//https://github.com/eluzbet
//main

import java.util.List;

public class Driver {
    public static void main(String[] args) {
        String baseUrl = "https://www.microcenter.com/search/search_results.aspx?N=4294966998&prt=clearance&page=";
        int storeId = 185;

        productScraper scraper = new productScraper(baseUrl, storeId);
        productSorting sort = new productSorting();

        List<Product> products = scraper.scrapePages();
        sort.mergeSort(products);

        System.out.println("Products found:" + products.size());

        for (Product product : products) {
            System.out.println(product);
        }





    }
}
