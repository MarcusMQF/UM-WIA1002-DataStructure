public class L2Q3a {
    public static void main(String[] args) {
        // Test with Integer
        StorePairGeneric<Integer> intPair = new StorePairGeneric<>(5, 10);
        System.out.println(intPair);
        
        // Test with String
        StorePairGeneric<String> strPair = new StorePairGeneric<>("Hello", "World");
        System.out.println(strPair);
        
        // Test with Double
        StorePairGeneric<Double> doublePair = new StorePairGeneric<>(2.5, 7.8);
        System.out.println(doublePair);
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
}
