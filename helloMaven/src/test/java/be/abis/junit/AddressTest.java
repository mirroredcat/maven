package be.abis.junit;

import be.abis.junit.model.Address;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class AddressTest {

    Address a;
    Address a2;
    File f;

    @BeforeEach
    void setUp() {
        a = new Address("Mechelsesteenweg", "31", "1800", "Antwerpen", "Belgium", "BE" );
        a2 = new Address("Tunhoutsebaan", "21", "1280", "Antwerpen", "Belgium", "BE");
        try {
            f = new File("C:/temp/javacourses/addressOutput.txt");
            f.setWritable(true);
            new FileWriter("C:/temp/javacourses/addressOutput.txt", false).close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void belgianZipCodeShouldBeNumeric(){
        assertTrue(a.isBelgianZipCodeNumeric());
    }

    @Test
    void writeToFileShouldWriteCorrectly() throws IOException {
        f.setWritable(true);
        a.writeToFile();
        List<String> lines;
        try (BufferedReader br = new BufferedReader(new FileReader("C:/temp/javacourses/addressOutput.txt"))){
            lines = br.lines().collect(Collectors.toList());
            assertEquals(lines.size(), 1);
            a2.writeToFile();
            a2.writeToFile();
            a.writeToFile();
            lines = br.lines().collect(Collectors.toList());
            assertEquals(lines.size(), 3);
        } catch (IOException e){
            throw new RuntimeException();
        }
    }

    @Test
    void writeToFileShouldRaiseIOException(){
        f = new File("C:/temp/javacourses/addressOutput.txt");
        f.setWritable(false);
        assertThrows(IOException.class, () -> a2.writeToFile());
    }



}
