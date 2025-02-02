// LeetCode Easy - 1752


// Approach 1 - Brute Force
// T.C. - O(n^2)
// S.C. - O(n)
class Solution {
    public boolean isSorted(List<Integer> list){
        for(int i = 0; i < list.size()-1; i++){
            if(list.get(i) > list.get(i+1)){
                return false;
            }
        }

        return true;
    }

    public boolean check(int[] nums) {
        int n = nums.length;

        for(int rotationIdx = 0; rotationIdx <= n; rotationIdx++){
            List<Integer> list = new ArrayList<>();

            // adding element from where ratation starts till end
            for(int i = rotationIdx; i < n; i++){
                list.add(nums[i]);
            }

            // if rotation starts from other than 0th index
            // then then the elements from start till rotated idx will be left
            // so, adding them here
            for(int i = 0; i < rotationIdx; i++){
                list.add(nums[i]);
            }

            // now checking if it is sorted
            if(isSorted(list)){
                return true;
            }
        }

        return false;
    }
}






// Optimal
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public boolean check(int[] nums) {
        int n = nums.length;

        int noOfPeak = 0;

        for(int i = 0; i < n; i++){
            if(nums[i] > nums[(i+1) % n]){
                noOfPeak++;
            }
        }


        // if the array has only one peak then it used to be sorted
        // else not
        return noOfPeak <= 1;
    }
}