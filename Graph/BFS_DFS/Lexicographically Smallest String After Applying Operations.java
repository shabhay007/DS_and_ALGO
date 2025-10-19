// LeetCode - 1625




// Approach - BFS
// T.C. - O(n^2)
// S.C. - O(n^2)
class Solution {
    private void reverseRange(StringBuilder sb, int left, int right){
        while(left < right){
            char temp = sb.charAt(left);
            sb.setCharAt(left, sb.charAt(right));
            sb.setCharAt(right, temp);
            left++;
            right--;
        }
    }

    public String rotate(String str, int b){
        StringBuilder sb = new StringBuilder(str);
        sb.reverse();
        reverseRange(sb, 0, b-1);
        reverseRange(sb, b, sb.length() - 1);

        return sb.toString();
    }

    public String findLexSmallestString(String s, int a, int b) {
        int n = s.length();
        Queue<String> q = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        q.offer(s);
        visited.add(s);
        String min = s;

        while(!q.isEmpty()){
            String curr = q.poll();

            if(curr.compareTo(min) < 0){
                min = curr;
            }

            // ******* 1st operation : addition of a
            char[] temp = curr.toCharArray();

            for(int i = 1; i<n; i += 2){
                // first converting to integer then adding a
                // then taking mod with 10 for cycle
                // then converting back to char
                int digit = (((temp[i] - '0') + a) % 10);
                temp[i] = (char) (digit + '0');
            }

            String newState = new String(temp);

            if(!visited.contains(newState)){
                q.offer(newState);
                visited.add(newState);
            }


            // ******* 2nd operation : rotation
            String rotatedStr = rotate(curr, b);

            if(!visited.contains(rotatedStr)){
                q.offer(rotatedStr);
                visited.add(rotatedStr);
            }
        }

        return min;
    }
}