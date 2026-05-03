// LeetCode - 796



// Approach 1 - StringBuilder
// T.C. - O(n^2)
// S.C. - O(n)
class Solution {
    public boolean rotateString(String s, String goal) {
        int n = s.length();
        StringBuilder sb = new StringBuilder(s);

        for(int i = 0; i<n; i++){
            if(sb.toString().equals(goal)){
                return true;
            }

            char ch = sb.charAt(0);
            sb.deleteCharAt(0);
            sb.append(ch);
        }

        return false;
    }
}




// Approach 2 - String Concatenation
// T.C. - O(n)
// S.C. - O(n)
class Solution {
    public boolean rotateString(String s, String goal) {
        if(s.length() != goal.length()){
            return false;
        }

        String str = s + s;

        return str.contains(goal);
    }
}