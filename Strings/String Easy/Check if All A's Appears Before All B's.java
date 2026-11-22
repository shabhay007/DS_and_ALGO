// LeetCode - 2124



// Approach 1 - String
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public boolean checkString(String s) {
        int n = s.length();
        boolean b = false;

        for(int i = 0; i<n; i++){
            if(s.charAt(i) == 'b'){
                b = true;
            }

            if(b && s.charAt(i) == 'a'){
                return false;
            }
        }

        return true;
    }
}





// Approach 2 - String Property
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public boolean checkString(String s) {
        return s.contains("ba") ? false : true;
    }
}






// Approach 3 - String
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public boolean checkString(String s) {
        int n = s.length();

        for(int i = 0; i<n-1; i++){
            if(s.charAt(i) == 'b' && s.charAt(i+1) == 'a'){
                return false;
            }
        }

        return true;
    }
}