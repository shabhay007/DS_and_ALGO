// LeetCode - 1353



// Approach 1 - Greedy + Heap
// T.C. - O(nlog(n))
// S.C. - O(n)
class Solution {
    public int maxEvents(int[][] events) {
        int n = events.length;
        Arrays.sort(events, (a, b) -> Integer.compare(a[0], b[0]));

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));

        int day = events[0][0];
        int i = 0;
        int result = 0;

        while(!pq.isEmpty() || i < n){
            while(i < n && events[i][0] == day){
                pq.offer(new int[]{events[i][0], events[i][1]});
                i++;
            }

            // pq will ensure, we'll attend the event having min end day
            if(!pq.isEmpty()){
                pq.poll();
                result++; // 1 event attended
            }

            day++;

            // skipping those events whose endDay < curr day
            // that means there are no ongoing meetings in curr day to attend
            while(!pq.isEmpty() && pq.peek()[1] < day){
                pq.poll();
            }
        }

        return result;
    }
}





// Approach 2 - Greedy + Heap (Slight improvement over Approach 1)
// T.C. - O(nlog(n))
// S.C. - O(n)
class Solution {
    public int maxEvents(int[][] events) {
        int n = events.length;
        Arrays.sort(events, (a, b) -> Integer.compare(a[0], b[0]));

        // we'll store only endDay
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> Integer.compare(a, b));

        int day = events[0][0];
        int i = 0;
        int result = 0;

        while(!pq.isEmpty() || i < n){
            // events [{5, 10}, {15, 20}]
            // pq = {}; empty
            // day = 6, 7
            // skipping days from 6-14
            if(pq.isEmpty()){
                day = events[i][0];
            }

            while(i < n && events[i][0] == day){
                pq.offer(events[i][1]);
                i++;
            }

            // pq will ensure, we'll attend the event having min end day
            if(!pq.isEmpty()){
                pq.poll();
                result++; // 1 event attended
            }

            day++;

            // skipping those events whose endDay < curr day
            // that means there are no ongoing meetings in curr day to attend
            while(!pq.isEmpty() && pq.peek() < day){
                pq.poll();
            }
        }

        return result;
    }
}