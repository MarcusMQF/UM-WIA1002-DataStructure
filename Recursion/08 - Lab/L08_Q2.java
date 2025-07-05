import java.util.*;

public class L08_Q2 {
    public static void main(String[] args) {
        System.out.println("Input String: \"ABC\"");
        System.out.println("Output Permutation:");
        
        List<String> permutations = permuteString("ABC");
        for (String perm : permutations) {
            System.out.println(perm);
        }
    }

    public static List<String> permuteString(String str) {
        // Base case: if string is empty or has one character
        if (str.length() <= 1) {
            List<String> result = new ArrayList<>();
            result.add(str);
            return result;
        }
        
        List<String> allPermutations = new ArrayList<>();
        
        // Try each character as the first character
        for (int i = 0; i < str.length(); i++) {
            // Extract the character at position i
            char firstChar = str.charAt(i);
            
            // Get remaining characters (excluding the character at position i)
            String remaining = str.substring(0, i) + str.substring(i + 1);
            
            // Get all permutations of the remaining characters
            List<String> subPermutations = permuteString(remaining);
            
            // Add the first character to the beginning of each sub-permutation
            for (String subPerm : subPermutations) {
                allPermutations.add(firstChar + subPerm);
            }
        }
        
        return allPermutations;
    }
}
