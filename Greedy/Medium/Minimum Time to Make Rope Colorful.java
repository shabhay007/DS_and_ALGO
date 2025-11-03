// LeetCode - 1578




// Approach 1 - Heap
// T.C. - O(nlog(n))
// S.C. - O(n)
class Solution {
    public int minCost(String colors, int[] neededTime) {
        int n = colors.length();
        char[] arr = colors.toCharArray();

        // min heap to minimize the cost of time to remove same colored
        // consecutive balloons
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        minHeap.offer(neededTime[0]);

        int minTime = 0;
        
        for(int i = 1; i<n; i++){
            if(arr[i] == arr[i-1]){
                minHeap.offer(neededTime[i]);
            }
            else{
                int size = minHeap.size();

                while(size > 1){
                    minTime += minHeap.poll();
                    size--;
                }

                minHeap = new PriorityQueue<>();
                minHeap.offer(neededTime[i]);
            }
        }

        // for last iteration
        int size = minHeap.size();

        while(size > 1){
            minTime += minHeap.poll();
            size--;
        }

        return minTime;
    }
}






// Approach 2 - Greedy + Simulation
// T.C. - O(n)
// S.C. - O(n)
class Solution {
    public int minCost(String colors, int[] neededTime) {
        int n = colors.length();
        char[] arr = colors.toCharArray();

        // tracker to minimize the cost of time to remove same colored
        // consecutive balloons
        int max = neededTime[0];
        int resultTime = 0;
        
        for(int i = 1; i<n; i++){
            if(arr[i] == arr[i-1]){
                // we need to keep the max and remove the balloon with min
                // removal time in order to minimize the cost
                if(neededTime[i] < max){
                    resultTime += neededTime[i];
                }
                else{
                    resultTime += max;
                    max = neededTime[i];
                }
            }
            else{
                max = neededTime[i];
            }
        }

        return resultTime;
    }
}





// Approach 3 - Greedy + Simulation (Slight change in Approach 2 to remove extra space)
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public int minCost(String colors, int[] neededTime) {
        int n = colors.length();

        // tracker to minimize the cost of time to remove same colored
        // consecutive balloons
        int max = neededTime[0];
        int resultTime = 0;
        
        for(int i = 1; i<n; i++){
            if(colors.charAt(i) == colors.charAt(i-1)){
                // we need to keep the max and remove the balloon with min
                // removal time in order to minimize the cost
                if(neededTime[i] < max){
                    resultTime += neededTime[i];
                }
                else{
                    resultTime += max;
                    max = neededTime[i];
                }
            }
            else{
                max = neededTime[i];
            }
        }

        return resultTime;
    }
}