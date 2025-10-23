// LeetCode - 3461



// Approach 1
// T.C. - O(n^2)
// S.C. - O(n)
class Solution {
    public boolean hasSameDigits(String s) {
        StringBuilder result = new StringBuilder(s);

        while(result.length() > 2){
            StringBuilder sb = new StringBuilder();
            int i = 0;

            while(i < result.length() - 1){
                int sum =(result.charAt(i) - '0') + (result.charAt(i+1) - '0');

                char ch = (char) ((sum % 10) + '0');
                sb.append(ch);
                i++;
            }

            result = sb;
        }

        return result.length() == 2 && result.charAt(0) == result.charAt(1);
    }
}