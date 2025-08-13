// LeetCode - 326



// Approach 1 - Recursion
// T.C. - O(log(base3)(n))
// S.C. - O(log(base3)(n))
class Solution {
    public boolean isPowerOfThree(int n) {
        if(n <= 0){
            return false;
        }

        if(n == 1){
            return true;
        }

        if(n % 3 != 0){
            return false;
        }

        return isPowerOfThree(n/3);
    }
}





// Approach 2 - Iterative
// T.C. - O(log(base3)(n))
// S.C. - O(1)
class Solution {
    public boolean isPowerOfThree(int n) {
        if(n <= 0){
            return false;
        }

        while(n % 3 == 0){
            n /= 3;
        }

        return n == 1;
    }
}





// Approach 3 - Maths (Using logarithms and checking if log base 3 of n is an integer)
// T.C. - O(1)
// S.C. - O(1)
class Solution {
    public boolean isPowerOfThree(int n) {
        if(n <= 0){
            return false;
        }

        double x = Math.log10(n) / Math.log10(3);

        return x == (int) x;
    }
}





// Approach 4 - checking with largest valid power of 3
// T.C. - O(1)
// S.C. - O(1)
class Solution {
    public boolean isPowerOfThree(int n) {
        //NOTE :  3^19 = 1162261467
        return n > 0 && (1162261467 % n) == 0;
    }
}