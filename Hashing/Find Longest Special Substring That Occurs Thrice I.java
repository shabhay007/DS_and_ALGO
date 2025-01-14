// LeetCode Medium -2981



// Approach 1 - Brute Force; Using Map
class Solution {
    public int maximumLength(String s) {
        int n = s.length();
        HashMap<String, Integer> map = new HashMap<>();

        // generating all substring and store in map
        for(int i = 0; i<n; i++){
            StringBuilder currString = new StringBuilder();
            
            for(int j = i; j<n; j++){
                if(currString.length() == 0 || s.charAt(j) == currString.charAt(currString.length() - 1)){
                    currString.append(s.charAt(j));

                    String str = currString.toString();
                    map.put(str, map.getOrDefault(str, 0) + 1);
                }
                else{
                    break;
                }
            }
        }

        int maxLength = -1;
        for(Map.Entry<String, Integer> entry : map.entrySet()){
            String key = entry.getKey();
            int count = entry.getValue();

            if(count > 2){
                maxLength = Math.max(maxLength, key.length());
            }
        }

        return maxLength;
    }
}