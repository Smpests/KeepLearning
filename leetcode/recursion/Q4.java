package recursion;

import java.util.Arrays;

/**
 * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
 * 算法的时间复杂度应该为 O(log (m+n)) 。
 *
 * 示例 1：
 *
 * 输入：nums1 = [1,3], nums2 = [2]
 * 输出：2.00000
 * 解释：合并数组 = [1,2,3] ，中位数 2
 * 示例 2：
 *
 * 输入：nums1 = [1,2], nums2 = [3,4]
 * 输出：2.50000
 * 解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/median-of-two-sorted-arrays
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class Q4 {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int totalLength = nums1.length + nums2.length;
        // 中位数位置索引，偶数两个位置，奇数一个位置
        // 中位数: 奇数为X（N+1/2），偶数(X(N/2) + X(N/2+1))/2
        if (totalLength % 2 == 0) {
            // 解决有一边为空数组的情况
            if (nums1.length == 0) {
                return (nums2[totalLength / 2 - 1] + nums2[totalLength / 2]) / 2.0;
            }
            if (nums2.length == 0) {
                return (nums1[totalLength / 2 - 1] + nums1[totalLength / 2]) / 2.0;
            }
            return (findKth(nums1, nums2, totalLength / 2) + findKth(nums1, nums2, totalLength / 2 + 1)) / 2.0;
        }
        else {
            if (nums1.length == 0) {
                return nums2[(totalLength + 1) / 2 - 1];
            }
            if (nums2.length == 0) {
                return nums1[(totalLength + 1) / 2 - 1];
            }
            return findKth(nums1, nums2, (totalLength + 1) / 2);
        }
    }

    /**
     * 找第K小，给定两个有序数组，给定一个位置索引，
     * 排除该位置处两数组较小的一方及其前方的数，返回
     * 如[1, 5]和[3, 4, 6]，索引3，3 / 2 = 1(取整)，
     * 比值索引1-1=0，1比3小，返回[5]和[3, 4, 6]
     * 时间复杂度: O(log(m+n))，m、n为两数组长度
    */
    private int findKth(int[] subNums1, int[] subNums2, int k) {
        if (k == 1) {
            return Math.min(subNums1[0], subNums2[0]);
        }
        int compareIndex = k / 2 - 1;
        int subNums1Index = Math.min(subNums1.length - 1, compareIndex);
        int subNums2Index = Math.min(subNums2.length - 1, compareIndex);
        if (subNums1[subNums1Index] > subNums2[subNums2Index]) {
            // subNums2没数了
            if (subNums2Index >= subNums2.length - 1) {
                return subNums1[k - subNums2Index - 2];
            }
            // Arrays.copyOfRange()比较耗时，通过传入数组边界直接在原数组上取值更快
            return findKth(subNums1, Arrays.copyOfRange(subNums2, subNums2Index + 1, subNums2.length), k - subNums2Index - 1);
        } else {
            // subNums1没数了
            if (subNums1Index >= subNums1.length - 1) {
                return subNums2[k - subNums1Index - 2];
            }
            return findKth(Arrays.copyOfRange(subNums1, subNums1Index + 1, subNums1.length), subNums2, k - subNums1Index - 1);
        }
    }

    // 时间复杂度O((m+n)log(m+n))
    // public double findMedianSortedArrays(int[] nums1, int[] nums2) {
    // 合并数组
    // int[] total = new int[nums1.length + nums2.length];
    // System.arraycopy(nums1, 0, total, 0, nums1.length);
    // System.arraycopy(nums2, 0, total, nums1.length, nums2.length);
    // // total快速排序
    // quickSort(total, 0, total.length - 1);
    // // 偶数计算中间两个数的平均值
    // if (total.length % 2 == 0) {
    //     return (total[total.length / 2 - 1] + total[total.length / 2]) / 2.0;
    // } else {
    //     return total[(total.length + 1) / 2 - 1];
    // }
    // }

    // 快排方法
    // private void quickSort(int[] arr,int low,int high) {
    //     int p,i,j,temp;
    //     if(low >= high) {
    //         return;
    //     }
    //     //p就是基准数,这里就是每个数组的第一个
    //     p = arr[low];
    //     i = low;
    //     j = high;
    //     while(i < j) {
    //         //右边当发现小于p的值时停止循环
    //         while(arr[j] >= p && i < j) {
    //             j--;
    //         }

    //         //这里一定是右边开始，上下这两个循环不能调换（下面有解析，可以先想想）

    //         //左边当发现大于p的值时停止循环
    //         while(arr[i] <= p && i < j) {
    //             i++;
    //         }

    //             temp = arr[j];
    //             arr[j] = arr[i];
    //             arr[i] = temp;
    //     }
    //     arr[low] = arr[i];//这里的arr[i]一定是停小于p的，经过i、j交换后i处的值一定是小于p的(j先走)
    //     arr[i] = p;
    //     quickSort(arr,low,j-1);  //对左边快排
    //     quickSort(arr,j+1,high); //对右边快排
    // }
}
