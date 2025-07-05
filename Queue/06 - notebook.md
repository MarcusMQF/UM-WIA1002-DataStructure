# Queue Data Structure in Java - Comprehensive Study Notes

## Table of Contents
1. [Introduction to Queue](#introduction-to-queue)
2. [Queue Characteristics](#queue-characteristics)
3. [Queue Implementation](#queue-implementation)
4. [Priority Queue](#priority-queue)
5. [Java API Classes](#java-api-classes)
6. [Practice Examples](#practice-examples)
7. [Exercise Solutions](#exercise-solutions)

## Introduction to Queue

### What is a Queue?
A queue is a linear data structure that represents a waiting list. It follows the **FIFO (First-In-First-Out)** principle, similar to a waiting line at a grocery store or bank.

### Real-World Examples
- Waiting line at a bank or grocery store
- Print job queue in a printer
- Process scheduling in operating systems
- Breadth-First Search (BFS) algorithms

### Visual Representation
```
Front/Head → [A] [B] [C] [D] ← Rear/Tail
             ↑               ↑
         Remove here     Add here
```

## Queue Characteristics

### Key Properties
1. **FIFO Ordering:** First element added is the first to be removed
2. **Two Access Points:**
   - Front/Head: Where elements are removed
   - Rear/Tail: Where elements are added
3. **Restricted Access:** Can only access the front element

### Basic Operations
- **Enqueue:** Add element to the rear of the queue
- **Dequeue:** Remove element from the front of the queue
- **Front/Peek:** View the front element without removing it
- **isEmpty:** Check if queue is empty
- **Size:** Get number of elements in queue

## Queue Implementation

### Why Use LinkedList?
Since deletions are made at the beginning of the list, it's more efficient to implement a queue using a LinkedList than an ArrayList.

**Time Complexity Comparison:**
- LinkedList: O(1) for both enqueue and dequeue
- ArrayList: O(1) for enqueue, O(n) for dequeue (due to shifting)

### Implementation Approaches

#### 1. Using Composition (Recommended)
```java
import java.util.LinkedList;

public class GenericQueue<E> {
    private LinkedList<E> list = new LinkedList<>();
    
    // Add element to the rear of the queue
    public void enqueue(E e) {
        list.addLast(e);
    }
    
    // Remove and return the front element
    public E dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return list.removeFirst();
    }
    
    // Get the front element without removing it
    public E getElement() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return list.getFirst();
    }
    
    // Check if queue is empty
    public boolean isEmpty() {
        return list.isEmpty();
    }
    
    // Get the size of the queue
    public int size() {
        return list.size();
    }
    
    // String representation
    @Override
    public String toString() {
        return "Queue: " + list.toString();
    }
}
```

#### 2. Using Inheritance
```java
import java.util.LinkedList;

public class GenericQueue<E> extends LinkedList<E> {
    
    public void enqueue(E e) {
        addLast(e);
    }
    
    public E dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return removeFirst();
    }
    
    public E getElement() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return getFirst();
    }
}
```

### Test GenericQueue Class
```java
public class TestGenericQueue {
    public static void main(String[] args) {
        // Create a queue of strings
        GenericQueue<String> queue = new GenericQueue<>();
        
        // Enqueue elements
        queue.enqueue("Alice");
        queue.enqueue("Bob");
        queue.enqueue("Charlie");
        
        System.out.println("Queue: " + queue);
        System.out.println("Size: " + queue.size());
        
        // Dequeue elements
        System.out.println("Dequeued: " + queue.dequeue()); // Alice
        System.out.println("Dequeued: " + queue.dequeue()); // Bob
        
        System.out.println("Front element: " + queue.getElement()); // Charlie
        System.out.println("Queue after operations: " + queue);
    }
}
```

## Priority Queue

### What is a Priority Queue?
A priority queue is a special type of queue where elements are assigned priorities. The element with the highest priority is removed first, regardless of insertion order.

### Key Characteristics
- **Not FIFO:** Elements are removed based on priority, not insertion order
- **Largest-In, First-Out:** Usually higher values have higher priority
- **Comparison-based:** All elements must be comparable

### Real-World Examples
- Emergency room patient prioritization
- Job scheduling in operating systems
- Dijkstra's shortest path algorithm
- Huffman coding

### Visual Representation
```
Priority Queue: [90] [75] [60] [45] [30]
                 ↑
            Highest priority (removed first)
```

## Java API Classes

### PriorityQueue Class
```java
import java.util.PriorityQueue;
import java.util.Queue;
```

### Common Methods
```java
// Creating a PriorityQueue
PriorityQueue<Integer> pq = new PriorityQueue<>();

// Adding elements
pq.offer(30);           // Add element
pq.add(20);             // Alternative to offer

// Removing elements
pq.remove(20);          // Remove specific element (returns boolean)
pq.poll();              // Remove and return head element
pq.clear();             // Remove all elements

// Accessing elements
pq.peek();              // Get head element without removing
pq.contains(30);        // Check if element exists

// Size operations
pq.size();              // Get number of elements
pq.isEmpty();           // Check if empty
```

### PriorityQueue Example 1: Basic Usage
```java
import java.util.PriorityQueue;

public class PriorityQueueExample1 {
    public static void main(String[] args) {
        // Create a priority queue of integers (min-heap by default)
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        // Add elements
        pq.offer(30);
        pq.offer(10);
        pq.offer(50);
        pq.offer(20);
        pq.offer(40);
        
        System.out.println("PriorityQueue: " + pq);
        
        // Remove elements (will be in priority order)
        while (!pq.isEmpty()) {
            System.out.println("Removed: " + pq.poll());
        }
        // Output: 10, 20, 30, 40, 50 (ascending order)
    }
}
```

### PriorityQueue Example 2: Custom Objects
```java
import java.util.PriorityQueue;
import java.util.Comparator;

// Customer class with priority
class Customer implements Comparable<Customer> {
    private String name;
    private int priority;
    
    public Customer(String name, int priority) {
        this.name = name;
        this.priority = priority;
    }
    
    @Override
    public int compareTo(Customer other) {
        // Higher priority number = higher priority
        return Integer.compare(other.priority, this.priority);
    }
    
    @Override
    public String toString() {
        return name + " (Priority: " + priority + ")";
    }
    
    // Getters
    public String getName() { return name; }
    public int getPriority() { return priority; }
}

public class PriorityQueueExample2 {
    public static void main(String[] args) {
        PriorityQueue<Customer> customerQueue = new PriorityQueue<>();
        
        // Add customers with different priorities
        customerQueue.offer(new Customer("Alice", 3));
        customerQueue.offer(new Customer("Bob", 1));
        customerQueue.offer(new Customer("Charlie", 5));
        customerQueue.offer(new Customer("David", 2));
        
        System.out.println("Serving customers in priority order:");
        while (!customerQueue.isEmpty()) {
            Customer customer = customerQueue.poll();
            System.out.println("Serving: " + customer);
        }
        // Output: Charlie (5), Alice (3), David (2), Bob (1)
    }
}
```

### Alternative: Using Comparator
```java
import java.util.PriorityQueue;
import java.util.Comparator;

public class PriorityQueueWithComparator {
    public static void main(String[] args) {
        // Create max-heap using custom comparator
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(
            Comparator.reverseOrder()
        );
        
        maxHeap.offer(30);
        maxHeap.offer(10);
        maxHeap.offer(50);
        maxHeap.offer(20);
        
        while (!maxHeap.isEmpty()) {
            System.out.println("Removed: " + maxHeap.poll());
        }
        // Output: 50, 30, 20, 10 (descending order)
    }
}
```

## Practice Examples

### Example 1: Queue with LinkedList (Java Collections)
```java
import java.util.LinkedList;
import java.util.Queue;

public class QueueExample {
    public static void main(String[] args) {
        Queue<String> queue = new LinkedList<>();
        
        // Add elements
        queue.offer("First");
        queue.offer("Second");
        queue.offer("Third");
        
        System.out.println("Queue: " + queue);
        
        // Process elements
        while (!queue.isEmpty()) {
            String element = queue.poll();
            System.out.println("Processing: " + element);
        }
    }
}
```

### Example 2: Breadth-First Search Using Queue
```java
import java.util.*;

public class BFSExample {
    static class Graph {
        private Map<Integer, List<Integer>> adjacencyList;
        
        public Graph() {
            adjacencyList = new HashMap<>();
        }
        
        public void addEdge(int from, int to) {
            adjacencyList.computeIfAbsent(from, k -> new ArrayList<>()).add(to);
            adjacencyList.computeIfAbsent(to, k -> new ArrayList<>()).add(from);
        }
        
        public void bfs(int start) {
            Set<Integer> visited = new HashSet<>();
            Queue<Integer> queue = new LinkedList<>();
            
            queue.offer(start);
            visited.add(start);
            
            while (!queue.isEmpty()) {
                int current = queue.poll();
                System.out.print(current + " ");
                
                for (int neighbor : adjacencyList.getOrDefault(current, new ArrayList<>())) {
                    if (!visited.contains(neighbor)) {
                        visited.add(neighbor);
                        queue.offer(neighbor);
                    }
                }
            }
        }
    }
    
    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 4);
        
        System.out.println("BFS traversal starting from vertex 0:");
        graph.bfs(0);
    }
}
```

## Exercise Solutions

### ArrayQueue Implementation
```java
public class ArrayQueue<E> {
    private static final int DEFAULT_CAPACITY = 10;
    private E[] queue;
    private int front;
    private int rear;
    private int size;
    
    @SuppressWarnings("unchecked")
    public ArrayQueue() {
        this(DEFAULT_CAPACITY);
    }
    
    @SuppressWarnings("unchecked")
    public ArrayQueue(int initial) {
        queue = (E[]) new Object[initial];
        front = 0;
        rear = -1;
        size = 0;
    }
    
    public void enqueue(E e) {
        if (size == queue.length) {
            resize();
        }
        rear = (rear + 1) % queue.length;
        queue[rear] = e;
        size++;
    }
    
    public E dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        E element = queue[front];
        queue[front] = null; // Help GC
        front = (front + 1) % queue.length;
        size--;
        return element;
    }
    
    public E getElement() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return queue[front];
    }
    
    public boolean isEmpty() {
        return size == 0;
    }
    
    public int size() {
        return size;
    }
    
    @SuppressWarnings("unchecked")
    private void resize() {
        E[] newQueue = (E[]) new Object[queue.length * 2];
        for (int i = 0; i < size; i++) {
            newQueue[i] = queue[(front + i) % queue.length];
        }
        queue = newQueue;
        front = 0;
        rear = size - 1;
    }
    
    @Override
    public String toString() {
        if (isEmpty()) return "[]";
        
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            sb.append(queue[(front + i) % queue.length]);
            if (i < size - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}
```

### ArrayList-based Queue Implementation
```java
import java.util.ArrayList;

public class ArrayListQueue<E> {
    private ArrayList<E> queue;
    
    public ArrayListQueue() {
        queue = new ArrayList<>();
    }
    
    public ArrayListQueue(int initial) {
        queue = new ArrayList<>(initial);
    }
    
    public void enqueue(E e) {
        queue.add(e);
    }
    
    public E dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return queue.remove(0); // O(n) operation
    }
    
    public E getElement() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return queue.get(0);
    }
    
    public boolean isEmpty() {
        return queue.isEmpty();
    }
    
    public int size() {
        return queue.size();
    }
    
    public void resize() {
        queue.trimToSize();
    }
    
    @Override
    public String toString() {
        return queue.toString();
    }
}
```

### Test ArrayQueue
```java
public class TestArrayQueue {
    public static void main(String[] args) {
        ArrayQueue<Integer> queue = new ArrayQueue<>(5);
        
        // Test enqueue
        for (int i = 1; i <= 7; i++) {
            queue.enqueue(i);
            System.out.println("Enqueued " + i + ": " + queue);
        }
        
        // Test dequeue
        while (!queue.isEmpty()) {
            int element = queue.dequeue();
            System.out.println("Dequeued " + element + ": " + queue);
        }
        
        // Test exception
        try {
            queue.dequeue();
        } catch (IllegalStateException e) {
            System.out.println("Exception caught: " + e.getMessage());
        }
    }
}
```

## Key Concepts Summary

### Queue vs Stack
| Queue (FIFO) | Stack (LIFO) |
|--------------|--------------|
| First In, First Out | Last In, First Out |
| Add at rear, remove from front | Add and remove from top |
| Breadth-First Search | Depth-First Search |
| Process scheduling | Function call management |

### Implementation Comparison
| Implementation | Enqueue | Dequeue | Space |
|----------------|---------|---------|-------|
| LinkedList | O(1) | O(1) | O(n) |
| Array (Circular) | O(1) | O(1) | O(n) |
| ArrayList | O(1) | O(n) | O(n) |

### Priority Queue Notes
- **Default:** Min-heap (smallest element first)
- **Custom:** Use Comparator for different ordering
- **Time Complexity:** O(log n) for offer/poll, O(1) for peek
- **Use Cases:** Task scheduling, graph algorithms, event simulation

### Best Practices
- Use LinkedList for general queue implementation
- Use ArrayDeque for better performance than LinkedList
- Use PriorityQueue when elements need priority ordering
- Handle empty queue exceptions properly
- Consider memory usage for large queues
