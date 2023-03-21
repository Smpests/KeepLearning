package org.qingsong;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

public class CheckPalindromeFormationTest {

    @Spy
    Q1616 q1616 = new Q1616();

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void checkPalindromeFormation_ThenReturnFalse_CaseA() {
        Assertions.assertFalse(q1616.checkPalindromeFormation("abda", "acmc"));
    }

    @Test
    public void checkPalindromeFormation_ThenReturnFalse_CaseB() {
        Assertions.assertTrue(q1616.checkPalindromeFormation("pvhmupgqeltozftlmfjjde", "yjgpzbezspnnpszebzmhvp"));
    }
}
