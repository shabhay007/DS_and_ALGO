// LeetCode Hard - 1298



// Approach 1
// T.C. - O(n + k + b)
// S.C. - O(n + k + b)
class Solution {
    public int maxCandies(int[] status, int[] candies, int[][] keys, int[][] containedBoxes, int[] initialBoxes) {
        int n = status.length;

        // Queue<Integer> q = new LinkedList<>();
        Set<Integer> boxes = new HashSet<>();
        Set<Integer> setOfKeys = new HashSet<>();
        boolean[] visited = new boolean[n];
        int result = 0;

        for(int box : initialBoxes){
            boxes.add(box);
        }

        boolean flag = true;

        while(flag){
            flag = false;
            
            for(int box : new HashSet<>(boxes)){
                if(visited[box]){
                    continue;
                }

                if(status[box] == 1 || setOfKeys.contains(box)){
                    visited[box] = true;
                    result += candies[box];
                    flag = true;

                    for(int key : keys[box]){ // O(k)
                        setOfKeys.add(key);
                    }

                    // adding contained boxes
                    for(int newBox : containedBoxes[box]){ // O(b); boxes
                        boxes.add(newBox);
                    }
                }
            }
        }

        return result;
    }
}






// Approach 2 - DFS
// T.C. - O(n); visiting all boxes only once
// S.C. - O(2n)
class Solution {
    public int dfs(int currBox, int[] status, int[] candies, int[][] keys, int[][] containedBoxes, boolean[] visited, Set<Integer> foundBoxes){
        if(visited[currBox]){
            return 0;
        }

        if(status[currBox] == 0){
            // adding curr box to set to avoid duplicacy and
            // at the same time, if we find key for this
            // box, we can open this in future
            foundBoxes.add(currBox);
            return 0;
        }

        visited[currBox] = true;
        int totalCandies = candies[currBox];

        // now exploring its neighbours
        for(int newBox : containedBoxes[currBox]){
            if(!visited[newBox]){
                totalCandies += dfs(newBox, status, candies, keys, containedBoxes, visited, foundBoxes);
            }
        }

        // now finding keys for the found boxes
        for(int boxKey : keys[currBox]){
            // marking the status 1
            status[boxKey] = 1;

            if(foundBoxes.contains(boxKey)){
                totalCandies += dfs(boxKey, status, candies, keys, containedBoxes, visited, foundBoxes);
            }
        }

        return totalCandies;
    }

    public int maxCandies(int[] status, int[] candies, int[][] keys, int[][] containedBoxes, int[] initialBoxes) {
        int n = status.length;

        int result = 0;
        boolean[] visited = new boolean[n];
        Set<Integer> foundBoxes = new HashSet<>();

        for(int initialBox : initialBoxes){
            result += dfs(initialBox, status, candies, keys, containedBoxes, visited, foundBoxes);
        }

        return result;
    }
}





// Approach 3 - BFS
// T.C. - O(n); visiting all boxes only once
// S.C. - O(3n)
class Solution {
    public int maxCandies(int[] status, int[] candies, int[][] keys, int[][] containedBoxes, int[] initialBoxes) {
        int n = status.length;

        int result = 0;
        boolean[] visited = new boolean[n];
        Set<Integer> foundBoxes = new HashSet<>();
        Queue<Integer> q = new LinkedList<>();

        for(int box : initialBoxes){
            foundBoxes.add(box);

            if(status[box] == 1){
                q.offer(box);
                visited[box] = true;
                result += candies[box];
            }
        }

        while(!q.isEmpty()){
            int currBox = q.poll();

            for(int nextBox : containedBoxes[currBox]){
                foundBoxes.add(nextBox);

                if(status[nextBox] == 1 && !visited[nextBox]){
                    q.offer(nextBox);
                    visited[nextBox] = true;
                    result += candies[nextBox];
                }
            }

            // storing the keys
            for(int keyBox : keys[currBox]){
                status[keyBox] = 1;

                if(foundBoxes.contains(keyBox) && !visited[keyBox]){
                    q.offer(keyBox);
                    visited[keyBox] = true;
                    result += candies[keyBox];
                }
            }
        }

        return result;
    }
}