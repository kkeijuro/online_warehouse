package cat.uab.tq.online_warehouse.clients;

public class Client {
    final private String _uid;
    final private String _name;
    final private String _address;
    final private String _email;
    final private String _phone;
    final private float _discount = 0.05f;

    public Client(String name,
                  String uid,
                  String address,
                  String email,
                  String phone) {
        _name = name;
        _uid = uid;
        _address = address;
        _email = email;
        _phone = phone;
    }

    public String getName() {
        return _name;
    }

    public String getUid() {
        return _uid;
    }

    public String getAddress() {
        return _address;
    }

    public String getEmail() {
        return _email;
    }

    public String getPhone() {
        return _phone;
    }

    public float getDiscount() {
        return _discount;
    }
}
