// LeetCode - 2598



// Approach - Map + Greedy
// T.C. - O(n)
// S.C. - O(n)


/*
    i.) Find % of all numbers -> store in map
    ii.) start from mex = 0
    iii.) if map doesn't contain mex % val; else mex++
*/
class Solution {
    public int findSmallestInteger(int[] nums, int value) {
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();

        for(int x : nums){
            int num = ((x % value) + value) % value;

            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        int result = -1;
        int mex = 0;

        // most important
        // till count of mex exist in map, w'll check for mex
        while(map.getOrDefault(mex % value, 0) > 0){
            map.put(mex % value, map.get(mex % value) - 1);
            mex++;
        }

        return mex;
    }
}