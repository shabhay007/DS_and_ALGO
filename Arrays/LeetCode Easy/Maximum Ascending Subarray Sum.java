// LeetCode Easy - 1800


// Brute Force
// T.C. - O(n^2)
// S.C. - O(1)
class Solution {
    public int maxAscendingSum(int[] nums) {
        int n = nums.length;
        int maxSum = 0;

        for(int i = 0; i<n; i++){
            int sum = nums[i];

            for(int j = i+1; j<n; j++){
                if(nums[j] > nums[j-1]){
                    sum += nums[j];

                    maxSum = Math.max(maxSum, sum);
                }
                else{
                    break;
                }
            }

            maxSum = Math.max(maxSum, sum);
        }

        return maxSum;
    }
}





// Optimal
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public int maxAscendingSum(int[] nums) {
        int n = nums.length;
        int maxSum = nums[0];
        int tempSum = nums[0];

        for(int i = 1; i<n; i++){
            if(nums[i] > nums[i-1]){
                tempSum += nums[i];
            }
            else{
                tempSum = nums[i];
            }

            maxSum = Math.max(maxSum, tempSum);
        }

        return maxSum;
    }
}