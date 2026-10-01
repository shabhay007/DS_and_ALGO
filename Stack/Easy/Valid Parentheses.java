// LeetCode - 20



class Solution {
    public boolean isValid(String s) {
        int n = s.length();
        Stack<Character> stack = new Stack<>();

        for(int i = 0; i<n; i++){
            char ch = s.charAt(i);

            if(!stack.isEmpty() && (
                (ch == ')' && stack.peek() == '(') || 
                (ch == ']' && stack.peek() == '[') || 
                (ch == '}' && stack.peek() == '{')
                )){
                stack.pop();
            }
            else{
                stack.push(ch);
            }
        }

        return stack.isEmpty();
    }
}