import java.util.ArrayList;

/**
 * MyStack class - Stack ADT implementation
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

public class L05_Q3 {
    
    /**
     * Q3 - Algorithm to find sum of every element in stack S
     * Using only stack ADT operations (push, pop, peek, isEmpty, getSize)
     * 
     * APPROACH 1: Non-destructive method (preserves original stack)
     * @param S the stack containing positive integers
     * @return sum of all elements in the stack
     */
    public static int findSumPreserveStack(MyStack<Integer> S) {
        // Check if stack is empty
        if (S.isEmpty()) {
            return 0;
        }
        
        // Create a temporary stack to hold elements
        MyStack<Integer> tempStack = new MyStack<>();
        int sum = 0;
        
        // Step 1: Pop all elements from S, calculate sum, and store in tempStack
        System.out.println("Step 1: Popping elements from original stack and calculating sum:");
        while (!S.isEmpty()) {
            Integer element = S.pop();
            sum += element;
            tempStack.push(element);
            System.out.println("  Popped: " + element + ", Running sum: " + sum);
        }
        
        // Step 2: Restore original stack by popping from tempStack and pushing back to S
        System.out.println("Step 2: Restoring original stack:");
        while (!tempStack.isEmpty()) {
            Integer element = tempStack.pop();
            S.push(element);
            System.out.println("  Restored: " + element + " to original stack");
        }
        
        return sum;
    }
    
    /**
     * APPROACH 2: Destructive method (modifies original stack)
     * This approach is simpler but destroys the original stack
     * @param S the stack containing positive integers
     * @return sum of all elements in the stack
     */
    public static int findSumDestructive(MyStack<Integer> S) {
        int sum = 0;
        
        System.out.println("Calculating sum (destructive approach):");
        while (!S.isEmpty()) {
            Integer element = S.pop();
            sum += element;
            System.out.println("  Popped: " + element + ", Running sum: " + sum);
        }
        
        return sum;
    }
    
    /**
     * APPROACH 3: Recursive method (preserves original stack)
     * @param S the stack containing positive integers
     * @return sum of all elements in the stack
     */
    public static int findSumRecursive(MyStack<Integer> S) {
        // Base case: if stack is empty
        if (S.isEmpty()) {
            return 0;
        }
        
        // Pop the top element
        Integer top = S.pop();
        
        // Recursively find sum of remaining elements
        int remainingSum = findSumRecursive(S);
        
        // Restore the popped element
        S.push(top);
        
        // Return sum of current element and remaining elements
        return top + remainingSum;
    }
    
    /**
     * Test program to demonstrate the algorithms
     */
    public static void main(String[] args) {
        System.out.println("======================================================================");
        System.out.println("Q3: FINDING SUM OF ELEMENTS IN STACK USING ONLY STACK ADT OPERATIONS");
        System.out.println("======================================================================");
        
        // Create and populate test stack
        MyStack<Integer> testStack = new MyStack<>();
        int[] testValues = {5, 10, 15, 20, 25};
        
        System.out.println("Creating test stack with values: ");
        for (int value : testValues) {
            testStack.push(value);
            System.out.println("Pushed: " + value);
        }
        
        System.out.println("Original stack: " + testStack.toString());
        System.out.println("Expected sum: " + (5 + 10 + 15 + 20 + 25) + " = 75");
        
        // Test Approach 1: Non-destructive
        System.out.println("\n==================================================");
        System.out.println("APPROACH 1: NON-DESTRUCTIVE METHOD");
        System.out.println("==================================================");
        int sum1 = findSumPreserveStack(testStack);
        System.out.println("Sum calculated: " + sum1);
        System.out.println("Stack after calculation: " + testStack.toString());
        System.out.println("Stack preserved: " + (!testStack.isEmpty()));
        
        // Test Approach 2: Destructive (using a copy)
        System.out.println("\n==================================================");
        System.out.println("APPROACH 2: DESTRUCTIVE METHOD (using copy)");
        System.out.println("==================================================");
        MyStack<Integer> copyStack = createCopy(testStack);
        int sum2 = findSumDestructive(copyStack);
        System.out.println("Sum calculated: " + sum2);
        System.out.println("Copy stack after calculation: " + copyStack.toString());
        System.out.println("Copy stack empty: " + copyStack.isEmpty());
        
        // Test Approach 3: Recursive (using another copy)
        System.out.println("\n==================================================");
        System.out.println("APPROACH 3: RECURSIVE METHOD");
        System.out.println("==================================================");
        MyStack<Integer> recursiveStack = createCopy(testStack);
        System.out.println("Stack before recursive calculation: " + recursiveStack.toString());
        int sum3 = findSumRecursive(recursiveStack);
        System.out.println("Sum calculated: " + sum3);
        System.out.println("Stack after recursive calculation: " + recursiveStack.toString());
        System.out.println("Stack preserved: " + (!recursiveStack.isEmpty()));
        
        // Algorithm Analysis
        System.out.println("\n======================================================================");
        System.out.println("ALGORITHM ANALYSIS");
        System.out.println("======================================================================");
        System.out.println("PROBLEM: Find sum using ONLY stack ADT operations:");
        System.out.println("  - push(E o)");
        System.out.println("  - pop()");
        System.out.println("  - peek()");
        System.out.println("  - isEmpty()");
        System.out.println("  - getSize()");
        System.out.println();
        System.out.println("CHALLENGE: Stack only allows access to top element!");
        System.out.println();
        System.out.println("SOLUTIONS:");
        System.out.println("1. NON-DESTRUCTIVE: Use temporary stack to preserve original");
        System.out.println("   - Time: O(n), Space: O(n)");
        System.out.println("   - Pros: Preserves original stack");
        System.out.println("   - Cons: Uses extra space");
        System.out.println();
        System.out.println("2. DESTRUCTIVE: Pop all elements while summing");
        System.out.println("   - Time: O(n), Space: O(1)");
        System.out.println("   - Pros: Space efficient");
        System.out.println("   - Cons: Destroys original stack");
        System.out.println();
        System.out.println("3. RECURSIVE: Use recursion stack to preserve order");
        System.out.println("   - Time: O(n), Space: O(n) due to recursion");
        System.out.println("   - Pros: Elegant solution, preserves stack");
        System.out.println("   - Cons: Uses system stack space");
    }
    
    /**
     * Helper method to create a copy of the stack for testing
     */
    private static MyStack<Integer> createCopy(MyStack<Integer> original) {
        MyStack<Integer> temp = new MyStack<>();
        MyStack<Integer> copy = new MyStack<>();
        
        // Move elements to temp
        while (!original.isEmpty()) {
            temp.push(original.pop());
        }
        
        // Move elements back to original and to copy
        while (!temp.isEmpty()) {
            Integer element = temp.pop();
            original.push(element);
            copy.push(element);
        }
        
        return copy;
    }
}
