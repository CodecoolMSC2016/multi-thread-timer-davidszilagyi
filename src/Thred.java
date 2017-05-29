/**
 * Created by David Szilagyi on 2017. 05. 29..
 */
public class Thred extends Thread {
    private int seconds = 0;
    boolean running;

    public Thred () {
        running = true;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public void run() {
        while(running) {
            try {
                seconds++;
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                running = false;
                System.out.println(getName() + " received interrupt while sleeping");
            }
        }
    }

    @Override
    public String toString() {
        return "Name: " + getName() + ", ThreadID: " + getId() + ", Seconds: " + getSeconds();
    }
}
