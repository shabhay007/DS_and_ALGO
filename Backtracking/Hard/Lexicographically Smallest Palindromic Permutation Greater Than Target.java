// LeetCode - 3734



// Approach 1 - Greedy + Backtracking
// T.C. - O(n)
// S.C. - O(n)
class Solution {
    int halfLen;
    String result = "";
    char oddChar = '$';

    public boolean isPalindrome(StringBuilder sb){
        String org = sb.toString();
        String reverse = sb.reverse().toString();

        return org.equals(reverse);
    }

    public boolean solve(StringBuilder curr, int[] freq, String target, int i, boolean greater){
        if(curr.length() == halfLen){
            StringBuilder candidate = new StringBuilder(curr); // left half
            StringBuilder rightHalf = new StringBuilder(curr).reverse();
            
            if(oddChar != '$'){
                candidate.append(oddChar);
            }

            candidate.append(rightHalf);

            if(candidate.toString().compareTo(target) > 0){
                result = candidate.toString();
                return true;
            }

            return false;
        }

        for(char ch = 'a'; ch <= 'z'; ch++){
            if(freq[ch - 'a'] == 0){
                continue;
            }

            if(!greater && ch < target.charAt(i)){
                continue;
            }

            curr.append(ch);
            freq[ch - 'a']--;

            // exploring
            boolean isGreater = greater || ch > target.charAt(i);

            if(solve(curr, freq, target, i+1, isGreater)){
                return true;
            }

            // backtrack
            curr.deleteCharAt(curr.length() - 1);
            freq[ch - 'a']++;
        }

        return false;
    }

    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int[] freq = new int[26];

        for(int i = 0; i<n; i++){
            char ch = s.charAt(i);
            freq[ch - 'a']++;
        }

        // checking if palindrome is possible or not
        int odd = 0;
        for(int i = 0; i<26; i++){
            if(freq[i] % 2 == 1){
                odd++;
                oddChar = (char) (i + 'a');
            }
        }

        // not possible to make palindrome
        if(odd > 1){
            return "";
        }

        // in case palindrome is possible
        // taking half of each even freq. and the odd char for left half
        for(int i = 0; i<26; i++){
            freq[i] /= 2;
        }

        halfLen = n/2;
        StringBuilder curr = new StringBuilder();
        solve(curr, freq, target, 0, false);

        return result;
    }
}