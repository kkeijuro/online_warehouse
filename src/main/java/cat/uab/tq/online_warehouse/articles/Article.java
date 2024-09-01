package cat.uab.tq.online_warehouse.articles;
public class Article {
    final private String _name;
    final private String _description;
    final private double _price;
    final private String _serialNumber;

    public Article(String name, 
                   String description,
                   double price,
                   String serialNumber) {
        _name = name;
        _description = description;
        _price = 0;
        _serialNumber = serialNumber;
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
}
