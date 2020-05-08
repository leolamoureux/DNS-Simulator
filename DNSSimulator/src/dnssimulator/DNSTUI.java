package dnssimulator;

import java.util.Scanner;

public class DNSTUI {

    ActionCommand actionCommand = new ActionCommand();

    protected void nextCommand() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Hi welcome to DNS Simulator");
        System.out.println("Tape 1 to search domain name, "
                + "2 to search IP Address, "
                + "3 to search all domain's machines "
                + "and 4 to exit");
        System.out.println("After your choice, press enter key");

        do {
            System.out.print("Please tape your choice : ");
            actionCommand.choice = sc.nextLine();
            actionCommand.execute();

            int numberChoice = Integer.parseInt(actionCommand.choice);
            if (numberChoice > 0 && numberChoice < 4) {
                printResult();
            }

        } while (true);
    }

    protected void printResult() {
        if (!actionCommand.resultList.isEmpty()) {
            for (String result : actionCommand.resultList) {
                System.out.println(result);
            }
        } else {
            System.out.println("No result for your search");
        }

    }
}
