// LeetCode Hard - 689


// Recursion - Gives TLE
// T.C. - O(n + 2^count * n + 2^count * n)
// S.C. - O(n)
class Solution {
    public int sumUsingRecursion(int i, int k, int[] subArraySum, int count){
        if(count == 0){
            return 0;
        }

        if(i >= subArraySum.length){
            return Integer.MIN_VALUE; // Invalid
        }

        int take = subArraySum[i] + sumUsingRecursion(i+k, k, subArraySum, count-1);
        int notTake = sumUsingRecursion(i+1, k, subArraySum, count);

        return Math.max(take, notTake);
    }

    public void helper(int i, int k, int[] subArraySum, int count, ArrayList<Integer> result){
        if(count == 0){
            return;
        }

        if(i >= subArraySum.length){
            return;
        }

        int take = subArraySum[i] + sumUsingRecursion(i+k, k, subArraySum, count-1);
        int notTake = sumUsingRecursion(i+1, k, subArraySum, count);

        if(take >= notTake){
            result.add(i);
            helper(i+k, k, subArraySum, count-1, result);
        }
        else{
            helper(i+1, k, subArraySum, count, result);
        }
    }

    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int n = nums.length;

        // Sliding window to calculate sum of sub arrays
        int[] subArraySum = new int[n];
        int i = 0, j = 0;
        int windowSum = 0;

        while(j < n){
            windowSum += nums[j];

            if(j-i+1 == k){
                subArraySum[i] = windowSum;
                windowSum -= nums[i];
                i++;
            }
            
            j++;
        }

        int count = 3;
        ArrayList<Integer> result = new ArrayList<>();

        // now passing subArraySum instead nums array
        helper(0, k, subArraySum, count, result);

        // Converting ArrayList to int[]
        int[] intArray = result.stream().mapToInt(m -> m).toArray();

        return intArray;
    }
}




// DP - MEMOIZATION
// T.C. - O(n + n + n)
// S.C. - O(n * 3) ~ O(n)
class Solution {
    // O(n * m) ~ O(n), since m = 3 i.e. constant
    public int sumUsingRecursion(int i, int k, int[] subArraySum, int count, int[][] dp){ 
        if(count == 0){
            return 0;
        }

        if(i >= subArraySum.length){
            return Integer.MIN_VALUE; // Invalid
        }

        if(dp[i][count] != -1){
            return dp[i][count];
        }

        int take = subArraySum[i] + sumUsingRecursion(i+k, k, subArraySum, count-1, dp);
        int notTake = sumUsingRecursion(i+1, k, subArraySum, count, dp);

        return dp[i][count] = Math.max(take, notTake);
    }

    // O(n)
    public void helper(int i, int k, int[] subArraySum, int count, ArrayList<Integer> result, int[][] dp){
        if(count == 0){
            return;
        }

        if(i >= subArraySum.length){
            return;
        }

        int take = subArraySum[i] + sumUsingRecursion(i+k, k, subArraySum, count-1, dp);
        int notTake = sumUsingRecursion(i+1, k, subArraySum, count, dp);

        if(take >= notTake){
            result.add(i);
            helper(i+k, k, subArraySum, count-1, result, dp);
        }
        else{
            helper(i+1, k, subArraySum, count, result, dp);
        }
    }

    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int n = nums.length;

        // Sliding window to calculate sum of sub arrays
        int[] subArraySum = new int[n];
        int i = 0, j = 0;
        int windowSum = 0;

        while(j < n){ // O(n)
            windowSum += nums[j];

            if(j-i+1 == k){
                subArraySum[i] = windowSum;
                windowSum -= nums[i];
                i++;
            }
            
            j++;
        }

        int count = 3;
        ArrayList<Integer> result = new ArrayList<>();

        // For memoization
        int[][] dp = new int[n+1][3+1];
        for(int[] row : dp){
            Arrays.fill(row, -1);
        }

        // now passing subArraySum instead nums array
        helper(0, k, subArraySum, count, result, dp);

        // Converting ArrayList to int[]
        int[] intArray = result.stream().mapToInt(m -> m).toArray();

        return intArray;
    }
}