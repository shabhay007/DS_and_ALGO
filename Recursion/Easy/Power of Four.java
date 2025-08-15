// LeetCode - 342



// Approach 1 - Recursion
// T.C. - O(log4(n))
// S.C. - O(log4(n))
class Solution {
    public boolean isPowerOfFour(int n) {
        if(n <= 0){
            return false;
        }

        if(n == 1){
            return true;
        }

        if(n % 4 != 0){
            return false;
        }

        return isPowerOfFour(n/4);
    }
}




// Approach 2 - Iterative
// T.C. - O(log4(n))
// S.C. - O(1)
class Solution {
    public boolean isPowerOfFour(int n) {
        if(n <= 0){
            return false;
        }
        
        while(n % 4 == 0){
            n /= 4;
        }

        return n == 1;
    }
}





// Approach 3 - Logarithmic Math
// T.C. - O(1)
// S.C. - O(1)
class Solution {
    public boolean isPowerOfFour(int n) {
        if(n <= 0){
            return false;
        }
        
        double x = Math.log10(n) / Math.log10(4);

        return x == (int) x;
    }
}