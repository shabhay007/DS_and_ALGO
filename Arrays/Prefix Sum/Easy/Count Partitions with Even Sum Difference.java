// LeetCode - 3432



// Approach 1 - Prefix sum
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public int countPartitions(int[] nums) {
        int n = nums.length;
        int sum = 0;

        for(int num : nums){
            sum += num;
        }

        int partitions = 0;
        int currSubSum = 0;

        for(int i = 0; i<n-1; i++){
            currSubSum += nums[i];
            int rightSubSum = sum - currSubSum;
            int diff = currSubSum - rightSubSum;

            if(diff % 2 == 0){
                partitions++;
            }
        }

        return partitions;
    }
}