// LeetCode Medium - 1014


// Brute Force - Gives TLE
// T.C. - O(n^2)
// S.C. - O(1)
class Solution {
    public int maxScoreSightseeingPair(int[] values) {
        int n = values.length;
        int maxScore = 0;

        for(int i = 0; i<n; i++){
            for(int j = i+1; j<n; j++){
                maxScore = Math.max(maxScore, (values[i] + values[j] + i -j));
            }
        }

        return maxScore;
    }
}



// DP
// T.C. - O(n)
// S.C. - O(n)
class Solution {
    public int maxScoreSightseeingPair(int[] values) {
        int n = values.length;
        int maxScore = 0;

        int[] maxTillIdx = new int[n];
        maxTillIdx[0] = values[0];

        for(int i = 1; i<n; i++){
            maxTillIdx[i] = Math.max(maxTillIdx[i-1], values[i] + i);
        }

        for(int i = 1; i<n; i++){
            maxScore = Math.max(maxScore, maxTillIdx[i-1] + values[i] - i);
        }

        return maxScore;
    }
}



// DP - Space Optimized
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public int maxScoreSightseeingPair(int[] values) {
        int n = values.length;
        int maxScore = 0;

        int maxTillIdx = values[0];

        for(int i = 1; i<n; i++){
            // calculating current pair score
            maxScore = Math.max(maxScore, maxTillIdx + values[i] - i);

            // updating max value so far
            maxTillIdx = Math.max(maxTillIdx, values[i] + i);
        }

        return maxScore;
    }
}