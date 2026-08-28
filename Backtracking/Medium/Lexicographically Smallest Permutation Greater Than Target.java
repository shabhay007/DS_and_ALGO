// LeetCode - 3720



// Approach 1 - Greedy + backtracking
// T.C : O(n)
// S.C : O(n)
class Solution {
    int n;
    String result = "";

    public boolean solve(StringBuilder sb, int[] freq, String target, int i, boolean greater){
        if(i == n){
            if(greater){
                result = sb.toString();
                
                return true;
            }

            return false;
        }

        for(char ch = 'a'; ch <= 'z'; ch++){
            // do
            if(freq[ch - 'a'] == 0){
                continue;
            }

            if(!greater && ch < target.charAt(i)){
                continue;
            }

            sb.append(ch);
            freq[ch - 'a']--;

            // explore
            boolean isGreater = greater || ch > target.charAt(i);

            if(solve(sb, freq, target, i+1, isGreater)){
                return true;
            }

            // backtrack
            sb.deleteCharAt(sb.length() - 1);
            freq[ch - 'a']++;
        }

        return false;
    }

    public String lexGreaterPermutation(String s, String target) {
        n = s.length();
        int[] freq = new int[26];

        for(int i = 0; i<n; i++){
            char ch = s.charAt(i);
            freq[ch - 'a']++;
        }

        StringBuilder sb = new StringBuilder();

        solve(sb, freq, target, 0, false);

        return result;
    }
}