// LeetCode Hard - 3041



// Approach 1 - Sorting + DP (Memoization)
// T.C. - O(nlog(n))
// S.C. - O(n)
class Solution {
    int n;

    // binary search
    public int find(int i, int val, int[] nums){
        int idx = -1;
        int l = i;
        int r = n-1;

        while(l <= r){
            int mid = l + (r - l)/2;

            if(nums[mid] == val){
                idx = mid;
                r = mid - 1; // first occurance
            }
            else if(nums[mid] > val){
                r = mid - 1;
            }
            else{
                l = mid + 1;
            }
        }

        return idx;
    }

    public int solve(int i, int increment, int[] nums, int[][] dp){
        if(i >= n){
            return 0;
        }

        int len = 1;
        int currVal = nums[i] + increment; // inc = 0/1

        if(dp[i][increment] != -1){
            return dp[i][increment];
        }

        // 1st option -> find currVal+1
        int j = find(i+1, currVal+1, nums);
        if(j != -1){
            len = Math.max(len, 1 + solve(j, 0, nums, dp));
        }

        // 2nd option -> find currVal  and then inc by 1
        j = find(i+1, currVal, nums);
        if(j != -1){
            len = Math.max(len, 1 + solve(j, 1, nums, dp));
        }

        return dp[i][increment] = len;
    }

    public int maxSelectedElements(int[] nums) {
        n = nums.length;

        Arrays.sort(nums);

        int result = 1;
        int[][] dp = new int[n+1][2]; // increment will be 0/1
        for(int[] row : dp){
            Arrays.fill(row, -1);
        }

        for(int i = 0; i<n; i++){
            result = Math.max(result, Math.max(solve(i, 0, nums, dp), 
                                        solve(i, 1, nums, dp)));
        }

        return result;
    }
}






// Approach 2 - Sorting + DP (Bottom Up)
// T.C. - O(nlog(n))
// S.C. - O(n)
class Solution {
    int n;

    // binary search
    public int find(int i, int val, int[] nums){
        int idx = -1;
        int l = i;
        int r = n-1;

        while(l <= r){
            int mid = l + (r - l)/2;

            if(nums[mid] == val){
                idx = mid;
                r = mid - 1; // first occurance
            }
            else if(nums[mid] > val){
                r = mid - 1;
            }
            else{
                l = mid + 1;
            }
        }

        return idx;
    }

    public int maxSelectedElements(int[] nums) {
        n = nums.length;

        Arrays.sort(nums);

        int result = 1;
        int[][] dp = new int[n+1][2]; // increment will be 0/1
        
        for(int i = n-1; i >= 0; i--){
            for(int inc = 0; inc < 2; inc++){
                int currVal = nums[i] + inc;
                int len = 1;

                // case 1 - finding currVal + 1 directly
                int j = find(i+1, currVal+1, nums);
                if(j != -1){
                    len = Math.max(len, 1 + dp[j][0]); // j is future idx
                }

                // case 2 - finding currVal then incremented by 1
                j = find(i+1, currVal, nums);
                if(j != -1){
                    len = Math.max(len, 1 + dp[j][1]);
                }

                dp[i][inc] = len;
                result = Math.max(result, dp[i][inc]);
            }
        }

        return result;
    }
}