// LeetCode - 976



// Approach - Sorting
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public int largestPerimeter(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        
        for(int i = n-1; i >= 2; i--){
            if(nums[i-2] + nums[i-1] > nums[i]){
                // it will be largest perimeter if triangle is valid
                // because of sorting
                return nums[i-2] + nums[i-1] + nums[i];
            }
        }

        return 0;
    }
}