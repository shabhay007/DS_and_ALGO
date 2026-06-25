// LeetCode - 3737



// Approach 1 - Brute Force
// T.C. - O(n^2)
// S.C. - O(1)
class Solution {
    public int countMajoritySubarrays(int[] nums, int target) {
        int n = nums.length;
        int result = 0;

        for(int i = 0; i<n; i++){
            int count = 0;

            for(int j = i; j<n; j++){
                if(nums[j] == target){
                    count++;
                }

                if(count > (j-i+1)/2){
                    result++;
                }
            }
        }

        return result;
    }
}