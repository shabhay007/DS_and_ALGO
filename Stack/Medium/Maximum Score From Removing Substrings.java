// LeetCode - 1717



// Approach 1 - String + Stack
// T.C. - O(2n)
// S.C. - O(n)
class Solution {
    public String removeSubstr(String s, String str){
        Stack<Character> st = new Stack<>();

        for(char ch : s.toCharArray()){
            if(!st.isEmpty() && ch == str.charAt(1) && st.peek() == str.charAt(0)){
                st.pop();
            }
            else{
                st.push(ch);
            }
        }

        StringBuilder sb = new StringBuilder();

        while(!st.isEmpty()){
            sb.append(st.pop());
        }

        sb.reverse();

        return sb.toString();
    }

    public int maximumGain(String s, int x, int y) {
        int n = s.length();
        int score = 0;

        String maxStr = (x > y) ? "ab" : "ba";
        String minStr = "";

        if(maxStr.equals("ab")) {
            minStr = "ba";
        }
        else {
            minStr = "ab";
        }


        // first pass
        String first = removeSubstr(s, maxStr);
        int newLen = first.length();

        int pairsRemoved = (n - newLen)/2;
        score += pairsRemoved * Math.max(x, y); // to get the max score

        // second pass
        String second = removeSubstr(first, minStr);
        int L = second.length();

        int pairs = (newLen - L)/2;
        score += pairs * Math.min(x, y);

        return score;
    }
}




// Approach 2 - String + Two Pointers
// T.C. - O(2n)
// S.C. - O(n)
class Solution {
    public String removeSubstr(String s, String str){
        int n = s.length();
        int i = 0;
        StringBuilder sb = new StringBuilder(s);

        for(int j = 0; j<n; j++){
            sb.setCharAt(i, s.charAt(j));
            i++;

            if(i >= 2 && sb.charAt(i-2) == str.charAt(0) && sb.charAt(i-1) == str.charAt(1)){
                i -= 2;
            }
        }

        return sb.substring(0, i).toString();
    }

    public int maximumGain(String s, int x, int y) {
        int n = s.length();
        int score = 0;

        String maxStr = (x > y) ? "ab" : "ba";
        String minStr = "";

        if(maxStr.equals("ab")) {
            minStr = "ba";
        }
        else {
            minStr = "ab";
        }


        // first pass
        String first = removeSubstr(s, maxStr);
        int newLen = first.length();

        int pairsRemoved = (n - newLen)/2;
        score += pairsRemoved * Math.max(x, y); // to get the max score

        // second pass
        String second = removeSubstr(first, minStr);
        int L = second.length();

        int pairs = (newLen - L)/2;
        score += pairs * Math.min(x, y);

        return score;
    }
}