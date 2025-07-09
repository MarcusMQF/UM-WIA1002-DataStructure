import java.util.LinkedList;
import java.util.Scanner;

public class Q2 {
    
    public static class ExamStack<E>{
        private LinkedList<E> stack;
        
        public ExamStack(){
            stack = new LinkedList<>();
        }
        
        public void push(E element){
            stack.addFirst(element);
        }
        
        public E pop() {
            if (stack.isEmpty()) {
                return null; // or throw exception
            }
            return stack.removeFirst();
        }
        
        // Peek at top element without removing it
        public E peek() {
            if (stack.isEmpty()) {
                return null; // or throw exception
            }
            return stack.peekFirst();
        }
        
        // Get current size of stack
        public int getSize() {
            return stack.size();
        }
        
        // Check if stack is empty
        public boolean isEmpty() {
            return stack.isEmpty();
        }
    }
    
    /**
     * Evaluates arithmetic expressions using two stacks
     * Supports +, -, *, /, parentheses, and follows proper operator precedence
     * @param expr the arithmetic expression to evaluate
     * @return the result of the evaluation
     */
    private static double evaluateExpr(String expr) {
        ExamStack<Double> operandStack = new ExamStack<>();
        ExamStack<Character> operatorStack = new ExamStack<>();
        
        // Remove all spaces from the expression
        expr = expr.replaceAll(" ", "");
        
        for (int i = 0; i < expr.length(); i++) {
            char ch = expr.charAt(i);
            
            // If character is a digit, extract the complete number
            if (Character.isDigit(ch)) {
                StringBuilder number = new StringBuilder();
                
                // Extract the complete number (including decimal points)
                while (i < expr.length() && (Character.isDigit(expr.charAt(i)) || expr.charAt(i) == '.')) {
                    number.append(expr.charAt(i));
                    i++;
                }
                i--; // Step back one position since loop will increment
                
                operandStack.push(Double.parseDouble(number.toString()));
            }
            // If character is an opening parenthesis, push it to operator stack
            else if (ch == '(') {
                operatorStack.push(ch);
            }
            // If character is a closing parenthesis, process until opening parenthesis
            else if (ch == ')') {
                while (!operatorStack.isEmpty() && operatorStack.peek() != '(') {
                    // Process operator - inline helper logic
                    if (operandStack.getSize() >= 2) {
                        char operator = operatorStack.pop();
                        double operand2 = operandStack.pop();
                        double operand1 = operandStack.pop();
                        
                        // Perform operation - inline logic
                        double result;
                        switch (operator) {
                            case '+':
                                result = operand1 + operand2;
                                break;
                            case '-':
                                result = operand1 - operand2;
                                break;
                            case '*':
                                result = operand1 * operand2;
                                break;
                            case '/':
                                if (operand2 == 0) {
                                    throw new ArithmeticException("Division by zero");
                                }
                                result = operand1 / operand2;
                                break;
                            default:
                                throw new IllegalArgumentException("Invalid operator: " + operator);
                        }
                        operandStack.push(result);
                    }
                }
                // Remove the opening parenthesis
                if (!operatorStack.isEmpty()) {
                    operatorStack.pop();
                }
            }
            // If character is an operator
            else if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
                // Get precedence - inline logic
                int currentPrecedence;
                switch (ch) {
                    case '+':
                    case '-':
                        currentPrecedence = 1;
                        break;
                    case '*':
                    case '/':
                        currentPrecedence = 2;
                        break;
                    default:
                        currentPrecedence = 0;
                }
                
                // Process operators with higher or equal precedence
                while (!operatorStack.isEmpty() && operatorStack.peek() != '(') {
                    // Get precedence of top operator
                    char topOperator = operatorStack.peek();
                    int topPrecedence;
                    switch (topOperator) {
                        case '+':
                        case '-':
                            topPrecedence = 1;
                            break;
                        case '*':
                        case '/':
                            topPrecedence = 2;
                            break;
                        default:
                            topPrecedence = 0;
                    }
                    
                    if (topPrecedence >= currentPrecedence) {
                        // Process operator - inline helper logic
                        if (operandStack.getSize() >= 2) {
                            char operator = operatorStack.pop();
                            double operand2 = operandStack.pop();
                            double operand1 = operandStack.pop();
                            
                            // Perform operation - inline logic
                            double result;
                            switch (operator) {
                                case '+':
                                    result = operand1 + operand2;
                                    break;
                                case '-':
                                    result = operand1 - operand2;
                                    break;
                                case '*':
                                    result = operand1 * operand2;
                                    break;
                                case '/':
                                    if (operand2 == 0) {
                                        throw new ArithmeticException("Division by zero");
                                    }
                                    result = operand1 / operand2;
                                    break;
                                default:
                                    throw new IllegalArgumentException("Invalid operator: " + operator);
                            }
                            operandStack.push(result);
                        }
                    } else {
                        break;
                    }
                }
                operatorStack.push(ch);
            }
        }
        
        // Process remaining operators
        while (!operatorStack.isEmpty()) {
            // Process operator - inline helper logic
            if (operandStack.getSize() >= 2) {
                char operator = operatorStack.pop();
                double operand2 = operandStack.pop();
                double operand1 = operandStack.pop();
                
                // Perform operation - inline logic
                double result;
                switch (operator) {
                    case '+':
                        result = operand1 + operand2;
                        break;
                    case '-':
                        result = operand1 - operand2;
                        break;
                    case '*':
                        result = operand1 * operand2;
                        break;
                    case '/':
                        if (operand2 == 0) {
                            throw new ArithmeticException("Division by zero");
                        }
                        result = operand1 / operand2;
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid operator: " + operator);
                }
                operandStack.push(result);
            }
        }
        
        // The final result should be the only element in operand stack
        return operandStack.pop();
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Test cases for verification
        String[] testCases = {
            "5-3+4",                    // Expected: 6.0
            "2+4*5-(9+7*6)",           // Expected: -29.0
            "9-2*(5/2-3/5+2)/6",       // Expected: 7.7
            "10+2*6",                   // Expected: 22.0
            "(1+2)*3",                  // Expected: 9.0
            "2*3+4*5",                  // Expected: 26.0
            "100/4/5",                  // Expected: 5.0
            "2+3*4-1",                  // Expected: 13.0
            "(2+3)*(4-1)",             // Expected: 15.0
            "1+2+3+4+5"                // Expected: 15.0
        };
        
        System.out.println("=== Testing Arithmetic Expression Evaluator ===");
        System.out.println("Running automated test cases:\n");
        
        for (String testExpr : testCases) {
            try {
                double result = evaluateExpr(testExpr);
                System.out.printf("%-20s = %.1f%n", testExpr, result);
            } catch (Exception e) {
                System.out.printf("%-20s = Error: %s%n", testExpr, e.getMessage());
            }
        }
        
        System.out.println("\n=== Interactive Mode ===");
        System.out.println("Enter arithmetic expressions (or 'quit' to exit):");
        
        while (true) {
            System.out.print("Enter expression to evaluate: ");
            String expr = scanner.nextLine().trim();
            
            if (expr.equalsIgnoreCase("quit") || expr.equalsIgnoreCase("exit")) {
                System.out.println("Goodbye!");
                break;
            }
            
            if (expr.isEmpty()) {
                System.out.println("Please enter a valid expression.");
                continue;
            }
            
            try {
                double result = evaluateExpr(expr);
                System.out.printf("%s = %.1f%n%n", expr, result);
            } catch (Exception e) {
                System.out.println("Error evaluating expression: " + e.getMessage());
                System.out.println("Please check your expression and try again.\n");
            }
        }
        
        scanner.close();
    }
}
