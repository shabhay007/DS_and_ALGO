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





// Approach 3 - Bit Manipulation
// T.C. - O(1)
// S.C. - O(1)

/*
    If n is power of 2, then there will only be one set bit
    eg. 2 - 10,  16, - 10000, ....

    So, when we perform & opration between n and (n-1) (not power of two), 
    then because of n-1, all the bits will be 0

    And we can say that n is power of 2.
*/

class Solution {
    public boolean isPowerOfTwo(int n) {
        if(n <= 0){
            return false;
        }

        return (n & (n-1)) == 0;
    }
}






// Approach 4 - Bit Manipulation
// T.C. - O(log(n))
// S.C. - O(1)

/*
    If n is power of 2, then there will only be one set bit
    eg. 2 - 10,  16, - 10000, ....

    So, when we perform & opration between n and (n-1) (not power of two), 
    then because of n-1, all the bits will be 0

    And we can say that n is power of 2.

    Because of this same concept, we can also say that, if a number has only
    1 set bit then it will be power of 2, else not
*/

class Solution {
    public boolean isPowerOfTwo(int n) {
        if(n <= 0){
            return false;
        }

        return Integer.bitCount(n) == 1; // internally it will take O(log(n))
    }
}