package main;

import java.util.concurrent.Semaphore;

class DiningPhilosophers {
	
	Semaphore []sem;
    public DiningPhilosophers() {
        sem = new Semaphore[5];
        for(int i = 0;i < 5;i++)
        	sem[i] = new Semaphore(1);
    }

    // call the run() method of any runnable to execute its code
    public void wantsToEat(int philosopher, Runnable pickLeftFork, Runnable pickRightFork, Runnable eat, Runnable putLeftFork,
                           Runnable putRightFork) throws InterruptedException {
        Semaphore left = philosopher == 4?sem[0]:sem[philosopher+1];
        Semaphore right = sem[philosopher];
        
        if(philosopher%2 == 0) {
        	left.acquire();
        	pickLeftFork.run();
        	right.acquire();
        	pickRightFork.run();
        } else {
        	right.acquire();
        	pickRightFork.run();
        	left.acquire();
        	pickLeftFork.run();
        }
        
        eat.run();
        putLeftFork.run();
        left.release();
        putRightFork.run();
        right.release();
        
    }
}

public class Main {
	public static void main(String[] args) {

	}
}
