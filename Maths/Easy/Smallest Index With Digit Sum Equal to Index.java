// LeetCode - 3550



// Approach 1 - Maths
// T.C. - O(n * d); d = no. of digits
// S.C. - O(1)
class Solution {
    public int getDigitSum(int num){
        int sum = 0;

        while(num > 0){
            sum += num % 10;
            num /= 10;
        }

        return sum;
    }
    
    public int smallestIndex(int[] nums) {
        int n = nums.length;

        for(int i = 0; i<n; i++){
            int digitSum = getDigitSum(nums[i]);

            if(digitSum == i){
                return i;
            }
        }

        return -1;
    }
}