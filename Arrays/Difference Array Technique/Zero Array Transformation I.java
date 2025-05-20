// LeetCode Medium - 3355



// Approach 1 - Brute Force (Gives TLE)
// T.C. - O(q.n + n)
// S.C. - O(1)
class Solution {
    public boolean isZeroArray(int[] nums, int[][] queries) {
        int n = nums.length;
        
        for(int query[] : queries){
            int l = query[0];
            int r = query[1];

            for(int i = l; i <= r; i++){
                if(nums[i] != 0){
                    nums[i] -= 1;
                }
            }
        }

        for(int i = 0; i<n; i++){
            if(nums[i] != 0){
                return false;
            }
        }

        return true;
    }
}





// Approach 2 (Optimal) - Difference Array Technique
// T.C. - O(q + 2n)
// S.C. - O(n)
class Solution {
    public boolean isZeroArray(int[] nums, int[][] queries) {
        int n = nums.length;
        int[] diffArray = new int[n];

        for(int[] query : queries){
            int l = query[0];
            int r = query[1];

            diffArray[l] += 1;

            if(r + 1 < n){
                diffArray[r+1] -= 1;
            }
        }

        // finding cummulative sum
        int cummulativeSum = 0;
        int[] result = new int[n];

        for(int i = 0; i<n; i++){
            cummulativeSum += diffArray[i];

            result[i] = cummulativeSum;
        }

        // now, checking if possible to transform
        for(int i = 0; i<n; i++){
            if(result[i] < nums[i]){
                return false;
            }
        }

        return true;
    }
}