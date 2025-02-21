// LeetCode Medium - 1352


// Approach 1 - Using ArrayList
// T.C. - O(k)
// S.C. - O(n)
class ProductOfNumbers {
    List<Integer> numList;

    public ProductOfNumbers() {
        this.numList = new ArrayList<>();
    }
    
    public void add(int num) {
        numList.add(num);
    }
    
    public int getProduct(int k) { // O(k)
        int n = numList.size();
        int product = 1;
        
        // to get last product of last k elements
        for(int i = n-k; i<n; i++){
            product *= numList.get(i);
        }

        return product;
    }
}





// Optimal Approach 2 - Using concept of Prefix sum
// T.C. - O(1)
// S.C. - O(n)
class ProductOfNumbers {
    List<Integer> productList;

    public ProductOfNumbers() {
        this.productList = new ArrayList<>();
    }
    
    public void add(int num) {
        int n = productList.size();

        // if we encounter 0 clear the list i.e. removing all the elements
        // because it will make product of all the elements 0
        // and then, initiate fresh start of adding the elements
        if(num == 0){
            productList.clear();
        }
        else{
            // now fresh insert the elements
            if(productList.isEmpty()){
                productList.add(num);
            }
            else{
                // adding the product of the last and the current elements 
                productList.add(productList.get(n-1) * num);
            }
        }
    }
    
    public int getProduct(int k) { // O(k)
        int n = productList.size();
        
        if(k > n){
            return 0;
        }

        if(n == k){
            return productList.get(n-1);
        }
        
        int currProduct = productList.get(n-1);
        int prevProduct = productList.get(n-k-1);

        return (currProduct / prevProduct);
    }
}