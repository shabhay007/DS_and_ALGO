// LeetCode - 2211



// Approach 1 - Stack
// T.C. - O(n)
// S.C. - O(n)
class Solution {
    public int countCollisions(String directions) {
        int n = directions.length();
        Stack<Character> stack = new Stack<>();
        int result = 0;

        for(int i = 0; i<n; i++){
            char ch = directions.charAt(i);

            if(ch == 'R'){
                stack.push(ch);
            }
            else if(ch == 'L'){
                if(!stack.isEmpty() && stack.peek() == 'R'){
                    // for current RL pair
                    stack.pop();
                    result += 2;

                    // popping remaining R's
                    while(!stack.isEmpty() && stack.peek() == 'R'){
                        result++;
                        stack.pop();
                    }

                    stack.push('S');
                }
                else if(!stack.isEmpty() && stack.peek() == 'S'){
                    result++;
                }
            }
            else{ // ch == 'S'
                // R -> S collisions
                while(!stack.isEmpty() && stack.peek() == 'R'){
                    result++;
                    stack.pop();
                }

                stack.push('S');

            }
        }

        return result;
    }
}





// Approach 2 - Pointer Approach of Stack
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public int countCollisions(String directions) {
        int n = directions.length();
        boolean isStationary = false;
        int result = 0;
        int right = 0;

        for(int i = 0; i<n; i++){
            char ch = directions.charAt(i);

            if(ch == 'R'){
                right++;
            }
            else if(ch == 'L'){
                if(right > 0){
                    result += right+1;
                    right = 0;
                    isStationary = true;
                }
                else if(isStationary){
                    result++;
                }
            }
            else if(ch == 'S'){
                isStationary = true;

                if(right > 0){
                    result += right;
                    right = 0;
                }
            }
        }

        return result;
    }
}