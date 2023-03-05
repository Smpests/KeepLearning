/**
 * 你正在经营一座摩天轮，该摩天轮共有 4 个座舱 ，每个座舱 最多可以容纳 4 位游客 。
 * 你可以 逆时针 轮转座舱，但每次轮转都需要支付一定的运行成本 runningCost 。摩天轮每次轮转都恰好转动 1 / 4 周。
 * 给你一个长度为 n 的数组 customers ， customers[i] 是在第 i 次轮转（下标从 0 开始）之前到达的新游客的数量。
 * 这也意味着你必须在新游客到来前轮转 i 次。
 * 每位游客在登上离地面最近的座舱前都会支付登舱成本 boardingCost ，一旦该座舱再次抵达地面，他们就会离开座舱结束游玩。
 * 你可以随时停下摩天轮，即便是 在服务所有游客之前 。
 * 如果你决定停止运营摩天轮，为了保证所有游客安全着陆，将免费进行所有后续轮转 。
 * 注意，如果有超过 4 位游客在等摩天轮，那么只有 4 位游客可以登上摩天轮，其余的需要等待 下一次轮转 。
 * 返回最大化利润所需执行的 最小轮转次数 。 如果不存在利润为正的方案，则返回 -1 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/maximum-profit-of-operating-a-centennial-wheel
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class Q1599 {
    private final static int CABIN_CAPACITY = 4;

    /**
     * 贪心，每次轮转近地座舱坐满最优
     */
    public int minOperationsMaxProfit(int[] customers, int boardingCost, int runningCost) {
        if (customers.length == 0) {
            return 0;
        }
        // 如果每轮坐满都赚不到钱，直接返回-1
        if (boardingCost * CABIN_CAPACITY - runningCost < 0) {
            return -1;
        }
        int minOperations = 0;
        int profit = 0;
        // 等待中的游客
        int waitVisitors = 0;
        // 这时候要转才有游客，即至少要转customers.length次
        for (int customer: customers) {
            // 游客到达，等待队列增加
            waitVisitors += customer;
            // 如果有游客在等，等到能坐满才转
            if (waitVisitors > 0) {
                // 等待人数减去当前近地座舱能乘坐的人数
                int passengers = Math.min(CABIN_CAPACITY, waitVisitors);
                waitVisitors -= passengers;
                profit += passengers * boardingCost;
            }
            // 游客上座或当前座舱空舱，摩天轮开动
            minOperations++;
            profit -= runningCost;
        }
        // 无新游客，清空等待游客
        int rest = waitVisitors % CABIN_CAPACITY;
        int waitOperations = waitVisitors / CABIN_CAPACITY;
        if (rest * boardingCost - runningCost <= 0) {
            profit += (waitVisitors - rest) * boardingCost - waitOperations * runningCost;
        } else {
            if (rest > 0) {
                ++waitOperations;
            }
            profit += waitVisitors * boardingCost - waitOperations * runningCost;
        }
        // 没人在等，如果天上还有人，需要放下来，不收轮转费(理解题意要这么做)
        // 但题解并没有将这些操作次数放到最终结果里，因为达到最大收益时，最小操作次数已经定了
        // 后续操作不算在内
        return profit > 0 ? minOperations + waitOperations : -1;
    }


    public static void main(String[] args) {
        int[] customer = new int[] {10, 10, 6, 4, 7};
        System.out.println(new Q1599().minOperationsMaxProfit(customer, 3, 8));
    }
}
