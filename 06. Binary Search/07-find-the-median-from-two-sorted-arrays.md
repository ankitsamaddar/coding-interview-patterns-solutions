# Find the Median From Two Sorted Arrays

> Time Limit: 2s
> Space Limit: 256 MB
> Link: [https://leetcode.com/problems/median-of-two-sorted-arrays/](https://leetcode.com/problems/median-of-two-sorted-arrays/)

## Description

You are given two sorted integer arrays. Your task is to find their median value as if they were merged into a single sorted sequence. The overall run time complexity should be $O(\log (m+n))$.

**Example**:
Input: nums1 = [1, 3], nums2 = [2]
Output: 2.0
Explanation: merged array = [1, 2, 3] and median is 2.0.

**Constraints**:
- `nums1.length == m`, `nums2.length == n`
- `0 <= m <= 1000`, `0 <= n <= 1000`
- `1 <= m + n <= 2000`
- `-10^6 <= nums1[i], nums2[i] <= 10^6`

**Code Template**:
```java
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // Implementation goes here
    }
}
```

**Hint**: Try to partition the two arrays into left and right halves such that every element in the left half is less than or equal to every element in the right half.

## Solution

<details>
<summary>Click to view the solution</summary>

**Code**:
```java
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // Optimization: Ensure nums1 is the smaller array to minimize search space.
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int m = nums1.length;
        int n = nums2.length;
        int totalLen = m + n;
        int halfTotalLen = totalLen / 2;

        int left = 0;
        int right = m;

        while (left <= right) {
            // Partition point for nums1 (count of elements in left half)
            int i = left + (right - left) / 2;
            // Partition point for nums2 (derived from halfTotalLen)
            int j = halfTotalLen - i;

            // Handle boundary cases with infinity values
            // L1 and L2 are the largest values in the left partitions
            // R1 and R2 are the smallest values in the right partitions
            int L1 = (i > 0) ? nums1[i - 1] : Integer.MIN_VALUE;
            int R1 = (i < m) ? nums1[i] : Integer.MAX_VALUE;
            int L2 = (j > 0) ? nums2[j - 1] : Integer.MIN_VALUE;
            int R2 = (j < n) ? nums2[j] : Integer.MAX_VALUE;

            // Check if we found the correct slice
            if (L1 <= R2 && L2 <= R1) {
                if (totalLen % 2 != 0) {
                    // Odd length: median is the smallest on the right
                    return (double) Math.min(R1, R2);
                } else {
                    // Even length: average of max(left) and min(right)
                    return (Math.max(L1, L2) + Math.min(R1, R2)) / 2.0;
                }
            } else if (L1 > R2) {
                // L1 is too big, move the partition left
                right = i - 1;
            } else {
                // L2 is too big (R1 is too small), move the partition right
                left = i + 1;
            }
        }
        return 0.0;
    }
}
```

**Approach**: Binary Search on Partitions.

**Intuition**:
The goal is to "slice" both arrays into a left partition and a right partition such that the total number of elements on the left equals the total on the right (or differs by one). When the **correct slice** is found, every element in the combined left partition is less than or equal to every element in the combined right partition.

To verify this, we look at four boundary values:
*   **L1**: The last element in the left partition of `nums1`.
*   **R1**: The first element in the right partition of `nums1`.
*   **L2**: The last element in the left partition of `nums2`.
*   **R2**: The first element in the right partition of `nums2`.

Since the arrays are sorted, we only need to check cross-comparisons. The slice is correct if **$L1 \leq R2$** and **$L2 \leq R1$**.

**Mathematical/Other Foundation**:

We binary search for the partition index $i$ in the smaller array `nums1`. The partition index $j$ in `nums2` is calculated as $j = \text{halfTotalLen} - i$.
The correctness condition is:
$$ \text{nums1}[i-1] \le \text{nums2}[j] \quad \text{and} \quad \text{nums2}[j-1] \le \text{nums1}[i] $$
If the total length is odd, the median is $\min(\text{nums1}[i], \text{nums2}[j])$.
If the total length is even, the median is $\frac{\max(\text{nums1}[i-1], \text{nums2}[j-1]) + \min(\text{nums1}[i], \text{nums2}[j])}{2}$.

**Algorithm**:
1.  Ensure `nums1` is the smaller array. Initialize `left = 0` and `right = m`.
2.  Loop while `left <= right`. (ans might be exactly at boundary)
    *   Calculate partition `i` for `nums1` using binary search.
    *   Calculate partition `j` for `nums2` as `halfTotalLen - i`.
    *   Determine values `L1`, `R1`, `L2`, `R2`, handling out-of-bounds indices with $\pm \infty$.
3.  Check the slice conditions:
    *   If $L1 > R2$: The partition in `nums1` is too far right. Move `right = i - 1`.
    *   If $L2 > R1$: The partition in `nums1` is too far left. Move `left = i + 1`.
    *   If valid: Return the median based on the parity of the total length.

**Complexity**:
- Time: $O(\log(\min(m, n)))$. We perform binary search on the smaller of the two arrays.
- Space: $O(1)$. We use a constant amount of extra space.

**Test Cases**:

| Input | Output | Notes |
|-------|--------|-------|
| nums1=[1,3], nums2=[2] | 2.0 | Odd total length. |
| nums1=[1,2], nums2=[3,4] | 2.5 | Even total length. |
| nums1=[], nums2=[1] | 1.0 | One empty array. |
| nums1=[3,4], nums2=[1,2] | 2.5 | `nums1` initially larger. |

**Pro Tips**:
- Handling edge cases where a partition is empty is crucial. Setting out-of-bounds values to `Integer.MIN_VALUE` and `Integer.MAX_VALUE` simplifies the comparison logic significantly.
- Always calculating $j$ based on $i$ ensures the left partition size remains constant, allowing us to focus solely on optimizing the boundary values.
</details>

## Solutions Link

- [[JAVA] Binary Search on Partitions.](solutions/_07_FindTheMedianFromTwoSortedArrays_Solution01.java)
