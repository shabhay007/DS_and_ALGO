// LeetCode - 944



// Approach 1 - String
// T.C. - O(m*n)
// S.C. - O(1)
class Solution {
    public int minDeletionSize(String[] strs) {
        int m = strs.length;
        int n = strs[0].length();
        int count = 0;

        for(int col = 0; col < n; col++){
            for(int row = 0; row < m-1; row++){
                if(strs[row].charAt(col) > strs[row+1].charAt(col)){
                    count++;
                    break;
                }
            }
        }

        return count;
    }
}