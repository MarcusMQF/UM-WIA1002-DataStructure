public class L08_Q1 {
    public static void main(String[] args) {
        System.out.println(substituteAI("flabbergasted"));
        System.out.println(substituteAI("Astronaut"));
    }

    public static String substituteAI(String word){
        // Base case: if string is empty, return empty string
        if(word.length() == 0) {
            return "";
        }
        
        // Get the first character
        char firstChar = word.charAt(0);
        
        // Recursive case: process the rest of the string
        String restOfString = substituteAI(word.substring(1));
        
        // If first character is lowercase 'a', replace with 'i'
        if(firstChar == 'a') {
            return 'i' + restOfString;
        } else {
            // Otherwise, keep the original character
            return firstChar + restOfString;
        }
    }
}
