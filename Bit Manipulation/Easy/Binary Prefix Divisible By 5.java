// LeetCode - 1018



// Approach 1 - Bit Manipulation (Instead of building large number, use remainder)
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public List<Boolean> prefixesDivBy5(int[] nums) {
        int n = nums.length;
        List<Boolean> result = new ArrayList<>();
        int remainder = 0;

        for(int i = 0; i<n; i++){
            remainder = (remainder * 2 + nums[i]) % 5;

            result.add(remainder == 0);
        }

        return result;
    }
}