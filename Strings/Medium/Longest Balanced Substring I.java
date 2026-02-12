// LeetCode - 3713



// Approach 1 - Brute Force
// T.C. - O(n^2 * 26)
// S.C. - O(26)
class Solution {
    public int longestBalanced(String s) {
        int n = s.length();
        int maxL = 0;

        for(int i = 0; i<n; i++){
            int[] freq = new int[26];

            for(int j = i; j<n; j++){
                char ch = s.charAt(j);
                freq[ch - 'a']++;

                int prev = -1;
                boolean flag = true;
                for(int f : freq){
                    if(f == 0){
                        continue;
                    }
                    else if(prev == -1){
                        prev = f;
                    }
                    else if(f != prev){
                        flag = false;
                        break;
                    }
                }

                if(flag){
                    maxL = Math.max(maxL, j - i + 1);
                }
            }
        }

        return maxL;
    }
}