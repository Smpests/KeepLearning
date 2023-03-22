package org.qingsong;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * 不在Leetcode的每题下写main运行了，全部写到这里
 */
public class AlgorithmAggregateTest {

    @Test
    public void q27_RemoveElementTest_Case_1() {
        Q27 q27 = new Q27();
        int result = q27.removeElement(new int[] {0,1,2,2,3,0,4,2}, 2);
        Assertions.assertEquals(5, result);
    }

    @Test
    public void q1626_BestTeamScoreTest_Case_1() {
        Q1626 q1626 = new Q1626();
        int result = q1626.bestTeamScore(new int[] {4, 5, 6, 5 }, new int[] {2, 1, 2, 1});
        Assertions.assertEquals(16, result);
    }
}
