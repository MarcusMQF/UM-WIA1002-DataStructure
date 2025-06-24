public class L2Q3e {
    public static void main(String[] args) {
        // Create three StorePairGeneric objects with Integer type
        StorePairGeneric<Integer> a = new StorePairGeneric<>(6, 4);
        StorePairGeneric<Integer> b = new StorePairGeneric<>(2, 2);
        StorePairGeneric<Integer> c = new StorePairGeneric<>(6, 3);
        
        // Display the objects
        System.out.println("Object a: " + a);
        System.out.println("Object b: " + b);
        System.out.println("Object c: " + c);
        
        // Testing the equals() method
        System.out.println("\n--- Testing equals() method ---");
        System.out.println("a.equals(b): " + a.equals(b));  // Should be false (6 != 2)
        System.out.println("a.equals(c): " + a.equals(c));  // Should be true (6 == 6)
        System.out.println("b.equals(c): " + b.equals(c));  // Should be false (2 != 6)
        
        // Testing the compareTo() method
        System.out.println("\n--- Testing compareTo() method ---");
        System.out.println("a.compareTo(b): " + a.compareTo(b));  // Should be positive (6 > 2)
        System.out.println("a.compareTo(c): " + a.compareTo(c));  // Should be 0 (6 == 6)
        System.out.println("b.compareTo(c): " + b.compareTo(c));  // Should be negative (2 < 6)
        
        // Additional tests to verify relationship properties
        System.out.println("\n--- Verifying relationship properties ---");
        
        // Testing transitivity: if a equals c and c equals a, then they should be comparable as 0
        boolean equalsTransitive = a.equals(c) && c.equals(a) && a.compareTo(c) == 0;
        System.out.println("Equals/compareTo consistency for a and c: " + equalsTransitive);
        
        // Testing consistency: if a is not equal to b, then compareTo should not be 0
        boolean compareConsistent = !a.equals(b) && a.compareTo(b) != 0;
        System.out.println("Equals/compareTo consistency for a and b: " + compareConsistent);
    }
}

class StorePairGeneric<T extends Comparable<T>> implements Comparable<StorePairGeneric<T>> {
    private T first, second;
    
    public StorePairGeneric(T first, T second) {
        this.first = first;
        this.second = second;
    }
    
    public T getFirst() {
        return first;
    }
    
    public T getSecond() {
        return second;
    }
    
    public void setPair(T first, T second) {
        this.first = first;
        this.second = second;
    }
    
    @Override
    public String toString() {
        return "first = " + first + " second = " + second;
    }
    
    @Override
    public boolean equals(Object o) {
        // Check if same object reference
        if (this == o) return true;
        
        // Check if null or different class
        if (o == null || getClass() != o.getClass()) return false;
        
        // Cast to StorePairGeneric
        StorePairGeneric<?> that = (StorePairGeneric<?>) o;
        
        // Compare first values
        return first != null ? first.equals(that.first) : that.first == null;
    }
    
    @Override
    public int compareTo(StorePairGeneric<T> other) {
        // Compare the first values
        return this.first.compareTo(other.first);
    }
}
