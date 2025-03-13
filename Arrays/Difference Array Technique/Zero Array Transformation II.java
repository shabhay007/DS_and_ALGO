// LeetCode Medium - 3356



// Approach 1 - Brute Force (Gives TLE)
// T.C. - O(q * 2n)
// S.C. - O(1)
class Solution {
    public boolean isZeroArray(int[] nums){
        for(int num : nums){
            if(num != 0){
                return false;
            }
        }

        return true;
    }

    public int minZeroArray(int[] nums, int[][] queries) {
        int n = nums.length;
        int q = queries.length;
        int k = 0;

        if(isZeroArray(nums)){
            return 0;
        }

        for(int[] query : queries){ // O(q)
            int left = query[0];
            int right = query[1];
            int val = query[2];

            // updation
            boolean flag = false;

            for(int i = left; i <= right; i++){ // O(n)
                nums[i] = (nums[i] - val < 0) ? 0 : (nums[i] - val);
                flag = true;
            }
            
            // if flag is true, it means 1 query contributed for transformation
            if(flag) k += 1;

            if(isZeroArray(nums)){ // O(n)
                return k;
            }
        }

        return -1;
    }
}




// Approach 2 - Brute Force using Diff Array Technique (Gives TLE)
// T.C. - O(q * (q + n))
// S.C. - O(n)
class Solution {
    public boolean isZeroArray(int[] nums){
        for(int num : nums){
            if(num != 0){
                return false;
            }
        }

        return true;
    }

    public boolean transformArray(int[] nums, int[][] queries, int k){
        int m = nums.length;
        int[] diffArray = new int[m];

        // step 1 : processing
        for(int i = 0; i<=k; i++){
            int left = queries[i][0];
            int right = queries[i][1];
            int val = queries[i][2];

            diffArray[left] += val;
            
            if(right+1 < m){
                diffArray[right + 1] -= val;
            }
        }

        // step 2 : cummulative sum
        int sum = 0;
        for(int i = 0; i<m; i++){
            sum += diffArray[i];
            diffArray[i] = sum;

            // nums[i] can't be transformed to 0
            if(nums[i] - diffArray[i] > 0){
                return false;
            }
        }

        return true;
    }

    public int minZeroArray(int[] nums, int[][] queries) {
        int q = queries.length;

        // if already zero, no transformation required
        if(isZeroArray(nums)){
            return 0;
        }

        // case 2 : not zero array
        for(int i = 0; i<q; i++){ // O(q)
            if(transformArray(nums, queries, i)){ // O(q + n)
                return i+1;
            }
        }

        return -1;
    }
}






// Approach 3 - Diff Array Technique + Binary Search
// T.C. - O(n + log(q) * (q + n))
// S.C. - O(n)
class Solution {
    public boolean isZeroArray(int[] nums){
        for(int num : nums){
            if(num != 0){
                return false;
            }
        }

        return true;
    }

    public boolean transformArray(int[] nums, int[][] queries, int k){
        int m = nums.length;
        int[] diffArray = new int[m];

        // step 1 : processing
        for(int i = 0; i<=k; i++){
            int left = queries[i][0];
            int right = queries[i][1];
            int val = queries[i][2];

            diffArray[left] += val;
            
            if(right+1 < m){
                diffArray[right + 1] -= val;
            }
        }

        // step 2 : cummulative sum
        int sum = 0;
        for(int i = 0; i<m; i++){
            sum += diffArray[i];
            diffArray[i] = sum;

            // nums[i] can't be transformed to 0
            if(nums[i] - diffArray[i] > 0){
                return false;
            }
        }

        return true;
    }

    public int minZeroArray(int[] nums, int[][] queries) {
        int n = nums.length;
        int q = queries.length;

        // if already zero, no transformation required
        if(isZeroArray(nums)){ // O(n)
            return 0;
        }

        // case 2 : not zero array
        int l = 0;
        int r = q-1;
        int k = -1;

        while(l <= r){ // O(log(q))
            int mid = l + (r - l)/2;

            if(transformArray(nums, queries, mid)){ // O(q + n)
                k = mid + 1; // possible answer
                r = mid - 1; // exploring left
            }
            else{
                l = mid + 1;
            }
        }

        return k;
    }
}