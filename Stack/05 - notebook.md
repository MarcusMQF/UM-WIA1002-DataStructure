# Stack Data Structure

## 📚 Overview
A **stack** is a fundamental data structure that follows the **Last-In, First-Out (LIFO)** principle. Think of it like a stack of plates - you can only add or remove plates from the top!

## 🔍 Key Characteristics
- **LIFO Behavior**: The last element added is the first one to be removed
- **Restricted Access**: Elements can only be accessed from one end (the top)
- **Special Type of List**: A stack is essentially a list with restricted operations

## 🛠️ Core Operations

### Primary Operations
1. **Push()** - Add an element to the top of the stack
2. **Pop()** - Remove and return the top element
3. **Peek()/Top()** - View the top element without removing it

### Additional Operations
- **isEmpty()** - Check if the stack is empty
- **size()** - Get the number of elements in the stack

## 📖 Visual Example

```bash
Initial Stack: []

Push A    →  [A]
Push B    →  [A, B]
Push C    →  [A, B, C]
Pop C     →  [A, B]     (returns C)
Pop B     →  [A]       (returns B)
Push D    →  [A, D]
```

## 💻 Implementation in Java

### Using ArrayList with Composition Approach

```java
import java.util.ArrayList;
import java.util.EmptyStackException;

public class GenericStack<E> {
    private ArrayList<E> list = new ArrayList<>();
    
    // Get the size of the stack
    public int getSize() {
        return list.size();
    }
    
    // Check if stack is empty
    public boolean isEmpty() {
        return list.isEmpty();
    }
    
    // Add element to top of stack
    public void push(E element) {
        list.add(element);
    }
    
    // Remove and return top element
    public E pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return list.remove(list.size() - 1);
    }
    
    // View top element without removing
    public E peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return list.get(list.size() - 1);
    }
    
    @Override
    public String toString() {
        return list.toString();
    }
}
```

## 🧪 Test Example

```java
public class TestStack {
    public static void main(String[] args) {
        GenericStack<String> stack = new GenericStack<>();
        
        // Push elements
        stack.push("Tom");
        System.out.println("(1) stack: " + stack);  // [Tom]
        
        stack.push("Susan");
        System.out.println("(2) stack: " + stack);  // [Tom, Susan]
        
        stack.push("Kim");
        stack.push("Michael");
        System.out.println("(3) stack: " + stack);  // [Tom, Susan, Kim, Michael]
        
        // Pop elements
        System.out.println("(4) " + stack.pop());   // Michael
        System.out.println("(5) " + stack.pop());   // Kim
        System.out.println("(6) stack: " + stack);  // [Tom, Susan]
    }
}
```

## 🔢 Postfix Expression Evaluation

### What is Postfix Notation?
- Also called **Reverse Polish Notation (RPN)**
- Operators come **after** their operands
- **Advantages**: No need for parentheses or precedence rules

### Examples of Postfix Conversion

| Infix Expression | Postfix Expression | Explanation |
|------------------|-------------------|-------------|
| `a + b * c`      | `a b c * +`       | Multiplication first, then addition |
| `(a + b) * c`    | `a b + c *`       | Addition in parentheses first |
| `(a*b + c) / d + e` | `a b * c + d / e +` | Multiple operations in sequence |

### Postfix Evaluation Algorithm

```java
public static int evaluatePostfix(String expression) {
    Stack<Integer> stack = new Stack<>();
    String[] tokens = expression.split(" ");
    
    for (String token : tokens) {
        if (isOperator(token)) {
            // Pop two operands (order matters!)
            int operand2 = stack.pop();  // Second operand
            int operand1 = stack.pop();  // First operand
            
            int result = performOperation(operand1, operand2, token);
            stack.push(result);
        } else {
            // It's an operand, push to stack
            stack.push(Integer.parseInt(token));
        }
    }
    
    return stack.pop();  // Final result
}

private static boolean isOperator(String token) {
    return token.equals("+") || token.equals("-") || 
           token.equals("*") || token.equals("/");
}

private static int performOperation(int a, int b, String operator) {
    switch (operator) {
        case "+": return a + b;
        case "-": return a - b;
        case "*": return a * b;
        case "/": return a / b;
        default: throw new IllegalArgumentException("Invalid operator");
    }
}
```

### Step-by-Step Example: "4 3 5 * +"

Expression: `"4 3 5 * +"`

```
Step 1: Read "4" → Push 4        Stack: [4]
Step 2: Read "3" → Push 3        Stack: [4, 3]
Step 3: Read "5" → Push 5        Stack: [4, 3, 5]
Step 4: Read "*" → Pop 5,3       Stack: [4]
        Calculate: 3 * 5 = 15    Push 15: [4, 15]
Step 5: Read "+" → Pop 15,4      Stack: []
        Calculate: 4 + 15 = 19   Push 19: [19]

Result: 19
```

## ⚠️ Error Handling in Postfix Evaluation

### Too Many Operators
```
Expression: "3 8 + * 9"
Problem: The "*" operator needs two operands but stack has only one
```

### Too Many Operands
```
Expression: "9 8 + 7"
Problem: After evaluation, stack contains more than one element
Expected: [17] but got: [17, 7]
```

## 🏗️ Implementation Strategies

### 1. Inheritance Approach
```java
public class GenericStack<E> extends ArrayList<E> {
    // Inherit all ArrayList methods
    // Add stack-specific methods
}
```

### 2. Composition Approach (Recommended)
```java
public class GenericStack<E> {
    private ArrayList<E> list = new ArrayList<>();
    // Define only stack-specific methods
    // Better encapsulation
}
```

## 🎯 Key Advantages of Stacks

- **Simple Operations**: Only three main operations to master
- **Efficient**: O(1) time complexity for all operations
- **Memory Efficient**: Uses ArrayList internally
- **Versatile**: Perfect for expression evaluation, undo operations, etc.

## 📝 Best Practices

- Always check for empty stack before `pop()` or `peek()`
- Use generics for type safety
- Handle exceptions appropriately
- Choose composition over inheritance for better encapsulation

## 🔧 Advanced Applications

### 1. Balanced Parentheses Checker

```java
public static boolean isBalanced(String expression) {
    Stack<Character> stack = new Stack<>();
    
    for (char ch : expression.toCharArray()) {
        // Push opening brackets
        if (ch == '(' || ch == '[' || ch == '{') {
            stack.push(ch);
        }
        // Check closing brackets
        else if (ch == ')' || ch == ']' || ch == '}') {
            if (stack.isEmpty()) return false;
            
            char top = stack.pop();
            if (!isMatchingPair(top, ch)) {
                return false;
            }
        }
    }
    
    return stack.isEmpty(); // All brackets should be matched
}

private static boolean isMatchingPair(char open, char close) {
    return (open == '(' && close == ')') ||
           (open == '[' && close == ']') ||
           (open == '{' && close == '}');
}
```

### 2. Infix to Postfix Conversion

```java
public static String infixToPostfix(String infix) {
    Stack<Character> stack = new Stack<>();
    StringBuilder postfix = new StringBuilder();
    
    for (char ch : infix.toCharArray()) {
        if (Character.isLetterOrDigit(ch)) {
            postfix.append(ch).append(" ");
        }
        else if (ch == '(') {
            stack.push(ch);
        }
        else if (ch == ')') {
            while (!stack.isEmpty() && stack.peek() != '(') {
                postfix.append(stack.pop()).append(" ");
            }
            stack.pop(); // Remove the '('
        }
        else if (isOperator(ch)) {
            while (!stack.isEmpty() && precedence(ch) <= precedence(stack.peek())) {
                postfix.append(stack.pop()).append(" ");
            }
            stack.push(ch);
        }
    }
    
    // Pop remaining operators
    while (!stack.isEmpty()) {
        postfix.append(stack.pop()).append(" ");
    }
    
    return postfix.toString().trim();
}

private static int precedence(char operator) {
    switch (operator) {
        case '+': case '-': return 1;
        case '*': case '/': return 2;
        case '^': return 3;
        default: return 0;
    }
}
```

### 3. Function Call Stack Simulation

```java
public class CallStack {
    private Stack<String> callStack = new Stack<>();
    
    public void enterFunction(String functionName) {
        callStack.push(functionName);
        System.out.println("Entering: " + functionName);
        System.out.println("Call Stack: " + callStack);
    }
    
    public void exitFunction() {
        if (!callStack.isEmpty()) {
            String function = callStack.pop();
            System.out.println("Exiting: " + function);
            System.out.println("Call Stack: " + callStack);
        }
    }
    
    public void showCurrentStack() {
        System.out.println("Current Call Stack: " + callStack);
    }
}
```

## 🌟 Real-World Applications

### 1. **Browser History**
- Back button functionality
- Each visited page is pushed onto the stack
- Back button pops the most recent page

### 2. **Undo Operations**
- Text editors (Ctrl+Z)
- Each action is pushed onto the stack
- Undo pops the most recent action

### 3. **Compiler Design**
- Syntax parsing
- Expression evaluation
- Symbol table management

### 4. **Memory Management**
- Function call management
- Local variable storage
- Return address tracking

## 📊 Time Complexity Analysis

| Operation | Time Complexity | Space Complexity |
|-----------|----------------|------------------|
| Push      | O(1)           | O(1)             |
| Pop       | O(1)           | O(1)             |
| Peek      | O(1)           | O(1)             |
| isEmpty   | O(1)           | O(1)             |
| Size      | O(1)           | O(1)             |

## 🔍 Common Interview Questions

### 1. **Implement Stack using Arrays**
```java
public class ArrayStack<T> {
    private T[] array;
    private int top;
    private int capacity;
    
    @SuppressWarnings("unchecked")
    public ArrayStack(int size) {
        capacity = size;
        array = (T[]) new Object[capacity];
        top = -1;
    }
    
    public void push(T item) {
        if (top == capacity - 1) {
            throw new StackOverflowError("Stack is full");
        }
        array[++top] = item;
    }
    
    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        T item = array[top];
        array[top--] = null; // Avoid memory leak
        return item;
    }
    
    public boolean isEmpty() {
        return top == -1;
    }
}
```

### 2. **Min Stack Implementation**
```java
public class MinStack {
    private Stack<Integer> stack = new Stack<>();
    private Stack<Integer> minStack = new Stack<>();
    
    public void push(int val) {
        stack.push(val);
        if (minStack.isEmpty() || val <= minStack.peek()) {
            minStack.push(val);
        }
    }
    
    public void pop() {
        if (stack.peek().equals(minStack.peek())) {
            minStack.pop();
        }
        stack.pop();
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int getMin() {
        return minStack.peek();
    }
}
```

## 🎓 Summary

Stacks are fundamental data structures that provide:
- **LIFO access pattern**
- **Efficient O(1) operations**
- **Wide range of applications**
- **Essential for expression evaluation**
- **Critical in system programming**

Understanding stacks is crucial for:
- Algorithm design
- System programming
- Compiler construction
- Problem-solving in interviews

---

*Remember: Practice implementing stacks in different ways and solving various stack-based problems to master this important data structure!*
