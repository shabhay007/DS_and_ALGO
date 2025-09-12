// LeetCode - 3227



// Approach 2 - Counting Vowels
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public boolean doesAliceWin(String s) {
        int n = s.length();
        int numOfVowels = 0;

        for(char ch : s.toCharArray()){
            if(ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u'){
                numOfVowels++;
            }
        }

        if(numOfVowels == 0){
            return false;
        }

        return true;
    }
}





// Approach 2
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public boolean doesAliceWin(String s) {
        int n = s.length();

        for(char ch : s.toCharArray()){
            if(ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u'){
                return true;
            }
        }

        return false;
    }
}