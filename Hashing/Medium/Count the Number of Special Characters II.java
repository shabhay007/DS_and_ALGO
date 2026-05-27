// LeetCode - 3121



// Approach 1 - Comparing last occurence of lowerCase and first occurence of upperCase
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public int numberOfSpecialChars(String word) {
        int n = word.length();
        
        // storing last occurence index of lowercase
        int[] lastLower = new int[26];
        Arrays.fill(lastLower, -1);

        // storing first occurence index of uppercase
        int[] firstUpper = new int[26];
        Arrays.fill(firstUpper, -1);

        for(int i = 0; i<n; i++){
            char ch = word.charAt(i);

            if(Character.isLowerCase(ch)){
                lastLower[ch - 'a'] = i;
            }
            else if(firstUpper[ch - 'A'] == -1){
                firstUpper[ch - 'A'] = i;
            }
        }

        // processing
        int count = 0;
        for(int i = 0; i<26; i++){
            if(lastLower[i] != -1 && firstUpper[i] != -1 && lastLower[i] < firstUpper[i]){
                count++;
            }
        }

        return count;
    }
}