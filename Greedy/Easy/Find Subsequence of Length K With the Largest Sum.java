// LeetCode - 2099



// Approach 1 (Brute Force) - Backtracking (Gives TLE)
// T.C. - O(2^n * k)
// S.C. - O(k + n)
class Solution {
    int maxSum = Integer.MIN_VALUE;

    public void getMax(int i, int[] nums, int k, List<Integer> temp, List<Integer> result){
        if(temp.size() == k){
            int sum = 0;
            for(int num : temp){
                sum += num;
            }

            if(sum > maxSum){
                result.clear();
                result.addAll(temp);
                maxSum = sum;
            }

            return;
        }

        if(i >= nums.length || temp.size() > k){
            return;
        }

        temp.add(nums[i]);
        getMax(i+1, nums, k, temp, result);
        temp.remove(temp.size() - 1);

        getMax(i+1, nums, k, temp, result);
    }

    public int[] maxSubsequence(int[] nums, int k) {
        int n = nums.length;
        List<Integer> result = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();

        getMax(0, nums, k, temp, result);

        int[] arr = new int[result.size()];

        for (int i = 0; i < result.size(); i++) {
            arr[i] = result.get(i);
        }

        return arr;
    }
}




// Approach 2 - Greedy + Sorting
// T.C. - O(n + nlog(n) + n)
// S.C. - O(n)
class Solution {
    public int[] maxSubsequence(int[] nums, int k) {
        int n = nums.length;
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>(){
            public int compare(int[] a, int[] b){
                return b[0] - a[0];
            }
        });

        for(int i = 0; i<n; i++){
            pq.offer(new int[]{nums[i], i});
        }


        int[] result = new int[k];
        List<int[]> list = new ArrayList<>();

        while(!pq.isEmpty() && k > 0){
            int[] pair = pq.poll();
            list.add(pair);
            k--;
        }

        Collections.sort(list, (a, b) -> Integer.compare(a[1], b[1]));

        int i = 0;

        for(int[] pair : list){
            result[i] = pair[0];
            i++;
        }

        return result;
    }
}





// Approach 3 - Greedy + Sorting (Improvement over Approach 2)
// T.C. - O(nlog(k) + k)
// S.C. - O(k)
class Solution {
    public int[] maxSubsequence(int[] nums, int k) {
        int n = nums.length;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));

        for(int i = 0; i<n; i++){
            if(pq.size() < k){
                pq.offer(new int[]{nums[i], i});
            }
            else if(nums[i] > pq.peek()[0]){
                pq.poll();
                pq.offer(new int[]{nums[i], i});
            }
        }


        int[] result = new int[k];
        List<int[]> list = new ArrayList<>();

        while(!pq.isEmpty()){
            list.add(pq.poll());
        }

        Collections.sort(list, (a, b) -> Integer.compare(a[1], b[1]));

        int i = 0;

        for(int[] pair : list){
            result[i] = pair[0];
            i++;
        }

        return result;
    }
}






// Approach 4 - Greedy + Sorting
// T.C. - O(n + nlog(n) + klog(k) + k)
// S.C. - O(n)
class Solution {
    public int[] maxSubsequence(int[] nums, int k) {
        int n = nums.length;
        int[][] arr = new int[n][2];

        for(int i = 0; i<n; i++){
            arr[i][0] = i;
            arr[i][1] = nums[i];
        }

        // sorting in descending order
        Arrays.sort(arr, (a, b) -> Integer.compare(b[1], a[1]));

        // now sorting the top k pair a/c to index to maintain the order
        // in ascending order
        Arrays.sort(arr, 0, k, (a, b) -> Integer.compare(a[0], b[0]));


        int[] result = new int[k];

        for(int i = 0; i<k; i++){
            result[i] = arr[i][1];
        }

        return result;
    }
}





// Approach 5 - Greedy + Sorting (Like 3 - using Array instead of Heap)
// T.C. - O(n + nlog(n) + klog(k) + k)
// S.C. - O(n)
class Solution {
    public int[] maxSubsequence(int[] nums, int k) {
        int n = nums.length;
        int[][] arr = new int[n][2];

        for(int i = 0; i<n; i++){
            arr[i][0] = i;
            arr[i][1] = nums[i];
        }

        // sorting in descending order
        Arrays.sort(arr, (a, b) -> Integer.compare(b[1], a[1]));

        int[][] newArr = new int[k][2];

        for(int i = 0; i<k; i++){
            newArr[i][0] = arr[i][0];
            newArr[i][1] = arr[i][1];
        }

        // now sorting the top k pair a/c to index to maintain the order
        // in ascending order
        Arrays.sort(newArr, (a, b) -> Integer.compare(a[0], b[0]));


        int[] result = new int[k];

        for(int i = 0; i<k; i++){
            result[i] = newArr[i][1];
        }

        return result;
    }
}