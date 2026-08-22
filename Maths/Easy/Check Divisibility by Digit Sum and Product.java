// LeetCode - 3622



// Approach 1 - Maths
// T.C. - O(d); d = no of digits in n
// S.C. - O(1)
class Solution {
    public int getDigitSumAndProduct(int n){
        int sum = 0;
        int product = 1;

        while(n > 0){
            int d = n % 10;
            sum += d;
            product *= d;
            n /= 10;
        }

        return sum + product;
    }

    public boolean checkDivisibility(int n) {
        return (n % getDigitSumAndProduct(n)) == 0;
    }
}