// LeetCode - 2144



// Approach 1 - Greedy
// T.C. - O(nlog(n))
// S.C. - O(1)
class Solution {
    public int minimumCost(int[] cost) {
        int n = cost.length;
        int minCost = 0;

        // sorting so that min cost candy among 3 comes left to the 2nd min among them
        Arrays.sort(cost);

        int i = n-1;
        int count = 0; // to track if idx is multiple of 3

        while(i >= 0){ // descending traversal
            count++;

            if(count % 3 != 0){
                minCost += cost[i];
            }

            i--;
        }

        return minCost;
    }
}