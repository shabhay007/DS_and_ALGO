// LeetCode - 1356



// Approach 1 - Sorting + Lambda + Bit Manipulation
// T.C. - O(nlog(n))
// S.C. - O(n)
class Solution {
    public int[] sortByBits(int[] arr) {
        Integer[] boxed = Arrays.stream(arr).boxed().toArray(Integer[]::new);

        Arrays.sort(boxed, (a, b) -> {
            int bitCountA = Integer.bitCount(a);
            int bitCountB = Integer.bitCount(b);

            if (bitCountA != bitCountB) {
                return bitCountA - bitCountB;
            }

            return Integer.compare(a, b);
        });

        for(int i = 0; i<arr.length; i++){
            arr[i] = boxed[i];
        }

        return arr;
    }
}






// Approach 2 - Sorting + Lambda + Bit Manipulation
// T.C. - O(nlog(n) * log2(num))
// S.C. - O(n)
class Solution {
    public int getBitCount(int num){ // O(log2(num))
        int count = 0;

        while(num != 0){
            count += num & 1;
            num >>= 1;
        }

        return count;
    }

    public int[] sortByBits(int[] arr) {
        Integer[] boxed = Arrays.stream(arr).boxed().toArray(Integer[]::new);

        Arrays.sort(boxed, (a, b) -> {
            int bitCountA = getBitCount(a);
            int bitCountB = getBitCount(b);

            if (bitCountA != bitCountB) {
                return bitCountA - bitCountB;
            }

            return Integer.compare(a, b);
        });

        for(int i = 0; i<arr.length; i++){
            arr[i] = boxed[i];
        }

        return arr;
    }
}