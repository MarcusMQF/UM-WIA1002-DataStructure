/**
 * A class that implements a bag of objects by using a resizable array.
 * When the array becomes full, it automatically doubles its capacity.
 * The bag is not ordered and allows duplicate entries.
 * 
 * @param <T> the type of elements in this bag
 * @author Data Structure Course
 * @version 1.0
 */
public final class ResizableArrayBag<T> implements BagInterface<T> {
    private T[] bag;
    private int numberOfEntries;
    private boolean initialized = false;
    private static final int DEFAULT_CAPACITY = 25;
    private static final int MAX_CAPACITY = 10000;

    /**
     * Creates an empty bag whose initial capacity is 25.
     */
    public ResizableArrayBag() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Creates an empty bag having a given initial capacity.
     * @param initialCapacity The integer capacity desired.
     */
    @SuppressWarnings("unchecked")
    public ResizableArrayBag(int initialCapacity) {
        if (initialCapacity <= MAX_CAPACITY) {
            // The cast is safe because the new array contains null entries
            T[] tempBag = (T[]) new Object[initialCapacity];
            bag = tempBag;
            numberOfEntries = 0;
            initialized = true;
        } else {
            throw new IllegalStateException("Attempt to create a bag " +
                    "whose capacity exceeds allowed maximum.");
        }
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
        return false; // ResizableArrayBag is never full (until max capacity)
    }

    @Override
    public boolean add(T newEntry) {
        checkInitialization();
        
        if (isArrayFull()) {
            if (!doubleCapacity()) {
                return false; // Could not resize due to capacity limit
            }
        }
        
        bag[numberOfEntries] = newEntry;
        numberOfEntries++;
        return true;
    }

    @Override
    public T remove() {
        checkInitialization();
        T result = removeEntry(numberOfEntries - 1);
        return result;
    }

    @Override
    public boolean remove(T anEntry) {
        checkInitialization();
        int index = getIndexOf(anEntry);
        T result = removeEntry(index);
        return anEntry.equals(result);
    }

    @Override
    public void clear() {
        while (!isEmpty()) {
            remove();
        }
    }

    @Override
    public int getFrequencyOf(T anEntry) {
        checkInitialization();
        int counter = 0;
        for (int index = 0; index < numberOfEntries; index++) {
            if (anEntry.equals(bag[index])) {
                counter++;
            }
        }
        return counter;
    }

    @Override
    public boolean contains(T anEntry) {
        checkInitialization();
        return getIndexOf(anEntry) > -1;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T[] toArray() {
        checkInitialization();
        T[] result = (T[]) new Object[numberOfEntries];
        for (int index = 0; index < numberOfEntries; index++) {
            result[index] = bag[index];
        }
        return result;
    }

    @Override
    public BagInterface<T> union(BagInterface<T> anotherBag) {
        checkInitialization();
        BagInterface<T> result = new ResizableArrayBag<>(this.getCurrentSize() + anotherBag.getCurrentSize());
        
        // Add all items from this bag
        for (int index = 0; index < numberOfEntries; index++) {
            result.add(bag[index]);
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
        checkInitialization();
        BagInterface<T> result = new ResizableArrayBag<>();
        BagInterface<T> copyOfAnotherBag = new ResizableArrayBag<>();
        
        // Create a copy of anotherBag to avoid modifying original
        T[] otherBagArray = anotherBag.toArray();
        for (T item : otherBagArray) {
            copyOfAnotherBag.add(item);
        }
        
        // Check each item in this bag
        for (int index = 0; index < numberOfEntries; index++) {
            T currentItem = bag[index];
            if (copyOfAnotherBag.contains(currentItem)) {
                result.add(currentItem);
                copyOfAnotherBag.remove(currentItem);
            }
        }
        
        return result;
    }

    @Override
    public BagInterface<T> difference(BagInterface<T> anotherBag) {
        checkInitialization();
        BagInterface<T> result = new ResizableArrayBag<>();
        BagInterface<T> copyOfAnotherBag = new ResizableArrayBag<>();
        
        // Create a copy of anotherBag to avoid modifying original
        T[] otherBagArray = anotherBag.toArray();
        for (T item : otherBagArray) {
            copyOfAnotherBag.add(item);
        }
        
        // Check each item in this bag
        for (int index = 0; index < numberOfEntries; index++) {
            T currentItem = bag[index];
            if (copyOfAnotherBag.contains(currentItem)) {
                copyOfAnotherBag.remove(currentItem);
            } else {
                result.add(currentItem);
            }
        }
        
        return result;
    }

    @Override
    public boolean equals(BagInterface<T> anotherBag) {
        checkInitialization();
        boolean result = false;
        
        if (this.getCurrentSize() == anotherBag.getCurrentSize()) {
            result = true;
            T[] thisArray = this.toArray();
            
            for (T item : thisArray) {
                if (this.getFrequencyOf(item) != anotherBag.getFrequencyOf(item)) {
                    result = false;
                    break;
                }
            }
        }
        
        return result;
    }

    @Override
    public int getCapacity() {
        return bag.length;
    }

    // Private helper methods
    
    /**
     * Returns true if the array bag is full, or false if not.
     */
    private boolean isArrayFull() {
        return numberOfEntries >= bag.length;
    }

    /**
     * Doubles the size of the array bag.
     * @return true if resize successful, false if would exceed max capacity
     */
    @SuppressWarnings("unchecked")
    private boolean doubleCapacity() {
        boolean success = false;
        int oldCapacity = bag.length;
        int newCapacity = 2 * oldCapacity;
        
        if (newCapacity <= MAX_CAPACITY) {
            T[] oldBag = bag;
            T[] tempBag = (T[]) new Object[newCapacity];
            bag = tempBag;
            
            // Copy old contents to new array
            for (int index = 0; index < oldCapacity; index++) {
                bag[index] = oldBag[index];
            }
            
            success = true;
        }
        
        return success;
    }

    /**
     * Locates a given entry within the array bag.
     * @param anEntry The entry to locate.
     * @return The index of the entry, if located, or -1 otherwise.
     */
    private int getIndexOf(T anEntry) {
        int where = -1;
        boolean found = false;
        int index = 0;

        while (!found && (index < numberOfEntries)) {
            if (anEntry.equals(bag[index])) {
                found = true;
                where = index;
            }
            index++;
        }

        return where;
    }

    /**
     * Removes and returns the entry at a given index within the array.
     * @param givenIndex The index of the entry to remove.
     * @return The removed entry if successful, null otherwise.
     */
    private T removeEntry(int givenIndex) {
        T result = null;

        if (!isEmpty() && (givenIndex >= 0)) {
            result = bag[givenIndex];
            int lastIndex = numberOfEntries - 1;
            bag[givenIndex] = bag[lastIndex];
            bag[lastIndex] = null;
            numberOfEntries--;
        }

        return result;
    }

    /**
     * Throws an exception if this object is not initialized.
     */
    private void checkInitialization() {
        if (!initialized) {
            throw new SecurityException("ResizableArrayBag object is not initialized properly.");
        }
    }

    /**
     * Gets the current capacity of the internal array.
     * @return The current capacity.
     */
    public int getCurrentCapacity() {
        return bag.length;
    }

    /**
     * Provides information about resizing history for debugging.
     * @return A string describing the current state.
     */
    public String getCapacityInfo() {
        return String.format("Current size: %d, Current capacity: %d, Max capacity: %d", 
                           numberOfEntries, bag.length, MAX_CAPACITY);
    }
} 