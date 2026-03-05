// LeetCode - 1758



// Approach 1 - Brute Force (Generating both the strings)
// T.C. - O(3n)
// S.C. - O(2n)
class Solution {
    public int minOperations(String s) {
        int n = s.length();
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();

        for(int i = 0; i<n; i++){
            if(i % 2 == 0){
                sb1.append('0');
                sb2.append('1');
            }
            else{
                sb1.append('1');
                sb2.append('0');
            }
        }

        int count1 = 0;
        for(int i = 0; i<n; i++){
            if(s.charAt(i) != sb1.charAt(i)){
                count1++;
            }
        }

        int count2 = 0;
        for(int i = 0; i<n; i++){
            if(s.charAt(i) != sb2.charAt(i)){
                count2++;
            }
        }

        return Math.min(count1, count2);
    }
}






// Approach 2 - Without Generating Strings
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public int minOperations(String s) {
        int n = s.length();
        int startWithZero = 0; // 010101....
        int startWithOne = 0; // 101010....

        for(int i = 0; i<n; i++){
            if(i % 2 == 0){
                if(s.charAt(i) == '0'){
                    startWithOne++;
                }
                else{
                    startWithZero++;
                }
            }
            else{
                if(s.charAt(i) == '1'){
                    startWithOne++;
                }
                else{
                    startWithZero++;
                }
            }
        }

        return Math.min(startWithOne, startWithZero);
    }
}






// Approach 3 - Slight optimization in approach 2
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public int minOperations(String s) {
        int n = s.length();
        int startWithZero = 0; // 010101....

        for(int i = 0; i<n; i++){
            if(i % 2 == 0){
                if(s.charAt(i) == '1'){
                    startWithZero++;
                }
            }
            else{
                if(s.charAt(i) == '0'){
                    startWithZero++;
                }
            }
        }

        return Math.min(startWithZero, n - startWithZero);
    }
}