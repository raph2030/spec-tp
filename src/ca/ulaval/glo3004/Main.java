package ca.ulaval.glo3004;

public class Main {
  public static void main(String args[]) {
    int nbOfTrainA = Integer.parseInt(args[0]);
    int nbOfTrainB = Integer.parseInt(args[0]);
    int nbOfTrainC = Integer.parseInt(args[0]);
    
    for (int i = 0; i < nbOfTrainB; i++) { //placer B en premier donne priorité automatiquement au Train B pour le TronconAB. Nous avons tout de même un autre mécanisme dans tronçonAB qui assure la priorité de B.
	  Thread threadB = new Thread(new TrainB(i));
	  threadB.start();
	}

    for (int i = 0; i < nbOfTrainA; i++) {
      Thread threadA = new Thread(new TrainA(i));
      threadA.start();
    }

    

    for (int i = 0; i < nbOfTrainC; i++) {
      Thread threadC = new Thread(new TrainC(i));
      threadC.start();
    }

  }
}
