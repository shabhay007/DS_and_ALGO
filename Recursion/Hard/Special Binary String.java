// LeetCode - 761



// Approach 1 - Recursion
// T.C. - O(n^2)
// S.C. - O(n)
class Solution {
    public String solve(String s) {
        List<String> specials = new ArrayList<>();
        int sum = 0;
        int start = 0;

        for (int i = 0; i < s.length(); i++) {
            sum += (s.charAt(i) == '1') ? 1 : -1;

            if (sum == 0) {
                String inner = s.substring(start + 1, i);
                specials.add("1" + solve(inner) + "0");
                start = i + 1;
            }
        }

        Collections.sort(specials, Collections.reverseOrder());

        StringBuilder result = new StringBuilder();
        for (String str : specials) {
            result.append(str);
        }

        return result.toString();
    }

    public String makeLargestSpecial(String s) {
        return solve(s);
    }
}