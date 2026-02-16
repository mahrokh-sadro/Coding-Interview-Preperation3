// You are given two string arrays username and
// website and an integer array timestamp.
// All the given arrays are of the same
// length and the tuple [username[i], website[i]
// , timestamp[i]] indicates that the user 
// username[i] visited the website website[i] 
// at time timestamp[i].
// A pattern is a list of three websites 
// (not necessarily distinct).
// For example, ["home", "away", "love"],
// ["leetcode", "love", "leetcode"], and 
// ["luffy", "luffy", "luffy"] are all patterns.

// The score of a pattern is the number of users
// that visited all the websites in the
// pattern in the same order they appeared in the pattern.

// For example, if the pattern is 
// ["home", "away", "love"], the score is 
// the number of users x such that x visited
// "home" then visited "away" and visited 
// "love" after that.
// Similarly, if the pattern is 
// ["leetcode", "love", "leetcode"], the score
// is the number of users x such that x visited
// "leetcode" then visited "love" and visited
// "leetcode" one more time after that.
// Also, if the pattern is ["luffy", "luffy", "luffy"]
// , the score is the number of users x such
// that x visited "luffy" three different times
// at different timestamps.

// Return the pattern with the largest score. 
// If there is more than one pattern with the
// same largest score, return the lexicographically
// smallest such pattern.

// Note that the websites in a pattern do not need
// to be visited contiguously, they only need to
// be visited in the order they appeared in the
// pattern.

// 3 <= username.length <= 50  
// 1 <= username[i].length <= 10  

// timestamp.length == username.length  
// 1 <= timestamp[i] <= 10^9  

// website.length == username.length  
// 1 <= website[i].length <= 10  

// username[i] and website[i] consist of 
// lowercase English letters.  

// It is guaranteed that at least one user 
// visited at least three websites.  

// All tuples [username[i], timestamp[i], 
// website[i]] are unique.
// Node represents one visit record
// (username, timestamp, website)
class Node {

    String name;       // user name
    int timestamp;     // visit time
    String website;    // website visited

    Node(String name, int timestamp, String website) {
        this.name = name;
        this.timestamp = timestamp;
        this.website = website;
    }
}


public List<String> mostVisitedPattern(
    String[] username,
    int[] timestamp,
    String[] website
) {
    // Step 1:
    // Combine the parallel arrays into a single list of Node objects
    // so we can easily sort them by timestamp.
    List<Node> nodes = new ArrayList<>();
    for (int i = 0; i < username.length; i++) {
        nodes.add(new Node(username[i], timestamp[i], website[i]));
    }
    // Step 2:
    // Sort all visits globally by timestamp to ensure chronological order.
    nodes.sort((a, b) -> a.timestamp - b.timestamp);

    // Step 3:
    // Group visits by user.
    // After sorting, each user's visits will already be in time order.
    Map<String, List<Node>> userVisits = new HashMap<>();
    for (Node node : nodes) {
        userVisits
            .computeIfAbsent(node.name, k -> new ArrayList<>())
            .add(node);
    }
    // Step 4:
    // Map to count how many users followed each 3-website pattern.
    Map<List<String>, Integer> route = new HashMap<>();
    // Iterate over each user's visit history
    for (List<Node> visits : userVisits.values()) {
        // Use a Set to avoid counting duplicate patterns
        // multiple times for the same user.
        Set<List<String>> tmp = new HashSet<>();

        // Generate all combinations of 3 websites (i < j < k)
        for (int i = 0; i < visits.size()-2; i++) {
            for (int j = i + 1; j < visits.size()-1; j++) {
                for (int k = j + 1; k < visits.size(); k++) {

                    List<String> path = Arrays.asList(
                        visits.get(i).website,
                        visits.get(j).website,
                        visits.get(k).website
                    );

                    tmp.add(path); // add unique pattern for this user
                }
            }
        }
        // Update global pattern counts (each user contributes at most 1)
        for (List<String> path : tmp) {
            route.put(path, route.getOrDefault(path, 0) + 1);
        }
    }
    // Step 5:
    // Find the pattern with:
    // 1. Highest frequency
    // 2. Lexicographically smallest if tie
    List<String> result = null;
    int max = -1;
    for (Map.Entry<List<String>, Integer> entry : route.entrySet()) {

        if (
            entry.getValue() > max ||
            (entry.getValue() == max &&
                entry.getKey().toString().compareTo(result.toString()) < 0)
        ) {
            max = entry.getValue();
            result = entry.getKey();
        }
    }

    return result;
}

//time:O(Nlogn+U∗K3)
//space:O(N+U∗K3)