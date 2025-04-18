// LeetCode Medium - 443



// Approach 1 - Pointers
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public int compress(char[] chars) {
        int n = chars.length;
        int idx = 0; // idx for chars array
        int i = 0;

        while(i < n){
            char curr = chars[i];
            int count = 0;

            while(i < n && curr == chars[i]){
                i++;
                count++;
            }

            chars[idx++] = curr;

            if(count > 1){
                String str = String.valueOf(count);

                for(char ch : str.toCharArray()){
                    chars[idx++] = ch;
                }
            }
        }


        return idx;
    }
}