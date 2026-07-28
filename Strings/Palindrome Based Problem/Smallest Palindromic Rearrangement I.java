// LeetCode Medium - 3517



// Approach 1 - Frequency count + StringBuilder
// T.C. - O(n)
// S.C. - O(n)
class Solution {
    public String smallestPalindrome(String s) {
        int n = s.length();

        if(n == 1){
            return s;
        }

        int[] freq = new int[26];

        for(char ch : s.toCharArray()){
            freq[ch - 'a']++;
        }

        StringBuilder sb = new StringBuilder();
        char oddCh = '*';

        for(int i = 0; i<26; i++){
            char ch = (char) (i + 'a');

            if(freq[i] % 2 != 0){
                oddCh = ch;
            }

            for(int j = 0; j<freq[i]/2; j++){
                sb.append(ch);
            }
        }

        StringBuilder firstHalf = new StringBuilder(sb);

        if(oddCh != '*'){
            sb.append(oddCh);
        }

        return sb.append(new StringBuilder(firstHalf).reverse()).toString();
    }
}