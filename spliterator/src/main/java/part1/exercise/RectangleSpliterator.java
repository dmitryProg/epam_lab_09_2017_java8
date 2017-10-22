package part1.exercise;

import java.util.Arrays;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.IntConsumer;

public class RectangleSpliterator extends Spliterators.AbstractIntSpliterator {

    private final int[][] array;
    private final long endExclusive;
    private long startInclusive;

    public RectangleSpliterator(int[][] array) {
//        super(checkArrayAndCalcEstimatedSize(array), 0);
//       super(estimatedSize, Spliterator.IMMUTABLE
//                          | Spliterator.ORDERED
//                          | Spliterator.SIZED
//                          | Spliterator.SUBSIZED
//                          | Spliterator.NONNULL);
        this(array, 0, checkArrayAndCalcEstimatedSize(array));
    }

    private RectangleSpliterator(int[][] array, long startInclusive, long endExclusive) {
        super(endExclusive - startInclusive, Spliterator.IMMUTABLE
                | Spliterator.ORDERED
                | Spliterator.SIZED
                | Spliterator.SUBSIZED
                | Spliterator.NONNULL);
        this.startInclusive = startInclusive;
        this.endExclusive = endExclusive;
        this.array = array;
    }

    private static long checkArrayAndCalcEstimatedSize(int[][] array) {
        if (!Arrays.stream(array).allMatch(ar -> ar.length == array[0].length)) {
            throw new IllegalStateException("Not rectangle!");
        }
        return array.length * array[0].length;
    }

    @Override
    public OfInt trySplit() {
        long length = endExclusive - startInclusive;
        if (length < 2) {
            return null;
        }

        long midValue = startInclusive + length / 2;
        RectangleSpliterator result = new RectangleSpliterator(array, startInclusive, midValue);
        startInclusive = midValue;
        return result;
    }

    @Override
    public long estimateSize() {
        return endExclusive - startInclusive;
    }

    @Override
    public boolean tryAdvance(IntConsumer action) {
        if (startInclusive < endExclusive) {
            int value = array[(int) (startInclusive / array[0].length)][(int) (startInclusive % array[0].length)];
            ++startInclusive;
            action.accept(value);
            return true;
        }

        return false;
    }
}

class A {

    protected String val;

    A() {
        setVal();
    }

    public void setVal() {
        val = "A";
    }
}

class B extends A {

    @Override
    public void setVal() {
        val = "B";
    }

    public static void main(String[] args) {
        System.out.println(new B().val);

    }
}