// LeetCode - 1695



// Approach 1 - Sliding Window + Set
// T.C. - O(2n)
// S.C. - O(n)
class Solution {
    public int maximumUniqueSubarray(int[] nums) {
        int n = nums.length;
        int i = 0;
        int j = 0;
        Set<Integer> set = new HashSet<>();
        int sum = 0;
        int maxSum = 0;

        while(j < n){
            while(set.contains(nums[j])){
                set.remove(nums[i]);
                sum -= nums[i];
                i++;
            }

            if(!set.contains(nums[j])){
                set.add(nums[j]);
                sum += nums[j];
                maxSum = Math.max(maxSum, sum);
            }

            j++;
        }

        return maxSum;
    }
}