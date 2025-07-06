# Abstract Data Type (ADT) & Bag - Comprehensive Study Notes

## Table of Contents
1. [Abstract Data Type (ADT) Concepts](#abstract-data-type-adt-concepts)
2. [Data Organization in Programming](#data-organization-in-programming)
3. [ADT vs Data Structure](#adt-vs-data-structure)
4. [The Bag ADT](#the-bag-adt)
5. [Bag Interface Design](#bag-interface-design)
6. [Implementation Examples](#implementation-examples)
7. [Practical Applications](#practical-applications)
8. [Java Collections Framework](#java-collections-framework)
9. [Design Decisions and Best Practices](#design-decisions-and-best-practices)
10. [Key Takeaways](#key-takeaways)

---

## Abstract Data Type (ADT) Concepts

### What is an ADT?
An **Abstract Data Type (ADT)** is a conceptual model that defines:
- The type of data it stores
- The operations that can be performed on the data
- **NOT** how the data is stored or operations are implemented

### Key Characteristics
- **Abstract**: Irrelevant implementation details are ignored
- **Interface-focused**: Defines what operations can be performed (public)
- **Implementation-independent**: How operations work is hidden (private)
- **Language-independent**: Focuses on concepts, not specific programming languages

### Real-World Analogy: Vending Machine
> **Vending Machine = ADT**
> - You know what it does (dispense items)
> - You know how to use it (insert money, select item)
> - You don't know how it works internally
> - You can use it regardless of internal mechanisms

---

## Data Organization in Programming

### Real-World Data Organization Examples
- 📚 **Stack of books**: Last book placed is first to be removed (LIFO)
- 🚶‍♂️ **Waiting in line**: First person in line is first to be served (FIFO)
- 📖 **Dictionary**: Key-value pairs for quick lookup
- ✅ **To-Do list**: Ordered collection of tasks
- 🗺️ **Road map**: Network of connected locations

### Collections and Containers

#### Collection
A **collection** is a general term for an ADT that stores a group of objects and defines operations like:
- Adding elements
- Removing elements
- Searching for elements

#### Container
A **container** is a class that implements a collection, providing the actual implementation in a specific programming language.

```java
// Collection (ADT concept)
interface Collection<T> {
    boolean add(T item);
    boolean remove(T item);
    int size();
}

// Container (actual implementation)
class ArrayList<T> implements Collection<T> {
    private T[] array;
    private int size;
    
    public boolean add(T item) {
        // Implementation details
    }
    
    public boolean remove(T item) {
        // Implementation details
    }
    
    public int size() {
        return size;
    }
}
```

---

## ADT vs Data Structure

### ADT (Abstract Data Type)
- Conceptual model defining what operations can be performed
- Interface specification without implementation details
- Language-independent design

### Data Structure
- Actual implementation of an ADT in a programming language
- Defines how data is stored and how operations are performed
- Language-specific implementation

### Examples

| ADT | Possible Data Structure Implementation |
|-----|---------------------------------------|
| List ADT | Array, LinkedList, Vector |
| Stack ADT | Array-based Stack, LinkedList-based Stack |
| Queue ADT | Array-based Queue, LinkedList-based Queue |
| Map ADT | Hash Table, Binary Search Tree |

---

## The Bag ADT

### Definition
A **Bag** is a finite collection of objects with these characteristics:
- No particular order (unordered collection)
- Allows duplicate items
- No standard size limit
- Focus on membership, not position

### Real-World Examples
- 🛍️ Shopping bag with various items
- 🔴 Bag of marbles (can have duplicates)
- 🪙 Collection of coins in a piggy bank

### Bag Behaviors
- ➕ Add objects to the bag
- ➖ Remove objects from the bag
- 🔢 Count items in the bag
- ❓ Check if empty
- 🔍 Search for specific items
- 📊 Get frequency of items

---

## Bag Interface Design

### UML Notation for Bag ADT
```
+---------------------------------------+
|               Bag<T>                  |
+---------------------------------------+
| +getCurrentSize(): int                |
| +isEmpty(): boolean                   |
| +add(newEntry: T): boolean            |
| +remove(): T                          |
| +remove(anEntry: T): boolean          |
| +clear(): void                        |
| +getFrequencyOf(anEntry: T): int      |
| +contains(anEntry: T): boolean        |
| +toArray(): T[]                       |
+---------------------------------------+
```

### Java Interface Implementation

```java
public interface BagInterface<T> {
    /**
     * Gets the current number of entries in this bag.
     * @return The integer number of entries currently in the bag.
     */
    public int getCurrentSize();
    
    /**
     * Checks whether this bag is empty.
     * @return True if the bag is empty, false otherwise.
     */
    public boolean isEmpty();
    
    /**
     * Adds a new entry to this bag.
     * @param newEntry The object to be added as a new entry.
     * @return True if the addition is successful, false otherwise.
     */
    public boolean add(T newEntry);
    
    /**
     * Removes one unspecified entry from this bag, if possible.
     * @return Either the removed entry, if the removal was successful, 
     *         or null if the bag is empty.
     */
    public T remove();
    
    /**
     * Removes one occurrence of a given entry from this bag, if possible.
     * @param anEntry The entry to be removed.
     * @return True if the removal was successful, false otherwise.
     */
    public boolean remove(T anEntry);
    
    /**
     * Removes all entries from this bag.
     */
    public void clear();
    
    /**
     * Counts the number of times a given entry appears in this bag.
     * @param anEntry The entry to be counted.
     * @return The number of times anEntry appears in the bag.
     */
    public int getFrequencyOf(T anEntry);
    
    /**
     * Tests whether this bag contains a given entry.
     * @param anEntry The entry to find.
     * @return True if the bag contains anEntry, false otherwise.
     */
    public boolean contains(T anEntry);
    
    /**
     * Retrieves all entries that are in this bag.
     * @return A newly allocated array of all the entries in the bag.
     */
    public T[] toArray();
}
```

---

## Implementation Examples

### ArrayBag Implementation (Fixed-Size Array)

```java
public class ArrayBag<T> implements BagInterface<T> {
    private final T[] bag;
    private int numberOfEntries;
    private static final int DEFAULT_CAPACITY = 25;
    
    @SuppressWarnings("unchecked")
    public ArrayBag() {
        this(DEFAULT_CAPACITY);
    }
    
    @SuppressWarnings("unchecked")
    public ArrayBag(int capacity) {
        bag = (T[]) new Object[capacity];
        numberOfEntries = 0;
    }
    
    @Override
    public int getCurrentSize() {
        return numberOfEntries;
    }
    
    @Override
    public boolean isEmpty() {
        return numberOfEntries == 0;
    }
    
    @Override
    public boolean add(T newEntry) {
        if (numberOfEntries < bag.length) {
            bag[numberOfEntries] = newEntry;
            numberOfEntries++;
            return true;
        }
        return false;
    }
    
    @Override
    public T remove() {
        if (isEmpty()) {
            return null;
        }
        
        T result = bag[numberOfEntries - 1];
        bag[numberOfEntries - 1] = null;
        numberOfEntries--;
        return result;
    }
    
    @Override
    public boolean remove(T anEntry) {
        int index = getIndexOf(anEntry);
        if (index >= 0) {
            bag[index] = bag[numberOfEntries - 1];
            bag[numberOfEntries - 1] = null;
            numberOfEntries--;
            return true;
        }
        return false;
    }
    
    @Override
    public void clear() {
        while (!isEmpty()) {
            remove();
        }
    }
    
    @Override
    public int getFrequencyOf(T anEntry) {
        int counter = 0;
        for (int index = 0; index < numberOfEntries; index++) {
            if (anEntry.equals(bag[index])) {
                counter++;
            }
        }
        return counter;
    }
    
    @Override
    public boolean contains(T anEntry) {
        return getIndexOf(anEntry) >= 0;
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public T[] toArray() {
        T[] result = (T[]) new Object[numberOfEntries];
        for (int index = 0; index < numberOfEntries; index++) {
            result[index] = bag[index];
        }
        return result;
    }
    
    private int getIndexOf(T anEntry) {
        for (int index = 0; index < numberOfEntries; index++) {
            if (anEntry.equals(bag[index])) {
                return index;
            }
        }
        return -1;
    }
}
```

### LinkedBag Implementation (Linked List)

```java
public class LinkedBag<T> implements BagInterface<T> {
    private Node firstNode;
    private int numberOfEntries;
    
    private class Node {
        private T data;
        private Node next;
        
        private Node(T dataPortion) {
            this(dataPortion, null);
        }
        
        private Node(T dataPortion, Node nextNode) {
            data = dataPortion;
            next = nextNode;
        }
    }
    
    public LinkedBag() {
        firstNode = null;
        numberOfEntries = 0;
    }
    
    @Override
    public int getCurrentSize() {
        return numberOfEntries;
    }
    
    @Override
    public boolean isEmpty() {
        return numberOfEntries == 0;
    }
    
    @Override
    public boolean add(T newEntry) {
        Node newNode = new Node(newEntry);
        newNode.next = firstNode;
        firstNode = newNode;
        numberOfEntries++;
        return true;
    }
    
    @Override
    public T remove() {
        if (isEmpty()) {
            return null;
        }
        
        T result = firstNode.data;
        firstNode = firstNode.next;
        numberOfEntries--;
        return result;
    }
    
    @Override
    public boolean remove(T anEntry) {
        Node nodeToRemove = getReferenceTo(anEntry);
        if (nodeToRemove != null) {
            nodeToRemove.data = firstNode.data;
            firstNode = firstNode.next;
            numberOfEntries--;
            return true;
        }
        return false;
    }
    
    @Override
    public void clear() {
        while (!isEmpty()) {
            remove();
        }
    }
    
    @Override
    public int getFrequencyOf(T anEntry) {
        int frequency = 0;
        Node currentNode = firstNode;
        while (currentNode != null) {
            if (anEntry.equals(currentNode.data)) {
                frequency++;
            }
            currentNode = currentNode.next;
        }
        return frequency;
    }
    
    @Override
    public boolean contains(T anEntry) {
        return getReferenceTo(anEntry) != null;
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public T[] toArray() {
        T[] result = (T[]) new Object[numberOfEntries];
        int index = 0;
        Node currentNode = firstNode;
        while (currentNode != null) {
            result[index] = currentNode.data;
            index++;
            currentNode = currentNode.next;
        }
        return result;
    }
    
    private Node getReferenceTo(T anEntry) {
        Node currentNode = firstNode;
        while (currentNode != null) {
            if (anEntry.equals(currentNode.data)) {
                return currentNode;
            }
            currentNode = currentNode.next;
        }
        return null;
    }
}
```

---

## Practical Applications

### Example 1: Online Shopping Cart

```java
public class OnlineShoppingCart {
    private BagInterface<String> cart;
    
    public OnlineShoppingCart() {
        cart = new ArrayBag<>();
    }
    
    public void addItem(String item) {
        if (cart.add(item)) {
            System.out.println("Added " + item + " to cart");
        } else {
            System.out.println("Could not add " + item + " to cart");
        }
    }
    
    public void removeItem(String item) {
        if (cart.remove(item)) {
            System.out.println("Removed " + item + " from cart");
        } else {
            System.out.println(item + " not found in cart");
        }
    }
    
    public void viewCart() {
        System.out.println("Shopping Cart Contents:");
        String[] items = cart.toArray();
        for (String item : items) {
            System.out.println("- " + item);
        }
        System.out.println("Total items: " + cart.getCurrentSize());
    }
    
    public void checkout() {
        System.out.println("Checking out with " + cart.getCurrentSize() + " items");
        cart.clear();
        System.out.println("Cart cleared after checkout");
    }
}

// Usage example
public class ShoppingDemo {
    public static void main(String[] args) {
        OnlineShoppingCart cart = new OnlineShoppingCart();
        
        cart.addItem("Laptop");
        cart.addItem("Mouse");
        cart.addItem("Keyboard");
        cart.addItem("Mouse"); // Duplicate allowed
        
        cart.viewCart();
        cart.removeItem("Mouse");
        cart.viewCart();
        cart.checkout();
    }
}
```

### Example 2: Piggy Bank

```java
public class PiggyBank {
    private BagInterface<String> coins;
    
    public PiggyBank() {
        coins = new LinkedBag<>();
    }
    
    public void addCoin(String coinType) {
        coins.add(coinType);
        System.out.println("Added " + coinType + " to piggy bank");
    }
    
    public String removeCoin() {
        String coin = coins.remove();
        if (coin != null) {
            System.out.println("Removed " + coin + " from piggy bank");
        } else {
            System.out.println("Piggy bank is empty");
        }
        return coin;
    }
    
    public void showContents() {
        System.out.println("Piggy Bank Contents:");
        String[] coinArray = coins.toArray();
        for (String coin : coinArray) {
            System.out.println("- " + coin);
        }
        System.out.println("Total coins: " + coins.getCurrentSize());
    }
    
    public int countCoinType(String coinType) {
        return coins.getFrequencyOf(coinType);
    }
    
    public double getTotalValue() {
        double total = 0.0;
        String[] coinArray = coins.toArray();
        for (String coin : coinArray) {
            switch (coin.toLowerCase()) {
                case "penny":
                    total += 0.01;
                    break;
                case "nickel":
                    total += 0.05;
                    break;
                case "dime":
                    total += 0.10;
                    break;
                case "quarter":
                    total += 0.25;
                    break;
                case "dollar":
                    total += 1.00;
                    break;
            }
        }
        return total;
    }
}

// Usage example
public class PiggyBankDemo {
    public static void main(String[] args) {
        PiggyBank bank = new PiggyBank();
        
        bank.addCoin("Quarter");
        bank.addCoin("Dime");
        bank.addCoin("Penny");
        bank.addCoin("Quarter");
        bank.addCoin("Dollar");
        
        bank.showContents();
        System.out.println("Number of quarters: " + bank.countCoinType("Quarter"));
        System.out.println("Total value: $" + String.format("%.2f", bank.getTotalValue()));
        
        bank.removeCoin();
        bank.showContents();
    }
}
```

---

## Java Collections Framework

### The Set Interface (Similar to Bag)

```java
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;

public class SetExample {
    public static void main(String[] args) {
        Set<String> set = new HashSet<>();
        
        // Add elements (duplicates not allowed in Set)
        set.add("Apple");
        set.add("Banana");
        set.add("Cherry");
        set.add("Apple"); // Duplicate - won't be added
        
        System.out.println("Set size: " + set.size()); // 3
        
        // Check if contains
        if (set.contains("Apple")) {
            System.out.println("Set contains Apple");
        }
        
        // Remove element
        set.remove("Banana");
        
        // Iterate through set
        System.out.println("Set contents:");
        for (String item : set) {
            System.out.println("- " + item);
        }
        
        // Convert to array
        String[] array = set.toArray(new String[0]);
        
        // Clear set
        set.clear();
        System.out.println("Set is empty: " + set.isEmpty());
    }
}
```

### Bag vs Set Comparison

| Feature | Bag | Set |
|---------|-----|-----|
| Duplicates | Allowed | Not allowed |
| Order | No specific order | No specific order |
| Primary Use | Collection with duplicates | Unique elements only |
| Example | Shopping cart items | Unique user IDs |

---

## Design Decisions and Best Practices

### Handling Unusual Conditions

When designing ADT operations, consider:
1. **Assume it won't happen** - Simplest but risky
2. **Ignore invalid situations** - May cause silent failures
3. **Guess at client's intention** - Can lead to unexpected behavior
4. **Return value that signals problem** - Clear but requires checking
5. **Return boolean for success/failure** - Simple and clear
6. **Throw exceptions** - Forces error handling

### Example: Enhanced Bag with Exception Handling

```java
public class SafeBag<T> implements BagInterface<T> {
    private final T[] bag;
    private int numberOfEntries;
    
    @SuppressWarnings("unchecked")
    public SafeBag(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be positive");
        }
        bag = (T[]) new Object[capacity];
        numberOfEntries = 0;
    }
    
    @Override
    public boolean add(T newEntry) {
        if (newEntry == null) {
            throw new IllegalArgumentException("Cannot add null entry");
        }
        
        if (numberOfEntries >= bag.length) {
            throw new IllegalStateException("Bag is full");
        }
        
        bag[numberOfEntries] = newEntry;
        numberOfEntries++;
        return true;
    }
    
    @Override
    public T remove() {
        if (isEmpty()) {
            throw new IllegalStateException("Cannot remove from empty bag");
        }
        
        T result = bag[numberOfEntries - 1];
        bag[numberOfEntries - 1] = null;
        numberOfEntries--;
        return result;
    }
    
    // ... other methods with appropriate error handling
}
```

---

## Key Takeaways

### ADT Benefits
- **Information Hiding**: Implementation details are hidden from users
- **Modularity**: Easy to modify implementation without affecting client code
- **Reusability**: Can be used in different contexts
- **Abstraction**: Focus on what operations do, not how they work

### Implementation Comparison

| Implementation | Advantages | Disadvantages |
|----------------|------------|---------------|
| Array-based | Fast random access, memory efficient | Fixed size, expensive insertions/deletions |
| Linked List | Dynamic size, efficient insertions | No random access, extra memory for pointers |

### When to Use Bag ADT
- ✅ When you need to store a collection of items
- ✅ Order doesn't matter
- ✅ Duplicates are allowed
- ✅ Simple add/remove operations are sufficient
- ✅ Counting occurrences of items is important

### Design Principles
1. **Separate interface from implementation**
2. **Use meaningful method names**
3. **Provide comprehensive documentation**
4. **Handle edge cases appropriately**
5. **Consider performance implications**
6. **Design for extensibility**

---

> 💡 **Remember**: The Bag ADT demonstrates fundamental concepts of abstraction, encapsulation, and interface design that are essential for understanding more complex data structures and algorithms in computer science.