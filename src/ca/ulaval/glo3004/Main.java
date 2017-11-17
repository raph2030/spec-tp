package ca.ulaval.glo3004;

public class Main {
  public static void main(String args[]) {
    int nbOfTrainA = Integer.parseInt(args[0]);
    int nbOfTrainB = Integer.parseInt(args[0]);
    int nbOfTrainC = Integer.parseInt(args[0]);
    
    for (int i = 0; i < nbOfTrainB; i++) { //placer B en premier donne priorit� automatiquement au Train B pour le TronconAB. Nous avons tout de m�me un autre m�canisme dans tron�onAB qui assure la priorit� de B.
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
