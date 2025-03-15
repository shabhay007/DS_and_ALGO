// LeetCode Medium - 2560


// Approach 1 - Backtracking (Gives TLE)
// T.C. - O(2^n)
// S.C. - O(n + k)
class Solution {
    public int getMinCapability(int idx, int[] nums, int n, int k, List<Integer> list){
        if(k == 0){
            int max = 0;

            for(int amount : list){
                max = Math.max(max, amount);
            }

            return max;
        }

        if(idx >= n){
            return Integer.MAX_VALUE;
        }

        list.add(nums[idx]);
        int take = getMinCapability(idx + 2, nums, n, k-1, list);
        list.remove(list.size() - 1);

        int skip = getMinCapability(idx + 1, nums, n, k, list);

        return Math.min(take, skip);
    }

    public int minCapability(int[] nums, int k) {
        int n = nums.length;

        return getMinCapability(0, nums, n, k, new ArrayList<>());
    }
}





// Approach 2 - Recursion + Memoization (Gives MLE)
// T.C. - O(n * k)
// S.C. - O(n * k)
class Solution {
    public int getMinCapability(int idx, int[] nums, int n, int k, int dp[][]){
        if(k == 0){
            return 0; // no more house can be robbed
        }

        if(idx >= n){
            return Integer.MAX_VALUE;
        }

        if(dp[idx][k] != -1){
            return dp[idx][k];
        }

        int take = Math.max(nums[idx], getMinCapability(idx + 2, nums, n, k-1, dp));

        int skip = getMinCapability(idx + 1, nums, n, k, dp);

        return dp[idx][k] = Math.min(take, skip);
    }

    public int minCapability(int[] nums, int k) {
        int n = nums.length;
        int dp[][] = new int[n+1][k+1];

        for(int row[] : dp){
            Arrays.fill(row, -1);
        }

        return getMinCapability(0, nums, n, k, dp);
    }
}






// Approach 3 (Optimal) - Binary Search on answers
// T.C. - O(n + n.log(max))
// S.C. - O(1)
class Solution {
    public boolean isPossible(int mid, int[] nums, int k, int n){
        int count = 0;
        int i = 0;

        while(i < n){
            if(nums[i] <= mid){
                count++;
                i += 2; // skipping adjacent houses
            }
            else{
                i++;
            }
        }

        // is possible to rob atleast k houses or not
        return count >= k;
    }

    public int minCapability(int[] nums, int k) {
        int n = nums.length;

        int min = Integer.MAX_VALUE;
        int max = 0;

        for(int num : nums){ // O(n)
            min = Math.min(min, num);
            max = Math.max(max, num);
        }

        int l = min;
        int r = max;
        int minCapability = Integer.MAX_VALUE;

        while(l <= r){ // O(log(max))
            int mid = l + (r - l)/2;

            if(isPossible(mid, nums, k, n)){ // O(n)
                minCapability = mid;
                r = mid - 1;
            }
            else{
                l = mid + 1;
            }
        }

        return minCapability;
    }
}