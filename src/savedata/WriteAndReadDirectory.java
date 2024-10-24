package savedata;

import model.Directory;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class WriteAndReadDirectory {
    private final File file = new File("C:\\Coder\\APJ-EXAM_C0642l1_TranMaiTuanKhanh\\src\\savedata\\contacts.csv");

    //Ghi file
    public void writeDirectory(List<Directory> directoryList, boolean append) {
        try (FileWriter fw = new FileWriter(this.file, append);
             BufferedWriter bw = new BufferedWriter(fw)) {

            for (Directory directory : directoryList) {
                bw.write(directory.getPhoneNumber() + "," + directory.getGroupPhone() + "," + directory.getUserName() + "," + directory.getGender() + "," + directory.getAddress() + "," + directory.getDob() + "," + directory.getEmail());
                bw.newLine();
            }

        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    //Doc file
    public List<Directory> readDirectory() {
        List<Directory> directoryList = new ArrayList<>();

        try (FileReader fr = new FileReader(this.file);
             BufferedReader br = new BufferedReader(fr)) {

            String line;
            while ((line = br.readLine()) != null) {

                String[] directoryString = line.split(",");
                String phoneNumber = directoryString[0];
                String groupPhone = directoryString[1];
                String userName = directoryString[2];
                String gender = directoryString[3];
                String address = directoryString[4];
                LocalDate dob  = LocalDate.parse(directoryString[5]);
                String email = directoryString[6];

                Directory directory = new Directory(phoneNumber, groupPhone, userName, gender, address, dob, email);
                directoryList.add(directory);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return directoryList;
    }

}
