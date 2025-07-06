# Linked List Data Structure 
## Table of Contents
1. [Overview](#overview)
2. [Java Collection Framework](#java-collection-framework)
3. [Singly Linked List](#singly-linked-list)
4. [Doubly Linked List](#doubly-linked-list)
5. [Types of Linked Lists](#types-of-linked-lists)
6. [Performance Comparison](#performance-comparison)
7. [Practical Usage Example](#practical-usage-example)

---

## Overview

**Linked List** is a fundamental data structure that stores elements in sequential order using nodes. Unlike arrays, linked lists don't store elements in contiguous memory locations. Instead, each element is stored in a node that contains the data and a reference (pointer) to the next node.

### Key Characteristics:
- **Dynamic size**: Can grow or shrink during runtime
- **Non-contiguous memory**: Elements can be stored anywhere in memory
- **Sequential access**: Must traverse from head to reach a specific element
- **Efficient insertion/deletion**: O(1) at known positions

---

## Java Collection Framework

The Collection Framework provides a unified architecture for storing and manipulating groups of objects.

### List Interface Operations:
- **Retrieve**: Get an element from the list
- **Insert**: Add a new element to the list
- **Delete**: Remove an element from the list
- **Size**: Find the number of elements
- **Search**: Check if an element exists
- **Empty check**: Determine if the list is empty

### Array vs Linked List Implementation:

| Operation | Array | Linked List |
|-----------|-------|-------------|
| Access by index | O(1) | O(n) |
| Insert at end | O(1) | O(1) |
| Insert at middle | O(n) | O(1) at known position |
| Delete from middle | O(n) | O(1) at known position |
| Memory overhead | Low | High (extra pointers) |

---

## Singly Linked List

### Node Structure
```java
class Node<E> {
    E element;        // Data stored in the node
    Node<E> next;     // Reference to the next node
    
    public Node(E element) {
        this.element = element;
        this.next = null;
    }
}
```

### Basic LinkedList Class Structure

The `MyLinkedList` class implements a generic singly linked list with the following key components:

```java
public class MyLinkedList<E> {
    private Node<E> head;  // Reference to the first node
    private Node<E> tail;  // Reference to the last node
    private int size;      // Number of elements in the list
    
    public MyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }
    
    // Inner Node class
    private static class Node<E> {
        E element;      // Data stored in the node
        Node<E> next;   // Reference to the next node
        
        Node(E element) {
            this.element = element;
            this.next = null;
        }
    }
}
```

#### Class Components Explained:

**1. Instance Variables:**
- **`head`**: Points to the first node in the list. If the list is empty, `head` is `null`.
- **`tail`**: Points to the last node in the list. This allows O(1) insertion at the end.
- **`size`**: Keeps track of the number of elements, enabling O(1) size queries.

**2. Constructor:**
- Initializes an empty list by setting all references to `null` and size to 0.
- This represents the initial state where no nodes exist.

**3. Inner Node Class:**
- **`static`**: Can be accessed without an instance of the outer class.
- **`private`**: Encapsulated within the LinkedList class for data hiding.
- **Generic `<E>`**: Can store any type of data.
- **`element`**: Holds the actual data value.
- **`next`**: Reference to the next node in the sequence (null for the last node).

#### Visual Representation:

```
Empty List:
head -> null
tail -> null
size = 0

List with Elements [A, B, C]:
head -> [A|next] -> [B|next] -> [C|null] <- tail
        Node1       Node2       Node3
size = 3
```

#### Key Design Decisions:

**Why use both head and tail?**
- **head**: Enables O(1) insertion/deletion at the beginning
- **tail**: Enables O(1) insertion at the end (without tail, adding to end would be O(n))

**Why track size?**
- Provides O(1) size queries instead of O(n) traversal
- Useful for bounds checking and optimization

**Why inner static class?**
- Encapsulation: Node is only used within LinkedList
- Memory efficiency: Static class doesn't hold reference to outer class
- Type safety: Generic type parameter ensures type consistency

### Core Operations Implementation

#### 1. Add First Element
```java
public void addFirst(E element) {
    Node<E> newNode = new Node<>(element);
    
    if (head == null) {
        // Empty list
        head = tail = newNode;
    } else {
        // Non-empty list
        newNode.next = head;
        head = newNode;
    }
    size++;
}
```

#### 2. Add Last Element
```java
public void addLast(E element) {
    Node<E> newNode = new Node<>(element);
    
    if (tail == null) {
        // Empty list
        head = tail = newNode;
    } else {
        // Non-empty list
        tail.next = newNode;
        tail = newNode;
    }
    size++;
}
```

#### 3. Add at Specific Index
```java
public void add(int index, E element) {
    if (index < 0 || index > size) {
        throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }
    
    if (index == 0) {
        addFirst(element);
    } else if (index == size) {
        addLast(element);
    } else {
        Node<E> newNode = new Node<>(element);
        Node<E> current = head;
        
        // Traverse to the node before the insertion point
        for (int i = 0; i < index - 1; i++) {
            current = current.next;
        }
        
        newNode.next = current.next;
        current.next = newNode;
        size++;
    }
}
```

#### 4. Remove First Element
```java
public E removeFirst() {
    if (head == null) {
        throw new NoSuchElementException("List is empty");
    }
    
    E element = head.element;
    
    if (head == tail) {
        // Only one element
        head = tail = null;
    } else {
        head = head.next;
    }
    
    size--;
    return element;
}
```

#### 5. Remove Last Element
```java
public E removeLast() {
    if (tail == null) {
        throw new NoSuchElementException("List is empty");
    }
    
    E element = tail.element;
    
    if (head == tail) {
        // Only one element
        head = tail = null;
    } else {
        // Find the second-to-last node
        Node<E> current = head;
        while (current.next != tail) {
            current = current.next;
        }
        
        tail = current;
        tail.next = null;
    }
    
    size--;
    return element;
}
```

#### 6. Remove at Specific Index
```java
public E remove(int index) {
    if (index < 0 || index >= size) {
        throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }
    
    if (index == 0) {
        return removeFirst();
    } else if (index == size - 1) {
        return removeLast();
    } else {
        Node<E> current = head;
        
        // Traverse to the node before the one to be removed
        for (int i = 0; i < index - 1; i++) {
            current = current.next;
        }
        
        E element = current.next.element;
        current.next = current.next.next;
        size--;
        return element;
    }
}
```

#### 7. Traversal Methods
```java
public void printList() {
    Node<E> current = head;
    System.out.print("List: ");
    
    while (current != null) {
        System.out.print(current.element + " -> ");
        current = current.next;
    }
    System.out.println("null");
}

public E get(int index) {
    if (index < 0 || index >= size) {
        throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }
    
    Node<E> current = head;
    for (int i = 0; i < index; i++) {
        current = current.next;
    }
    
    return current.element;
}

public boolean contains(E element) {
    Node<E> current = head;
    
    while (current != null) {
        if (current.element.equals(element)) {
            return true;
        }
        current = current.next;
    }
    
    return false;
}
```

---

## Doubly Linked List

### Node Structure
```java
class DoublyNode<E> {
    E element;
    DoublyNode<E> next;
    DoublyNode<E> prev;
    
    public DoublyNode(E element) {
        this.element = element;
        this.next = null;
        this.prev = null;
    }
}
```

### DoublyLinkedList Class
```java
public class DoublyLinkedList<E> {
    private DoublyNode<E> head;
    private DoublyNode<E> tail;
    private int size;
    
    public DoublyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }
    
    private static class DoublyNode<E> {
        E element;
        DoublyNode<E> next;
        DoublyNode<E> prev;
        
        DoublyNode(E element) {
            this.element = element;
            this.next = null;
            this.prev = null;
        }
    }
}
```

### Core Operations Implementation

#### 1. Add First
```java
public void addFirst(E element) {
    DoublyNode<E> newNode = new DoublyNode<>(element);
    
    if (head == null) {
        // Empty list
        head = tail = newNode;
    } else {
        newNode.next = head;
        head.prev = newNode;
        head = newNode;
    }
    size++;
}
```

#### 2. Add Last
```java
public void addLast(E element) {
    DoublyNode<E> newNode = new DoublyNode<>(element);
    
    if (tail == null) {
        // Empty list
        head = tail = newNode;
    } else {
        tail.next = newNode;
        newNode.prev = tail;
        tail = newNode;
    }
    size++;
}
```

#### 3. Add at Index
```java
public void add(int index, E element) {
    if (index < 0 || index > size) {
        throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }
    
    if (index == 0) {
        addFirst(element);
    } else if (index == size) {
        addLast(element);
    } else {
        DoublyNode<E> newNode = new DoublyNode<>(element);
        DoublyNode<E> current = head;
        
        // Traverse to the insertion point
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        
        // Insert the new node
        newNode.next = current;
        newNode.prev = current.prev;
        current.prev.next = newNode;
        current.prev = newNode;
        
        size++;
    }
}
```

#### 4. Remove First
```java
public E removeFirst() {
    if (head == null) {
        throw new NoSuchElementException("List is empty");
    }
    
    E element = head.element;
    
    if (head == tail) {
        // Only one element
        head = tail = null;
    } else {
        head = head.next;
        head.prev = null;
    }
    
    size--;
    return element;
}
```

#### 5. Remove Last
```java
public E removeLast() {
    if (tail == null) {
        throw new NoSuchElementException("List is empty");
    }
    
    E element = tail.element;
    
    if (head == tail) {
        // Only one element
        head = tail = null;
    } else {
        tail = tail.prev;
        tail.next = null;
    }
    
    size--;
    return element;
}
```

#### 6. Remove at Index
```java
public E remove(int index) {
    if (index < 0 || index >= size) {
        throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }
    
    if (index == 0) {
        return removeFirst();
    } else if (index == size - 1) {
        return removeLast();
    } else {
        DoublyNode<E> current = head;
        
        // Traverse to the node to be removed
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        
        E element = current.element;
        
        // Update links
        current.prev.next = current.next;
        current.next.prev = current.prev;
        
        size--;
        return element;
    }
}
```

#### 7. Traversal Methods
```java
public void printForward() {
    DoublyNode<E> current = head;
    System.out.print("Forward: ");
    
    while (current != null) {
        System.out.print(current.element + " <-> ");
        current = current.next;
    }
    System.out.println("null");
}

public void printBackward() {
    DoublyNode<E> current = tail;
    System.out.print("Backward: ");
    
    while (current != null) {
        System.out.print(current.element + " <-> ");
        current = current.prev;
    }
    System.out.println("null");
}
```

#### 8. Clear All Nodes
```java
public void clear() {
    DoublyNode<E> current = head;
    
    while (current != null) {
        DoublyNode<E> next = current.next;
        current.element = null;
        current.next = null;
        current.prev = null;
        current = next;
    }
    
    head = tail = null;
    size = 0;
}
```

---

## Types of Linked Lists

### 1. Singly Linked List
- Each node points to the next node
- Traversal only in forward direction
- Less memory overhead

### 2. Doubly Linked List
- Each node has pointers to both next and previous nodes
- Bidirectional traversal
- More memory overhead but more flexible operations

### 3. Circular Linked List
```java
// In circular linked list, last node points to first node
public class CircularLinkedList<E> {
    private Node<E> tail; // We keep reference to tail, head is tail.next
    
    public void addFirst(E element) {
        Node<E> newNode = new Node<>(element);
        
        if (tail == null) {
            tail = newNode;
            newNode.next = newNode; // Points to itself
        } else {
            newNode.next = tail.next;
            tail.next = newNode;
        }
    }
}
```

### 4. Circular Doubly Linked List
- Combines features of both doubly linked and circular linked lists
- First node's prev points to last node
- Last node's next points to first node

---

## Performance Comparison

### Time Complexity

| Operation | Singly Linked | Doubly Linked | Array |
|-----------|---------------|---------------|-------|
| Access (by index) | O(n) | O(n) | O(1) |
| Search | O(n) | O(n) | O(n) |
| Insert at beginning | O(1) | O(1) | O(n) |
| Insert at end | O(1) | O(1) | O(1) |
| Insert at middle | O(n) | O(n) | O(n) |
| Delete from beginning | O(1) | O(1) | O(n) |
| Delete from end | O(n) | O(1) | O(1) |
| Delete from middle | O(n) | O(n) | O(n) |

### Space Complexity
- **Singly Linked List**: O(n) where each node stores data + 1 pointer
- **Doubly Linked List**: O(n) where each node stores data + 2 pointers
- **Array**: O(n) where each element stores only data

### Advantages and Disadvantages

#### Singly Linked List:
**Advantages:**
- ✅ Simple implementation
- ✅ Less memory per node

**Disadvantages:**
- ❌ No backward traversal
- ❌ O(n) to delete from end

#### Doubly Linked List:
**Advantages:**
- ✅ Bidirectional traversal
- ✅ Efficient deletion from both ends
- ✅ More flexible operations

**Disadvantages:**
- ❌ Extra memory for prev pointer
- ❌ More complex implementation

---

## Practical Usage Example

```java
import java.util.NoSuchElementException;

public class LinkedListDemo {
    public static void main(String[] args) {
        // Using custom singly linked list
        MyLinkedList<String> list = new MyLinkedList<>();
        
        // Adding elements
        list.addFirst("World");
        list.addFirst("Hello");
        list.addLast("!");
        list.add(2, "Java");
        
        // Display list
        list.printList(); // Output: Hello -> World -> Java -> ! -> null
        
        // Removing elements
        String removed = list.removeFirst();
        System.out.println("Removed: " + removed); // Output: Hello
        
        // Using Java's built-in LinkedList
        java.util.LinkedList<Integer> javaList = new java.util.LinkedList<>();
        javaList.addFirst(1);
        javaList.addLast(2);
        javaList.add(1, 10);
        
        System.out.println("Java LinkedList: " + javaList); // [1, 10, 2]
        
        // Demonstrating doubly linked list
        DoublyLinkedList<Integer> doublyList = new DoublyLinkedList<>();
        doublyList.addFirst(5);
        doublyList.addLast(10);
        doublyList.addFirst(1);
        
        doublyList.printForward();  // Forward: 1 <-> 5 <-> 10 <-> null
        doublyList.printBackward(); // Backward: 10 <-> 5 <-> 1 <-> null
    }
}
```

---

## Summary

This comprehensive guide covers all the essential concepts of linked lists:

1. **Basic Structure**: Understanding nodes and pointers
2. **Implementation**: Complete code for both singly and doubly linked lists
3. **Operations**: All CRUD operations with proper error handling
4. **Performance**: Time and space complexity analysis
5. **Variations**: Different types of linked lists and their use cases
6. **Practical Examples**: Real-world usage scenarios

Linked lists are fundamental data structures that provide dynamic memory allocation and efficient insertion/deletion operations, making them essential for many algorithmic solutions and system designs.
