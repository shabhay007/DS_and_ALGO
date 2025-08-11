// LeetCode - 2438



// Approach - Bit Manipulation
// T.C. - O(32 * n)
// S.C. - O(1)
class Solution {
    public int[] productQueries(int n, int[][] queries) {
        int mod = (int) 1e9 + 7;
        List<Integer> powers = new ArrayList<>();

        // size of powers can only be 32 at max because if all 32 bit is set

        for(int i = 0; i<32; i++){
            if((n & (1 << i)) != 0){ // if ith bit is set
                powers.add(1 << i);
            }
        }

        int[] result = new int[queries.length];
        int j = 0;

        for(int[] query : queries){
            int start = query[0];
            int end = query[1];

            long product = 1;

            for(int i = start; i<= end; i++){
                product = (product * powers.get(i)) % mod;
            }

            result[j++] = (int) product;
        }

        return result;
    }
}