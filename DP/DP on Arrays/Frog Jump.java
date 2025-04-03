// LeetCode Hard - 403


// Approach 1 - Recursion + Memoization
// T.C. - O(n^2)
// S.C. - O(n^2)
class Solution {
    public boolean isPossible(int currStoneIndex, int prevJump, int stones[], int n, Map<Integer, Integer> map, Boolean[][] dp){
        if(currStoneIndex == n-1){
            return true;
        }

        if(dp[currStoneIndex][prevJump] != null){
            return dp[currStoneIndex][prevJump];
        }

        boolean canCrossTheRiver = false;

        for(int jump = prevJump-1; jump < prevJump+2; jump++){
            if(jump > 0){
                int nextStone = stones[currStoneIndex] + jump;

                if(map.containsKey(nextStone)){
                    int nextStoneIndex = map.get(nextStone);
                    canCrossTheRiver = canCrossTheRiver || isPossible(nextStoneIndex, jump, stones, n, map, dp);
                }
            }
        }

        dp[currStoneIndex][prevJump] = canCrossTheRiver;
        return canCrossTheRiver;
    }

    public boolean canCross(int[] stones) {
        int n = stones.length;

        if(stones[1] != 1){
            return false;
        }

        // Map<Element, Index>
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i<n; i++){
            map.put(stones[i], i);
        }

        Boolean[][] dp = new Boolean[n+1][n+1];

        return isPossible(0, 0, stones, n, map, dp);
    }
}