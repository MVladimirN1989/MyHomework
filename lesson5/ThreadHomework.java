package lesson5;

import java.util.Arrays;

public class ThreadHomework {
    static final int size = 10000000;
    static final int half = size / 2;
    float[] arr = new float[size];

    public void process() {
        long startTime = System.currentTimeMillis();

        Arrays.fill(arr, 1);
        float a1[] = new float[half];
        float a2[] = new float[half];

        System.arraycopy(arr, 0, a1, 0, half);
        System.arraycopy(arr, half, a2, 0, half);

        new Thread(() -> {
            synchronized (a1) {
                calcVal(a1);
            }
        }).start();

        new Thread(() -> {
            synchronized (a2) {
                calcVal(a2);
            }
        }).start();
        complete(a1, a2, startTime);
    }

    private void complete(float[] a1, float[] a2, long startTime) {

        synchronized (a1) {
            synchronized (a2) {
                System.arraycopy(a1, 0, arr, 0, half);
                System.arraycopy(a2, 0, arr, half, half);
                long endTime = System.currentTimeMillis() - startTime;
                System.out.println("Need time " + endTime);
            }
        }
    }

    private void calcVal(float[] val) {
        for (int i = 0; i < val.length; i++) {
            val[i] = calc(val[i], i);
        }
    }

    private float calc(float val, int incr) {
        return (float) (val * Math.sin(0.2f + incr / 5) * Math.cos(0.2f + incr / 5) * Math.cos(0.4f + incr / 2));
    }
}