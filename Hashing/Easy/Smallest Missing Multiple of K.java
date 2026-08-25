// LeetCode - 3718



// Approach 1 - Hashing
// T.C. - O(n)
// S.C. - O(n)
class Solution {
    public int missingMultiple(int[] nums, int k) {
        int n = nums.length;
        Set<Integer> set = new HashSet<>();

        for(int i = 0; i<n; i++){
            set.add(nums[i]);
        }

        int result = 0;
        int i = 1;
        while(set.contains(k * i)){
            i++;
        }

        return k * i;
    }
}