// LeetCode Medium - 1262



// Approach 1 - Greedy + Sorting
// T.C. - O(nlog(n))
// S.C. - O(n)
class Solution {
    public int maxSumDivThree(int[] nums) {
        int n = nums.length;
        
        // contains numbers which gives remainder 1 upon divide by 3
        List<Integer> rem1 = new ArrayList<>();

        // contains numbers which gives remainder 2 upon divide by 3
        List<Integer> rem2 = new ArrayList<>();
        int sum = 0;

        for(int num : nums){
            sum += num;

            if(num % 3 == 1){
                rem1.add(num);
            }
            else if(num % 3 == 2){
                rem2.add(num);
            }
        }

        if(sum % 3 == 0){
            return sum;
        }

        Collections.sort(rem1);
        Collections.sort(rem2);

        int result = 0;
        int rem = sum % 3; // 1 or 2

        if(rem == 1){
            int remove1 = (rem1.size() >= 1) ? rem1.get(0) : Integer.MAX_VALUE;
            int remove2 = (rem2.size() >= 2) ? rem2.get(0) + rem2.get(1) : Integer.MAX_VALUE;

            result = Math.max(result, sum - Math.min(remove1, remove2));
        }
        else{
            int remove1 = (rem2.size() >= 1) ? rem2.get(0) : Integer.MAX_VALUE;
            int remove2 = (rem1.size() >= 2) ? rem1.get(0) + rem1.get(1) : Integer.MAX_VALUE;

            result = Math.max(result, sum - Math.min(remove1, remove2));
        }

        return result;
    }
}






// Approach 2 - DP (Memoization)
// T.C. - O(n * 3)
// S.C. - O(n)
class Solution {
    public int getMax(int i, int[] nums, int n, int rem, int[][] dp){
        if(i == n){
            if(rem == 0) return 0;

            return Integer.MIN_VALUE;
        }

        if(dp[i][rem] != -1){
            return dp[i][rem];
        }

        int take = nums[i] + getMax(i+1, nums, n, (rem + nums[i]) % 3, dp);
        int skip = getMax(i+1, nums, n, rem, dp);

        return dp[i][rem] = Math.max(take, skip);
    }

    public int maxSumDivThree(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n][3]; // rem can be 0, 1, 2

        for(int[] row : dp){
            Arrays.fill(row, -1);
        }
        
        return getMax(0, nums, n, 0, dp);
    }
}






// Approach 3 - DP (Bottom Up)
// T.C. - O(n * 3)
// S.C. - O(n * 3)
class Solution {
    public int maxSumDivThree(int[] nums) {
        int n = nums.length;

        // maximum sum we can obtain using elements from index i to end (n-1) 
        // such that the total sum has remainder r when divided by 3
        int[][] dp = new int[n+1][3]; // rem can be 0, 1, 2

        // for index out of bounds as we need (i+1) to find for i
        dp[n][0] = 0;
        dp[n][1] = Integer.MIN_VALUE; // invalid value
        dp[n][2] = Integer.MIN_VALUE; // invalid value

        for(int i = n-1; i >= 0; i--){
            for(int rem = 0; rem <= 2; rem++){
                int newR = (rem + nums[i]) % 3;

                // take -> fun(i+1, newR)
                int take = (dp[i+1][newR] == Integer.MIN_VALUE) 
                        ? Integer.MIN_VALUE 
                        : nums[i] + dp[i+1][newR];

                // skip -> fun(i+1, old rem)
                int skip = dp[i+1][rem];

                dp[i][rem] = Math.max(take, skip);
            }
        }
        
        return dp[0][0]; // it will store the best answer;
    }
}