// LeetCode Easy - 3042


// Brute Force 
// T.C. - O(n^2)
// S.C. - O(1)
class Solution {
    public int countPrefixSuffixPairs(String[] words) {
        int n = words.length;
        int count = 0;

        for(int i = 0; i<n; i++){
            int m = words[i].length();

            for(int j = 0; j<n; j++){
                if(i < j && words[j].length() >= m){
                    boolean isPrefix = words[j].substring(0, m).equals(words[i]);
                    boolean isSuffix = words[j].substring(words[j].length() - m).equals(words[i]);

                    if(isPrefix && isSuffix){
                        count++;
                    }
                }
            }
        }

        return count;
    }
}



// Better - String functions
// T.C. - O(n^2)
// S.C. - O(1)
class Solution {
    public int countPrefixSuffixPairs(String[] words) {
        int n = words.length;
        int count = 0;

        for(int i = 0; i<n; i++){
            String str1 = words[i];

            for(int j = i+1; j<n; j++){
                String str2 = words[j];

                if(str1.length() > str2.length()) continue;

                if(str2.startsWith(str1) && str2.endsWith(str1)){
                    count++;
                }
            }
        }

        return count;
    }
}