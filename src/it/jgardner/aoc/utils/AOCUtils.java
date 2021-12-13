package it.jgardner.aoc.utils;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.Buffer;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class AOCUtils {

    public static List<String> getInputData(int year, int day) {
        try {
            File inputDataFile = new File(year + "-" + day + ".txt");
            if (inputDataFile.exists()) {
                return new BufferedReader(new FileReader(inputDataFile))
                        .lines()
                        .collect(Collectors.toList());
            }
            String cookieData =
                    new BufferedReader(
                                    new InputStreamReader(
                                            Objects.requireNonNull(
                                                    AOCUtils.class.getResourceAsStream(
                                                            "cookieData.ini"))))
                            .lines()
                            .collect(Collectors.joining());

            URLConnection urlConnection =
                    new URL("https://adventofcode.com/" + year + "/day/" + day + "/input")
                            .openConnection();
            urlConnection.setRequestProperty("Cookie", "session=" + cookieData);
            List<String> inputData =
                    new BufferedReader(new InputStreamReader(urlConnection.getInputStream()))
                            .lines()
                            .collect(Collectors.toList());
            inputDataFile.createNewFile();
            FileWriter inputDataFileWriter = new FileWriter(inputDataFile);
            inputDataFileWriter.write(String.join("\n", inputData));
            inputDataFileWriter.close();

            return inputData;
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Input data cannot be retrieved");
    }
}
