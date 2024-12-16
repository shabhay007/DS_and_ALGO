// LeetCode Easy - 3264


// Approach 1 - Brute Force (Simulation)
// T.C - O((n * k)
// S.C - O(1)
class Solution {
    public int[] getFinalState(int[] nums, int k, int multiplier) {
        int n = nums.length;

        for(int i = 0; i<k; i++){
            int minElementIndex = 0;

            for(int j = 0; j<n; j++){
                if(nums[j] < nums[minElementIndex]){
                    minElementIndex = j;
                }
            }

            nums[minElementIndex] = multiplier * nums[minElementIndex];
        }

        return nums;
    }
}



// Approach 2 - Heap
// T.C - O((n * log(n)) + (k * log(n)))
// S.C - O(n)
class Solution {
    public class Pair {
        int element;
        int index;

        public Pair(int element, int index){
            this.element = element;
            this.index = index;
        }
    }

    public int[] getFinalState(int[] nums, int k, int multiplier) {
        int n = nums.length;
        PriorityQueue<Pair> pq = new PriorityQueue<>(new Comparator<Pair>(){
            public int compare(Pair p1, Pair p2){
                if(p1.element == p2.element){
                    return p1.index - p2.index;
                }

                return p1.element - p2.element;
            }
        });

        // When insertion happens in empty heap, T.C. will be O(n)
        for(int i = 0; i<n; i++){
            pq.offer(new Pair(nums[i], i));
        }

        for(int i = 0; i<k; i++){ // O(k * log(n))
            Pair p = pq.poll();
            int ele = p.element;
            int idx = p.index;

            pq.offer(new Pair(ele * multiplier, idx));
        }

        int[] result = new int[n];
        while(!pq.isEmpty()){
            Pair p = pq.poll();
            int ele = p.element;
            int idx = p.index;

            result[idx] = ele;
        }

        return result;
    }
}