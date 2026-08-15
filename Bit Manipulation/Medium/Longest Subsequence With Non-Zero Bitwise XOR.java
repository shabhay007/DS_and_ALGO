// LeetCode - 3702



// Approach 1 - Bit Manipulation
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public int longestSubsequence(int[] nums) {
        int n = nums.length;
        int xor = 0;

        for (int num : nums) {
            xor ^= num;
        }

        // Entire array has non-zero XOR
        if (xor != 0) {
            return n;
        }

        // Total XOR is zero.
        // Remove one non-zero element to make XOR non-zero.
        for (int num : nums) {
            if (num != 0) {
                return n - 1;
            }
        }

        // All elements are zero.
        return 0;
    }
}