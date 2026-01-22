// LeetCode - 3507



// Approach 1 - Brute Force
// T.C. - O(n^2)
// S.C. - O(n)
class Solution {
    public boolean isSorted(List<Integer> list){
        for(int i = 1; i<list.size(); i++){
            if(list.get(i) < list.get(i-1)){
                return false;
            }
        }

        return true;
    }

    public int minimumPairRemoval(int[] nums) {
        List<Integer> list = Arrays.stream(nums).boxed().collect(Collectors.toList());
        // System.out.println("Org List : " + list.toString());
        if(isSorted(list)){
            return 0;
        }

        List<Integer> temp = new ArrayList<>();
        int ops = 0;
        
        while(!isSorted(list)){
            int minSum = Integer.MAX_VALUE;
            int pos = -1;

            for(int i = 0; i<list.size()-1; i++){
                int sum = list.get(i) + list.get(i+1);

                if(sum < minSum){
                    minSum = sum;
                    pos = i;
                }
            }

            if(pos != -1){
                for(int i = 0; i<list.size(); i++){
                    if(i == pos){
                        temp.add(minSum);
                        i++;
                    }
                    else{
                        temp.add(list.get(i));
                    }
                }
            }

            ops++;
            list.clear();
            list.addAll(temp);
            // System.out.println(list.toString());
            temp.clear();
        }

        return ops;
    }
}