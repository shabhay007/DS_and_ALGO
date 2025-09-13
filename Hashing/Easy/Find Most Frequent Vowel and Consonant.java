// LeetCode - 3541




// Approach 1 - Map
// T.C. - O(n)
// S.C. - O(n)
class Solution {
    public int maxFreqSum(String s) {
        int n = s.length();
        Map<Character, Integer> map = new HashMap<>();

        for(char ch : s.toCharArray()){
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        int maxVowel = 0;
        int maxConsonants = 0;

        for(Map.Entry<Character, Integer> entry : map.entrySet()){
            char key = entry.getKey();
            int val = entry.getValue();

            if(key == 'a' || key == 'e' || key == 'i' || key == 'o' || key == 'u'){
                maxVowel = Math.max(maxVowel, val);
            }
            else{
                maxConsonants = Math.max(maxConsonants, val);
            }
        }

        return maxVowel + maxConsonants;
    }
}