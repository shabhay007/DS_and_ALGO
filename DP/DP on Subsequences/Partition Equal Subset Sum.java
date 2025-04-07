// LeetCode Medium - 416


// Approach 1 - Backtracking (Gives TLE)
// T.C. - O(2^n * L)
// S.C. - O(2n)
class Solution {
    public boolean partition(int i, int[] nums, int n, List<Integer> list, int totalSum){
        if(i == n){
            int sum = 0;

            for(int idx : list){ // O(L)
                sum += nums[idx];
            }

            return sum * 2 == totalSum;
        }

        // take
        list.add(i);

        if(partition(i+1, nums, n, list, totalSum)){
            return true;
        }

        list.remove(list.size() - 1);

        // skip
        if(partition(i+1, nums, n, list, totalSum)){
            return true;
        }

        return false;
    }

    public boolean canPartition(int[] nums) {
        int n = nums.length;

        int totalSum = 0;

        for(int num : nums){
            totalSum += num;
        }

        return partition(0, nums, n, new ArrayList<>(), totalSum);
    }
}





// Approach 2 - Recursion (Gives TLE)
// T.C. - O(2^n)
// S.C. - O(n)
class Solution {
    public Boolean partition(int i, int[] nums, int n, int target){
        if(i == n || target < 0){
            return false;
        }

        if(target == 0){
            return true;
        }

        return (partition(i+1, nums, n, target - nums[i]) || partition(i+1, nums, n, target));
    }

    public boolean canPartition(int[] nums) {
        int n = nums.length;

        int totalSum = 0;

        for(int num : nums){
            totalSum += num;
        }

        if(totalSum % 2 != 0){
            return false;
        }

        int target = totalSum / 2;

        return partition(0, nums, n, target);
    }
}





// Approach 3 - Recursion + Memoization
// T.C. - O(n * target)
// S.C. - O(n * target)
class Solution {
    public Boolean partition(int i, int[] nums, int n, int target, Boolean[][] dp){
        if(i == n || target < 0){
            return false;
        }

        if(target == 0){
            return true;
        }

        if(dp[i][target] != null){
            return dp[i][target];
        }

        return dp[i][target] = (partition(i+1, nums, n, target - nums[i], dp) || 
                    partition(i+1, nums, n, target, dp));
    }

    public boolean canPartition(int[] nums) {
        int n = nums.length;

        int totalSum = 0;

        for(int num : nums){
            totalSum += num;
        }

        if(totalSum % 2 != 0){
            return false;
        }

        int target = totalSum / 2;

        Boolean[][] dp = new Boolean[n+1][target+1];

        return partition(0, nums, n, target, dp);
    }
}