// LeetCode - 2452



// Approach 1 - Brute Force
// T.C. - O(m * n * L); m = queries.length, n = dict.length, L = avg. length or words
// S.C. - O(1)
class Solution {
    public List<String> twoEditWords(String[] queries, String[] dictionary) {
        List<String> result = new ArrayList<>();

        for(String str : queries){

            // checking in dictionary
            for(String dictStr : dictionary){
                int diff = 0;

                // matching characters in words
                for(int i = 0; i < str.length(); i++){
                    if(str.charAt(i) != dictStr.charAt(i)){
                        diff++;
                    }
                }

                // checking if max edits <= 2
                if(diff <= 2){
                    result.add(str);
                    break;
                }
            }
        }

        return result;
    }
}