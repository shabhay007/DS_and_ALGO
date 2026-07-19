// LeetCode Medium - 1081



// Approach 1 - Greedy + Monotonic Stack properties used in StringBuilder
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public String smallestSubsequence(String s) {
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






// Approach 2 - Greedy (Slight change in approach 1, using boolean visited)
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public String smallestSubsequence(String s) {
        int n = s.length();
        int[] lastIndex = new int[26];
        
        for(int i = 0; i<n; i++){
            char ch = s.charAt(i);
            lastIndex[ch - 'a'] = i;
        }

        boolean[] visited = new boolean[26];
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i<n; i++){
            char ch = s.charAt(i);

            if(visited[ch - 'a']){
                continue;
            }

            if(sb.isEmpty()){
                sb.append(ch);
                visited[ch - 'a'] = true;
            }
            else{
                char chLast = sb.charAt(sb.length() - 1);

                while(sb.length() > 0 
                    && ch < sb.charAt(sb.length() - 1) 
                    && i < lastIndex[sb.charAt(sb.length() - 1) - 'a']){

                    visited[sb.charAt(sb.length() - 1) - 'a'] = false;
                    sb.deleteCharAt(sb.length() - 1);
                }

                sb.append(ch);
                visited[ch - 'a'] = true;
            }
        }

        return sb.toString();
    }
}