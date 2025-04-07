//https://github.com/eluzbet




import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Main {
    public static void main(String[] args) {
        String url = "https://www.microcenter.com/search/search_results.aspx?storeid=185&Ntk=all&prt=clearance&sortby=match&N=4294966998"; // Replace with actual store URL

        try {
            // Fetch the webpage
            Document doc = Jsoup.connect(url).get();

            // Select product elements (Modify the selectors based on the website structure)
            Elements products = doc.select("#productGrid .product_wrapper");


            for (Element product : products) {
                String name = product.select(".product_wrapper .h2 a").text();

                String priceText = product.select(".product_wrapper .price_wrapper .price > span[itemprop=price]").text();
               // priceText = priceText.replaceAll("[^0-9.]", ""); used below
                double price = Double.parseDouble(priceText.replaceAll("[^0-9.]", ""));

                String clearanceText = product.select(".price-label.compareTo").text();
                double clearancePrice = Double.parseDouble(clearanceText.replaceAll("[^0-9.]", ""));

                String sku = product.select(".sku").text();

                System.out.println("Product Name: " + name);
                System.out.println(sku);
                System.out.println("Price: $" + price);
                System.out.println("Clearance Price from: $" + clearancePrice);
                System.out.println("---------------------------");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error");
        }
    }
}
