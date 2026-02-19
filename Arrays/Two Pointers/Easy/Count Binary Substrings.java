// LeetCode - 696



// Approach 1 - Two Pointer Simulation
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public int countBinarySubstrings(String s) {
        int n = s.length();
        int prevCount = 0;
        int currCount = 1;
        int result = 0;

        for(int i = 1; i<n; i++){
            if(s.charAt(i) == s.charAt(i-1)){
                currCount++;
            }
            else{
                result += Math.min(prevCount, currCount);
                prevCount = currCount;
                currCount = 1;
            }
        }

        result += Math.min(prevCount, currCount);

        return result;
    }
}