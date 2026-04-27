// ITERATOR PATTERN TEMPLATE
// Use when: You want to traverse elements of a collection without exposing its underlying structure.

import java.util.ArrayList;
import java.util.List;

// 1. Iterator Interface
interface Iterator {
    boolean hasNext();

    Object next();
}

// 2. Collection Interface
interface Container {
    Iterator getIterator();
}

// 3. Concrete Collection
class ConcreteContainer implements Container {
    public String[] names = { "Robert", "John", "Julie" };

    public Iterator getIterator() {
        return new ConcreteIterator();
    }

    // 4. Concrete Iterator (Usually an inner class so it can access collection
    // data)
    private class ConcreteIterator implements Iterator {
        int index = 0;

        public boolean hasNext() {
            return index < names.length;
        }

        public Object next() {
            if (this.hasNext()) {
                return names[index++];
            }
            return null;
        }
    }
}

// 5. Client
class IteratorTemplateDemo {
    public static void main(String[] args) {
        Container repository = new ConcreteContainer();

        Iterator iter = repository.getIterator();
        while (iter.hasNext()) {
            System.out.println("Name : " + iter.next());
        }
    }
}
