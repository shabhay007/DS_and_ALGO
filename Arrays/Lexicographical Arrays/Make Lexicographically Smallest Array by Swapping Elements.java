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