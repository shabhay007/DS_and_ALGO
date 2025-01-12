// LeetCode Medium - 2116



// Approach 1 - Stack
// T.C. - O(2n)
// S.C. - O(n)
class Solution {
    public boolean canBeValid(String s, String locked) {
        int n = s.length();

        if(n % 2 != 0){
            return false;
        }

        // to store index of open parenthesis which is fixed i.e. locked[i] = 1;
        Stack<Integer> open = new Stack<>();

        // to store index of any parenthesis which is flexible i.e. locked[i] = 0
        Stack<Integer> openClose = new Stack<>();

        for(int i = 0; i<n; i++){
            if(locked.charAt(i) == '0'){
                openClose.push(i);
            }
            else if(s.charAt(i) == '('){
                open.push(i);
            }
            else{ // char is ')' and fixed
                if(!open.isEmpty()){
                    open.pop();
                }
                else if(!openClose.isEmpty()){
                    openClose.pop();
                }
                else{
                    return false;
                }
            }
        }

        while(!open.isEmpty() && !openClose.isEmpty() && open.peek() < openClose.peek()){
            open.pop();
            openClose.pop();
        }

        return open.isEmpty();
    }
}





// Approach 2 - Simulation (Without Stack)
// T.C. - O(2n)
// S.C. - O(1)
class Solution {
    public boolean canBeValid(String s, String locked) {
        int n = s.length();

        if(n % 2 != 0){
            return false;
        }

        // traversing LEFT to RIGHT
        // increasing count of open bracket, when encountering '(' in s string or 
        // '0' in locked string (greedily)
        // and decreasing the count when encountering ')'
        int open = 0;
        for(int i = 0; i<n; i++){
            if(s.charAt(i) == '(' || locked.charAt(i) == '0'){
                open++;
            }
            else{
                open--;
            }

            if(open < 0){
                return false;
            }
        }


        // traversing RIGHT to LEFT
        // increasing count of closing bracket, when encountering ')' in s string or 
        // '0' in locked string (greedily)
        // and decreasing the count when encountering '('
        int close = 0;
        for(int i = n-1; i>=0; i--){
            if(s.charAt(i) == ')' || locked.charAt(i) == '0'){
                close++;
            }
            else{
                close--;
            }

            if(close < 0){
                return false;
            }
        }

        return true;
    }
}