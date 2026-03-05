// You are given a string s consisting only
// of lowercase English letters.
// In one move, you can select any two
// adjacent characters of s and swap them.
// Return the minimum number of moves needed
// to make s a palindrome.
// Note that the input will be generated such 
// that s can always be converted to a palindrome.

// Input: s = "aabb"
// Output: 2

// Input: s = "letelt"
// Output: 2

// 1 <= s.length <= 2000
// s consists only of lowercase English letters.
// s can be converted to a palindrome 
// using a finite number of moves.
class Solution {
    public int minMovesToMakePalindrome(String s) {
        
        char[] arr=s.toCharArray();
        int n=s.length();

        int moves=0;
        int l=0,r=n-1;
        //we compare elements at l and r
        //if arr[l]==arr[r] l++ r--
        //we set k to r and move back to find the
        //first element thats the same as arr[l]
        //if we dont find it,arr[l] is unique so
        // needs to be in the middle, we move it one step frw
        //if we find it, we need to swap step by step till k!=r
        while(l<r){
            if(arr[l]==arr[r]){
                l++;
                r--;
                continue;
            }

            int k=r;
            while(k>l && arr[k]!=arr[l]){
                k--;
            }

            if(k==l){
                swap(arr,l);//move element at index l one frw
                moves++;
            }
            else{
                while(k!=r){
                    swap(arr,k);
                    moves++;
                    k++;
                }
                l++;
                r--;
            }
        }

        return moves;
    }

    private void swap(char[] arr,int i){
        char temp=arr[i];
        arr[i]=arr[i+1];
        arr[i+1]=temp;
    }
}


// time:
// space:
