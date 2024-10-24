package controller;

import model.Directory;

import java.util.List;

public interface IDirectoryController <E>{
    void add(E e);

    List<Directory> getAll();

    void update(String phoneNumber, Directory directory);

    List<Directory> searchDirectoryByPhone(String phoneNumber);

    void delete(String phoneNumber);

    List<Directory> searchDirectoryByFullName(String userName);
}
