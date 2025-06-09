// LeetCode - 17



// Approach - Backtracking
// T.C. - O(3^n * 4^m);
// S.C. - O(n); digits.length()

/*
    n is the number of digits that map to 3 letters (2, 3, 4, 5, 6, 8)
    m is the number of digits that map to 4 letters (7 and 9)
*/
class Solution {
    public void findCombination(int i, String digits, Map<Integer, String> map, List<String> list, StringBuilder sb, int n){
        if(i == n){
            list.add(sb.toString());
            return;
        }

        int digit = digits.charAt(i) - '0';

        for(char ch : map.get(digit).toCharArray()){
            sb.append(ch);
            findCombination(i+1, digits, map, list, sb, n);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public List<String> letterCombinations(String digits) {
        int n = digits.length();
        List<String> list = new ArrayList<>();

        if(n == 0){
            return list;
        }

        Map<Integer, String> map = new HashMap<>();
        map.put(2, "abc");
        map.put(3, "def");
        map.put(4, "ghi");
        map.put(5, "jkl");
        map.put(6, "mno");
        map.put(7, "pqrs");
        map.put(8, "tuv");
        map.put(9, "wxyz");

        findCombination(0, digits, map, list, new StringBuilder(), n);

        return list;
    }
}