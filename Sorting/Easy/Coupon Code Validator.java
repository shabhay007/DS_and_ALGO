// LeetCode - 3606



// Approach 1 - Sorting + String manipulation
// T.C. - O(n^2)
// S.C. - O(n)
class Solution {
    public boolean isValid(String code){
        if(code.isEmpty()){
            return false;
        }
        
        for(char ch : code.toCharArray()){
            if(!Character.isLetterOrDigit(ch) && ch != '_'){
                return false;
            }
        }

        return true;
    }

    public boolean isLineValid(String line){
        return line.equals("electronics") 
            || line.equals("grocery") 
            || line.equals("pharmacy") 
            || line.equals("restaurant")
        ;
    }

    public List<String> validateCoupons(String[] code, String[] businessLine, boolean[] isActive) {
        int n = code.length;
        List<String[]> result = new ArrayList<>();

        for(int i = 0; i<n; i++){
            if(isValid(code[i]) && isLineValid(businessLine[i]) && isActive[i]){
                result.add(new String[]{businessLine[i], code[i]});
            }
        }

        Collections.sort(result, (a, b) -> {
            int cmp = a[0].compareTo(b[0]);

            if(cmp != 0){
                return cmp;
            }

            return a[1].compareTo(b[1]);
        });

        List<String> coupons = new ArrayList<>();

        for(String[] pair : result){
            coupons.add(pair[1]);
        }

        return coupons;
    }
}





// Approach 2 - Sorting + String Simulation
// T.C. - O(n^2)
// S.C. - O(n)

class Pair implements Comparable<Pair> {
    int num;
    String code;

    public Pair(int num, String code){
        this.num = num;
        this.code = code;
    }

    @Override
    public int compareTo(Pair p){
        if(this.num != p.num){
            return this.num - p.num;
        }

        return this.code.compareTo(p.code);
    }
}

class Solution {
    public boolean isValid(String code){
        if(code.isEmpty()){
            return false;
        }
        
        for(char ch : code.toCharArray()){
            if(!Character.isLetterOrDigit(ch) && ch != '_'){
                return false;
            }
        }

        return true;
    }

    public boolean isLineValid(String line){
        return line.equals("electronics") 
            || line.equals("grocery") 
            || line.equals("pharmacy") 
            || line.equals("restaurant")
        ;
    }

    public List<String> validateCoupons(String[] code, String[] businessLine, boolean[] isActive) {
        int n = code.length;
        List<Pair> result = new ArrayList<>();

        // Assigning integers as it will help to avoid custom sorting or passing
        // lambda exp
        Map<String, Integer> mp = new HashMap<>();
        mp.put("electronics", 0);
        mp.put("grocery", 1);
        mp.put("pharmacy", 2);
        mp.put("restaurant", 3);

        for(int i = 0; i<n; i++){
            if(isValid(code[i]) && isLineValid(businessLine[i]) && isActive[i]){
                result.add(new Pair(mp.get(businessLine[i]), code[i]));
            }
        }

        // pair(0, SAVE20) -> if 1st is equal, it will sort on the basis of 2nd
        Collections.sort(result);

        List<String> coupons = new ArrayList<>();

        for(Pair pair : result){
            coupons.add(pair.code);
        }

        return coupons;
    }
}