package nu.redpois0n.oslib;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Utils {

    public static List<String> readProcess(String[] args) throws Exception {
        List<String> raw = new ArrayList<>();

        Process p = Runtime.getRuntime().exec(args);

        BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;

        while ((line = reader.readLine()) != null) {
            if (line.trim().length() == 0) continue;
            raw.add(line);
        }

        reader.close();

        return raw;
    }

    public static Map<String, String> mapFile(File file, String delimiter) throws Exception {
        Map<String, String> map = new HashMap<>();

        List<String> lines = readFile(file);

        for (String line : lines) {
            if (line.trim().length() == 0) continue;
            String[] split = line.split(delimiter);
            String key = split[0].trim();
            String value = split[1].trim();
            map.put(key, value);
        }

        return map;
    }

    public static List<String> readFile(File file) throws Exception {
        List<String> list = new ArrayList<>();

        if (file.exists()) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));

            String line;

            while ((line = reader.readLine()) != null) {
                if (line.trim().length() == 0) continue;
                list.add(line);
            }

            reader.close();
        }

        return list;
    }

    /**
     * Runs the command "uname -a" and reads the first line
     */
    public static String getUname() {
        String uname = null;

        try {
            Process p = Runtime.getRuntime().exec(new String[]{"uname", "-a"});
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            uname = reader.readLine();
            reader.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return uname;
    }

}
