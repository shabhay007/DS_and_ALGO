// LeetCode Medium - 2131



// Approach 1 - Map
// T.C. - O(2n)
// S.C. - O(n)
class Solution {
    public int longestPalindrome(String[] words) {
        int n = words.length;
        int maxLen = 0;
        Map<String, Integer> map = new HashMap<>();

        for(String word : words){
            String str = new StringBuilder(word).reverse().toString();

            if(map.containsKey(str) && map.get(str) > 0){
                // adding 4 because of word and it's reversed i.e. str
                maxLen += 4;

                // decreasing the frequency of the reversed string
                map.put(str, map.get(str) - 1);
            }
            else{
                map.put(word, map.getOrDefault(word, 0) + 1);
            }
        }

        // finding same char's (aa, bb, ...) string and adding once
        for(String str : map.keySet()){
            if(str.charAt(0) == str.charAt(1) && map.get(str) > 0){
                maxLen += 2;
                break;
            }
        }

        return maxLen;
    }
}