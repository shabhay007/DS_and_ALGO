// LeetCode - 3300



// Approach 1 - Maths
// T.C. - O(n * log(digits))
// S.C. - O(1)
class Solution {
    public int sumOfDigits(int num){
        int sum = 0;

        while(num > 0){
            sum += num % 10;
            num /= 10;
        }
        
        return sum;
    }

    public int minElement(int[] nums) {
        int n = nums.length;
        int min = Integer.MAX_VALUE;

        for(int i = 0; i<n; i++){
            min = Math.min(min, sumOfDigits(nums[i]));
        }

        return min;
    }
}