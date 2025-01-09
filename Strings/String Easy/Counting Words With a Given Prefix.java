// LeetCode Easy - 2185


// Approach 2 - Using sub string function
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public int prefixCount(String[] words, String pref) {
        int n = words.length;
        int m = pref.length();
        int count = 0;

        for(int i = 0; i<n; i++){
            if(words[i].length() < m) continue;

            String subStr = words[i].substring(0, m);

            if(pref.equals(subStr)){
                count++;
            }
        }

        return count;
    }
}




// Approach 2 - Using String function
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public int prefixCount(String[] words, String pref) {
        int n = words.length;
        int count = 0;

        for(int i = 0; i<n; i++){
            String str = words[i];

            if(str.startsWith(pref)){
                count++;
            }
        }

        return count;
    }
}