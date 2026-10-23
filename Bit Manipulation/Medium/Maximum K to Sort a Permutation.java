// LeetCode - 3644



// Approach 1 - Bit Manipulation
// T.C. - O(n)
// T.C. - O(1)
class Solution {
    public int sortPermutation(int[] nums) {
        int n = nums.length;
        int k = -1;

        for(int i = 0; i<n; i++){
            if(i != nums[i]){
                k &= nums[i];
            }
        }

        return k == -1 ? 0 : k;
    }
}