package java8;


import java.util.concurrent.locks.StampedLock;

/**
 * StampedLock锁
 *
 */
public class Point {

    private double x , y;
    private final StampedLock sl = new StampedLock();

    void move(double deltaX, double deltaY) {
        long stamp = sl.writeLock();
        try {
            x+=deltaX;
            x+=deltaY;
        }finally {
            sl.unlockWrite(stamp);
        }
    }


    double distanceFromOrigin() {
        long stamp = sl.tryOptimisticRead();
        double currentX = x , currentY = y;
        if (!sl.validate(stamp)) {
            stamp = sl.readLock();
            try {
                currentX = x;
                currentY = y;
            }finally {
                sl.unlockRead(stamp);
            }
        }
        return Math.sqrt(currentX * currentX + currentY * currentY);
    }
}
