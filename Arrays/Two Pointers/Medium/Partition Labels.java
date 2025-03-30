// LeetCode Mecium - 763


// Approach 1 - Using Map + Sorting + Merge Intervals
// T.C. - O(4n + nlog(n))
// S.C. - O(3n)
class Solution {
    public List<Integer> partitionLabels(String s) {
        int n = s.length();

        HashMap<Character, int[]> map = new HashMap<>();

        for(int i = 0; i<n; i++){ // O(n)
            char ch = s.charAt(i);

            // first occurence
            if(!map.containsKey(ch)){
                map.put(ch, new int[]{i, i});
            }
            else{
                map.get(ch)[1] = i;
            }
        }

        // extracting the intervals from the map
        int[][] intervals = new int[map.size()][2];
        int i = 0;

        for(int[] interval : map.values()){ // O(n)
            intervals[i] = interval;
            i++;
        }

        // now sorting the intervals a/c start index
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]); // O(nlog(n))

        // now merging the overlapped intervals
        List<int[]> list = new ArrayList<>();

        for(int[] interval : intervals){ // O(n)
            if(list.isEmpty() || interval[0] > list.get(list.size() - 1)[1]){
                list.add(interval);
            }
            else{
                list.get(list.size() - 1)[1] = Math.max(list.get(list.size() - 1)[1], interval[1]);
            }
        }

        // now finding the max partition, we can do
        List<Integer> result = new ArrayList<>();

        for(int[] interval : list){ // O(n)
            int size = interval[1] - interval[0] + 1;
            result.add(size);
        }

        return result;
    }
}




// Approach 2 - Two Pointer
// T.C. - O(4n)
// S.C. - O(1)
class Solution {
    public List<Integer> partitionLabels(String s) {
        int n = s.length();
        int[] lastOccurence = new int[26];
        Arrays.fill(lastOccurence, -1); // O(n)

        for(int i = 0; i<n; i++){ // O(n)
            char ch = s.charAt(i);

            lastOccurence[ch - 'a'] = i;
        }

        int i = 0;
        List<Integer> result = new ArrayList<>();

        while(i < n){ // O(2n)
            char ch = s.charAt(i);
            int end = lastOccurence[ch - 'a'];

            int j = i;

            while(j < end){
                char newCh = s.charAt(j);
                end = Math.max(end, lastOccurence[newCh - 'a']);
                j++; // update
            }

            result.add(j-i+1);
            i = j + 1; // update
        }

        return result;
    }
}




// Approach 3 - Two Pointer
// T.C. - O(3n)
// S.C. - O(1)
class Solution {
    public List<Integer> partitionLabels(String s) {
        int n = s.length();
        int[] lastOccurence = new int[26];
        Arrays.fill(lastOccurence, -1); // O(n)

        for(int i = 0; i<n; i++){ // O(n)
            char ch = s.charAt(i);

            lastOccurence[ch - 'a'] = i;
        }

        int i = 0;
        int start = 0;
        int end = 0;
        List<Integer> result = new ArrayList<>();

        while(i < n){ // O(n)
            char ch = s.charAt(i);
            end = Math.max(end, lastOccurence[ch - 'a']);
            
            if(i == end){
                result.add(end - start + 1);
                start = end + 1;
            }

            i++;
        }

        return result;
    }
}