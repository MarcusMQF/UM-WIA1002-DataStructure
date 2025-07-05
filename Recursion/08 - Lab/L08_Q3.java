public class L08_Q3 {
    public static void main(String[] args) {
        // Test cases
        System.out.println("exponent(10, 3) = " + exponent(10, 3));
        System.out.println("exponent(2, 5) = " + exponent(2, 5));
        System.out.println("exponent(3, 4) = " + exponent(3, 4));
        System.out.println("exponent(5, 0) = " + exponent(5, 0));
        System.out.println("exponent(7, 1) = " + exponent(7, 1));
        
        // Trace demonstration
        System.out.println("\nTracing exponent(2, 4):");
        System.out.println("Result: " + exponentWithTrace(2, 4, 0));
    }
    
    public static long exponent(int x, int m) {
        // Base case: any number to the power of 0 is 1
        if (m == 0) {
            return 1;
        }
        
        // Recursive case: x^m = x * x^(m-1)
        return x * exponent(x, m - 1);
    }
    
    // Helper method to show the trace
    public static long exponentWithTrace(int x, int m, int depth) {
        // Print indentation based on recursion depth
        String indent = "  ".repeat(depth);
        System.out.println(indent + "exponent(" + x + ", " + m + ")");
        
        // Base case
        if (m == 0) {
            System.out.println(indent + "  → Base case: return 1");
            return 1;
        }
        
        // Recursive case
        System.out.println(indent + "  → " + x + " * exponent(" + x + ", " + (m-1) + ")");
        long result = x * exponentWithTrace(x, m - 1, depth + 1);
        System.out.println(indent + "  → " + x + " * " + (result/x) + " = " + result);
        
        return result;
    }
}
