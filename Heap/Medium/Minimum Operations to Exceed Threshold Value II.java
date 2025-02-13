// LeetCode Medium - 3066


// Approach 1 - Using Heap
// T.C. - O(nlog(n) + nlog(n))
// S.C. - O(n)
class Solution {
    public int minOperations(int[] nums, int k) {
        PriorityQueue<Long> minHeap = new PriorityQueue<>();

        // this method takes O(nlog(n)) time
        for(long num : nums){ // O(n)
            minHeap.offer(num); // log(n)
        }

        // if we add all the elements of nums
        // internally java uses HEAPIFY and takes O(n) time
        // and above method takes O(nlog(n)) time
        // but this will not work here because of type issue
        // we need some modification in order to run this
        // PriorityQueue<Long> minHeap = new PriorityQueue<>(Arrays.asList(nums));

        int numOfOperations = 0;

        while(minHeap.peek() < k){ // O(n)
            numOfOperations++;

            long x = minHeap.poll(); // log(n)
            long y = minHeap.poll(); // log(n)

            long reqNumber = x * 2 + y;

            minHeap.offer(reqNumber); // log(n)
        }

        return numOfOperations;
    }
}