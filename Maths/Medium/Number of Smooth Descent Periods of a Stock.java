// LeetCode - 2110



// Approach 1 - Maths
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public long getDescentPeriods(int[] prices) {
        int n = prices.length;
        int length = 1;
        long result = 1;

        for(int i = 1; i<n; i++){
            if(prices[i] + 1 == prices[i-1]){
                length++;
                result += length;
            }
            else{
                // for one length subarray
                result++;
                length = 1;
            }
        }

        return result;
    }
}