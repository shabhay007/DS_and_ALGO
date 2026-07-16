// LeetCode 3867



// Approach 1 - Maths + Simulation + Two Pointers
// T.C. - O(n * log(min(a, b)))
// S.C. - O(n)
class Solution {
    public int gcd(int num1, int num2){
        while(num2 != 0){
            int temp = num2;
            num2 = num1 % num2;
            num1 = temp;
        }

        return num1;
    }

    public long gcdSum(int[] nums) {
        int n = nums.length;

        // constructing prefix gcd array
        int[] prefixGcd = new int[n];
        int max = 0;

        for(int i = 0; i<n; i++){
            max = Math.max(max, nums[i]);
            prefixGcd[i] = gcd(max, nums[i]);
        }

        // sorting prefix gcd array
        Arrays.sort(prefixGcd);

        // forming pair as required
        int i = 0;
        int j = n-1;
        long gcdSum = 0;

        while(i < j){
            int pairGcd = gcd(prefixGcd[i], prefixGcd[j]);
            gcdSum += pairGcd;

            i++;
            j--;
        }

        return gcdSum;
    }
}