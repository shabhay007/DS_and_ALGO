// LeetCode - 2409



// Approach 1
// T.C. - O(1)
// S.C. - O(1)
class Solution {
    private static int getDayOfYear(int day, int month) {
        int[] daysInMonths = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        
        int totalDays = day;
        for (int i = 1; i < month; i++) {
            totalDays += daysInMonths[i];
        }

        return totalDays;
    }

    public int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
        // Convert Alice's dates to absolute days of the year
        int aliceStart = getDayOfYear(Integer.parseInt(arriveAlice.substring(3, 5)), Integer.parseInt(arriveAlice.substring(0, 2)));
        int aliceEnd = getDayOfYear(Integer.parseInt(leaveAlice.substring(3, 5)), Integer.parseInt(leaveAlice.substring(0, 2)));
        
        // Convert Bob's dates to absolute days of the year
        int bobStart = getDayOfYear(Integer.parseInt(arriveBob.substring(3, 5)), Integer.parseInt(arriveBob.substring(0, 2)));
        int bobEnd = getDayOfYear(Integer.parseInt(leaveBob.substring(3, 5)), Integer.parseInt(leaveBob.substring(0, 2)));

        // Find the latest arrival day and the earliest departure day
        int latestStart = Math.max(aliceStart, bobStart);
        int earliestEnd = Math.min(aliceEnd, bobEnd);

        // Calculate overlap (Include the 1 to make it inclusive, or return 0 if no overlap)
        if (latestStart <= earliestEnd) {
            return earliestEnd - latestStart + 1;
        }
        else {
            return 0; 
        }
    }
}
