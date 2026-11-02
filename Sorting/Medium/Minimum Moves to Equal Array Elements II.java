// LeetCode - 462



// Approach 1 - Sorting + Maths
// T.C. - O(nlog(n))
// S.C. - O(1)
class Solution {
    public int minMoves2(int[] nums) {
        int n = nums.length;

        Arrays.sort(nums);
        long mid = -1;

        if(n % 2 != 0){
            mid = nums[n/2];
        }
        else{
            mid = (nums[n/2 - 1] + nums[n/2]) / 2;
        }

        long moves = 0;
        for(int i = 0; i<n; i++){
            moves += Math.abs(mid - nums[i]);
        }

        return (int) moves;
    }
}






// Approach 2 - Slight improvement on approach 1
// T.C. - O(nlog(n))
// S.C. - O(1)
class Solution {
    public int minMoves2(int[] nums) {
        int n = nums.length;

        Arrays.sort(nums);
        long mid = nums[n/2];;

        long moves = 0;
        for(int i = 0; i<n/2; i++){
            moves += Math.abs(mid - nums[i]);
            moves += Math.abs(nums[n-i-1] - mid);
        }

        return (int) moves;
    }
}