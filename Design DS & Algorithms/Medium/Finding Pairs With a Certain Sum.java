// LeetCode - 1865



// Brute Force (Gives TLE)
// T.C. - O(n1 * n2)
// S.C. - O(n1 + n2)
class FindSumPairs {
    int[] nums1;
    int[] nums2;

    public FindSumPairs(int[] nums1, int[] nums2) {
        this.nums1 = nums1;
        this.nums2 = nums2;
    }
    
    public void add(int index, int val) {
        nums2[index] += val;
    }
    
    public int count(int tot) {
        int total = 0;

        for(int i = 0; i<nums1.length; i++){
            for(int j = 0; j<nums2.length; j++){
                if(nums1[i] + nums2[j] == tot){
                    total++;
                }
            }
        }

        return total;
    }
}





// Approach 2 - Using Map
// T.C. - O(n1)
// S.C. - O(n1 + n2)
class FindSumPairs {
    int[] nums1;
    int[] nums2;
    Map<Integer, Integer> map;

    public FindSumPairs(int[] nums1, int[] nums2) {
        this.nums1 = nums1;
        this.nums2 = nums2;
        this.map = new HashMap<>();

        // processing the elements in nums2;
        for(int num : nums2){
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
    }
    
    public void add(int index, int val) {
        // first decrementing the old element frequency by 1
        map.put(nums2[index], map.get(nums2[index]) - 1);
        
        // removing the key if element's frequency is 0
        if(map.get(nums2[index]) <= 0){
            map.remove(nums2[index]);
        }

        // then modifying the element at the given index
        nums2[index] += val;

        // then incrementing the new element frequency by 1
        map.put(nums2[index], map.getOrDefault(nums2[index], 0) + 1);
    }
    
    public int count(int tot) {
        int totalPairCount = 0;

        for(int i = 0; i<nums1.length; i++){ // O(n1)
            int key = tot - nums1[i];

            if(map.containsKey(key)){
                totalPairCount += map.get(key);
            }
        }

        return totalPairCount;
    }
}