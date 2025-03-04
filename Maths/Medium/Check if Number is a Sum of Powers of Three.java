// LeetCode Medium - 1780


// Approach 1 - Recursion
// T.C. - O(log3(n))
// S.C. - O(log3(n))
class Solution {
    public boolean solve(int start, int n){
        if(n == 0){
            return true;
        }

        if(n < 0){
            return false;
        }

        for(int power = start; (int) Math.pow(3, power) <= n; power++){
            if(solve(power + 1, n - (int) Math.pow(3, power))){
                return true;
            }
        }

        return false;
    }

    public boolean checkPowersOfThree(int n) {
        return solve(0, n);
    }
}




// Approach 2
// T.C. - O(log3(n))
// S.C. - O(1)
class Solution {
    public boolean checkPowersOfThree(int n) {
        int power = 0;

        while(Math.pow(3, power) <= n){
            power++;
        }

        while(n > 0){
            if(n >= Math.pow(3, power)){
                n -= Math.pow(3, power);
            }

            // means we have to use the same power again
            if(n >= Math.pow(3, power)){
                return false;
            }

            power--;
        }

        return true;
    }
}




// Approach 3
// T.C. - O(log3(n))
// S.C. - O(1)
class Solution {
    public boolean checkPowersOfThree(int n) {
        while(n > 0){
            // See MIK's lecture from 23:00 (Amazing Explanation)
            // Related to Ternary representation of a number i.e. num/3 like
            // in binary rep. we use num/2
            if(n % 3 == 2){
                return false;
            }

            n = n/3;
        }

        return true;
    }
}