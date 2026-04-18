// LeetCode - 3783



// Approach 1 - Maths
// T.C. - O(log(n))
// S.C. - O(1)
class Solution {
    public int getReverse(int num){
        int result = 0;

        while(num > 0){
            int digit = num % 10;
            result = result * 10 + digit;
            num /= 10;
        }

        return result;
    }

    public int mirrorDistance(int n) {
        return Math.abs(n - getReverse(n));
    }
}