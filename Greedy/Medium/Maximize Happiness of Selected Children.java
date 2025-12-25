// LeetCode - 3075



// Approach 1 - Greedy + Sorting
// T.C. - O(nlog(n) + k)
// S.C. - O(1)
class Solution {
    public long maximumHappinessSum(int[] happiness, int k) {
        int n = happiness.length;
        Arrays.sort(happiness);

        long result = 0;
        int negCount = 0;
        int i = 0;

        while(i < k){
            result += Math.max(happiness[n-1-i] - negCount, 0);
            negCount++;
            i++;
        }

        return result;
    }
}