// LeetCode -131


// Approach - Backtracking
// T.C. - O(2^n * n)
// S.C. - O(n)
class Solution {
    public boolean isPalindrome(int l, int r, String s){
        while(l <= r){
            if(s.charAt(l) != s.charAt(r)){
                return false;
            }

            l++;
            r--;
        }

        return true;
    }

    public void solve(int idx, String s, List<String> temp, List<List<String>> result){
        if(idx == s.length()){
            result.add(new ArrayList<>(temp));
            return;
        }

        for(int i = idx; i<s.length(); i++){
            if(isPalindrome(idx, i, s)){
                temp.add(s.substring(idx, i+1));
                solve(i+1, s, temp, result);
                temp.remove(temp.size() - 1);
            }
        }
    }

    public List<List<String>> partition(String s) {
        int n = s.length();
        List<List<String>> result = new ArrayList<>();
        List<String> temp = new ArrayList<>();

        solve(0, s, temp, result);

        return result;
    }
}