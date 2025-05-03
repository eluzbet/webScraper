import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.ArrayList;
import java.util.List;



public class productScraper {
    private final String baseUrl;
    private  int storeId;

    public productScraper(String baseUrl, int storeId){
        this.baseUrl = baseUrl;
        this.storeId = storeId;
    }

    public List<Product> scrapePages(){  //returns list in site order
        List<Product> productList = new ArrayList<>();
        int page = 1;

        try {
            while (true) {
                String url = baseUrl + page + "&storeid=" + storeId;
                Document doc = Jsoup.connect(url).get();
                Elements products = doc.select("#productGrid .product_wrapper");

                if (products.isEmpty()) break; // ends loop once a page with no products is detected.

                for (Element product : products) {
                    String name = product.select(".product_wrapper .h2 a").text();
                    String sku = product.select(".sku").text();

                    String priceText = product.select(".product_wrapper .price_wrapper .price > span[itemprop=price]").text();
                    double price = priceText.isEmpty() ? 0.0 : Double.parseDouble(priceText.replaceAll("[^0-9.]", ""));

                    String clearanceText = product.select(".price-label.compareTo").text();
                    double clearancePrice = clearanceText.isEmpty() ? 0.0 : Double.parseDouble(clearanceText.replaceAll("[^0-9.]", ""));

                    productList.add(new Product(name, sku, price, clearancePrice, page));
                }

                page++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return productList;
    }









}
