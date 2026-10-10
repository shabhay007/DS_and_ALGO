// LeetCode - 2333



// Approach 1 - Heap
// T.C - O(n + klog(n)); k = k1 + k2
// S.C. - O(n)
class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;

        PriorityQueue<Integer> heap = new PriorityQueue<>((a, b) -> b - a);

        for(int i = 0; i<n; i++){
            heap.offer(Math.abs(nums1[i] - nums2[i]));
        }
        
        // now processing min square
        long k = k1 + k2;
        while(k > 0){
            int top = heap.poll();

            if(top == 0){
                heap.offer(top);
                break;
            }

            heap.offer(top-1);
            k--;
        }

        System.out.println(heap);

        long result = 0;
        while(!heap.isEmpty()){
            long top = heap.poll();
            
            if(top == 0){
                break;
            }

            result += 1L * top * top;
        }

        return result;
    }
}







// Approach 2 - Greedy + Counting Sort
// T.C - O(n + maxDiff)
// S.C. - O(n + maxDiff)
class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;

        int[] diff = new int[n];
        int maxDiff = 0;

        for(int i = 0; i<n; i++){
            diff[i] = Math.abs(nums1[i] - nums2[i]);
            maxDiff = Math.max(diff[i], maxDiff);
        }
        
        // countDiff[d] = count of indices with diff exactly d
        int[] countDiff = new int[maxDiff + 1];
        for(int i = 0; i<n; i++){
            countDiff[diff[i]]++;
        }

        long k = k1 + k2;
        for(int currDiff = maxDiff; currDiff > 0 && k > 0; currDiff--){
            int ops = (int) Math.min(k, countDiff[currDiff]);
            countDiff[currDiff] -= ops;
            countDiff[currDiff - 1] += ops;
            k -= ops;
        }

        // processing result
        long result = 0;
        for(int i = 1; i <= maxDiff; i++){
            result += 1L * countDiff[i] * i * i;
        }

        return result;
    }
}