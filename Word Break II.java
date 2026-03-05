// Given a string s and a dictionary of 
// strings wordDict, add spaces in s to
// construct a sentence where each word 
// is a valid dictionary word. Return all
// such possible sentences in any order.

// Note that the same word in the dictionary
// may be reused multiple times in the
// segmentation.

// Input: s = "catsanddog", 
// wordDict = ["cat","cats","and","sand","dog"]
// Output: ["cats and dog","cat sand dog"]

// Input: s = "pineapplepenapple",
// wordDict = ["apple","pen","applepen","pine","pineapple"]
// Output: ["pine apple pen apple",
// "pineapple pen apple","pine applepen apple"]
// Explanation: Note that you are allowed
// to reuse a dictionary word.

// Input: s = "catsandog", 
// wordDict = ["cats","dog","sand","and","cat"]
// Output: []

// 1 <= s.length <= 20
// 1 <= wordDict.length <= 1000
// 1 <= wordDict[i].length <= 10
// s and wordDict[i] consist of only lowercase English letters.
// All the strings of wordDict are unique.
// Input is generated in a way that the length of the answer doesn't exceed 10^5.


/*
 * Approach explanation:
 * ---------------------
 * The problem asks us to return all valid 
 sentences formed from the string `s` 
 * using words from the given dictionary. 
 *
 * Step 1: We need to check every possible
 split of `s` to see if it matches a word.
 * Step 2: We recursively find all sentences
 for the remaining substring.
 * Step 3: Combine the current word with all
 valid sentences from recursion.
 *
 * The naive solution (pure recursion) would 
 recompute the same substring multiple times,
 * leading to exponential time. 
 *
 * Optimization: We use memoization to store
 results for each starting index.
 * This ensures we never recompute the same substring, drastically improving performance.
 *
 * This is why we use DFS + memoization.
 */

public List<String> wordBreak(String s, List<String> wordDict) {// Convert wordDict to a HashSet for O(1) lookups
    Set<String> set = new HashSet<>(wordDict);
// Memoization map: start index -> list of valid sentences starting at this index
    Map<Integer, List<String>> memo = new HashMap<>();
    // Start DFS from index 0
    return dfs(s, 0, set, memo);
}

private List<String> dfs(String s, int index, Set<String> set, Map<Integer, List<String>> memo) {
// If we have already computed sentences starting at `start`, return from memo
    if (memo.containsKey(index)) return memo.get(index);
// List to store all sentences starting from this index
    List<String> res = new ArrayList<>();
// Base case: if start reaches the end of string, return a list with empty string
// This allows proper sentence concatenation in recursion
    if (index == s.length()) {
        res.add("");
        return res;
    }
// Try every possible end index to form a substring from start
    for (int end = index + 1; end <= s.length(); end++) {
// Current word candidate
        String word = s.substring(index, end); 
// If this substring is in the dictionary, proceed
        if (set.contains(word)) {
// Recursively find all sentences starting from the next index
            List<String> subs = dfs(s, end, set, memo);
// Combine current word with all sentences from recursion
            for (String sub : subs) {
// Add a space only if the sub-sentence is not empty
                res.add(word + (sub.isEmpty() ? "" : " " + sub));
            }
        }
    }
// Save the computed sentences in memo to avoid recomputation
    memo.put(index, res);
    return res;
}


// time:O(N^3)
// space:O(N^2)


