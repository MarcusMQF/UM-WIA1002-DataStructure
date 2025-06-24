public class L2Q2 {
    public static void main(String[] args) {
        // Test with Integer values
        System.out.println("Maximum of 3, 4, 5 is: " + 
            CompareMax.maximum(3, 4, 5));
        
        // Test with Double values
        System.out.println("Maximum of 6.6, 7.7, 8.8 is: " + 
            CompareMax.maximum(6.6, 7.7, 8.8));
        
        // Test with String values
        System.out.println("Maximum of apple, orange, banana is: " + 
            CompareMax.maximum("apple", "orange", "banana"));
    }
}

class CompareMax {
    // T extends Comparable<T> ensures that T implements the Comparable interface
    public static <T extends Comparable<T>> T maximum(T a, T b, T c) {
        T max = a;

        if(b.compareTo(max) > 0){
            max = b;
        }

        if(c.compareTo(max) > 0){
            max = c;
        }

        return max;
    }
}
