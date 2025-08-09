// LeetCode - 231



// Approach 1 - Bit Manipulation
// T.C. - O(log(n))
// S.C. - O(1)
class Solution {
    public boolean isPowerOfTwo(int n) {
        for(int i = 0; (1L << i) <= n; i++){ // 1L to avoid integer overflow
            if((1L << i) == n){
                return true;
            }
        }

        return false;
    }
}





// Approach 2 - Recursion
// T.C. - O(log (base 2) (n))
// S.C. - O(log (base 2) (n))
class Solution {
    public boolean isPowerOfTwo(int n) {
        if(n <= 0){ // in case of -ve
            return false;
        }

        if(n == 1){ // if fully divisible by 2
            return true;
        }

        if(n % 2 == 1){ // in case of odd
            return false;
        }

        return isPowerOfTwo(n/2);
    }
}