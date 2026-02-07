// Leetcode - 1653



// Approach 1 - Stack
// T.C. - O(n)
// S.C. - O(n)
class Solution {
    public int minimumDeletions(String s) {
        int n = s.length();
        Stack<Character> stack = new Stack<>();
        int count = 0;

        for(int i = 0; i<n; i++){
            char ch = s.charAt(i);

            if(!stack.isEmpty() && ch == 'a' && stack.peek() == 'b'){
                stack.pop();
                count++;
            }
            else{
                stack.push(ch);
            }
        }

        return count;
    }
}