// Iterator Pattern - Custom Collection with Step Iterator
import java.util.ArrayList;

// ── Iterator Interface ─────────────────────────────────────────────────────
interface AbstractIterator {
    Item next();
    boolean isDone();
    Item currentItem();
    void setStep(int step);
}

// ── Collection Interface ───────────────────────────────────────────────────
interface AbstractCollection {
    BookIterator createIterator();
}

// ── Item (element stored in collection) ────────────────────────────────────
class Item {
    private String name;
    public Item(String name) { this.name = name; }
    public String getName() { return name; }
}

// ── Concrete Iterator ──────────────────────────────────────────────────────
class BookIterator implements AbstractIterator {
    private BookCollection collection;
    private int current = 0;
    private int step = 1;

    public BookIterator(BookCollection collection) { this.collection = collection; }

    public Item next() {
        current += step;
        return isDone() ? null : collection.get(current);
    }
    public boolean isDone()      { return current >= collection.size(); }
    public Item currentItem()    { return collection.get(current); }
    public void setStep(int step){ this.step = step; }
}

// ── Concrete Collection ────────────────────────────────────────────────────
class BookCollection implements AbstractCollection {
    private ArrayList<Item> items = new ArrayList<>();

    public BookIterator createIterator() { return new BookIterator(this); }
    public void add(Item item)           { items.add(item); }
    public Item get(int index)           { return items.get(index); }
    public int size()                    { return items.size(); }
}

// ── Client ─────────────────────────────────────────────────────────────────
class IteratorDemo {
    public static void main(String[] args) {
        BookCollection collection = new BookCollection();
        for (int i = 0; i <= 8; i++) collection.add(new Item("Item " + i));

        BookIterator iterator = collection.createIterator();
        iterator.setStep(2); // skip every other item

        System.out.println("Iterating with step 2:");
        while (!iterator.isDone()) {
            Item item = iterator.next();
            if (item != null) System.out.println(item.getName());
        }
    }
}
