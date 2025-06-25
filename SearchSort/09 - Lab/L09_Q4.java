public class L09_Q4 {
    public static void main(String[] args) {
        int[] arr = {10, 34, 2, 56, 7, 67, 88, 42};
        
        System.out.println("Original array:");
        for(int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
        
        insertionSort(arr);
        
        System.out.println("After insertion sort:");
        for(int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
    
    public static void insertionSort(int[] arr) {
        int length = arr.length;
        
        // Start from the second element (index 1)
        for(int i = 1; i < length; i++) {
            int key = arr[i];  // Current element to be inserted
            int j = i - 1;     // Index of the last element in sorted portion
            
            // Move elements that are greater than key one position ahead
            while(j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];  // Shift element to the right
                j--;
            }
            
            // Insert the key at its correct position
            arr[j + 1] = key;
        }
    }
}
