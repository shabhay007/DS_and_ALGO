// LeetCode - 1464



// Approach 1 - Enumeration
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public int maxProduct(int[] nums) {
        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;

        for(int num : nums){
            if(num > max1){
                max2 = max1;
                max1 = num;
            }
            else if(num > max2){
                max2 = num;
            }
        }

        return (max1 - 1) * (max2 - 1);
    }
}