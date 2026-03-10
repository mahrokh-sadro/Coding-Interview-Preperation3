// You are given a 0-indexed binary string s 
// which represents the types of buildings
// along a street where:

// s[i] = '0' denotes that the ith building 
// is an office and
// s[i] = '1' denotes that the ith building
// is a restaurant.

// As a city official, you would like to select
// 3 buildings for random inspection. However, 
// to ensure variety, no two consecutive buildings 
// out of the selected buildings can be of the
// same type.

// For example, given s = "001101", we 
// cannot select the 1st, 3rd, and 5th 
// buildings as that would form "011" which
// is not allowed due to having two 
// consecutive buildings of the same type.

// Return the number of valid ways to select 3 
// buildings.
public long numberOfWays(String s) {
    int n = s.length();
    char[] arr = s.toCharArray();
    
    // Step 1: Count total number of 0s and 1s
    int totalZeros = 0, totalOnes = 0;
    for (char c : arr) {
        if (c == '0') totalZeros++;
        else totalOnes++;
    }
    
    // Step 2: Build prefix sums for 0s and 1s
    int[] prefixZeros = new int[n];
    int[] prefixOnes  = new int[n];
    
    prefixZeros[0] = arr[0] == '0' ? 1 : 0;
    prefixOnes[0]  = arr[0] == '1' ? 1 : 0;
    
    for (int i = 1; i < n; i++) {
        prefixZeros[i] = prefixZeros[i-1] + (arr[i] == '0' ? 1 : 0);
        prefixOnes[i]  = prefixOnes[i-1]  + (arr[i] == '1' ? 1 : 0);
    }
    
    // Step 3: Iterate over each building as the "middle" building
    long count = 0;
    for (int i = 0; i < n; i++) {
        if (arr[i] == '0') {
            // Pattern 101 → need '1's on left and right
            int leftOnes  = i > 0 ? prefixOnes[i-1] : 0;
            int rightOnes = totalOnes - prefixOnes[i];
            count += (long) leftOnes * rightOnes;
        } else {
            // Pattern 010 → need '0's on left and right
            int leftZeros  = i > 0 ? prefixZeros[i-1] : 0;
            int rightZeros = totalZeros - prefixZeros[i];
            count += (long) leftZeros * rightZeros;
        }
    }
    
    return count;
}


// tme:o(n)
// space:o(n)
