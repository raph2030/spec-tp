package ca.ulaval.glo3004;

import java.util.concurrent.ThreadLocalRandom;

abstract interface Train extends Runnable {
  default boolean randomBoolean() {
    int randomNum = ThreadLocalRandom.current().nextInt(0, 100 + 1);
    return randomNum % 10 == 0;
  }

  default void checkDelay(Train train) throws InterruptedException {
    if (randomBoolean()) {
      System.out.println(train.getClass().getSimpleName() + " - " + train.getId() + " a un délai de 1 seconde");
      Thread.sleep(1000);
    }
  }

  default void checkBroken(Train train) throws InterruptedException {
    if (randomBoolean()) {
      System.out.println(train.getClass().getSimpleName() + " - " + train.getId() + " est en panne.");
      Thread.sleep(2000);
      System.out.println(train.getClass().getSimpleName() + " - " + train.getId() + " est réparé");
      Thread.sleep(2000);
    }
  }

  public int getId();
}