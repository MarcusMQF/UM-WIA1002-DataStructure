public class T09_Q2 {
    public static void main(String[] args) {
        int[] originalArray = {90, 8, 7, 56, 125, 237, 9, 1, 653};
        
        System.out.println("=== SORTING ALGORITHMS DEMONSTRATION ===");
        System.out.println("Original Array: [90, 8, 7, 56, 125, 237, 9, 1, 653]");
        System.out.println();
        
        // a. Selection Sort
        demonstrateSelectionSort(originalArray.clone());
        System.out.println();
        
        // b. Insertion Sort
        demonstrateInsertionSort(originalArray.clone());
        System.out.println();
        
        // c. Bubble Sort
        demonstrateBubbleSort(originalArray.clone());
        System.out.println();
        
        // d. Merge Sort
        demonstrateMergeSort(originalArray.clone());
    }
    
    // ================ SELECTION SORT ================
    public static void demonstrateSelectionSort(int[] arr) {
        System.out.println("a. SELECTION SORT");
        System.out.println("Technique: Find the smallest element and swap it with the first element,");
        System.out.println("then find the next smallest and swap with second element, and so on.");
        System.out.println();
        
        System.out.println("Trace of Execution:");
        printArray("Initial", arr);
        
        for(int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            
            // Find minimum element in remaining array
            for(int j = i + 1; j < arr.length; j++) {
                if(arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            
            // Swap if minimum is not at current position
            if(minIndex != i) {
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
                
                System.out.println("Pass " + (i + 1) + ": Found min " + arr[i] + 
                                 " at index " + minIndex + ", swap with position " + i);
                printArray("Result", arr);
            } else {
                System.out.println("Pass " + (i + 1) + ": Element " + arr[i] + 
                                 " already in correct position");
                printArray("Result", arr);
            }
        }
        System.out.println("Selection Sort Complete!");
    }
    
    // ================ INSERTION SORT ================
    public static void demonstrateInsertionSort(int[] arr) {
        System.out.println("b. INSERTION SORT");
        System.out.println("Technique: Take each element and insert it into its correct position");
        System.out.println("in the already sorted portion of the array.");
        System.out.println();
        
        System.out.println("Trace of Execution:");
        printArray("Initial", arr);
        
        for(int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;
            
            System.out.println("Pass " + i + ": Insert " + key + " into sorted portion");
            
            // Shift elements greater than key to the right
            while(j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
            
            printArray("Result", arr);
        }
        System.out.println("Insertion Sort Complete!");
    }
    
    // ================ BUBBLE SORT ================
    public static void demonstrateBubbleSort(int[] arr) {
        System.out.println("c. BUBBLE SORT");
        System.out.println("Technique: Compare adjacent elements and swap if they are in wrong order.");
        System.out.println("Repeat this process until no more swaps are needed.");
        System.out.println();
        
        System.out.println("Trace of Execution:");
        printArray("Initial", arr);
        
        int passCount = 0;
        for(int i = 0; i < arr.length - 1; i++) {
            boolean swapped = false;
            passCount++;
            
            System.out.println("Pass " + passCount + ":");
            for(int j = 0; j < arr.length - 1 - i; j++) {
                if(arr[j] > arr[j + 1]) {
                    // Swap elements
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                    
                    System.out.println("  Swap " + arr[j + 1] + " and " + arr[j]);
                }
            }
            printArray("After Pass " + passCount, arr);
            
            if(!swapped) {
                System.out.println("No swaps needed, array is sorted!");
                break;
            }
        }
        System.out.println("Bubble Sort Complete!");
    }
    
    // ================ MERGE SORT ================
    public static void demonstrateMergeSort(int[] arr) {
        System.out.println("d. MERGE SORT");
        System.out.println("Technique: Divide the array into smaller subarrays, sort them recursively,");
        System.out.println("then merge the sorted subarrays back together.");
        System.out.println();
        
        System.out.println("Trace of Execution:");
        printArray("Initial", arr);
        
        mergeSortRecursive(arr, 0, arr.length - 1, 0);
        
        printArray("Final", arr);
        System.out.println("Merge Sort Complete!");
    }
    
    public static void mergeSortRecursive(int[] arr, int left, int right, int level) {
        if(left < right) {
            int mid = (left + right) / 2;
            
            // Print divide step
            String indent = "  ".repeat(level);
            System.out.println(indent + "Divide: [" + left + ".." + right + "] into [" + 
                             left + ".." + mid + "] and [" + (mid + 1) + ".." + right + "]");
            
            // Recursively sort both halves
            mergeSortRecursive(arr, left, mid, level + 1);
            mergeSortRecursive(arr, mid + 1, right, level + 1);
            
            // Merge the sorted halves
            merge(arr, left, mid, right, level);
        }
    }
    
    public static void merge(int[] arr, int left, int mid, int right, int level) {
        // Create temporary arrays for left and right subarrays
        int[] leftArr = new int[mid - left + 1];
        int[] rightArr = new int[right - mid];
        
        // Copy data to temporary arrays
        for(int i = 0; i < leftArr.length; i++) {
            leftArr[i] = arr[left + i];
        }
        for(int j = 0; j < rightArr.length; j++) {
            rightArr[j] = arr[mid + 1 + j];
        }
        
        String indent = "  ".repeat(level);
        System.out.print(indent + "Merge: [");
        for(int x = 0; x < leftArr.length; x++) {
            System.out.print(leftArr[x] + (x < leftArr.length - 1 ? "," : ""));
        }
        System.out.print("] and [");
        for(int x = 0; x < rightArr.length; x++) {
            System.out.print(rightArr[x] + (x < rightArr.length - 1 ? "," : ""));
        }
        System.out.print("] -> ");
        
        // Merge the temporary arrays back into arr[left..right]
        int i = 0, j = 0, k = left;
        
        while(i < leftArr.length && j < rightArr.length) {
            if(leftArr[i] <= rightArr[j]) {
                arr[k] = leftArr[i];
                i++;
            } else {
                arr[k] = rightArr[j];
                j++;
            }
            k++;
        }
        
        // Copy remaining elements
        while(i < leftArr.length) {
            arr[k] = leftArr[i];
            i++;
            k++;
        }
        while(j < rightArr.length) {
            arr[k] = rightArr[j];
            j++;
            k++;
        }
        
        // Print merged result
        System.out.print("[");
        for(int x = left; x <= right; x++) {
            System.out.print(arr[x] + (x < right ? "," : ""));
        }
        System.out.println("]");
    }
    
    // Helper method to print array
    public static void printArray(String label, int[] arr) {
        System.out.print(label + ": [");
        for(int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + (i < arr.length - 1 ? ", " : ""));
        }
        System.out.println("]");
    }
}
