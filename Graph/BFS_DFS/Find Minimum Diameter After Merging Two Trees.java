// LeetCode Hard - 3203


// Approach 1 - BFS
// T.C. - O(V+E)
// S.C. - O(V+E)
class Solution {
    public HashMap<Integer, List<Integer>> createAdjList(int[][] edges){
        int n = edges.length;
        HashMap<Integer, List<Integer>> adj = new HashMap<>();

        for(int[] edge : edges){
            int u = edge[0];
            int v = edge[1];

            // if(!adj.containsKey(u)){
            //     adj.put(u, new ArrayList<>());
            // }

            // if(!adj.containsKey(v)){
            //     adj.put(v, new ArrayList<>());
            // }

            adj.putIfAbsent(u, new ArrayList<>());
            adj.putIfAbsent(v, new ArrayList<>());


            adj.get(v).add(u);
            adj.get(u).add(v);
        }

        return adj;
    }

    public int findDiameter(HashMap<Integer, List<Integer>> adj){
        // STEP 1 - Finding the farthest node from a random node, let's say 0
        // STEP 2 - the farthest node we got above will be one end of the diameter of adj

        // 0 is taken as random node
        // here list stores farthest node and distance from the random node
        List<Integer> farthestNode = BFS(adj, 0);

        // STEP 3 - now finding the farthest node from the one end of the diameter we got above
        // and that will be the other end of the diameter
        List<Integer> otherFarthestNode = BFS(adj, farthestNode.get(0));

        return otherFarthestNode.get(1);
    }

    public List<Integer> BFS(HashMap<Integer, List<Integer>> adj, int srcNode){
        Queue<Integer> q = new LinkedList<>();
        q.add(srcNode);

        HashMap<Integer, Boolean> visited = new HashMap<>();
        visited.put(srcNode, true);

        int distance = 0;
        int farthestNode = srcNode;

        while(!q.isEmpty()){
            int n = q.size();

            for(int i = 0; i<n; i++){
                int currNode = q.poll();
                farthestNode = currNode;

                for(int nbr : adj.getOrDefault(currNode, new ArrayList<>())){
                    if(!visited.containsKey(nbr)){
                        visited.put(nbr, true);
                        q.offer(nbr);
                    }
                }
            }

            if(!q.isEmpty()){
                distance++;
            }
        }

        return Arrays.asList(farthestNode, distance);
    }

    public int minimumDiameterAfterMerge(int[][] edges1, int[][] edges2) {
        HashMap<Integer, List<Integer>> adj1 = createAdjList(edges1);
        HashMap<Integer, List<Integer>> adj2 = createAdjList(edges2);

        int d1 = findDiameter(adj1);
        int d2 = findDiameter(adj2);

        int mergedDiameter = (d1+1)/2 + (d2+1)/2 + 1;

        return Math.max(Math.max(d1, d2), mergedDiameter);
    }
}