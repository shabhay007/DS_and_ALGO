// LeetCode - 3024



// Approach 1
// T.C. - O(1)
// S.C. - O(1)
class Solution {
    public String triangleType(int[] nums) {
        int a = nums[0];
        int b = nums[1];
        int c = nums[2];

        // condition for a valid triangle
        if(a + b > c && a + c > b && b + c > a){
            if(a == b && b == c) return "equilateral";
            else if(a == b || b == c || a == c) return "isosceles";
            else return "scalene";
        }

        return "none";
    }
}