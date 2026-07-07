// LeetCode 3754



// Approach 1 - Maths
// T.C. - O(log(n))
// S.C. - O(1)
class Solution {
    public long sumAndMultiply(int n) {
        int sum = 0;
        long num = 0;

        // here we'll get non-zero in reverse order
        while(n > 0){
            int d = n % 10;

            if(d != 0){
                sum += d;
                num = num * 10 + d;
            }

            n /= 10;
        }

        // so here we're restoring the order
        long orgNum = 0;
        while(num > 0){
            orgNum = (orgNum * 10) + (num % 10);
            num /= 10;
        }

        return orgNum * sum;
    }
}