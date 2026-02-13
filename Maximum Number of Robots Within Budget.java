// You have n robots. You are given two 
// 0-indexed integer arrays, chargeTimes
// and runningCosts, both of length n. 
// The ith robot costs chargeTimes[i] 
// units to charge and costs runningCosts[i]
// units to run. You are also given an 
// integer budget.

// The total cost of running k chosen robots
// is equal to max(chargeTimes) + k * sum(runningCosts),
// where max(chargeTimes) is the largest charge
// cost among the k robots and sum(runningCosts)
// is the sum of running costs among the k robots.

// Return the maximum number of consecutive
// robots you can run such that the total 
// cost does not exceed budget.

// chargeTimes.length == runningCosts.length == n
// 1 <= n <= 5 * 10^4
// 1 <= chargeTimes[i], runningCosts[i] <= 10^5
// 1 <= budget <= 10^15

public int maximumRobots(int[] chargeTimes, int[] runningCosts, long budget) {

    int n = chargeTimes.length;

    // This deque will store indices of robots.
    // It will maintain chargeTimes in DECREASING order.
    // The front of the deque will always hold the index
    // of the maximum chargeTime in the current window.
    Deque<Integer> deque = new ArrayDeque<>();

    // runningSum stores sum of runningCosts in current window
    long runningSum = 0;

    int left = 0;          // left pointer of sliding window
    int maxRobots = 0;     // result (maximum valid window size)

    for (int right = 0; right < n; right++) {

        // 1️⃣ Add the new robot's running cost
        runningSum += runningCosts[right];

        // 2️⃣ Maintain monotonic decreasing deque
        // Remove all smaller chargeTimes from the back
        // because they can never become maximum while
        // the current robot is inside the window.
        while (!deque.isEmpty() &&
               chargeTimes[deque.peekLast()] <= chargeTimes[right]) {
            deque.pollLast();
        }

        // Add current index to deque
        deque.offerLast(right);

        // 3️⃣ Check if current window exceeds budget
        // Cost formula:
        // max(chargeTimes in window)
        // + windowSize * sum(runningCosts in window)
        //
        // max chargeTime = chargeTimes[deque.peekFirst()]
        // window size = (right - left + 1)
        //
        while (!deque.isEmpty() &&
               chargeTimes[deque.peekFirst()] +
               (long)(right - left + 1) * runningSum > budget) {

            // If the element going out of window
            // is the current maximum, remove it
            if (deque.peekFirst() == left) {
                deque.pollFirst();
            }

            // Remove running cost of left robot
            runningSum -= runningCosts[left];

            // Shrink window
            left++;
        }

        // 4️⃣ Update answer with current valid window size
            maxRobots = Math.max(maxRobots, right - left + 1);
        }

        return maxRobots;
    }

