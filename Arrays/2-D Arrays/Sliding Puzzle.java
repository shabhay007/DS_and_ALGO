// LeetCode - 773 - Hard


// Optimal - BFS
class Solution {
    public int slidingPuzzle(int[][] board) {
        StringBuilder start = new StringBuilder();

        for(int i = 0; i<2; i++){
            for(int j = 0; j<3; j++){
                start.append(board[i][j]);
            }
        }

        // positions = 6
        // total possible states = 0(6!);
        // worst case, we have to visit all states
        // Space - O(6!)
        String target = "123450";

        Queue<String> q = new LinkedList<>();
        q.offer(start.toString());

        // mapping that if 0 is present in any indices in 2 * 3 
        // from that position, where can we move
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        map.put(0, Arrays.asList(1, 3));
        map.put(1, Arrays.asList(0, 2, 4));
        map.put(2, Arrays.asList(1, 5));
        map.put(3, Arrays.asList(0, 4));
        map.put(4, Arrays.asList(1, 3, 5));
        map.put(5, Arrays.asList(2, 4));

        // taking a set to track, which we have already visited
        HashSet<String> visitedSet = new HashSet<>();
        visitedSet.add(start.toString());
        
        int moves = 0; // level

        while(!q.isEmpty()){
            int n = q.size();

            // processing all elements in the curr level
            for(int i = 0; i<n; i++){
                String curr = q.poll();

                if(curr.equals(target)){
                    return moves;
                }

                int idxOfZero = curr.indexOf("0");

                for(int swapIndex : map.get(idxOfZero)){
                    char[] newState = curr.toCharArray();
                    char temp = newState[idxOfZero];
                    newState[idxOfZero] = newState[swapIndex];
                    newState[swapIndex] = temp;

                    String str = new String(newState);

                    if(!visitedSet.contains(str)){
                        q.offer(str);
                        visitedSet.add(str);
                    }
                }
            }

            moves++;
        }

        return -1;
    }
}
