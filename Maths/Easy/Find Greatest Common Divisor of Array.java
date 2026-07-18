// LeetCode - 1979



// Approach 1 - Sorting + Maths
// T.C. - O(nlogn + log(min(a, b)))
// S.C. - O(1)
class Solution {
    public int gcd(int num1, int num2){
        while(num2 != 0){
            int temp = num2;
            num2 = num1 % num2;
            num1 = temp;
        }

        return num1;
    }

    public int findGCD(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);

        return gcd(nums[0], nums[n-1]);
    }
}






// Approach 2 - Maths
// T.C. - O(n + log(min(a, b)))
// S.C. - O(1)
class Solution {
    public int gcd(int num1, int num2){
        while(num2 != 0){
            int temp = num2;
            num2 = num1 % num2;
            num1 = temp;
        }

        return num1;
    }

    public int findGCD(int[] nums) {
        int n = nums.length;
        int min = 1001;
        int max = 0;

        for(int i = 0; i<n; i++){
            min = Math.min(min, nums[i]);
            max = Math.max(max, nums[i]);
        }

        return gcd(min, max);
    }
}