// LeetCode - 756



// Approach 1 - Backtracking
// T.C. - O(Exponential)
// S.C. - O(n)
class Solution {
    public boolean isValid(StringBuilder curr, Map<String, List<Character>> map, int i,
        StringBuilder above){
        if(curr.length() == 1){ // we have reached the top
            return true;
        }
        
        // not possible to make pairs
        // out of bound, it means we need to go for the string 'above'
        // and for that row string index will start from 0
        if(i+1 == curr.length()){
            return isValid(above, map, 0, new StringBuilder());
        }

        // now make the pair
        String pair = curr.substring(i, i+2);

        // if this pair is not present in map, not possible to make the pyramid
        if(!map.containsKey(pair)){
            return false;
        }

        // backtracking
        // checking all paths
        for(Character ch : map.get(pair)){
            // do
            above.append(ch);

            // explore
            if(isValid(curr, map, i+1, above)){
                return true;
            }

            // undo
            above.deleteCharAt(above.length() - 1);
        }

        return false;
    }

    public boolean pyramidTransition(String bottom, List<String> allowed) {
        int n = bottom.length();
        Map<String, List<Character>> map = new HashMap<>();

        for(String str : allowed){
            String key = str.substring(0, 2);
            Character ch = str.charAt(2);

            map.putIfAbsent(key, new ArrayList<>());
            map.get(key).add(ch);
        }

        StringBuilder sb = new StringBuilder(bottom);
        return isValid(sb, map, 0, new StringBuilder());
    }
}






// Approach 2 - Backtracking + Memoization
// T.C. ~(L^n) , L = maximum count of top characters available for each pairs 
// in allowed, n = bottom.length()

// S.C : O(n^2) recursion stack can go at most n levels deep, and at each level 
// you keep a partially built row of length ≤ n, so the total stack memory is 
// O(n × n) = O(n²)
class Solution {
    public boolean isValid(StringBuilder curr, Map<String, List<Character>> map, int i,
        StringBuilder above, Map<String, Boolean> dp){
        if(curr.length() == 1){ // we have reached the top
            return true;
        }

        String key = curr + "-" + i + "-" + above.toString();
        if(dp.containsKey(key)){
            return dp.get(key);
        }
        
        // not possible to make pairs
        // out of bound, it means we need to go for the string 'above'
        // and for that row string index will start from 0
        if(i+1 == curr.length()){
            boolean res = isValid(above, map, 0, new StringBuilder(), dp);
            dp.put(key, res);
            return res;
        }

        // now make the pair
        String pair = curr.substring(i, i+2);

        // if this pair is not present in map, not possible to make the pyramid
        if(!map.containsKey(pair)){
            dp.put(key, false);
            return false;
        }

        // backtracking
        // checking all paths
        for(Character ch : map.get(pair)){
            // do
            above.append(ch);

            // explore
            if(isValid(curr, map, i+1, above, dp)){
                dp.put(key, true);
                return true;
            }

            // undo
            above.deleteCharAt(above.length() - 1);
        }

        dp.put(key, false);
        return false;
    }

    public boolean pyramidTransition(String bottom, List<String> allowed) {
        int n = bottom.length();
        Map<String, List<Character>> map = new HashMap<>();

        for(String str : allowed){
            String key = str.substring(0, 2);
            Character ch = str.charAt(2);

            map.putIfAbsent(key, new ArrayList<>());
            map.get(key).add(ch);
        }

        StringBuilder sb = new StringBuilder(bottom);
        Map<String, Boolean> dp = new HashMap<>();

        return isValid(sb, map, 0, new StringBuilder(), dp);
    }
}