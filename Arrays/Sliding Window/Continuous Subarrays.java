// LeetCode Medium - 2762


// Approach 1 :- Sliding Window, TreeMap(Ordered Map in java)
// T.C :- O(n log(size of map)) ; size of map always <= 3 -> constant, therefore T.C = O(n)
// S.C :- O(log(s)) :- O(1);
class Solution {
    public long continuousSubarrays(int[] nums) {
        int n = nums.length;

        // ordered map in java
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int i = 0;
        int j = 0;
        long count = 0;

        while(j < n){ // O(n)
            map.put(nums[j], map.getOrDefault(nums[j], 0) + 1);

            // checking if diff of each pair is <= 2
            while(Math.abs(map.lastKey() - map.firstKey()) > 2){ // O(log(size of map))
                map.put(nums[i], map.get(nums[i]) - 1);

                if(map.get(nums[i]) == 0){
                    map.remove(nums[i]);
                }

                i++;
            }

            count += j - i + 1; // length of subarrays
            j++;
        }

        return count;
    }
}



// Approach 2 :- Heap
// T.C :- O(n log(size of map)) ; size of map always <= 3 -> constant, therefore T.C = O(n)
// S.C :- O(log(s)) :- O(1);
class Solution {
    public class Pair {
        int num;
        int index;

        public Pair(int num, int idx){
            this.num = num;
            this.index = idx;
        }
    }

    public long continuousSubarrays(int[] nums) {
        int n = nums.length;

        // min-heap
        PriorityQueue<Pair> minHeap = new PriorityQueue<>((a, b) -> a.num - b.num);

        //  max-heap
        PriorityQueue<Pair> maxHeap = new PriorityQueue<>((a, b) -> b.num - a.num);

        int i = 0;
        int j = 0;
        long count = 0;

        while(j < n){ // O(n)
            minHeap.offer(new Pair(nums[j], j)); // O(log(n))
            maxHeap.offer(new Pair(nums[j], j)); // O(log(n))

            // doesn't need to use Math.abs() as element through max-heap is always greater
            // than element through min-heap
            while(Math.abs(maxHeap.peek().num - minHeap.peek().num) > 2){
                i++;

                while(!maxHeap.isEmpty() && maxHeap.peek().index < i){
                    maxHeap.poll();
                }

                while(!minHeap.isEmpty() && minHeap.peek().index < i){
                    minHeap.poll();
                }
            }

            count += j-i+1;
            j++;
        }

        return count;
    }
}



// Approach 3 :- Simulation
// T.C :- O(2*n)
// S.C :- O(1)
class Solution {
    public long continuousSubarrays(int[] nums) {
        int n = nums.length;
        int start = 0;
        int end = 0;
        long count = 0;
        int currMin = Integer.MAX_VALUE;
        int currMax = Integer.MIN_VALUE;

        while(end < n){
            currMin = Math.min(currMin, nums[end]);
            currMax = Math.max(currMax, nums[end]);

            if(currMax - currMin > 2){
                start = end;
                currMin = nums[end];
                currMax = nums[end];

                while(start - 1 >= 0 && Math.abs(nums[start-1] - nums[end]) <= 2){
                    start--;
                    currMin = Math.min(currMin, nums[start]);
                    currMax = Math.max(currMax, nums[start]);
                }
            }

            count += end - start + 1;
            end++;
        }

        return count;
    }
}