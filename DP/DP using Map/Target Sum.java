// LeetCode - 494


// Recursion
// T.C. - O(2^n)
// S.C. - O(recursion height/depth)
class Solution {
    public int recursion(int i, int sum, int[] nums, int target){
        if(i == nums.length){
            if(sum == target){
                return 1;
            }

            return 0;
        }

        int takeSum = recursion(i+1, sum + nums[i], nums, target);
        int takeDifference = recursion(i+1, sum - nums[i], nums, target);

        return takeSum + takeDifference; // total ways to reach target
    }

    public int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        return recursion(0, sum, nums, target);
    }
}



// DP - Memoization using Map
// T.C. - O(n * totalSum)
// S.C. - O(n * totalSum)
class Solution {
    public int recursion(int i, int sum, int[] nums, int target, HashMap<String, Integer> map){
        if(i == nums.length){
            if(sum == target){
                return 1;
            }

            return 0;
        }

        // StringBuilder sb = new StringBuilder();
        // sb.append(i).append("_").append(sum);
        // String key = sb.toString();

        String key = i + "_" + sum;

        if(map.containsKey(key)){
            return map.get(key);
        }

        int takeSum = recursion(i+1, sum + nums[i], nums, target, map);
        int takeDifference = recursion(i+1, sum - nums[i], nums, target, map);

        int ways = takeSum + takeDifference;
        map.put(key, ways);

        return ways;
    }

    public int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        HashMap<String, Integer> map = new HashMap<>();

        return recursion(0, sum, nums, target, map);
    }
}



// DP - Memoization using 2-Matrix
// if we take minus(-) for all elements the sum will be, let's say -k
// and if we take + for all elements then the sum will become k
// and the range will be [-k, k]
// if we add the total sum (k) to avoid -ve sum, the range becomes [0, k+k];
// This concept is used here

// T.C. - O(n * totalSum)
// S.C. - O(n * totalSum)
class Solution {
    public int recursion(int i, int currSum, int[] nums, int target, int[][] dp, int totalSum){
        if(i == nums.length){
            if(currSum == target){
                return 1;
            }

            return 0;
        }

        // adding totalSum in currSum to avoid -ve index because of -ve currSum
        if(dp[i][currSum + totalSum] != -1){
            return dp[i][currSum + totalSum];
        }

        int takeSum = recursion(i+1, currSum + nums[i], nums, target, dp, totalSum);
        int takeDifference = recursion(i+1, currSum - nums[i], nums, target, dp, totalSum);

        return dp[i][currSum + totalSum] = takeSum + takeDifference;
    }

    public int findTargetSumWays(int[] nums, int target) {
        int n = nums.length;
        int totalSum = 0;
        for(int num : nums){
            totalSum += num;
        }

        int sum = 0;
        int[][] dp = new int[n][2*totalSum + 1];
        for(int[] row : dp){
            Arrays.fill(row, -1);
        }

        return recursion(0, sum, nums, target, dp, totalSum);
    }
}