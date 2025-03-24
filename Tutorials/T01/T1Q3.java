/*
output: AB

1. The main method in class C creates an array of Object type named o and initializes it with two elements: an instance of class A and an instance of class B.

2. The toString method is overridden in both classes A and B. In class A, it returns "A", and in class B, it returns "B".

3. When System.out.print(o[0]) is called, it prints the result of the toString method of the first element in the array, which is an instance of A. Therefore, it prints "A".

4. When System.out.print(o[1]) is called, it prints the result of the toString method of the second element in the array, which is an instance of B. Therefore, it prints "B".

Combining these two prints, the output is "AB".
*/
