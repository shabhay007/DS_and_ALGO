// LeetCode Medium - 368


// Approach 1 - Recursion + Backtracking
// T.C. - O(2^n * L); L = avg length of all subsets
// S.C. - O(3n)
class Solution {
    public void solve(int i, int nums[], int n, List<Integer> temp, List<Integer> result){
        if(i >= n){
            if(temp.size() > result.size()){
                result.clear();
                result.addAll(temp);
            }

            return;
        }
        
        // take
        if(temp.isEmpty() || nums[i] % temp.get(temp.size() - 1) == 0){
            temp.add(nums[i]);
            solve(i + 1, nums, n, temp, result);
            temp.remove(temp.size() - 1);
        }

        // skip
        solve(i + 1, nums, n, temp, result);
    }

    public List<Integer> largestDivisibleSubset(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);

        List<Integer> result = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();

        solve(0, nums, n, temp, result);

        return result;
    }
}





// Approach 2 - Bottom Up
// T.C. - O(n.log(n) + n^2)
// S.C. - O(2n)
class Solution {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);

        int[] dp = new int[n];
        int[] prevIndex = new int[n];

        Arrays.fill(dp, 1);
        Arrays.fill(prevIndex, -1);

        int maxLength = 1;
        int lastElementIndex = 0;

        for(int i = 0; i<n; i++){
            for(int j = 0; j<i; j++){
                if(nums[i] % nums[j] == 0){
                    if(dp[i] < dp[j] + 1){
                        dp[i] = dp[i] + 1;
                        prevIndex[i] = j;
                    }
                    
                    if(dp[i] > maxLength){
                        maxLength = dp[i];
                        lastElementIndex  = i;
                    }
                }
            }
        }

        List<Integer> result = new ArrayList<>();
        while(lastElementIndex != -1){
            result.add(nums[lastElementIndex]);
            lastElementIndex = prevIndex[lastElementIndex];
        }

        return result;
    }
}