// LeetCode Hard - 2127



// BFS, Cycle detection
// T.C. - O(n); overall
// S.C. - O(n); overall
class Solution {
    public int BFS(int startNode, List<List<Integer>> graph, boolean[] visited){ // O(n)
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{startNode, 0});

        int maxDistance = 0; // max path length

        while(!q.isEmpty()){
            int[] curr = q.poll();
            int currNode = curr[0];
            int dist = curr[1];

            for(int neigh : graph.get(currNode)){
                if(!visited[neigh]){
                    visited[neigh] = true;
                    q.offer(new int[]{neigh, dist + 1}); // dist for goint currNode -> nextNode = 1

                    // update max distance
                    maxDistance = Math.max(maxDistance, dist + 1);
                }
            }
        }

        return maxDistance;
    }

    public int maximumInvitations(int[] favorite) {
        int n = favorite.length;

        List<List<Integer>> adjList = new ArrayList<>();

        for(int i = 0; i<n; i++){ // O(n)
            adjList.add(new ArrayList<>());
        }

        for(int u = 0; u<n; u++){ // O(n)
            int v = favorite[u];

            // initially u --> v
            // but we will make the graph with reversed edges (reversed graph)
            // so that we can find the path length after traversal
            adjList.get(v).add(u);
        }

        int longestCycleNodesCount = 0;
        int happyNodesGroup = 0; // cycle of only 2 nodes

        boolean[] visited = new boolean[n];

        for(int i = 0; i<n; i++){ // O(n); as we are only visiting nodes which are not visited in all cases
            if(!visited[i]){
                // {currNode, past nodes which has been seen before currNode}
                HashMap<Integer, Integer> nodeCountMap = new HashMap<>();

                int currNode = i;
                int nodeCountForCurrNode = 0;

                while(!visited[currNode]){
                    visited[currNode] = true;
                    nodeCountMap.put(currNode, nodeCountForCurrNode);

                    int nextNode = favorite[currNode]; // or i in place of currNode
                    
                    // now we have seen one node, so increment the node count for 
                    // nextNode
                    nodeCountForCurrNode++;

                    // check if nextNode is visited or not through map
                    // if already visited, means cycle is present
                    if(nodeCountMap.containsKey(nextNode)){ // if visited
                        // means cycle is present, so find the cycle length
                        int cycleLength = nodeCountForCurrNode - nodeCountMap.get(nextNode);
                        longestCycleNodesCount = Math.max(longestCycleNodesCount, cycleLength);

                        if(cycleLength == 2){ // happy nodes group
                            // currNode <--> nextNode
                            boolean[] vis = new boolean[n];

                            vis[currNode] = true;
                            vis[nextNode] = true;

                            // take summation of happy nodes group, as we can accomodate all 
                            // happy nodes group
                            happyNodesGroup += 2 + BFS(currNode, adjList, vis) + BFS(nextNode, adjList, vis);
                        }

                        // we have found cycle, so break
                        break;
                    }

                    // if cycle is not found, then do same for next node
                    currNode = nextNode;
                }
            }
        }

        return Math.max(longestCycleNodesCount, happyNodesGroup);
    }
}