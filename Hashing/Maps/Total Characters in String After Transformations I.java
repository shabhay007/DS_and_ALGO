// LeetCode - 3335



// Approach 2 - Brute Force (Gives TLE)
// T.C. - O(n.2^t)
// S.C. - O(n.2^t)
class Solution {
    public int lengthAfterTransformations(String s, int t) {
        String curr = s;

        for(int i = 1; i<=t; i++){
            StringBuilder sb = new StringBuilder();

            for(int j = 0; j<curr.length(); j++){
                char ch = curr.charAt(j);

                if(ch == 'z'){
                    sb.append("ab");
                }
                else{
                    sb.append((char) (ch + 1));
                }
            }

            curr = sb.toString();
        }

        return curr.length();
    }
}




// Approach 2 (Optimal) - Map/Hash array
// T.C. - O(n + t.26)
// S.C. - O(26)
class Solution {
    public int lengthAfterTransformations(String s, int t) {
        int n = s.length();
        int mod = (int) 1e9 + 7;

        int[] map = new int[26];

        for(char ch : s.toCharArray()){
            map[ch - 'a']++;
        }

        while(t-- > 0){
            int[] temp = new int[26];

            for(int i = 0; i<26; i++){
                char ch = (char) (i + 'a');
                int freq = map[i];

                if(ch != 'z'){
                    temp[(ch + 1) - 'a'] = (temp[(ch + 1 - 'a')] + freq) % mod;
                }
                else{
                    temp['a' - 'a'] = (temp['a' - 'a'] + freq) % mod;
                    temp['b' - 'a'] = (temp['b' - 'a'] + freq) % mod;
                }
            }

            map = temp;
        }

        // now finding the total operations
        int totalOps = 0;

        for(int val : map){
            totalOps = (totalOps + val) % mod;
        }

        return totalOps;
    }
}