/**
 * A utility class that provides additional operations for bags.
 * This class demonstrates advanced bag manipulations and algorithms.
 * 
 * @author Data Structure Course
 * @version 1.0
 */
public class BagOperations {
    
    /**
     * Creates a union of two bags without modifying the original bags.
     * @param <T> the type of elements in the bags
     * @param bag1 the first bag
     * @param bag2 the second bag
     * @return a new bag containing all elements from both bags
     */
    public static <T> BagInterface<T> union(BagInterface<T> bag1, BagInterface<T> bag2) {
        BagInterface<T> result = new LinkedBag<>();
        
        // Add all items from first bag
        T[] bag1Array = bag1.toArray();
        for (T item : bag1Array) {
            result.add(item);
        }
        
        // Add all items from second bag
        T[] bag2Array = bag2.toArray();
        for (T item : bag2Array) {
            result.add(item);
        }
        
        return result;
    }
    
    /**
     * Creates an intersection of two bags without modifying the original bags.
     * @param <T> the type of elements in the bags
     * @param bag1 the first bag
     * @param bag2 the second bag
     * @return a new bag containing elements that appear in both bags
     */
    public static <T> BagInterface<T> intersection(BagInterface<T> bag1, BagInterface<T> bag2) {
        BagInterface<T> result = new LinkedBag<>();
        BagInterface<T> copyOfBag2 = copyBag(bag2);
        
        T[] bag1Array = bag1.toArray();
        for (T item : bag1Array) {
            if (copyOfBag2.contains(item)) {
                result.add(item);
                copyOfBag2.remove(item);
            }
        }
        
        return result;
    }
    
    /**
     * Creates the difference of two bags (bag1 - bag2) without modifying the original bags.
     * @param <T> the type of elements in the bags
     * @param bag1 the first bag
     * @param bag2 the second bag
     * @return a new bag containing elements in bag1 but not in bag2
     */
    public static <T> BagInterface<T> difference(BagInterface<T> bag1, BagInterface<T> bag2) {
        BagInterface<T> result = new LinkedBag<>();
        BagInterface<T> copyOfBag2 = copyBag(bag2);
        
        T[] bag1Array = bag1.toArray();
        for (T item : bag1Array) {
            if (copyOfBag2.contains(item)) {
                copyOfBag2.remove(item);
            } else {
                result.add(item);
            }
        }
        
        return result;
    }
    
    /**
     * Creates a copy of a bag.
     * @param <T> the type of elements in the bag
     * @param originalBag the bag to copy
     * @return a new bag with the same contents as the original
     */
    public static <T> BagInterface<T> copyBag(BagInterface<T> originalBag) {
        BagInterface<T> copy = new LinkedBag<>();
        T[] originalArray = originalBag.toArray();
        
        for (T item : originalArray) {
            copy.add(item);
        }
        
        return copy;
    }
    
    /**
     * Checks if one bag is a subset of another.
     * @param <T> the type of elements in the bags
     * @param subsetBag the potential subset bag
     * @param supersetBag the potential superset bag
     * @return true if subsetBag is a subset of supersetBag, false otherwise
     */
    public static <T> boolean isSubset(BagInterface<T> subsetBag, BagInterface<T> supersetBag) {
        T[] subsetArray = subsetBag.toArray();
        
        for (T item : subsetArray) {
            if (subsetBag.getFrequencyOf(item) > supersetBag.getFrequencyOf(item)) {
                return false;
            }
        }
        
        return true;
    }
    
    /**
     * Checks if two bags are equal (same elements with same frequencies).
     * @param <T> the type of elements in the bags
     * @param bag1 the first bag
     * @param bag2 the second bag
     * @return true if the bags are equal, false otherwise
     */
    public static <T> boolean areEqual(BagInterface<T> bag1, BagInterface<T> bag2) {
        if (bag1.getCurrentSize() != bag2.getCurrentSize()) {
            return false;
        }
        
        T[] bag1Array = bag1.toArray();
        for (T item : bag1Array) {
            if (bag1.getFrequencyOf(item) != bag2.getFrequencyOf(item)) {
                return false;
            }
        }
        
        return true;
    }
    
    /**
     * Checks if two bags are disjoint (have no common elements).
     * @param <T> the type of elements in the bags
     * @param bag1 the first bag
     * @param bag2 the second bag
     * @return true if the bags are disjoint, false otherwise
     */
    public static <T> boolean areDisjoint(BagInterface<T> bag1, BagInterface<T> bag2) {
        T[] bag1Array = bag1.toArray();
        
        for (T item : bag1Array) {
            if (bag2.contains(item)) {
                return false;
            }
        }
        
        return true;
    }
    
    /**
     * Gets all unique elements from a bag (removes duplicates).
     * @param <T> the type of elements in the bag
     * @param bag the input bag
     * @return a new bag containing only unique elements
     */
    public static <T> BagInterface<T> getUniqueElements(BagInterface<T> bag) {
        BagInterface<T> result = new LinkedBag<>();
        T[] bagArray = bag.toArray();
        
        for (T item : bagArray) {
            if (!result.contains(item)) {
                result.add(item);
            }
        }
        
        return result;
    }
    
    /**
     * Gets elements that appear more than once in a bag.
     * @param <T> the type of elements in the bag
     * @param bag the input bag
     * @return a new bag containing only duplicate elements (one copy each)
     */
    public static <T> BagInterface<T> getDuplicateElements(BagInterface<T> bag) {
        BagInterface<T> result = new LinkedBag<>();
        BagInterface<T> processed = new LinkedBag<>();
        T[] bagArray = bag.toArray();
        
        for (T item : bagArray) {
            if (!processed.contains(item)) {
                if (bag.getFrequencyOf(item) > 1) {
                    result.add(item);
                }
                processed.add(item);
            }
        }
        
        return result;
    }
    
    /**
     * Converts a bag to a string representation.
     * @param <T> the type of elements in the bag
     * @param bag the bag to convert
     * @return a string representation of the bag
     */
    public static <T> String bagToString(BagInterface<T> bag) {
        if (bag.isEmpty()) {
            return "Empty bag";
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append("Bag contents (").append(bag.getCurrentSize()).append(" items): [");
        
        T[] bagArray = bag.toArray();
        for (int i = 0; i < bagArray.length; i++) {
            sb.append(bagArray[i]);
            if (i < bagArray.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        
        return sb.toString();
    }
    
    /**
     * Displays frequency analysis of elements in a bag.
     * @param <T> the type of elements in the bag
     * @param bag the bag to analyze
     * @return a string showing frequency of each unique element
     */
    public static <T> String getFrequencyAnalysis(BagInterface<T> bag) {
        if (bag.isEmpty()) {
            return "Empty bag - no frequency analysis available";
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append("Frequency Analysis:\n");
        
        BagInterface<T> uniqueElements = getUniqueElements(bag);
        T[] uniqueArray = uniqueElements.toArray();
        
        for (T item : uniqueArray) {
            int frequency = bag.getFrequencyOf(item);
            sb.append(item).append(": ").append(frequency).append(" time");
            if (frequency != 1) {
                sb.append("s");
            }
            sb.append("\n");
        }
        
        return sb.toString();
    }
    
    /**
     * Merges multiple bags into one.
     * @param <T> the type of elements in the bags
     * @param bags the bags to merge
     * @return a new bag containing all elements from all input bags
     */
    @SafeVarargs
    public static <T> BagInterface<T> mergeBags(BagInterface<T>... bags) {
        BagInterface<T> result = new LinkedBag<>();
        
        for (BagInterface<T> bag : bags) {
            T[] bagArray = bag.toArray();
            for (T item : bagArray) {
                result.add(item);
            }
        }
        
        return result;
    }
    
    /**
     * Removes all occurrences of specific elements from a bag.
     * @param <T> the type of elements in the bag
     * @param bag the bag to modify
     * @param elementsToRemove the elements to remove
     * @return the number of elements removed
     */
    @SafeVarargs
    public static <T> int removeAllOccurrences(BagInterface<T> bag, T... elementsToRemove) {
        int removedCount = 0;
        
        for (T element : elementsToRemove) {
            while (bag.contains(element)) {
                if (bag.remove(element)) {
                    removedCount++;
                } else {
                    break; // Safety check
                }
            }
        }
        
        return removedCount;
    }
} 