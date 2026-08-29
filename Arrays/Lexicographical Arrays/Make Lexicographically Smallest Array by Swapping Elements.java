// LeetCode Medium - 2948


// Brute Force
// T.C. - O(n^2 + some extra time)
// S.C. - O(1)
class Solution {
    public void swap(int[] nums, int element, int minElementIdx){
        int temp = nums[element];
        nums[element] = nums[minElementIdx];
        nums[minElementIdx] = temp;
    }

    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;

        for(int i = 0; i < n; i++){
            // bring the smallest possible element at every index
            while(true){
                int minElementIdx = -1;

                for(int j = i+1; j < n; j++){
                    if(nums[j] < nums[i] && Math.abs(nums[i] - nums[j]) <= limit){
                        minElementIdx = j;
                    }
                }

                if(minElementIdx != -1){
                    swap(nums, i, minElementIdx);
                }
                else{
                    break; // break from the while loop
                }
            }
        }

        return nums;
    }
}





// Optimal
// T.C. - O(2n + nlog(n))
// S.C. - O(3n)
class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;

        int[] copiedNums = nums.clone();
        Arrays.sort(copiedNums);

        // map the numbers according to groups
        HashMap<Integer, Integer> numToGroup = new HashMap<>();
        int groupNum = 0;
        numToGroup.put(copiedNums[0], groupNum);

        // map the numbers i.e. which which element is present in which groups list
        HashMap<Integer, LinkedList<Integer>> groupToList = new HashMap<>();
        groupToList.putIfAbsent(groupNum, new LinkedList<>());
        groupToList.get(groupNum).add(copiedNums[0]);

        // mapping the numbers groupNum and groupList
        for(int i = 1; i < n; i++){
            if(Math.abs(copiedNums[i] - copiedNums[i-1]) > limit){
                groupNum++;
            }

            numToGroup.put(copiedNums[i], groupNum);
            groupToList.putIfAbsent(groupNum, new LinkedList<>());
            groupToList.get(groupNum).add(copiedNums[i]);
        }

        // Build the answer - by merging the groups
        for(int i = 0; i < n; i++){
            int num = nums[i];
            int group = numToGroup.get(num);

            // find the smallest num in this group
            nums[i] = groupToList.get(group).pollFirst();
        }

        return nums;
    }
}







// Approach 3 - Slight change in Approach 2
// T.C. - O(nlog(n))
// S.C. - O(n)
class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;
        int[] arr = nums.clone();
        Arrays.sort(arr);

        int group = 0;
        Map<Integer, LinkedList<Integer>> groupToList = new HashMap<>();
        Map<Integer, Integer> numToGroup = new HashMap<>();

        int i = 0;
        while(i < n){
            // storing all the numbers having diff <= limit in same group
            groupToList.put(group, new LinkedList<>());
            groupToList.get(group).add(arr[i]);

            // storing which group this number belongs to
            numToGroup.put(arr[i], group);

            while(i+1 < n && arr[i+1] - arr[i] <= limit){
                groupToList.get(group).add(arr[i+1]);

                // storing which numbers falls in which group
                numToGroup.put(arr[i+1], group);
                i++;
            }

            group++;
            i++;
        }

        // processing for answers
        int[] result = new int[n];

        for(i = 0; i<n; i++){
            // finding the element present in which group
            int elementGroup = numToGroup.get(nums[i]);

            // smallest available number in this group
            result[i] = groupToList.get(elementGroup).pollFirst();
        }

        return result;
    }
}