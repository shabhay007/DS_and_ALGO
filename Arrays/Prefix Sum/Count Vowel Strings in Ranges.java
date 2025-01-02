// LeetCode Medium - 2559


// Brute Force - Set, Strings - GIVES TLE
// T.C. - O(m * n)
// S.C. - O(1)
class Solution {
    public int[] vowelStrings(String[] words, int[][] queries) {
        int n = queries.length;
        int result[] = new int[n];
        
        HashSet<Character> set = new HashSet<>();
        set.add('a');
        set.add('e');
        set.add('i');
        set.add('o');
        set.add('u');

        int k = 0;
        for(int[] query : queries){
            int left = query[0];
            int right = query[1];
            int count = 0;

            for(int i = left; i <= right; i++){
                char startChar = words[i].charAt(0);
                char endChar = words[i].charAt(words[i].length() - 1);

                if(set.contains(startChar) && set.contains(endChar)){
                    count++;
                }
            }

            if(k < n){
                result[k] = count;
                k++;
            }
        }

        return result;
    }
}




// Optimal - Prefix Sum
// T.C. - O(m + n)
// S.C. - O(m)
class Solution {
    public int[] vowelStrings(String[] words, int[][] queries) {
        int n = queries.length;
        int result[] = new int[n];
        
        HashSet<Character> set = new HashSet<>();
        set.add('a');
        set.add('e');
        set.add('i');
        set.add('o');
        set.add('u');

        // prefix array
        int prefix[] = new int[words.length];
        char start = words[0].charAt(0);
        char end = words[0].charAt(words[0].length() - 1);
        prefix[0] = (set.contains(start) && set.contains(end)) ? 1 : 0;
        // int prev = (prefix[0] == 1) ? 1 : 0;

        for(int i = 1; i < words.length; i++){
            char startChar = words[i].charAt(0);
            char endChar = words[i].charAt(words[i].length() - 1);

            if(set.contains(startChar) && set.contains(endChar)){
                prefix[i] = prefix[i-1] + 1;
            }
            else{
                prefix[i] = prefix[i-1];
            }
        }

        int k = 0;
        for(int[] query : queries){
            int left = query[0];
            int right = query[1];
            int count = 0;

            result[k] = (left > 0) ? prefix[right] - prefix[left - 1] : prefix[right];
            k++;
        }

        return result;
    }
}




// Optimal - Prefix Sum
// T.C. - O(m + n)
// S.C. - O(m)
class Solution {
    public boolean isVowel(char ch){
        if(ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u'){
            return true;
        }

        return false;
    }

    public int[] vowelStrings(String[] words, int[][] queries) {
        int n = queries.length;
        int result[] = new int[n];

        // prefix array
        int prefix[] = new int[words.length];
        int sum = 0;

        for(int i = 0; i < words.length; i++){
            char startChar = words[i].charAt(0);
            char endChar = words[i].charAt(words[i].length() - 1);

            if(isVowel(startChar) && isVowel(endChar)){
                sum++;
            }

            prefix[i] = sum;
        }

        int k = 0;
        for(int[] query : queries){
            int left = query[0];
            int right = query[1];
            int count = 0;

            result[k] = prefix[right] - ((left > 0) ? prefix[left - 1] : 0);
            k++;
        }

        return result;
    }
}