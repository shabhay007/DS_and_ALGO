// LeetCode Easy - 3174


// Approach 1 - Using Stack
// T.C. - O(3n)
// S.C. - O(n)
class Solution {
    public String clearDigits(String s) {
        int n = s.length();

        Stack<Character> stack = new Stack<>();

        for(int i = 0; i<n; i++){ // O(n)
            char ch = s.charAt(i);

            if(!stack.isEmpty() && Character.isDigit(ch)){
                stack.pop();
            }
            else{
                stack.push(ch);
            }
        }

        if(stack.isEmpty()){
            return "";
        }

        StringBuilder sb = new StringBuilder();

        while(!stack.isEmpty()){ // o(n)
            sb.append(stack.pop());
        }

        return sb.reverse().toString(); // O(n)
    }
}




// Approach 2 - Using Stack
// T.C. - O(2n)
// S.C. - O(n)
class Solution {
    public String clearDigits(String s) {
        int n = s.length();

        Stack<Character> stack = new Stack<>();

        for(int i = 0; i<n; i++){ // O(n)
            char ch = s.charAt(i);

            if(!stack.isEmpty() && Character.isDigit(ch)){
                stack.pop();
            }
            else{
                stack.push(ch);
            }
        }

        StringBuilder sb = new StringBuilder();

        while(!stack.isEmpty()){ // o(n)
            sb.insert(0, stack.pop());
        }

        return sb.toString();
    }
}




// Approach 3 - Using StringBuilder
// T.C. - O(n)
// S.C. - O(n); as we are returning the result; but O(1) as we are not using any extra space
class Solution {
    public String clearDigits(String s) {
        int n = s.length();

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i<n; i++){ // O(n)
            char ch = s.charAt(i);

            if(sb.length() > 0 && Character.isDigit(ch)){
                sb.deleteCharAt(sb.length() - 1);
            }
            else{
                sb.append(ch);
            }
        }

        return sb.toString();
    }
}