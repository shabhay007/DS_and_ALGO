// LeetCode :- 2461 - Medium



// Brute Force - Gives TLE - Sliding Window + HashSet
class Solution {
    public long maximumSubarraySum(int[] nums, int k) {
        int n = nums.length;
        int maxSum = Integer.MIN_VALUE;

        for(int i = 0; i<n-k+1; i++){
            HashSet<Integer> set = new HashSet<>();
            int sum = 0;

            for(int j = 0; j<k; j++){
                if(!set.contains(nums[i+j])){
                    set.add(nums[i+j]);
                    sum += nums[i+j];
                }
                else{
                    continue;
                }
            }

            if(set.size() == k){
                maxSum = Math.max(maxSum, sum);
            }
        }

        return maxSum == Integer.MIN_VALUE ? 0 : maxSum;
    }
}



// Optimal - O(n) - Sliding Window + HashSet
class Solution {
    public long maximumSubarraySum(int[] nums, int k) {
        int n = nums.length;
        long maxSum = Long.MIN_VALUE;

        int i = 0;
        int j = 0;
        HashSet<Integer> set = new HashSet<>();
        long sum = 0;

        // All the elements are visited only two times one time by ith pointer 
        // and one time by jth pointer
        // So, overall time complexity will be O(2 * n)
        while(j < n){
            // Shrinking the window if duplicate element is found
            while(set.contains(nums[j])){
                sum -= nums[i];
                set.remove(nums[i]);
                i++;
            }

            set.add(nums[j]);
            sum += nums[j];

            // Updating maxSum, when correct window found
            if(j-i+1 == k){
                maxSum = Math.max(maxSum, sum);
                sum -= nums[i];
                set.remove(nums[i]);
                i++;
            }

            j++;
        }

        return maxSum == Long.MIN_VALUE ? 0 : maxSum;
    }
}
