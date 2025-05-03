// GeeksForGeeks


// Backtracking
// T.C. - O(4^(n*n)); accurately - O(3^(n*n))
// S.C. - O(n*n)
class Solution {
    public void getAllPaths(int i, int j, int n, int[][] maze, boolean[][] visited, StringBuilder temp, ArrayList<String> result){
        if(i >= n || i < 0 || j >= n || j < 0 || maze[i][j] == 0 || visited[i][j]){
            return;
        }
        
        if(i == n-1 && j == n-1){
            result.add(temp.toString());
            return;
        }
        
        visited[i][j] = true;
        
        temp.append("R");
        getAllPaths(i, j+1, n, maze, visited, temp, result); // right
        temp.deleteCharAt(temp.length() - 1);
        
        temp.append("D");
        getAllPaths(i+1, j, n, maze, visited, temp, result); // down
        temp.deleteCharAt(temp.length() - 1);
        
        temp.append("L");
        getAllPaths(i, j-1, n, maze, visited, temp, result); // left
        temp.deleteCharAt(temp.length() - 1);
        
        temp.append("U");
        getAllPaths(i-1, j, n, maze, visited, temp, result); // up
        temp.deleteCharAt(temp.length() - 1);
        
        visited[i][j] = false;
    }
    
    // Function to find all possible paths
    public ArrayList<String> ratInMaze(int[][] maze) {
        int n = maze.length;
        
        ArrayList<String> result = new ArrayList<>();
        StringBuilder temp = new StringBuilder();
        boolean[][] visited = new boolean[n][n];
        
        if(maze[0][0] == 0) return result;
        
        getAllPaths(0, 0, n, maze, visited, temp, result);
        
        // sorting
        Collections.sort(result);
        
        return result;
    }
}