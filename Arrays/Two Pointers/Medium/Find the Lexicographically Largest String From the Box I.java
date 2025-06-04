// LeetCode - 3403



// Approach 1
// T.C. - O(n^2)
// S.C. - O(1); no extra space; only O(n) which is req. to return as result
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





// Approach 2
// T.C. - O(n)
// S.C. - O(1); no extra space; only O(n) which is req. to return as result
class Solution {
    public int bestStartingPoint(String word, int n){
        int i = 0; // best starting point
        int j = 1; // it will move to find the best starting point

        while(j < n){
            int k = 0;

            // skipping equal characters
            // no need to check i+k < n as we are checking j+k < n, and j > i
            while(j + k < n && word.charAt(i + k) == word.charAt(j + k)){
                k++;
            }

            if(j + k < n && word.charAt(j + k) > word.charAt(i + k)){
                i = j; // updating best starting point
                j = j+1;
            }
            else{
                j = j + k + 1; // skipping already checked characters
            }
        }

        return i;
    }

    public String answerString(String word, int numFriends) {
        int n = word.length();

        if(numFriends == 1){
            return word;
        }

        int position = bestStartingPoint(word, n);
        int maxPossibleLen = n - (numFriends - 1);

        String result = word.substring(position, Math.min(n, position + maxPossibleLen));

        return result;
    }
}