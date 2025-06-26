import java.util.ArrayList;
import java.util.Scanner;

/**
 * MyStack class - Stack ADT implementation for palindrome checking
 */
class MyStack<E> {
    private ArrayList<E> list;
    
    public MyStack() {
        list = new ArrayList<>();
    }
    
    public void push(E o) {
        list.add(o);
    }
    
    public E pop() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }
        return list.remove(list.size() - 1);
    }
    
    public E peek() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }
        return list.get(list.size() - 1);
    }
    
    public int getSize() {
        return list.size();
    }
    
    public boolean isEmpty() {
        return list.isEmpty();
    }
    
    @Override
    public String toString() {
        return "Stack: " + list.toString();
    }
    
    public boolean search(E o) {
        return list.contains(o);
    }
}

/**
 * Q4 - Palindrome Checker using Stack
 */
public class L05_Q4 {
    
    /**
     * Main palindrome checking method using Stack
     * @param input the string to check (max 15 characters)
     * @return true if palindrome, false otherwise
     */
    public static boolean isPalindrome(String input) {
        // Validate input length
        if (input.length() > 15) {
            throw new IllegalArgumentException("String length cannot exceed 15 characters");
        }
        
        // Convert to lowercase and remove spaces for case-insensitive checking
        String cleanInput = input.toLowerCase().replaceAll("\\s+", "");
        
        // Create stack to store characters
        MyStack<Character> charStack = new MyStack<>();
        
        // Step 1: Push all characters onto the stack
        System.out.println("Step 1: Pushing characters onto stack:");
        for (int i = 0; i < cleanInput.length(); i++) {
            char ch = cleanInput.charAt(i);
            charStack.push(ch);
            System.out.println("  Pushed: '" + ch + "' | Stack: " + charStack.toString());
        }
        
        // Step 2: Pop characters and compare with original string
        System.out.println("\nStep 2: Popping characters and comparing:");
        StringBuilder reversedString = new StringBuilder();
        
        for (int i = 0; i < cleanInput.length(); i++) {
            char originalChar = cleanInput.charAt(i);
            char poppedChar = charStack.pop();
            reversedString.append(poppedChar);
            
            System.out.printf("  Position %d: Original='%c', Popped='%c', Match=%b%n", 
                            i, originalChar, poppedChar, (originalChar == poppedChar));
            
            // If characters don't match, it's not a palindrome
            if (originalChar != poppedChar) {
                System.out.println("Mismatch found! Not a palindrome.");
                return false;
            }
        }
        
        System.out.println("All characters match! It's a palindrome.");
        System.out.println("  Original:  " + cleanInput);
        System.out.println("  Reversed:  " + reversedString.toString());
        
        return true;
    }
    
    /**
     * Alternative approach: Build reversed string first, then compare
     * @param input the string to check
     * @return true if palindrome, false otherwise
     */
    public static boolean isPalindromeAlternative(String input) {
        if (input.length() > 15) {
            throw new IllegalArgumentException("String length cannot exceed 15 characters");
        }
        
        String cleanInput = input.toLowerCase().replaceAll("\\s+", "");
        MyStack<Character> charStack = new MyStack<>();
        
        // Push all characters
        for (char ch : cleanInput.toCharArray()) {
            charStack.push(ch);
        }
        
        // Build reversed string by popping
        StringBuilder reversed = new StringBuilder();
        while (!charStack.isEmpty()) {
            reversed.append(charStack.pop());
        }
        
        // Compare original with reversed
        return cleanInput.equals(reversed.toString());
    }
    
    /**
     * Test method with various palindrome examples
     */
    public static void runTests() {
        System.out.println("========================================");
        System.out.println("Q4: PALINDROME CHECKER USING STACK - COMPREHENSIVE TESTING");
        System.out.println("========================================");
        
        // Test cases (max 15 characters each)
        String[] testCases = {
            "racecar",      // Classic palindrome
            "A man a plan a canal Panama".substring(0, 15), // "A man a plan a"
            "madam",        // Simple palindrome
            "hello",        // Not a palindrome
            "level",        // Palindrome
            "world",        // Not a palindrome
            "noon",         // Palindrome
            "Racecar",      // Case-insensitive palindrome
            "a",            // Single character
            "ab",           // Two characters, not palindrome
            "aa",           // Two characters, palindrome
            "abcba",        // Odd length palindrome
            "abccba",       // Even length palindrome
            "abcdef",       // Not palindrome
            "Was it a car or a cat I saw".substring(0, 15) // "Was it a car or"
        };
        
        System.out.println("Testing multiple strings (max 15 characters each):\n");
        
        for (int i = 0; i < testCases.length; i++) {
            String testString = testCases[i];
            System.out.println("Test " + (i + 1) + ": \"" + testString + "\"");
            System.out.println("Length: " + testString.length() + " characters");
            
            boolean result = isPalindrome(testString);
            System.out.println("Result: " + (result ? "PALINDROME" : "NOT PALINDROME"));
            System.out.println("------------------------------------------------------------");
        }
    }
    
    /**
     * Interactive method for user input
     */
    public static void interactiveMode() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("\n============================================================");
        System.out.println("INTERACTIVE PALINDROME CHECKER");
        System.out.println("============================================================");
        System.out.println("Enter strings to check (max 15 characters)");
        System.out.println("Type 'quit' to exit");
        
        while (true) {
            System.out.print("\nEnter a string: ");
            String input = scanner.nextLine().trim();
            
            if (input.equalsIgnoreCase("quit")) {
                break;
            }
            
            if (input.isEmpty()) {
                System.out.println("Please enter a non-empty string.");
                continue;
            }
            
            if (input.length() > 15) {
                System.out.println("Error: String length exceeds 15 characters!");
                System.out.println("Your string: \"" + input + "\" (" + input.length() + " characters)");
                continue;
            }
            
            System.out.println("\nAnalyzing: \"" + input + "\"");
            boolean result = isPalindrome(input);
            System.out.println("\nFinal Result: " + (result ? "PALINDROME" : "NOT PALINDROME"));
        }
        
        scanner.close();
        System.out.println("Thank you for using the Palindrome Checker!");
    }
    
    /**
     * Main method - program entry point
     */
    public static void main(String[] args) {
        System.out.println("Q4: PALINDROME CHECKER USING STACK");
        System.out.println("Maximum string length: 15 characters");
        System.out.println();
        
        // Algorithm explanation
        System.out.println("ALGORITHM EXPLANATION:");
        System.out.println("1. A palindrome reads the same forwards and backwards");
        System.out.println("2. Stack's LIFO property naturally reverses the string");
        System.out.println("3. Push all characters onto stack");
        System.out.println("4. Pop characters and compare with original order");
        System.out.println("5. If all characters match, it's a palindrome");
        System.out.println();
        
        // Run comprehensive tests
        runTests();
        
        // Interactive mode
        interactiveMode();
        
        // Summary
        System.out.println("\n================================================================================");
        System.out.println("ALGORITHM ANALYSIS:");
        System.out.println("================================================================================");
        System.out.println("Time Complexity: O(n) where n is string length");
        System.out.println("Space Complexity: O(n) for the stack storage");
        System.out.println("Maximum Input: 15 characters as specified");
        System.out.println("Features: Case-insensitive, ignores spaces");
        System.out.println("Stack ADT Operations Used: push(), pop(), isEmpty()");
    }
}
