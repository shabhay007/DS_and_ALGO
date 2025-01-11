// LeetCode Medium - 1400



// Approach 1 - Using Map
// T.C. - O(n + n)
// S.C. - O(26) ~ O(1)
class Solution {
    public boolean canConstruct(String s, int k) {
        int n = s.length();

        if(n < k){
            return false;
        }

        if(n == k){
            return true;
        }

        HashMap<Character, Integer> map = new HashMap<>();
        for(char ch : s.toCharArray()){
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        int noOfCharWithOddFreq = 0;

        for(char ch : map.keySet()){
            if(map.get(ch) % 2 != 0){
                noOfCharWithOddFreq++;
            }
        }

        return noOfCharWithOddFreq <= k;
    }
}



// Approach 2 - Using Array as a map
// T.C. - O(n)
// S.C. - O(26) ~ O(1)
class Solution {
    public boolean canConstruct(String s, int k) {
        int n = s.length();

        if(n < k){
            return false;
        }

        if(n == k){
            return true;
        }

        int[] freq = new int[26];
        for(char ch : s.toCharArray()){ // O(n)
            freq[ch - 'a']++;
        }

        int noOfCharWithOddFreq = 0;

        for(int i = 0; i<26; i++){ // O(26) ~ O(1)
            if(freq[i] % 2 != 0){
                noOfCharWithOddFreq++;
            }
        }

        return noOfCharWithOddFreq <= k;
    }
}