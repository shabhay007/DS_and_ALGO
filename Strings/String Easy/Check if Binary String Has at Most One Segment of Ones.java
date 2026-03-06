// LeetCode - 1784



// Approach 1 - Simulation
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public boolean checkOnesSegment(String s) {
        int n = s.length();
        int one = 0;
        int segment = 0;

        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);

            if (ch == '1') {
                one++;
            } else {
                if (one > 0) {
                    segment++;
                }

                one = 0;
            }
        }

        if (one > 0) {
            segment++;
        }

        return (segment > 1) ? false : true;
    }
}







// Approach 2 - Simulation
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public boolean checkOnesSegment(String s) {
        int n = s.length();

        for (int i = 0; i < n-1; i++) {
            if(s.charAt(i) == '0' && s.charAt(i+1) == '1'){
                return false;
            }
        }

        return true;
    }
}






// Approach 3 - String Function
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public boolean checkOnesSegment(String s) {
        return s.contains("01") ? false : true;
    }
}