// LeetCode - 1190



// Approach 1 - Stack
// T.C. - O(n^2)
// S.C. - O(n)
class Solution {
    public void reverse(StringBuilder sb, int start, int end){
        while(start < end){
            char ch = sb.charAt(start);
            sb.setCharAt(start++, sb.charAt(end));
            sb.setCharAt(end--, ch);
        }
    }

    public String reverseParentheses(String s) {
        int n = s.length();

        // It will track, how much length we have to skip from start to reverse the
        // rest of the characters
        Stack<Integer> stack = new Stack<>();
        StringBuilder result = new StringBuilder();

        for(int i = 0; i<n; i++){
            char ch = s.charAt(i);

            if(ch == '('){
                stack.push(result.length());
            }
            else if(ch == ')'){
                int lenToSkipFromStart = stack.pop();
                reverse(result, lenToSkipFromStart, result.length()-1);
            }
            else{
                result.append(ch);
            }
        }

        return result.toString();
    }
}






// Approach 2 - Linear Approach
// T.C : O(n)
// S.C : O(n)
class Solution {
    public String reverseParentheses(String s) {
        int n = s.length();
        Stack<Integer> openBracket = new Stack<>();
        int[] door = new int[n];

        // First pass: Pair up parentheses
        for (int i = 0; i < n; ++i) {
            if (s.charAt(i) == '(') {
                openBracket.push(i);
            }
            else if (s.charAt(i) == ')') {
                int j = openBracket.pop();
                door[i] = j;
                door[j] = i;
            }
        }

        // Second pass: Build the result string
        StringBuilder result = new StringBuilder();
        int direction = 1; // Left to Right

        for (int i = 0; i < n; i += direction) {
            if (s.charAt(i) == '(' || s.charAt(i) == ')') {
                i = door[i];
                direction = -direction;
            }
            else {
                result.append(s.charAt(i));
            }
        }

        return result.toString();
    }
}