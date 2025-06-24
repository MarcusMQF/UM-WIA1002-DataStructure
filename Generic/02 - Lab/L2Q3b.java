public class L2Q3b {
    public static void main(String[] args) {
        // Create some StorePairGeneric objects
        StorePairGeneric<Integer> pair1 = new StorePairGeneric<>(5, 10);
        StorePairGeneric<Integer> pair2 = new StorePairGeneric<>(5, 20);  // Same first value as pair1
        StorePairGeneric<Integer> pair3 = new StorePairGeneric<>(15, 10);  // Different first value
        
        // Test equality
        System.out.println("pair1.equals(pair2): " + pair1.equals(pair2));  // Should be true (same first value)
        System.out.println("pair1.equals(pair3): " + pair1.equals(pair3));  // Should be false (different first value)
        
        // Test with different types
        StorePairGeneric<String> strPair1 = new StorePairGeneric<>("Hello", "World");
        StorePairGeneric<String> strPair2 = new StorePairGeneric<>("Hello", "Java");
        System.out.println("strPair1.equals(strPair2): " + strPair1.equals(strPair2));  // Should be true
    }
}

class StorePairGeneric<T> {
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
        // Note: we use the equals method of the first object to handle null correctly
        return first != null ? first.equals(that.first) : that.first == null;
    }
}
