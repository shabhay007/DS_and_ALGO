// LeetCode - 2043



// Approach 1 - Map
// T.C. - O(n)
// S.C. - O(n)
class Bank {
    Map<Integer, Long> bankAccounts;

    public Bank(long[] balance) { // O(n)
        this.bankAccounts = new HashMap<>();
        int n = balance.length;
        
        for(int i = 0; i<n; i++){
            bankAccounts.put(i+1, balance[i]);
        }
    }
    
    public boolean transfer(int account1, int account2, long money) { // O(1)
        if(!bankAccounts.containsKey(account1) || !bankAccounts.containsKey(account2)){
            return false;
        }

        if(bankAccounts.get(account1) >= money){
            bankAccounts.put(account1, bankAccounts.get(account1) - money);
            bankAccounts.put(account2, bankAccounts.get(account2) + money);

            return true;
        }

        return false;
    }
    
    public boolean deposit(int account, long money) { // O(1)
        if(bankAccounts.containsKey(account)){
            long balanceInAccount = bankAccounts.get(account);
            bankAccounts.put(account, balanceInAccount + money);

            return true;
        }

        return false;
    }
    
    public boolean withdraw(int account, long money) { // O(1)
        if(bankAccounts.containsKey(account)){
            long balanceInAccount = bankAccounts.get(account);
            
            if(balanceInAccount >= money){
                bankAccounts.put(account, balanceInAccount - money);

                return true;
            }

            return false;
        }

        return false;
    }
}




// Approach 2 - Array
// T.C. - O(n)
// S.C. - O(n)
class Bank {
    long bal[];
    int n;

    public Bank(long[] balance) { // O(n)
        this.n = balance.length;
        this.bal = balance;
    }
    
    public boolean transfer(int account1, int account2, long money) { // O(1)
        if(!isValid(account1) || !isValid(account2) || bal[account1 - 1] < money){
            return false;
        }

        bal[account1 - 1] -= money;
        bal[account2 - 1] += money;

        return true;
    }
    
    public boolean deposit(int account, long money) { // O(1)
        if(!isValid(account)){
            return false;
        }

        bal[account - 1] += money;

        return true;
    }
    
    public boolean withdraw(int account, long money) { // O(1)
        if(!isValid(account) || bal[account - 1] < money){
            return false;
        }

        bal[account - 1] -= money;

        return true;
    }

    public boolean isValid(int account){
        return account >= 1 && account <= n;
    }
}