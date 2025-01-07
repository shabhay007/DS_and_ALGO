// LeetCode Easy - 1408


// Approach 1
class Solution {
    public List<String> stringMatching(String[] words) {
        HashSet<String> set = new HashSet<>();

        for(String subString : words){
            for(String word : words){
                if(word.contains(subString) && !subString.equals(word)){
                    set.add(subString);
                }
            }
        }

        return new ArrayList<>(set);
    }
}



// Approach 2 
// T.C. - O(n^2 * m); m = length of word; contains function also takes linear time
// S.C. - O(1)
class Solution {
    public List<String> stringMatching(String[] words) {
        int n = words.length;
        List<String> list = new ArrayList<>();

        for(int i = 0; i<n; i++){
            for(int j = 0; j<n; j++){
                if(i != j && words[j].contains(words[i]) && !list.contains(words[i])){
                    list.add(words[i]);
                }
            }
        }

        return list;
    }
}




// Approach 3 - KMP Algorithm
// T.C. - O(n^2 * (x+y)); let's say avg length of every word is m then, T.C - O(n^2 * m)
// S.C. - O(n)
class Solution {
    public void findLps(String str, int m, int[] lps){
        int length = 0;
        lps[0] = 0;

        int i = 1;

        while(i < m){
            if(str.charAt(i) == str.charAt(length)){
                length++;
                lps[i] = length;
                i++;
            }
            else{
                if(length != 0){
                    length = lps[length - 1];
                }
                else{
                    lps[i] = 0;
                    i++;
                }
            }
        }
    }

    public boolean searchKMP(String pat, String txt){
        int n = txt.length();
        int m = pat.length();

        int[] lps = new int[m];
        findLps(pat, m, lps);

        int i = 0;
        int j = 0;

        while(i < n){
            if(pat.charAt(j) == txt.charAt(i)){
                i++;
                j++;
            }

            if(j == m){
                return true;
            }
            else if(i < n && pat.charAt(j) != txt.charAt(i)){
                if(j != 0){
                    j = lps[j-1];
                }
                else{
                    i++;
                }
            }
        }

        return false;
    }
    
    public List<String> stringMatching(String[] words) {
        int n = words.length;
        List<String> list = new ArrayList<>();

        for(int i = 0; i<n; i++){
            for(int j = 0; j<n; j++){
                if(i == j){
                    continue;
                }

                if(searchKMP(words[i], words[j])){
                    list.add(words[i]);
                    break;
                }
            }
        }

        return list;
    }
}