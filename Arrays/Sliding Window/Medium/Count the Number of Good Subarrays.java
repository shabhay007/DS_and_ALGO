// LeetCode Medium - 2537


// Approach 1 (Brute Force) - Processing all subarrays
// T.C. - O(n^4)
// S.C. - O(1)
class Solution {
    public boolean isGoodSubarray(int start, int end, int[] nums, int k){
        long count = 0;

        for(int i = start; i<= end; i++){
            for(int j = i+1; j<= end; j++){
                if(nums[i] == nums[j]){
                    count++;
                }
            }
        }

        return count >= k;
    }

    public long countGood(int[] nums, int k) {
        int n = nums.length;
        long count = 0;

        for(int i = 0; i<n; i++){
            for(int j = i; j<n; j++){
                if(isGoodSubarray(i, j, nums, k)){
                    count++;
                }
            }
        }

        return count;
    }
}




// Approach 2 - Sliding Window
// T.C. - O(2n)
// S.C. - O(n)
class Solution {
    public long countGood(int[] nums, int k) {
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>(); // element -> count

        int l = 0;
        int r = 0;

        long result = 0;
        long pair = 0;

        while(r < n){
            pair += map.getOrDefault(nums[r], 0); // how many times, we have seen nums[r]
            map.put(nums[r], map.getOrDefault(nums[r], 0) + 1);

            while(pair >= k){
                result += (n - r);

                map.put(nums[l], map.get(nums[l]) - 1);
                pair -= map.get(nums[l]);

                l++;
            }

            r++;
        }

        return result;
    }
}