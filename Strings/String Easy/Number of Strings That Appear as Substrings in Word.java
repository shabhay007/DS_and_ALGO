// LeetCode - 1967



// Approach 1 - String Utility Function
// T.C. - O(n^2)
// S.C. - O(0)
class Solution {
    public int numOfStrings(String[] patterns, String word) {
        int count = 0;

        for(String s : patterns){
            if(word.contains(s)){
                count++;
            }
        }

        return count;
    }
}