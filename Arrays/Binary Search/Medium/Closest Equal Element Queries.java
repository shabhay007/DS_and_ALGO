// LeetCode - 3488



// Approach 1 - Brute Force (Gives TLE)
// T.C. - O(m * n)
// S.C. - O(1)
class Solution {
    public List<Integer> solveQueries(int[] nums, int[] queries) {
        int n = nums.length;
        int m = queries.length;
        List<Integer> result = new ArrayList<>();

        for(int q : queries){
            int targetIdx = q;
            int targetEl = nums[q];
            int min = Integer.MAX_VALUE;

            for(int i = 0; i<n; i++){
                if(i != targetIdx && targetEl == nums[i]){
                    int straightDist = Math.abs(i - targetIdx);
                    int circularDist = n - straightDist;

                    min = Math.min(min, Math.min(straightDist, circularDist));
                }
            }

            if(min == Integer.MAX_VALUE){
                result.add(-1);
            }
            else{
                result.add(min);
            }
                
        }

        return result;
    }
}






// Approach 2 - Map
// T.C. - O(m * k); k = avg. length of list, in worst case it can be n
// S.C. - O(n)
class Solution {
    public List<Integer> solveQueries(int[] nums, int[] queries) {
        int n = nums.length;
        int m = queries.length;

        Map<Integer, List<Integer>> map = new HashMap<>();
        for(int i = 0; i<n; i++){
            map.putIfAbsent(nums[i], new ArrayList<>());
            map.get(nums[i]).add(i);
        }

        List<Integer> result = new ArrayList<>();
        if(map.size() == 1){ // bypassing the case which can couse TLE
            int freq = map.values().iterator().next().size();

            if(freq > 1){
                for(int i = 0; i < m; i++){
                    result.add(1);
                }
                return result;
            }
        }

        for(int q : queries){
            int targetIdx = q;
            int targetEl = nums[q];

            // in case of only one element
            if(map.get(targetEl).size() <= 1){
                result.add(-1);
                continue;
            }

            int min = Integer.MAX_VALUE;

            for(int j : map.get(targetEl)){
                if(j != targetIdx){
                    int straightDist = Math.abs(j - targetIdx);
                    int circularDist = n - straightDist;

                    min = Math.min(min, Math.min(straightDist, circularDist));
                }
            }

            result.add(min);
                
        }

        return result;
    }
}






// Approach 3 (Most Optimal) - Map + Binary Search
// T.C. - O(n + mlog(k)); k = avg. length of list, in worst case it can be n
// S.C. - O(n)
class Solution {
    public int lowerBound(List<Integer> list, int targetIdx){
        int l = 0;
        int r = list.size();
        int result = -1;

        while(l <= r){
            int mid = l + (r - l)/2;

            if(list.get(mid) >= targetIdx){
                result = mid;
                r = mid - 1;
            }
            else{
                l = mid + 1;
            }
        }

        return result;
    }

    public List<Integer> solveQueries(int[] nums, int[] queries) {
        int n = nums.length;
        int m = queries.length;

        Map<Integer, List<Integer>> map = new HashMap<>();
        for(int i = 0; i<n; i++){
            map.putIfAbsent(nums[i], new ArrayList<>());
            map.get(nums[i]).add(i);
        }

        List<Integer> result = new ArrayList<>();
        for(int q : queries){
            int targetEl = nums[q];
            List<Integer> list = map.get(targetEl);
            int size = list.size();

            // in case of only one element
            if(size <= 1){
                result.add(-1);
                continue;
            }

            // finding target index from list in map
            int pos = lowerBound(list, q);
            int res = Integer.MAX_VALUE;

            // idx adjacent to it will give the min dist
            // right adjacent
            int rightIdx = list.get((pos + 1) % size);
            int straightDist = Math.abs(q - rightIdx);
            int circularDist = n - straightDist;
            res = Math.min(res, Math.min(straightDist, circularDist));
            
            // left adjacent
            int leftIdx = list.get((pos - 1 + size) % size);
            int leftStraightDist = Math.abs(q - leftIdx);
            int leftCircularDist = n - leftStraightDist;
            res = Math.min(res, Math.min(leftStraightDist, leftCircularDist));

            result.add(res);
        }

        return result;
    }
}