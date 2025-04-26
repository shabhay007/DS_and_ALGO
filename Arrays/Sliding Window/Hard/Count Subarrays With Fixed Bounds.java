// LeetCode 2444


// Approach 1 - Brute Force (Gives TLE)
// T.C. - O(n^3)
// S.C. - O(1)
class Solution {
    public boolean isValid(int start, int end, int[] nums, int minK, int maxK){
        int min = Integer.MAX_VALUE;
        int max = 0;

        for(int i = start; i <= end; i++){
            min = Math.min(min, nums[i]);
            max = Math.max(max, nums[i]);
        }

        return (min == minK && max == maxK);
    }

    public long countSubarrays(int[] nums, int minK, int maxK) {
        int n = nums.length;
        int count = 0;

        for(int i = 0; i<n; i++){
            for(int j = i; j<n; j++){
                if(isValid(i, j, nums, minK, maxK)){
                    count++;
                }
            }
        }

        return count;
    }
}




// Approach 2 (Better) - Gives TLE
// T.C. - O(n^2)
// S.C. - O(1)
class Solution {
    public long countSubarrays(int[] nums, int minK, int maxK) {
        int n = nums.length;
        
        long result = 0;

        for(int i = 0; i<n; i++){
            int currMin = nums[i];
            int currMax = nums[i];

            for(int j = i; j<n; j++){
                currMin = Math.min(currMin, nums[j]);
                currMax = Math.max(currMax, nums[j]);

                if(currMin == minK && currMax == maxK){
                    result++;
                }
            }
        }

        return result;
    }
}





// Approach 3 (Optimal) - Sliding Window
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public long countSubarrays(int[] nums, int minK, int maxK) {
        int n = nums.length;
        int minPosition = -1;
        int maxPosition = -1;
        int culpritPosition = -1;

        long result = 0;

        for(int i = 0; i<n; i++){
            if(nums[i] < minK || nums[i] > maxK){
                culpritPosition = i;
            }

            if(nums[i] == minK){
                minPosition = i;
            }

            if(nums[i] == maxK){
                maxPosition = i;
            }

            long smallerIdx = Math.min(minPosition, maxPosition);
            long tempCount = smallerIdx - culpritPosition;
            result += (tempCount <= 0) ? 0 : tempCount;
        }

        return result;
    }
}