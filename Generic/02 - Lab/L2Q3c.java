public class L2Q3c {
    public static void main(String[] args) {
        // Create some StorePairGeneric objects
        StorePairGeneric<Integer> pair1 = new StorePairGeneric<>(5, 10);
        StorePairGeneric<Integer> pair2 = new StorePairGeneric<>(5, 20);  // Same first value as pair1
        StorePairGeneric<Integer> pair3 = new StorePairGeneric<>(15, 10); // Greater first value
        StorePairGeneric<Integer> pair4 = new StorePairGeneric<>(2, 30);  // Smaller first value
        
        // Test compareTo method
        System.out.println("pair1.compareTo(pair2): " + pair1.compareTo(pair2)); // Should return 0 (equal)
        System.out.println("pair1.compareTo(pair3): " + pair1.compareTo(pair3)); // Should return negative (5 < 15)
        System.out.println("pair1.compareTo(pair4): " + pair1.compareTo(pair4)); // Should return positive (5 > 2)
        
        // Test with String objects
        StorePairGeneric<String> strPair1 = new StorePairGeneric<>("Hello", "World");
        StorePairGeneric<String> strPair2 = new StorePairGeneric<>("Apple", "Fruit");
        System.out.println("strPair1.compareTo(strPair2): " + strPair1.compareTo(strPair2)); // Should return positive ("Hello" > "Apple")
    }
}

// Updated class that implements Comparable
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
        // Compare the first values using their natural ordering
        // This works because T extends Comparable<T>
        return this.first.compareTo(other.first);
    }
}
