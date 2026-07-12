// LeetCode 1331



// Approach 1 - Sorting
// T.C. - O(nlog(n))
// S.C. - O(n)
class Solution {
    public int[] arrayRankTransform(int[] arr) {
        int n = arr.length;

        if(n == 0){
            return new int[0];
        }

        List<int[]> list = new ArrayList<>();
        for(int i = 0; i<n; i++){
            list.add(new int[]{arr[i], i});
        }

        Collections.sort(list, (a, b) -> a[0] - b[0]);

        int rank = 1;
        int[] result = new int[n];
        int prev = list.get(0)[0];
        result[list.get(0)[1]] = 1;

        for(int i = 1; i<n; i++){
            int[] curr = list.get(i);
            int ele = curr[0];
            int idx = curr[1];

            if(ele == prev){
                result[idx] = rank;
            }
            else{
                rank++;
                result[idx] = rank;
                prev = ele;
            }
        }

        return result;
    }
}





// Approach 2 - Sorting + Map
// T.C. - O(nlog(n))
// S.C. - O(n)
class Solution {
    public int[] arrayRankTransform(int[] arr) {
        int n = arr.length;

        if(n == 0){
            return new int[0];
        }

        int[] sortedArr = new int[n];
        System.arraycopy(arr, 0, sortedArr, 0, n);
        Arrays.sort(sortedArr);

        Map<Integer, Integer> map = new HashMap<>();
        map.put(sortedArr[0], 1);

        for(int i = 1; i<n; i++){
            if(sortedArr[i] != sortedArr[i-1]){
                map.put(sortedArr[i], map.get(sortedArr[i-1]) + 1);
            }
        }

        // processing result
        int[] result = new int[n];
        for(int i = 0; i<n; i++){
            result[i] = map.get(arr[i]);
        }
        
        return result;
    }
}






// Approach 3 - Sorting + Map + Stream API
// T.C. - O(nlog(n))
// S.C. - O(n)
class Solution {
    public int[] arrayRankTransform(int[] arr) {
        int n = arr.length;

        if(n == 0){
            return new int[0];
        }

        int[] sortedArr = Arrays.stream(arr).sorted().distinct().toArray();
        Arrays.sort(sortedArr);

        Map<Integer, Integer> map = new HashMap<>();
        int rank = 1;

        for(int num : sortedArr){
            if(!map.containsKey(num)){
                map.put(num, rank++);
            }
        }

        // processing result
        int[] result = new int[n];
        for(int i = 0; i<n; i++){
            result[i] = map.get(arr[i]);
        }
        
        return result;
    }
}