// LeetCode Medium - 300


// Recursion + Memoization(DP)
// T.C. - O(n^2)
// S.C. - O(n^2)
class Solution {
    public int getMaxLength(int idx, int[] nums, int n, int prevIdx, int[][] dp){
        if(idx >= n){
            return 0;
        }

        if(prevIdx != -1 && dp[idx][prevIdx] != -1){
            return dp[idx][prevIdx];
        }

        int take = 0;

        if(prevIdx == -1 || nums[idx] > nums[prevIdx]){
            take = 1 + getMaxLength(idx + 1, nums, n, idx, dp);
        }

        int skip = getMaxLength(idx + 1, nums, n, prevIdx, dp);

        if(prevIdx != -1){
            dp[idx][prevIdx] = Math.max(take, skip);
        }

        return Math.max(take, skip);
    }

    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int prevIdx = -1;

        int[][] dp = new int[n+1][n+1];
        
        for(int[] row : dp){
            Arrays.fill(row, -1);
        }

        return getMaxLength(0, nums, n, prevIdx, dp);
    }
}





// Tabulation (Bottom UP)
// T.C. - O(n + n^2)
// S.C. - O(n)
class Solution {
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;

        // dp[i] = length of LIS ends on dp[i]
        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        int maxLIS = 1;

        for(int i = 0; i<n; i++){
            int length = 0;

            for(int j = 0; j<i; j++){
                if(nums[j] < nums[i]){
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    maxLIS = Math.max(maxLIS, dp[i]);
                }
            }
        }

        return maxLIS;
    }
}




// Most Optimal - Lazy/Patience Sorting
// T.C. - O(nlog(n))
// S.C. - O(n)
class Solution {
    public int lowerBound(List<Integer> arr, int target){
        int n = arr.size();
        int result = -1;

        int l = 0;
        int r = n-1;

        while(l <= r){
            int mid = l + (r-l)/2;

            if(arr.get(mid) >= target){
                result = mid;
                r = mid - 1;
            }
            else{
                l = mid + 1;
            }
        }

        return result;
    }

    public int lengthOfLIS(int[] nums) {
        int n = nums.length;

        List<Integer> sorted = new ArrayList<>();

        for(int i = 0; i<n; i++){
            int reqIndex = lowerBound(sorted, nums[i]); // O(log(n))

            // we have not found any element just greater or equal to nums[i] in sorted list
            // add a new entry of the curr element
            // or else replace the element in sorted list
            if(reqIndex == -1){
                sorted.add(nums[i]);
            }
            else{
                sorted.set(reqIndex, nums[i]); // replacing
            }
        }

        return sorted.size();
    }
}