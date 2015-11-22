package org.mcvly.tdd;

import org.hamcrest.core.IsEqual;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;

public class TapeEquilibriumTest {

    private TapeEquilibrium testObject;
    @Before
    public void setUp() throws Exception {
        testObject = new TapeEquilibrium();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyArrayShouldFail() throws Exception {
        testObject.solution(new int[] {});
    }

    @Test(expected = IllegalArgumentException.class)
    public void testOneElementShouldFail() throws Exception {
        testObject.solution(new int[] {1});
    }

    @Test
    public void testTwoElementsArray() throws Exception {
        int[] x = {1,2};
        assertEquals(1, testObject.solution(x));
    }

    @Test
    public void testThreeElementsArray() throws Exception {
        int[] x = {1,2,3};
        assertEquals(0, testObject.solution(x));
    }

    @Test
    public void testReferenceExample() {
        int[] x = {3, 1, 2, 4, 3};
        assertEquals(1, testObject.solution(x));
    }

    @Test
    public void testWorkWithNegative() {
        int[] x = {-1, -2, 2 ,1};
        assertEquals(2, testObject.solution(x));
        x = new int[]{-1, -1, -1, -1, -1};
        assertEquals(1, testObject.solution(x));
    }

    @Test
    public void calculateSumForward() {
        int[] x = {3, 1, 2, 4, 3};
        int[] sums = {3, 4, 6, 10};

        assertThat(testObject.sumsForward(x), is(IsEqual.equalTo(sums)));
    }

    @Test
    public void calculateSumBackward() {
        int[] x = {3, 1, 2, 4, 3};
        int[] sumsBackwards = {10, 9, 7 ,3};

        assertThat(testObject.sumsBackward(x), is(IsEqual.equalTo(sumsBackwards)));
    }



    @Test
    public void testComplexityIsLinear() throws Exception {
        int n = 100_000;
        int factor = 3;
        int a[] = new int[n];
        testObject.solution(a);
        assertThat(testObject.operations, is(lessThan(factor * n)));
    }
}