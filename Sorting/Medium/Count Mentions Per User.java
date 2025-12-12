// LeetCode - 3433



// Approach 1 - Sorting + Map + Simulation
// T.C. - O(mlog(m) + m*n); events.length = m
// S.C. - O(n)
class Solution {
    public int[] countMentions(int numberOfUsers, List<List<String>> events) {
        int n = numberOfUsers;

        // custom sorting
        Collections.sort(events, (a, b) -> !(a.get(1).equals(b.get(1)))
            ? Integer.parseInt(a.get(1)) - Integer.parseInt(b.get(1))
            : b.get(0).compareTo(a.get(0))
        );

        Map<Integer, Integer> offline = new HashMap<>();
        int[] mentions = new int[n];

        for(List<String> event : events){ // O(m)
            String msg = event.get(0);
            int time = Integer.parseInt(event.get(1));
            String payload = event.get(2);
            String[] tokens = payload.split(" ");

            if(msg.equals("MESSAGE")){
                for(String token : tokens){ // O(n)
                    if(token.equals("ALL")){
                        for(int i = 0; i<n; i++){
                            mentions[i]++;
                        }
                    }
                    else if(token.equals("HERE")){
                        for(int i = 0; i<n; i++){
                            if(!offline.containsKey(i)){
                                mentions[i]++;
                            }
                            else{
                                int offTime = offline.get(i);
                                if(time - offTime >= 60){
                                    mentions[i]++;
                                }
                            }
                        }
                    }
                    else{
                        int userId = Integer.parseInt(token.substring(2));
                        mentions[userId]++;
                    }
                }
            }
            else{
                for(String token : tokens){
                    if(token.equals("ALL") || token.equals("HERE")){
                        for(int i = 0; i<n; i++){
                            offline.put(i, time);
                        }
                    }
                    else{
                        int userId = Integer.parseInt(token);
                        offline.put(userId, time);
                    }
                }
            }
        }

        return mentions;
    }
}