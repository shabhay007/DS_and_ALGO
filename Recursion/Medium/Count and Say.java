// LeetCode Medium - 38



// Approach 1 - Recursion
// T.C. - O(2^n)
// S.C. - O(2^n); due to string growth as it grows nearly exponential

/* 
    for n :- result string length
    1 - 1
    2 - 2
    3 - 2
    4 - 4
    5 - 6
    6 - 6
    7 - 8 ,...

    ~ 1 + 2 + 4 + 8 + .... + 2^n-1
    ~ 2^n

    So final time complexity will be ~ O(2^n)

    And so to store the final result string, it will take ~ O(2^n) space
*/

class Solution {
    public String getStr(String str){ // O(k); k = avg length of str in each level
        int n = str.length();
        int i = 1;
        int count = 1;
        char prev = str.charAt(0);
        StringBuilder sb = new StringBuilder();

        while(i < n){
            char ch = str.charAt(i);

            if(ch == prev){
                count++;
            }
            else{
                sb.append(count).append(prev);
                prev = ch;
                count = 1;
            }

            i++;
        }

        sb.append(count).append(prev);

        return sb.toString();
    }

    public String say(int n){ // O(n)
        if(n == 1){
            return "1";
        }

        String prevSay = say(n-1);

        // processing
        String finalStr = getStr(prevSay);

        return finalStr;
    }

    public String countAndSay(int n) {
        if(n == 1){
            return "1";
        }

        return say(n);
    }
}





// Approach 2 - Iterative
// T.C. - O(n.L); L = max length of string & it's not exponential
// S.C. - O(L)
class Solution {
    public String getStr(String str){
        int n = str.length();
        int i = 1;
        int count = 1;
        char prev = str.charAt(0);
        StringBuilder sb = new StringBuilder();

        while(i < n){
            char ch = str.charAt(i);

            if(ch == prev){
                count++;
            }
            else{
                sb.append(count).append(prev);
                prev = ch;
                count = 1;
            }

            i++;
        }

        sb.append(count).append(prev);

        return sb.toString();
    }

    public String countAndSay(int n) {
        if(n == 1){
            return "1";
        }

        String current = "1";

        for(int i = 2; i<=n; i++){
            current = getStr(current);
        }

        return current;
    }
}