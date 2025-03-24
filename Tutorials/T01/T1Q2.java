/*

Output:
(1) Performs Person's tasks
(2) Invoke Employee's overloaded constructor
(3) Performs Employee's tasks 
(4) Performs Faculty's tasks

1. When new Faculty() is called in the main method, the constructor of Faculty is invoked.

2. Since Faculty extends Employee, the constructor of Employee is called first.

3. The Employee constructor calls another constructor within the same class using this("(2) Invoke Employee's overloaded constructor").

4. Before executing the body of the Employee constructor, the constructor of its superclass Person is called.

5. The Person constructor prints (1) Performs Person's tasks.

6. Control returns to the Employee constructor, which prints (2) Invoke Employee's overloaded constructor.

7. The Employee constructor then prints (3) Performs Employee's tasks.

8. Finally, control returns to the Faculty constructor, which prints (4) Performs Faculty's tasks.
*/
