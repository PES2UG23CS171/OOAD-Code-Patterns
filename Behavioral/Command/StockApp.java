// Command Pattern - Stock Trading App (Buy / Sell via Broker)
import java.util.ArrayList;
import java.util.List;

// ── Command Interface ──────────────────────────────────────────────────────
interface Order {
    void execute();
}

// ── Receiver ──────────────────────────────────────────────────────────────
class Stock {
    private String name;
    private int quantity;
    public Stock(String name, int quantity) { this.name = name; this.quantity = quantity; }
    public void buy()  { System.out.println("Stock [ Name: " + name + ", Qty: " + quantity + " ] bought"); }
    public void sell() { System.out.println("Stock [ Name: " + name + ", Qty: " + quantity + " ] sold"); }
}

// ── Concrete Commands ──────────────────────────────────────────────────────
class BuyStock implements Order {
    private Stock stock;
    public BuyStock(Stock stock) { this.stock = stock; }
    public void execute() { stock.buy(); }
}

class SellStock implements Order {
    private Stock stock;
    public SellStock(Stock stock) { this.stock = stock; }
    public void execute() { stock.sell(); }
}

// ── Invoker ────────────────────────────────────────────────────────────────
class Broker {
    private List<Order> orderList = new ArrayList<>();
    public void takeOrder(Order order) { orderList.add(order); }
    public void placeOrders() {
        for (Order order : orderList) order.execute();
        orderList.clear();
    }
}

// ── Client ─────────────────────────────────────────────────────────────────
class StockApp {
    public static void main(String[] args) {
        Stock google = new Stock("Google", 24);
        Stock apple  = new Stock("Apple", 10);

        Broker broker = new Broker();
        broker.takeOrder(new BuyStock(apple));
        broker.takeOrder(new SellStock(google));
        broker.placeOrders();
    }
}
