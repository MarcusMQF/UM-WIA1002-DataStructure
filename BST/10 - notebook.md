# Binary Search Tree (BST) - Comprehensive Study Notes

## Table of Contents
1. [Introduction to Trees](#introduction-to-trees)
2. [Binary Trees](#binary-trees)
3. [Binary Search Trees](#binary-search-trees)
4. [Tree Representation](#tree-representation)
5. [BST Operations](#bst-operations)
6. [Tree Traversal](#tree-traversal)
7. [Implementation](#implementation)
8. [Deletion Operations](#deletion-operations)
9. [Complexity Analysis](#complexity-analysis)

---

## Introduction to Trees

### Tree Definition
A tree is a hierarchical data structure that consists of nodes connected by edges. It's a non-linear data structure that resembles a hierarchical tree structure with a root value and subtrees of children.

### Key Terminology
- **Root**: The top node of the tree (level 0)
- **Path Length**: Number of edges in a path
- **Depth**: Length of path from root to a node
- **Level**: Set of all nodes at a given depth
- **Siblings**: Nodes that share the same parent
- **Left Child**: Root of the left subtree of a node
- **Leaf**: Node without children
- **Height**: Length of path from root node to its furthest leaf

### Types of Trees
- **General Tree**: Each node can have arbitrary number of children
- **n-ary Tree**: Each node has at most n children
- **Binary Tree**: Each node has at most 2 children

---

## Binary Trees

### Definition
A binary tree is a hierarchical structure that is either:
1. Empty, OR
2. Consists of a root element and two distinct binary trees (left and right subtrees)

### Properties
- Each node has zero, one, or two subtrees
- Maximum of 2 children per node
- Children are ordered (left child, right child)

### Visual Example
```
      60
     /  \
   55    100
  /  \   /  \
 45   57 67  107
```

---

## Binary Search Trees

### Definition
A Binary Search Tree (BST) is a special type of binary tree with the following property:

For every node in the tree:
- All values in the left subtree are less than the node's value
- All values in the right subtree are greater than the node's value
- No duplicate elements allowed

### BST Property Example
```
      60
     /  \
   55    100    ✓ Valid BST
  /  \   /  \
 45   57 67  107
```

---

## Tree Representation

### TreeNode Class Structure
```java
class TreeNode<E> {
    E element;
    TreeNode<E> left;
    TreeNode<E> right;
    
    public TreeNode(E o) {
        element = o;
    }
}
```

### Creating Nodes
```java
// Create root node
TreeNode<Integer> root = new TreeNode<Integer>(new Integer(60));

// Create left child
root.left = new TreeNode<Integer>(new Integer(55));

// Create right child
root.right = new TreeNode<Integer>(new Integer(100));
```

---

## BST Operations

### 1. Search Operation
**Algorithm**: Start from root, compare target with current node, go left if smaller, right if larger.

```java
public boolean search(E element) {
    TreeNode<E> current = root; // Start from the root
    
    while (current != null) {
        if (element < current.element) {
            current = current.left; // Go left
        }
        else if (element > current.element) {
            current = current.right; // Go right
        }
        else {
            return true; // Element found
        }
    }
    return false; // Element not found
}
```
**Time Complexity**: O(log n) average, O(n) worst case

### 2. Insert Operation
**Algorithm**:
1. If tree is empty, create root
2. Find the parent node for new element
3. Insert as left child if smaller, right child if larger

```java
public boolean insert(E element) {
    if (root == null) {
        root = new TreeNode(element);
        return true;
    }
    
    TreeNode<E> parent = null;
    TreeNode<E> current = root;
    
    // Locate the parent node
    while (current != null) {
        if (element < current.element) {
            parent = current;
            current = current.left;
        }
        else if (element > current.element) {
            parent = current;
            current = current.right;
        }
        else {
            return false; // Duplicate not inserted
        }
    }
    
    // Create new node and attach to parent
    if (element < parent.element) {
        parent.left = new TreeNode(element);
    } else {
        parent.right = new TreeNode(element);
    }
    
    return true;
}
```

### Insert Example: Inserting 101 into the tree
```
Initial:     After Insert:
    60           60
   /  \         /  \
  55  100      55  100
 /  \  / \    /  \  / \
45  57 67 107 45  57 67 107
                        /
                      101
```

---

## Tree Traversal

### 1. Inorder Traversal (Left → Root → Right)
```java
public void inorder(TreeNode<E> root) {
    if (root != null) {
        inorder(root.left);    // Visit left subtree
        System.out.print(root.element + " "); // Visit root
        inorder(root.right);   // Visit right subtree
    }
}
```
**Output**: 45 55 57 59 60 67 100 101 107 (sorted order for BST)

### 2. Preorder Traversal (Root → Left → Right)
```java
public void preorder(TreeNode<E> root) {
    if (root != null) {
        System.out.print(root.element + " "); // Visit root
        preorder(root.left);   // Visit left subtree
        preorder(root.right);  // Visit right subtree
    }
}
```
**Output**: 60 55 45 57 59 100 67 107 101

### 3. Postorder Traversal (Left → Right → Root)
```java
public void postorder(TreeNode<E> root) {
    if (root != null) {
        postorder(root.left);  // Visit left subtree
        postorder(root.right); // Visit right subtree
        System.out.print(root.element + " "); // Visit root
    }
}
```
**Output**: 45 59 57 55 67 101 107 100 60

### 4. Breadth-First (Level-Order) Traversal
```java
public void breadthFirst() {
    if (root == null) return;
    
    Queue<TreeNode<E>> queue = new LinkedList<>();
    queue.offer(root);
    
    while (!queue.isEmpty()) {
        TreeNode<E> current = queue.poll();
        System.out.print(current.element + " ");
        
        if (current.left != null) queue.offer(current.left);
        if (current.right != null) queue.offer(current.right);
    }
}
```
**Output**: 60 55 100 45 57 67 107 59 101

### Memory Aid for Traversals
- **Inorder**: 1 + 2 (left, root, right)
- **Preorder**: + 1 2 (root, left, right)  
- **Postorder**: 1 2 + (left, right, root)

---

## Implementation

### Tree Interface
```java
interface Tree<E> {
    boolean search(E e);
    boolean insert(E e);
    boolean delete(E e);
    void inorder();
    void preorder();
    void postorder();
    int getSize();
    boolean isEmpty();
    void clear();
}
```

### BST Class Structure
```java
public class BST<E extends Comparable<E>> extends AbstractTree<E> {
    protected TreeNode<E> root;
    protected int size;
    
    public BST() {
        // Default constructor
    }
    
    public BST(E[] objects) {
        for (E object : objects) {
            insert(object);
        }
    }
    
    // Implementation of Tree interface methods
}
```

---

## Deletion Operations

### Case 1: Node with No Left Child
**Algorithm**: Connect parent directly to the right child of the node to be deleted.

```java
// Simplified deletion for case 1
if (current.left == null) {
    if (parent == null) {
        root = current.right;
    } else if (isLeftChild) {
        parent.left = current.right;
    } else {
        parent.right = current.right;
    }
}
```

**Example**: Deleting node 10
```
Before:      After:
   20           20
  /  \         /  \
 10   40      16   40
  \   / \          / \
  16 30  80       30  80
```

### Case 2: Node with Left Child
**Algorithm**:
1. Find the rightmost node in the left subtree (largest element smaller than current)
2. Replace current node's value with rightmost node's value
3. Delete the rightmost node

```java
// Find rightmost node in left subtree
TreeNode<E> rightMost = current.left;
TreeNode<E> parentOfRightMost = current;

while (rightMost.right != null) {
    parentOfRightMost = rightMost;
    rightMost = rightMost.right;
}

// Replace current's element with rightMost's element
current.element = rightMost.element;

// Delete rightMost node
if (parentOfRightMost.right == rightMost) {
    parentOfRightMost.right = rightMost.left;
} else {
    parentOfRightMost.left = rightMost.left;
}
```

**Example**: Deleting node 20
```
Before:         After:
   20             16
  /  \           /  \
 10   40        10   40
/  \  / \      /    / \
5  16 30 80   5    30  80
   /             
  14
```

---

## Key Advantages of BST
- **Efficient Search**: O(log n) average time complexity
- **Ordered Data**: Inorder traversal gives sorted sequence
- **Dynamic**: Can grow and shrink during runtime
- **No Memory Waste**: Only allocates memory for existing elements

## Applications
- **Database Indexing**: B-trees (variant of BST)
- **Expression Parsing**: Syntax trees
- **File Systems**: Directory structures
- **Compression**: Huffman coding trees
- **Game Development**: Decision trees

---

## Complexity Analysis

| Operation | Average Case | Worst Case |
|-----------|--------------|------------|
| Search    | O(log n)     | O(n)       |
| Insert    | O(log n)     | O(n)       |
| Delete    | O(log n)     | O(n)       |
| Traversal | O(n)         | O(n)       |

**Note**: Worst case occurs when tree becomes skewed (essentially a linked list).

---

This comprehensive guide covers all the essential concepts of Binary Search Trees as presented in the course materials. Practice implementing these operations and understanding the traversal patterns to master BST operations in Java.
