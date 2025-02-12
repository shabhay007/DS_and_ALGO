// LeetCode - 2342



// Brute Force
// T.C. - O(n^2)
// S.C. - O(1)
class Solution {
    public long sum(int num){ // O(log(k)) ~ O(1)
        long sum = 0;

        while(num > 0){
            int lastDigit = num % 10;
            sum += lastDigit;

            num = num/10;
        }

        return sum;
    }

    public int maximumSum(int[] nums) {
        int n = nums.length;
        long maxValue = -1;

        for(int i = 0; i<n; i++){
            for(int j = 0; j<n; j++){
                if(i != j && sum(nums[i]) == sum(nums[j])){
                    maxValue = Math.max(maxValue, nums[i] + nums[j]);
                }
            }
        }

        return (int) maxValue;
    }
}






// Better - Using Map
// T.C. - O(n)
// S.C. - O(n)
class Solution {
    public int getSum(int num){ // O(log(k)) ~ O(1)
        int sum = 0;

        while(num > 0){
            int lastDigit = num % 10;
            sum += lastDigit;

            num = num/10;
        }

        return sum;
    }

    public int maximumSum(int[] nums) {
        int n = nums.length;

        // store the sum of all the elements in map; sum -> nums[i]
        HashMap<Integer, Long> map = new HashMap<>();
        long maxValue = -1;

        for(int i = 0; i<n; i++){ // O(n)
            int sum = getSum(nums[i]);
            
            if(map.containsKey(sum)){
                long currNum = nums[i];
                long prevNum = map.get(sum);

                maxValue = Math.max(maxValue, currNum + prevNum);

                // update the map with max num corresponding to the sum
                map.put(sum, Math.max(currNum, prevNum));
            }
            else{
                map.put(sum, (long) nums[i]);
            }
        }

        return (int) maxValue;
    }
}






// Optimal - Using Hash array
// T.C. - O(n)
// S.C. - O(82) ~ O(1)
class Solution {
    public int getSum(int num){ // O(log(k)) ~ O(1)
        int sum = 0;

        while(num > 0){
            int lastDigit = num % 10;
            sum += lastDigit;

            num = num/10;
        }

        return sum;
    }

    public int maximumSum(int[] nums) {
        int n = nums.length;

        // since nums[i] = 10^9
        // max sum possible = 81
        // take array of size 82, where index will represent sum
        // value will represent the element
        long[] hashMap = new long[82];
        long maxValue = -1;

        for(int i = 0; i<n; i++){ // O(n)
            int sum = getSum(nums[i]);
            
            if(hashMap[sum] > 0){
                long currNum = nums[i];
                long prevNum = hashMap[sum];

                maxValue = Math.max(maxValue, currNum + prevNum);

                // update the map with max num corresponding to the sum
                hashMap[sum] = Math.max(currNum, prevNum);
            }
            else{
                hashMap[sum] = nums[i];
            }
        }

        return (int) maxValue;
    }
}





// Approach 4 - Using Pair & sorting
// T.C. - O(n + nlog(n) + n)
// S.C. - O(n)
class Solution {
    public int getSum(int num){ // O(log(k)) ~ O(1)
        int sum = 0;

        while(num > 0){
            int lastDigit = num % 10;
            sum += lastDigit;

            num = num/10;
        }

        return sum;
    }

    public int maximumSum(int[] nums) {
        int n = nums.length;

        List<int[]> list = new ArrayList<>();

        for(int i = 0; i<n; i++){
            list.add(new int[]{getSum(nums[i]), nums[i]});
        }

        // sort the list
        Collections.sort(list, new Comparator<int[]>(){
            public int compare(int[] a, int[] b){
                if(a[0] != b[0]){
                    return Long.compare(a[0], b[0]);
                }
                else{
                    return Long.compare(b[1], a[1]);
                }
            }
        });

        long maxValue = -1;

        for(int i = 1; i<list.size(); i++){
            int prevSum = list.get(i-1)[0];
            int prevNum = list.get(i-1)[1];

            int currSum = list.get(i)[0];
            int currNum = list.get(i)[1];

            if(currSum == prevSum){
                maxValue = Math.max(maxValue, prevNum + currNum);
            }
        }

        return (int) maxValue;
    }
}