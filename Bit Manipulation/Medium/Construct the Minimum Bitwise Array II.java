// LeetCode - 3315 (Higher version of 3314)



// For brute force check problem 3314 (Bit Manipulation -> Easy)



// Approach Optimal - Bit Manipulation
// T.C. - O(n * 32)
// S.C. - O(1)
class Solution {
    public int[] minBitwiseArray(List<Integer> nums) {
        int n = nums.size();
        int[] result = new int[n];

        for(int i = 0; i<n; i++){
            int num = nums.get(i);

            if(num == 2){
                result[i] = -1;
                continue;
            }

            int ans = -1;

            for(int j = 0; j<32; j++){
                // if jth bit is set, skip
                if((num & (1 << j)) != 0){
                    continue;
                }
                
                // found an unset bit at position j
                // flipping (j-1)th bit
                // making previous bit (most significant) 0 in order to get the min x
                ans = num ^ (1 << (j-1));
                break;
            }

            result[i] = ans;
        }

        return result;
    }
}