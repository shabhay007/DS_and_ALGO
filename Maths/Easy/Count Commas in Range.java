// LeetCode - 3870



// Approach 1 - Maths
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public int countCommas(int n) {
        if(n < 1000){
            return 0;
        }

        int result = 0;
        int i = 1000;
        // 100000

        while(i <= n){
            result++;
            i++;
        }

        return result;
    }
}





// Approach 2 - Maths
// T.C. - O(1)
// S.C. - O(1)
class Solution {
    public int countCommas(int n) {
        if(n < 1000){
            return 0;
        }

        return n - 999;
    }
}