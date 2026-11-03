// LeetCode - 3736



// Approach 1 - Sorting
// T.C. - O(nlog(n))
// S.C. - O(1)
class Solution {
    public int minMoves(int[] nums) {
        int n = nums.length;

        Arrays.sort(nums);

        int mid = nums[n-1];

        int moves = 0;
        for(int i = 0; i<n; i++){
            moves += Math.abs(mid - nums[i]);
        }

        return moves;
    }
}






// Approach 2 - Maths
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public int minMoves(int[] nums) {
        int n = nums.length;

        int max = 0;
        for(int i = 0; i<n; i++){
            max = Math.max(max, nums[i]);
        }

        int moves = 0;
        for(int i = 0; i<n; i++){
            moves += Math.abs(max - nums[i]);
        }

        return moves;
    }
}