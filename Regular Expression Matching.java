class Solution {
    public boolean isMatch(String s, String p) {
        int n = s.length();
        int m = p.length();

        // dp[i][j] = does first i characters of s
        // match first j characters of p?
        boolean[][] dp = new boolean[n + 1][m + 1];

        // Empty string matches empty pattern
        dp[0][0] = true;

        // Handle patterns like a*, a*b*, a*b*c*
        // These can match empty string
        for (int i = 2; i <= m; i++) {
            if (p.charAt(i - 1) == '*') {
                // If pattern ends with '*', it can eliminate
                // the preceding character (zero occurrence)
                dp[0][i] = dp[0][i - 2];
            }
        }

        // Fill DP table
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
            // Case 1: Direct match OR pattern has '.'
            // '.' matches any single character
                if (p.charAt(j - 1) == s.charAt(i - 1) || 
                    p.charAt(j - 1) == '.') {
            // Move diagonally (consume both characters)
                    dp[i][j] = dp[i - 1][j - 1];
                } 
             // Case 2: Pattern has '*'
                else if (p.charAt(j - 1) == '*') {
            // Option 1: Treat '*' as zero occurrences
            // Skip the previous character + '*'
                    dp[i][j] = dp[i][j - 2];
            // Option 2: Treat '*' as one or more occurrences
            // Check if preceding character matches current char
            //p.charAt(j - 2) == s.charAt(i - 1)  "aa"  "a*"
            //Compare the previous character in the pattern
            // (the one before *) with the current character in the string.
            //p.charAt(j - 2) == '.'
            //"aa"  ".*"
                    if (p.charAt(j - 2) == s.charAt(i - 1) || 
                        p.charAt(j - 2) == '.') {
            // Stay on same pattern index (j)
            // when removing one char from string
            // "aaa"   "a*"
                        dp[i][j] = dp[i][j] || dp[i - 1][j];
                    }
                }
            }
        }

        // Final answer: full string matches full pattern
        return dp[n][m];
    }
}
