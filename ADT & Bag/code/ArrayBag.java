public final class ArrayBag<T> implements BagInterface<T> {
    private final T[] bag;
    private int numberOfEntries;
    private boolean initialized = false;
    private static final int DEFAULT_CAPACITY = 25;
    private static final int MAX_CAPACITY = 10000;

    /**
     * Creates an empty bag whose initial capacity is 25.
     */
    public ArrayBag() {
        this(DEFAULT_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public ArrayBag(int desiredCapacity) {
        if (desiredCapacity <= MAX_CAPACITY) {
            // The cast is safe because the new array contains null entries
            T[] tempBag = (T[]) new Object[desiredCapacity];
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
        return numberOfEntries >= bag.length;
    }

    @Override
    public boolean add(T newEntry) {
        checkInitialization();
        boolean result = true;
        if (isFull()) {
            result = false;
        } else {
            bag[numberOfEntries] = newEntry;
            numberOfEntries++;
        }
        return result;
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
        BagInterface<T> result = new ArrayBag<>(this.getCurrentSize() + anotherBag.getCurrentSize());
        
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
        BagInterface<T> result = new ArrayBag<>();
        BagInterface<T> copyOfAnotherBag = new ArrayBag<>();
        
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
        BagInterface<T> result = new ArrayBag<>();
        BagInterface<T> copyOfAnotherBag = new ArrayBag<>();
        
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

    public int getCapacity() {
        return bag.length;
    }

    // Private helper methods
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
            throw new SecurityException("ArrayBag object is not initialized properly.");
        }
    }
} 