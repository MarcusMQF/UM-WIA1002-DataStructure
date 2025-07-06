# Java Generics 

## Table of Contents
1. [Introduction to Generics](#introduction-to-generics)
2. [ArrayList with Generics](#arraylist-with-generics)
3. [Generic Classes and Interfaces](#generic-classes-and-interfaces)
4. [Generic Methods](#generic-methods)
5. [Bounded Generic Types](#bounded-generic-types)
6. [Raw Types and Backward Compatibility](#raw-types-and-backward-compatibility)
7. [Wildcard Generic Types](#wildcard-generic-types)
8. [Type Erasure](#type-erasure)
9. [Restrictions on Generics](#restrictions-on-generics)
10. [Practical Examples and Best Practices](#practical-examples-and-best-practices)

---

## Introduction to Generics

### What are Generics?
Generics are the capability to parameterize types in Java. They allow you to define classes, interfaces, and methods with generic types that can be substituted with concrete types by the compiler.

### Key Benefits:
- **Compile-time type safety**: Catch type mismatches at compile time
- **Elimination of casting**: No need for explicit type casting
- **Code reusability**: Write generic code that works with different types
- **Better code readability**: Clear indication of what types are expected

### Before Generics (Pre-Java 5):
```java
// Without generics - prone to runtime errors
ArrayList list = new ArrayList();
list.add("Hello");
list.add(123);  // This compiles but may cause issues later

// Requires casting and can cause ClassCastException
String s = (String) list.get(0);  // OK
String s2 = (String) list.get(1); // Runtime error!
```

### With Generics (Java 5+):
```java
// With generics - type-safe
ArrayList<String> list = new ArrayList<String>();
list.add("Hello");
// list.add(123);  // Compile error - won't compile!

// No casting needed
String s = list.get(0);  // No casting required
```

### Generic Type Parameters Naming Convention:
| Parameter | Meaning | Example |
|-----------|---------|---------|
| E | Element | List<E>, ArrayList<E> |
| K | Key | Map<K,V> |
| V | Value | Map<K,V> |
| N | Number | Calculator<N> |
| T | Type | GenericClass<T> |
| S, U, V | Additional types | Triple<S,U,V> |

---

## ArrayList with Generics

### Creating Generic ArrayLists:
```java
// Full syntax
ArrayList<String> cities = new ArrayList<String>();

// Diamond operator (Java 7+)
ArrayList<String> cities = new ArrayList<>();

// Common examples
ArrayList<Integer> numbers = new ArrayList<>();
ArrayList<Double> prices = new ArrayList<>();
ArrayList<MyClass> objects = new ArrayList<>();
```

### Important Rules:
**Only reference types allowed**: Cannot use primitive types
Must use wrapper classes for primitives

```java
// ✅ Correct
ArrayList<Integer> numbers = new ArrayList<>();  // Use Integer
ArrayList<Double> doubles = new ArrayList<>();   // Use Double
ArrayList<Character> chars = new ArrayList<>();  // Use Character

// ❌ Incorrect - Compile error
ArrayList<int> numbers = new ArrayList<>();      // Cannot use int
ArrayList<double> doubles = new ArrayList<>();   // Cannot use double
ArrayList<char> chars = new ArrayList<>();       // Cannot use char
```

### No Casting Required:
```java
// Without generics - requires casting
ArrayList list = new ArrayList();
list.add("hello");
String s = (String) list.get(0);  // Casting needed

// With generics - no casting
ArrayList<String> list = new ArrayList<>();
list.add("hello");
String s = list.get(0);  // No casting needed
```

### Array vs ArrayList Comparison:
| Feature | Array | ArrayList |
|---------|-------|-----------|
| Size | Fixed | Dynamic |
| Type Safety | Limited | Strong (with generics) |
| Performance | Faster | Slightly slower |
| Memory | Less overhead | More overhead |
| Syntax | String[] | ArrayList<String> |

---

## Generic Classes and Interfaces

### Generic Class Declaration:
```java
public class GenericClass<T> {
    private T data;
    
    public GenericClass(T data) {
        this.data = data;
    }
    
    public T getData() {
        return data;
    }
    
    public void setData(T data) {
        this.data = data;
    }
}
```

### Generic Interface Declaration:
```java
public interface GenericInterface<T> {
    void process(T item);
    T getResult();
}
```

### Multiple Generic Parameters:
```java
public class Pair<T, U> {
    private T first;
    private U second;
    
    public Pair(T first, U second) {
        this.first = first;
        this.second = second;
    }
    
    public T getFirst() { return first; }
    public U getSecond() { return second; }
}

// Usage
Pair<String, Integer> nameAge = new Pair<>("John", 25);
Pair<Integer, Double> idScore = new Pair<>(123, 95.5);
```

### Generic Stack Example:
```java
public class GenericStack<E> {
    private java.util.ArrayList<E> list = new java.util.ArrayList<>();
    
    public void push(E item) {
        list.add(item);
    }
    
    public E pop() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }
        return list.remove(list.size() - 1);
    }
    
    public E peek() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty");
        }
        return list.get(list.size() - 1);
    }
    
    public boolean isEmpty() {
        return list.isEmpty();
    }
    
    public int size() {
        return list.size();
    }
}

// Usage
GenericStack<String> stringStack = new GenericStack<>();
stringStack.push("Hello");
stringStack.push("World");
System.out.println(stringStack.pop()); // "World"

GenericStack<Integer> intStack = new GenericStack<>();
intStack.push(10);
intStack.push(20);
System.out.println(intStack.pop()); // 20
```

### Generic Box Example:
```java
public class GenericBox<T> {
    private T content;
    
    public void set(T content) {
        this.content = content;
    }
    
    public T get() {
        return content;
    }
    
    public boolean isEmpty() {
        return content == null;
    }
}

// Usage
GenericBox<String> stringBox = new GenericBox<>();
stringBox.set("Hello Generic");
String message = stringBox.get();

GenericBox<Integer> intBox = new GenericBox<>();
intBox.set(42);
Integer number = intBox.get();
```

### Important Points:
- **Constructor is not generic**: `public GenericStack()` not `public GenericStack<E>()`
- **Multiple parameters allowed**: `<E1, E2, E3>`
- **Inheritance with generics**: `public class StringList extends ArrayList<String>`

---

## Generic Methods

### Generic Method Declaration:
```java
public class GenericMethodExample {
    
    // Generic static method
    public static <E> void printArray(E[] array) {
        for (E element : array) {
            System.out.print(element + " ");
        }
        System.out.println();
    }
    
    // Generic instance method
    public <T> boolean isEqual(T obj1, T obj2) {
        return obj1.equals(obj2);
    }
    
    // Generic method with return type
    public static <T> T getMiddle(T[] array) {
        return array[array.length / 2];
    }
}
```

### Generic Method Usage:
```java
// Method invocation with explicit type
GenericMethodExample.<String>printArray(new String[]{"A", "B", "C"});

// Method invocation with type inference
String[] names = {"Alice", "Bob", "Charlie"};
GenericMethodExample.printArray(names);  // Type inferred as String

Integer[] numbers = {1, 2, 3, 4, 5};
GenericMethodExample.printArray(numbers);  // Type inferred as Integer

// Using generic method with return type
String middle = GenericMethodExample.getMiddle(names);
Integer midNum = GenericMethodExample.getMiddle(numbers);
```

### Multiple Generic Parameters in Methods:
```java
public class UtilityMethods {
    
    public static <T, U> void printPair(T first, U second) {
        System.out.println("First: " + first + ", Second: " + second);
    }
    
    public static <T> void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}

// Usage
UtilityMethods.printPair("Name", 25);
UtilityMethods.printPair(100, 200.5);

String[] words = {"hello", "world", "java"};
UtilityMethods.swap(words, 0, 2);  // Swaps "hello" and "java"
```

---

## Bounded Generic Types

### Upper Bounded Wildcards:
```java
// T must be a subtype of Number
public class NumberContainer<T extends Number> {
    private T value;
    
    public NumberContainer(T value) {
        this.value = value;
    }
    
    public double getDoubleValue() {
        return value.doubleValue();  // Can call Number methods
    }
    
    public boolean isPositive() {
        return value.doubleValue() > 0;
    }
}

// Usage
NumberContainer<Integer> intContainer = new NumberContainer<>(42);
NumberContainer<Double> doubleContainer = new NumberContainer<>(3.14);
// NumberContainer<String> stringContainer = new NumberContainer<>("hello"); // Compile error!
```

### Bounded Generic Methods:
```java
public class MathUtils {
    
    // Method that only accepts Number subtypes
    public static <T extends Number> double sum(T[] array) {
        double total = 0;
        for (T element : array) {
            total += element.doubleValue();
        }
        return total;
    }
    
    // Multiple bounds
    public static <T extends Number & Comparable<T>> T findMax(T[] array) {
        if (array.length == 0) return null;
        
        T max = array[0];
        for (T element : array) {
            if (element.compareTo(max) > 0) {
                max = element;
            }
        }
        return max;
    }
}

// Usage
Integer[] numbers = {1, 5, 3, 9, 2};
double sum = MathUtils.sum(numbers);  // 20.0
Integer max = MathUtils.findMax(numbers);  // 9
```

### Bounded Type Examples:
```java
// Geometric shapes example
public abstract class GeometricObject {
    protected String color;
    
    public GeometricObject(String color) {
        this.color = color;
    }
    
    public abstract double getArea();
}

public class Circle extends GeometricObject {
    private double radius;
    
    public Circle(String color, double radius) {
        super(color);
        this.radius = radius;
    }
    
    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }
}

// Generic class bounded by GeometricObject
public class ShapeContainer<T extends GeometricObject> {
    private T shape;
    
    public ShapeContainer(T shape) {
        this.shape = shape;
    }
    
    public double getShapeArea() {
        return shape.getArea();  // Can call GeometricObject methods
    }
}

// Usage
ShapeContainer<Circle> circleContainer = new ShapeContainer<>(new Circle("red", 5.0));
double area = circleContainer.getShapeArea();
```

---

## Raw Types and Backward Compatibility

### Raw Types Definition:
**Raw Type**: A generic class or interface used without specifying a type parameter.

```java
// Raw type usage
ArrayList list = new ArrayList();  // Raw type
list.add("Hello");
list.add(123);  // No type checking

// Equivalent to
ArrayList<Object> list = new ArrayList<Object>();
```

### Problems with Raw Types:
```java
// Unsafe raw type example
public class UnsafeMax {
    public static Comparable max(Comparable o1, Comparable o2) {
        if (o1.compareTo(o2) > 0) 
            return o1;
        else 
            return o2;
    }
}

// This compiles but causes runtime error
Comparable result = UnsafeMax.max("Welcome", 23);  // ClassCastException!
```

### Safe Generic Version:
```java
public class SafeMax {
    public static <T extends Comparable<T>> T max(T o1, T o2) {
        if (o1.compareTo(o2) > 0) 
            return o1;
        else 
            return o2;
    }
}

// This won't compile - type safety enforced
// String result = SafeMax.max("Welcome", 23);  // Compile error!

// Correct usage
String result1 = SafeMax.max("Welcome", "Hello");  // OK
Integer result2 = SafeMax.max(23, 45);  // OK
```

### Best Practices:
```java
// ✅ Use generic types
ArrayList<String> list = new ArrayList<>();

// ❌ Avoid raw types
ArrayList list = new ArrayList();
```

---

## Wildcard Generic Types

### Why Wildcards are Necessary:
```java
// This doesn't work even though Integer is a subtype of Number
ArrayList<Integer> intList = new ArrayList<>();
// ArrayList<Number> numList = intList;  // Compile error!

// Solution: Use wildcards
ArrayList<? extends Number> numList = intList;  // OK!
```

### Three Types of Wildcards:

#### 1. Unbounded Wildcard (?)
```java
public static void printList(List<?> list) {
    for (Object item : list) {
        System.out.println(item);
    }
}

// Can accept any type of list
List<String> strings = Arrays.asList("A", "B", "C");
List<Integer> integers = Arrays.asList(1, 2, 3);
printList(strings);   // OK
printList(integers);  // OK
```

#### 2. Upper Bounded Wildcard (? extends T)
```java
public static double sumNumbers(List<? extends Number> list) {
    double sum = 0;
    for (Number num : list) {
        sum += num.doubleValue();
    }
    return sum;
}

// Can accept Number and its subtypes
List<Integer> integers = Arrays.asList(1, 2, 3);
List<Double> doubles = Arrays.asList(1.5, 2.5, 3.5);
List<Number> numbers = Arrays.asList(1, 2.5, 3);

double sum1 = sumNumbers(integers);  // OK
double sum2 = sumNumbers(doubles);   // OK
double sum3 = sumNumbers(numbers);   // OK
```

#### 3. Lower Bounded Wildcard (? super T)
```java
public static void addNumbers(List<? super Integer> list) {
    list.add(1);
    list.add(2);
    list.add(3);
}

// Can accept Integer and its supertypes
List<Integer> integers = new ArrayList<>();
List<Number> numbers = new ArrayList<>();
List<Object> objects = new ArrayList<>();

addNumbers(integers);  // OK
addNumbers(numbers);   // OK
addNumbers(objects);   // OK
```

### Practical Wildcard Examples:
```java
public class WildcardExamples {
    
    // Copy from source to destination
    public static <T> void copy(List<? extends T> source, List<? super T> dest) {
        for (T item : source) {
            dest.add(item);
        }
    }
    
    // Find maximum in a list
    public static <T extends Comparable<T>> T findMax(List<? extends T> list) {
        if (list.isEmpty()) return null;
        
        T max = list.get(0);
        for (T item : list) {
            if (item.compareTo(max) > 0) {
                max = item;
            }
        }
        return max;
    }
}

// Usage
List<Integer> source = Arrays.asList(1, 2, 3);
List<Number> dest = new ArrayList<>();
WildcardExamples.copy(source, dest);

List<String> words = Arrays.asList("apple", "banana", "cherry");
String maxWord = WildcardExamples.findMax(words);  // "cherry"
```

---

## Type Erasure

### What is Type Erasure?
Type Erasure is the process by which the Java compiler removes all generic type information during compilation. This enables backward compatibility with pre-generic Java code.

### How Type Erasure Works:

#### 1. Unbounded Generic Types:
```java
// Before erasure
public class GenericClass<T> {
    private T data;
    
    public void setData(T data) {
        this.data = data;
    }
    
    public T getData() {
        return data;
    }
}

// After erasure (what actually runs)
public class GenericClass {
    private Object data;  // T becomes Object
    
    public void setData(Object data) {
        this.data = data;
    }
    
    public Object getData() {
        return data;
    }
}
```

#### 2. Bounded Generic Types:
```java
// Before erasure
public class NumberContainer<T extends Number> {
    private T value;
    
    public T getValue() {
        return value;
    }
}

// After erasure
public class NumberContainer {
    private Number value;  // T becomes Number (the bound)
    
    public Number getValue() {
        return value;
    }
}
```

#### 3. Generic Methods:
```java
// Before erasure
public static <T> void printArray(T[] array) {
    for (T element : array) {
        System.out.println(element);
    }
}

// After erasure
public static void printArray(Object[] array) {
    for (Object element : array) {
        System.out.println(element);
    }
}
```

### Important Implications:
```java
// All instances share the same class
ArrayList<String> list1 = new ArrayList<>();
ArrayList<Integer> list2 = new ArrayList<>();

System.out.println(list1 instanceof ArrayList);  // true
System.out.println(list2 instanceof ArrayList);  // true
System.out.println(list1.getClass() == list2.getClass());  // true

// Only one ArrayList class is loaded into JVM
```

### Compiler Adds Casting:
```java
// Your code
ArrayList<String> list = new ArrayList<>();
list.add("Hello");
String s = list.get(0);

// After erasure (compiler adds casting)
ArrayList list = new ArrayList();
list.add("Hello");
String s = (String) list.get(0);  // Cast added by compiler
```

---

## Restrictions on Generics

### 1. Cannot Create Instance of Generic Type
```java
public class GenericClass<T> {
    private T obj;
    
    public GenericClass() {
        // obj = new T();  // ❌ Compile error!
    }
    
    // Workaround: Use factory method or Class parameter
    public static <T> GenericClass<T> create(Class<T> clazz) {
        try {
            GenericClass<T> instance = new GenericClass<>();
            instance.obj = clazz.getDeclaredConstructor().newInstance();
            return instance;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
```

### 2. Cannot Create Generic Arrays
```java
public class GenericArray<T> {
    private T[] array;
    
    public GenericArray(int size) {
        // array = new T[size];  // ❌ Compile error!
        
        // Workaround: Create Object array and cast
        array = (T[]) new Object[size];  // ⚠️ Unchecked warning
    }
    
    // Better approach: Use ArrayList
    private ArrayList<T> list = new ArrayList<>();
}
```

### 3. Generic Type Parameters Not Allowed in Static Context
```java
public class GenericClass<T> {
    private T instance;           // ✅ OK
    private static T staticVar;   // ❌ Compile error!
    
    public void instanceMethod(T param) {  // ✅ OK
        // ...
    }
    
    public static void staticMethod(T param) {  // ❌ Compile error!
        // ...
    }
    
    // Workaround: Make static method generic
    public static <U> void staticMethod(U param) {  // ✅ OK
        // ...
    }
}
```

### 4. Exception Classes Cannot Be Generic
```java
// ❌ This is illegal
public class MyException<T> extends Exception {
    private T data;
}

// ✅ This is OK
public class MyException extends Exception {
    private Object data;  // Use Object instead
    
    public MyException(String message, Object data) {
        super(message);
        this.data = data;
    }
}
```

### 5. Cannot Use instanceof with Generic Types
```java
public class GenericClass<T> {
    public void method(Object obj) {
        // if (obj instanceof GenericClass<String>) { }  // ❌ Compile error!
        
        // ✅ This works
        if (obj instanceof GenericClass<?>) {
            GenericClass<?> gc = (GenericClass<?>) obj;
        }
    }
}
```

---

## Practical Examples and Best Practices

### Complete Generic Data Structure Example:
```java
public class GenericLinkedList<E> {
    private Node<E> head;
    private Node<E> tail;
    private int size;
    
    private static class Node<E> {
        E data;
        Node<E> next;
        
        Node(E data) {
            this.data = data;
        }
    }
    
    public void add(E element) {
        Node<E> newNode = new Node<>(element);
        
        if (head == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }
    
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        
        Node<E> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }
    
    public int size() {
        return size;
    }
}
```

### Generic Utility Methods:
```java
public class GenericUtils {
    
    // Generic search method
    public static <T> int search(T[] array, T target) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(target)) {
                return i;
            }
        }
        return -1;
    }
    
    // Generic sort method (requires Comparable)
    public static <T extends Comparable<T>> void sort(T[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i].compareTo(array[j]) > 0) {
                    T temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }
    }
    
    // Generic method with wildcards
    public static void printCollection(Collection<?> collection) {
        for (Object item : collection) {
            System.out.println(item);
        }
    }
}
```

### Best Practices Summary:

1. **Always use generics for type safety**
2. **Use bounded wildcards for flexibility**
3. **Avoid raw types in new code**
4. **Use meaningful type parameter names**
5. **Prefer generic methods over raw types**
6. **Use wildcards for read-only operations**
7. **Use bounded types when you need specific capabilities**

```java
// ✅ Good practices
List<String> names = new ArrayList<>();
Map<String, Integer> ages = new HashMap<>();
Set<? extends Number> numbers = new HashSet<>();

// ❌ Avoid these
List names = new ArrayList();  // Raw type
Map ages = new HashMap();      // Raw type
```

---

## Summary

Java Generics provide:
- **Type Safety**: Catch errors at compile time
- **Code Reusability**: Write once, use with multiple types
- **Performance**: Eliminate casting overhead
- **Readability**: Clear type information in code

Key concepts to master:
- Generic classes and methods
- Bounded types and wildcards
- Type erasure implications
- Generic restrictions and workarounds
- Best practices for clean, maintainable code

Remember: Generics are a compile-time feature that helps write safer, more maintainable code while maintaining backward compatibility with older Java versions.
