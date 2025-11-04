// LeetCode - 3318



// Approach 1 - Sliding Window + Heap
// T.C. - O(n * klog(k))
// S.C. - O(n + k)
class Solution {
    public int xSum(Map<Integer, Integer> map, int x){
        PriorityQueue<int[]> heap = new PriorityQueue<>(new Comparator<int[]>(){
            public int compare(int[] a, int[] b){
                if(a[1] != b[1]){
                    return b[1] - a[1];
                }

                return b[0] - a[0]; // in case of equal freq
            }
        });


        for(Map.Entry<Integer, Integer> entry : map.entrySet()){
            int element = entry.getKey();
            int freq = entry.getValue();

            heap.offer(new int[] {element, freq});
        }

        int count = x;
        int temp = 0;

        while(count > 0 && !heap.isEmpty()){
            int pair[] = heap.poll();

            temp += pair[0] * pair[1];
            count--;
        }

        return temp;
    }


    public int[] findXSum(int[] nums, int k, int x) {
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();

        int i = 0;
        int j = 0;
        int m = 0;
        int[] result = new int[n-k+1];

        while(j < n){
            map.put(nums[j], map.getOrDefault(nums[j], 0) + 1);

            if(j-i+1 > k){
                map.put(nums[i], map.get(nums[i]) - 1);

                if(map.get(nums[i]) == 0){
                    map.remove(nums[i]);
                }

                i++;
            }

            if(j-i+1 == k){
                result[m++] = xSum(map, x);
            }

            j++;
        }

        return result;
    }
}