package ca.ulaval.glo3004;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TronconABC {
  public static final Lock lockABC = new ReentrantLock();
  static private int nextTrainToEnterA = 0;
  static private int nextTrainToExitA = 0;
  static private int nextTrainToEnterB = 0;
  static private int nextTrainToExitB = 0;
  static private int nextTrainToEnterC = 0;
  static private int nextTrainToExitC = 0;

  private static boolean canEnter(Train train) {
    if (train.getClass().getSimpleName().equals("TrainA")) {
      if (nextTrainToEnterB == nextTrainToExitB && nextTrainToEnterC == nextTrainToExitC) {
        if (nextTrainToEnterA == train.getId()) {
          System.out.println("TrainA - " + train.getId() + " entre dans le tronconABC");
          return true;
        }
      }
    } else if (train.getClass().getSimpleName().equals("TrainB")) {
      if (nextTrainToEnterA == nextTrainToExitA && nextTrainToEnterC == nextTrainToExitC) {
        if (nextTrainToEnterB == train.getId()) {
          System.out.println("TrainB - " + train.getId() + " entre dans le tronconABC");
          return true;
        }
      }
    } else {
      if (nextTrainToEnterA == nextTrainToExitA && nextTrainToEnterB == nextTrainToExitB) {
        if (nextTrainToEnterC == train.getId()) {
          System.out.println("TrainC - " + train.getId() + " entre dans le tronconABC");
          return true;
        }
      }
    }
    return false;
  }

  public static boolean canExit(Train train) {
    if (train.getClass().getSimpleName().equals("TrainA")) {
      if (nextTrainToExitA == train.getId()) {
        System.out.println("TrainA - " + train.getId() + " sort du tronconABC");
        return true;
      }
    } else if (train.getClass().getSimpleName().equals("TrainB")) {
      if (nextTrainToExitB == train.getId()) {
        System.out.println("TrainB - " + train.getId() + " sort du tronconABC");
        return true;
      }
    } else {
      if (nextTrainToExitC == train.getId()) {
        System.out.println("TrainC - " + train.getId() + " sort du tronconABC");
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
      lockABC.unlock();
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
      lockABC.unlock();
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
      lockABC.unlock();
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
      lockABC.unlock();
    }
  }

  public static void addTrainC(Train train) throws InterruptedException {
    boolean canEnter = false;
    while (!canEnter) {
      lock();
      canEnter = canEnter(train);
      if (canEnter) {
        nextTrainToEnterC++;
      }
      lockABC.unlock();
    }
  }

  public static void removeTrainC(Train train) {
    boolean canExit = false;
    while (!canExit) {
      lock();
      canExit = canExit(train);
      if (canExit) {
        nextTrainToExitC++;
      }
      lockABC.unlock();
    }
  }

  static synchronized void lock() {
    while (!lockABC.tryLock()) {
    }
  }

}
