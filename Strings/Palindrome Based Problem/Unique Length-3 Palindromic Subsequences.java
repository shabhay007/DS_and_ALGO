// LeetCode Medium - 1930


// Brute Force - Gives TLE
// T.C. - O(n^3)
// S.C. - O(n)
class Solution {
    public boolean isPalindrome(String str){
        if(str.charAt(0) == str.charAt(2)) return true;

        return false;
    }

    public int countPalindromicSubsequence(String s) {
        int n = s.length();
        HashSet<String> set = new HashSet<>();


        for(int i = 0; i<n-2; i++){
            for(int j = i+1; j<n-1; j++){
                for(int k = j+1; k<n; k++){
                    String subSequence = "" + s.charAt(i) + s.charAt(j) + s.charAt(k);

                    if(isPalindrome(subSequence) && !set.contains(subSequence)){
                        set.add(subSequence);
                    }
                }
            }
        }

        return set.size();
    }
}



// Better Approach 2 - Set 
// T.C. - O(n + n + (26 * n))
// S.C. - O(26 + (26 * 26))
class Solution {
    public int countPalindromicSubsequence(String s) {
        int n = s.length();
        HashSet<Character> set = new HashSet<>();

        for(int i = 0; i<n; i++){
            set.add(s.charAt(i));
        }

        int count = 0;
        for(char ch : set){
            int first = -1;
            int last = -1;

            for(int i = 0; i<n; i++){
                if(s.charAt(i) == ch){
                    if(first == -1){
                        first = i;
                    }

                    last = i;
                }
            }

            if(first == last) continue;

            HashSet<Character> charSet = new HashSet<>();

            for(int i = first+1; i<last; i++){
                charSet.add(s.charAt(i));
            }

            count += charSet.size();
        }

        return count;
    }
}




// Optimal Approach 3 - Map 
// T.C. - O(n + (26 * n))
// S.C. - O(26 + 26 + (26 * 26)) ~ O(1)
class Solution {
    public int countPalindromicSubsequence(String s) {
        int n = s.length();
        HashMap<Character, Integer> firstOcc = new HashMap<>();
        HashMap<Character, Integer> lastOcc = new HashMap<>();

        for(int i = 0; i<n; i++){
            char ch = s.charAt(i);

            if(!firstOcc.containsKey(ch)){
                firstOcc.put(ch, i);
            }

            // put in last occurence map for last index
            lastOcc.put(ch, i);
        }

        int count = 0;
        for(char ch : firstOcc.keySet()){ // O(26)
            int first = firstOcc.get(ch);
            int last = lastOcc.get(ch);

            if(first == last) continue;

            HashSet<Character> charSet = new HashSet<>();

            for(int i = first+1; i<last; i++){
                charSet.add(s.charAt(i));
            }

            count += charSet.size();
        }

        return count;
    }
}




// Optimal Approach 4 - Array, Set
// T.C. - O(n + (26 * n))
// S.C. - O(26 + 26 + (26 * 26)) ~ O(1)
class Solution {
    public int countPalindromicSubsequence(String s) {
        int n = s.length();

        int[] firstOcc = new int[26];
        int[] lastOcc = new int[26];
        Arrays.fill(firstOcc, -1);
        Arrays.fill(lastOcc, -1);

        for(int i = 0; i<n; i++){
            int currIdx = s.charAt(i) - 'a';

            if(firstOcc[currIdx] == -1){
                firstOcc[currIdx] = i;
            }

            // put in last occurence map for last index
            lastOcc[currIdx] = i;
        }

        int count = 0;
        for(int i = 0; i<26; i++){ // O(26)
            if(firstOcc[i] == -1) continue;

            HashSet<Character> charSet = new HashSet<>();

            for(int j = firstOcc[i] + 1; j<lastOcc[i]; j++){ // O(n) - worst case
                charSet.add(s.charAt(j));
            }

            count += charSet.size();
        }

        return count;
    }
}




// Approach 5 - Map to track first and last occurrence
// T.C. - O(26*n)
// S.C. - O(1)
class Solution {
    public int countPalindromicSubsequence(String s) {
        int n = s.length();

        // O(1) -> 26 chars with int[2] values
        // storing first and last occurrence
        Map<Character, int[]> map = new HashMap<>();

        for(int i = 0; i<n; i++){
            char ch = s.charAt(i);

            if(!map.containsKey(ch)){
                map.put(ch, new int[]{i, i});
            }
            else{
                // updating last occurrence
                map.get(ch)[1] = i;
            }
        }

        // now traversing in map and finding unique chars
        // for 3 length palindromic subsequences, finding unique chars
        // in between 2 same characters
        int result = 0;

        for(int[] indices : map.values()){
            int start = indices[0];
            int end = indices[1];

            if(start == end){
                continue;
            }

            // O(1) -> 26 chars max
            Set<Character> set = new HashSet<>();

            for(int i = start+1; i<end; i++){
                char ch = s.charAt(i);
                set.add(ch);
            }

            result += set.size();
        }

        return result;
    }
}