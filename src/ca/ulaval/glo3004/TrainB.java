package ca.ulaval.glo3004;

public class TrainB implements Runnable, Train {
  private int index;

  public TrainB(int index) {
    this.index = index;
  }

  @Override
  public void run() {
    try {
      checkDelay(this);
      checkBroken(this);

      TronconAB.addTrainB(this);
      Thread.sleep(200);
      TronconAB.removeTrainB(this);
      Thread.sleep(200);

      checkDelay(this);
      checkBroken(this);

      TronconBC.addTrainB(this);
      Thread.sleep(200);
      TronconBC.removeTrainB(this);
      Thread.sleep(200);

      checkDelay(this);
      checkBroken(this);

      TronconABC.addTrainB(this);
      Thread.sleep(200);
      TronconABC.removeTrainB(this);
      Thread.sleep(200);
    } catch (Exception e) {
      System.out.println("Something went wrong.");
      System.out.println(e);
    }
  }

  @Override
  public int getId() {
    return index;
  }

}
