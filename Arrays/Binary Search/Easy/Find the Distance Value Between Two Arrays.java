// LeetCode - 1385



// Approach 1 - Brute Force
// T.C. - O(m * n)
// S.C. - O(1)
class Solution {
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        int m = arr1.length;
        int n = arr2.length;

        int count = 0;

        for(int i = 0; i<m; i++){
            boolean flag = true;

            for(int j = 0; j<n; j++){
                if(Math.abs(arr1[i] - arr2[j]) <= d){
                    flag = false;
                    break;
                }
            }

            if(flag){
                count++;
            }
        }

        return count;
    }
}