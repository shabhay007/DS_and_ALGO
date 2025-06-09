// LeetCode - 440



// Approach 1 (Brute Force) - Gives TLE
// T.C. - O(n)
// S.C. - O(n)
class Solution {
    public void solve(long curr, int n, int[] k, int num[]){
        if(curr > n || num[0] != -1){
            return;
        }

        k[0]--;

        if(k[0] == 0){
            num[0] = (int) curr;
            return;
        }

        for(int i = 0; i<=9; i++){
            long newNum = curr * 10 + i;

            if(newNum > n){
                break;
            }

            solve(newNum, n, k, num);
        }
    }
    
    public int findKthNumber(int n, int k) {
        int num[] = {-1};
        int[] counter = {k};

        for(int i = 1; i<=9; i++){
            solve(i, n, counter, num);

            if(num[0] != -1){
                break;
            }
        }

        return num[0];
    }
}





// Approach 2 (Optimal) - Trie
// T.C. - O(log(n) * log(n))
// S.C. - O(log(n))
class Solution {
    public long findCount(long curr, long next, int n){ // O(log(n))
        long countNum = 0;

        while(curr <= n){
            countNum += (next - curr);
            curr *= 10;
            next *= 10;
            next = Math.min(next, n+1);
        }

        return countNum;
    } 
    
    public int findKthNumber(int n, int k) {
        long curr = 1; 
        k -= 1; // since 1 is starting number

        while(k > 0){ // O(log(n))
            long count = findCount(curr, curr + 1, n); // curr, next, limit

            if(count <= k){
                curr++;
                k -= count; // skipping elements under current prefix tree
            }
            else{
                curr *= 10; // moving to the next level
                k -= 1;
            }
        }

        return (int) curr;
    }
}