// LeetCode - 3314



// Approach 1 - Brute Force
// T.C. - O(n * Max(nums[i]))
// S.C. - O(1)
class Solution {
    public int[] minBitwiseArray(List<Integer> nums) {
        int n = nums.size();
        int[] result = new int[n];

        for(int i = 0; i<n; i++){
            int num = nums.get(i);
            int ans = -1;

            for(int j = 1; j<num; j++){
                if((j | j+1) == num){
                    ans = j;
                    break;
                }
            }

            result[i] = ans;
        }
        
        return result;
    }
}





// Approach 2 - Bit Manipulation
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

            for(int j = 1; j<32; j++){
                // if jth bit is set, skip
                if((num & (1 << j)) != 0){
                    continue;
                }

                // found an unset bit at position j
                // flipping (j-1)th bit
                // making previous bit (most significant) 0 in order to get the min x
                int prev = j-1;
                ans = num ^ (1 << (prev));
            }

            result[i] = ans;
        }
        
        return result;
    }
}