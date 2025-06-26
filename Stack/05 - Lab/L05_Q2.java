import java.util.ArrayList;
import java.util.Scanner;

/**
 * MyStack class from Q1(a) - needed for TestIntMyStack
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
 * Q2 - TestIntMyStack class
 * Test program for MyStack with integer values
 */
class TestIntMyStack {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MyStack<Integer> intStack = new MyStack<>();
        
        // Q2(a) - Prompt user to enter an integer value
        System.out.print("Enter an integer value: ");
        int userValue = scanner.nextInt();
        
        // Q2(b) - Push values 1 through the user entered value onto the stack
        System.out.println("\nPushing values 1 through " + userValue + " onto the stack:");
        for (int i = 1; i <= userValue; i++) {
            intStack.push(i);
            System.out.println("Pushed: " + i + " | Stack: " + intStack.toString());
        }
        
        // Q2(c) - Print the size of the stack
        System.out.println("\nSize of the stack: " + intStack.getSize());
        
        // Q2(d) - Display contents by repeatedly calling pop() until stack is empty
        System.out.println("\nDisplaying contents by popping until stack is empty:");
        System.out.println("Popping order: ");
        
        int position = 1;
        while (!intStack.isEmpty()) {
            int poppedValue = intStack.pop();
            System.out.println(position + ". Popped: " + poppedValue + " | Remaining stack: " + intStack.toString());
            position++;
        }
        
        System.out.println("\nStack is now empty: " + intStack.isEmpty());
        
        // Analysis of the output
        System.out.println("\n============================================================");
        System.out.println("ANALYSIS OF OUTPUT:");
        System.out.println("============================================================");
        System.out.println("Question: What is the output of the elements? What is the order, why?");
        System.out.println();
        System.out.println("Answer:");
        System.out.println("1. OUTPUT ORDER: The elements are popped in REVERSE order");
        System.out.println("   - If user enters 5, push order is: 1, 2, 3, 4, 5");
        System.out.println("   - Pop order will be: 5, 4, 3, 2, 1");
        System.out.println();
        System.out.println("2. WHY THIS ORDER?");
        System.out.println("   - Stack follows LIFO (Last In, First Out) principle");
        System.out.println("   - The last element pushed (highest number) is the first to be popped");
        System.out.println("   - Elements are stacked on top of each other, like a stack of plates");
        System.out.println("   - You can only access the top element at any given time");
        System.out.println();
        System.out.println("3. STACK BEHAVIOR DEMONSTRATION:");
        System.out.println("   Push 1: [1]           <- 1 is at bottom");
        System.out.println("   Push 2: [1, 2]        <- 2 is on top of 1");
        System.out.println("   Push 3: [1, 2, 3]     <- 3 is on top of 2");
        System.out.println("   Pop():  [1, 2]        <- 3 is removed first");
        System.out.println("   Pop():  [1]           <- 2 is removed next");
        System.out.println("   Pop():  []            <- 1 is removed last");
        
        scanner.close();
    }
}

public class L05_Q2 {
    public static void main(String[] args) {
        // Run the TestIntMyStack program
        TestIntMyStack.main(args);
    }
}
