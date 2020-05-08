package dnssimulator;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Properties;

public class DNS {

    private String dbPath;
    private List<String> lines;

    public DNS() {
        try (InputStream input = new FileInputStream("serverDB.properties")) {

            Properties prop = new Properties();
            prop.load(input);
            dbPath = prop.getProperty("dbPATH");

        } catch (IOException io) {
            io.printStackTrace();
        }

    }

    private List readAllLines() {
        lines = new ArrayList<String>();
        try {
            lines = Files.readAllLines(Paths.get(dbPath), Charset.defaultCharset());
        } catch (IOException ex) {
            System.out.println("Error for read all lines of db file" + ex);
        }
        return lines;
    }

    protected String getItem(IPAddress ipAddress) {
        DomaineName domaineName = new DomaineName();
        readAllLines();
        for (String line : lines) {
            String[] words = line.split(" ");
            if (words[1].equals(ipAddress.getIPAddress())) {
                domaineName.setDomaineName(words[0]);
            }
        }
        return domaineName.getDomaineName();
    }

    protected String getItem(DomaineName domaineName) {
        IPAddress ipAddress = new IPAddress();
        readAllLines();
        for (String line : lines) {
            String[] words = line.split(" ");
            if (words[0].equals(domaineName.getDomaineName())) {
                ipAddress.setIPAddress(words[1]);
            }
        }
        return ipAddress.getIPAddress();
    }

    protected List getItems(String domaineName) {
        List<String> items = new ArrayList<String>();
        readAllLines();
        for (String line : lines) {
            if (line.contains(domaineName)) {
                items.add(line);
            }
        }

        items.sort(Comparator.naturalOrder());

        if (items.isEmpty()) {
            System.out.println("No result for this domaine name");
        }

        return items;
    }

}
