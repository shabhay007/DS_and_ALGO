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





// Approach 2 - Recursion
// T.C. - O(log(k))
// S.C. - O(log(k))
class Solution {
    public char kthCharacter(long k, int[] operations) {
        if(k == 1){
            return 'a';
        }

        int n = operations.length;
        long len = 1;
        long newK = -1;
        int operationType = -1;

        for(int i = 0; i<n; i++){ // log(k)
            len *= 2; // "a", "aa", "aaaa"

            if(len >= k){
                operationType = operations[i];
                newK = k - len/2; // k/2, k/4, ...
                break;
            }
        }

        char ch = kthCharacter(newK, operations);

        if(operationType == 0){
            return ch;
        }

        return (ch == 'z') ? 'a' : (char) (ch + 1);
    }
}