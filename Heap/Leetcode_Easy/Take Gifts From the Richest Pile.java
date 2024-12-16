// LeetCode Easy - 2558


// Approach 1 - Optimal Heap
// T.C. - O(n + k*2*log(n) + n)
// S.C. - O(n);
class Solution {
    public long pickGifts(int[] gifts, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);

        for(int gift : gifts){
            pq.offer(gift);
        }

        for(int i = 0; i < k; i++){ // k * 2*log(n); 2 comes because of insertion and deletion
            pq.offer((int) Math.floor(Math.sqrt(pq.poll())));
        }

        long remainingGifts = 0;
        while(!pq.isEmpty()){ // O(n)
            remainingGifts += pq.poll();
        }

        return remainingGifts;
    }
}



// Approach 2 - Optimal Heap
// T.C. - O(n + k*2*log(n))
// S.C. - O(n);
class Solution {
    public long pickGifts(int[] gifts, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);

        long totalSum = 0;
        for(int gift : gifts){
            totalSum += gift;
            pq.offer(gift);
        }

        // subtracting element's sum to be discarded
        for(int i = 0; i < k; i++){ // k * 2*log(n); 2 comes because of insertion and deletion
            if(pq.isEmpty()) break;

            int largest = pq.poll();
            int squareRoot = (int) Math.sqrt(largest);
            totalSum -= largest - squareRoot;
            pq.offer(squareRoot);
        }

        return totalSum;
    }
}