// LeetCode Medium - 797


// Approach 1 - Backtracking
// T.C. - O(2^n.n);
// S.C. - O(2^n.n + n + n)
class Solution {
    public void getAllPaths(int curr, int target, int[][] graph, List<Integer> currPath, List<List<Integer>> result){
        currPath.add(curr); // do

        if(curr == target){
            result.add(new ArrayList<>(currPath));
        }
        else{ // exploring neighbours
            for(int neigh : graph[curr]){
                getAllPaths(neigh, target, graph, currPath, result);
            }
        }

        currPath.remove(currPath.size() - 1); // backtrack
    }

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        int n = graph.length;
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> currPath = new ArrayList<>();

        getAllPaths(0, n-1, graph, currPath, result);

        return result;
    }
}