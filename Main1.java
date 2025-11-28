import java.util.*;

class Orders {
    private UUID id;
    private String customer;
    private String status;

    public Orders(String customer) {
        this.id = UUID.randomUUID();
        this.customer = customer;
        this.status = "CREATED";
    }

    public UUID getId() { return id; }
    public String getCustomer() { return customer; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "Orders{id=" + id + ", customer=" + customer + ", status=" + status + "}";
    }
}

class LogisticsBackend {
    private Map<UUID, Orders> orders = new HashMap<>();

    // Тапсырыс құру
    public Orders createOrder(String customer) {
        Orders o = new Orders(customer);
        orders.put(o.getId(), o);
        System.out.println("✔ Orders Created: " + o);
        return o;
    }

    // Статусты өзгерту
    public void updateOrderStatus(UUID id, String status) {
        Orders o = orders.get(id);
        if (o != null) {
            o.setStatus(status);
            System.out.println("✳ Status Updated: " + o);
        }
    }

    // Orders ақпаратын алу
    public Orders getOrder(UUID id) {
        return orders.get(id);
    }
}

public class Main1 {
    public static void main(String[] args) {
        LogisticsBackend backend = new LogisticsBackend();

        Orders orders = backend.createOrder("Aidar");

        backend.updateOrderStatus(orders.getId(), "IN DELIVERY");
        backend.updateOrderStatus(orders.getId(), "DELIVERED");

        System.out.println("Final Orders Info: " + backend.getOrder(orders.getId()));
    }
}
