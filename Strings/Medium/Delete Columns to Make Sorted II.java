// LeetCode - 955



// Approach 1 - Array + String
// T.C. - O(m * n)
// S.C. - O(m)
class Solution {
    public int minDeletionSize(String[] strs) {
        int m = strs.length;
        int n = strs[0].length();
        int count = 0;
        boolean[] alreadySorted = new boolean[m-1];

        for(int col = 0; col < n; col++){
            boolean isDeleted = false;

            for(int row = 0; row < m-1; row++){
                if(!alreadySorted[row] && 
                    strs[row].charAt(col) > strs[row+1].charAt(col)){ 
                    isDeleted = true;
                    count++;
                    break;
                }
            }

            if(isDeleted){
                continue;
            }

            for(int i = 0; i<m-1; i++){
                alreadySorted[i] = alreadySorted[i] | 
                                    (strs[i].charAt(col) < strs[i+1].charAt(col));
            }
        }

        return count;
    }
}