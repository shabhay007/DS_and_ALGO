// LeetCode Medium - 856



// Approach 1 - Stack + Observation
// T.C. - O(n)
// S.C. - O(n)
class Solution {
    public int scoreOfParentheses(String s) {
        int n = s.length();

        Stack<Integer> stack = new Stack<>();
        int score = 0;

        for(int i = 0; i<n; i++){
            char ch = s.charAt(i);

            if(ch == '('){
                stack.push(score);
                score = 0;
            }
            else{
                // found the innermost () -> score + 1
                if(s.charAt(i-1) == '('){
                    score = stack.peek() + 1;
                }
                else{
                    score = 2 * score + stack.peek();
                }

                stack.pop();
            }
        }

        return score;
    }
}







// Approach 2 - Observation
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public int scoreOfParentheses(String s) {
        int n = s.length();
        int score = 0;
        int depth = 0;

        for(int i = 0; i<n; i++){
            char ch = s.charAt(i);

            if(ch == '('){
                depth++;
            }
            else{
                depth--;

                if(s.charAt(i-1) == '('){
                    score += (1 << depth); // 2^depth
                }
            }
        }

        return score;
    }
}