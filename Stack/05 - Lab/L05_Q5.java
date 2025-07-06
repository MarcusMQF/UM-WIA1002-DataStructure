import java.util.ArrayList;
import java.util.Scanner;

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
        return list.toString();
    }
    
    public boolean search(E o) {
        return list.contains(o);
    }
}

/**
 * Q5 - Tower of Hanoi Solver using Stacks
 */
public class L05_Q5 {
    // Three rods represented as stacks
    private static MyStack<Integer> rodA = new MyStack<>();  // Source rod
    private static MyStack<Integer> rodB = new MyStack<>();  // Auxiliary rod
    private static MyStack<Integer> rodC = new MyStack<>();  // Destination rod
    
    // Move counter
    private static int moveCounter = 0;
    
    /**
     * Initialize the Tower of Hanoi with n disks on rod A
     * @param n number of disks
     */
    public static void initializeTower(int n) {
        // Clear all rods
        rodA = new MyStack<>();
        rodB = new MyStack<>();
        rodC = new MyStack<>();
        moveCounter = 0;
        
        // Place disks on rod A in descending order (largest at bottom)
        for (int i = n; i >= 1; i--) {
            rodA.push(i);
        }
        
        System.out.println("Tower of Hanoi initialized with " + n + " disks:");
        displayTower();
    }
    
    /**
     * Display the current state of all three rods
     */
    public static void displayTower() {
        System.out.println("=============================================================");
        System.out.println("                    TOWER OF HANOI                      ");
        System.out.println("=============================================================");
        System.out.printf("Rod A (Source):      %s%n", rodA.toString());
        System.out.printf("Rod B (Auxiliary):   %s%n", rodB.toString());
        System.out.printf("Rod C (Destination): %s%n", rodC.toString());
        System.out.println("=============================================================");
        
        // Visual representation
        System.out.println("\nVisual Representation:");
        displayVisual();
        System.out.println();
    }
    
    /**
     * Display visual representation of the towers
     */
    public static void displayVisual() {
        // Get max height
        int maxHeight = Math.max(Math.max(rodA.getSize(), rodB.getSize()), rodC.getSize());
        if (maxHeight == 0) maxHeight = 1;
        
        // Create visual arrays
        String[][] visual = new String[maxHeight][3];
        
        // Fill rod A
        ArrayList<Integer> rodAList = getStackContents(rodA);
        for (int i = 0; i < maxHeight; i++) {
            int diskIndex = rodAList.size() - 1 - i;
            if (diskIndex >= 0) {
                visual[i][0] = "[" + rodAList.get(diskIndex) + "]";
            } else {
                visual[i][0] = " | ";
            }
        }
        
        // Fill rod B
        ArrayList<Integer> rodBList = getStackContents(rodB);
        for (int i = 0; i < maxHeight; i++) {
            int diskIndex = rodBList.size() - 1 - i;
            if (diskIndex >= 0) {
                visual[i][1] = "[" + rodBList.get(diskIndex) + "]";
            } else {
                visual[i][1] = " | ";
            }
        }
        
        // Fill rod C
        ArrayList<Integer> rodCList = getStackContents(rodC);
        for (int i = 0; i < maxHeight; i++) {
            int diskIndex = rodCList.size() - 1 - i;
            if (diskIndex >= 0) {
                visual[i][2] = "[" + rodCList.get(diskIndex) + "]";
            } else {
                visual[i][2] = " | ";
            }
        }
        
        // Display from top to bottom
        for (int i = 0; i < maxHeight; i++) {
            System.out.printf("   %s      %s      %s%n", visual[i][0], visual[i][1], visual[i][2]);
        }
        System.out.println("  ===    ===    ===");
        System.out.println("   A      B      C");
    }
    
    /**
     * Helper method to get stack contents as ArrayList
     */
    private static ArrayList<Integer> getStackContents(MyStack<Integer> stack) {
        ArrayList<Integer> contents = new ArrayList<>();
        MyStack<Integer> temp = new MyStack<>();
        
        // Move to temp stack
        while (!stack.isEmpty()) {
            temp.push(stack.pop());
        }
        
        // Move back to original and build contents list
        while (!temp.isEmpty()) {
            Integer disk = temp.pop();
            contents.add(disk);
            stack.push(disk);
        }
        
        return contents;
    }
    
    /**
     * Move a disk from source rod to destination rod
     * @param source source stack
     * @param destination destination stack
     * @param sourceName source rod name
     * @param destName destination rod name
     */
    public static void moveDisk(MyStack<Integer> source, MyStack<Integer> destination, 
                               String sourceName, String destName) {
        if (source.isEmpty()) {
            throw new IllegalStateException("Cannot move from empty rod " + sourceName);
        }
        
        Integer disk = source.peek();
        
        // Check if move is valid (no disk on smaller disk)
        if (!destination.isEmpty() && destination.peek() < disk) {
            throw new IllegalStateException("Cannot place disk " + disk + 
                " on smaller disk " + destination.peek());
        }
        
        // Perform the move
        disk = source.pop();
        destination.push(disk);
        moveCounter++;
        
        System.out.println("Move " + moveCounter + ": Move disk " + disk + 
                          " from Rod " + sourceName + " to Rod " + destName);
        displayTower();
        
        // Add pause for visualization
        try {
            Thread.sleep(1000); // 1 second pause
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    /**
     * Recursive solution to Tower of Hanoi
     * @param n number of disks to move
     * @param source source stack
     * @param destination destination stack
     * @param auxiliary auxiliary stack
     * @param sourceName source rod name
     * @param destName destination rod name
     * @param auxName auxiliary rod name
     */
    public static void solveHanoi(int n, MyStack<Integer> source, MyStack<Integer> destination,
                                 MyStack<Integer> auxiliary, String sourceName, 
                                 String destName, String auxName) {
        if (n == 1) {
            // Base case: move single disk
            moveDisk(source, destination, sourceName, destName);
        } else {
            // Step 1: Move n-1 disks from source to auxiliary
            solveHanoi(n - 1, source, auxiliary, destination, sourceName, auxName, destName);
            
            // Step 2: Move the largest disk from source to destination
            moveDisk(source, destination, sourceName, destName);
            
            // Step 3: Move n-1 disks from auxiliary to destination
            solveHanoi(n - 1, auxiliary, destination, source, auxName, destName, sourceName);
        }
    }
    
    /**
     * Check if puzzle is solved (all disks on rod C)
     */
    public static boolean isSolved(int totalDisks) {
        if (rodC.getSize() != totalDisks) {
            return false;
        }
        
        // Check if disks are in correct order (largest at bottom)
        ArrayList<Integer> contents = getStackContents(rodC);
        for (int i = 0; i < contents.size() - 1; i++) {
            if (contents.get(i) >= contents.get(i + 1)) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Interactive mode for manual solving
     */
    public static void interactiveMode(int n) {
        Scanner scanner = new Scanner(System.in);
        initializeTower(n);
        
        System.out.println("\nINTERACTIVE MODE - Solve the puzzle manually!");
        System.out.println("Commands: AB (A to B), AC (A to C), BA (B to A), BC (B to C), CA (C to A), CB (C to B)");
        System.out.println("Type 'auto' for automatic solution, 'quit' to exit");
        
        while (!isSolved(n)) {
            System.out.print("\nEnter your move: ");
            String move = scanner.nextLine().trim().toUpperCase();
            
            if (move.equals("QUIT")) {
                break;
            } else if (move.equals("AUTO")) {
                System.out.println("Switching to automatic solution...");
                solveHanoi(rodA.getSize(), rodA, rodC, rodB, "A", "C", "B");
                break;
            }
            
            try {
                switch (move) {
                    case "AB":
                        moveDisk(rodA, rodB, "A", "B");
                        break;
                    case "AC":
                        moveDisk(rodA, rodC, "A", "C");
                        break;
                    case "BA":
                        moveDisk(rodB, rodA, "B", "A");
                        break;
                    case "BC":
                        moveDisk(rodB, rodC, "B", "C");
                        break;
                    case "CA":
                        moveDisk(rodC, rodA, "C", "A");
                        break;
                    case "CB":
                        moveDisk(rodC, rodB, "C", "B");
                        break;
                    default:
                        System.out.println("Invalid move! Use format like 'AB' to move from A to B");
                        continue;
                }
            } catch (Exception e) {
                System.out.println("Invalid move: " + e.getMessage());
            }
        }
        
        if (isSolved(n)) {
            System.out.println("CONGRATULATIONS! You solved the Tower of Hanoi puzzle!");
            System.out.println("Total moves: " + moveCounter);
            System.out.println("Minimum possible moves: " + (int)(Math.pow(2, n) - 1));
        }
        scanner.close();
    }
    
    /**
     * Main method - program entry point
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("TOWER OF HANOI SOLVER USING STACKS");
        System.out.println("==================================================");
        
        // Algorithm explanation
        System.out.println("RULES:");
        System.out.println("1. Only one disk may be moved at a time");
        System.out.println("2. Each move consists of taking the upper disk from one rod");
        System.out.println("3. No disk may be placed on top of a smaller disk");
        System.out.println();
        
        System.out.println("ALGORITHM:");
        System.out.println("To move n disks from A to C using B:");
        System.out.println("1. Move n-1 disks from A to B (using C as auxiliary)");
        System.out.println("2. Move the largest disk from A to C");
        System.out.println("3. Move n-1 disks from B to C (using A as auxiliary)");
        System.out.println();
        
        // Get number of disks
        System.out.print("Enter number of disks (1-8 recommended): ");
        int n = scanner.nextInt();
        scanner.nextLine(); // consume newline
        
        if (n <= 0) {
            System.out.println("Number of disks must be positive!");
            return;
        }
        
        System.out.println("\nChoose solving method:");
        System.out.println("1. Automatic solution (recursive)");
        System.out.println("2. Interactive mode (manual solving)");
        System.out.print("Enter choice (1 or 2): ");
        
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline
        
        if (choice == 1) {
            // Automatic solution
            System.out.println("\nAUTOMATIC SOLUTION");
            System.out.println("==============================");
            
            initializeTower(n);
            System.out.println("Solving Tower of Hanoi with " + n + " disks...");
            System.out.println("Minimum moves required: " + (int)(Math.pow(2, n) - 1));
            System.out.println();
            
            long startTime = System.currentTimeMillis();
            solveHanoi(n, rodA, rodC, rodB, "A", "C", "B");
            long endTime = System.currentTimeMillis();
            
            System.out.println("PUZZLE SOLVED!");
            System.out.println("Total moves: " + moveCounter);
            System.out.println("Time taken: " + (endTime - startTime) + " ms");
            System.out.println("Verification: " + (isSolved(n) ? "CORRECT" : "ERROR"));
            
        } else if (choice == 2) {
            // Interactive mode
            interactiveMode(n);
        } else {
            System.out.println("Invalid choice!");
        }
        
        // Algorithm analysis
        System.out.println("\n============================================================");
        System.out.println("ALGORITHM ANALYSIS");
        System.out.println("============================================================");
        System.out.println("Time Complexity: O(2^n) - exponential");
        System.out.println("Space Complexity: O(n) - recursion depth");
        System.out.println("Minimum Moves: 2^n - 1");
        System.out.println("Stack Operations: push(), pop(), peek(), isEmpty()");
        System.out.println("Problem Type: Classic recursion + stack application");
        
        scanner.close();
    }
}
