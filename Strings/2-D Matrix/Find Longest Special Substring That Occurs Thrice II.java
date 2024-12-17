// LeetCode Medium - 2982


// Approach 1 - 2D matrix to store same character substrings
// T.C. - O(n + 26*n)
// S.C. - O(26*n)
class Solution {
    public int maximumLength(String s) {
        int n = s.length();
        int[][] subStringMatrix = new int[26][n+1];
        char prevChar = s.charAt(0);
        int length = 0;

        for(int i = 0; i<n; i++){
            char currChar = s.charAt(i);

            if(currChar == prevChar){
                length++;
                subStringMatrix[currChar - 'a'][length] += 1;
            }
            else{
                length = 1;
                subStringMatrix[currChar - 'a'][length] += 1;
                prevChar = currChar;
            }
        }

        int specialSubstringLength = -1;
        for(int row = 0; row<26; row++){ // O(26 * n) ~ O(n) i.e. 26 is const.
            int cummSum = 0;

            for(int col = n; col>0; col--){
                cummSum += subStringMatrix[row][col];
                
                if(cummSum >= 3){
                    // length will be the column number
                    specialSubstringLength = Math.max(specialSubstringLength, col);
                    break;
                }
            }
        }

        return specialSubstringLength;
    }
}