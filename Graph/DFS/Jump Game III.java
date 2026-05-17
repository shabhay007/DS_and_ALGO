// LeetCode Medium - 1306



// Approach 1 - DFS
// T.C. - O(n)
// S.C. - O(n)
class Solution {
    public boolean solve(int i, int[] arr, int n, boolean[] visited){
        if(i < 0 || i >= n || visited[i]){
            return false;
        }

        visited[i] = true;

        if(arr[i] == 0){
            return true;
        }

        return solve(i + arr[i], arr, n, visited) || solve(i - arr[i], arr, n, visited);
    }

    public boolean canReach(int[] arr, int start) {
        int n = arr.length;
        boolean[] visited = new boolean[n];

        return solve(start, arr, n, visited);
    }
}





// Approach 2 - BFS
// T.C. - O(n)
// S.C. - O(n)
// Approach 2 - BFS
// T.C. - O(n)
// S.C. - O(n)
class Solution {
    public boolean canReach(int[] arr, int start) {
        int m = arr.length;
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[m];

        q.add(start);
        visited[start] = true;

        while(!q.isEmpty()){
            int i = q.poll();
            int num = arr[i];

            if(num == 0){
                return true;
            }

            int n1 = i + num;
            int n2 = i - num;

            if(n1 >= 0 && n1 < m && !visited[n1]){
                q.offer(n1);
                visited[n1] = true;
            }

            if(n2 >= 0 && n2 < m && !visited[n2]){
                q.offer(n2);
                visited[n2] = true;
            }
        }

        return false;
    }
}