package controller;

import model.Directory;
import savedata.WriteAndReadDirectory;

import java.util.ArrayList;
import java.util.List;

public class DirectoryController implements IDirectoryController<Directory> {
    private WriteAndReadDirectory writeAndRead = new WriteAndReadDirectory();
    private List<Directory> directoryList;
    public DirectoryController(){
        this.directoryList = writeAndRead.readDirectory();
    }
    public void add(Directory directory) {
        this.directoryList.add(directory);
        writeAndRead.writeDirectory(directoryList,true);
    }

    @Override
    public List<Directory> getAll() {
        return directoryList;
    }

    @Override
    public void update(String phoneNumber, Directory directory) {
        for (int i = 0; i < directoryList.size(); i++) {
            if (directoryList.get(i).getPhoneNumber().equals(phoneNumber)) {
                directoryList.set(i, directory);
                saveDirectoryToFile();
                System.out.println("Cập nhật danh bạ thành công.");
                return;
            }
        }
        System.out.println("Không tìm thấy số điện thoại trong danh bạ.");
    }



    @Override
    public void delete(String phoneNumber) {
        for (int i = 0; i < directoryList.size(); i++) {
            if (directoryList.get(i).getPhoneNumber().equals(phoneNumber)) {
                directoryList.remove(i);
                saveDirectoryToFile();
                return;
            }
        }
        System.out.println("Không tìm thấy số điện thoại để xóa.");
    }
    @Override
    public List<Directory> searchDirectoryByPhone(String phoneNumber) {
        List<Directory> directoryLists = new ArrayList<>();
        for (Directory directory : directoryList) {
            if (directory.getPhoneNumber().contains(phoneNumber)) {
                directoryLists.add(directory);
            }
        }
        return directoryLists;
    }
    @Override
    public List<Directory> searchDirectoryByFullName(String userName) {
        List<Directory> directoryLists = new ArrayList<>();
        for (Directory directory : directoryList) {
            if (directory.getUserName().toLowerCase().contains(userName.toLowerCase())) {
                directoryLists.add(directory);
            }
        }
        return directoryLists;
    }

    public void saveDirectoryToFile() {
        writeAndRead.writeDirectory(directoryList,false);
    }
    public void reloadDirectory() {
        this.directoryList = writeAndRead.readDirectory();
    }
}
