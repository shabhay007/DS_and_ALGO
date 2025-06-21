// LeetCode - 3085



// Approach 1 - Greedy
// T.C. - O(n + 26*26)
// S.C. - O(26)
class Solution {
    public int minimumDeletions(String word, int k) {
        int n = word.length();
        int[] freq = new int[26];

        for(int i = 0; i<n; i++){
            char ch = word.charAt(i);
            freq[ch - 'a']++;
        }

        int result = Integer.MAX_VALUE;

        for(int i = 0; i<26; i++){
            int currFreq = freq[i];
            int delete = 0;

            for(int j = 0; j<26; j++){
                if(i == j){
                    continue;
                }
                else if(freq[j] < currFreq){
                    delete += freq[j];
                }
                else if(Math.abs(currFreq - freq[j]) > k){
                    delete += Math.abs(freq[j] - currFreq - k);
                }
            }

            result = Math.min(result, delete);
        }

        return result;
    }
}





// Approach 2 - Greedy + Sorting + Pruning
// T.C. - O(n + 26*26)
// S.C. - O(26)
class Solution {
    public int minimumDeletions(String word, int k) {
        int n = word.length();
        int[] freq = new int[26];

        for(int i = 0; i<n; i++){
            char ch = word.charAt(i);
            freq[ch - 'a']++;
        }

        Arrays.sort(freq);

        int result = Integer.MAX_VALUE;
        int cummulativeDeletions = 0;

        for(int i = 0; i<26; i++){
            int delete = cummulativeDeletions;

            for(int j = 25; j>i; j--){
                if(freq[j] - freq[i] <= k){ // pruning
                    break;
                }
                    
                delete += freq[j] - freq[i] - k;
            }

            result = Math.min(result, delete);

            // prev characters should be deleted as it is < currCharFreq
            // because of sorting
            cummulativeDeletions += freq[i];
        }

        return result;
    }
}