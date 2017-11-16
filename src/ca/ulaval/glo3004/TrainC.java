package ca.ulaval.glo3004;

public class TrainC implements Runnable, Train {
  private int index;

  public TrainC(int index) {
    this.index = index;
  }

  @Override
  public void run() {
    try {
      checkDelay(this);
      checkBroken(this);

      TronconBC.addTrainC(this);
      Thread.sleep(200);
      TronconBC.removeTrainC(this);
      Thread.sleep(200);

      checkDelay(this);
      checkBroken(this);

      TronconABC.addTrainC(this);
      Thread.sleep(200);
      TronconABC.removeTrainC(this);
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
