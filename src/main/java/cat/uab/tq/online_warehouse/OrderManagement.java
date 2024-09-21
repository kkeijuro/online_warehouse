package cat.uab.tq.online_warehouse;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class OrderManagement {
    
        Map<String, Order> _orders = new HashMap<String, Order>();

        public String save(Order order) {
            String uuid = UUID.randomUUID().toString();
            _orders.put(uuid, order);
            return uuid;
        }

        public Order get(String orderId) {
            return _orders.get(orderId);
        }

        public void delete(String orderId) {
            _orders.remove(orderId);
        }
}
