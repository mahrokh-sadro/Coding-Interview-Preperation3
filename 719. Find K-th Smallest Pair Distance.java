// The distance of a pair of integers a
// and b is defined as the absolute 
// difference between a and b.

// Given an integer array nums and an
// integer k, return the kth smallest 
// distance among all the pairs nums[i] 
// and nums[j] where 0 <= i < j < nums.length.

// Input: nums = [1,3,1], k = 1
// Output: 0
// Explanation: Here are all the pairs:
// (1,3) -> 2
// (1,1) -> 0
// (3,1) -> 2
// Then the 1st smallest distance pair 
// is (1,1), and its distance is 0.

// Input: nums = [1,1,1], k = 2
// Output: 0

// Input: nums = [1,6,1], k = 3
// Output: 5

// n == nums.length
// 2 <= n <= 10^4
// 0 <= nums[i] <= 10^6
// 1 <= k <= n * (n - 1) / 2

public int smallestDistancePair(int[] nums, int k) {
    Arrays.sort(nums); // Step 1: sort the array
    int n = nums.length;
// Minimum possible distance
    int low = 0; 
// Maximum possible distance
    int high = nums[n - 1] - nums[0]; 
// Binary search on distance
    while (low < high) {
        int mid = low + (high - low) / 2;
// Count how many pairs have distance <= mid
        int count = countPairs(nums, mid);
        if (count < k) {
// Too few pairs, need larger distance
            low = mid + 1;
        } 
        else {
// Enough pairs, try smaller distance
            high = mid;
        }
    }
// At the end, lo == hi, which is the
//k-th smallest distance
    return low;
}

// Helper function: counts number of
//pairs with distance <= target
private int countPairs(int[] nums, int target) {
    int count = 0;
    int n = nums.length;
    int left = 0;

    for (int right = 0; right < n; right++) {
// Move left pointer until distance <= target
        while (nums[right] - nums[left] > target) {
            left++;
        }
// All pairs between left and right
//have distance <= target
        count += right - left;
    }
    return count;
}

//time:O(nlogn+nlogW)
//space:O(1)

//W = max(nums) - min(nums)
