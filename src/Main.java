//https://github.com/eluzbet
//test class



import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Main {
    public static void main(String[] args) {
        String baseUrl = "https://www.microcenter.com/search/search_results.aspx?N=4294966998&prt=clearance&page=";
        int storeId = 185; // store location
        int page = 1; // page number
        //default items per page = 24

        try {
            while (true) {
                String url = baseUrl + page + "&storeid=" + storeId; //edits url based on page and store location
                System.out.println("Fetching page: " + page);
                Document doc = Jsoup.connect(url).get(); // fetches url's html content

                Elements products = doc.select("#productGrid .product_wrapper"); //grabs products

                if (products.isEmpty()) {
                    System.out.println("No more products found. Exiting at page " + page);
                    break;
                }

                for (Element product : products) {
                    String name = product.select(".product_wrapper .h2 a").text(); //grab product name

                    String sku = product.select(".sku").text(); //grabs product sku #

                    String priceText = product.select(".product_wrapper .price_wrapper .price > span[itemprop=price]").text(); //grabs product  retail price as String
                    double price = priceText.isEmpty() ? 0.0 : Double.parseDouble(priceText.replaceAll("[^0-9.]", "")); // parses to double

                    String clearanceText = product.select(".price-label.compareTo").text(); //grabs product clearance price as String
                    double clearancePrice = clearanceText.isEmpty() ? 0.0 : Double.parseDouble(clearanceText.replaceAll("[^0-9.]", ""));  // parses to double

                    double discount = Math.round(((price - clearancePrice) / price) * 1000) / 10.0;




                    System.out.println("Product Name: " + name);
                    System.out.println("SKU: " + sku);
                    System.out.println("Price: $" + price);
                    System.out.println("Clearance Price from: $" + clearancePrice);
                    System.out.println("Discount: " + discount + "%");
                    System.out.println("---------------------------");
                }

                page++;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error.");
        }
    }
}
