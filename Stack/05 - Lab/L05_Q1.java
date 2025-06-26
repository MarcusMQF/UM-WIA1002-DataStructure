import java.util.ArrayList;

/**
 * Q1(a) - Generic MyStack class implementation using ArrayList
 * @param <E> the type of elements in this stack
 */
class MyStack<E> {
    private ArrayList<E> list;
    
    /**
     * Constructor to create an empty stack
     */
    public MyStack() {
        list = new ArrayList<>();
    }
    
    /**
     * 1) Push an element onto the top of the stack
     * @param o the element to be pushed
     */
    public void push(E o) {
        list.add(o);
    }
    
    /**
     * 2) Remove and return the top element from the stack
     * @return the top element of the stack
     * @throws RuntimeException if stack is empty
     */
    public E pop() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }
        return list.remove(list.size() - 1);
    }
    
    /**
     * 3) Return the top element without removing it
     * @return the top element of the stack
     * @throws RuntimeException if stack is empty
     */
    public E peek() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }
        return list.get(list.size() - 1);
    }
    
    /**
     * 4) Return the number of elements in the stack
     * @return the size of the stack
     */
    public int getSize() {
        return list.size();
    }
    
    /**
     * 5) Check if the stack is empty
     * @return true if stack is empty, false otherwise
     */
    public boolean isEmpty() {
        return list.isEmpty();
    }
    
    /**
     * 6) Return string representation of the stack
     * @return string representation with bottom to top order
     */
    @Override
    public String toString() {
        return "Stack: " + list.toString();
    }
    
    /**
     * 7) Search for an element in the stack
     * @param o the element to search for
     * @return true if element is found, false otherwise
     */
    public boolean search(E o) {
        return list.contains(o);
    }
}

/**
 * Q1(b) and Q1(c) - Test program for MyStack class
 */
class TestMyStack {
    public static void main(String[] args) {
        System.out.println("=== Q1(b) - Testing MyStack with Character type ===");
        
        // Q1(b) - Create a stack of type Character
        MyStack<Character> charStack = new MyStack<>();
        
        // Q1(b).1 - Add elements a, b, and c in the stack (following given order)
        charStack.push('a');
        charStack.push('b');
        charStack.push('c');
        
        // Q1(b).2 - Print the stack
        System.out.println("Character stack after adding a, b, c:");
        System.out.println(charStack.toString());
        
        // Q1(b).3 - Check if element 'b' is in the stack
        boolean foundB = charStack.search('b');
        System.out.println("Is element 'b' in the stack? " + foundB);
        
        // Q1(b).4 - Check if element 'k' is in the stack
        boolean foundK = charStack.search('k');
        System.out.println("Is element 'k' in the stack? " + foundK);
        
        System.out.println("\n=== Q1(c) - Testing MyStack with Integer type ===");
        
        // Q1(c) - Create a second stack of type Integer
        MyStack<Integer> intStack = new MyStack<>();
        
        // Q1(c).1 - Add elements 1, 2, and 3 in the stack (following given order)
        intStack.push(1);
        intStack.push(2);
        intStack.push(3);
        
        // Q1(c).2 - Print the stack
        System.out.println("Integer stack after adding 1, 2, 3:");
        System.out.println(intStack.toString());
        
        // Q1(c).3 - Check if element '6' is in the stack
        boolean found6 = intStack.search(6);
        System.out.println("Is element '6' in the stack? " + found6);
        
        // Additional demonstration of other methods
        System.out.println("\n=== Additional Method Demonstrations ===");
        System.out.println("Character stack size: " + charStack.getSize());
        System.out.println("Integer stack size: " + intStack.getSize());
        System.out.println("Character stack top element (peek): " + charStack.peek());
        System.out.println("Integer stack top element (peek): " + intStack.peek());
        System.out.println("Is character stack empty? " + charStack.isEmpty());
        System.out.println("Is integer stack empty? " + intStack.isEmpty());
    }
}

// Main class for running the program
public class L05_Q1 {
    public static void main(String[] args) {
        // Run the test program
        TestMyStack.main(args);
    }
}
