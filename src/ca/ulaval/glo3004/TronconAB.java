package ca.ulaval.glo3004;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TronconAB {
  public static final Lock lockAB = new ReentrantLock();
  private static int nextTrainToEnterA = 0;
  private static int nextTrainToExitA = 0;
  private static int nextTrainToEnterB = 0;
  private static int nextTrainToExitB = 0;

  private static boolean canEnter(Train train) {
    if (train.getClass().getSimpleName().equals("TrainA")) {
      if (nextTrainToEnterB == nextTrainToExitB) {
        if (nextTrainToEnterA == train.getId()) {
          System.out.println("Train A-" + train.getId() + " entre dans le tronconAB");
          return true;
        }
      }
    } else {
      if (nextTrainToEnterA == nextTrainToExitA) {
        if (nextTrainToEnterB == train.getId()) {
          System.out.println("Train B-" + train.getId() + " entre dans le tronconAB");
          return true;
        }
      }
    }
    return false;
  }

  public static boolean canExit(Train train) {
    if (train.getClass().getSimpleName().equals("TrainA")) {
      if (nextTrainToExitA == train.getId()) {
        System.out.println("Train A-" + train.getId() + " sort du tronconAB");
        return true;
      }
    } else {
      if (nextTrainToExitB == train.getId()) {
        System.out.println("Train B-" + train.getId() + " sort du tronconAB");
        return true;
      }
    }
    return false;
  }

  public static void addTrainA(Train train) throws InterruptedException {
    boolean canEnter = false;
    while (!canEnter) {
      lock();
      canEnter = canEnter(train);
      if (canEnter) {
        nextTrainToEnterA++;
      }
      lockAB.unlock();
    }
  }

  public static void removeTrainA(Train train) {
    boolean canExit = false;
    while (!canExit) {
      lock();
      canExit = canExit(train);
      if (canExit) {
        nextTrainToExitA++;
      }
      lockAB.unlock();
    }
  }

  public static void addTrainB(Train train) throws InterruptedException {
    boolean canEnter = false;
    while (!canEnter) {
      lock();
      canEnter = canEnter(train);
      if (canEnter) {
        nextTrainToEnterB++;
      }
      lockAB.unlock();
    }
  }

  public static void removeTrainB(Train train) {
    boolean canExit = false;
    while (!canExit) {
      lock();
      canExit = canExit(train);
      if (canExit) {
        nextTrainToExitB++;
      }
      lockAB.unlock();
    }
  }

  static synchronized void lock() {
    while (!lockAB.tryLock()) {
    }
  }
}
