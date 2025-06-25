/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Queue;

import java.util.Arrays;

/**
 *
 * @author hoo
 * @param <E>
 */
public class ArrayQueue<E> { //Declare one type parameter

    private static final int DEFAULT_INITIAL_SIZE = 8;  // default size for the "array" created
    
    private E[] array;          // Creates an array "array" to store the data with designated type parameter
    private int arraySize;      // Initial size for "array" // private int queueSize;
    private int size;           // current size of the "array". Can be used as index to add at the last of the array as well. (enqueue)
    
    public ArrayQueue() { //constructor
        this(DEFAULT_INITIAL_SIZE);                     // Called the constructor ArrayQueue(initial) by passing in default initial value.                 
        // this.array = (E[]) new Object[arraySize];    // Not needed, redundant with line 31
        // this.size = 0;                               // Not needed, redundant with line 32
    }

    public ArrayQueue(int initial) { //constructor with initial size provided
        this.arraySize = initial;                       // assign initial created size for "array"    
        this.array = (E[]) new Object[arraySize];       // create new object and cast to the type parameter E.
        this.size = 0;                                  // initialize size to be 0. This also means index to enqueue at the beginning is 0 too. 
    }

    public void enqueue(E e) {      // Insert element at the end of the array. Similar to addLast() concept
        if (size < array.length) {  // Current index (obtained from size variable) is smaller than the "array" size, to make sure the index is acceptable by the array
            array[size] = e;        // Similar to this.array[this.queueSize] = e;
            size++;                 // Increase the current size with elements
        } else {
            System.out.println("Array is full"); //Assuming index is full, and we no longer accept any new elements. 
            
            //What if we want to make sure the "array" is not restricted by the initial size?
            /*
            TODO
            hint: how can we increase the size of an array?
            */
        }

        // Doesn't need to loop as array can access to the index to insert directly. Technically not wrong.  
        /* for loop to check every index of array
        for (int i = 0; i < queueSize; i++) {
            if (array[i] == null) {
                array[i] = e;
                size++;
                break;
            }
        }
        */
    }

    public E dequeue() {    //remove the first element in the "array". Similar to removeFirst()
        
        // Do we need to check when size = 0?
        /*
            TODO
        */
        
        E temp = array[0];  //store element that needs to be removed
        
        //move one unit index in front for every existing element after removing the first element 
        for (int i = 0; i < array.length - 1; i++) {    // check elements
            if (array[i + 1] != null) {                 // there is an element to move
                array[i] = array[i + 1];                // element in index i+1 assigned to index i
            } else {                                    // no more element to move
                array[i] = null;                        // set the element of current index to null, as the element has been copied to the previous index
                break;                                  // break the loop since you expect the rest of the element to be null as well
            }
        }
        if (size > 0) { // Will we have this scenario?
            size--;     // Reduce size by one since one element is removed, and the new index for enqueue should be updated. 
        }
        return temp;    // Return the removed element. 

        
        /* Another example. What is the difference?
        E temp;
        if (size == 0) {
            System.out.println("array is empty");
        } else {
            temp = array[0];
            for (int i = 1; i < size; i++) {
                array[i - 1] = array[i];        // Anything wrong here? Do we need line 73?
                size--;
            }
        }
        return temp;
        */

        /* Another example. What is the difference? Anything wrong?
        E e = array[0];
        for(int i = 0; i<size-1; i++){
            array[i] = array[i+1];
        }

        array[size-1] = null;
        size--;

        return e;
        */
    }

    public E getElement() {     //return the 1st element of "array". Do not remove.
        return array[0];        // return element at index 0, as the beginning of the queue
    }

    public boolean isEmpty() {  // Check if the "array" is empty
        return size == 0;       // If size is 0, return true. 
    }

    public int size() {         // Return the size of the "array" with element
        return size;            // Return "size", but not "arraySize"
    }

    public void resize() {
        
        /*
        Do we really need this part? Or we can use "size" instead?
        Assuming array of [1,2,3,4,5,null,null,null], size = 5, index calculated is also 5. 
        */
        int index = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == null) {
                index = i;
                break;
            }
        }

        // Will array.clone() possibly throw an error if we didnt implement cloneable? 
        // Yes. https://stackoverflow.com/questions/8192223/object-cloning-with-out-implementing-cloneable-interface
        E[] copy = array.clone(); // save a copy of the array first

        array = (E[]) new Object[index];    // Create new array with the size of the elements. *Why do we need + 1 here? // array = (E[]) new Object[index + 1]; what happen if we keep + 1?
        for (int i = 0; i < array.length; i++) {
            array[i] = copy[i];             // Reassign elements back to "array" from "copy"
        }
    }
    
    @Override
    public String toString() {  // What is the difference with toStringWithNull()?
        // return "Queue: " + Arrays.toString(array); // Whats wrong with this?
        String out = "| ";
        for (int i = size- 1; i >= 0; i--)
            out += array[i] + " | ";
        return out;
        
        //If another way round?
//        String out = "";
//        for (int i = 0; i < size; i++)
//            out += " | " + array[i];
//        out += "| ";
//        return out;
        
    }
    
    public String toStringWithNull() {
        String out = "| ";
        for (int i = array.length- 1; i >= 0; i--)
            out += array[i] + " | ";
        return out;
        
        //If another way round?
//        String out = "";
//        for (int i = 0; i < array.length; i++)
//            out += " | " + array[i];
//        out += "| ";
//        return out;
    }

    // Do we really need arraySize here?
}