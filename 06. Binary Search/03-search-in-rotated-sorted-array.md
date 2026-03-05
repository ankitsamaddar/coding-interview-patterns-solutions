# Search in Rotated Sorted Array

> Time Limit: 2s
> Space Limit: 256 MB
> Link: https://leetcode.com/problems/search-in-rotated-sorted-array/

## Description

Given an array of unique integers sorted in ascending order that has been rotated at an unknown pivot, write a function to search for a target value. If the target exists, return its index. If not, return -1.

**Example**:
Input: `nums = [8,9,1,2,3,4,5,6,7]`, `target = 1`
Output: `2`
Explanation: The array was originally sorted [1,2,3,4,5,6,7,8,9] and rotated at index 2. The target 1 is found at index 2.

**Constraints**:
- $1 \le \text{nums.length} \le 5000$
- All integers in `nums` are unique.
- The array is sorted in ascending order and rotated between 1 and `n` times.

**Code Template**:
```java
class Solution {
    public int search(int[] nums, int target) {
        // Your code here
    }
}
```

**Hint**: One half of the array is always sorted. Determine which half is sorted, then check if the target lies within that range to decide where to search next.

## Solution

<details>
<summary>Click to view the solution</summary>

**Code**:
```java
class Solution {
    // Stage 1: Direct Translation
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid;
            }

            // Check if the left subarray [left...mid] is sorted
            if (nums[left] <= nums[mid]) {
                // Target is in the sorted left range
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            // Otherwise, the right subarray [mid...right] must be sorted
            else {
                // Target is in the sorted right range
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }

        // Post-loop check for the final element
        return nums[left] == target ? left : -1;
    }
}

// Stage 2: Idiomatic Binary Search
class OptimizedSolution {
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid;
            }

            // If the left portion is sorted
            if (nums[left] <= nums[mid]) {
                if (target >= nums[left] && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            // If the right portion is sorted
            else {
                if (target > nums[mid] && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }

        return -1;
    }
}
```

> The Stage 2 solution uses the standard `left <= right` loop condition. This is more conventional for binary search problems because it handles the exit condition cleanly inside the loop, removing the need for a post-loop bounds check. It also guards against index out of bounds errors that might occur if the input array is empty, which the direct translation handles with an explicit check at the top.

**Approach**: Modified Binary Search.

**Intuition**:
The array is rotated, so it isn't fully sorted. This breaks the standard binary search assumption. However, if we pick a midpoint, we know that one of the two halves must be sorted because the rotation point can only be in one half. We can check which side is sorted and then determine if our target falls within that sorted range. If it does, we search there. If it doesn't, we search the other half. This logic allows us to discard half the search space in every step, maintaining logarithmic time complexity.

**Mathematical/Other Foundation**:
The core principle relies on the fact that a sorted array split into two parts will always have at least one part that remains sorted.
Let $n$ be the number of elements.
In each iteration, the search space is divided by 2.
The number of iterations $k$ satisfies $n/2^k = 1$.
Solving for $k$ yields $k = \log_2 n$.
Therefore, the time complexity is $O(\log n)$.

**Algorithm**:
1. Initialize `left` to 0 and `right` to `n-1`.
2. Loop while `left` <= `right`.
3. Calculate `mid`.
4. If `nums[mid]` equals the target, return `mid`.
5. Determine if the left half `[left, mid]` is sorted by checking if `nums[left] <= nums[mid]`.
6. If the left half is sorted:
   - Check if the target lies within this range (`nums[left] <= target < nums[mid]`).
   - If yes, search left (`right = mid - 1`).
   - If no, search right (`left = mid + 1`).
7. If the right half `[mid, right]` is sorted:
   - Check if the target lies within this range (`nums[mid] < target <= nums[right]`).
   - If yes, search right (`left = mid + 1`).
   - If no, search left (`right = mid - 1`).
8. If the loop finishes without finding, return -1.

**Complexity**:
- Time: $O(\log n)$ because we cut the search space in half each time.
- Space: $O(1)$ since we only use a few variables for pointers.

**Test Cases**:

| Input                | Output | Notes                       |
| -------------------- | ------ | --------------------------- |
| `[4,5,6,7,0,1,2], 0` | `4`    | Target in the rotated half  |
| `[4,5,6,7,0,1,2], 3` | `-1`   | Target not present          |
| `[1], 0`             | `-1`   | Single element, not found   |
| `[1,3], 3`           | `1`    | Two elements, target at end |
| `[5,1,3], 5`         | `0`    | Rotation at index 1         |

**Pro Tips**:
- The condition `nums[left] <= nums[mid]` implies the left side is sorted. We use `<=` because `left` and `mid` could be the same index. This works correctly because the problem guarantees unique elements. If duplicates existed, we could not distinguish sorted from unsorted this simply.
- Be careful with boundary checks. When checking if the target is in the sorted left range, use `target < nums[mid]` because we already checked `target == nums[mid]` in step 4.
</details>

## Solutions Link

- [[JAVA] Modified Binary Search.](solutions/_03_SearchInRotatedSortedArray_Solution01.java)
