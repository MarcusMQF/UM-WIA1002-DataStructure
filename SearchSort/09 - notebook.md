# Search & Sort Algorithm 

## Search Algorithm
**Definition**: Process of looking for a specific element in a group of items (such as in an array)
**Purpose**: Find if an element exists and determine its location
**Return Values**: 
- Index of element if found
- -1 if not found

### Two main search approach
1. Linear Search
2. Binary Search

---

## Linear Search
- Compares the key element sequentially with each element in the array
- Continues until ket matches and element OR list is exhausted
- Simple but potentially slow for large datasets

*Implementation*

```java
public static int linearSearch(int[] list, int key) {
    for(int i = 0; i < list.length; i++){
        if(key == list[i]){
            return i; // Return index if found
        }
    }

    return -1; // Return -1 if not found
}
```
*Example Usage*

```java
int[] list = {1, 4, 4, 2, 5, -3, 6, 2};
int i = linearSearch(list, 4); // Return 1
int j = linearSearch(list, -4); // Return -1
int l = linearSearch(list, -3); // Return 5
```

### Time Complexity
- Best Case O(1): Element found at first position
- Nomarl Case O(n): Element found at middle/last position/not found
- Space Complexity: O(1)

## Binary Search
> ⚠️ Element must be sorted before applying binary search

- Compares key with middle element
- Eleminates half of the search space in each iteration
- Much more efficient than linear search for large datasets

**Three Cases**
1. key < middle: Search first half
2. key = middle: Match found
3. key > middle: Search second half

```java
public static int binarySearch(int[] list, int key){
    int low = 0;
    int high = list.length - 1;

    while(high >= low){
        int mid = (low + high) / 2;

        if(key < list[mid]){
            high = mid - 1; // Search left half
        }else if(key == list[mid]){
            return mid; // Found
        }else{
            low = mid + 1; // Search right half
        }
    }

    return -low - 1; // low = not found + where to insert
}
```
### Time Complexity
- All cases: O(log n)
- Space Complexity: O(1)

---

## Sort Algorithm
**Definition**: Process of arranging elements in a particular order (ascending/descending)
**Importance**: Many algorithms work more efficiently on sorted data

Four Main Algorithms Covered:
1. Selection Sort - Find minimum, place in position
2. Insertion Sort - Insert each element in correct position
3. Bubble Sort - Compare adjacent elements, swap if needed
4. Merge Sort - Divide and conquer with merging

## Selection Sort
- Find smallest element and place it first
- Find second smallest and place it second
- Continue until only one element remains

*Implementation*

```java
public static void selectionSort(double[] list){

    for(int i = 0; i < list.length - 1; i++){
        double currentMin = list[i];
        int currentMinIndex = i;

        for(int j = i + 1; j < list.length; j++){
            if(currentMin > list[j]){
                currentMin = list[j];
                currentMinIndex = j;
            }
        }

        // Swap if necessary
        if(currentMinIndex != i){
            list[currentMinIndex] = list[i];
            list[i] = currentMin;
        }
    }
}
```
**Time Complexity**: O(n^2)
**Space Complexity** O(1)

## Insertion Sort
- Start with second element (first is "sorted")
- Insert each element into correct position in sorted portion

*Implementation*
```java
public static void insertionSort(double[] list) {
    for (int i = 1; i < list.length; i++) {
        double currentElement = list[i];
        int k;
        
        // Shift elements to the right
        for (k = i - 1; k >= 0 && list[k] > currentElement; k--) {
            list[k + 1] = list[k];
        }
        
        // Insert current element
        list[k + 1] = currentElement;
    }
}
```
**Time Complexity**: O(n^2)
**Space Complexity**: O(1)

## Bubble Sort
- Swap if they're in wrong order

*Implementation*

```java
public static void bubbleSort(double[] list) {
    boolean needNextPass = true;
    
    for (int k = 1; k < list.length && needNextPass; k++) {
        needNextPass = false;  // Assume array is sorted
        
        for (int i = 0; i < list.length - k; i++) {
            if (list[i] > list[i + 1]) {
                // Swap elements
                double temp = list[i];
                list[i] = list[i + 1];
                list[i + 1] = temp;
                
                needNextPass = true;  // Still need more passes
            }
        }
    }
}
```
**Time Complexity**: O(n^2)
**Space Complexity**: O(1)

## Merge Sort
- Divide and Conquer approach
- Recursively divide array into halves
- Sort each half separately
- Merge sorted halves back together

*Recursive Structure*

```bash
mergeSort(list):
    if list.length > 1:
        firstHalf = mergeSort(firstHalf)
        secondHalf = mergeSort(secondHalf)  
        list = merge(firstHalf, secondHalf)
```

*Implementation*

```java
public static void mergeSort(int[] list) {
    if (list.length > 1) {
        // Create and sort first half
        int[] firstHalf = new int[list.length / 2];
        System.arraycopy(list, 0, firstHalf, 0, list.length / 2);
        mergeSort(firstHalf);
        
        // Create and sort second half
        int secondHalfLength = list.length - list.length / 2;
        int[] secondHalf = new int[secondHalfLength];
        System.arraycopy(list, list.length / 2, secondHalf, 0, secondHalfLength);
        mergeSort(secondHalf);
        
        // Merge both halves
        merge(firstHalf, secondHalf, list);
    }
}

public static void merge(int[] list1, int[] list2, int[] temp) {
    int current1 = 0, current2 = 0, current3 = 0;
    
    // Compare and merge
    while (current1 < list1.length && current2 < list2.length) {
        if (list1[current1] < list2[current2])
            temp[current3++] = list1[current1++];
        else
            temp[current3++] = list2[current2++];
    }
    
    // Copy remaining elements
    while (current1 < list1.length)
        temp[current3++] = list1[current1++];
    while (current2 < list2.length)
        temp[current3++] = list2[current2++];
}
```

**Time Complexity**: O(n log n) *Work best than other sort methods
**Space Complexity**: O(n) *Use more space than other sort methods

## Generic Sorting

**Why Generic Methods?**
- Code Reusability: One method for all comparable types
- Type Safety: Compile-time type checking
- Flexibility: Works with custom objects

```java
public static <E extends Comparable<E>> void selectionSort(E[] list) {
    for (int i = 0; i < list.length - 1; i++) {
        E currentMin = list[i];
        int currentMinIndex = i;
        
        for (int j = i + 1; j < list.length; j++) {
            if (currentMin.compareTo(list[j]) > 0) {
                currentMin = list[j];
                currentMinIndex = j;
            }
        }
        
        if (currentMinIndex != i) {
            list[currentMinIndex] = list[i];
            list[i] = currentMin;
        }
    }
}
```

**Understanding Generic Syntax**
```java
<E extends Comparable<E>>
 │        │              │
 │        │              └─ E must be comparable to itself
 │        └─ Must implement Comparable interface  
 └─ Generic type parameter E
```

**Example Usage**
```java
// String array
String[] names = {"John", "Alice", "Bob"};
selectionSort(names);  // Result: ["Alice", "Bob", "John"]

// Integer array
Integer[] numbers = {64, 25, 12, 22, 11};
selectionSort(numbers);  // Result: [11, 12, 22, 25, 64]
```

**Custom Class Example**
```java
public class Person implements Comparable<Person> {
    private String name;
    private int age;
    
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    @Override
    public int compareTo(Person other) {
        return Integer.compare(this.age, other.age);  // Sort by age
    }
    
    @Override
    public String toString() {
        return name + "(" + age + ")";
    }
}
```

---

## Complete Comparison Table

| Algorithm       | Best Case  | Average Case | Worst Case  | Space  | Stable  |
|-----------------|------------|--------------|-------------|--------|---------|
| **Linear Search**  | O(1)      | O(n)        | O(n)       | O(1)   | N/A     |
| **Binary Search**  | O(1)      | O(log n)    | O(log n)   | O(1)   | N/A     |
| **Selection Sort** | O(n²)     | O(n²)       | O(n²)      | O(1)   | No      |
| **Insertion Sort** | O(n)      | O(n²)       | O(n²)      | O(1)   | Yes     |
| **Bubble Sort**    | O(n)      | O(n²)       | O(n²)      | O(1)   | Yes     |
| **Merge Sort**     | O(n log n)| O(n log n)  | O(n log n) | O(n)   | Yes     |


## 📌 When to Use Each Algorithm:

### 🔍 **Search Algorithms:**
- **Linear Search**: Small/unsorted data, simplicity needed
- **Binary Search**: Large **sorted** data, frequent searches

### 🔄 **Sort Algorithms:**
- **Selection Sort**: Very small datasets (**n < 20**), educational
- **Insertion Sort**: Small/nearly sorted data (**n < 50**)
- **Bubble Sort**: Educational purposes only
- **Merge Sort**: Large datasets, stability required, consistent performance
