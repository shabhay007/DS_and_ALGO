// LeetCode Medium - 2349


// Approach 1 - Using 2 map and Priority Queue - GIVES TLE
// T.C. - O(n + log(n))
// S.C. - O(2n)
class NumberContainers {
    HashMap<Integer, PriorityQueue<Integer>> numToIndex; // O(n)
    HashMap<Integer, Integer> indexToNum; // O(n)

    public NumberContainers() {
        this.numToIndex = new HashMap<>();
        this.indexToNum = new HashMap<>();
    }
    
    public void change(int index, int number) {
        if(indexToNum.containsKey(index)){
            int oldNum = indexToNum.get(index);

            if(oldNum == number){ // no need to change if oldNum and number is same
                return;
            }

            if(numToIndex.containsKey(oldNum)){
                numToIndex.get(oldNum).remove(index); // O(n)
            }
        }

        numToIndex.putIfAbsent(number, new PriorityQueue<>());
        numToIndex.get(number).add(index); // O(logn) - insertion on PQ
        indexToNum.put(index, number);
    }
    
    public int find(int number) {
        if(!numToIndex.containsKey(number)){
            return -1;
        }
        
        PriorityQueue<Integer> pq = numToIndex.get(number);

        return pq.isEmpty() ? -1 : pq.peek();
    }
}





// Approach 2 - Using 2 map and TreeSet
// T.C. - O(2log(n))
// S.C. - O(2n)
class NumberContainers {
    HashMap<Integer, TreeSet<Integer>> numToIndex; // O(n)
    HashMap<Integer, Integer> indexToNum; // O(n)

    public NumberContainers() {
        this.numToIndex = new HashMap<>();
        this.indexToNum = new HashMap<>();
    }
    
    public void change(int index, int number) {
        // if index is already present
        if(indexToNum.containsKey(index)){
            int oldNum = indexToNum.get(index);

            // then find the index and if old number and curr number is same, do nothing
            if(oldNum == number){
                return;
            }

            // check if old number exists in num to index map or not
            // if exists, remove the index from the old number
            if(numToIndex.containsKey(oldNum)){
                numToIndex.get(oldNum).remove(index); // O(log(n))
            }

            // if old numbers set gets empty, then remove the key also
            if(numToIndex.get(oldNum).isEmpty()){
                numToIndex.remove(oldNum);
            }
        }

        // after removal or
        // if it comes first time
        numToIndex.putIfAbsent(number, new TreeSet<>());
        numToIndex.get(number).add(index); // O(logn)
        
        // updating the index to number  map
        indexToNum.put(index, number);
    }
    
    public int find(int number) {
        if(!numToIndex.containsKey(number)){
            return -1;
        }
        
        TreeSet<Integer> tSet = numToIndex.get(number);

        return tSet.isEmpty() ? -1 : tSet.first();
    }
}





// Approach 3 - Using 2 map and Priority Queue and LAZY UPDATE
// T.C. - O(nlog(n) + log(n))
// S.C. - O(2n)
class NumberContainers {
    HashMap<Integer, PriorityQueue<Integer>> numToIndex; // O(n)
    HashMap<Integer, Integer> indexToNum; // O(n)

    public NumberContainers() {
        this.numToIndex = new HashMap<>();
        this.indexToNum = new HashMap<>();
    }
    
    public void change(int index, int number) {
        numToIndex.putIfAbsent(number, new PriorityQueue<>());
        numToIndex.get(number).add(index); // O(logn)
        
        // updating the index to number  map
        indexToNum.put(index, number);
    }
    
    public int find(int number) {
        if(!numToIndex.containsKey(number)){
            return -1;
        }
        
        // now check number's smallest index still maps to this number or not
        // if not, it means we have deleted that index from that number
        // although, we haven't deleted it physically
        PriorityQueue<Integer> pq = numToIndex.get(number);

        while(!pq.isEmpty()){ // O(n)
            int idx = pq.peek(); // O(1)

            if(indexToNum.get(idx) == number){
                return idx;
            }
            else{
                pq.poll(); // O(log(n))
            }
        }

        // remove the number from the map if it is not present at any index
        if(pq.isEmpty()){
            numToIndex.remove(number); // O(1)
        }

        return pq.isEmpty() ? -1 : pq.peek();
    }
}