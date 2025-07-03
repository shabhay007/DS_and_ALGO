// LeetCode - 3304



// Approach 1
// T.C. - O(k)
// S.C. - O(k)

/*

    The string approximately doubles in size each round:
    1, 2, 4, 8, ... (i.e., powers of 2)

    Example:
    "a" → "ab" → "abbc" → "abbcbccd" → ...

    But we stop appending once sb.length() >= k,
    so we don’t process the full exponential growth.

    Total characters processed and appended: O(k)

*/

class Solution {
    public char kthCharacter(int k) {
        StringBuilder sb = new StringBuilder("a");

        while(sb.length() <= k){
            int len = sb.length();

            for(int i = 0; i<len; i++){
                char ch = sb.charAt(i);
                char next = (ch == 'z') ? 'a' : (char) (ch + 1);
                sb.append(next);

                if(sb.length() >= k){
                    // System.out.println(sb.toString());
                    return sb.charAt(k-1);
                }
            }
        }

        return sb.charAt(k-1);
    }
}