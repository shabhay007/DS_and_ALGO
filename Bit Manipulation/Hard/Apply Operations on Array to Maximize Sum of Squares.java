// LeetCode - 2897



// Approach 1 - Greedy + Bit Manipulation
// T.C. - O(n + k)
// S.C. - O(1)
class Solution {
    public int maxSum(List<Integer> nums, int k) {
        int n = nums.size();
        int mod = (int) 1e9 + 7;

        int[] setBits = new int[32];
        for(int num : nums){ // O(n)
            for(int bit = 0; bit<32; bit++){
                if((num & (1 << bit)) != 0){
                    setBits[bit]++;
                }
            }
        }

        long result = 0;
        while(k-- > 0){ // O(k)
            long curr = 0;

            for(int bit = 0; bit<32; bit++){
                // if that bit has set bits
                if(setBits[bit] > 0){
                    setBits[bit]--;
                    curr = (curr | (1 << bit));
                }
            }

            result = (result + (curr * curr)) % mod;
        }

        return (int) result;
    }
}