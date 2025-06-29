// LeetCode - 1498



// Approach 1 - Sorting + Two Pointers
// T.C. - O(nlog(n) + 2n)
// S.C. - O(n)
class Solution {
    public int numSubseq(int[] nums, int target) {
        int mod = (int) 1e9 + 7;
        int n = nums.length;
        Arrays.sort(nums);

        int l = 0;
        int r = n-1;
        long count = 0;

        // precomputing the powers
        // maxDiff between r and l will be n-1 - 0 = n-1
        int[] power = new int[n];
        power[0] = 1;

        for(int i = 1; i<n; i++){
            power[i] = (power[i-1] * 2) % mod;
        }

        while(l <= r){
            if(nums[l] + nums[r] <= target){
                count = (count % mod + power[r-l]) % mod;
                l++;
            }
            else{
                r--;
            }
        }

        return (int) count;
    }
}