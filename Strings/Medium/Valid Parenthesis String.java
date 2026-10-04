// LeetCode - 678



// Approach 1 - Greedy
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public boolean checkValidString(String s) {
        int n = s.length();

        int minOpen = 0;
        int maxOpen = 0;

        for(int i = 0; i<n; i++){
            char ch = s.charAt(i);

            if(ch == '('){
                minOpen++;
                maxOpen++;
            }
            else if(ch == ')'){
                minOpen--;
                minOpen = Math.max(0, minOpen);

                maxOpen--;
            }
            else{
                minOpen--;  // treating * as ')'
                minOpen = Math.max(0, minOpen);

                maxOpen++;  // treating * as '('
            }

            if(maxOpen < 0){
                return false;
            }
        }

        return minOpen == 0;
    }
}