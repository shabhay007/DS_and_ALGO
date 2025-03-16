// LeetCode Medium - 1358



// Approach 1 - Using Set (Checking all substrings)
// T.C. - O(n^3)
// S.C. - O(3)
class Solution {
    public boolean isContains(String s, int i, int j){
        HashSet<Character> set = new HashSet<>();

        for(int start = i; start <= j; start++){
            char ch = s.charAt(start);
            set.add(ch);
        }

        // is all the 3 char's exists then set's size will be 3
        return set.size() == 3;
    }

    public int numberOfSubstrings(String s) {
        int n = s.length();
        int count = 0;

        for(int i = 0; i<n; i++){
            for(int j = i; j<n; j++){
                if(isContains(s, i, j)){
                    count++;
                }
            }
        }

        return count;
    }
}





// Approach 2 - Sliding Window
// T.C. - O(2n)
// S.C. - O(1)
class Solution {
    public boolean isValid(int[] freq){
        return freq[0] > 0 && freq[1] > 0 && freq[2] > 0;
    }

    public int numberOfSubstrings(String s) {
        int n = s.length();
        int count = 0;

        int i = 0;
        int j = 0;
        int freq[] = new int[3];

        while(j < n){
            char ch = s.charAt(j);
            freq[ch - 'a']++;

            while(isValid(freq)){
                count += n - j; // all substring starting from i having all 3 char's

                // shrink the window
                char newCh = s.charAt(i);
                freq[newCh - 'a']--;
                i++;
            }

            j++;
        }

        return count;
    }
}