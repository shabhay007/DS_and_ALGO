// LeetCode - 3403



// Approach 1
// T.C. - O(n^2)
// S.C. - O(n); max length of result string can be n
class Solution {
    public String answerString(String word, int numFriends) {
        int n = word.length();

        if(numFriends == 1){
            return word;
        }

        int maxPossibleLen = n - (numFriends - 1);
        String result = "";

        for(int i = 0; i<n; i++){ // O(n^2)
            String current = word.substring(i, Math.min(n, i + maxPossibleLen));

            if(current.compareTo(result) > 0){
                result = current;
            }
        }

        return result;
    }
}