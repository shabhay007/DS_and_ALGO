// LeetCode - 2016



// Approach 1 - Brute Force
// T.C. - O(n^2)
// S.C. - O(1)
class Solution {
    public int maximumDifference(int[] nums) {
        int n = nums.length;
        int maxDiff = -1;

        for(int i = 0; i<n; i++){
            for(int j = i+1; j<n; j++){
                if(nums[i] < nums[j]){
                    maxDiff = Math.max(maxDiff, (nums[j] - nums[i]));
                }
            }
        }

        return maxDiff;
    }
}





// Approach 2 - Optimal
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public int maximumDifference(int[] nums) {
        int n = nums.length;
        int maxDiff = -1;
        int min = Integer.MAX_VALUE;
        int pos = -1;

        for(int i = 0; i<n; i++){
            if(nums[i] < min){
                min = nums[i];
                pos = i;
            }
            
            if(pos != -1 && pos < i && nums[pos] < nums[i]){
                maxDiff = Math.max(maxDiff, nums[i] - min);
            }
        }

        return maxDiff;
    }
}






// Approach 3 - Optimal
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public int maximumDifference(int[] nums) {
        int n = nums.length;
        int maxDiff = -1;
        int min = nums[0];

        for(int i = 1; i<n; i++){
            if(nums[i] > min){
                maxDiff = Math.max(maxDiff, nums[i] - min);
            }
            else{
                min = nums[i];
            }
        }

        return maxDiff;
    }
}