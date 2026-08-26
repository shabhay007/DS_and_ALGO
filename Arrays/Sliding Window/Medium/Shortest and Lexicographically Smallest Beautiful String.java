// LeetCode - 2904



// Approach 1 - Sliding Window
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        int n = s.length();

        String result = "";
        int one = 0;
        int i = 0;
        int j = 0;

        while(j < n){
            char ch = s.charAt(j);

            if(ch == '1'){
                one++;
            }

            while(one > k || (i <= j && s.charAt(i) == '0')){
                char chi = s.charAt(i);

                if(chi == '1'){
                    one--;
                }

                i++;
            }

            if(one == k){
                String sub = s.substring(i, j+1);

                if(result.isEmpty() || sub.length() < result.length()){
                    result = sub;
                }
                else if(sub.length() == result.length() && sub.compareTo(result) < 0){
                    result = sub;
                }
            }

            j++;
        }

        return result;
    }
}