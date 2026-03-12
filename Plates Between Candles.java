// There is a long table with a line of
// plates and candles arranged on top of it.
// You are given a 0-indexed string s
// consisting of characters '*' and '|' only,
// where a '*' represents a plate and a '|' 
// represents a candle.

// You are also given a 0-indexed 2D integer 
// array queries where queries[i] = [lefti, righti]
// denotes the substring s[lefti...righti]
// (inclusive). For each query, you need to
// find the number of plates between candles
// that are in the substring. A plate is
// considered between candles if there is at 
// least one candle to its left and at least
// one candle to its right in the substring.

// For example, s = "||**||**|*", and a 
// query [3, 8] denotes the substring "*||**|".
// The number of plates between candles in this 
// substring is 2, as each of the two plates has
// at least one candle in the substring to its
// left and right.

// Return an integer array answer where answer[i]
// is the answer to the ith query.

// 3 <= s.length <= 10^5
// s consists of '*' and '|' characters.
// 1 <= queries.length <= 10^5
// queries[i].length == 2
// 0 <= lefti <= righti < s.length

public int[] platesBetweenCandles(String s, int[][] queries) {

    int n = s.length();

    // prefix[i] = number of plates '*' from index 0 → i
    // This allows us to quickly count plates between two indices
    int prefix[] = new int[n];

    // running count of plates seen so far
    int plates = 0;

    // last seen candle index while scanning left → right
    int last = -1;

    // leftCandle[i] = nearest candle index to the LEFT of i (including i)
    int[] leftCandle = new int[n];

    // rightCandle[i] = nearest candle index to the RIGHT of i (including i)
    int[] rightCandle = new int[n];

    // Build:
    // 1) prefix plate counts
    // 2) nearest left candle for every index
    for(int i = 0; i < n; i++){

        // If current character is a plate, increase count
        if(s.charAt(i) == '*'){
            plates++;
        }
        else{
            // If it's a candle, update last seen candle
            last = i;
        }
        // Store number of plates seen so far
        prefix[i] = plates;
        // Store nearest candle on the left
        leftCandle[i] = last;
    }
    // Reset last for right side scan
    last = -1;

    // Build nearest right candle array by scanning right → left
    for(int i = n - 1; i >= 0; i--){
        if(s.charAt(i) == '|'){
            last = i;
        }

        rightCandle[i] = last;
    }
    // Result array
    int[] ans = new int[queries.length];
    // Process each query
    for(int i = 0; i < queries.length; i++){
        int L = queries[i][0];
        int R = queries[i][1];
        // Find first candle inside the query range
        //it gives me closest candle to the right of L,
        // which is the closest | to the right as if 
        // it was to the left of L it was out of bound
        int l = rightCandle[L];
        // Find last candle inside the query range
        int r = leftCandle[R];
        // If there are not two candles inside the range
        // then no plates can exist between candles
        if(l == -1 || r == -1 || l > r){
            ans[i] = 0;
        }
        else{
            // Plates between candles =
            // prefix[r] - prefix[l]
            // because prefix stores cumulative plate counts
            ans[i] = prefix[r] - prefix[l];
        }
    }

    return ans;
}
//time:O(N+Q)
//space: O(n + q)

