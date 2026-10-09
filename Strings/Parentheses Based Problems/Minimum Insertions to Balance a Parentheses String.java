// LeetCode Medium - 1541



// Approach 1 - Greedy
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public int minInsertions(String s) {
        int n = s.length();
        int open = 0;
        int i = 0;
        int result = 0;

        while(i<n){
            char ch = s.charAt(i);

            if(ch == '('){
                open++;
                i++;
            }
            else{
                if(open > 0){
                    open--;
                }
                else{
                    // inserting one open bracket in order to balance
                    result++;
                }

                if(i+1 < n && s.charAt(i+1) == ')'){
                    i += 2; // balanced
                }
                else{
                    result++; // adding a closing bracket
                    i++;
                }
            }
        }

        if(open > 0){
            result += open * 2;
        }

        return result;
    }
}