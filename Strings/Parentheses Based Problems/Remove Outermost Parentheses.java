// LeetCode Easy - 1021



// Approach 1
// T.C. - O(n)
// S.C. - O(n)
class Solution {
    public String removeOuterParentheses(String s) {
        int n = s.length();
        int open = 0;
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i<n; i++){
            char ch = s.charAt(i);

            if(ch == '('){
                open++;

                if(open == 1){
                    continue;
                }
            }
            else if (ch == ')'){
                open--;

                if(open == 0){
                    continue;
                }
            }

            sb.append(ch);
        }

        return sb.toString();
    }
}