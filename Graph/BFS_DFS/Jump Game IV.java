// LeetCode Hard - 1345



// Approach 1 - BFS
// T.C. - 0(n)
// S.C. - O(n)
class Solution {
    public int solve(int[] arr, Map<Integer, List<Integer>> map, int n) {
        boolean[] visited = new boolean[n];
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0, arr[0], 0});
        visited[0] = true;

        while(!q.isEmpty()){
            int[] curr = q.poll();
            int idx = curr[0];
            int element = curr[1];
            int currSteps = curr[2];

            if(idx == n-1){
                return currSteps;
            }

            // case I
            if(idx + 1 < n && !visited[idx+1]){
                q.offer(new int[]{idx+1, arr[idx+1], currSteps+1});
                visited[idx+1] = true;
            }

            // case II
            if(idx - 1 >= 0 && !visited[idx-1]){
                q.offer(new int[]{idx-1, arr[idx-1], currSteps+1});
                visited[idx-1] = true;
            }

            // case III - Same value jumps
            for(int i : map.get(element)){
                if(i != idx && !visited[i]){
                    q.offer(new int[]{i, arr[i], currSteps+1});
                    visited[i] = true;
                }
            }

            // Imp. Optimisation, otherwise it will stuck in loop and results in O(n^2)
            // Since every element should be processed only once
            map.get(element).clear();
        }

        return -1;
    }

    public int minJumps(int[] arr) {
        int n = arr.length;
        Map<Integer, List<Integer>> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            map.putIfAbsent(arr[i], new ArrayList<>());
            map.get(arr[i]).add(i);
        }

        return solve(arr, map, n);
    }
}







// Approach 2 - BFS
// T.C. - 0(n)
// S.C. - O(n)
class Solution {
    public int solve(int[] arr, Map<Integer, List<Integer>> map, int n) {
        boolean[] visited = new boolean[n];
        Queue<Integer> q = new LinkedList<>();
        q.offer(0);
        visited[0] = true;

        int steps = 0;

        while (!q.isEmpty()) {
            int size = q.size();

            while (size > 0) {
                int curr = q.poll();
                int left = curr - 1;
                int right = curr + 1;

                if (curr == n - 1) {
                    return steps;
                }

                // case I
                if (right < n && !visited[right]) {
                    q.offer(right);
                    visited[right] = true;
                }

                // case II
                if (left >= 0 && !visited[left]) {
                    q.offer(left);
                    visited[left] = true;
                }

                if(map.containsKey(arr[curr])){
                    for(int idx : map.get(arr[curr])){
                        if(curr != idx && !visited[idx]){
                            q.offer(idx);
                            visited[idx] = true;
                        }
                    }

                    // Imp. Optimisation, otherwise it will stuck in loop and 
                    // results in O(n^2)
                    // Since every element should be processed only once
                    map.remove(arr[curr]);
                }

                size--;
            }

            steps++;
        }

        return steps;
    }

    public int minJumps(int[] arr) {
        int n = arr.length;
        Map<Integer, List<Integer>> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            map.putIfAbsent(arr[i], new ArrayList<>());
            map.get(arr[i]).add(i);
        }

        return solve(arr, map, n);
    }
}