// LeetCode - 3551



// Approach 1 - Sorting
// T.C. - O(nlog(n))
// S.C. - O(n)
class Solution {
    public int digitSum(int num){
        int sum = 0;
        while(num > 0){
            sum += num % 10;
            num /= 10;
        }

        return sum;
    }

    public int minSwaps(int[] nums) {
        int n = nums.length;
        Map<Integer, Integer> map = IntStream.range(0, n).boxed()
                .collect(Collectors.toMap(i -> nums[i], i -> i));

        Integer[] arr = Arrays.stream(nums).boxed().toArray(Integer[]::new);

        Arrays.sort(arr, (a, b) -> {
            int digitSumA = digitSum(a);
            int digitSumB = digitSum(b);

            if(digitSumA != digitSumB){
                return Integer.compare(digitSumA, digitSumB);
            }
            
            return Integer.compare(a, b);
        });

        // System.out.println(Arrays.toString(arr));

        int swaps = 0;
        for(int i = 0; i<n; i++){
            int expected = arr[i];
            int curr = nums[i];

            if(expected == curr){
                continue;
            }

            int idx = map.get(expected);

            // swaping elements
            nums[i] = expected;
            nums[idx] = curr;

            // updating current indices
            map.put(expected, i);
            map.put(curr, idx);

            swaps++;
        }

        return swaps;
    }
}