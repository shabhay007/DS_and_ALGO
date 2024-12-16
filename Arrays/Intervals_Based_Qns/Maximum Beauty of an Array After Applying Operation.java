// LeetCode Medium - 2779


// Approach 4 - Sweep Line Algorithm
// T.C. - O(2 * n)
// S.C. - O(max in nums + 1)
class Solution {
    public int maximumBeauty(int[] nums, int k) {
        int n = nums.length;

        if(n == 1) return 1;

        int max = 0;
        for(int num : nums){
            max = Math.max(max, num);
        }

        int count[] = new int[max + 1];

        // insert range in count array
        for(int num : nums){
            // restricting to 0; as num - k could go -ve
            count[Math.max(num - k, 0)]++;

            // restricting to max element
            count[Math.min(num+k+1, max)]--;
        }

        // find max of prefix sum
        int currSum = 0; 
        int maxSum = 0;
        for(int c : count){
            currSum += c;
            maxSum = Math.max(maxSum, currSum);
        }

        return maxSum;
    }
}

// Approach 1 - Using Queue (interval based)
// T.C - O(n * log(n))
// S.C - O(n)
class Solution {
    public int maximumBeauty(int[] nums, int k) {
        int n = nums.length;

        int[][] intervals = new int[n][2];
        for(int i = 0; i<n; i++){ // O(n)
            intervals[i] = new int[]{nums[i] - k, nums[i] + k};
        }

        Arrays.sort(intervals, (a, b) -> a[0] - b[0]); // O(n * log(n))

        Queue<Integer> q = new LinkedList<>();
        int maxBeauty = 0;

        for(int[] interval : intervals){ // O(2 * n)
            while(!q.isEmpty() && q.peek() < interval[0]){
                q.poll();
            }

            q.offer(interval[1]);

            maxBeauty = Math.max(maxBeauty, q.size());
        }

        return maxBeauty;
    }
}



// Approach 2 - Binary Search
// T.C - O(n * log(n))
// S.C - O(1)
class Solution {
    public int binarySearch(int target, int[] nums){
        int start = 0;
        int end = nums.length - 1;
        int result = -1;

        while(start <= end){
            int mid = start + (end - start)/2;

            if(nums[mid] <= target){ // x+2*k
                result = mid;
                start = mid + 1; // we need farthest element, so move rightwards
            }
            else{
                end = mid - 1;
            }
        }

        return result;
    }

    public int maximumBeauty(int[] nums, int k) {
        int n = nums.length;

        // sort the array
        Arrays.sort(nums); // O(n * log(n))

        int maxBeauty = 0;

        for(int i = 0; i<n; i++){
            // x < y
            // (x-k, x+k)   (y-k, y+k)
            // x+k <= y-k ; for overlapping
            // y <= x + 2*k
            int x = nums[i];
            int y = x + 2*k;

            // Find the farthest y, such that y <= x + 2*k
            int indexOfY = binarySearch(y, nums); // log(n)

            maxBeauty = Math.max(maxBeauty, indexOfY-i+1);
        }

        return maxBeauty;
    }
}



// Approach 3 - Sliding Window
// T.C - O(n + n * log(n))
// S.C - O(1)
class Solution {
    public int maximumBeauty(int[] nums, int k) {
        int n = nums.length;

        // sort the array
        Arrays.sort(nums); // O(n * log(n))

        int maxBeauty = 0;
        int i = 0; 
        int j = 0;

        while(i < n){ // O(2*n) i.e. every element is visited 2 times
            while(j < n && nums[j] <= nums[i] + 2*k){ // y <= x + 2*k
                j++;
            }

            maxBeauty = Math.max(maxBeauty, j-i);
            i++;
        }

        return maxBeauty;
    }
}