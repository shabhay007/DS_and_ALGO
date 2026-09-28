// LeetCode - 1614



// Approach 1
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public int maxDepth(String s) {
        int n = s.length();
        int result = 0;
        int depth = 0;

        for(int i = 0; i<n; i++){
            char ch = s.charAt(i);

            if(ch == '('){
                result++;
                depth = Math.max(depth, result);
            }
            else if(ch == ')'){
                result--;
            }
        }

        return depth;
    }
}