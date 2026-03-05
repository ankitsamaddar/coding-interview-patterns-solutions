# First and Last Occurrences of a Number

> Time Limit: 2s
> Space Limit: 256 MB
> Link: https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/

## Description

Given an array of integers sorted in non-decreasing order, find the starting and ending position of a given target value. If the target is not found in the array, return `[-1, -1]`.

**Example**:
Input: `nums = [1, 2, 3, 4, 4, 4, 5, 6, 7, 8, 9, 10, 11]`, `target = 4`
Output: `[3, 5]`
Explanation: The target value 4 appears first at index 3 and last at index 5.

**Constraints**:
- $0 \le \text{nums.length} \le 10^5$
- $-10^9 \le \text{nums[i]} \le 10^9$
- `nums` is sorted in non-decreasing order.

**Code Template**:
```java
import java.util.Arrays;

class Solution {
    public int[] searchRange(int[] nums, int target) {
        // Your code here
    }
}
```

**Hint**: A standard binary search finds a match, but you need to find the boundaries. Try running one binary search to find the first occurrence and a second one to find the last.

## Solution

<details>
<summary>Click to view the solution</summary>

**Code**:
```java
import java.util.Arrays;

class Solution {
    public int[] searchRange(int[] nums, int target) {
        if (nums.length == 0) {
            return new int[]{-1, -1};
        }

        int first = findFirst(nums, target);
        int last = findLast(nums, target);

        return new int[]{first, last};
    }

    // Stage 1: Direct Translation from editorial logic
    private int findFirst(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                // Found target, but we want the leftmost one
                right = mid;
            }
        }
        return nums[left] == target ? left : -1;
    }

    // Stage 2: Optimized for upper bound
    private int findLast(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            // Bias mid to the right to avoid infinite loops
            int mid = left + (right - left) / 2 + 1;

            if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                // Found target, but we want the rightmost one
                left = mid;
            }
        }
        return nums[right] == target ? right : -1;
    }
}
```

**Approach**: Modified Binary Search (Lower and Upper Bound).

**Intuition**:
Running a linear scan works, but it ignores the fact that the input is sorted. We should use binary search. However, a standard binary search stops as soon as it finds the target. We need the range. We can split the problem into finding the "lower bound" (first occurrence) and the "upper bound" (last occurrence). When the midpoint matches the target, instead of stopping, we narrow the search space to continue hunting for the boundaries.

**Mathematical/Other Foundation**:
The algorithm relies on reducing the search space by half each iteration.
For an array of size $n$, the number of steps $k$ required satisfies $n / 2^k = 1$.
Solving for $k$ gives $k = \log_2 n$.
Thus, the time complexity for one search is $O(\log n)$. Since we perform two searches, the total complexity remains $O(\log n)$.

**Algorithm**:
1. **Find First Occurrence (Lower Bound)**:
   - Perform binary search.
   - If `nums[mid] == target`, move `right` to `mid`. This keeps the target in the search space while forcing the window to shrink leftwards.
   - Loop until `left == right`.

2. **Find Last Occurrence (Upper Bound)**:
   - Perform binary search with a right-biased midpoint: `mid = (left + right) / 2 + 1`.
   - If `nums[mid] == target`, move `left` to `mid`. This forces the window to shrink rightwards.
   - The right bias is critical here. Without it, `left` could equal `mid` indefinitely when the window size is 2.

3. **Validation**: After the loops, verify if the element at the final index actually equals the target. If not, return -1.

**Complexity**:
- Time: $O(\log n)$, where $n$ is the length of the array. We run binary search twice.
- Space: $O(1)$, as we only store a few pointer variables.

**Test Cases**:

| Input                | Output     | Notes                          |
| -------------------- | ---------- | ------------------------------ |
| `[1,2,3,4,4,4,5], 4` | `[3, 5]`   | Standard case with duplicates  |
| `[1,2,3,4,5], 3`     | `[2, 2]`   | Single occurrence              |
| `[1,2,3,4,5], 6`     | `[-1, -1]` | Target not found               |
| `[], 0`              | `[-1, -1]` | Empty array edge case          |
| `[2,2], 2`           | `[0, 1]`   | Two element infinite loop trap |

**Pro Tips**:
- The infinite loop in the upper-bound search is the most common bug. When `left` and `right` are adjacent, a standard `mid` calculation leans left. If you set `left = mid`, the window never shrinks. Always bias `mid` to the right when searching for the upper bound.
- Test your implementation with a two-element array like `[2, 2]`. This is the quickest way to verify your upper-bound logic handles the infinite loop trap.
</details>

## Solutions Link

- [[JAVA] Modified Binary Search (Lower and Upper Bound).](solutions/_02_FirstAndLastOccurrencesOfANumber_Solution01.java)
