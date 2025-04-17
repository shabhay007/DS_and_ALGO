// LeetCode Easy - 2176



// Approach 1 - Brute Force
// T.C. - O(n^2)
// S.C. - O(1)
class Solution {
    public int countPairs(int[] nums, int k) {
        int n = nums.length;
        int pairs = 0;

        for(int i = 0; i<n; i++){
            for(int j = i+1; j<n; j++){
                if(nums[i] == nums[j] && (i * j) % k == 0){
                    pairs++;
                }
            }
        }

        return pairs;
    }
}





// Approach 2 (Optimal) - Using Map and Set
// T.C. - O(n + sqrt(k) + n.(log(k) + sqrt(k)))
// S.C. - O(n)
class Solution {
    public int GCD(int x, int k){
        while(k != 0){
            int temp = k;
            k = x % k;
            x = temp;
        }

        return x;
    }

    public int countPairs(int[] nums, int k) {
        int n = nums.length;

        // step 1 : Map (element -> list<indices>)
        Map<Integer, List<Integer>> map = new HashMap<>();

        for(int i = 0; i<n; i++){ // O(n)
            map.putIfAbsent(nums[i], new ArrayList<>());
            map.get(nums[i]).add(i);
        }

        // step 2 : find the factors of k
        // ex : 36 -> 1, 2, 3, 4, 6, 9, 12, 18, 36
        Set<Integer> set = new HashSet<>(); // set of divisors

        for(int i = 1; i*i <= k; i++){ // sqrt(n)
            if(k % i == 0){
                set.add(i);
                set.add(k/i);
            }
        }

        // step 3 : processing every index
        int result = 0;

        // here we are processing every index and for each index
        // we are finding GCD (O(log(k))) and iterating every divisor of k (O(sqrt(k)))
        // Overall T.C. - O(n.(log(k) + sqrt(k)))
        for(List<Integer> indices : map.values()){
            Map<Integer, Integer> factors = new HashMap<>();

            for(int index : indices){
                // finding GCD
                int gcd = GCD(index, k);
                int j = k/gcd;

                result += factors.getOrDefault(j, 0);

                // storing the j in map
                for(int div : set){
                    if(index % div == 0){
                        factors.put(div, factors.getOrDefault(div, 0) + 1);
                    }
                }
            }
        }

        return result;
    }
}