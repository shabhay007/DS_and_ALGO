// LeetCode - 3487



// Approach 1 - Using Set
// T.C. - O(n)
// S.C. - O(n)
class Solution {
    public int maxSum(int[] nums) {
        int n = nums.length;
        Set<Integer> set = new HashSet<>();
        int maxSum = Integer.MIN_VALUE;
        int sum = 0;
        int min = Integer.MIN_VALUE;
        int zero = 0;

        for(int i = 0; i<n; i++){
            if(nums[i] >= 0 && !set.contains(nums[i])){
                if(nums[i] == 0){
                    zero++;
                }

                sum += nums[i];

                if(zero > 0 || sum > 0){
                    maxSum = Math.max(maxSum, sum);
                }
                
                set.add(nums[i]);
            }
            else{
                min = Math.max(min, nums[i]);
            }
        }

        return maxSum == Integer.MIN_VALUE ? min : maxSum;
    }
}





// Approach 2 - Using Set (Slight change in approach 1)
// T.C. - O(n)
// S.C. - O(n)
class Solution {
    public int maxSum(int[] nums) {
        int n = nums.length;
        Set<Integer> set = new HashSet<>();
        int maxSum = 0;
        int min = Integer.MIN_VALUE;

        for(int i = 0; i<n; i++){
            if(nums[i] >= 0 && !set.contains(nums[i])){
                maxSum += nums[i];
                set.add(nums[i]);
            }
            else{
                min = Math.max(min, nums[i]);
            }
        }

        return set.isEmpty() ? min : maxSum;
    }
}





// Approach 3 - Using Hash Array
// T.C. - O(2n)
// S.C. - O(1)
class Solution {
    public int maxSum(int[] nums) {
        int n = nums.length;
        int[] set = new int[101];
        Arrays.fill(set, -1);

        int maxSum = 0;
        int min = Integer.MIN_VALUE;

        for(int num : nums){
            if(num <= 0){
                min = Math.max(min, num);
            }
            else if(set[num] == -1){
                maxSum += num;
                set[num] = 1;
            }
            
        }

        return maxSum == 0 ? min : maxSum;
    }
}