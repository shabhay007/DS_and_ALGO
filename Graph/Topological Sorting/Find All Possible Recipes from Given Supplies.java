// LeetCode Medium - 2115


// Approach 1 - Using Maps & Sets
// T.C. - O(m + 2n + n^2.k)
// S.C. - O(n + (n + m) + n); n = recipes set length; 
// (n+m) = supplies set length; n = map 
class Solution {
    public List<String> findAllRecipes(String[] recipes, List<List<String>> ingredients, String[] supplies) {
        int n = recipes.length;

        // supplies
        Set<String> suppliesSet = new HashSet<>();

        for(String supply : supplies){ // O(m)
            suppliesSet.add(supply);
        }

        // map of ingredients fo the recipe
        Map<String, List<String>> map = new HashMap<>();

        for(int i = 0; i<n; i++){ // O(n)
            map.put(recipes[i], ingredients.get(i));
        }

        // try to prepare the recipes
        Set<String> cookedRecipe = new HashSet<>();

        // for every recipe, we have to iterate the entire recipe
        // because we may encounter that the 1st recipe can't be made as it
        // is dependent on other recipes
        int count = n;
        while(count >= 0){ // O(n^2.k)

            for(String recipe : recipes){ // O(n)
                if(cookedRecipe.contains(recipe)){
                    continue; // skip, if recipe is already cooked
                }

                boolean isPossible = true;

                // O(k); avg length of list
                for(String ingredient : map.get(recipe)){ // O(k)
                    if(!suppliesSet.contains(ingredient)){
                        isPossible = false;
                        break;
                    }
                }

                if(isPossible){
                    cookedRecipe.add(recipe);
                    suppliesSet.add(recipe);
                }
            }

            count--;
        }

        // converting set back to ArrayList
        return new ArrayList<>(cookedRecipe); // O(n)
    }
}



// Approach 2 - Slight change in approach 1
// T.C. - O(m + n + n^2.k)
// S.C. - O(n + (n + m) + n); n = cookedRecipe length; 
// (n+m) = supplies set length; n = map 
class Solution {
    public List<String> findAllRecipes(String[] recipes, List<List<String>> ingredients, String[] supplies) {
        int n = recipes.length;

        // supplies
        Set<String> suppliesSet = new HashSet<>();

        for(String supply : supplies){ // O(m)
            suppliesSet.add(supply);
        }

        // map of ingredients fo the recipe
        Map<String, List<String>> map = new HashMap<>();

        for(int i = 0; i<n; i++){ // O(n)
            map.put(recipes[i], ingredients.get(i));
        }

        // try to prepare the recipes
        boolean[] cookedRecipe = new boolean[n];
        List<String> result = new ArrayList<>();
        int count = n;

        while(count >= 0){ // O(n^2.k)

            for(int j = 0; j<n; j++){ // O(n)
                if(cookedRecipe[j]){
                    continue; // skip, if recipe is already cooked
                }

                boolean isPossible = true;

                // O(k); avg length of list
                for(String ingredient : map.get(recipes[j])){ // O(k)
                    if(!suppliesSet.contains(ingredient)){
                        isPossible = false;
                        break;
                    }
                }

                if(isPossible){
                    cookedRecipe[j] = true;
                    result.add(recipes[j]);
                    suppliesSet.add(recipes[j]);
                }
            }

            count--;
        }

        return result;
    }
}




// Approach 3 - Topological sort using Kahn's algorithm, O(V + E)
// T.C. - O(n + m + s); n = V (no of vertices), m = E (no of edges), s = supplies set
// S.C. - O(s + (n + m) + 2n); s = set, (n + m) ~ (V + E) = graph, n = indegree & queue
class Solution {
    public List<String> findAllRecipes(String[] recipes, List<List<String>> ingredients, String[] supplies) {
        int n = recipes.length;

        // step 1 : put all the supplies in a set for constant look-up
        // supplies
        Set<String> suppliesSet = new HashSet<>(Arrays.asList(supplies));


        // step 2 : make a graph and fill the indegree array
        // graph : ingredient -> recipe
        Map<String, List<Integer>> adj = new HashMap<>();
        int[] indegree = new int[n];

        for(int i = 0; i<n; i++){
            for(String ingredient : ingredients.get(i)){
                if(!suppliesSet.contains(ingredient)){
                    adj.putIfAbsent(ingredient, new ArrayList<>());
                    adj.get(ingredient).add(i);
                    indegree[i]++;
                }
            }
        }

        // step 3 : put the recipe (here index) whose indegree is 0 in queue
        Queue<Integer> q = new LinkedList<>();

        for(int i = 0; i<n; i++){
            if(indegree[i] == 0){
                q.offer(i);
            }
        }

        // step 4 : processing the result
        List<String> result = new ArrayList<>();

        while(!q.isEmpty()){
            int curr = q.poll();
            String recipe = recipes[curr];
            result.add(recipe);

            if(adj.containsKey(recipe)){
                for(int neigh : adj.get(recipe)){
                    indegree[neigh]--;

                    if(indegree[neigh] == 0){
                        q.offer(neigh);
                    }
                }
            }
        }

        return result;
    }
}