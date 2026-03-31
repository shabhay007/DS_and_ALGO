// LeetCode - 3474



// Approach 1 - Greedy
// T.C. - O(n * m)
// S.C. - O(n + m)
class Solution {
    public boolean isSame(int start, int m, String str, char[] word){
        for(int i = 0; i<m; i++){
            if(word[i+start] != str.charAt(i)){
                return false;
            }
        }

        return true;
    }

    public String generateString(String str1, String str2) {
        int n = str1.length();
        int m = str2.length();
        char[] word = new char[n + m - 1];
        Arrays.fill(word, '$');

        boolean[] canChange = new boolean[n + m - 1];

        // Processing T's
        for(int i = 0; i<n; i++){
            if(str1.charAt(i) == 'T'){
                int l = i;

                for(int j = 0; j<m; j++){
                    if(word[l] != '$' && word[l] != str2.charAt(j)){
                        return ""; // not possible because of conflict with other T
                    }

                    word[l] = str2.charAt(j);
                    l++;
                }
            }
        }

        // Filling the remaining spaces in char array with 'a' to get lex. smallest
        for(int i = 0; i<word.length; i++){
            if(word[i] == '$'){
                word[i] = 'a';
                canChange[i] = true;
            }
        }

        // step 3 : validating with F in str1
        for(int i = 0; i<n; i++){
            if(str1.charAt(i) == 'F'){
                // checking if substr in word is same with str2 or not
                if(isSame(i, m, str2, word)){
                    // in case both are same, change the char that we can change
                    // from last
                    boolean changed = false;
                    for(int k = i + m - 1; k >= i; k--){
                        if(canChange[k]){
                            word[k] = 'b';
                            changed = true;
                            break;
                        }
                    }

                    if(!changed){
                        return ""; // equality can't be broken
                    }
                }
            }
        }

        return new String(word);
    }
}