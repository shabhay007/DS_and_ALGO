// LeetCode - 2097 - Hard


class Solution {
    public int[][] validArrangement(int[][] pairs) {
        // STEP-1 Build Adjacency list - Graph
        HashMap<Integer, List<Integer>> adj = new HashMap<>();

        // STEP - 2 Find indegree and outdegree
        HashMap<Integer, Integer> indegree = new HashMap<>();
        HashMap<Integer, Integer> outdegree = new HashMap<>();

        for(int[] pair : pairs){
            int u = pair[0];
            int v = pair[1];

            if(!adj.containsKey(u)){
                adj.put(u, new ArrayList<>());
            }

            adj.get(u).add(v);
            // indegree.get(v)++;
            indegree.put(v, indegree.getOrDefault(v, 0) + 1);
            // outdegree.get(u)++;
            outdegree.put(u, outdegree.getOrDefault(u, 0) + 1);
        }

        // STEP - 3 Find the starting node of the Euler path
        int startNode = pairs[0][0];
        for(int node : adj.keySet()){
            if(outdegree.getOrDefault(node, 0) - indegree.getOrDefault(node, 0) == 1){
                startNode = node;
                break;
            }
        }


        // STEP - 4 Perform DFS
        List<Integer> eulerPath = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        stack.push(startNode);

        while(!stack.isEmpty()){
            int curr = stack.peek();

            if(adj.containsKey(curr) && !adj.get(curr).isEmpty()){
                int neigh = adj.get(curr).remove(adj.get(curr).size() - 1);
                stack.push(neigh);
            }
            else{
                eulerPath.add(curr);
                stack.pop();
            }
        }

        // STEP - 5 Build the result from euler path
        Collections.reverse(eulerPath);

        int[][] result = new int[pairs.length][2];
        for(int i = 0; i<pairs.length; i++){
            result[i][0] = eulerPath.get(i);
            result[i][1] = eulerPath.get(i+1);
        }

        return result;
    }
}