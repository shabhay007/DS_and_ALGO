// LeetCode - 3170



// Optimal - Using Heap
// T.C. - O(n.log(n) + n)
// S.C. - O(n)
class Solution {
    class Pair{
        char ch;
        int idx;

        public Pair(char ch, int idx){
            this.ch = ch;
            this.idx = idx;
        }
    }

    public String clearStars(String s) {
        int n = s.length();
        char[] arr = s.toCharArray();

        PriorityQueue<Pair> pq = new PriorityQueue<>(new Comparator<Pair>(){
            public int compare(Pair p1, Pair p2){
                if(p1.ch == p2.ch){
                    return p2.idx - p1.idx;
                }

                return p1.ch - p2.ch;
            }
        });

        for(int i = 0; i<n; i++){
            char curr = s.charAt(i);

            if(curr != '*'){
                pq.offer(new Pair(curr, i));
            }
            else if(curr == '*' && !pq.isEmpty()){
                Pair p = pq.poll();

                // now marking the deleted char in char arr, so that order get maintained
                arr[p.idx] = '*';
            }
        }

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i<n; i++){
            if(arr[i] != '*'){
                sb.append(arr[i]);
            }
        }

        return sb.toString();
    }
}