public class LinkedListStack<E> {
    
    // Private Node class for the linked list implementation
    private class Node {
        E data;
        Node next;
        
        Node(E data) {
            this.data = data;
            this.next = null;
        }
    }
    
    // Instance variables
    private Node head;  // Points to the top of the stack (head of linked list)
    private int size;   // Counter for efficiency
    
    // Constructor
    public LinkedListStack() {
        head = null;
        size = 0;
    }
    
    /**
     * Adds an element to the top of the stack
     * @param element the element to be pushed onto the stack
     */
    public void push(E element) {
        Node newNode = new Node(element);
        newNode.next = head;  // Point new node to current head
        head = newNode;       // Make new node the head
        size++;
    }
    
    /**
     * Removes and returns the top element from the stack
     * @return the top element of the stack
     * @throws RuntimeException if the stack is empty
     */
    public E pop() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }
        
        E data = head.data;   // Store the data to return
        head = head.next;     // Move head to next node
        size--;
        return data;
    }
    
    /**
     * Returns the top element without removing it
     * @return the top element of the stack
     * @throws RuntimeException if the stack is empty
     */
    public E peek() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }
        return head.data;
    }
    
    /**
     * Checks if the stack is empty
     * @return true if the stack is empty, false otherwise
     */
    public boolean isEmpty() {
        return head == null;
    }
    
    /**
     * Returns the number of elements in the stack
     * @return the size of the stack
     */
    public int getSize() {
        return size;
    }
    
    /**
     * Returns a string representation of the stack for debugging
     * @return string representation of the stack
     */
    @Override
    public String toString() {
        if (isEmpty()) {
            return "stack: []";
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append("stack: [");
        
        Node current = head;
        while (current != null) {
            sb.append(current.data);
            if (current.next != null) {
                sb.append(", ");
            }
            current = current.next;
        }
        
        sb.append("]");
        return sb.toString();
    }
}