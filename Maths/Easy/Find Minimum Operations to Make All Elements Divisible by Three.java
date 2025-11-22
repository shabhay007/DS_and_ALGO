// LeetCode - 3190




// Approach 1 - Maths
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public int minimumOperations(int[] nums) {
        int result = 0;

        for(int num : nums){
            if(num % 3 != 0){
                result++;
            }
        }

        return result;
    }
}