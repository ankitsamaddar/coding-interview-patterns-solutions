# Find the Insertion Index

> Time Limit: 1s
> Space Limit: 256 MB
> Link: [LeetCode 35. Search Insert Position](https://leetcode.com/problems/search-insert-position/)


## Description

You have a sorted array of unique integers and a target value. Return the index of the target if it exists. If it does not, return the index where it would be inserted to maintain sorted order.

**Example**:
```
Input: nums = [1,2,4,5,7,8,9], target = 4
Output: 2
Explanation: The target 4 exists at index 2.
```
```
Input: nums = [1,2,4,5,7,8,9], target = 6
Output: 4
Explanation: 6 is not in the array. It should be inserted at index 4, between 5 and 7.
```

**Constraints**:
- $1 \leq \text{{nums.length}} \leq 10^4$
- $-10^4 \leq \text{{nums[i]}} \leq 10^4$
- `nums` contains distinct values sorted in ascending order.
- $-10^4 \leq \text{{target}} \leq 10^4$

**Code Template**:
```java
class Solution {{
    public int searchInsert(int[] nums, int target) {{
        // Implementation goes here
    }}
}}
```

**Hint**: Look for the first element that is greater than or equal to the target.

## Solution

<details>
<summary>Click to view the solution</summary>

**Code (Stage 1: Direct Translation)**:
```java
class Solution {{
    public int searchInsert(int[] nums, int target) {{
        int left = 0;
        int right = nums.length;

        while (left < right) {{
            int mid = left + (right - left) / 2;

            if (nums[mid] >= target) {{
                // The lower bound is at mid or to the left.
                right = mid;
            }} else {{
                // The target is to the right of mid.
                left = mid + 1;
            }}
        }}

        return left;
    }}
}}
```

**Code (Stage 2: Idiomatic)**:
```java
import java.util.Arrays;

class Solution {{
    public int searchInsert(int[] nums, int target) {{
        int index = Arrays.binarySearch(nums, target);

        if (index >= 0) {{
            return index;
        }}

        // If not found, binarySearch returns (-(insertion point) - 1)
        return -(index + 1);
    }}
}}
```

> The idiomatic solution uses the Java standard library. `Arrays.binarySearch` is highly optimized and reduces the risk of implementation errors. While the manual implementation is essential for understanding the algorithm, the library method is preferred for production code due to its reliability and brevity.

**Approach**: Lower Bound Binary Search.

**Intuition**:
I find it helpful to view this problem as a boundary search rather than a simple value lookup. We are not just asking "Is this value here?" We are asking "Where does this value belong?". This translates to finding the first element that is greater than or equal to the target. If the target exists, this logic finds its index. If the target is missing, this logic finds the insertion point immediately before the first larger element.

**Mathematical/Other Foundation**:
The algorithm halves the search space with every step. The recurrence relation is $T(n) = T(n/2) + O(1)$. Solving this yields a time complexity of $O(\log n)$.

A subtle mathematical detail involves the search space. We define the range as $[0, n]$ rather than $[0, n-1]$. This inclusion of $n$ allows us to handle the case where the target is larger than all existing elements. In that scenario, the insertion index is exactly $n$.

**Algorithm**:
1. Initialize `left` to 0 and `right` to `nums.length`.
2. Loop while `left` is less than `right`.
3. Calculate `mid` using overflow-safe addition.
4. If `nums[mid]` is greater than or equal to the target, we know the boundary exists at `mid` or to the left. We set `right = mid`.
5. If `nums[mid]` is smaller than the target, the boundary must be to the right. We set `left = mid + 1`.
6. When the loop finishes, `left` and `right` converge. Return `left`.

**Complexity**:
- Time: $O(\log n)$ because we divide the search interval in half during each iteration.
- Space: $O(1)$ since we only use a few variables for pointers.

**Test Cases**:

| Input | Output | Notes |
|-------|--------|-------|
| `[1,3,5,6]`, 5 | 2 | Target found. |
| `[1,3,5,6]`, 2 | 1 | Insert between 1 and 3. |
| `[1,3,5,6]`, 7 | 4 | Insert at the end. |
| `[1,3,5,6]`, 0 | 0 | Insert at the start. |

**Pro Tips**:
- Use `left + (right - left) / 2` for calculating the midpoint. It prevents integer overflow that can happen with `(left + right) / 2` on massive arrays.
- Be careful with the `right` initialization. Setting it to `nums.length` instead of `nums.length - 1` is the key to handling insertion at the end of the array.
</details>

## Solutions Link

- [[JAVA] Lower Bound Binary Search.](solutions/_01_FindTheInsertionIndex_Solution01.java)
