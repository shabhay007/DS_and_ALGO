// LeetCode - 2411



// Approach 1 - Bit Manipulation
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public int[] smallestSubarrays(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        int[] setBitIndex = new int[32];
        Arrays.fill(setBitIndex, -1);

        for(int i = n-1; i>=0; i--){
            int endIndex = i;

            // nums[i] = binary representation
            for(int j = 0; j<32; j++){
                
                // if jth bit is not set
                if((nums[i] & (1 << j)) == 0){
                    if(setBitIndex[j] != -1){
                        endIndex = Math.max(endIndex, setBitIndex[j]);
                    }
                }
                else{
                    setBitIndex[j] = i;
                }
            }

            result[i] = endIndex - i + 1;
        }

        return result;
    }
}