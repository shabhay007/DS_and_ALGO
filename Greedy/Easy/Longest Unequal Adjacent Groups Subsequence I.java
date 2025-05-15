// LeetCode - 2900



// Brute Force
// T.C. - O(n^2)
// S.C. - O(n)
class Solution {
    public List<String> getLongestSubsequence(String[] words, int[] groups) {
        int n = words.length;
        List<String> result = new ArrayList<>();
        int maxLength = 0;

        for(int i = 0; i<n; i++){
            List<String> list = new ArrayList<>();
            list.add(words[i]);
            int prev = groups[i];

            for(int j = i+1; j<n; j++){
                if(prev != groups[j]){
                    list.add(words[j]);
                    prev = groups[j];
                }
            }
        
            if(maxLength < list.size()){
                result = list;
                maxLength = list.size();
            }
        }

        return result;
    }
}





// Approach 2 - Greedy
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public List<String> getLongestSubsequence(String[] words, int[] groups) {
        int n = words.length;
        List<String> result = new ArrayList<>();
        result.add(words[0]);
        int prev = groups[0];

        for(int i = 1; i<n; i++){
            if(prev != groups[i]){
                result.add(words[i]);
                prev = groups[i];
            }
        }

        return result;
    }
}