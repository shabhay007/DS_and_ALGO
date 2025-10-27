// LeetCode - 2125



// Approach 1 - String + BitCount
// T.C. - O(m * n)
// S.C. - O(1)
class Solution {
    public int getBitCount(String binaryStr){
        int result = 0;

        for(char ch : binaryStr.toCharArray()){
            if(ch == '1'){
                result++;
            }
        }

        return result;
    }

    public int numberOfBeams(String[] bank) {
        int n = bank.length;
        int totalBeams = 0;

        int laserCountR1 = getBitCount(bank[0]);

        for(int i = 1; i<n; i++){
            int laserCountR2 = getBitCount(bank[i]);

            if(laserCountR2 == 0){
                continue;
            }

            totalBeams += laserCountR1 * laserCountR2;
            laserCountR1 = laserCountR2;
        }

        return totalBeams;
    }
}