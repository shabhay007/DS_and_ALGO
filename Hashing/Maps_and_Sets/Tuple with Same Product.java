// LeetCode Medium - 1726


// Brute Force Approach
// T.C. - O(n^4)
// S.C. - O(1)
class Solution {
    public int tupleSameProduct(int[] nums) {
        int n = nums.length;
        int count = 0;

        // trying out all combinations
        for(int a = 0; a<n; a++){
            for(int b = 0; b<n; b++){
                for(int c = 0; c<n; c++){
                    for(int d = 0; d<n; d++){
                        if(a != b && a != c && a != d && b !=c && b != d && c != d){
                            if(nums[a] * nums[b] == nums[c]*nums[d]){
                                count++;
                            }
                        }
                    }
                }
            }
        }

        return count;
    }
}







// Better Approach - Using Set
// T.C. - O(n^3)
// S.C. - O(n)
class Solution {
    public int tupleSameProduct(int[] nums) {
        int n = nums.length;
        int count = 0;

        Arrays.sort(nums); // sort the array

        for(int i = 0; i<n; i++){
            for(int j = n-1; j>i; j--){
                HashSet<Integer> set = new HashSet<>();
                int product = nums[i]*nums[j];

                for(int k = i+1; k<j; k++){
                    if(product % nums[k] == 0){
                        int valueOfD = product/nums[k];

                        if(set.contains(valueOfD)){
                            count++;
                        }

                        set.add(nums[k]);
                    }
                }
            }
        }

        // there will be 8 permutations for every unique tuple
        return count*8;
    }
}






// Optimal Approach - Using Map
// T.C. - O(n^2)
// S.C. - O(n)
class Solution {
    public int tupleSameProduct(int[] nums) {
        int n = nums.length;
        int count = 0;

        HashMap<Integer, Integer> map = new HashMap<>();

        for(int i = 0; i<n; i++){
            for(int j = i+1; j<n; j++){
                int product = nums[i]*nums[j];
                map.put(product, map.getOrDefault(product, 0) + 1);
            }
        }

        // interating through the map to find total tuples
        for(int val : map.values()){
            if(val >= 2){
                count += val * (val - 1) / 2; // nC2
            }
        }

        // there will be 8 permutations for every unique tuple
        return count*8;
    }
}