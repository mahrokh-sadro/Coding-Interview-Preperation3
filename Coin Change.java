// You are given an integer array coins
// representing coins of different
// denominations and an integer amount
// representing a total amount of money.

// Return the fewest number of coins that
// you need to make up that amount. If that
// amount of money cannot be made up by any 
// combination of the coins, return -1.

// You may assume that you have an infinite
// number of each kind of coin.

class Solution {
    public int coinChange(int[] coins, int amount) {
        int[] dp=new int[amount+1];
        Arrays.fill(dp,amount+1);
        // Base case: 0 coins needed to make amount 0
        dp[0] = 0;
        for(int i=1;i<=amount;i++){
           for(int coin:coins){
              if(i-coin>=0){
                 dp[i]=Math.min(dp[i],dp[i-coin]+1);
              }
           }
        }
        return dp[amount]==amount+1?-1:dp[amount];
    }
}

// Time:O(amount × number_of_coins).
// Space:O(amount)

// This is a bottom-up dynamic programming solution.
// I define dp[i] as the minimum number of coins
//  needed to make amount i.

// I initialize the array with a large value 
// (amount + 1) to represent an impossible state, 
// and set dp[0] = 0 because zero coins are needed
//  to make amount 0.

// Then for each amount from 1 to the target, I
//  try every coin.
// If the coin can be used (i.e., i - coin >= 0),
//  I update:

// dp[i] = min(dp[i], dp[i - coin] + 1)

// This means: if I use this coin, how many 
// coins did I need to make the remaining amount?

// At the end, if dp[amount] is still the large value,
//  it means it’s impossible, so I return -1.
//   Otherwise, I return dp[amount].