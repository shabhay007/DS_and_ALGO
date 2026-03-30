// LeetCode - 2840



// Approach 1
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public boolean checkStrings(String s1, String s2) {
        int n = s1.length();
        int[] even = new int[26];
        int[] odd = new int[26];

        /*
            Even position characters can swap with only even positions
            and vice versa
        */

        for(int i = 0; i<n; i++){
            if(i % 2 == 0){
                even[s1.charAt(i) - 'a']++;
                even[s2.charAt(i) - 'a']--;
            }
            else{
                odd[s1.charAt(i) - 'a']++;
                odd[s2.charAt(i) - 'a']--;
            }
        }

        // if any one is not 0, not possible
        for(int i = 0; i<26; i++){
            if(even[i] != 0 || odd[i] != 0){
                return false;
            }
        }
        
        return true;
    }
}