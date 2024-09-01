package cat.uab.tq.online_warehouse.clients;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ClientTest {

    @Test
    public void testGetName() {
        Client client = new Client("John Doe", "12345", "123 Main St", "john@example.com", "123-456-7890");
        Assertions.assertEquals("John Doe", client.getName());
    }

    @Test
    public void testGetUid() {
        Client client = new Client("John Doe", "12345", "123 Main St", "john@example.com", "123-456-7890");
        Assertions.assertEquals("12345", client.getUid());
    }

    @Test
    public void testGetAddress() {
        Client client = new Client("John Doe", "12345", "123 Main St", "john@example.com", "123-456-7890");
        Assertions.assertEquals("123 Main St", client.getAddress());
    }

    @Test
    public void testGetEmail() {
        Client client = new Client("John Doe", "12345", "123 Main St", "john@example.com", "123-456-7890");
        Assertions.assertEquals("john@example.com", client.getEmail());
    }

    @Test
    public void testGetPhone() {
        Client client = new Client("John Doe", "12345", "123 Main St", "john@example.com", "123-456-7890");
        Assertions.assertEquals("123-456-7890", client.getPhone());
    }

    @Test
    public void testGetDiscount() {
        Client client = new Client("John Doe", "12345", "123 Main St", "john@example.com", "123-456-7890");
        Assertions.assertEquals(0.05f, client.getDiscount());
    }
}