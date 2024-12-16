// LeetCode Medium -2981



// Approach 1 - Brute Force; Using Map
// T.C - O(n^3)
// S.C. - O(n^2)
class Solution {
    public int maximumLength(String s) {
        int n = s.length();
        HashMap<String, Integer> map = new HashMap<>();

        // generating all substring and store in map
        for(int i = 0; i<n; i++){ // O(n^3)
            StringBuilder currString = new StringBuilder();
            
            for(int j = i; j<n; j++){
                if(currString.length() == 0 || s.charAt(j) == currString.charAt(currString.length() - 1)){
                    currString.append(s.charAt(j));

                    String str = currString.toString();
                    map.put(str, map.getOrDefault(str, 0) + 1); // ~O(n)
                }
                else{
                    break;
                }
            }
        }

        int maxLength = -1;
        for(Map.Entry<String, Integer> entry : map.entrySet()){
            String key = entry.getKey();
            int count = entry.getValue();

            if(count > 2){
                maxLength = Math.max(maxLength, key.length());
            }
        }

        return maxLength;
    }
}



// Approach 2 - Better Brute Force; Using Map
// T.C - O(n^2)
// S.C. - O(n^2)
class Solution {
    public int maximumLength(String s) {
        int n = s.length();
        HashMap<Pair<Character, Integer>, Integer> map = new HashMap<>();

        for(int i = 0; i<n; i++){ // O(n^2)
            char ch = s.charAt(i);
            int len = 0;
            
            for(int j = i; j<n; j++){
                if(s.charAt(j) == ch){
                    len++;
                    Pair<Character, Integer> key = new Pair<>(ch, len);
                    map.put(key, map.getOrDefault(key, 0) + 1);
                }
                else{
                    break;
                }
            }
        }

        int maxLength = -1;
        for(Map.Entry<Pair<Character, Integer>, Integer> entry : map.entrySet()){
            int length = entry.getKey().getValue();
            int count = entry.getValue();

            if(count > 2){
                maxLength = Math.max(maxLength, length);
            }
        }

        return maxLength;
    }
}



// Approach 3 - Optimal, using 2-D matrix
// T.C - O(n)
// S.C. - O(26 * n)
class Solution {
    public int maximumLength(String s) {
        int n = s.length();
        int[][] specialMatrix = new int[26][n+1];
        char prevChar = s.charAt(0);
        int len = 0;

        for(int i = 0; i<n; i++){
            char currChar = s.charAt(i);

            if(prevChar == currChar){
                len++; //incrementing length
                specialMatrix[currChar - 'a'][len] += 1; // incrementing frequency
            }
            else{
                len = 1;
                specialMatrix[currChar - 'a'][len] += 1;
                prevChar = currChar;
            }
        }

        int maxLength = -1;
        for(int i = 0; i<26; i++){ // O(26 * n) ~ O(n); i.e. 26 is a constant
            int cummulativeSum = 0;

            for(int j = n; j>0; j--){
                cummulativeSum += specialMatrix[i][j];

                if(cummulativeSum >= 3){
                    // curr col. number will be the length i.e. j
                    maxLength = Math.max(maxLength, j);
                    break;
                }
            }
        }

        return maxLength;
    }
}