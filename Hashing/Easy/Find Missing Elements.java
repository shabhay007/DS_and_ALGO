// LeetCode - 3731



// Approach 1 - Simulation
// T.C. - O(n + m) where m is the range of numbers between min and max
// S.C. - O(n)
class Solution {
    public List<Integer> findMissingElements(int[] nums) {
        int n = nums.length;
        Set<Integer> set = new HashSet<>();

        for(int num : nums){
            set.add(num);
        }

        int min = Arrays.stream(nums).min().getAsInt();
        int max = Arrays.stream(nums).max().getAsInt();

        List<Integer> list = new ArrayList<>();
        for(int i = min; i<max; i++){
            if(!set.contains(i)){
                list.add(i);
            }
        }

        return list;
    }
}