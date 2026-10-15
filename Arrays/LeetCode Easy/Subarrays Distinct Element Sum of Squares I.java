// LeetCode - 2913



// Approach 1 - Brute Force
// T.C. - O(n^2)
// S.C. - O(1)
class Solution {
    public int getSquare(int start, int j, List<Integer> nums){
        int sqSum = 0;
        int[] number = new int[101];

        for(int i = start; i <= j; i++){
            number[nums.get(i)]++;
        }

        int count = 0;
        for(int i = 0; i<101; i++){
            if(number[i] > 0){
                count++;
            }
        }

        return count * count;
    }

    public int sumCounts(List<Integer> nums) {
        int n = nums.size();
        long result = 0;

        for(int i = 0; i<n; i++){
            for(int j = i; j<n; j++){
                int square = getSquare(i, j, nums);

                result += square;
            }
        }

        return (int) result;
    }
}