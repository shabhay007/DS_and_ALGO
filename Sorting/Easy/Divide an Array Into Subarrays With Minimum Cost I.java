// LeetCode - 3010



// Approach 1 - Heap
// T.C. - O(nlog(n))
// S.C. - O(n)
class Solution {
    public int minimumCost(int[] nums) {
        int n = nums.length;
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for(int i = 1; i<n; i++){
            pq.offer(nums[i]);
        }

        int sum = nums[0];
        int counter = 0;

        while(!pq.isEmpty()){
            sum += pq.poll();
            counter++;

            if(counter == 2){
                break;
            }
        }

        return sum;
    }
}





// Approach 2 - Sorting
// T.C. - O(nlog(n))
// S.C. - O(1)
class Solution {
    public int minimumCost(int[] nums) {
        int n = nums.length;
        int first = nums[0];
        int sum = first;
        nums[0] = Integer.MAX_VALUE;

        Arrays.sort(nums);

        return sum + nums[0] + nums[1];
    }
}





// Approach 3 - Simulation
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public int minimumCost(int[] nums) {
        int n = nums.length;
        int sum = nums[0];
        
        int firstMin = Integer.MAX_VALUE;
        int secondMin = Integer.MAX_VALUE;

        for(int i = 1; i<n; i++){
            if(nums[i] < firstMin){
                secondMin = firstMin;
                firstMin = nums[i];
            }
            else if(nums[i] < secondMin){
                secondMin = nums[i];
            }
        }

        return sum + firstMin + secondMin;
    }
}