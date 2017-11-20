package ca.ulaval.glo3004;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//Cette classe représente le tronçon partagé AB
public class TronconAB {
  public static final Lock lockAB = new ReentrantLock();
  private static int nextTrainToEnterA = 0;
  private static int nextTrainToExitA = 0;
  private static int nextTrainToEnterB = 0;
  private static int nextTrainToExitB = 0;
  
  private static boolean nextBAskedToEnter = true;
  
  
  public static void cancelBFirstToStart() {
	  nextBAskedToEnter = false;
  }
//Cette fonction permet de retourner si le train en paramètre peut entrer dans TronconAB : un train d'une meme ligne ne peut pas en dépasser un autre
  //Elle s'assure aussi de retourner faux s'il y a deja un ou plusieurs autre train d'une meme ligne sur le tronçon
  //Elle s'assure aussi de laisser priorité au TrainB pour l'entrer dans le Troncon AB
  private static boolean canEnter(Train train) {
    if (train.getClass().getSimpleName().equals("TrainA")) {
		if(nextBAskedToEnter) {//faut laisser priorité au Train B qui veut entrer
			return false;
		}
        if (nextTrainToEnterB == nextTrainToExitB) {
          if (nextTrainToEnterA == train.getId()) {
	          
	          System.out.println("TrainA - " + train.getId() + " entre dans le tronconAB");
	          return true;
	      }
        }
    } else {
      if (nextTrainToEnterB == train.getId()) {
    	  nextBAskedToEnter = true;
    	if (nextTrainToEnterA == nextTrainToExitA) {
        
          System.out.println("TrainB - " + train.getId() + " entre dans le tronconAB");
          return true;
        }
      }
    }
    return false;
  }
  //Cette fonction permet de retourner si le train en paramètre peut sortir du TronconAB : un train d'une meme ligne ne peut pas en dépasser un autre
  public static boolean canExit(Train train) {
    if (train.getClass().getSimpleName().equals("TrainA")) {
      if (nextTrainToExitA == train.getId()) {
        System.out.println("TrainA - " + train.getId() + " sort du tronconAB");
        return true;
      }
    } else {
      if (nextTrainToExitB == train.getId()) {
        System.out.println("TrainB - " + train.getId() + " sort du tronconAB");
        nextBAskedToEnter = false;
        return true;
      }
    }
    return false;
  }
//Cette fonction fait entrer le Train dans le Tronçon lorsque ce sera son tour
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
//Cette fonction fait sortir le Train du Tronçon lorsque ce sera son tour
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
//Cette fonction fait entrer le Train dans le Tronçon lorsque ce sera son tour
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
//Cette fonction fait sortir le Train du Tronçon lorsque ce sera son tour
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
