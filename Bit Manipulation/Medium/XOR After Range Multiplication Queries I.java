// LeetCode - 3653



// Approach 1 - Brute Force (Simulation + Bit Manipulation)
// T.C. - O(q * (r - l) + n)
// S.C. - O(1)
class Solution {
    int mod = (int) 1e9 + 7;

    public int xorAfterQueries(int[] nums, int[][] queries) {
        for(int[] q : queries){
            int l = q[0];
            int r = q[1];
            int k = q[2];
            int v = q[3];

            if(k == 0){ // to avoid infinite loop
                continue;
            }

            while(l <= r){
                nums[l] = (int) ((1L * nums[l] * v) % mod); // to avoid overflow
                l += k;
            }
        }

        // processing for xor
        int result = 0;

        for(int num : nums){
            result ^= num;
        }

        return result;
    }
}