// LeetCode - 1007


// Approach - Greedy
// T.C. - O(6 * n)
// S.C. - O(1)
class Solution {
    public int find(int val, int[] tops, int[] bottoms){
        int n = tops.length;
        int topSwap = 0;
        int bottomSwap = 0;

        for(int i = 0; i<n; i++){
            if(tops[i] != val && bottoms[i] != val){
                return -1;
            }
            else if(tops[i] != val){
                topSwap++;
            }
            else if(bottoms[i] != val){
                bottomSwap++;
            }
        }

        return Math.min(topSwap, bottomSwap);
    }

    public int minDominoRotations(int[] tops, int[] bottoms) {
        int result = Integer.MAX_VALUE;

        for(int i = 1; i<=6; i++){
            int steps = find(i, tops, bottoms);

            if(steps != -1){
                result = Math.min(result, steps);
            }
        }

        return result == Integer.MAX_VALUE ? -1 : result;
    }
}