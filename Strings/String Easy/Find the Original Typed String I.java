// LeetCode - 3330



// Approach 1 - String Simulation
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public int possibleStringCount(String word) {
        int n = word.length();

        int result = 1;
        char prev = '0';

        for(char ch : word.toCharArray()){
            if(ch == prev){
                result++;
            }
            else{
                prev = ch;
            }
        }

        return result;
    }
}




// Approach 2 - String Simulation (Slight change in Approach 1)
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public int possibleStringCount(String word) {
        int n = word.length();

        int result = 1; // in case of no long press, then org string remains
        char prev = word.charAt(0);

        for(int i = 1; i<n; i++){
            char ch = word.charAt(i);

            if(ch == prev){
                result++;
            }
            else{
                prev = ch;
            }
        }

        return result;
    }
}