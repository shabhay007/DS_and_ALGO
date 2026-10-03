// LeetCode - 32



// Approach 1 - Observation
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public int longestValidParentheses(String s) {
        int n = s.length();

        int open = 0;
        int close = 0;

        int result = 0;

        // left to right
        for(int i = 0; i<n; i++){
            char ch = s.charAt(i);

            if(ch == '('){
                open++;
            }
            else{
                close++;
            }

            if(close > open){
                open = 0;
                close = 0;
            }

            if(open == close){
                result = Math.max(result, open + close);
            }
        }

        // right to left
        open = 0;
        close = 0;
        
        for(int i = n-1; i >= 0; i--){
            char ch = s.charAt(i);

            if(ch == '('){
                open++;
            }
            else{
                close++;
            }

            if(open > close){
                open = 0;
                close = 0;
            }

            if(open == close){
                result = Math.max(result, open + close);
            }
        }

        return result;
    }
}