package org.example;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SubStringFinder {
    private static long checkIndex(String block, String subString, int startInd, long startBlock) {
        long result = -1;

        for (int i = 0; i < subString.length(); i++) {
            if ((i + startInd) < block.length()) {
                if (subString.charAt(i) == block.charAt(i + startInd)) {
                    if (i == subString.length() - 1) {
                        result = startInd + startBlock;
                    }
                } else {
                    break;
                }
            } else {
                break;
            }
        }
        return result;
    }

    /**
     * check block, and add indexes.
     */
    private static ArrayList<Long> checkBlock(String block, String subString, long startBlock) {
        var result = new ArrayList<Long>();
        for (int i = 0; i < block.length(); i++) {
            char c = block.charAt(i);
            if (c == subString.charAt(0)) {
                long tmp = checkIndex(block, subString, i, startBlock);
                if (tmp != -1) {
                    result.add(tmp);
                }
            }
        }

        return result;
    }

    /**
     * read new block.
     */
    private static String readBlock(
            BufferedReader bufRead,
            Long subStringLength,
            Charset charset
    ) throws IOException {
        var block = new StringBuilder();

        for (int i = 0; i < subStringLength; i++) {
            int c;
            if ((c = bufRead.read()) != -1) {
                block.append((char) c);
            } else {
                break;
            }
        }
        return new String(block.toString().getBytes(charset), charset);
    }

    /**
     * find with filename in resources.
     */
    public static ArrayList<Long> find(String filename, String subString, Charset charset) throws IOException {
        //чтение из ресурсов
        ClassLoader classLoader = ClassLoader.getSystemClassLoader(); //Это загрузчик классов, может также быть использован для загрузки ресурсов

        URL resource = classLoader.getResource(filename);
        if (resource == null) {
            throw new IllegalArgumentException("file not found! " + filename);
        }
        File file;
        try {
            file = new File(resource.toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        return find(file, subString, charset);
    }


    /**
     * main method of class - find.
     */
    public static ArrayList<Long> find(File file, String subString1, Charset charset) throws IOException {
        ArrayList<Long> result = new ArrayList<>();
        var subString = new String(subString1.getBytes(), StandardCharsets.UTF_8);
        FileReader fileR = new FileReader(file, charset);
        try (var bufRead = new BufferedReader(fileR)) {

            String buffer1 = readBlock(bufRead, 1024L, charset);
            long startBlock = 0L;

            if (buffer1.length() < subString.length()) {
                return result;
            }
            String buffer2 = readBlock(bufRead, 1024L, charset);

            if (buffer2.length() < 1024L) {
                result.addAll(checkBlock(buffer1.concat(buffer2), subString, startBlock));
                return result;
            }

            while (true) {
                result.addAll(checkBlock(buffer1.concat(buffer2), subString, startBlock));
                String newBlock = readBlock(bufRead, 1024L, charset);

                startBlock += 1024L;


                if (newBlock.length() < 1024L) {
                    result.addAll(checkBlock(buffer2.concat(newBlock), subString, startBlock));
                    break;
                }

                buffer1 = buffer2;
                buffer2 = newBlock;
            }
        }
        List<Long> unique = result.stream().distinct().collect(Collectors.toList());
        result = new ArrayList<Long>(unique);
        return result;
    }
}