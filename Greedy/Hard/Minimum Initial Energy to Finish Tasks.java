// LeetCode - 1665



// Approach 1 - Binary Search on Answer
// T.C. - O(nlog(n))
// S.C. - O(1)
class Solution {
    public boolean isPossible(int mid, int[][] tasks){
        int minEnergy = mid;

        for(int[] task : tasks){
            if(minEnergy < task[1]){
                return false;
            }
            else{
                minEnergy -= task[0];
            }
        }

        return minEnergy >= 0;
    }

    public int minimumEffort(int[][] tasks) {
        // sorting based on remaining (decreasing)
        Arrays.sort(tasks, (a, b) -> (b[1] - b[0]) - (a[1] - a[0]));

        int l = 0;
        int r = (int) 1e9;
        int result = 0;

        while(l <= r){
            int mid = l + (r - l)/2;

            if(isPossible(mid, tasks)){
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






// Approach 2 - Greedy
// T.C. - O(nlog(n))
// S.C. - O(1)
class Solution {
    public int minimumEffort(int[][] tasks) {
        int n = tasks.length;

        // sorting based on remaining (min - actual) (decreasing)
        Arrays.sort(tasks, (a, b) -> (b[1] - b[0]) - (a[1] - a[0]));

        int result = 0;
        int sum = 0;
        
        for(int i = 0; i<n; i++){
            result = Math.max(result, tasks[i][1] + sum);
            sum += tasks[i][0];
        }

        return result;
    }
}