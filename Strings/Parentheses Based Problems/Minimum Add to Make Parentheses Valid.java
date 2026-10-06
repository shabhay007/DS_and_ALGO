// LeetCode Medium - 921



// Approach 1 - Stack
// T.C. - O(n)
// S.C. - O(n)
class Solution {
    public int minAddToMakeValid(String s) {
        Stack<Character> stack = new Stack<>();

        for(char ch : s.toCharArray()){
            if(ch == '('){
                stack.push(ch);
            }
            else if(!stack.isEmpty() && ch == ')' && stack.peek() == '('){
                stack.pop();
            }
            else if(ch == ')'){
                stack.push(ch);
            }
        }

        return stack.size();
    }
}







// Approach 2
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public int minAddToMakeValid(String s) {
        int opening = 0;
        int closing = 0;

        for(char ch : s.toCharArray()){
            if(ch == '('){
                opening++;
            }
            else if(opening > 0 && ch == ')'){
                opening--;
            }
            else if(ch == ')'){
                closing++;
            }
        }

        return opening + closing;
    }
}







// Approach 3
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public int minAddToMakeValid(String s) {
        int n = s.length();

        int open = 0;
        int result = 0;

        for(int i = 0; i<n; i++){
            char ch = s.charAt(i);

            if(ch == '('){
                open++;
            }
            else{
                open--;
            }

            if(open < 0){
                result++;
                open = 0;
            }
        }

        return open + result;
    }
}