// LeetCode - 3302



// Approach 1 - Two Pointer
// T.C. - O(n)
// S.C. - O(n)
class Solution {
    public int[] validSequence(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();

        int[] matchingCharsInRight = new int[n];
        int matched = 0;
        int i = n-1;
        int j = m-1;

        while(i >= 0){
            if(j >= 0 && word1.charAt(i) == word2.charAt(j)){
                matched++;
                j--;
            }

            matchingCharsInRight[i] = matched;
            i--;
        }

        int[] result = new int[m];
        i = 0;
        j = 0;
        int k = 0;
        boolean changePower = true; // can change only one character

        while(i < n && j < m){
            if(word1.charAt(i) == word2.charAt(j)){
                result[k++] = i;
                j++;
            }
            else if(changePower && i+1 < n && matchingCharsInRight[i+1] >= m-j-1){
                changePower = false;
                result[k++] = i;
                j++;
            }

            i++;
        }

        return j == m ? result : new int[0];
    }
}