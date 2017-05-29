import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by David Szilagyi on 2017. 05. 29..
 */
public class Main {
    public static ArrayList<Thred> listOfThreads = new ArrayList<>();
    public static int indexOfThred;

    public static void main(String[] args) throws InterruptedException {
        boolean run = true;
        while (run) {
            System.out.println("\nCommand?");
            String[] command = new Scanner(System.in).nextLine().split(" ");
            Thred thred = null;
            switch (command[0]) {
                case "check":
                    if (command.length > 1) {
                        System.out.println(getThred(command[1]).toString());
                    } else {
                        for(Thred i: listOfThreads) {
                            System.out.println(i.toString());
                        }
                    }
                    break;
                case "start":
                    thred = getThred(command[1]);
                    if(thred == null) {
                        createNewThread(command[1]);
                    } else {
                        createNewThread(command[1]);
                        setSeconds();
                    }
                    break;
                case "stop":
                    thred = getThred(command[1]);
                    if (thred != null) {
                        thred.interrupt();
                    } else {
                        System.out.println("Thread not available!");
                    }
                    break;
                case "exit":
                    run = false;
                    System.exit(0);
                    break;
                default:
                    break;
            }
            Thread.sleep(1);
        }
    }

    private static void createNewThread(String threadName) {
        Thred thred = new Thred();
        thred.setName(threadName);
        listOfThreads.add(thred);
        thred.start();
    }

    private static Thred getThred(String name) {
        for (Thred i : listOfThreads) {
            if (name.equalsIgnoreCase(i.getName())) {
                indexOfThred = listOfThreads.indexOf(i);
                return i;
            }
        }
        return null;
    }

    private static void setSeconds() {
        int lastIndex = listOfThreads.size() - 1;
        Thred lastAdded = listOfThreads.get(lastIndex);
        Thred needToReplace = listOfThreads.get(indexOfThred);
        lastAdded.setSeconds(needToReplace.getSeconds());
        listOfThreads.set(indexOfThred, lastAdded);
        listOfThreads.remove(lastIndex);
    }
}
