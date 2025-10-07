// LeetCode - 1488



// Approach 1 - Binary Search + Greedy
// T.C. - O(n*log(n))
// S.C. - O(n)
class Solution {
    // Finds the earliest dry day >= firstDay and < currDay
    public int lowerBound(int firstDay, int currDay, TreeSet<Integer> empty){
        // ceiling finds smallest >= firstDay
        Integer day = empty.ceiling(firstDay);

        if(day != null && day < currDay){
            return day;
        }

        return -1;
    }

    public int[] avoidFlood(int[] rains) {
        int n = rains.length;

        // to track which lake is full because of previous rain
        Map<Integer, Integer> map = new HashMap<>();

        // to track the day where there is no rain and we can dry any lake
        // as per requirement
        TreeSet<Integer> empty = new TreeSet<>();

        int[] result = new int[n];
        Arrays.fill(result, 1); // we can't keep it in defult i.e. 0

        for(int i = 0; i<n; i++){
            int lake = rains[i]; // as rains[i] gives the name/number of the lake

            if(lake == 0){
                empty.add(i); // day number
            }
            else{
                result[i] = -1;

                // now we need to check if this lake already contains water
                // if yes, we also need to check if we can dry it up or not
                // if not possible to dry it up, return empty array []

                // already filled with water
                if(map.containsKey(lake)){
                    // now we need to find the first day when it has not rain
                    // and we can dry it to avoid floods
                    int possibleToDryDay = lowerBound(map.get(lake), i, empty);

                    if(possibleToDryDay == -1){
                        // not possible to dry
                        return new int[0];
                    }

                    // possible to dry, so dried on day possibleToDryDay
                    result[possibleToDryDay] = lake;

                    // now remove this day from the treeset as we have used this day
                    // to dry the lake
                    empty.remove(possibleToDryDay);
                }

                // updating the map, ith day when it rain on lake
                map.put(lake, i);
            }
        }

        return result;
    }
}





// Approach 2 - Greedy + Using higer method for lower bound
// T.C. - O(n*log(n))
// S.C. - O(n)
class Solution {
    public int[] avoidFlood(int[] rains) {
        int n = rains.length;

        // to track which lake is full because of previous rain
        Map<Integer, Integer> map = new HashMap<>();

        // to track the day where there is no rain and we can dry any lake
        // as per requirement
        TreeSet<Integer> empty = new TreeSet<>();

        int[] result = new int[n];
        Arrays.fill(result, 1); // we can't keep it in defult i.e. 0

        for(int i = 0; i<n; i++){
            int lake = rains[i]; // as rains[i] gives the name/number of the lake

            if(lake == 0){
                empty.add(i); // day number
            }
            else{
                result[i] = -1;

                // now we need to check if this lake already contains water
                // if yes, we also need to check if we can dry it up or not
                // if not possible to dry it up, return empty array []

                // already filled with water
                if(map.containsKey(lake)){
                    // now we need to find the first day when it has not rain
                    // and we can dry it to avoid floods
                    // next dry day after it was last filled
                    Integer dryDay = empty.higher(map.get(lake));

                    if(dryDay == null){
                        // not possible to dry
                        return new int[0];
                    }

                    // possible to dry, so dried on day possibleToDryDay
                    result[dryDay] = lake;

                    // now remove this day from the treeset as we have used this day
                    // to dry the lake
                    empty.remove(dryDay);
                }

                // updating the map, ith day when it rain on lake
                map.put(lake, i);
            }
        }

        return result;
    }
}