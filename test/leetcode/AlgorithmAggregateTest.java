package leetcode;

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
}
