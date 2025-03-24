// LeetCode Medium - 3169


// Approach 1 - Sorting
// T.C. - O(n.log(n) + n + k); k = n in worst case when no overlapping
// S.C. - O(k)
class Solution {
    public int countDays(int days, int[][] meetings) {
        int n = meetings.length;

        // sorting based on start time
        Arrays.sort(meetings, (a, b) -> a[0] - b[0]); // nlog(n)

        // merging overlapping meetings
        List<int[]> merged = new ArrayList<>();

        for(int[] meeting : meetings){ // O(n)
            if(merged.isEmpty()){
                merged.add(meeting);
            }
            else if(meeting[0] <= merged.get(merged.size() - 1)[1]){ // Overlapping
                merged.get(merged.size() - 1)[0] = Math.min(meeting[0], merged.get(merged.size() - 1)[0]);
                merged.get(merged.size() - 1)[1] = Math.max(meeting[1], merged.get(merged.size() - 1)[1]);
            }
            else{
                merged.add(meeting);
            }
        }

        // now counting days without meetings
        for(int[] meeting : merged){ // O(k); k = meetings after merging overlapping meetings
            days -= meeting[1] - meeting[0] + 1;
        }

        return days;
    }
}



// Approach 2 - Sorting
// T.C. - O(n.log(n) + n)
// S.C. - O(1)
class Solution {
    public int countDays(int days, int[][] meetings) {
        int n = meetings.length;

        // sorting based on start time
        Arrays.sort(meetings, (a, b) -> a[0] - b[0]); // nlog(n)

        // now counting days without meetings
        int end = 0;
        int noOfDays = 0;

        for(int[] meeting : meetings){ // O(n)
            if(meeting[0] > end){
                noOfDays += meeting[0] - end - 1;
            }

            // updating end
            end = Math.max(end, meeting[1]);
        }

        if(end < days){
            noOfDays += days - end;
        }

        return noOfDays;
    }
}