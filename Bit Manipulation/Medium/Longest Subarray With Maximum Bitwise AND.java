// LeetCode - 2419



// Approach 1 - Two Pointer/Sliding Window (It is solved because of Bit Manipulation concept)
// T.C. - O(2n)
// S.C. - O(1)
class Solution {
    public int longestSubarray(int[] nums) {
        int n = nums.length;
        int max = 0;

        for(int i = 0; i<n; i++){
            max = Math.max(max, nums[i]);
        }

        int maxLen = 0;

        int i = 0;
        int j = 0;

        while(j < n){
            while(j < n && nums[j] == max){
                maxLen = Math.max(maxLen, j-i+1);
                j++;
            }

            j++;
            i = j;
        }

        return maxLen;
    }
}