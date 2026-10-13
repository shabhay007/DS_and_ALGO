// LeetCode - 3727



// Approach 1 - Greedy
// T.C. - O(n + nlog(n))
// T.C. - O(1)
class Solution {
    public long maxAlternatingSum(int[] nums) {
        int n = nums.length;

        // making -ve nums to +ve
        for(int i = 0; i<n; i++){
            if(nums[i] < 0){
                nums[i] *= -1;
            }
        }

        Arrays.sort(nums);

        long result = 0;
        int i = 0;
        int j = n-1;

        while(i <= j){
            if(i < j){
                result = result + (1L * nums[j] * nums[j]) - (1L * nums[i] * nums[i]);
            }
            else{
                result += 1L * nums[i] * nums[i];
            }

            i++;
            j--;
        }

        return result;
    }
}