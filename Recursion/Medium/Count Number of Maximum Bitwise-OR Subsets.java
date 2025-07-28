// LeetCode - 2044



// My Approach 1 - Using Map
// T.C. - O(2^n)
// S.C. - O(n)
class Solution {
    public void getMaxOR(int i, int[] nums, Map<Integer, Integer> map, int currOr){
        if(i >= nums.length){
            map.put(currOr, map.getOrDefault(currOr, 0) + 1);
            return;
        }

        getMaxOR(i+1, nums, map, currOr | nums[i]);
        getMaxOR(i+1, nums, map, currOr);
    }

    public int countMaxOrSubsets(int[] nums) {
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();

        getMaxOR(0, nums, map, 0);

        int max = 0;

        for(int count : map.values()){
            max = Math.max(max, count);
        }

        return max;
    }
}





// Approach 2
// T.C. - O(2^n)
// S.C. - O(n)
class Solution {
    public int getMaxOR(int i, int[] nums, int currOr, int maxOr){
        if(i >= nums.length){
            if(currOr == maxOr){
                return 1;
            }

            return 0;
        }

        int take = getMaxOR(i+1, nums, currOr | nums[i], maxOr);
        int skip = getMaxOR(i+1, nums, currOr, maxOr);

        return take + skip;
    }

    public int countMaxOrSubsets(int[] nums) {
        int n = nums.length;
        int maxOr = 0;

        for(int num : nums){
            maxOr |= num;
        }

        return getMaxOR(0, nums, 0, maxOr);
    }
}





// Approach 3 - Memoization
// T.C. - O(n * maxOr)
// S.C. - O(n)
class Solution {
    public int getMaxOR(int i, int[] nums, int currOr, int maxOr, int[][] dp){
        if(i >= nums.length){
            if(currOr == maxOr){
                return 1;
            }

            return 0;
        }

        if(dp[i][currOr] != -1){
            return dp[i][currOr];
        }

        int take = getMaxOR(i+1, nums, currOr | nums[i], maxOr, dp);
        int skip = getMaxOR(i+1, nums, currOr, maxOr, dp);

        return dp[i][currOr] = take + skip;
    }

    public int countMaxOrSubsets(int[] nums) {
        int n = nums.length;
        int maxOr = 0;

        for(int num : nums){
            maxOr |= num;
        }

        int[][] dp = new int[n+1][maxOr+1];

        for(int[] row : dp){
            Arrays.fill(row, -1);
        }

        return getMaxOR(0, nums, 0, maxOr, dp);
    }
}