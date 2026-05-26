// LeetCode - 3120



// Approach 1
// T.C. - O(n)
// S.C. - O(26)
class Solution {
    public int numberOfSpecialChars(String word) {
        int n = word.length();
        boolean[] lower = new boolean[26];
        boolean[] upper = new boolean[26];

        for(int i = 0; i<n; i++){
            char ch = word.charAt(i);

            if(Character.isLowerCase(ch)){
                lower[ch - 'a'] = true;
            }
            else{
                upper[ch - 'A'] = true;
            }
        }

        int count = 0;
        for(int i = 0; i<26; i++){
            if(lower[i] && upper[i]){
                count++;
            }
        }

        return count;
    }
}






// Approach 2 - Set
// T.C. - O(n)
// S.C. - O(n)
class Solution {
    public int numberOfSpecialChars(String word) {
        int n = word.length();
        Set<Character> set = new HashSet<>();

        for(char ch : word.toCharArray()){
            set.add(ch);
        }

        // processing
        int count = 0;
        for(char ch = 'a'; ch <= 'z'; ch++){
            if(set.contains(ch) && set.contains(Character.toUpperCase(ch))){
                count++;
            }
        }

        return count;
    }
}