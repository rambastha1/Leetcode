package main;

import java.util.concurrent.Semaphore;

class ZeroEvenOdd {
    private int n;
    Semaphore zero, odd, even;
    public ZeroEvenOdd(int n) {
        this.n = n;
        this.zero = new Semaphore(1);
        this.odd = new Semaphore(0);
        this.even = new Semaphore(0);
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        boolean printodd = true;
        for(int i = 1;i <= this.n;i++) {
        	zero.acquire();
        	printNumber.accept(0);
        	if(printodd)
        		odd.release();
        	else
        		even.release();
        	printodd = !printodd;
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        int n = this.n/2;
        int num = 2;
        for(int i = 1;i <= n;i++) {
        	even.acquire();
        	printNumber.accept(num);
        	num += 2;
        	zero.release();
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        int n = (this.n+1)/2;
        int num = 1;
        for(int i = 1;i <= n;i++) {
        	odd.acquire();
        	printNumber.accept(num);
        	num += 2;
        	zero.release();
        }
    }
}

public class Main {
	public static void main(String[] args) {

	}
}
