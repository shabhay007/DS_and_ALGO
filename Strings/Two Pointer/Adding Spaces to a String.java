// LeetCode Medium - 2109



// Approach 1 - Gives TLE - Using Set and StringBuilder
class Solution {
    public String addSpaces(String s, int[] spaces) {
        StringBuilder sb = new StringBuilder();
        sb.append(s);

        HashSet<Integer> set = new HashSet<>();
        for(int space : spaces){
            set.add(space);
        }

        for(int i = s.length() - 1; i>=0; i--){
            if(!set.isEmpty() && set.contains(i)){
                sb.insert(i, " ");
                set.remove(i);
            }
        }

        return sb.toString();
    }
}




// Approach 2 - Optimal - Using Two pointer and StringBuilder
class Solution {
    public String addSpaces(String s, int[] spaces) {
        int m = s.length();
        int n = spaces.length;
        StringBuilder sb = new StringBuilder();

        int j = 0;
        for(int i = 0; i<m; i++){
            if(j < n && i == spaces[j]){
                sb.append(" ");
                j++;
            }

            sb.append(s.charAt(i));
        }

        return sb.toString();
    }
}