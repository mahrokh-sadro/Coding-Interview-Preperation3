// Given an array of strings strs, group the
// together. You can return the answer in any order.

// Input: strs = ["eat","tea","tan","ate","nat","bat"]
// Output: [["bat"],["nat","tan"],["ate","eat","tea"]]

// 1 <= strs.length <= 10^4
// 0 <= strs[i].length <= 100
// strs[i] consists of lowercase English letters.

public List<List<String>> groupAnagrams(String[] strs) {
    Map<String,List<String>> map=new HashMap<>();
    for(String s:strs){
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }

        String key = Arrays.toString(count);
        if(map.containsKey(key)){
           map.get(key).add(s);
        }
        else{
           map.put(key,new ArrayList<>());
           map.get(key).add(s);
        }
    }

    return new ArrayList<>(map.values());
}

// Time: O(nk)
// Space:O(nk)

// n = number of strings
// k = average length of each string
