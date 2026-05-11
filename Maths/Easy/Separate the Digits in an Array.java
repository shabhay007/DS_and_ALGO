// LeetCode - 2553



// Approach 1 - Digit Extraction + Collection Reverse Function
// T.C. - O(nlog(d) + n*d); d = avg num length
// S.C. - O(n * d)
class Solution {
    public void extractDigits(int num, List<Integer> list){ // O(log(d))
        while(num > 0){
            list.add(num % 10);
            num /= 10;
        }
    }

    public int[] separateDigits(int[] nums) {
        int n = nums.length;
        List<Integer> list = new ArrayList<>();

        for(int i = n-1; i >= 0; i--){
            extractDigits(nums[i], list);
        }

        Collections.reverse(list); // O(n * d)

        return list.stream().mapToInt(Integer::intValue).toArray();
    }
}





// Approach 2 - String Properties
// T.C. - O(n * d); d = avg num length
// S.C. - O(n * d)
class Solution {
    public int[] separateDigits(int[] nums) {
        int n = nums.length;
        List<Integer> list = new ArrayList<>();

        for(int num : nums){
            String str = String.valueOf(num);

            for(char ch : str.toCharArray()){
                list.add(ch - '0');
            }
        }

        return list.stream().mapToInt(Integer::intValue).toArray();
    }
}