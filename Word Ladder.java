// A transformation sequence from word
// beginWord to word endWord using a 
// dictionary wordList is a sequence of
// words beginWord -> s1 -> s2 -> ... -> sk such that:

// Every adjacent pair of words differs 
// by a single letter.
// Every si for 1 <= i <= k is in wordList.
// Note that beginWord does not need to be 
// in wordList.
// sk == endWord

// Given two words, beginWord and endWord,
// and a dictionary wordList, return the
// number of words in the shortest transformation
// sequence from beginWord to endWord, or
// 0 if no such sequence exists.

// Example 1:

// Input: beginWord = "hit", endWord = "cog",
// wordList = ["hot","dot","dog","lot","log","cog"]
// Output: 5
// Explanation: One shortest transformation 
// sequence is "hit" -> "hot" -> "dot" -> "dog" -> cog",
// which is 5 words long.

// Example 2:

// Input: beginWord = "hit", endWord = "cog",
// wordList = ["hot","dot","dog","lot","log"]
// Output: 0
// Explanation: The endWord "cog" is not in 
// wordList, therefore there is no valid 
// transformation sequence.


// 1 <= beginWord.length <= 10
// endWord.length == beginWord.length
// 1 <= wordList.length <= 5000
// wordList[i].length == beginWord.length
// beginWord, endWord, and wordList[i] consist of lowercase English letters.
// beginWord != endWord
// All the words in wordList are unique.

public int ladderLength(String beginWord, String endWord, List<String> wordList) {
    // We treat each word as a node in a graph.
    //  Two words are connected if they differ 
    //  by exactly one letter. The problem reduces 
    //  to finding the shortest path from beginWord
    //   to endWord through valid intermediate
    //    words. BFS is used because it explores
    //     level by level and guarantees the 
    //     shortest path in an unweighted graph.

    // For a given word, adjacent nodes are all
    //  words in the dictionary that differ by 
    //  exactly one letter. In your solution, you
    //   generate them by iterating through each
    //    character position, replacing it with
    //     'a' to 'z', and checking if the resulting
    //      word exists in the word set.

    // Convert word list to HashSet for O(1) lookup
    Set<String> wordSet = new HashSet<>(wordList);
    // If endWord is not in dictionary, transformation is impossible
    if (!wordSet.contains(endWord)) return 0;
    // Queue for BFS traversal
    Queue<String> queue = new LinkedList<>();
    queue.offer(beginWord);
    // Level represents number of transformations (distance)
    int level = 1;
    /*
     * Why BFS?
     * Because we need the shortest number of transformations.
     * BFS explores level-by-level, guaranteeing shortest path in an unweighted graph.
     */
    while (!queue.isEmpty()) {
        int size = queue.size(); 
// number of nodes in current level
// Process all words at current transformation distance
        for (int i = 0; i < size; i++) {
            String word = queue.poll();
// If we reached endWord, return number of steps taken
            if (word.equals(endWord)) return level;

// Convert word to char array to modify letters
            char[] chars = word.toCharArray();

// Try changing each character
            for (int j = 0; j < chars.length; j++) {
// store original letter
                char original = chars[j]; 
// Replace current character with 'a' to 'z'
                for (char c = 'a'; c <= 'z'; c++) {
// Skip same letter to avoid unnecessary work
                    if (c == original) continue;
                    chars[j] = c;
                    String nextWord = new String(chars);
/*
 * If new word exists in dictionary,
 * then it is a valid neighbor in the graph.
 */
                    if (wordSet.contains(nextWord)) {
                        queue.offer(nextWord);
// Remove from set to mark as visited
// Prevents revisiting and cycles
                        wordSet.remove(nextWord);
                    }
                }
// Restore original character before moving to next position
                chars[j] = original;
            }
        }
// After finishing current level, increase transformation count
        level++;
    }
    // If BFS finishes without finding endWord
    return 0;
}
//add time n space complexity