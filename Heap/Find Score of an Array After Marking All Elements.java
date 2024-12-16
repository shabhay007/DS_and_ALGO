// LeetCode Medium - 2593


// Approach 1 :- Heap
// T.C. - O(n log(n) + n)
// S.C. - O(n)
class Solution {
    public class Pair {
        int element;
        int index;

        public Pair(int element, int index){
            this.element = element;
            this.index = index;
        }
    }

    public long findScore(int[] nums) {
        int n = nums.length;

        PriorityQueue<Pair> pq = new PriorityQueue<>(new Comparator<Pair>(){
            public int compare(Pair p1, Pair p2){
                if(p1.element == p2.element){
                    return p1.index - p2.index;
                }
                
                return p1.element - p2.element;
            }
        });

        for(int i = 0; i<n; i++){
            pq.offer(new Pair(nums[i], i));
        }

        boolean[] visited = new boolean[n];
        long score = 0;

        while(!pq.isEmpty()){
            Pair p = pq.poll();
            int ele = p.element;
            int idx = p.index;

            if(!visited[idx]){
                score += ele;

                visited[idx] = true;

                if(idx - 1 >= 0 && !visited[idx-1]){
                    visited[idx-1] = true;
                }

                if( idx + 1 < n && !visited[idx+1]){
                    visited[idx+1] = true;
                }
            }
        }

        return score;
    }
}



// Approach 2 :- Sorting and Simulation
// T.C. - O(n log(n) + n)
// S.C. - O(n)
class Solution {
    public class Pair {
        int element;
        int index;

        public Pair(int element, int index){
            this.element = element;
            this.index = index;
        }
    }

    public long findScore(int[] nums) {
        int n = nums.length;
        Pair arr[] = new Pair[n];

        for(int i = 0; i<n; i++){
            arr[i] = new Pair(nums[i], i);
        }

        Arrays.sort(arr, (a, b) -> a.element - b.element);

        boolean[] visited = new boolean[n];
        long score = 0;

        for(int i = 0; i<n; i++){
            int ele = arr[i].element;
            int idx = arr[i].index;

            if(!visited[idx]){
                score += ele;

                visited[idx] = true;

                if(idx - 1 >= 0 && !visited[idx - 1]){
                    visited[idx - 1] = true;
                }
                
                if(idx + 1 < n && !visited[idx + 1]){
                    visited[idx + 1] = true;
                }
            }
        }

        return score;
    }
}