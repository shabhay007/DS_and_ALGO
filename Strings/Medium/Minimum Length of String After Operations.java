// LeetCode Medium - 3223


// Optimal Approach - Using Frequency Array
// T.C. - O(n + 26)
// S.C. - O(26) ~ O(1)
class Solution {
    public int minimumLength(String s) {
        int n = s.length();
        int[] freq = new int[26];

        for(char ch : s.toCharArray()){
            freq[ch - 'a']++;
        }

        int minLength = 0;
        for(int count : freq){
            if(count == 0) continue;

            if(count % 2 == 0){
                minLength += 2;
            }
            else{
                minLength += 1;
            }
        }

        return minLength;
    }
}




// Optimal Approach - Using Frequency Array
// T.C. - O(n)
// S.C. - O(26) ~ O(1)
class Solution {
    public int minimumLength(String s) {
        int n = s.length();
        int[] freq = new int[26];
        int deletedCount = 0;

        for(char ch : s.toCharArray()){
            freq[ch - 'a']++;

            if(freq[ch - 'a'] == 3){
                freq[ch - 'a'] -= 2;
                deletedCount += 2;
            }
        }

        return n - deletedCount;
    }
}