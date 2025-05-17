// LeetCode - 2901



// Approach - DP
// T.C. - O(n^2.k + 2n); k = avg length of strings in words array
// S.C. - O(2n)
class Solution {
    public boolean isOne(String s1, String s2){
        int differ = 0;

        for(int i = 0; i < s1.length(); i++){
            if(s1.charAt(i) != s2.charAt(i)){
                differ++;
            }

            if(differ > 1){
                return false;
            }
        }

        return differ == 1;
    }

    public List<String> getWordsInLongestSubsequence(String[] words, int[] groups) {
        int n = words.length;
        int[] parent = new int[n];
        Arrays.fill(parent, -1);

        // dp[i] = longest subsequence length ending at index i
        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        int longestSub = 1;
        int longestSubIdx = 0;

        // O(n^2.k)
        for(int i = 0; i<n; i++){
            int l1 = words[i].length();

            for(int j = 0; j<i; j++){
                int l2 = words[j].length();

                if(groups[i] != groups[j] && l1 == l2 && isOne(words[i], words[j])){
                    if(dp[j] + 1 > dp[i]){
                        dp[i] = dp[j] + 1;
                        parent[i] = j;

                        if(longestSub < dp[i]){
                            longestSub = dp[i];
                            longestSubIdx = i;
                        }
                    }
                }
            }
        }


        List<String> result = new ArrayList<>();

        while(longestSubIdx != -1){ // O(n)
            result.add(words[longestSubIdx]);
            longestSubIdx = parent[longestSubIdx];
        }

        Collections.reverse(result); // O(n)

        return result;
    }
}