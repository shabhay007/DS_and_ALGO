// LeetCode Medium - 2976



// Approach 1 - Floyd Warshall
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public void floydWarshall(long[][] adjMatrix, char[] original, char[] changed, int[] cost){
        // filling the matrix
        for(int i = 0; i<original.length; i++){
            int s = original[i] - 'a';
            int t = changed[i] - 'a';

            adjMatrix[s][t] = Math.min(adjMatrix[s][t], (long) cost[i]);
        }

        // now applying floyd warshall
        for(int k = 0; k<26; k++){ // O(1)
            for(int i = 0; i<26; i++){
                for(int j = 0; j<26; j++){
                    adjMatrix[i][j] = Math.min(adjMatrix[i][j], adjMatrix[i][k] + adjMatrix[k][j]);
                }
            }
        }
    }

    public long minimumCost(String source, String target, char[] original, char[] changed, int[] cost) {
        long[][] adjMatrix = new long[26][26];

        // initializing with infinity
        for(long[] row : adjMatrix){
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        floydWarshall(adjMatrix, original, changed, cost);

        // now processing
        long result = 0;
        for(int i = 0; i<source.length(); i++){
            char chi = source.charAt(i);
            char chj = target.charAt(i);

            if(chi == chj){
                continue;
            }

            if(adjMatrix[chi - 'a'][chj - 'a'] == Integer.MAX_VALUE){
                return -1;
            }

            result += adjMatrix[chi - 'a'][chj - 'a'];
        }

        return result;
    }
}