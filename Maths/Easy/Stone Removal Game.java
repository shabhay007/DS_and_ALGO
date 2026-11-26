// LeetCode - 3360



// Approach 1 - Enumeration + Bit Manipulation
// T.C. - (1)
// S.C. - (1)
class Solution {
    public boolean canAliceWin(int n) {
        if(n < 10){
            return false;
        }

        int chance = 0;
        int curr = 10;
        boolean result = true;

        while(n >= 0){
            if(chance == 0 && curr > n){
                result = false;
            }
            else if(chance == 1 && curr > n){
                result = true;
            }

            n -= curr;
            curr--;
            chance ^= 1;
        }

        return result;
    }
}