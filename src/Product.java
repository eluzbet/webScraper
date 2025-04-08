

public class Product  {
    private String name;
    private String sku;
    private double price;
    private double clearancePrice;
    private int page;



    public Product(){
        this("NO NAME", "NO SKU", 0.0,0.0, 0);


    }

    public Product(String name, String sku, double price, double clearancePrice, int page){
        this.name = name;
        this.sku = sku;
        this.price = price;
        this.clearancePrice = clearancePrice;
        this.page = page;


    }

    public double getClearancePrice() {
        return clearancePrice;
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public String getSku() {
        return sku;
    }

    public int getPage() {
        return page;
    }

    public double getDiscountPercentage(){
        return Math.round(((price - clearancePrice) / price) * 1000) / 10.0;
    }

@Override
    public String toString(){
        return String.format("Product Name: %s\nSKU: %s\nPrice: $%.2f\nClearance Price from: $%.2f\nDiscount: %.1f%%\nPage: %d\n---------------------------",
                name, sku, price, clearancePrice, getDiscountPercentage(), page);
    }






}
