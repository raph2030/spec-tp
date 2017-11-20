package ca.ulaval.glo3004;
//Cette classe représente un train d'une ligne A
public class TrainA implements Runnable, Train {
  private int index;

  public TrainA(int index) {
    this.index = index;
  }

  @Override
  public void run() {
    try {
      checkDelay(this);
      checkBroken(this);

      TronconAB.addTrainA(this);
      Thread.sleep(1000);
      TronconAB.removeTrainA(this);
      Thread.sleep(1000);

      checkDelay(this);
      checkBroken(this);

      TronconABC.addTrainA(this);
      Thread.sleep(200);
      TronconABC.removeTrainA(this);
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
