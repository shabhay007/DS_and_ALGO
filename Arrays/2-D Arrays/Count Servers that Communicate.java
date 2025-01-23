// LeetCode Medium - 1267


// Approach 1 - Using Set, Lists & Strings
// T.C. - O((m * 2n) + (n * 2m))
// S.C. - O((m * n) + n + m)
class Solution {
    public int countServers(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        HashSet<String> servers = new HashSet<>();

        // checking for servers which can communicate to each other in row
        for(int i = 0; i < m; i++){ // O(m * (2n))
            List<String> temp = new ArrayList<>();

            for(int j = 0; j < n; j++){ // O(n)
                if(grid[i][j] == 1){
                    String str = i + "-" + j;
                    temp.add(str);
                }
            }

            if(temp.size() > 1){ // O(n)
                servers.addAll(temp);
            }
        }

        // now checking column-wise
        for(int col = 0; col < n; col++){ // O(n * 2m)
            List<String> temp = new ArrayList<>();

            for(int row = 0; row < m; row++){ // O(m)
                if(grid[row][col] == 1){
                    String str = row + "-" + col;
                    temp.add(str);
                }
            }

            if(temp.size() > 1){ // O(m)
                servers.addAll(temp);
            }
        }

        return servers.size();
    }
}





// Optimal Approach 2 - Using counting arrays
// T.C. - O(2(m * n))
// S.C. - O(m + n)
class Solution {
    public int countServers(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        int[] serverCountInRow = new int[m];
        int[] serverCountInCol = new int[n];

        // counting how many servers are present in specific rows and columns
        for(int row = 0; row < m; row++){
            for(int col = 0; col < n; col++){
                if(grid[row][col] == 1){
                    serverCountInRow[row]++;
                    serverCountInCol[col]++;
                }
            }
        }

        // counting the total servers that communicates with each other
        int totalServers = 0;

        for(int row = 0; row < m; row++){
            for(int col = 0; col < n; col++){
                if(grid[row][col] == 1 && (serverCountInRow[row] > 1 || serverCountInCol[col] > 1)){
                    totalServers++;
                }
            }
        }

        return totalServers;
    }
}