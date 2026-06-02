// LeetCode - 3633



// Approach 1 - Brute Force
// T.C. - O(n^2)
// S.C. - O(1)
class Solution {
    public int earliestFinishTime(int[] landStartTime, int[] landDuration, int[] waterStartTime, int[] waterDuration) {
        int n = landDuration.length;
        int m = waterDuration.length;
        int min = Integer.MAX_VALUE;

        // land rides first
        for(int i = 0; i<n; i++){
            int landFinish = landStartTime[i] + landDuration[i];

            for(int j = 0; j<m; j++){
                int waterBegin = Math.max(landFinish, waterStartTime[j]);
                int totalFinish = waterBegin + waterDuration[j];

                min = Math.min(min, totalFinish);
            }
        }

        // water rides first
        for(int j = 0; j<m; j++){
            int waterFinish = waterStartTime[j] + waterDuration[j];

            for(int i = 0; i<n; i++){
                int landBegin = Math.max(waterFinish, landStartTime[i]);
                int totalFinish = landBegin + landDuration[i];
                
                min = Math.min(min, totalFinish);
            }
        }

        return min;
    }
}