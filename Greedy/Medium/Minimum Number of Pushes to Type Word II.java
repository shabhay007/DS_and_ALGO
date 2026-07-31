// LeetCode - 3016



// Approach 1 - Greedy + Counting + Sorting
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public int minimumPushes(String word) {
        int[] count = new int[26];

        for(char ch : word.toCharArray()){
            count[ch - 'a']++;
        }

        Arrays.sort(count); // O(26 * log(26)) ~ O(1)

        int result = 0;
        for(int i = 25; i>=0; i--){
            if(i >= 18){
                result += count[i];
            }
            else if(i >= 10){
                result += 2 * count[i];
            }
            else if(i >= 2){
                result += 3 * count[i];
            }
            else{
                result += 4 * count[i];
            }
        }

        return result;
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






// Approach 3 - Greedy + Reverse Processing
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public int minimumPushes(String word) {
        int[] count = new int[26];
        for(char ch : word.toCharArray()){
            count[ch - 'a']++;
        }

        Arrays.sort(count);

        int result = 0;
        for(int i = 0; i<26; i++){ // processing in reverse order
            if(i < 2){
                result += 4 * count[i];
            }
            else if(i < 10){
                result += 3 * count[i];
            }
            else if(i < 18){
                result += 2 * count[i];
            }
            else{
                result += count[i];
            }
        }

        return result;
    }
}