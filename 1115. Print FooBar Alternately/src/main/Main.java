package main;

class FooBar {
    private int n;
    private static int val = 0;

    public FooBar(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        for (int i = 0; i < n; i++) {
        	synchronized (this) {
        		while(this.val%2 != 0)
        			wait();
        		// printFoo.run() outputs "foo". Do not change or remove this line.
        		printFoo.run();
				this.val++;
				notifyAll();
			}
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        
        for (int i = 0; i < n; i++) {
            synchronized (this) {
            	while(this.val%2 != 1)
            		wait();
            	// printBar.run() outputs "bar". Do not change or remove this line.
            	printBar.run();
				this.val++;
				notifyAll();
			}
        }
    }
}

public class Main {
	public static void main(String[] args) {

	}
}
