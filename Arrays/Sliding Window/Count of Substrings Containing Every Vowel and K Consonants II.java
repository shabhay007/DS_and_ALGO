// LeetCode Medium - 3306



// Approach 1 - Brute Force (Gives TLE - Checking all substrings)
// T.C. - O(n^3)
// S.C. - O(1)
class Solution {
    public boolean isValid(int start, int end, String word, int k){
        Set<Character> set1 = new HashSet<>();
        int consonantCount = 0;

        for(int i = start; i <= end; i++){
            char ch = word.charAt(i);

            if(ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u'){
                set1.add(ch);
            }
            else{
                consonantCount++;
            }
        }

        return (set1.size() == 5) && (consonantCount == k);
    }

    public long countOfSubstrings(String word, int k) {
        int n = word.length();
        long count = 0;

        for(int i = 0; i<n; i++){
            for(int j = i; j<n; j++){
                if(isValid(i, j, word, k)){
                    count++;
                }
            }
        }

        return count;
    }
}




// Approach 2 (Optimal) - Sliding Window
// T.C. - O(n + 2n)
// S.C. - O(n)
class Solution {
    public boolean isVowel(char ch){
        return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u';
    }

    public long countOfSubstrings(String word, int k) {
        int n = word.length();

        // to keep count of vowel in curr window
        Map<Character, Integer> map = new HashMap<>(); // O(1)

        // preprocessing the consonants
        int[] nextConsonantsIdx = new int[n]; // O(n)
        int lastConsonantsIdx = n;

        for(int i = n-1; i >= 0; i--){ // O(n)
            nextConsonantsIdx[i] = lastConsonantsIdx;

            if(!isVowel(word.charAt(i))){
                lastConsonantsIdx = i;
            }
        }

        // now finding the required count
        int l = 0;
        int r = 0;
        int consonantCount = 0;
        long count = 0;

        while(r < n){
            // step 1 : expanding to find valid substring
            char chR = word.charAt(r);

            if(isVowel(chR)){
                map.put(chR, map.getOrDefault(chR, 0) + 1);
            }
            else{
                consonantCount++;
            }

            // step 2 : shrinking as count of consonants == k (always)
            while(consonantCount > k){
                char ch = word.charAt(l);

                if(isVowel(ch)){
                    map.put(ch, map.get(ch) - 1);

                    if(map.get(ch) == 0){
                        map.remove(ch);
                    }
                }
                else{
                    consonantCount--;
                }

                l++; // shrinking the window
            }

            // step 3 : calculating the count
            while(map.size() == 5 && consonantCount == k){
                // getting the consonant idx after jth idx
                int nextIdx = nextConsonantsIdx[r];
                count += nextIdx - r;

                char ch = word.charAt(l);

                if(isVowel(ch)){
                    map.put(ch, map.get(ch) - 1);

                    if(map.get(ch) == 0){
                        map.remove(ch);
                    }
                }
                else{
                    consonantCount--;
                }

                l++; // shrinking the window
            }

            r++; // expanding the window
        }

        return count;
    }
}