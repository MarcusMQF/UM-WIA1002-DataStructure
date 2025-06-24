public class L2Q1 {
    public static void main(String[] args) {
       // Create an instance of MyGeneric with String type
       MyGeneric<String> strObj = new MyGeneric<>();
       strObj.setE("Hello, Generics!");
       
       // Create an instance of MyGeneric with Integer type
       MyGeneric<Integer> intObj = new MyGeneric<>();
       intObj.setE(100);
       
       // Display the values using getter methods
       System.out.println("String value: " + strObj.getE());
       System.out.println("Integer value: " + intObj.getE());
    }
}

class MyGeneric<T>{
    private T e;

    // No-arg constructor
    public MyGeneric(){

    }

    // Constructor with parameter
    public MyGeneric(T e){
        this.e = e;
    }

    public void setE(T e){
        this.e = e;
    }

    public T getE(){
        return e;
    }
}
