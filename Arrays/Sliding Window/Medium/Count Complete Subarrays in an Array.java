// LeetCode Medium - 2799


// Approach 1 - Sliding Window
// T.C. - O(3n)
// S.C. - O(2n)
class Solution {
    public int countCompleteSubarrays(int[] nums) {
        int n = nums.length;
        Set<Integer> set = new HashSet<>();

        for(int num : nums){
            set.add(num);
        }

        int distinct = set.size();

        int l = 0;
        int r = 0;
        int numOfCompleteSubarrays = 0;
        Map<Integer, Integer> map = new HashMap<>();

        while(r < n){
            map.put(nums[r], map.getOrDefault(nums[r], 0) + 1);

            while(map.size() >= distinct){
                numOfCompleteSubarrays += n-r;

                // shrinking the window
                map.put(nums[l], map.get(nums[l]) - 1);

                if(map.get(nums[l]) == 0){
                    map.remove(nums[l]);
                }

                l++;
            }

            r++;
        }

        return numOfCompleteSubarrays;
    }
}