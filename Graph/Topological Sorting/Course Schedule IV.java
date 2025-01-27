// LeetCode Medium - 1462



// Approach 1 - Brute Force - DFS
// T.C. - O(k * (V + E))
// S.C. - (V + E)
class Solution {
    public boolean DFS(List<List<Integer>> adjList, int src, int dest, boolean[] visited){
        visited[src] = true;

        if(src == dest){
            return true;
        }

        // check for neighbours
        boolean isReacheable = false;

        for(int neigh : adjList.get(src)){
            if(!visited[neigh]){
                isReacheable = (isReacheable || DFS(adjList, neigh, dest, visited));
            }
        }

        return isReacheable;
    }

    public List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
        int n = numCourses;
        List<Boolean> result = new ArrayList<>();

        // constructing a adjList
        List<List<Integer>> adjList = new ArrayList<>();

        // initialization
        for(int i = 0; i<n; i++){
            adjList.add(new ArrayList<>());
        }

        // adding the edges
        for(int[] prereq : prerequisites){
            int a = prereq[0];
            int b = prereq[1];

            adjList.get(a).add(b);
        }

        // is reacheable
        for(int[] query : queries){ // O(k * (V + E))
            int u = query[0]; // src
            int v = query[1]; // dest
            boolean[] visited = new boolean[n];

            result.add(DFS(adjList, u, v, visited));
        }

        return result;
    }
}







// Approach 2 - Brute Force - DFS
// T.C. - O(V^2 * (V + E))
// S.C. - (V + E)
class Solution {
    public boolean DFS(List<List<Integer>> adjList, int src, int dest, boolean[] visited){
        visited[src] = true;

        if(src == dest){
            return true;
        }

        // check for neighbours
        boolean isReacheable = false;

        for(int neigh : adjList.get(src)){
            if(!visited[neigh]){
                isReacheable = (isReacheable || DFS(adjList, neigh, dest, visited));
            }
        }

        return isReacheable;
    }

    public void preProcess(int numOfCourses, List<List<Integer>> adjList, boolean[][] grid){
        for(int u = 0; u < numOfCourses; u++){ // O(V^2)
            for(int v = 0; v < numOfCourses; v++){
                if(u != v){
                    boolean[] visited = new boolean[numOfCourses];
                    grid[u][v] = DFS(adjList, u, v, visited); // O(V + E)
                }
            }
        }
    }

    public List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
        int n = numCourses;
        List<Boolean> result = new ArrayList<>();

        // constructing a adjList
        List<List<Integer>> adjList = new ArrayList<>();

        // initialization
        for(int i = 0; i<n; i++){
            adjList.add(new ArrayList<>());
        }

        // adding the edges
        for(int[] prereq : prerequisites){
            int a = prereq[0];
            int b = prereq[1];

            adjList.get(a).add(b);
        }

        // preprocessing
        // grid[i][j] = true; it means i --> j (i is prerequisite of j)
        boolean[][] grid = new boolean[n][n];
        preProcess(n, adjList, grid);

        // is reacheable
        for(int[] query : queries){ // O(k * (V + E))
            int u = query[0]; // src
            int v = query[1]; // dest

            result.add(grid[u][v]);
        }

        return result;
    }
}









// Approach 3 - BFS using Kahn's Algorithm (Topological Sortin), Map
// T.C. - O(2(V + E) + n + k)
// S.C. - (V + E)
class Solution {
    public List<Integer> BFS(int[] indegree, List<List<Integer>> adjList){
        int n = indegree.length;
        List<Integer> result = new ArrayList<>();

        Queue<Integer> course = new LinkedList<>();

        // put the nodes whose indegree is 0
        for(int node = 0; node < n; node++){
            if(indegree[node] == 0){
                course.offer(node);
            }
        }

        // now finding the order
        while(!course.isEmpty()){
            int currCourse = course.poll();
            result.add(currCourse);

            for(int nextCourse : adjList.get(currCourse)){
                indegree[nextCourse]--;

                if(indegree[nextCourse] == 0){
                    course.offer(nextCourse);
                }
            }
        }

        return result;
    }

    public List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
        int n = numCourses;
        List<Boolean> isPrereq = new ArrayList<>();

        if(prerequisites.length == 0){
            for(int[] query : queries){ // add false for all the queries
                isPrereq.add(false);
            }

            return isPrereq;
        }

        // constructing a adjList
        List<List<Integer>> adjList = new ArrayList<>();
        int[] indegree = new int[n];

        // initialization
        for(int i = 0; i<n; i++){
            adjList.add(new ArrayList<>());
        }

        // adding the edges
        for(int[] prereq : prerequisites){
            int a = prereq[0];
            int b = prereq[1];

            adjList.get(a).add(b);
            indegree[b]++;
        }

        // preforming BFS
        List<Integer> result = BFS(indegree, adjList);
        
        // Calculate reachability for each node in topological order
        boolean[][] reachable = new boolean[n][n]; // To store reachability between nodes

        for (int course : result) {
            for (int nextCourse : adjList.get(course)) {
                reachable[course][nextCourse] = true;
                // Propagate reachability
                for (int k = 0; k < n; k++) {
                    if (reachable[k][course]) {
                        reachable[k][nextCourse] = true;
                    }
                }
            }
        }

        // Answer queries based on reachability matrix
        for (int[] query : queries) {
            int u = query[0];
            int v = query[1];
            isPrereq.add(reachable[u][v]);
        }

        return isPrereq;
    }
}






// Approach 4 - Floyd Warshall
// T.C. - O(n^3)
// S.C. - O(n*n)
class Solution {
    public List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
        int n = numCourses;
        boolean[][] grid = new boolean[n][n];

        for(int i = 0; i<prerequisites.length; i++){
            int src = prerequisites[i][0];
            int dest = prerequisites[i][1];

            grid[src][dest] = true;
        }

        //floyd warshall
        for(int k = 0; k < n; k++){ // O(n^3)
            for(int src = 0; src<n; src++){
                for(int dest = 0; dest<n; dest++){
                    grid[src][dest] = grid[src][dest] || (grid[src][k] && grid[k][dest]);
                }
            }
        }

        List<Boolean> result = new ArrayList<>();
        for(int i = 0; i<queries.length; i++){
            int src = queries[i][0];
            int dest = queries[i][1];

            if(grid[src][dest]){
                result.add(true);
            }
            else{
                result.add(false);
            }
        }

        return result;
    }
}