// LeetCode Easy - 1422


// Approach 1 - Brute Force
// T.C. - O(n^2)
// S.C. - O(1);
class Solution {
    public int count(int idx, String s){
        int zeroInLeft = (s.charAt(0) == '0' ? 1 : 0);
        int oneInRight = (s.charAt(s.length() - 1) == '1' ? 1 : 0);

        for(int i = 1; i<s.length()-1; i++){
            if(i < idx && s.charAt(i) == '0'){
                zeroInLeft++;
            }

            if(i >= idx && s.charAt(i) == '1'){
                oneInRight++;
            }
        }

        return zeroInLeft + oneInRight;
    }

    public int maxScore(String s) {
        int n = s.length();
        int maxCount = 0;

        for(int i = 0; i<n; i++){
            maxCount = Math.max(maxCount, count(i, s));
        }

        return maxCount;
    }
}




// Approach 2 - Better - 2 pass
// T.C. - O(2n)
// S.C. - O(1)
class Solution {
    public int maxScore(String s) {
        int n = s.length();
        int maxScore = 0;
        int totalOnesCount = 0;

        for(int i = 0; i<n; i++){
            if(s.charAt(i) == '1'){
                totalOnesCount++;
            }
        }

        int zeroInLeft = 0;
        int oneInLeft = 0;
        for(int i = 0; i<n-1; i++){
            if(s.charAt(i) == '0'){
                zeroInLeft++;
            }
            else{
                oneInLeft++;
            }

            maxScore = Math.max(maxScore, zeroInLeft + (totalOnesCount - oneInLeft));
        }

        return maxScore;
    }
}




// Approach 3 - Optimal - One pass
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public int maxScore(String s) {
        int n = s.length();
        int maxScore = Integer.MIN_VALUE;

        int ones = 0;
        int zeroes = 0;
        for(int i = 0; i<n-1; i++){
            if(s.charAt(i) == '1'){
                ones++;
            }
            else{
                zeroes++;
            }
            
            maxScore = Math.max(maxScore, zeroes - ones);
        }


        // if last char is 1 then increments ones
        // and if last char is 0, then no increments as it makes sure that both splitted string
        // is not empty as well
        if(s.charAt(n-1) == '1'){
            ones++;
        }

        return maxScore + ones;
    }
}