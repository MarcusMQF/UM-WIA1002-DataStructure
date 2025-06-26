/*
1. peek()

2. 
s2: [two]
s3: [one]
s1: []
s1: [two]
s2: [one]
s2: [two]

Question 3: Stack Push/Pop Sequence Analysis
Elements 1, 2, and 3 are pushed onto a stack in that order.
Determine if the following sequences can be created by pop operations:

(a) 1-2-3: NO
    - To get 1 first, we must pop it immediately after pushing it
    - Operations: push(1), pop() -> 1
    - Then push(2), pop() -> 2  
    - Then push(3), pop() -> 3
    - This would require popping 1 before 2 and 3 are pushed, but the problem states
      all elements are pushed in order first.

(b) 2-3-1: YES
    Operations:
    push(1), push(2), pop() -> 2
    push(3), pop() -> 3
    pop() -> 1
    Stack states: [] -> [1] -> [1,2] -> [1] -> [1,3] -> [1] -> []

(c) 3-2-1: YES
    Operations:
    push(1), push(2), push(3), pop() -> 3
    pop() -> 2
    pop() -> 1
    Stack states: [] -> [1] -> [1,2] -> [1,2,3] -> [1,2] -> [1] -> []

(d) 1-3-2: NO
    - To get 1 first, we need to pop it before 2 and 3
    - But then 3 cannot come before 2 in the output sequence
    - When we push(1), push(2), push(3), we can only pop in order 3,2,1
    - There's no valid sequence of operations to achieve 1-3-2

Question 4: Convert infix expressions to postfix

(a) a + b * c
    Step-by-step conversion:
    - a: output a
    - +: push to stack
    - b: output b  
    - *: higher precedence than +, push to stack
    - c: output c
    - End: pop all operators (* then +)
    Result: a b c * +

(b) a * b – c/d
    Step-by-step conversion:
    - a: output a
    - *: push to stack
    - b: output b
    - –: same precedence as *, pop *, push –
    - c: output c
    - /: higher precedence than –, push to stack
    - d: output d
    - End: pop all operators (/ then –)
    Result: a b * c d / –

(c) a + (b*c + d)/e
    Step-by-step conversion:
    - a: output a
    - +: push to stack
    - (: push to stack
    - b: output b
    - *: push to stack
    - c: output c
    - +: pop * (higher precedence), push +
    - d: output d
    - ): pop until (, which gives us +
    - /: push to stack
    - e: output e
    - End: pop all operators (/ then +)
    Result: a b c * d + e / +

Question 5: Convert postfix expressions to infix

(a) a b + c *
    Step-by-step conversion:
    - a: push a
    - b: push b
    - +: pop b and a, create (a + b), push result
    - c: push c
    - *: pop c and (a + b), create ((a + b) * c)
    Result: (a + b) * c

(b) a b c + *
    Step-by-step conversion:
    - a: push a
    - b: push b
    - c: push c
    - +: pop c and b, create (b + c), push result
    - *: pop (b + c) and a, create (a * (b + c))
    Result: a * (b + c)

Question 6: Applications of stack
Answer: D. all of the above

Explanation:
A. Finding factorial - YES: Recursive calls use the system stack
B. Tower of Hanoi - YES: Recursive solution uses stack for backtracking
C. Infix to postfix - YES: Stack is used to handle operator precedence
D. All of the above - CORRECT: All mentioned applications use stacks
*/
