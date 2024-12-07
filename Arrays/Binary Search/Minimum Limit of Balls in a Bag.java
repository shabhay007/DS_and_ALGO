// LeetCode Medium - 1760


// Approach that comes first - But it is WRONG APPROACH
// class Solution {
//     public int minimumSize(int[] nums, int maxOperations) {
//         PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>(){
//             public int compare(Integer num1, Integer num2){
//                 return num2 - num1;
//             }
//         });

//         for(int num : nums){
//             pq.offer(num);
//         }

//         for(int i = 0; i<maxOperations; i++){
//             int max = pq.poll();
//             int part1 = (int) Math.floor(max/2.0);
//             int part2 = max - part1;

//             pq.offer(part1);
//             pq.offer(part2);
//         }

//         return pq.peek();
//     }
// }



// Binary Search
// T.C - O(n log(maxElement))
// S.C - O(1)
class Solution {
    public boolean isPossible(int[] nums, int maxOperations, int mid){
        int totalOps = 0;

        for(int num : nums){
            int ops = num/mid;

            if(num % mid == 0){
                ops -= 1;
            }

            totalOps += ops;
        }

        return totalOps <= maxOperations;
    }

    public int minimumSize(int[] nums, int maxOperations) {
        int maxElement = 0;

        for(int num : nums){
            maxElement = Math.max(maxElement, num);
        }

        int l = 1;
        int r = maxElement;
        int result = maxElement; // we have to minimize the result

        // O(n * log(maxElement))
        while(l <= r){
            int mid = l + (r - l)/2;

            if(isPossible(nums, maxOperations, mid)){ // isPossible() - O(n)
                result = mid;
                r = mid - 1;
            }
            else{
                l = mid + 1;
            }
        }

        return result;
    }
}