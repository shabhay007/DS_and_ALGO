// LeetCode Medium - 3310



// Approach 1 - DFS
// T.C. - O(n + m)
// S.C. - O(n + m)
class Solution {
    public void dfs(int curr, List<List<Integer>> adj, Set<Integer> set){
        if(set.contains(curr)){
            return;
        }

        set.add(curr);

        for(int ngbr : adj.get(curr)){
            dfs(ngbr, adj, set);
        }
    }

    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {
        // STEP 1: Graph Building
        List<List<Integer>> adj = new ArrayList<>();

        for(int i = 0; i<n; i++){
            adj.add(new ArrayList<>());
        }

        for(int[] inv : invocations){
            int a = inv[0];
            int b = inv[1];

            adj.get(a).add(b);
        }

        // STEP 2: performing dfs from kth node
        Set<Integer> suspect = new HashSet<>();
        dfs(k, adj, suspect);

        // STEP 3: checking dependency of the suspects
        boolean isRemovalPossible = true;

        for(int i = 0; i<n; i++){
            if(!suspect.contains(i)){
                for(int ngbr : adj.get(i)){
                    if(suspect.contains(ngbr)){
                        isRemovalPossible = false;
                        break;
                    }
                }
            }
        }

        // STEP 4: remaining methods
        List<Integer> list = new ArrayList<>();

        if(!isRemovalPossible){
            for(int i = 0; i<n; i++){
                list.add(i);
            }
        }
        else{
            for(int i = 0; i<n; i++){
                if(!suspect.contains(i)){
                    list.add(i);
                }
            }
        }

        return list;
    }
}






// Approach 2 - BFS
// T.C. - O(n + m)
// S.C. - O(n + m)
class Solution {
    public void dfs(int curr, List<List<Integer>> adj, Set<Integer> set){
        Queue<Integer> q = new LinkedList<>();
        q.offer(curr);

        while(!q.isEmpty()){
            int node = q.poll();
            
            set.add(node);

            for(int ngbr : adj.get(node)){
                if(!set.contains(ngbr)){
                    q.offer(ngbr);
                }
            }
        }
    }

    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {
        // STEP 1: Graph Building
        List<List<Integer>> adj = new ArrayList<>();

        for(int i = 0; i<n; i++){
            adj.add(new ArrayList<>());
        }

        for(int[] inv : invocations){
            int a = inv[0];
            int b = inv[1];

            adj.get(a).add(b);
        }

        // STEP 2: performing dfs from kth node
        Set<Integer> suspect = new HashSet<>();
        dfs(k, adj, suspect);

        // STEP 3: checking dependency of the suspects
        boolean isRemovalPossible = true;

        for(int i = 0; i<n; i++){
            if(!suspect.contains(i)){
                for(int ngbr : adj.get(i)){
                    if(suspect.contains(ngbr)){
                        isRemovalPossible = false;
                        break;
                    }
                }
            }
        }

        // STEP 4: remaining methods
        List<Integer> list = new ArrayList<>();

        if(!isRemovalPossible){
            for(int i = 0; i<n; i++){
                list.add(i);
            }
        }
        else{
            for(int i = 0; i<n; i++){
                if(!suspect.contains(i)){
                    list.add(i);
                }
            }
        }

        return list;
    }
}