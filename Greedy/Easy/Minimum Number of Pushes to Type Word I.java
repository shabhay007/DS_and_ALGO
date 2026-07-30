// LeetCode - 3014



// Approach - Greedy + Simulation
// T.C. - O(26)
// S.C. - O(1)
class Solution {
    public int minimumPushes(String word) {
        int n = word.length();

        if(n <= 8){
            return n;
        }

        if(n <= 16){
            return 8 + 2 * (n - 8);
        }
        else if(n <= 24){
            return 8 + 16 + 3 * (n - 16);
        }
        else{
            return 8 + 16 + 24 + 4 * (n - 24);
        }
    }
}






// Approach 2 - Greedy + Sorting
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public int minimumPushes(String word) {
        Integer[] count = new Integer[26];
        Arrays.fill(count, 0); // as by default, it initializes with null

        for(char ch : word.toCharArray()){
            count[ch - 'a']++;
        }

        Arrays.sort(count, (a, b) -> b - a); // descending order

        int result = 0;
        for(int i = 0; i<26; i++){
            int freq = count[i];
            int noOfPress = (i/8) + 1;
            result += freq * noOfPress;
        }

        return result;
    }
}