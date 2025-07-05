/**
 * A comprehensive performance analysis tool for comparing different Bag implementations.
 * This class provides detailed benchmarking and analysis of various operations.
 * 
 * @author Data Structure Course
 * @version 1.0
 */
public class PerformanceAnalysis {
    
    /**
     * Inner class to store benchmark results.
     */
    public static class BenchmarkResult {
        private String operation;
        private String implementation;
        private int dataSize;
        private long timeNanoseconds;
        private long memoryUsed;
        
        public BenchmarkResult(String operation, String implementation, int dataSize, 
                              long timeNanoseconds, long memoryUsed) {
            this.operation = operation;
            this.implementation = implementation;
            this.dataSize = dataSize;
            this.timeNanoseconds = timeNanoseconds;
            this.memoryUsed = memoryUsed;
        }
        
        public double getTimeMilliseconds() {
            return timeNanoseconds / 1_000_000.0;
        }
        
        public double getTimeSeconds() {
            return timeNanoseconds / 1_000_000_000.0;
        }
        
        public double getMemoryMB() {
            return memoryUsed / (1024.0 * 1024.0);
        }
        
        @Override
        public String toString() {
            return String.format("%-15s %-20s %,8d elements: %,12d ns (%.2f ms) | Memory: %.2f MB",
                implementation, operation, dataSize, timeNanoseconds, getTimeMilliseconds(), getMemoryMB());
        }
    }
    
    /**
     * Main method to run performance analysis.
     */
    public static void main(String[] args) {
        System.out.println("=== Bag Implementation Performance Analysis ===\n");
        
        // Test different data sizes
        int[] dataSizes = {100, 500, 1000, 5000, 10000};
        
        for (int size : dataSizes) {
            System.out.printf("=== Performance Analysis for %,d elements ===\n", size);
            
            // Analyze each implementation
            analyzeArrayBag(size);
            analyzeLinkedBag(size);
            analyzeResizableArrayBag(size);
            
            System.out.println();
        }
        
        // Comprehensive comparison
        performComprehensiveComparison();
        
        // Scalability analysis
        performScalabilityAnalysis();
        
        // Memory usage analysis
        performMemoryAnalysis();
        
        // Operation-specific analysis
        performOperationAnalysis();
    }
    
    /**
     * Analyzes ArrayBag performance.
     */
    public static void analyzeArrayBag(int size) {
        System.out.println("--- ArrayBag Analysis ---");
        
        // Test with sufficient capacity
        BagInterface<Integer> bag = new ArrayBag<>(size * 2);
        
        // Add operation
        BenchmarkResult addResult = benchmarkAddOperation("ArrayBag", bag, size);
        System.out.println(addResult);
        
        // Search operation
        BenchmarkResult searchResult = benchmarkSearchOperation("ArrayBag", bag, size / 10);
        System.out.println(searchResult);
        
        // Remove operation
        BenchmarkResult removeResult = benchmarkRemoveOperation("ArrayBag", bag, size / 4);
        System.out.println(removeResult);
        
        // Frequency operation
        BenchmarkResult freqResult = benchmarkFrequencyOperation("ArrayBag", bag, size / 20);
        System.out.println(freqResult);
    }
    
    /**
     * Analyzes LinkedBag performance.
     */
    public static void analyzeLinkedBag(int size) {
        System.out.println("--- LinkedBag Analysis ---");
        
        BagInterface<Integer> bag = new LinkedBag<>();
        
        // Add operation
        BenchmarkResult addResult = benchmarkAddOperation("LinkedBag", bag, size);
        System.out.println(addResult);
        
        // Search operation
        BenchmarkResult searchResult = benchmarkSearchOperation("LinkedBag", bag, size / 10);
        System.out.println(searchResult);
        
        // Remove operation
        BenchmarkResult removeResult = benchmarkRemoveOperation("LinkedBag", bag, size / 4);
        System.out.println(removeResult);
        
        // Frequency operation
        BenchmarkResult freqResult = benchmarkFrequencyOperation("LinkedBag", bag, size / 20);
        System.out.println(freqResult);
    }
    
    /**
     * Analyzes ResizableArrayBag performance.
     */
    public static void analyzeResizableArrayBag(int size) {
        System.out.println("--- ResizableArrayBag Analysis ---");
        
        BagInterface<Integer> bag = new ResizableArrayBag<>(10); // Start small to test resizing
        
        // Add operation (including resizing)
        BenchmarkResult addResult = benchmarkAddOperation("ResizableArrayBag", bag, size);
        System.out.println(addResult);
        
        // Search operation
        BenchmarkResult searchResult = benchmarkSearchOperation("ResizableArrayBag", bag, size / 10);
        System.out.println(searchResult);
        
        // Remove operation
        BenchmarkResult removeResult = benchmarkRemoveOperation("ResizableArrayBag", bag, size / 4);
        System.out.println(removeResult);
        
        // Frequency operation
        BenchmarkResult freqResult = benchmarkFrequencyOperation("ResizableArrayBag", bag, size / 20);
        System.out.println(freqResult);
    }
    
    /**
     * Benchmarks add operation.
     */
    public static BenchmarkResult benchmarkAddOperation(String implementation, 
                                                       BagInterface<Integer> bag, int count) {
        long memoryBefore = getUsedMemory();
        long startTime = System.nanoTime();
        
        for (int i = 0; i < count; i++) {
            bag.add(i % 100); // Create some duplicates
        }
        
        long endTime = System.nanoTime();
        long memoryAfter = getUsedMemory();
        
        return new BenchmarkResult("Add", implementation, count, 
                                 endTime - startTime, memoryAfter - memoryBefore);
    }
    
    /**
     * Benchmarks search operation.
     */
    public static BenchmarkResult benchmarkSearchOperation(String implementation, 
                                                          BagInterface<Integer> bag, int searches) {
        long memoryBefore = getUsedMemory();
        long startTime = System.nanoTime();
        
        for (int i = 0; i < searches; i++) {
            bag.contains(i % 50); // Search for various elements
        }
        
        long endTime = System.nanoTime();
        long memoryAfter = getUsedMemory();
        
        return new BenchmarkResult("Search", implementation, searches, 
                                 endTime - startTime, memoryAfter - memoryBefore);
    }
    
    /**
     * Benchmarks remove operation.
     */
    public static BenchmarkResult benchmarkRemoveOperation(String implementation, 
                                                          BagInterface<Integer> bag, int removals) {
        long memoryBefore = getUsedMemory();
        long startTime = System.nanoTime();
        
        for (int i = 0; i < removals && !bag.isEmpty(); i++) {
            bag.remove();
        }
        
        long endTime = System.nanoTime();
        long memoryAfter = getUsedMemory();
        
        return new BenchmarkResult("Remove", implementation, removals, 
                                 endTime - startTime, memoryAfter - memoryBefore);
    }
    
    /**
     * Benchmarks frequency operation.
     */
    public static BenchmarkResult benchmarkFrequencyOperation(String implementation, 
                                                             BagInterface<Integer> bag, int queries) {
        long memoryBefore = getUsedMemory();
        long startTime = System.nanoTime();
        
        for (int i = 0; i < queries; i++) {
            bag.getFrequencyOf(i % 25);
        }
        
        long endTime = System.nanoTime();
        long memoryAfter = getUsedMemory();
        
        return new BenchmarkResult("Frequency", implementation, queries, 
                                 endTime - startTime, memoryAfter - memoryBefore);
    }
    
    /**
     * Performs comprehensive comparison across implementations.
     */
    public static void performComprehensiveComparison() {
        System.out.println("=== Comprehensive Implementation Comparison ===");
        
        int testSize = 5000;
        
        // Create bags
        BagInterface<Integer> arrayBag = new ArrayBag<>(testSize);
        BagInterface<Integer> linkedBag = new LinkedBag<>();
        BagInterface<Integer> resizableBag = new ResizableArrayBag<>();
        
        // Populate bags with same data
        for (int i = 0; i < testSize; i++) {
            int value = i % 200; // Create duplicates
            arrayBag.add(value);
            linkedBag.add(value);
            resizableBag.add(value);
        }
        
        System.out.println("All bags populated with " + testSize + " elements (200 unique values)");
        System.out.println();
        
        // Compare search operations
        System.out.println("--- Search Performance Comparison ---");
        compareSearchPerformance(arrayBag, linkedBag, resizableBag, 1000);
        
        // Compare advanced operations
        System.out.println("--- Advanced Operations Comparison ---");
        compareAdvancedOperations(arrayBag, linkedBag, resizableBag);
        
        System.out.println();
    }
    
    /**
     * Compares search performance across implementations.
     */
    public static void compareSearchPerformance(BagInterface<Integer> arrayBag,
                                               BagInterface<Integer> linkedBag,
                                               BagInterface<Integer> resizableBag,
                                               int searches) {
        
        // Test contains operation
        long startTime = System.nanoTime();
        for (int i = 0; i < searches; i++) {
            arrayBag.contains(i % 100);
        }
        long arrayTime = System.nanoTime() - startTime;
        
        startTime = System.nanoTime();
        for (int i = 0; i < searches; i++) {
            linkedBag.contains(i % 100);
        }
        long linkedTime = System.nanoTime() - startTime;
        
        startTime = System.nanoTime();
        for (int i = 0; i < searches; i++) {
            resizableBag.contains(i % 100);
        }
        long resizableTime = System.nanoTime() - startTime;
        
        System.out.printf("Contains operation (%,d searches):\n", searches);
        System.out.printf("  ArrayBag:          %,12d ns (%.2f ms)\n", arrayTime, arrayTime / 1_000_000.0);
        System.out.printf("  LinkedBag:         %,12d ns (%.2f ms)\n", linkedTime, linkedTime / 1_000_000.0);
        System.out.printf("  ResizableArrayBag: %,12d ns (%.2f ms)\n", resizableTime, resizableTime / 1_000_000.0);
        
        // Determine winner
        long fastest = Math.min(arrayTime, Math.min(linkedTime, resizableTime));
        String winner = fastest == arrayTime ? "ArrayBag" : 
                       fastest == linkedTime ? "LinkedBag" : "ResizableArrayBag";
        System.out.printf("  Winner: %s\n\n", winner);
    }
    
    /**
     * Compares advanced operations (union, intersection, difference).
     */
    public static void compareAdvancedOperations(BagInterface<Integer> arrayBag,
                                                BagInterface<Integer> linkedBag,
                                                BagInterface<Integer> resizableBag) {
        
        // Create second bag for operations
        BagInterface<Integer> secondBag = new LinkedBag<>();
        for (int i = 0; i < 1000; i++) {
            secondBag.add(i % 150);
        }
        
        // Test union operation
        long startTime = System.nanoTime();
        BagInterface<Integer> unionResult1 = arrayBag.union(secondBag);
        long arrayUnionTime = System.nanoTime() - startTime;
        
        startTime = System.nanoTime();
        BagInterface<Integer> unionResult2 = linkedBag.union(secondBag);
        long linkedUnionTime = System.nanoTime() - startTime;
        
        startTime = System.nanoTime();
        BagInterface<Integer> unionResult3 = resizableBag.union(secondBag);
        long resizableUnionTime = System.nanoTime() - startTime;
        
        System.out.println("Union operation:");
        System.out.printf("  ArrayBag:          %,12d ns (%.2f ms) | Result size: %,d\n", 
                         arrayUnionTime, arrayUnionTime / 1_000_000.0, unionResult1.getCurrentSize());
        System.out.printf("  LinkedBag:         %,12d ns (%.2f ms) | Result size: %,d\n", 
                         linkedUnionTime, linkedUnionTime / 1_000_000.0, unionResult2.getCurrentSize());
        System.out.printf("  ResizableArrayBag: %,12d ns (%.2f ms) | Result size: %,d\n", 
                         resizableUnionTime, resizableUnionTime / 1_000_000.0, unionResult3.getCurrentSize());
        
        // Test intersection operation
        startTime = System.nanoTime();
        BagInterface<Integer> intersectionResult1 = arrayBag.intersection(secondBag);
        long arrayIntersectionTime = System.nanoTime() - startTime;
        
        startTime = System.nanoTime();
        BagInterface<Integer> intersectionResult2 = linkedBag.intersection(secondBag);
        long linkedIntersectionTime = System.nanoTime() - startTime;
        
        startTime = System.nanoTime();
        BagInterface<Integer> intersectionResult3 = resizableBag.intersection(secondBag);
        long resizableIntersectionTime = System.nanoTime() - startTime;
        
        System.out.println("\nIntersection operation:");
        System.out.printf("  ArrayBag:          %,12d ns (%.2f ms) | Result size: %,d\n", 
                         arrayIntersectionTime, arrayIntersectionTime / 1_000_000.0, intersectionResult1.getCurrentSize());
        System.out.printf("  LinkedBag:         %,12d ns (%.2f ms) | Result size: %,d\n", 
                         linkedIntersectionTime, linkedIntersectionTime / 1_000_000.0, intersectionResult2.getCurrentSize());
        System.out.printf("  ResizableArrayBag: %,12d ns (%.2f ms) | Result size: %,d\n", 
                         resizableIntersectionTime, resizableIntersectionTime / 1_000_000.0, intersectionResult3.getCurrentSize());
    }
    
    /**
     * Performs scalability analysis.
     */
    public static void performScalabilityAnalysis() {
        System.out.println("=== Scalability Analysis ===");
        
        int[] sizes = {100, 500, 1000, 5000, 10000, 25000};
        
        System.out.println("Add operation scalability (time in milliseconds):");
        System.out.printf("%-8s %-12s %-12s %-12s\n", "Size", "ArrayBag", "LinkedBag", "ResizableBag");
        System.out.println("-".repeat(50));
        
        for (int size : sizes) {
            // ArrayBag
            BagInterface<Integer> arrayBag = new ArrayBag<>(size);
            long startTime = System.nanoTime();
            for (int i = 0; i < size; i++) {
                arrayBag.add(i);
            }
            long arrayTime = System.nanoTime() - startTime;
            
            // LinkedBag
            BagInterface<Integer> linkedBag = new LinkedBag<>();
            startTime = System.nanoTime();
            for (int i = 0; i < size; i++) {
                linkedBag.add(i);
            }
            long linkedTime = System.nanoTime() - startTime;
            
            // ResizableArrayBag
            BagInterface<Integer> resizableBag = new ResizableArrayBag<>();
            startTime = System.nanoTime();
            for (int i = 0; i < size; i++) {
                resizableBag.add(i);
            }
            long resizableTime = System.nanoTime() - startTime;
            
            System.out.printf("%-8s %-12.2f %-12.2f %-12.2f\n", 
                             String.format("%,d", size),
                             arrayTime / 1_000_000.0,
                             linkedTime / 1_000_000.0,
                             resizableTime / 1_000_000.0);
        }
        
        System.out.println();
    }
    
    /**
     * Performs memory usage analysis.
     */
    public static void performMemoryAnalysis() {
        System.out.println("=== Memory Usage Analysis ===");
        
        int testSize = 10000;
        
        // Force garbage collection before testing
        System.gc();
        Thread.yield();
        
        long baseMemory = getUsedMemory();
        
        // Test ArrayBag memory usage
        BagInterface<Integer> arrayBag = new ArrayBag<>(testSize);
        for (int i = 0; i < testSize; i++) {
            arrayBag.add(i);
        }
        long arrayMemory = getUsedMemory() - baseMemory;
        
        // Clear and test LinkedBag
        arrayBag = null;
        System.gc();
        Thread.yield();
        baseMemory = getUsedMemory();
        
        BagInterface<Integer> linkedBag = new LinkedBag<>();
        for (int i = 0; i < testSize; i++) {
            linkedBag.add(i);
        }
        long linkedMemory = getUsedMemory() - baseMemory;
        
        // Clear and test ResizableArrayBag
        linkedBag = null;
        System.gc();
        Thread.yield();
        baseMemory = getUsedMemory();
        
        BagInterface<Integer> resizableBag = new ResizableArrayBag<>();
        for (int i = 0; i < testSize; i++) {
            resizableBag.add(i);
        }
        long resizableMemory = getUsedMemory() - baseMemory;
        
        System.out.printf("Memory usage for %,d integers:\n", testSize);
        System.out.printf("  ArrayBag:          %,12d bytes (%.2f MB)\n", arrayMemory, arrayMemory / (1024.0 * 1024.0));
        System.out.printf("  LinkedBag:         %,12d bytes (%.2f MB)\n", linkedMemory, linkedMemory / (1024.0 * 1024.0));
        System.out.printf("  ResizableArrayBag: %,12d bytes (%.2f MB)\n", resizableMemory, resizableMemory / (1024.0 * 1024.0));
        
        System.out.printf("\nMemory per element:\n");
        System.out.printf("  ArrayBag:          %.2f bytes/element\n", (double) arrayMemory / testSize);
        System.out.printf("  LinkedBag:         %.2f bytes/element\n", (double) linkedMemory / testSize);
        System.out.printf("  ResizableArrayBag: %.2f bytes/element\n", (double) resizableMemory / testSize);
        
        System.out.println();
    }
    
    /**
     * Performs operation-specific detailed analysis.
     */
    public static void performOperationAnalysis() {
        System.out.println("=== Operation-Specific Analysis ===");
        
        int bagSize = 5000;
        
        // Create and populate bags
        BagInterface<Integer> arrayBag = new ArrayBag<>(bagSize);
        BagInterface<Integer> linkedBag = new LinkedBag<>();
        BagInterface<Integer> resizableBag = new ResizableArrayBag<>();
        
        for (int i = 0; i < bagSize; i++) {
            int value = i % 100; // Create duplicates
            arrayBag.add(value);
            linkedBag.add(value);
            resizableBag.add(value);
        }
        
        // Test toArray operation
        System.out.println("toArray() operation:");
        benchmarkToArrayOperation(arrayBag, linkedBag, resizableBag);
        
        // Test clear operation
        System.out.println("\nclear() operation:");
        benchmarkClearOperation(arrayBag, linkedBag, resizableBag);
    }
    
    /**
     * Benchmarks toArray operation.
     */
    public static void benchmarkToArrayOperation(BagInterface<Integer> arrayBag,
                                                BagInterface<Integer> linkedBag,
                                                BagInterface<Integer> resizableBag) {
        
        long startTime = System.nanoTime();
        Integer[] arrayResult = arrayBag.toArray();
        long arrayTime = System.nanoTime() - startTime;
        
        startTime = System.nanoTime();
        Integer[] linkedResult = linkedBag.toArray();
        long linkedTime = System.nanoTime() - startTime;
        
        startTime = System.nanoTime();
        Integer[] resizableResult = resizableBag.toArray();
        long resizableTime = System.nanoTime() - startTime;
        
        System.out.printf("  ArrayBag:          %,12d ns (%.2f ms) | Array length: %,d\n", 
                         arrayTime, arrayTime / 1_000_000.0, arrayResult.length);
        System.out.printf("  LinkedBag:         %,12d ns (%.2f ms) | Array length: %,d\n", 
                         linkedTime, linkedTime / 1_000_000.0, linkedResult.length);
        System.out.printf("  ResizableArrayBag: %,12d ns (%.2f ms) | Array length: %,d\n", 
                         resizableTime, resizableTime / 1_000_000.0, resizableResult.length);
    }
    
    /**
     * Benchmarks clear operation.
     */
    public static void benchmarkClearOperation(BagInterface<Integer> arrayBag,
                                              BagInterface<Integer> linkedBag,
                                              BagInterface<Integer> resizableBag) {
        
        long startTime = System.nanoTime();
        arrayBag.clear();
        long arrayTime = System.nanoTime() - startTime;
        
        startTime = System.nanoTime();
        linkedBag.clear();
        long linkedTime = System.nanoTime() - startTime;
        
        startTime = System.nanoTime();
        resizableBag.clear();
        long resizableTime = System.nanoTime() - startTime;
        
        System.out.printf("  ArrayBag:          %,12d ns (%.2f ms)\n", arrayTime, arrayTime / 1_000_000.0);
        System.out.printf("  LinkedBag:         %,12d ns (%.2f ms)\n", linkedTime, linkedTime / 1_000_000.0);
        System.out.printf("  ResizableArrayBag: %,12d ns (%.2f ms)\n", resizableTime, resizableTime / 1_000_000.0);
    }
    
    /**
     * Gets current memory usage.
     */
    private static long getUsedMemory() {
        Runtime runtime = Runtime.getRuntime();
        return runtime.totalMemory() - runtime.freeMemory();
    }
} 