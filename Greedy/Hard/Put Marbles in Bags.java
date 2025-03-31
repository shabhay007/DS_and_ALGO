// LeetCode Hard - 2551


// Approach 1 - Sorting + Greedy
// T.C. - O(n + nlog(n) + k)
// S.C. - O(n)
class Solution {
    public long putMarbles(int[] weights, int k) {
        int n = weights.length;
        int pair = n - 1;
        int[] pairSum = new int[pair];

        for(int i = 0; i<pair; i++){ // O(n)
            pairSum[i] = weights[i] + weights[i+1];
        }

        Arrays.sort(pairSum); // nlog(n)

        long maxSum = 0;
        long minSum = 0;

        for(int i = 0; i<k-1; i++){
            minSum += pairSum[i];
            maxSum += pairSum[pair - 1 - i];
        }

        return maxSum - minSum;
    }
}