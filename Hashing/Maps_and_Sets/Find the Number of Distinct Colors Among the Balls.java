// LeetCode Medium - 3160


// Brute Force - Using Array and Set - Gives MLE
// T.C. - O(n * limit)
// S.C. - O(n)
class Solution {
    public int[] queryResults(int limit, int[][] queries) {
        int n = queries.length;
        int balls[] = new int[limit + 1];

        int i = 0;
        int result[] = new int[n];

        for(int[] query : queries){
            int ballNum = query[0];
            int color = query[1];

            // marking the color on the ball
            balls[ballNum] = color;

            // set to track distinct elements
            HashSet<Integer> set = new HashSet<>();

            for(int ball : balls){
                if(ball != 0){
                    set.add(ball);
                }
            }

            if(i < n){
                // storing the distinct colors a/c to query
                result[i] = set.size();
                i++;
            }
        }

        return result;
    }
}





// Better - Using Array and Map - Gives MLE
// T.C. - O(n)
// S.C. - O(limit + n)
class Solution {
    public int[] queryResults(int limit, int[][] queries) {
        int n = queries.length;
        int balls[] = new int[limit + 1];

        int i = 0;
        int result[] = new int[n];

        // map to store color -> color count
        HashMap<Integer, Integer> map = new HashMap<>();

        for(int[] query : queries){
            int ballNum = query[0];
            int color = query[1];

            // marking the color on the ball
            if(balls[ballNum] != 0){
                // finding the prev color of the ball
                int prevColor = balls[ballNum];
                map.put(prevColor, map.get(prevColor) - 1);

                if(map.get(prevColor) == 0){
                    map.remove(prevColor);
                }
            }

            // assigning new color to the ball
            balls[ballNum] = color;
            map.put(color, map.getOrDefault(color, 0) + 1);

            if(i < n){
                // storing the distinct colors a/c to query
                result[i] = map.size();
                i++;
            }
        }

        return result;
    }
}






// Optimal - Using 2 Maps
// T.C. - O(n)
// S.C. - O(n + n)
class Solution {
    public int[] queryResults(int limit, int[][] queries) {
        int n = queries.length;

        int i = 0;
        int result[] = new int[n];

        // map to store :- ball -> color
        HashMap<Integer, Integer> ballMap = new HashMap<>();

        // map to store :- color -> color count
        HashMap<Integer, Integer> colorMap = new HashMap<>();

        for(int[] query : queries){
            int ball = query[0];
            int color = query[1];

            // marking the color on the ball
            if(ballMap.containsKey(ball)){
                // finding the prev color of the ball
                int prevColor = ballMap.get(ball);
                colorMap.put(prevColor, colorMap.get(prevColor) - 1);

                if(colorMap.get(prevColor) == 0){
                    colorMap.remove(prevColor);
                }
            }

            // assigning new color to the ball
            ballMap.put(ball, color);

            // updating the colorMap
            colorMap.put(color, colorMap.getOrDefault(color, 0) + 1);

            if(i < n){
                // storing the distinct colors a/c to query
                result[i] = colorMap.size();
                i++;
            }
        }

        return result;
    }
}
