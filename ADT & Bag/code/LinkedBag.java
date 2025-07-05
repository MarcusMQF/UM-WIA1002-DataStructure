/**
 * A class that implements a bag of objects by using a chain of linked nodes.
 * The bag is not ordered and allows duplicate entries.
 * 
 * @param <T> the type of elements in this bag
 * @author Data Structure Course
 * @version 1.0
 */
public final class LinkedBag<T> implements BagInterface<T> {
    private Node firstNode;
    private int numberOfEntries;

    /**
     * Inner class for nodes in the linked chain.
     */
    private class Node {
        private T data;
        private Node next;

        private Node(T dataPortion) {
            this(dataPortion, null);
        }

        private Node(T dataPortion, Node nextNode) {
            data = dataPortion;
            next = nextNode;
        }
    }

    /**
     * Creates an empty bag.
     */
    public LinkedBag() {
        firstNode = null;
        numberOfEntries = 0;
    }

    @Override
    public int getCurrentSize() {
        return numberOfEntries;
    }

    @Override
    public boolean isEmpty() {
        return numberOfEntries == 0;
    }

    @Override
    public boolean isFull() {
        return false; // LinkedBag is never full (until memory runs out)
    }

    @Override
    public boolean add(T newEntry) {
        // Add to beginning of chain
        Node newNode = new Node(newEntry);
        newNode.next = firstNode;
        firstNode = newNode;
        numberOfEntries++;
        return true;
    }

    @Override
    public T remove() {
        if (isEmpty()) {
            return null;
        }

        T result = firstNode.data;
        firstNode = firstNode.next;
        numberOfEntries--;
        return result;
    }

    @Override
    public boolean remove(T anEntry) {
        Node nodeToRemove = getReferenceTo(anEntry);
        if (nodeToRemove != null) {
            // Replace data in node to remove with data from first node
            nodeToRemove.data = firstNode.data;
            firstNode = firstNode.next;
            numberOfEntries--;
            return true;
        }
        return false;
    }

    @Override
    public void clear() {
        while (!isEmpty()) {
            remove();
        }
    }

    @Override
    public int getFrequencyOf(T anEntry) {
        int frequency = 0;
        Node currentNode = firstNode;
        while (currentNode != null) {
            if (anEntry.equals(currentNode.data)) {
                frequency++;
            }
            currentNode = currentNode.next;
        }
        return frequency;
    }

    @Override
    public boolean contains(T anEntry) {
        return getReferenceTo(anEntry) != null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T[] toArray() {
        T[] result = (T[]) new Object[numberOfEntries];
        int index = 0;
        Node currentNode = firstNode;
        while (currentNode != null) {
            result[index] = currentNode.data;
            index++;
            currentNode = currentNode.next;
        }
        return result;
    }

    @Override
    public BagInterface<T> union(BagInterface<T> anotherBag) {
        BagInterface<T> result = new LinkedBag<>();
        
        // Add all items from this bag
        Node currentNode = firstNode;
        while (currentNode != null) {
            result.add(currentNode.data);
            currentNode = currentNode.next;
        }
        
        // Add all items from another bag
        T[] otherBagArray = anotherBag.toArray();
        for (T item : otherBagArray) {
            result.add(item);
        }
        
        return result;
    }

    @Override
    public BagInterface<T> intersection(BagInterface<T> anotherBag) {
        BagInterface<T> result = new LinkedBag<>();
        BagInterface<T> copyOfAnotherBag = new LinkedBag<>();
        
        // Create a copy of anotherBag to avoid modifying original
        T[] otherBagArray = anotherBag.toArray();
        for (T item : otherBagArray) {
            copyOfAnotherBag.add(item);
        }
        
        // Check each item in this bag
        Node currentNode = firstNode;
        while (currentNode != null) {
            T currentItem = currentNode.data;
            if (copyOfAnotherBag.contains(currentItem)) {
                result.add(currentItem);
                copyOfAnotherBag.remove(currentItem);
            }
            currentNode = currentNode.next;
        }
        
        return result;
    }

    @Override
    public BagInterface<T> difference(BagInterface<T> anotherBag) {
        BagInterface<T> result = new LinkedBag<>();
        BagInterface<T> copyOfAnotherBag = new LinkedBag<>();
        
        // Create a copy of anotherBag to avoid modifying original
        T[] otherBagArray = anotherBag.toArray();
        for (T item : otherBagArray) {
            copyOfAnotherBag.add(item);
        }
        
        // Check each item in this bag
        Node currentNode = firstNode;
        while (currentNode != null) {
            T currentItem = currentNode.data;
            if (copyOfAnotherBag.contains(currentItem)) {
                copyOfAnotherBag.remove(currentItem);
            } else {
                result.add(currentItem);
            }
            currentNode = currentNode.next;
        }
        
        return result;
    }

    @Override
    public boolean equals(BagInterface<T> anotherBag) {
        boolean result = false;
        
        if (this.getCurrentSize() == anotherBag.getCurrentSize()) {
            result = true;
            Node currentNode = firstNode;
            
            while (currentNode != null && result) {
                T currentItem = currentNode.data;
                if (this.getFrequencyOf(currentItem) != anotherBag.getFrequencyOf(currentItem)) {
                    result = false;
                }
                currentNode = currentNode.next;
            }
        }
        
        return result;
    }

    @Override
    public int getCapacity() {
        return -1; // Unlimited capacity (limited by available memory)
    }

    // Private helper methods
    
    /**
     * Locates a given entry within the chain of nodes.
     * @param anEntry The entry to locate.
     * @return A reference to the node containing the entry, or null if not found.
     */
    private Node getReferenceTo(T anEntry) {
        Node currentNode = firstNode;
        while (currentNode != null) {
            if (anEntry.equals(currentNode.data)) {
                return currentNode;
            }
            currentNode = currentNode.next;
        }
        return null;
    }
} 