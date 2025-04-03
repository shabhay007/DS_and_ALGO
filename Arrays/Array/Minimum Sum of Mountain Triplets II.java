// LeetCode Medium - 2909


// Approach 1 (Brute Force) - Gives TLE
// T.C. - O(n^3)
// S.C. - O(1)
class Solution {
    public int minimumSum(int[] nums) {
        int n = nums.length;

        int minSum = Integer.MAX_VALUE;

        for(int i = 0; i<n; i++){
            for(int j = i+1; j<n; j++){
                for(int k = j+1; k<n; k++){
                    if(nums[i] < nums[j] && nums[k] < nums[j]){
                        minSum = Math.min(minSum, nums[i] + nums[j] + nums[k]);
                    }
                }
            }
        }

        return (minSum == Integer.MAX_VALUE) ? -1 : minSum;
    }
}




// Approach 2 (Optimal) - Prefix min & Suffix min
// T.C. - O(3n)
// S.C. - O(2n)
class Solution {
    public int minimumSum(int[] nums) {
        int n = nums.length;
        int[] leftMin = new int[n];
        int[] rightMin = new int[n];

        // populating leftMin
        leftMin[0] = Integer.MAX_VALUE;

        for(int i = 1; i<n; i++){
            leftMin[i] = Math.min(leftMin[i-1], nums[i-1]);
        }

        // populating rightMin
        rightMin[n-1] = Integer.MAX_VALUE;

        for(int i = n-2; i >= 0; i--){
            rightMin[i] = Math.min(rightMin[i+1], nums[i+1]);
        }

        // finding the min sum of mountain triplet
        int minSum = Integer.MAX_VALUE;

        for(int i = 1; i<n-1; i++){
            if(leftMin[i] < nums[i] && rightMin[i] < nums[i]){
                int sum = leftMin[i] + nums[i] + rightMin[i];
                minSum = Math.min(minSum, sum);
            }
        }

        return (minSum == Integer.MAX_VALUE) ? -1 : minSum;
    }
}