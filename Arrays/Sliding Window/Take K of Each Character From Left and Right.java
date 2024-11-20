// LeetCode - 2516 - Medium


// Brute Force - Recursion - O(2^n)
class Solution {
    int result = Integer.MAX_VALUE;

    public void solve(int i, int j, int[] freq, String s, int k, int minutes){
        if(freq[0] >= k && freq[1] >= k && freq[2] >= k){
            result = Math.min(result, minutes);
        }

        if(i > j){ // all characters visited or invalid case
            return;
        }

        //taking characters form left
        int[] tempFreqFromLeft = freq.clone();
        tempFreqFromLeft[s.charAt(i) - 'a'] += 1;
        solve(i+1, j, tempFreqFromLeft, s, k, minutes+1);

        //taking characters form right
        int[] tempFreqFromRight = freq.clone();
        tempFreqFromRight[s.charAt(j) - 'a'] += 1;
        solve(i, j-1, tempFreqFromRight, s, k, minutes+1);
    }

    public int takeCharacters(String s, int k) {
        int n = s.length();
        int i = 0;
        int j = n-1;
        int[] freq = new int[3]; // a, b, c
        int minutes = 0;

        solve(i, j, freq, s, k, minutes);

        return result == Integer.MAX_VALUE ? -1 : result;
    }
}



// Optimal - Sliding Window - O(n)
class Solution {
    public int takeCharacters(String s, int k) {
        int n = s.length();
        int count_A = 0;
        int count_B = 0;
        int count_C = 0;

        for(int i = 0; i<n; i++){
            char ch = s.charAt(i);

            if(ch == 'a') count_A++;  
            else if(ch == 'b') count_B++;
            else count_C++;
        }

        // Not possible to delete k characters
        if(count_A < k || count_B < k || count_C < k){
            return -1;
        }

        int i = 0;
        int j = 0;
        int notDeletedSize = 0;

        while(j < n){
            char ch = s.charAt(j);

            if(ch == 'a'){
                count_A--;
            }

            if(ch == 'b'){
                count_B--;
            }

            if(ch == 'c'){
                count_C--;
            }

            // if deletion count of any character becomes less than k
            // then shrink the window and reinstate that character
            while(i <= j && (count_A < k || count_B < k || count_C < k)){
                char chr = s.charAt(i);

                if(chr == 'a'){
                    count_A++;
                }
                else if(chr == 'b'){
                    count_B++;
                }
                else{
                    count_C++;
                }

                i++;
            }

            notDeletedSize = Math.max(notDeletedSize, j-i+1);
            j++;
        }

        return n-notDeletedSize;
    }
}
