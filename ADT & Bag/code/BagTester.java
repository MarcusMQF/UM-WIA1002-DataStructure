/**
 * A comprehensive test class for testing all Bag implementations.
 * This class provides various test scenarios and validation methods.
 * 
 * @author Data Structure Course
 * @version 1.0
 */
public class BagTester {
    
    private static int totalTests = 0;
    private static int passedTests = 0;
    private static int failedTests = 0;
    
    /**
     * Main method to run all tests.
     */
    public static void main(String[] args) {
        System.out.println("=== Comprehensive Bag Testing Suite ===\n");
        
        // Test all implementations
        testArrayBag();
        testLinkedBag();
        testResizableArrayBag();
        testBagOperations();
        testEdgeCases();
        testPerformanceComparison();
        
        // Print final results
        printTestSummary();
    }
    
    /**
     * Tests ArrayBag implementation.
     */
    public static void testArrayBag() {
        System.out.println("=== Testing ArrayBag ===");
        BagInterface<String> bag = new ArrayBag<>(5); // Small capacity for testing
        
        testBasicOperations("ArrayBag", bag);
        testCapacityLimits("ArrayBag", bag);
        testAdvancedOperations("ArrayBag", bag);
        
        System.out.println();
    }
    
    /**
     * Tests LinkedBag implementation.
     */
    public static void testLinkedBag() {
        System.out.println("=== Testing LinkedBag ===");
        BagInterface<String> bag = new LinkedBag<>();
        
        testBasicOperations("LinkedBag", bag);
        testUnlimitedCapacity("LinkedBag", bag);
        testAdvancedOperations("LinkedBag", bag);
        
        System.out.println();
    }
    
    /**
     * Tests ResizableArrayBag implementation.
     */
    public static void testResizableArrayBag() {
        System.out.println("=== Testing ResizableArrayBag ===");
        BagInterface<String> bag = new ResizableArrayBag<>(3); // Start small to test resizing
        
        testBasicOperations("ResizableArrayBag", bag);
        testResizingCapability("ResizableArrayBag", (ResizableArrayBag<String>) bag);
        testAdvancedOperations("ResizableArrayBag", bag);
        
        System.out.println();
    }
    
    /**
     * Tests basic operations common to all bag implementations.
     */
    public static void testBasicOperations(String bagType, BagInterface<String> bag) {
        System.out.println("--- Basic Operations for " + bagType + " ---");
        
        // Test initial state
        assertTrue(bagType + " should be empty initially", bag.isEmpty());
        assertEquals(bagType + " size should be 0 initially", 0, bag.getCurrentSize());
        
        // Test adding elements
        assertTrue(bagType + " should add 'apple'", bag.add("apple"));
        assertTrue(bagType + " should add 'banana'", bag.add("banana"));
        assertTrue(bagType + " should add 'apple' again", bag.add("apple"));
        
        assertFalse(bagType + " should not be empty after adding", bag.isEmpty());
        assertEquals(bagType + " size should be 3 after adding", 3, bag.getCurrentSize());
        
        // Test contains
        assertTrue(bagType + " should contain 'apple'", bag.contains("apple"));
        assertTrue(bagType + " should contain 'banana'", bag.contains("banana"));
        assertFalse(bagType + " should not contain 'cherry'", bag.contains("cherry"));
        
        // Test frequency
        assertEquals(bagType + " frequency of 'apple' should be 2", 2, bag.getFrequencyOf("apple"));
        assertEquals(bagType + " frequency of 'banana' should be 1", 1, bag.getFrequencyOf("banana"));
        assertEquals(bagType + " frequency of 'cherry' should be 0", 0, bag.getFrequencyOf("cherry"));
        
        // Test removal
        String removed = bag.remove();
        assertNotNull(bagType + " remove should return an item", removed);
        assertEquals(bagType + " size should be 2 after removal", 2, bag.getCurrentSize());
        
        assertTrue(bagType + " should remove 'apple'", bag.remove("apple"));
        assertEquals(bagType + " frequency of 'apple' should be 1 after removal", 1, bag.getFrequencyOf("apple"));
        
        // Test toArray
        String[] array = bag.toArray();
        assertNotNull(bagType + " toArray should not return null", array);
        assertEquals(bagType + " array length should match bag size", bag.getCurrentSize(), array.length);
        
        // Test clear
        bag.clear();
        assertTrue(bagType + " should be empty after clear", bag.isEmpty());
        assertEquals(bagType + " size should be 0 after clear", 0, bag.getCurrentSize());
    }
    
    /**
     * Tests capacity limits for fixed-size bags.
     */
    public static void testCapacityLimits(String bagType, BagInterface<String> bag) {
        System.out.println("--- Capacity Limits for " + bagType + " ---");
        
        // Fill the bag to capacity
        for (int i = 0; i < bag.getCapacity(); i++) {
            assertTrue(bagType + " should accept item " + i, bag.add("item" + i));
        }
        
        assertTrue(bagType + " should be full", bag.isFull());
        
        // Try to add when full
        assertFalse(bagType + " should reject item when full", bag.add("overflow"));
        
        bag.clear(); // Clean up
    }
    
    /**
     * Tests unlimited capacity for linked implementations.
     */
    public static void testUnlimitedCapacity(String bagType, BagInterface<String> bag) {
        System.out.println("--- Unlimited Capacity for " + bagType + " ---");
        
        // Add many items
        for (int i = 0; i < 100; i++) {
            assertTrue(bagType + " should accept item " + i, bag.add("item" + i));
        }
        
        assertFalse(bagType + " should never be full", bag.isFull());
        assertEquals(bagType + " size should be 100", 100, bag.getCurrentSize());
        assertEquals(bagType + " capacity should be unlimited", -1, bag.getCapacity());
        
        bag.clear(); // Clean up
    }
    
    /**
     * Tests resizing capability for ResizableArrayBag.
     */
    public static void testResizingCapability(String bagType, ResizableArrayBag<String> bag) {
        System.out.println("--- Resizing Capability for " + bagType + " ---");
        
        int initialCapacity = bag.getCurrentCapacity();
        System.out.println("Initial capacity: " + initialCapacity);
        
        // Add items to trigger resize
        for (int i = 0; i < initialCapacity + 5; i++) {
            assertTrue(bagType + " should add item " + i, bag.add("item" + i));
        }
        
        int newCapacity = bag.getCurrentCapacity();
        System.out.println("New capacity after resize: " + newCapacity);
        
        assertTrue(bagType + " capacity should have increased", newCapacity > initialCapacity);
        assertFalse(bagType + " should not be full after resize", bag.isFull());
        
        bag.clear(); // Clean up
    }
    
    /**
     * Tests advanced operations (union, intersection, difference).
     */
    public static void testAdvancedOperations(String bagType, BagInterface<String> bag) {
        System.out.println("--- Advanced Operations for " + bagType + " ---");
        
        // Setup first bag
        bag.add("apple");
        bag.add("banana");
        bag.add("cherry");
        bag.add("apple"); // duplicate
        
        // Create second bag
        BagInterface<String> bag2 = new LinkedBag<>();
        bag2.add("banana");
        bag2.add("date");
        bag2.add("elderberry");
        bag2.add("banana"); // duplicate
        
        // Test union
        BagInterface<String> union = bag.union(bag2);
        assertEquals(bagType + " union size should be 8", 8, union.getCurrentSize());
        assertTrue(bagType + " union should contain all elements", 
            union.contains("apple") && union.contains("banana") && 
            union.contains("cherry") && union.contains("date") && 
            union.contains("elderberry"));
        
        // Test intersection
        BagInterface<String> intersection = bag.intersection(bag2);
        assertEquals(bagType + " intersection should contain 2 bananas", 2, intersection.getFrequencyOf("banana"));
        assertFalse(bagType + " intersection should not contain apple", intersection.contains("apple"));
        
        // Test difference
        BagInterface<String> difference = bag.difference(bag2);
        assertTrue(bagType + " difference should contain apple", difference.contains("apple"));
        assertTrue(bagType + " difference should contain cherry", difference.contains("cherry"));
        assertFalse(bagType + " difference should not contain date", difference.contains("date"));
        
        // Test equals
        BagInterface<String> bag3 = new LinkedBag<>();
        bag3.add("apple");
        bag3.add("banana");
        bag3.add("cherry");
        bag3.add("apple");
        
        assertTrue(bagType + " should equal bag with same contents", bag.equals(bag3));
        
        bag.clear(); // Clean up
    }
    
    /**
     * Tests BagOperations utility methods.
     */
    public static void testBagOperations() {
        System.out.println("=== Testing BagOperations Utility Class ===");
        
        BagInterface<String> bag1 = new ArrayBag<>();
        bag1.add("red");
        bag1.add("blue");
        bag1.add("green");
        bag1.add("red");
        
        BagInterface<String> bag2 = new LinkedBag<>();
        bag2.add("blue");
        bag2.add("yellow");
        bag2.add("purple");
        
        // Test static methods
        BagInterface<String> union = BagOperations.union(bag1, bag2);
        assertEquals("Union size should be 7", 7, union.getCurrentSize());
        
        BagInterface<String> intersection = BagOperations.intersection(bag1, bag2);
        assertEquals("Intersection should contain 1 blue", 1, intersection.getFrequencyOf("blue"));
        
        assertTrue("Bags should not be equal", !BagOperations.areEqual(bag1, bag2));
        
        BagInterface<String> copy = BagOperations.copyBag(bag1);
        assertTrue("Copy should equal original", BagOperations.areEqual(bag1, copy));
        
        assertFalse("Bags should not be disjoint", BagOperations.areDisjoint(bag1, bag2));
        
        BagInterface<String> unique = BagOperations.getUniqueElements(bag1);
        assertEquals("Unique elements should be 3", 3, unique.getCurrentSize());
        assertEquals("Red frequency should be 1 in unique", 1, unique.getFrequencyOf("red"));
        
        BagInterface<String> duplicates = BagOperations.getDuplicateElements(bag1);
        assertEquals("Should find 1 duplicate element", 1, duplicates.getCurrentSize());
        assertTrue("Duplicates should contain red", duplicates.contains("red"));
        
        System.out.println();
    }
    
    /**
     * Tests edge cases and error conditions.
     */
    public static void testEdgeCases() {
        System.out.println("=== Testing Edge Cases ===");
        
        // Test with null elements (should handle gracefully or throw exception)
        BagInterface<String> bag = new LinkedBag<>();
        
        // Test operations on empty bag
        assertNull("Remove from empty bag should return null", bag.remove());
        assertFalse("Remove specific item from empty bag should return false", bag.remove("nonexistent"));
        assertEquals("Frequency in empty bag should be 0", 0, bag.getFrequencyOf("anything"));
        assertFalse("Contains on empty bag should return false", bag.contains("anything"));
        
        String[] emptyArray = bag.toArray();
        assertNotNull("toArray on empty bag should not return null", emptyArray);
        assertEquals("Empty bag array should have length 0", 0, emptyArray.length);
        
        // Test with many duplicates
        for (int i = 0; i < 10; i++) {
            bag.add("duplicate");
        }
        assertEquals("Should have 10 duplicates", 10, bag.getFrequencyOf("duplicate"));
        
        // Remove all duplicates
        while (bag.contains("duplicate")) {
            bag.remove("duplicate");
        }
        assertTrue("Bag should be empty after removing all", bag.isEmpty());
        
        System.out.println();
    }
    
    /**
     * Compares performance of different implementations.
     */
    public static void testPerformanceComparison() {
        System.out.println("=== Performance Comparison ===");
        
        int testSize = 1000;
        
        // Test ArrayBag
        long startTime = System.nanoTime();
        BagInterface<Integer> arrayBag = new ArrayBag<>(testSize);
        for (int i = 0; i < testSize; i++) {
            arrayBag.add(i % 100); // Create some duplicates
        }
        long arrayTime = System.nanoTime() - startTime;
        
        // Test LinkedBag
        startTime = System.nanoTime();
        BagInterface<Integer> linkedBag = new LinkedBag<>();
        for (int i = 0; i < testSize; i++) {
            linkedBag.add(i % 100);
        }
        long linkedTime = System.nanoTime() - startTime;
        
        // Test ResizableArrayBag
        startTime = System.nanoTime();
        BagInterface<Integer> resizableBag = new ResizableArrayBag<>();
        for (int i = 0; i < testSize; i++) {
            resizableBag.add(i % 100);
        }
        long resizableTime = System.nanoTime() - startTime;
        
        System.out.printf("Adding %d elements:\n", testSize);
        System.out.printf("ArrayBag:          %,d nanoseconds\n", arrayTime);
        System.out.printf("LinkedBag:         %,d nanoseconds\n", linkedTime);
        System.out.printf("ResizableArrayBag: %,d nanoseconds\n", resizableTime);
        
        // Test search performance
        Integer searchValue = 50;
        
        startTime = System.nanoTime();
        arrayBag.contains(searchValue);
        long arraySearchTime = System.nanoTime() - startTime;
        
        startTime = System.nanoTime();
        linkedBag.contains(searchValue);
        long linkedSearchTime = System.nanoTime() - startTime;
        
        startTime = System.nanoTime();
        resizableBag.contains(searchValue);
        long resizableSearchTime = System.nanoTime() - startTime;
        
        System.out.printf("\nSearching for element:\n");
        System.out.printf("ArrayBag:          %,d nanoseconds\n", arraySearchTime);
        System.out.printf("LinkedBag:         %,d nanoseconds\n", linkedSearchTime);
        System.out.printf("ResizableArrayBag: %,d nanoseconds\n", resizableSearchTime);
        
        System.out.println();
    }
    
    // Utility methods for testing
    
    public static void assertTrue(String message, boolean condition) {
        totalTests++;
        if (condition) {
            passedTests++;
            System.out.println("✓ PASS: " + message);
        } else {
            failedTests++;
            System.out.println("✗ FAIL: " + message);
        }
    }
    
    public static void assertFalse(String message, boolean condition) {
        assertTrue(message, !condition);
    }
    
    public static void assertEquals(String message, int expected, int actual) {
        totalTests++;
        if (expected == actual) {
            passedTests++;
            System.out.println("✓ PASS: " + message + " (expected: " + expected + ", actual: " + actual + ")");
        } else {
            failedTests++;
            System.out.println("✗ FAIL: " + message + " (expected: " + expected + ", actual: " + actual + ")");
        }
    }
    
    public static void assertNotNull(String message, Object object) {
        totalTests++;
        if (object != null) {
            passedTests++;
            System.out.println("✓ PASS: " + message);
        } else {
            failedTests++;
            System.out.println("✗ FAIL: " + message + " (object was null)");
        }
    }
    
    public static void assertNull(String message, Object object) {
        totalTests++;
        if (object == null) {
            passedTests++;
            System.out.println("✓ PASS: " + message);
        } else {
            failedTests++;
            System.out.println("✗ FAIL: " + message + " (object was not null)");
        }
    }
    
    public static void printTestSummary() {
        System.out.println("=== Test Summary ===");
        System.out.println("Total Tests: " + totalTests);
        System.out.println("Passed: " + passedTests + " (" + 
                         String.format("%.1f", (passedTests * 100.0 / totalTests)) + "%)");
        System.out.println("Failed: " + failedTests + " (" + 
                         String.format("%.1f", (failedTests * 100.0 / totalTests)) + "%)");
        
        if (failedTests == 0) {
            System.out.println("🎉 All tests passed!");
        } else {
            System.out.println("⚠️  Some tests failed. Please review the implementation.");
        }
    }
} 