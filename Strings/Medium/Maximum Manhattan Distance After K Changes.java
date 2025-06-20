// LeetCode - 3443



// Approach 1
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public int maxDistance(String s, int k) {
        int n = s.length();
        int N = 0;
        int S = 0;
        int E = 0;
        int W = 0;
        int maxD = 0;

        for(int i = 0; i<n; i++){
            char ch = s.charAt(i);

            if(ch == 'N'){
                N++;
            }
            else if(ch == 'S'){
                S++;
            }
            else if(ch == 'E'){
                E++;
            }
            else{
                W++;
            }

            int mD = Math.abs(N - S) + Math.abs(E - W);

            int steps = i+1;
            int stepsWasted = steps - mD;

            int extra = 0;

            if(stepsWasted != 0){ // steps != mD
                extra = Math.min(2*k, stepsWasted);
            }

            int finalMD = mD + extra;

            maxD = Math.max(maxD, finalMD);
        }

        return maxD;
    }
}