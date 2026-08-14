// LeetCode - 3090



// Approach 1 - Sliding Window
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public boolean isExists(int[] arr){
        for(int i = 0; i<26; i++){
            if(arr[i] == 0){
                continue;
            }
            else if(arr[i] > 2){
                return false;
            }
        }

        return true;
    }

    public int maximumLengthSubstring(String s) {
        int n = s.length();
        int[] freq = new int[26];
        int max = 0;

        int i = 0;
        int j = 0;

        while(j < n){
            char ch = s.charAt(j);
            int idx = ch - 'a';

            freq[idx]++;

            if(isExists(freq)){
                max = Math.max(max, j-i+1);
            }

            while(i < j && freq[idx] > 2){
                char chi = s.charAt(i);
                freq[chi - 'a']--;
                i++;
            }

            j++;
        }

        return max;
    }
}