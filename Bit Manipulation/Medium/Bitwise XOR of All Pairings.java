// LeetCode Medium - 2425


// Brute Force - Using List - Gives MLE
// T.C. - O(2(m*n))
// S.C. - O(m * n)
class Solution {
    public int xorAllNums(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        List<Integer> bitWiseXorOfPairs = new ArrayList<>();

        for(int num1 : nums1){ // O(m * n)
            for(int num2 : nums2){
                bitWiseXorOfPairs.add(num1 ^ num2);
            }
        }

        int finalBitwiseXor = 0;
        
        for(int num : bitWiseXorOfPairs){ // O(m * n)
            finalBitwiseXor ^= num;
        }

        return finalBitwiseXor;
    }
}




// Better - Using Map
// T.C. - O(2(m + n))
// S.C. - O(m + n)
class Solution {
    public int xorAllNums(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;

        HashMap<Integer, Integer> freqMap = new HashMap<>();

        // storing the frequency of nums1
        for(int num : nums1){
            freqMap.put(num, freqMap.getOrDefault(num, 0) + n);
        }

        // storing the frequency of nums2
        for(int num : nums2){
            freqMap.put(num, freqMap.getOrDefault(num, 0) + m);
        }

        int finalXor = 0;

        // finding final xor from map
        for(Map.Entry<Integer, Integer> entry : freqMap.entrySet()){
            int key = entry.getKey();
            int value = entry.getValue();

            if(value % 2 != 0){
                finalXor ^= key;
            }
        }

        return finalXor;
    }
}




// Better - XOR properties
// T.C. - O(m + n)
// S.C. - O(1)
class Solution {
    public int xorAllNums(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;

        if(m % 2 == 0 && n % 2 == 0) return 0;

        int finalXor = 0;

        if(m % 2 != 0){
            for(int num : nums2){
                finalXor ^= num;
            }
        }

        if(n % 2 != 0){
            for(int num : nums1){
                finalXor ^= num;
            }
        }

        return finalXor;
    }
}