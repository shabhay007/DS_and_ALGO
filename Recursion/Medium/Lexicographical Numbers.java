// LeetCode - 386



// Approach 1 - DFS/Recursion
// T.C. - O(n); each number in [1, n] will be added exactly once
// S.C. - O(maxDigit(n)) ~ O(log(n))
class Solution {
    public void solve(StringBuilder curr ,int n, List<Integer> list){
        int num = Integer.parseInt(curr.toString());
        if(num > n){
            return;
        }

        list.add(num);

        for(int i = 0; i<=9; i++){
            curr.append(i);
            solve(curr, n, list);
            curr.deleteCharAt(curr.length() - 1);
        }
    }

    public List<Integer> lexicalOrder(int n) {
        List<Integer> list = new ArrayList<>();
        
        for(int i = 1; i<=9; i++){
            if(i > n) break;

            StringBuilder sb = new StringBuilder();
            sb.append(i);
            solve(sb, n, list);
        }

        return list;
    }
}





// Approach 2 - DFS
// T.C. - O(n)
// S.C. - O(log(n))
class Solution {
    public void solve(int curr ,int n, List<Integer> list){
        if(curr > n){
            return;
        }

        list.add(curr);

        for(int i = 0; i<=9; i++){
            int newNum = curr*10 + i;

            if(newNum > n) return;
            
            solve(newNum, n, list);
        }
    }

    public List<Integer> lexicalOrder(int n) {
        List<Integer> list = new ArrayList<>();
        
        for(int i = 1; i<=9; i++){
            if(i > n) break;

            solve(i, n, list);
        }

        return list;
    }
}