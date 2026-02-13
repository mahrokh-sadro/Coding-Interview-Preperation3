// You are given a non-negative integer
// array nums. In one operation, you must:
// Choose a positive integer x such 
// that x is less than or equal to the
// smallest non-zero element in nums.
// Subtract x from every positive element in nums.
// Return the minimum number of operations
// to make every element in nums equal to 0

// 1 <= nums.length <= 100
// 0 <= nums[i] <= 100
class Solution {
    public int minimumOperations(int[] nums) {
        Set<Integer> set=new HashSet<>();
        for(int num:nums){
            if(num>0){
                set.add(num);
            }
        }

        return set.size();
    }

    //x<=min(nums) && !=0
    //num - x  
    //1,5,0,3,5
    //0,1,3,5,5 -->1
    //0,0,2,4,4 -->2
    //0,0,0,2,2-->2
    //0,0,0,0,0
    //numbers of distinc numbers that are not 0
}

// Time: O(n)
// Space: O(n)