package dnssimulator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class ActionCommand implements Command {

    protected String choice;
    protected List<String> resultList;
    protected String result;

    private boolean validIP(String ipAddress) {
        String regexIP
                = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
                + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
                + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
                + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";
        return Pattern.compile(regexIP).matcher(ipAddress).matches();
    }

    private boolean validDomainName(String domaineName) {
        String regexName = "^([a-zA-Z0-9-]+\\.){2}[a-zA-Z0-9]+";
        return Pattern.compile(regexName).matcher(domaineName).matches();
    }

    private boolean validAllDomains(String domaineName) {
        String regexName = "^[a-zA-Z0-9-]+";
        return Pattern.compile(regexName).matcher(domaineName).matches();
    }

    public void execute() {
        DNS dns = new DNS();
        Scanner sc = new Scanner(System.in);
        resultList = new ArrayList<String>();

        switch (choice) {
            case "1":
                System.out.println("Search Name");
                System.out.print("Please enter an IP Address with this format xx.xx.xx.xx which x is a number : ");
                String iPAddressEnter = sc.nextLine();

                if (!validIP(iPAddressEnter)) {
                    System.out.println("Your ip is not valid");
                } else {
                    IPAddress ipAddress = new IPAddress();
                    ipAddress.setIPAddress(iPAddressEnter);
                    resultList.add(dns.getItem(ipAddress));
                }
                break;
            case "2":
                System.out.println("Search IP");
                System.out.print("Please enter domaine name with this format machine.domaine.local : ");
                String domaineNameEnter = sc.nextLine();

                if (!validDomainName(domaineNameEnter)) {
                    System.out.println("Your domaine name is not valid");
                } else {
                    DomaineName domaineName = new DomaineName();
                    domaineName.setDomaineName(domaineNameEnter);
                    resultList.add(dns.getItem(domaineName));
                }
                break;
            case "3":
                System.out.println("Search domain's machines");
                System.out.print("Please enter domaine name : ");
                String domaine = sc.nextLine();
                
                if (!validAllDomains(domaine)) {
                    System.out.println("Your domaine name is not valid");
                } else {
                    resultList = dns.getItems(domaine);
                }
                break;
            case "4":
                System.out.println("Bye bye");
                System.exit(0);
                break;
            default:
                System.out.println("Your choice is not possible, you should enter : 1,2,3 or 4");
        }
    }

}
