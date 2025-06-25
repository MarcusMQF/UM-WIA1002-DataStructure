public class L09_Q1 {
    public static void main(String[] args) {
        int[] arr = {45, 7, 2, 8, 19, 3};

        System.out.println("Original array:");
        for(int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();

        // Create an object to call non-static method
        L09_Q1 sorter = new L09_Q1();
        sorter.selectionSortSmallest(arr);
        
        System.out.println("After selectionSortSmallest:");
        for(int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
        
        int[] arr2 = {45, 7, 2, 8, 19, 3};
        
        System.out.println("Original array:");
        for(int i = 0; i < arr2.length; i++) {
            System.out.print(arr2[i] + " ");
        }
        System.out.println();
        
        // Using the same object to call another non-static method
        sorter.selectionSortLargest(arr2);
        
        System.out.println("After selectionSortLargest:");
        for(int i = 0; i < arr2.length; i++) {
            System.out.print(arr2[i] + " ");
        }
        System.out.println();
    }
    
    public void selectionSortSmallest(int[] arr) {
        int length = arr.length;

        for(int i = 0; i < length - 1; i++){
            int currentMin = arr[i];
            int currentMinIndex = i;

            // To find the smallest element in the array
            for(int j = i + 1; j < length; j++){
                if(currentMin > arr[j]){
                    currentMin = arr[j];
                    currentMinIndex = j;
                }
            }

            // Swap the smallest element with the current element
            if(currentMinIndex != i){
                arr[currentMinIndex] = arr[i];
                arr[i] = currentMin;
            }
        }
    }
    
    public void selectionSortLargest(int[] arr) {
        int length = arr.length;

        // Start from the last position and work backwards
        for(int i = length - 1; i > 0; i--){
            int currentMax = arr[i];
            int currentMaxIndex = i;

            // Find the largest element in the unsorted portion (from 0 to i)
            for(int j = i - 1; j >= 0; j--){
                if(currentMax < arr[j]){
                    currentMax = arr[j];
                    currentMaxIndex = j;
                }
            }

            // Swap the largest element with the current position
            if(currentMaxIndex != i){
                arr[currentMaxIndex] = arr[i];
                arr[i] = currentMax;
            }
        }
    }
}
