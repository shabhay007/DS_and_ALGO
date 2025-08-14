// LeetCode - 2264



// Approach 1 - Simulation + Sliding window
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public String largestGoodInteger(String num) {
        int n = num.length();

        String result = "";

        for(int i = 0; i <= n-3; i++){
            char ch1 = num.charAt(i);
            char ch2 = num.charAt(i+1);
            char ch3 = num.charAt(i+2);

            if(ch1 == ch2 && ch1 == ch3){
                String substr = num.substring(i, i+3);

                if(result.isEmpty() || substr.compareTo(result) > 0){
                    result = substr;
                }
            }
        }

        return result;
    }
}




// Approach 2 - Simulation
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public String largestGoodInteger(String num) {
        int n = num.length();

        char result = ' ';

        for(int i = 2; i < n; i++){
            char ch1 = num.charAt(i);
            char ch2 = num.charAt(i-1);
            char ch3 = num.charAt(i-2);

            if(ch1 == ch2 && ch1 == ch3){
                result = (char) Math.max(result, ch1);
            }
        }

        return result == ' ' ? "" : new String(new char[] {result, result, result});
    }
}