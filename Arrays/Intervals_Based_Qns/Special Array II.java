// LeetCode Medium - 3152


// Brute Force - O(n * m)
class Solution {
    public boolean[] isArraySpecial(int[] nums, int[][] queries) {
        int n = queries.length;
        boolean result[] = new boolean[n];
        Arrays.fill(result, true);

        for(int i = 0; i<n; i++){
            int start = queries[i][0];
            int end = queries[i][1];

            for(int j = queries[i][0]; j<queries[i][1]; j++){
                if(nums[j] % 2 == 0 && nums[j+1] % 2 == 0 || nums[j] % 2 != 0 && nums[j+1] % 2 != 0){
                    result[i] = false;
                    break;
                }
            }
        }

        return result;
    }
}




// Optimal - O(n + m)
class Solution {
    public boolean[] isArraySpecial(int[] nums, int[][] queries) {
        int n = nums.length;
        int[] count = new int[n];
        count[0] = 0;

        for(int i = 1; i<n; i++){
            int parity = (nums[i] % 2 == nums[i-1] % 2) ? 1 : 0;

            count[i] = count[i-1] + parity;
        }
        
        int m = queries.length;
        boolean result[] = new boolean[m];

        for(int i = 0; i<m; i++){
            int start = queries[i][0];
            int end = queries[i][1];

            result[i] = ((count[end] - count[start]) == 0);

            // OR

            // int problematicCount = count[end] - count[start];

            // if(problematicCount > 0){
            //     result[i] = false;
            // }
            // else{
            //     result[i] = true;
            // }
        }

        return result;
    }
}