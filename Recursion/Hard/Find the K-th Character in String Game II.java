// LeetCode - 3307

// Easy variation of this question -> LeetCode - 3304 (Find it on String Easy section)



// Approach 1 (Brute Force) - Simulation (Gives TLE/MLE)
// T.C. - O(k)
// S.C. - O(k)
class Solution {
    public char kthCharacter(long k, int[] operations) {
        StringBuilder sb = new StringBuilder("a");
        int j = 0;

        while(sb.length() <= k){
            int len = sb.length();

            if(operations[j] == 0){
                for(int i = 0; i<len; i++){
                    char ch = sb.charAt(i);
                    sb.append(ch);

                    if(sb.length() >= k){
                        return sb.charAt((int) k-1);
                    }
                }
            }
            else{
                for(int i = 0; i<len; i++){
                    char ch = sb.charAt(i);
                    char next = (char) (ch + 1);
                    sb.append(next);

                    if(sb.length() >= k){
                        return sb.charAt((int) k-1);
                    }
                }
            }

            j++;
        }

        return sb.charAt((int) k-1);
    }
}