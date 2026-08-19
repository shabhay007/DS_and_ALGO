// LeetCode - 1386




// Approach 1 - Set + Simulation
// T.C. - O(n)
// S.C. - O(n)
class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        Arrays.sort(reservedSeats, (a, b) -> a[0] - b[0]);
        Set<String> set = new HashSet<>();

        for(int[] curr : reservedSeats){
            int row = curr[0];
            int seat = curr[1];

            set.add(row + "#" + seat);
        }

        int groups = 0;
        for(int row = 1; row <= n; row++){
            boolean start = false;
            boolean end = false;
            boolean mid = false;

            if(!set.contains(row + "#" + 2) && !set.contains(row + "#" + 3)
                    && !set.contains(row + "#" + 4) && !set.contains(row + "#" + 5)){
                start = true;
            }

            if(!set.contains(row + "#" + 6) && !set.contains(row + "#" + 7)
                    && !set.contains(row + "#" + 8) && !set.contains(row + "#" + 9)){
                end = true;
            }

            if(!set.contains(row + "#" + 4) && !set.contains(row + "#" + 5)
                    && !set.contains(row + "#" + 6) && !set.contains(row + "#" + 7)){
                mid = true;
            }

            if(start && end){
                groups += 2;
            }
            else if(start || mid || end){
                groups++;
            }
        }

        return groups;
    }
}






// Approach 2 - Map + Set + Simulation
// T.C. - O(m)
// S.C. - O(m)
class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        Map<Integer, Set<Integer>> map = new HashMap<>();

        for(int[] r : reservedSeats){
            int row = r[0];
            int seat = r[1];

            map.putIfAbsent(row, new HashSet<>());
            map.get(row).add(seat);
        }

        int total = 2 * n;
        for(int row : map.keySet()){
            Set<Integer> set = map.get(row);

            boolean left = !(set.contains(2) || set.contains(3) 
                            || set.contains(4) || set.contains(5));

            boolean mid = !(set.contains(4) || set.contains(5) 
                            || set.contains(6) || set.contains(7));

            boolean right = !(set.contains(6) || set.contains(7) 
                            || set.contains(8) || set.contains(9));

            if(left && right){
                continue; // no change
            }
            else if(left || mid || right){
                total -= 1;
            }
            else{
                total -= 2;
            }
        }

        return total;
    }
}







// Approach 3 - Sorting + Hashing + Observation
// T.C. - O(mlog(m) + m)
// S.C. - O(1)
class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        int m = reservedSeats.length;
        int totalGroups = n * 2; // at most 2 groups can be assigned

        Arrays.sort(reservedSeats, (a, b) -> {
            if(a[0] != b[0]){
                return Integer.compare(a[0], b[0]);
            }

            return Integer.compare(a[1], b[1]);
        });

        int i = 0;
        while(i < m){
            int currRow = reservedSeats[i][0];
            Set<Integer> set = new HashSet<>();

            int j = i;

            // Gather all reserved seats for the current row
            while(j < m && reservedSeats[j][0] == currRow){
                set.add(reservedSeats[j][1]);
                j++;
            }

            boolean left = true;
            boolean right = true;
            boolean center = true;

            // Check availability for the 3 possible 4-seat blocks
            if(set.contains(2) || set.contains(3) || set.contains(4) || set.contains(5)){
                left = false;
            }
            
            if(set.contains(4) || set.contains(5) || set.contains(6) || set.contains(7)){
                center = false;
            }
            
            if(set.contains(6) || set.contains(7) || set.contains(8) || set.contains(9)){
                right = false;
            }

            // Deducting groups based on seat blocks blocked
            if(!left && !center && !right){
                totalGroups -= 2;
            }
            else if(!left && !right){
                // Left and right blocked, but center is open -> fits 1 group instead of 2
                totalGroups -= 1;
            }
            else if(!left || !center || !right){
                // Only one or two blocks are blocked, but we can still form exactly 1 group
                totalGroups -= 1;
            }

            i = j; // Move to the next unvisited row
        }

        return totalGroups;
    }
}