public class T09_Q1 {
    public static void main(String[] args) {
        int[] arr = {3, 8, 12, 34, 54, 85,61, 110};
        int linearSearch1 = linearSearch(arr, 45);
        int linearSearch2 = linearSearch(arr, 54);

        System.out.println("Linear Search for 45: " + linearSearch1);
        System.out.println("Linear Search for 54: " + linearSearch2);

        int binarySearch1 = binarySearch(arr, 45);
        int binarySearch2 = binarySearch(arr, 54);
        
        System.out.println("\nBinary Search for 45: " + binarySearch1);
        System.out.println("Binary Search for 54: " + binarySearch2);

    }

    public static int linearSearch(int[] list, int key) {
        for(int i = 0; i < list.length; i++){
            if(list[i] == key){
                return i;
            }
        }
        return -1;
    }

    public static int binarySearch(int[] list, int key){
        int low = 0;
        int high = list.length - 1;

        while (high >= low) {

            int mid = (low + high) / 2;
            
            if (key < list[mid])
              high = mid - 1;
            else if (key == list[mid])
              return mid;
            else
              low = mid + 1;
        }
      
        return -1; // Now high < low
    }
}
