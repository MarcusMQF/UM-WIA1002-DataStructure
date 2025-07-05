# Recursion

**Recursion is a programming technique where a method calls itself to fulfill its overall purpose. It's also known as self-invocation.**

## Characteristics of Recursion
All recursive methods must have these two essential components:

1. **Base Case(s):**
   - The simplest scenarios where the problem can be solved directly
   - Serves as the stopping condition to prevent infinite recursion
   - Critical: Without proper base cases, you get StackOverflowError

2. **Recursive Case:**
   - The method calls itself to solve a smaller version of the original problem
   - Each recursive call should move the problem closer to the base case
   - Must make progress toward the base case

## Implementation

### Factorial
```java
public static long factorial(int n) {
    // Base case
    if (n == 0) {
        return 1;
    }
    // Recursive case
    return n * factorial(n - 1);
}
```

**Execution Trace for factorial(4):**
```
factorial(4) = 4 * factorial(3)
             = 4 * (3 * factorial(2))
             = 4 * (3 * (2 * factorial(1)))
             = 4 * (3 * (2 * (1 * factorial(0))))
             = 4 * (3 * (2 * (1 * 1)))
             = 4 * (3 * (2 * 1))
             = 4 * (3 * 2)
             = 4 * 6
             = 24
```

**Stack Frame Analysis:**

When factorial(4) is called:
1. factorial(4) is pushed onto stack
2. factorial(3) is pushed onto stack
3. factorial(2) is pushed onto stack
4. factorial(1) is pushed onto stack
5. factorial(0) is pushed onto stack (base case reached)
6. Stack frames are popped off one by one as values are returned

### Recursion vs Iteration

**Factorial: Iterative Implementation**
```java
public static long factorialIterative(int n) {
    long result = 1;
    while (n > 0) {
        result *= n;
        n--;
    }
    return result;
}
```

**Comparison Table:**

| Aspect | Recursion | Iteration |
|--------|-----------|-----------|
| Termination | Base case reached | Condition becomes false |
| Memory Usage | Extra stack frame space | No extra memory per iteration |
| Infinite Cases | Stack overflow | Infinite loop |
| Code Complexity | Often simpler for recursive problems | May be more complex |
| Performance | Generally slower due to overhead | Generally faster |

**When to Use Recursion:**
- Problems that are naturally recursive (tree traversal, fractals)
- When the recursive solution is significantly simpler
- When working with recursive data structures

**When to Use Iteration:**
- When performance is critical
- When memory usage is a concern
- For simple repetitive tasks

## Problem Solving with Recursion

### Example 1: Print Message N Times
```java
public static void nPrintln(String message, int times) {
    if (times >= 1) {  // Base case: times == 0
        System.out.println(message);
        nPrintln(message, times - 1);  // Recursive case
    }
}

// Usage
nPrintln("Welcome", 5);
```

### Example 2: Directory Size Calculation
```java
public static long directorySize(File directory) {
    long size = 0;
    
    // Base case: if it's a file, return its size
    if (directory.isFile()) {
        return directory.length();
    }
    
    // Recursive case: sum sizes of all contents
    File[] files = directory.listFiles();
    if (files != null) {
        for (File file : files) {
            size += directorySize(file);  // Recursive call
        }
    }
    
    return size;
}
```

### Example 3: Power Function
```java
public static double power(double base, int exponent) {
    // Base case
    if (exponent == 0) {
        return 1;
    }
    
    // Handle negative exponents
    if (exponent < 0) {
        return 1 / power(base, -exponent);
    }
    
    // Recursive case
    return base * power(base, exponent - 1);
}
```

## Common Pitfalls

### 1. Missing Base Case
```java
// WRONG - No base case
public static long factorial(int n) {
    return n * factorial(n - 1);  // StackOverflowError!
}
```

### 2. Incorrect Base Case
```java
// WRONG - Base case never reached for positive n
public static long factorial(int n) {
    if (n == 1) return 1;  // What about n = 0?
    return n * factorial(n - 1);
}
```

### 3. Not Making Progress
```java
// WRONG - Infinite recursion
public static void badRecursion(int n) {
    if (n == 0) return;
    badRecursion(n);  // n never changes!
}
```

## Best Practices

### 1. Always Define Clear Base Cases
```java
public static int gcd(int a, int b) {
    // Clear base case
    if (b == 0) {
        return a;
    }
    // Recursive case that makes progress
    return gcd(b, a % b);
}
```

### 2. Ensure Progress Toward Base Case
```java
public static void countdown(int n) {
    if (n <= 0) {  // Base case
        System.out.println("Done!");
        return;
    }
    System.out.println(n);
    countdown(n - 1);  // Progress: n decreases
}
```

### 3. Consider Memoization for Optimization
```java
// Optimized Fibonacci with memoization
public static long fibonacciMemo(int n, Map<Integer, Long> memo) {
    if (n == 0) return 0;
    if (n == 1) return 1;
    
    if (memo.containsKey(n)) {
        return memo.get(n);
    }
    
    long result = fibonacciMemo(n - 1, memo) + fibonacciMemo(n - 2, memo);
    memo.put(n, result);
    return result;
}
```

### 4. Handle Edge Cases
```java
public static long factorial(int n) {
    // Handle negative input
    if (n < 0) {
        throw new IllegalArgumentException("Factorial is not defined for negative numbers");
    }
    
    // Base case
    if (n == 0 || n == 1) {
        return 1;
    }
    
    // Recursive case
    return n * factorial(n - 1);
}
```

## Practice Problems

### 1. Sum of Natural Numbers
```java
public static int sum(int n) {
    if (n <= 0) return 0;
    return n + sum(n - 1);
}
```

### 2. Reverse a String
```java
public static String reverse(String str) {
    if (str.length() <= 1) return str;
    return reverse(str.substring(1)) + str.charAt(0);
}
```

### 3. Check if String is Palindrome
```java
public static boolean isPalindrome(String str, int start, int end) {
    if (start >= end) return true;
    if (str.charAt(start) != str.charAt(end)) return false;
    return isPalindrome(str, start + 1, end - 1);
}
```


