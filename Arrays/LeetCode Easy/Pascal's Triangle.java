// LeetCode - 118



// Approach 1 - Simulation
// T.C. - O(n^2)
// S.C. - O(n^2)
class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> list = new ArrayList<>();

        for(int i = 0; i<numRows; i++){
            List<Integer> curr = new ArrayList<>();

            for(int j = 0; j<=i; j++){

                if(j == 0 || j == i){
                    curr.add(1);
                }
                else{
                    int num = list.get(i-1).get(j) + list.get(i-1).get(j-1);
                    curr.add(num);
                }
            }

            list.add(curr);
        }

        return list;
    }
}