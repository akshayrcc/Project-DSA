import unittest

class Solution:
    @staticmethod
    def solution(nums1, nums2):
        n = len(nums1)
        sums = []
        for t in range(n):
            shifted = nums1[t:] + nums1[:t]
            total = sum(abs(shifted[i] - nums2[i]) for i in range(n))
            sums.append(total)
        return sorted(sums)

class TestSolution(unittest.TestCase):
    def test_solution(self):
        nums1 = [1, 2, 3, 4, 5]
        nums2 = [2, 3, 4, 5, 1]
        expected_output = [0, 1, 2, 2, 3]
        self.assertEqual(Solution.solution(nums1, nums2), expected_output)

if __name__ == '__main__':
    unittest.main()
