// LeetCode Medium - 3761



// Approach 1 - Brute Force (Gives TLE)
// T.C. - O(n^2)
// S.C. - O(1)
class Solution {
    public int getReverse(int num){
        StringBuilder sb = new StringBuilder(String.valueOf(num));
        sb.reverse();
        
        return Integer.parseInt(sb.toString());
    }

    public int minMirrorPairDistance(int[] nums) {
        int n = nums.length;
        int min = Integer.MAX_VALUE;

        for(int i = 0; i<n; i++){
            int reverseNum = getReverse(nums[i]);

            for(int j = i+1; j<n; j++){
                if(reverseNum == nums[j]){
                    min = Math.min(min, j - i);
                }
            }
        }

        return (min == Integer.MAX_VALUE) ? -1 : min;
    }
}





// Approach 2 - Map
// T.C. - O(n)
// S.C. - O(n)
class Solution {
    public int getReverse(int num){
        StringBuilder sb = new StringBuilder(String.valueOf(num));
        sb.reverse();
        
        return Integer.parseInt(sb.toString());
    }

    public int minMirrorPairDistance(int[] nums) {
        int n = nums.length;

        Map<Integer, Integer> map = new HashMap<>();
        int min = Integer.MAX_VALUE;

        for(int i = n-1; i >= 0; i--){
            int reverseNum = getReverse(nums[i]);

            if(!map.isEmpty() && map.containsKey(reverseNum)){
                min = Math.min(min, map.get(reverseNum) - i);
            }
            
            // it will handle duplicate also, as idx gets updated to most recent
            // which have min distance
            map.put(nums[i], i);
        }

        return (min == Integer.MAX_VALUE) ? -1 : min;
    }
}






// Approach 3 - Map + Math
// T.C. - O(n)
// S.C. - O(n)
class Solution {
    public int getReverse(int num){
        int reverse = 0;

        while(num > 0){
            reverse = reverse * 10 + (num % 10);
            num /= 10;
        }

        return reverse;
    }

    public int minMirrorPairDistance(int[] nums) {
        int n = nums.length;

        Map<Integer, Integer> map = new HashMap<>();
        int min = Integer.MAX_VALUE;

        for(int i = n-1; i >= 0; i--){
            int reverseNum = getReverse(nums[i]);

            if(!map.isEmpty() && map.containsKey(reverseNum)){
                min = Math.min(min, map.get(reverseNum) - i);
            }
            
            // it will handle duplicate also, as idx gets updated to most recent
            // which have min distance
            map.put(nums[i], i);
        }

        return (min == Integer.MAX_VALUE) ? -1 : min;
    }
}