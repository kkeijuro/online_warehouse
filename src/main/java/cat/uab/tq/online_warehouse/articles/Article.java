package cat.uab.tq.online_warehouse.articles;
public class Article {
    final private String _name;
    final private String _description;
    final private double _price;
    final private String _serialNumber;
    final private int _quantity;

    public Article(String name, 
                   String description,
                   double price,
                   String serialNumber,
                   int quantity) {
        _name = name;
        _description = description;
        _price = price;
        _serialNumber = serialNumber;
        _quantity = quantity;
    }
    
    public String getName() {
        return _name;
    }

    public String getDescription() {
        return _description;
    }

    public double getPrice() {
        return _price;
    }

    public String getSerialNumber() {
        return _serialNumber;
    }
    
    public int getQuantity() {
        return _quantity;
    }
}
