// LeetCode Easy - 2570


// Approach 1 - Map + Sorting
// T.C. - O(n + (m+n) + (m+n)log(m+n) + (m+n))
// S.C. - O(m + 2n)
class Solution {
    public int[][] mergeArrays(int[][] nums1, int[][] nums2) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for(int[] num : nums2){ // O(n)
            int key = num[0];
            int value = num[1];

            map.put(key, value);
        }

        List<int[]> result = new ArrayList<>();

        for(int[] num : nums1){
            int key = num[0];
            int value = num[1];

            if(map.containsKey(key)){
                result.add(new int[]{key, value + map.get(key)});
                map.remove(key);
            }
            else{
                result.add(num);
            }
        }

        for(Map.Entry<Integer, Integer> entry : map.entrySet()){
            int key = entry.getKey();
            int value = entry.getValue();

            result.add(new int[]{key, value});
        }

        Collections.sort(result, (a, b) -> a[0] - b[0]); // O((m+n)log(m+n))

        return result.toArray(new int[result.size()][]); // O(m+n)
    }
}




// Approach 2 (Optimal) - Merge Sort
// T.C. - O((m+n) + (m+n))
// S.C. - O(m + n)
class Solution {
    public int[][] mergeArrays(int[][] nums1, int[][] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        List<int[]> result = new ArrayList<>();

        int i = 0;
        int j = 0;

        while(i < m && j < n){
            int key1 = nums1[i][0];
            int key2 = nums2[j][0];
            int value1 = nums1[i][1];
            int value2 = nums2[j][1];

            if(key1 == key2){
                result.add(new int[]{key1, value1 + value2});
                i++;
                j++;
            }
            else if(key1 < key2){
                result.add(new int[]{key1, value1});
                i++;
            }
            else{
                result.add(new int[]{key2, value2});
                j++;
            }
        }

        while(i < m){
            int key1 = nums1[i][0];
            int value1 = nums1[i][1];

            result.add(new int[]{key1, value1});
            i++; // update
        }

        while(j < n){
            int key2 = nums2[j][0];
            int value2 = nums2[j][1];

            result.add(new int[]{key2, value2});
            j++; // update
        }

        return result.toArray(new int[result.size()][]);
    }
}





// Approach 3 - Using Ordered Map (TreeMap)
// T.C. - O(2(m+n))
// S.C. - O(m + n)
class Solution {
    public int[][] mergeArrays(int[][] nums1, int[][] nums2) {
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();

        for(int[] num : nums1){
            int key = num[0];
            int value = num[1];

            treeMap.put(key, value);
        }

        for(int[] num : nums2){
            int key = num[0];
            int value = num[1];

            if(treeMap.containsKey(key)){
                treeMap.put(key, value + treeMap.get(key));
            }
            else{
                treeMap.put(key, value);
            }
        }

        int[][] result = new int[treeMap.size()][2];
        int i = 0;

        for(Map.Entry<Integer, Integer> entry : treeMap.entrySet()){
            int key = entry.getKey();
            int value = entry.getValue();

            result[i] = new int[]{key, value};
            i++;
        }

        return result;
    }
}