// LeetCode - 3068



// Approach 1 - Greedy (Optimal)
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public long maximumValueSum(int[] nums, int k, int[][] edges) {
        int n = nums.length;
        int count = 0;
        long min = Long.MAX_VALUE;
        long sum = 0;

        for(int num : nums){
            if((num ^ k) > num){
                count++;
                sum += num ^ k;
            }
            else{
                sum += num;
            }

            min = Math.min(min, Math.abs(num - (num ^ k)));
        }

        return (count % 2 == 0) ? sum : sum - min;
    }
}





// Approach 2 - Greedy
// T.C. - O(2n + nlog(n))
// S.C. - O(n)
class Solution {
    public long maximumValueSum(int[] nums, int k, int[][] edges) {
        int n = nums.length;
        Long change[] = new Long[n];
        long sum = 0;

        for(int i = 0; i<n; i++){
            sum += nums[i];
            change[i] = (nums[i] ^ (long) k) - (long) nums[i];
        }

        // sorting in desc with custom comparator
        Arrays.sort(change, new Comparator<Long>() {
            @Override
            public int compare(Long a, Long b) {
                return Long.compare(b, a); // descending order
            }
        });

        // selecting the change in pairs if net > 0
        for(int i = 0; i<n-1; i += 2){
            if(change[i] + change[i+1] > 0){
                sum += change[i] + change[i+1];
            }
        }

        return sum;
    }
}