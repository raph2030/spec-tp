package ca.ulaval.glo3004;

import java.util.concurrent.ThreadLocalRandom;

abstract interface Train extends Runnable {
	//cette fonction permet de g�n�rer un boolean de facon al�atoire selon une probabilit�
  default boolean randomBoolean() {
    int randomNum = ThreadLocalRandom.current().nextInt(0, 100 + 1);
    return randomNum % 10 == 0;
  }
//Cette fonction permet de g�n�rer de fa�on al�atoire un d�lai sur un train
  default void checkDelay(Train train) throws InterruptedException {
	  
    if (randomBoolean()) {
    	if(train.getClass().getSimpleName().equals("TrainB") && (train.getId() == 0)) {
  		  TronconAB.cancelBFirstToStart();
  		
  	  }
      System.out.println(train.getClass().getSimpleName() + " - " + train.getId() + " a un d�lai de 1 seconde");
      Thread.sleep(1000);
    }
  }
//Cette fonction permet de g�n�rer de fa�on al�atoire un bris sur un train
  default void checkBroken(Train train) throws InterruptedException {
    if (randomBoolean()) {
    	if(train.getClass().getSimpleName().equals("TrainB") && (train.getId() == 0)) {
    		  TronconAB.cancelBFirstToStart();
    	  }
      System.out.println(train.getClass().getSimpleName() + " - " + train.getId() + " est en panne.");
      Thread.sleep(2000);
      System.out.println(train.getClass().getSimpleName() + " - " + train.getId() + " est r�par�");
      Thread.sleep(2000);
    }
  }

  public int getId();
}
