package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.Test;

/**
 * substring.
 */
public class SubStringFinderTest {
    /**
     * someTest.
     */
    @Test
    public void test() throws IOException {
        var fileName = "test.txt";
        var subStr = "br";
        List<Long> a = Arrays.asList(39L, 41L, 43L, 45L, 47L, 56L, 60L, 67L, 69L, 71L, 73L);
        assertEquals(a, SubStringFinder.find(fileName, subStr, StandardCharsets.UTF_8));
    }

    @Test
    public void chinaTest() throws IOException {
        var fileName = "china.txt";
        var subStr = "文";
        List<Long> a = Arrays.asList(7L, 8L, 10L);
        assertEquals(a, SubStringFinder.find(fileName, subStr, StandardCharsets.UTF_8));
    }

    public void largeTest() throws IOException, InterruptedException {
        String resourcePath = "largefile.txt";
        long fileSizeInBytes = 1024L * 1024 * 1024; // 8 GB
        File file = new File(resourcePath);

        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            Random random = new Random();

            // спользуем UTF-8 кодировку
            Charset charset = StandardCharsets.UTF_8;
            CharsetEncoder encoder = charset.newEncoder();

            CharBuffer charBuffer = CharBuffer.allocate(1024 * 1024);
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024 * 1024);

            for (long i = 0; i < fileSizeInBytes; i += byteBuffer.capacity()) {
                // Генерация случайных символов
                for (long j = 0; j < charBuffer.capacity(); j++) {
                    charBuffer.put((char) (random.nextInt(128)));
                }
                charBuffer.flip();

                encoder.encode(charBuffer, byteBuffer, true);
                byteBuffer.flip();

                // Запись байтов в файл
                fileOutputStream.write(byteBuffer.array());

                charBuffer.clear();
                byteBuffer.clear();
            }
            System.out.println("Large file created successfully in resources.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<Long> arr = SubStringFinder.find(file, "adh", StandardCharsets.UTF_8);
        System.out.println(arr);
        System.out.println(arr.size());

        Path filePath = Path.of(resourcePath);
        Files.deleteIfExists(filePath);
        System.out.println("File deleted successfully.");

    }
}
