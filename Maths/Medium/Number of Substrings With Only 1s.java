// LeetCode - 1513



// Approach 1 - Maths
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public int numSub(String s) {
        int n = s.length();
        int mod = (int) 1e9 + 7;
        long result = 0;
        int oneCount = 0;

        for(int i = 0; i<n; i++){
            char ch = s.charAt(i);

            if(ch == '1'){
                oneCount++;
            }
            else{
                result = (result + (oneCount * (oneCount + 1L) / 2) % mod) % mod;
                oneCount = 0;
            }
        }

        // for last run
        result = (result + (oneCount * (oneCount + 1L) / 2) % mod) % mod;

        return (int) result;
    }
}