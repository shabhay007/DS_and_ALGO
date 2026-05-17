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