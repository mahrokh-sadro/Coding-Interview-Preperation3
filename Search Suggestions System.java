// You are given an array of strings products 
// and a string searchWord.
// Design a system that suggests at most 
// three product names from products after
// each character of searchWord is typed. 
// Suggested products should have common 
// prefix with searchWord. If there are more
// than three products with a common prefix
// return the three lexicographically minimums products.

// Return a list of lists of the suggested
// products after each character of
// searchWord is typed.

// 1 <= products.length <= 1000
// 1 <= products[i].length <= 3000
// 1 <= sum(products[i].length) <= 2 * 10^4
// All the strings of products are unique.
// products[i] consists of lowercase English letters.
// 1 <= searchWord.length <= 1000
// searchWord consists of lowercase English letters.
class Solution {
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        List<List<String>> list=new ArrayList<>();
        Arrays.sort(products);
        int n=products.length;
        for(int i=0;i<searchWord.length();i++){
           String prefix=searchWord.substring(0,i+1);
           int l=0;
           int r=n-1;
           int index=-1;
           List<String> sublist=new ArrayList<>();
           while(l<=r){
             int mid=l+(r-l)/2;
             if(products[mid].compareTo(prefix) >= 0){
               index=mid;
               r=mid-1;
             }
             else {
               l=mid+1;
             }
           }
//after binary search we are sure
//we found the smallest word that is ≥ prefix      
           if(index!=-1){
              for(int j=index;j<index+3 && j< n;j++){
                if(products[j].startsWith(prefix)){
                    sublist.add(products[j]);
                }
              }  
           }
           list.add(new ArrayList<>(sublist));
        }
        return list;
    }

}


//time:O(NLogN+M∗N)
//space:O(M)
