// LeetCode - 3652



// Approach 1 - Brute Force
// T.C. - O(n * (n-k)) ~ O(n^2)
// S.C. - O(1)
class Solution {
    public long maxProfit(int[] prices, int[] strategy, int k) {
        int n = prices.length;
        long result = 0;

        // max profit with no modification
        for(int i = 0; i<n; i++){
            result += prices[i] * strategy[i];
        }

        // profit with modification
        int count = n-k+1;
        int i = 0;
        while(i < count){
            long res = 0;
            int left = i;
            int right = i+k-1;
            int mid = i + k/2;

            for(int j = 0; j<n; j++){
                if(j < left || j > right){
                    res += prices[j] * strategy[j];
                }
                else if(j >= mid){
                    res += prices[j];
                }
            }

            i++;
            result = Math.max(result, res);
        }

        return result;
    }
}





// Approach 2 - Sliding Window
// T.C : O(n)
// S.C : O(n)
class Solution {
    public long maxProfit(int[] prices, int[] strategy, int k) {
        int n = prices.length;

        long actualProfit = 0;
        long[] profit = new long[n]; // original profit of each day

        for (int idx = 0; idx < n; idx++) {
            profit[idx] = (long) strategy[idx] * prices[idx];
            actualProfit += profit[idx];
        }

        long originalWindowProfit = 0;
        long modifiedWindowProfit = 0;
        long maxGain = 0; // modifiedWindowProfit - originalWindowProfit

        int i = 0, j = 0;

        // Khandani sliding window technique
        while (j < n) {

            originalWindowProfit += profit[j];

            // Second half of the window contributes to modifiedWindowProfit
            if (j - i + 1 > k / 2) {
                modifiedWindowProfit += prices[j];
            }

            if (j - i + 1 > k) {
                originalWindowProfit -= profit[i];
                modifiedWindowProfit -= prices[i + k / 2];
                i++;
            }

            // Evaluate window of size k
            if (j - i + 1 == k) {
                maxGain = Math.max(maxGain, modifiedWindowProfit - originalWindowProfit);
            }

            j++;
        }

        return actualProfit + maxGain;
    }
}