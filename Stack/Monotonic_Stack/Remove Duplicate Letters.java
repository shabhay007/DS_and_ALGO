// LeetCode Medium - 316 (Same as 1081 - Smallest Subsequence of Distinct Characters)



// Approach 1 - Greedy + Monotonic Stack properties used in StringBuilder
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public String removeDuplicateLetters(String s) {
        int n = s.length();
        int[] lastIndex = new int[26];
        
        for(int i = 0; i<n; i++){
            char ch = s.charAt(i);
            lastIndex[ch - 'a'] = i;
        }

        int[] currIndex = new int[26];
        Arrays.fill(currIndex, -1);

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i<n; i++){
            char ch = s.charAt(i);

            if(currIndex[ch - 'a'] != -1){
                continue;
            }

            if(sb.isEmpty()){
                sb.append(ch);
                currIndex[ch - 'a'] = i;
            }
            else{
                char chLast = sb.charAt(sb.length() - 1);

                while(sb.length() > 0 
                    && ch < sb.charAt(sb.length() - 1) 
                    && i < lastIndex[sb.charAt(sb.length() - 1) - 'a']){

                    currIndex[sb.charAt(sb.length() - 1) - 'a'] = -1;
                    sb.deleteCharAt(sb.length() - 1);
                }

                sb.append(ch);
                currIndex[ch - 'a'] = i;
            }
        }

        return sb.toString();
    }
}