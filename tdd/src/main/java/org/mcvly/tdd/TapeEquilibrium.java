package org.mcvly.tdd;

import java.util.Arrays;

public class TapeEquilibrium {

    int operations;

    public int solution(int A[]) {
        if (A == null || A.length < 2) throw new IllegalArgumentException("Array must be non empty");
        operations = 0;

        int[] x = sumsForward(A);
        int[] y = sumsBackward(A);

        return calculateMinSum(A.length, x, y);
    }

    private int calculateMinSum(int N, int[] x, int[] y) {
        int minSum = 100_000;

        for (int i = 0; i < N-1; i++) {
            operations++;
            int z = Math.abs(x[i] - y[i]);
            if (z < minSum) {
                operations++;
                minSum = z;
            }
        }

        return minSum;
    }

    int[] sumsForward(int[] x) {
        int[] sums = new int[x.length-1];
        sums[0] = x[0];
        operations++;
        for (int i = 1; i < x.length - 1; i++) {
            operations++;
            sums[i] = sums[i - 1] + x[i];
        }
        return sums;
    }

    int[] sumsBackward(int[] x) {
        int[] sums = new int[x.length];
        for (int i = x.length-2; i >= 0; i--) {
            operations++;
            sums[i] = sums[i + 1] + x[i+1];
        }
        return Arrays.copyOfRange(sums, 0, x.length-1);
    }


    private int originalVersion(int[] A, int N, int minSum) {
        for(int i = 1; i<N; i++) {
            operations++;
            int sumFirst = 0;
            for(int j=0; j<i;j++) {
                operations++;
                sumFirst += A[j];
            }
            int sumSecond = 0;
            for (int j=i; j<N; j++) {
                operations++;
                sumSecond += A[j];
            }
            if (Math.abs(sumFirst - sumSecond) < minSum) {
                operations++;
                minSum = Math.abs(sumFirst - sumSecond);
            }
        }
        return minSum;
    }

}
