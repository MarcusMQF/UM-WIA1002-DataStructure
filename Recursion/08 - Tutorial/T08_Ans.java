public class T08_Ans {
    
    public static void main(String[] args) {
        System.out.println("=== RECURSION TUTORIAL ANSWERS ===\n");
        
        // Question 1 & 2: Demonstrate the problems
        demonstrateProblems();
        
        // Question 3: Reverse string
        System.out.println("=== QUESTION 3: Reverse String ===");
        String original = "String";
        String reversed = reverseString(original);
        System.out.println("Original: " + original);
        System.out.println("Reversed: " + reversed);
        System.out.println();
        
        // Question 4: Sum from 1 to N
        System.out.println("=== QUESTION 4: Sum 1 to N ===");
        int n = 5;
        System.out.println("Sum from 1 to " + n + " = " + sumToN(n));
        System.out.println("\nTracing sum(5):");
        sumToNWithTrace(5, 0);
        System.out.println();
        
        // Question 5: Print digits
        System.out.println("=== QUESTION 5: Print Digits ===");
        int number = 4567;
        System.out.print("Number " + number + " as digits: ");
        printDigit(number);
        System.out.println();
        
        System.out.print("Number 123 as digits: ");
        printDigit(123);
        System.out.println();
    }
    
    // ==================== QUESTION 1 & 2: PROBLEMS ====================
    
    public static void demonstrateProblems() {
        System.out.println("=== QUESTION 1: Problem with f(0) ===");
        System.out.println("public static int f(int n) {");
        System.out.println("    if (n == 1) return n;");
        System.out.println("    else return n * f(n-1);");
        System.out.println("}");
        System.out.println();
        System.out.println("PROBLEM: When f(0) is called:");
        System.out.println("- n = 0, which is not equal to 1");
        System.out.println("- So it goes to else: return 0 * f(0-1) = 0 * f(-1)");
        System.out.println("- f(-1): n = -1, not equal to 1, so return -1 * f(-2)");
        System.out.println("- This continues infinitely: f(-2), f(-3), f(-4)...");
        System.out.println("- RESULT: StackOverflowError - infinite recursion!");
        System.out.println("- FIX: Add base case for n <= 0");
        System.out.println();
        
        System.out.println("=== QUESTION 2: Problem with f() ===");
        System.out.println("public static int f(int n) {");
        System.out.println("    if (n == 0) return n;");
        System.out.println("    else return f(n+1) + n;");
        System.out.println("}");
        System.out.println();
        System.out.println("PROBLEM: When f(any positive number) is called:");
        System.out.println("- Example f(1): n = 1, not equal to 0");
        System.out.println("- So it goes to else: return f(1+1) + 1 = f(2) + 1");
        System.out.println("- f(2): n = 2, not equal to 0, so return f(3) + 2");
        System.out.println("- This continues infinitely: f(3), f(4), f(5)...");
        System.out.println("- RESULT: StackOverflowError - infinite recursion!");
        System.out.println("- FIX: The recursive case should move toward base case (n-1, not n+1)");
        System.out.println();
    }
    
    // ==================== QUESTION 3: REVERSE STRING ====================
    
    public static String reverseString(String str) {
        // Base case: empty string or single character
        if (str.length() <= 1) {
            return str;
        }
        
        // Recursive case: last character + reverse of remaining string
        char lastChar = str.charAt(str.length() - 1);
        String remaining = str.substring(0, str.length() - 1);
        
        return lastChar + reverseString(remaining);
    }
    
    // ==================== QUESTION 4: SUM 1 TO N ====================
    
    public static int sumToN(int n) {
        // Base case: when n reaches 1
        if (n == 1) {
            return 1;
        }
        
        // Recursive case: n + sum of (n-1)
        return n + sumToN(n - 1);
    }
    
    // Helper method to show trace
    public static int sumToNWithTrace(int n, int depth) {
        String indent = "  ".repeat(depth);
        System.out.println(indent + "sum(" + n + ")");
        
        // Base case
        if (n == 1) {
            System.out.println(indent + "  → Base case: return 1");
            return 1;
        }
        
        // Recursive case
        System.out.println(indent + "  → " + n + " + sum(" + (n-1) + ")");
        int result = n + sumToNWithTrace(n - 1, depth + 1);
        System.out.println(indent + "  → " + n + " + " + (result - n) + " = " + result);
        
        return result;
    }
    
    // ==================== QUESTION 5: PRINT DIGITS ====================
    
    public static void printDigit(int number) {
        // Base case: single digit
        if (number < 10) {
            System.out.print(number + " ");
            return;
        }
        
        // Recursive case: print all digits except last, then print last digit
        int lastDigit = number % 10;
        int remainingDigits = number / 10;
        
        printDigit(remainingDigits);  // Print remaining digits first
        System.out.print(lastDigit + " ");  // Then print last digit
    }
    
    // ==================== ADDITIONAL EXPLANATIONS ====================
    
    /*
     * QUESTION 3 EXPLANATION:
     * To reverse "String":
     * 1. Take last char 'g' + reverse("Strin")
     * 2. Take last char 'n' + reverse("Stri") 
     * 3. Take last char 'i' + reverse("Str")
     * 4. Take last char 'r' + reverse("St")
     * 5. Take last char 't' + reverse("S")
     * 6. Base case: "S" (length 1) returns "S"
     * 7. Build back: "t" + "S" = "tS"
     * 8. Build back: "r" + "tS" = "rtS"
     * 9. Build back: "i" + "rtS" = "irtS"
     * 10. Build back: "n" + "irtS" = "nirtS"
     * 11. Build back: "g" + "nirtS" = "gnirtS"
     */
    
    /*
     * QUESTION 4 EXPLANATION:
     * Base case: n = 1, return 1
     * Recursive case: n + sum(n-1)
     * 
     * Trace for sum(5):
     * sum(5) = 5 + sum(4)
     *        = 5 + (4 + sum(3))
     *        = 5 + (4 + (3 + sum(2)))
     *        = 5 + (4 + (3 + (2 + sum(1))))
     *        = 5 + (4 + (3 + (2 + 1)))
     *        = 5 + (4 + (3 + 3))
     *        = 5 + (4 + 6)
     *        = 5 + 10
     *        = 15
     */
    
    /*
     * QUESTION 5 EXPLANATION:
     * To print digits of 4567:
     * 1. 4567 ÷ 10 = 456 remainder 7
     * 2. Recursively print 456 first
     * 3. Then print 7
     * 
     * This ensures left-to-right printing:
     * printDigit(456) → prints "4 5 6 "
     * Then print 7 → complete output "4 5 6 7 "
     */
}
