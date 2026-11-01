// LeetCode - 453



// Approach 1 - Maths
// T.C. - O(n*k); k = no of times while loop runs
// S.C. - O(1)
class Solution {
    public int minMoves(int[] nums) {
        int n = nums.length;

        long moves = 0;
        int maxIdx = -1;
        int max = Integer.MIN_VALUE;

        // finding maxEl and it's idx
        for(int i = 0; i<n; i++){
            if(nums[i] > max){
                max = nums[i];
                maxIdx = i;
            }
        }
        
        int min = Arrays.stream(nums).min().getAsInt();

        while(min != max){
            moves += max - min;

            for(int i = 0; i<n; i++){
                if(i != maxIdx){
                    nums[i] += max - min;
                }
            }

            // updating maxEl and it's idx
            for(int i = 0; i<n; i++){
                if(nums[i] > max){
                    max = nums[i];
                    maxIdx = i;
                }
            }

            min = Arrays.stream(nums).min().getAsInt();
        }

        return (int) moves;
    }
}






// Approach 2 - Maths
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public int minMoves(int[] nums) {
        int n = nums.length;
        long moves = 0;
        int min = Arrays.stream(nums).min().getAsInt();

        for(int i = 0; i<n; i++){
            moves += nums[i] - min;
        }

        return (int) moves;
    }
}