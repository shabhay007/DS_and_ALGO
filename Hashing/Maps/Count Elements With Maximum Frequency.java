// LeetCode - 3005



// Approach 1 - Map
// T.C. - O(3n)
// S.C. - O(n)
class Solution {
    public int maxFrequencyElements(int[] nums) {
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();

        for(int num : nums){
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        int maxFreq = 0;

        for(int freq : map.values()){
            maxFreq = Math.max(maxFreq, freq);
        }

        int freqCount = 0;

        for(int freq : map.values()){
            if(freq == maxFreq){
                freqCount += freq;
            }
        }

        return freqCount;
    }
}





// Approach 2 - Map
// T.C. - O(2n)
// S.C. - O(n)
class Solution {
    public int maxFrequencyElements(int[] nums) {
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        int maxFreq = 0;

        for(int num : nums){
            map.put(num, map.getOrDefault(num, 0) + 1);
            maxFreq = Math.max(maxFreq, map.get(num));
        }

        int freqCount = 0;

        for(int freq : map.values()){
            if(freq == maxFreq){
                freqCount += freq;
            }
        }

        return freqCount;
    }
}





// Approach 3 - Hash Array
// T.C. - O(n + 100)
// S.C. - O(100) ~ O(1)
class Solution {
    public int maxFrequencyElements(int[] nums) {
        int[] hash = new int[101];
        int maxFreq = 0;

        for(int num : nums){
            hash[num]++;
            maxFreq = Math.max(maxFreq, hash[num]);
        }

        int sumOfFreq = 0;

        for(int freq : hash){
            if(freq == maxFreq){
                sumOfFreq += freq;
            }
        }

        return sumOfFreq;
    }
}