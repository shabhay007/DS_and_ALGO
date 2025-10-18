// LeetCode - 3397



// Approach 1 - Greedy
// T.C. - O(nlog(n) + n)
// S.C. - O(1)
class Solution {
    public int maxDistinctElements(int[] nums, int k) {
        int n = nums.length;
        Arrays.sort(nums);
        int count = 0;
        int prevVal = Integer.MIN_VALUE;

        for(int i = 0; i<n; i++){
            int minVal = nums[i] - k;
            int maxVal = nums[i] + k;

            if(prevVal < minVal){
                prevVal = minVal;
                count++;
            }
            else if(prevVal < maxVal){
                prevVal += 1;
                count++;
            }

        }

        return count;
    }
}