// LeetCode Medium - 1061



//Approach 1 - DFS
//T.C : O(m*(V+E))
//S.C : O(V+E)
class Solution {
    public char dfs(char start, boolean[] visited, Map<Character, List<Character>> adj){
        visited[start - 'a'] = true;

        char minCh = start;

        for(char ch : adj.getOrDefault(start, new ArrayList<>())){
            if(!visited[ch - 'a']){
                char next = dfs(ch, visited, adj);

                if(next < minCh){
                    minCh = next;
                }
            }
        }

        return minCh;
    }

    public String smallestEquivalentString(String s1, String s2, String baseStr) {
        int n = s1.length();
        int m = baseStr.length();

        Map<Character, List<Character>> adj = new HashMap<>();

        for(int i = 0; i<n; i++){
            char u = s1.charAt(i);
            char v = s2.charAt(i);

            adj.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
            adj.computeIfAbsent(v, k -> new ArrayList<>()).add(u);
        }

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i<m; i++){
            boolean[] visited = new boolean[26];
            char ch = baseStr.charAt(i);
            char minCh = dfs(ch, visited, adj);
            sb.append(minCh);
        }

        return sb.toString();
    }
}
