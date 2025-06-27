// LeetCode - 2014



//Approach 1 - Backtracking remplate - storing all possible strings
//T.C : O(n * ((n/k)!))
//S.C : O(n/k)
class Solution {
    String result = "";

    public boolean isSubsequence(String s, StringBuilder curr, int k){
        int n = s.length();
        int L = curr.length();

        int i = 0; // for s
        int j = 0; // subSeq

        while(i < n && j < k*L){
            if(s.charAt(i) == curr.charAt(j % L)){
                i++;
                j++;
            }
            else{
                i++;
            }
        }

        return j == k * L;
    }

    public void backtracking(String s, StringBuilder curr, boolean[] charToUse, int[] requiredFreq, int k, int maxLen){
        if(curr.length() > maxLen){
            return;
        }

        if((curr.length() > result.length() || (curr.length() == result.length() && curr.toString().compareTo(result) > 0)) && 
        isSubsequence(s, curr, k)){
            result = curr.toString();
        }

        for(int i = 0; i<26; i++){
            if(charToUse[i] == false || requiredFreq[i] == 0){
                continue;
            }

            // DO
            char ch = (char) (i + 'a');
            curr.append(ch);
            requiredFreq[i]--;

            // EXPLORE
            backtracking(s, curr, charToUse, requiredFreq, k, maxLen);

            // UNDO
            curr.deleteCharAt(curr.length() - 1);
            requiredFreq[i]++;
        }
    }

    public String longestSubsequenceRepeatedK(String s, int k) {
        int n = s.length();
        int[] freq = new int[26];

        for(char ch : s.toCharArray()){
            freq[ch - 'a']++;
        }

        // char with freq >= k, can be used to make the subsequence * k
        boolean[] charToUse = new boolean[26];

        // the number of freq use to make 1 sub
        int[] requiredFreq = new int[26];

        for(int i = 0; i<26; i++){
            if(freq[i] >= k){
                charToUse[i] = true;
                requiredFreq[i] = freq[i]/k; // atmost freq that can be used to make 1 sub
            }
        }

        // max length of a sub
        int maxLen = n/k;

        StringBuilder curr = new StringBuilder();
        
        backtracking(s, curr, charToUse, requiredFreq, k, maxLen);

        return result;
    }
}





// Improvement over Approach 1
// Approach 2 - Backtracking (Only checking largest and returning early)
// T.C : O(n * ((n/k)!))
// S.C : O(n/k)
class Solution {
    String result = "";

    public boolean isSubsequence(String s, StringBuilder curr, int k){
        int n = s.length();
        int L = curr.length();

        int i = 0; // for s
        int j = 0; // subSeq

        while(i < n && j < k*L){
            if(s.charAt(i) == curr.charAt(j % L)){
                j++;
            }

            i++;
        }

        return j == k * L;
    }

    public boolean backtracking(String s, StringBuilder curr, boolean[] charToUse, int[] requiredFreq, int k, int maxLen){
        if(curr.length() == maxLen){
            if(isSubsequence(s, curr, k)){
                result = curr.toString();
                return true;
            }

            return false;
        }

        for(int i = 25; i >= 0; i--){
            if(charToUse[i] == false || requiredFreq[i] == 0){
                continue;
            }

            // DO
            char ch = (char) (i + 'a');
            curr.append(ch);
            requiredFreq[i]--;

            // EXPLORE
            if(backtracking(s, curr, charToUse, requiredFreq, k, maxLen)){
                return true;
            }

            // UNDO
            curr.deleteCharAt(curr.length() - 1);
            requiredFreq[i]++;
        }

        return false;
    }

    public String longestSubsequenceRepeatedK(String s, int k) {
        int n = s.length();
        int[] freq = new int[26];

        for(char ch : s.toCharArray()){
            freq[ch - 'a']++;
        }

        // char with freq >= k, can be used to make the subsequence * k
        boolean[] charToUse = new boolean[26];

        // the number of freq use to make 1 sub
        int[] requiredFreq = new int[26];

        for(int i = 0; i<26; i++){
            if(freq[i] >= k){
                charToUse[i] = true;
                requiredFreq[i] = freq[i]/k; // atmost freq that can be used to make 1 sub
            }
        }

        // max length of a sub
        int maxLen = n/k;
        
        for(int len = maxLen; len >= 0; len--){
            int[] temp = requiredFreq.clone();
            StringBuilder curr = new StringBuilder();

            if(backtracking(s, curr, charToUse, temp, k, len)){
                return result;
            }
        }

        return result;
    }
}