// LeetCode Medium - 2579



// Approach 1 - Power of 4
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public long coloredCells(int n) {
        if(n == 1){
            return 1;
        }

        long numOfColoredCells = 1;

        for(int i = 1; i < n; i++){
            numOfColoredCells += 4 * i;
        }

        return numOfColoredCells;
    }
}




// Approach 2 - sum of n-1 natural numbers
// 1 + (4 * 1) + (4 * 2) + (4 * 3) ....
// 1 + 4((n-1).(n-1 + 1) / 2);
// T.C. - O(1)
// S.C. - O(1)
class Solution {
    public long coloredCells(int n) {
        long res = n;
        return 1 + res * (res-1) * 2;
    }
}