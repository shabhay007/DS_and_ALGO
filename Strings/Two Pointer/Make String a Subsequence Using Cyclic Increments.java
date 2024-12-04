// LeetCode Medium - 2825


// Approach 1
class Solution {
    public char cyclicShift(char ch){ // performs cyclic shifts
        return (char) ((ch - 'a' + 1) % 26 + 'a');
    }

    public boolean canMakeSubsequence(String str1, String str2) {
        int m = str1.length();
        int n = str2.length();
        
        // if length of str2 is greater, it can't be a subsequence of str1
        if(m < n) return false;

        int j = 0;

        for(int i = 0; i<m && j<n; i++){
            char ch = str2.charAt(j);

            if(ch == str1.charAt(i) || ch == cyclicShift(str1.charAt(i))){
                j++;
            }
        }

        return j == n;
    }
}



// Approach 2
class Solution {
    public boolean canMakeSubsequence(String str1, String str2) {
        int m = str1.length();
        int n = str2.length();

        // if length of str2 is greater, it can't be a subsequence of str1
        if(m < n) return false;

        int i = 0;
        int j = 0;

        while(i < m && j < n){
            char ch = str2.charAt(j);

            // str1.charAt(i) - 25 = 'z', for cyclic nature
            if(str1.charAt(i) == ch || str1.charAt(i) + 1 == ch || str1.charAt(i) - 25 == ch){
                j++;
            }

            i++;
        }

        return j == n;
    }
}