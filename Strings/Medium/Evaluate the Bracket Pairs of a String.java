// LeetCode - 1807



// Approach 1 - Hashing + Simulation
// T.C. - O(m + n)
// S.C. - O(m + n)
class Solution {
    public String evaluate(String s, List<List<String>> knowledge) {
        int n = s.length();
        int m = knowledge.size();
        Map<String, String> map = new HashMap<>();

        for(int i = 0; i<m; i++){
            String key = knowledge.get(i).get(0);
            String value = knowledge.get(i).get(1);

            map.put(key, value);
        }

        // processing the s
        StringBuilder sb = new StringBuilder();
        int i = 0;

        while(i < n){
            char ch = s.charAt(i);

            if(ch == '('){
                int j = i+1;

                while(s.charAt(j) != ')'){
                    j++;
                }

                String str = s.substring(i+1, j);

                if(map.containsKey(str)){
                    sb.append(map.get(str));
                }
                else{
                    sb.append('?');
                }

                i = j+1;
            }
            else{
                sb.append(ch);
                i++;
            }
        }

        return sb.toString();
    }
}