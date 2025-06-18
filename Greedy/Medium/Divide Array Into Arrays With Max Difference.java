// LeetCode - 2966



// Approach 1 - Sorting & Greedy
// T.C. - O(nlog(n))
// S.C. - O(n)
class Solution {
    public int[][] divideArray(int[] nums, int k) {
        int n = nums.length;
        int size = n/3;

        Arrays.sort(nums); // sorting

        List<List<Integer>> result = new ArrayList<>();
        int start = 0;

        while(start < n){
            List<Integer> temp = new ArrayList<>();

            // comparing eveny element with the 1st element of that list
            // as if diff between any > k than next elements are not valid 
            // as diff will always greater because of sorting
            for(int j = start; j < (3 + start); j++){
                if(temp.isEmpty()){
                    temp.add(nums[j]);
                }
                else if(nums[j] - temp.get(0) <= k){
                    temp.add(nums[j]);
                }
                else{
                    return new int[0][];
                }
            }

            start += 3;
            result.add(new ArrayList<>(temp));
        }


        // converting list to 2D array
        int[][] ans = new int[result.size()][3];

        for(int i = 0; i<result.size(); i++){
            for(int j = 0; j<result.get(0).size(); j++){
                ans[i][j] = result.get(i).get(j);
            }
        }

        return ans;
    }
}





// Approach 2 - Sorting & Greedy
// T.C. - O(nlog(n))
// S.C. - O(1)
class Solution {
    public int[][] divideArray(int[] nums, int k) {
        int n = nums.length;
        int size = n/3;

        Arrays.sort(nums); // sorting

        int[][] result = new int[size][3];
        int i = 0;

        while(i < n){
            // 1st and last element of that group will give the maxDiff
            if(nums[i + 2] - nums[i] > k){
                return new int[0][];
            }

            result[i / 3] = new int[]{nums[i], nums[i+1], nums[i+2]};

            i += 3;
        }

        return result;
    }
}