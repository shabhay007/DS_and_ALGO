// LeetCode - 2958



// Approach 1 - Sliding Window
// T.C. - O(n)
// S.C. - O(n)
class Solution {
    public int maxSubarrayLength(int[] nums, int k) {
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        int i = 0;
        int j = 0;
        int size = 0;

        while(j < n){
            map.put(nums[j], map.getOrDefault(nums[j], 0) + 1);

            if(map.get(nums[j]) <= k){
                size = Math.max(size, j-i+1);
            }

            while(i < j && map.get(nums[j]) > k){
                map.put(nums[i], map.get(nums[i]) - 1);

                if(map.get(nums[i]) == 0){
                    map.remove(nums[i]);
                }

                i++;
            }

            j++;
        }

        return size;
    }
}