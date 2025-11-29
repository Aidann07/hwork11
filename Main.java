import java.util.*;
abstract class User {
    protected int id;
    protected String name;
    protected String email;
    protected String address;
    protected String phone;
    protected String role;

    public User(int id, String name, String email, String address, String phone, String role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.role = role;
    }

    public void register() { System.out.println(name + " тіркелді."); }
    public void login() { System.out.println(name + " жүйеге кірді."); }
    public void updateInfo() { System.out.println(name + " ақпараттарын жаңартты."); }
}
class Client extends User {
    private int loyaltyPoints = 0;
    List<Order> orders = new ArrayList<>();

    public Client(int id, String name, String email, String address, String phone) {
        super(id, name, email, address, phone, "CLIENT");
    }

    public void addPoints(int points) {
        loyaltyPoints += points;
        System.out.println(name + " +" + points + " бонус алды.");
    }

    public int getPoints() { return loyaltyPoints; }
}
class Admin extends User {
    public Admin(int id, String name, String email) {
        super(id, name, email, "", "", "ADMIN");
    }

    public void logAction(String action) {
        System.out.println("LOG (admin): " + action);
    }
}
class Category {
    int id;
    String name;

    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
class Product {
    int id;
    String name;
    String desc;
    double price;
    int stock;
    Category category;

    public Product(int id, String name, String desc, double price, int stock, Category category) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.price = price;
        this.stock = stock;
        this.category = category;
    }

    public void create() { System.out.println(name + " тауар жасалды."); }
    public void update() { System.out.println(name + " тауар жаңартылды."); }
    public void delete() { System.out.println(name + " тауар өшірілді."); }
}
class PromoCode {
    String code;
    double discountPercent;

    public PromoCode(String code, double discountPercent) {
        this.code = code;
        this.discountPercent = discountPercent;
    }

    public double apply(double price) {
        return price * (1 - discountPercent / 100);
    }
}
class Payment {
    int id;
    String type; // карта, кошелек
    double amount;
    String status;
    Date date;

    public Payment(int id, String type, double amount) {
        this.id = id;
        this.type = type;
        this.amount = amount;
        this.status = "Қабылданбаған";
        this.date = new Date();
    }

    public void process() {
        status = "Өтті";
        System.out.println("Төлем өтті: " + amount);
    }

    public void refund() {
        status = "Қайтарылды";
        System.out.println("Төлем қайтарылды.");
    }
}
class Courier {
    int id;
    String name;

    public Courier(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

class Delivery {
    int id;
    String address;
    String status;
    Courier courier;

    public Delivery(int id, String address, Courier courier) {
        this.id = id;
        this.address = address;
        this.courier = courier;
        this.status = "Дайындалып жатыр";
    }

    public void send() { status = "Жеткізілуде"; }
    public void track() { System.out.println("Жеткізу статусы: " + status); }
    public void complete() { status = "Жеткізілді"; }
}

class Order {
    int id;
    Date created;
    String status;
    Client client;
    List<Product> products = new ArrayList<>();
    double totalSum;
    Delivery delivery;

    public Order(int id, Client client) {
        this.id = id;
        this.client = client;
        this.created = new Date();
        this.status = "Оформлен";
    }

    public void addProduct(Product p) {
        products.add(p);
        totalSum += p.price;
    }

    public void applyPromo(PromoCode promo) {
        totalSum = promo.apply(totalSum);
    }

    public void pay() {
        status = "Оплачено";
        client.addPoints((int) totalSum / 10);
    }

    public void cancel() { status = "Отменен"; }
}

class ProductFactory {
    public static Product createSimpleProduct(int id, String name, double price) {
        return new Product(id, name, "Сипаттама жоқ", price, 10, new Category(1, "Бөлім жоқ"));
    }
}
public class Main {
    public static void main(String[] args) {
        Client c1 = new Client(1, "Али", "ali@gmail.com", "Алматы", "+7700...");
        c1.register();

        Category cat = new Category(10, "Электроника");

        Product p1 = new Product(101, "Телефон", "Заманауи смартфон", 120000, 5, cat);

        Order order = new Order(5001, c1);
        order.addProduct(p1);

        PromoCode promo = new PromoCode("SALE20", 20);
        order.applyPromo(promo);

        Payment pay = new Payment(9001, "Карта", order.totalSum);
        pay.process();

        Courier courier = new Courier(1, "Ерасыл");
        Delivery delivery = new Delivery(3001, "Алматы, Абылай хан 3", courier);
        delivery.send();
        delivery.track();

        delivery.complete();
        order.pay();

        System.out.println("Тапсырыс жалпы сомасы: " + order.totalSum + " тг");
    }
}
