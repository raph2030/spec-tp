package ca.ulaval.glo3004;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TronconBC {
  public static final Lock lockBC = new ReentrantLock();
  static private int nextTrainToEnterB = 0;
  static private int nextTrainToExitB = 0;
  static private int nextTrainToEnterC = 0;
  static private int nextTrainToExitC = 0;

  private static boolean canEnter(Train train) {
    if (train.getClass().getSimpleName().equals("TrainB")) {
      if (nextTrainToEnterC == nextTrainToExitC) {
        if (nextTrainToEnterB == train.getId()) {
          System.out.println("TrainB - " + train.getId() + " entre dans le tronconBC");
          return true;
        }
      }
    } else {
      if (nextTrainToEnterB == nextTrainToExitB) {
        if (nextTrainToEnterC == train.getId()) {
          System.out.println("TrainC - " + train.getId() + " entre dans le tronconBC");
          return true;
        }
      }
    }
    return false;
  }

  private static boolean canExit(Train train) {
    if (train.getClass().getSimpleName().equals("TrainB")) {
      if (nextTrainToExitB == train.getId()) {
        System.out.println("TrainB - " + train.getId() + " sort du tronconBC");
        return true;
      }
    } else {
      if (nextTrainToExitC == train.getId()) {
        System.out.println("TrainC - " + train.getId() + " sort du tronconBC");
        return true;
      }
    }
    return false;
  }

  public static void addTrainB(Train train) throws InterruptedException {
    boolean canEnter = false;
    while (!canEnter) {
      lock();
      canEnter = canEnter(train);
      if (canEnter) {
        nextTrainToEnterB++;
      }
      lockBC.unlock();
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
      lockBC.unlock();
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
      lockBC.unlock();
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
      lockBC.unlock();
    }
  }

  static synchronized void lock() {
    while (!lockBC.tryLock()) {
    }
  }
}
