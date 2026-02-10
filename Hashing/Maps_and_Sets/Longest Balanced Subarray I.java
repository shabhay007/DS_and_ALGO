// LeetCode Medium - 3719



// Approach 1 - Brute Force (Checking all subarrays)
// T.C. - O(n^2)
// S.C. - O(n)
class Solution {
    public int longestBalanced(int[] nums) {
        int n = nums.length;
        int maxL = 0;

        for (int i = 0; i < n; i++) {
            Set<Integer> even = new HashSet<>();
            Set<Integer> odd = new HashSet<>();

            for (int j = i; j < n; j++) {
                if (nums[j] % 2 == 0) {
                    even.add(nums[j]);
                } else {
                    odd.add(nums[j]);
                }

                if (even.size() == odd.size()) {
                    maxL = Math.max(maxL, j-i+1);
                }
            }
        }

        return maxL;
    }
}