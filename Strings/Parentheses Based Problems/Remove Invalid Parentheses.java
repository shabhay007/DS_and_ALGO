// LeetCode Hard - 301



// Approach - Backtracking
// T.C. - O(2^n)
// S.C. - O(n)
class Solution {
    int n;
    Set<String> set;
    int maxLen;

    public void solve(int i, StringBuilder curr, int open, String s){
        if(open < 0){
            return;
        }

        if(i == n){
            if(open == 0){
                if(curr.length() > maxLen){
                    maxLen = curr.length();
                    set.clear();
                }
                
                if(curr.length() == maxLen){
                    set.add(curr.toString());
                }
            }

            return;
        }
        
        char ch = s.charAt(i);

        if(ch != '(' && ch != ')'){
            curr.append(ch);
            solve(i+1, curr, open, s);
            curr.deleteCharAt(curr.length() - 1);
            return;
        }

        curr.append(ch);
        solve(i+1, curr, open + (ch == '(' ? 1 : -1), s);
        curr.deleteCharAt(curr.length() - 1);

        solve(i+1, curr, open, s);
    }

    public List<String> removeInvalidParentheses(String s) {
        n = s.length();
        set = new HashSet<>();
        maxLen = 0;

        solve(0, new StringBuilder(), 0, s);

        return new ArrayList<>(set);
    }
}