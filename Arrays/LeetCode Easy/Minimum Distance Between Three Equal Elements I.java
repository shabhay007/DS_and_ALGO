// LeetCode - 3740



// Approach 1 - Brute Force
// T.C. - O(n^3)
// S.C. - O(1)
class Solution {
    public int minimumDistance(int[] nums) {
        int n = nums.length;

        int minDistance = Integer.MAX_VALUE;
        for(int i = 0; i<n; i++){
            for(int j = i+1; j<n; j++){
                for(int k = j+1; k<n; k++){
                    if(nums[i] == nums[j] && nums[j] == nums[k]){
                        int curr = k - i + k - j + j - i;
                        minDistance = Math.min(minDistance, curr);
                    }
                }
            }
        }

        return minDistance == Integer.MAX_VALUE ? -1 : minDistance;
    }
}