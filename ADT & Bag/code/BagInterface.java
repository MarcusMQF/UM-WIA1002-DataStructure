/**
 * An interface that describes the operations of a bag of objects.
 * A bag is an unordered collection that allows duplicates.
 * 
 * @param <T> the type of elements in this bag
 * @author Data Structure Course
 * @version 1.0
 */
public interface BagInterface<T> {
    
    /**
     * Gets the current number of entries in this bag.
     * @return The integer number of entries currently in the bag.
     */
    public int getCurrentSize();
    
    /**
     * Checks whether this bag is empty.
     * @return True if the bag is empty, false otherwise.
     */
    public boolean isEmpty();
    
    /**
     * Checks whether this bag is full.
     * @return True if the bag is full, false otherwise.
     */
    public boolean isFull();
    
    /**
     * Adds a new entry to this bag.
     * @param newEntry The object to be added as a new entry.
     * @return True if the addition is successful, false otherwise.
     */
    public boolean add(T newEntry);
    
    /**
     * Removes one unspecified entry from this bag, if possible.
     * @return Either the removed entry, if the removal was successful, 
     *         or null if the bag is empty.
     */
    public T remove();
    
    /**
     * Removes one occurrence of a given entry from this bag, if possible.
     * @param anEntry The entry to be removed.
     * @return True if the removal was successful, false otherwise.
     */
    public boolean remove(T anEntry);
    
    /**
     * Removes all entries from this bag.
     */
    public void clear();
    
    /**
     * Counts the number of times a given entry appears in this bag.
     * @param anEntry The entry to be counted.
     * @return The number of times anEntry appears in the bag.
     */
    public int getFrequencyOf(T anEntry);
    
    /**
     * Tests whether this bag contains a given entry.
     * @param anEntry The entry to find.
     * @return True if the bag contains anEntry, false otherwise.
     */
    public boolean contains(T anEntry);
    
    /**
     * Retrieves all entries that are in this bag.
     * @return A newly allocated array of all the entries in the bag.
     *         Note: If the bag is empty, the returned array is empty.
     */
    public T[] toArray();
    
    /**
     * Creates a new bag that combines the contents of this bag and anotherBag.
     * @param anotherBag The bag that is to be added.
     * @return A combined bag containing all entries from both bags.
     */
    public BagInterface<T> union(BagInterface<T> anotherBag);
    
    /**
     * Creates a new bag that contains those objects that occur in both 
     * this bag and anotherBag.
     * @param anotherBag The bag that is to be compared.
     * @return A bag containing the intersection of both bags.
     */
    public BagInterface<T> intersection(BagInterface<T> anotherBag);
    
    /**
     * Creates a new bag of objects that would be left in this bag after 
     * removing those that also occur in anotherBag.
     * @param anotherBag The bag whose elements are to be removed.
     * @return A bag containing the difference of the two bags.
     */
    public BagInterface<T> difference(BagInterface<T> anotherBag);
    
    /**
     * Checks if this bag equals another bag (same elements with same frequencies).
     * @param anotherBag The bag to compare with.
     * @return True if both bags contain the same elements with same frequencies.
     */
    public boolean equals(BagInterface<T> anotherBag);
    
    /**
     * Returns the maximum capacity of this bag.
     * @return The maximum number of elements this bag can hold, 
     *         or -1 if unlimited.
     */
    public int getCapacity();
} 