package view;

import common.ExceptionHandler;
import controller.DirectoryController;
import model.Directory;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DirectoryView {
    private DirectoryController directoryController = new DirectoryController();
    private Scanner scString = new Scanner(System.in);
    int choice;

    public void renderMenu() {
        showMenu();
    }

    public void showMenu() {
        do {
            System.out.println("----CHƯƠNG TRÌNH QUẢN LÝ DANH BẠ ----");
            System.out.println("Chọn chức năng theo số (để tiếp tục):");
            System.out.println("1. Xem danh sách");
            System.out.println("2. Thêm mới");
            System.out.println("3. Cập nhập");
            System.out.println("4. Xóa");
            System.out.println("5. Tìm kiếm");
            System.out.println("6. Đọc từ file");
            System.out.println("7. Ghi vào file");
            System.out.println("8. Thoát");

            System.out.println("Chọn chức năng :");
            choice = ExceptionHandler.checkParseInt();

            switch (choice) {
                case 1:
                    showAll();
                    break;
                case 2:
                    addDirectory();
                    break;
                case 3:
                    updateDirectory();
                    break;
                case 4:
                    removeDirectory();
                    break;
                case 5:
                    searchDirectory();
                    break;
                case 6:
                    readFile();
                    break;
                case 7:
                    writeFile();
                    break;
                case 8:
                    System.out.println("----THOÁT----");
                    break;
                default:
                    System.out.println("Du lieu khong hop le");

            }
        } while (choice != 8);
    }

    private void writeFile() {
        System.out.println("====LƯU VÀO FILE====");
        if (true) {
            String config;
            do {
                System.out.println("Lựa chọn “Lưu vào File” sẽ thực hiện cảnh báo người dùng trước khi cập nhật file danh\n" +
                        "bạ.  Nếu người dùng chấp nhận sẽ thực hiện cập nhật lại toàn bộ nội dung file");
                config = scString.nextLine();
                if (config.isEmpty()) {
                    System.out.println("Dữ liệu bắt buộc phải nhập");
                }
            } while (config.isEmpty());

            directoryController.saveDirectoryToFile();
            System.out.println("Lưu danh bạ vào file thành công.");
        } else {
            System.out.println("Lưu vào file đã bị hủy.");
        }
    }

    private void readFile() {
        System.out.println("====ĐỌC TỪ FILE====");
        if (true) {
            String config;
            do {
                System.out.println("Lựa chọn “Đọc từ File” sẽ thực hiện cảnh báo người dùng trước khi cập nhật bộ nhớ (xóa\n" +
                        "toàn bộ danh bạ đang có trong bộ nhớ). Nếu người dùng chấp nhận sẽ thực hiện cập nhật\n" +
                        "lại  toàn bộ bộ nhớ danh bạ từ file.");
                config = scString.nextLine();
                if (config.isEmpty()) {
                    System.out.println("Dữ liệu bắt buộc phải nhập");
                }
            } while (config.isEmpty());

            directoryController.reloadDirectory();

            List<Directory> directories = directoryController.getAll();
            if (!directories.isEmpty()) {
                System.out.println("Danh sách hiện tại:");
                for (Directory directory : directories) {
                    System.out.println(directory);
                }
            } else {
                System.out.println("Danh bạ rỗng hoặc không có dữ liệu từ file.");
            }
        } else {
            System.out.println("Đọc từ file đã bị hủy.");
        }

    }

    private void searchDirectory() {
        do {
            System.out.println("======TÌM KIẾM DANH BẠ========");
            System.out.println("1.Tìm theo số điện thoại :");
            System.out.println("2.Tìm theo họ tên :");
            System.out.println("3.Quay lại trang chủ");
            int choice = ExceptionHandler.checkParseInt();
            List<Directory> directoryLists;
            switch (choice) {
                case 1:
                    System.out.println("Nhập số điện thoại :");
                    String phoneNumber = scString.nextLine();
                    directoryLists = directoryController.searchDirectoryByPhone(phoneNumber);
                    break;
                case 2:
                    System.out.println("Nhập họ tên: ");
                    String userName = scString.nextLine();
                    directoryLists = directoryController.searchDirectoryByFullName(userName);
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ.");
                    return;
            }

            if (directoryLists.isEmpty()) {
                System.out.println("Không tìm thấy kết quả phù hợp.");
            } else {
                System.out.println("Kết quả tìm kiếm:");
                for (Directory directory : directoryLists) {
                    System.out.println(directory);
                }
            }
        } while (choice != 3);
    }

    private void removeDirectory() {
        System.out.println("====XÓA DANH BẠ====");
        String phoneNumber;
        do {
            System.out.print("Nhập số điện thoại cần xóa (nhấn Enter để quay lại Menu): ");
            phoneNumber = scString.nextLine();
            if (phoneNumber.isEmpty()) {
                System.out.println("Quay lại Menu chính.");
                return;
            }

            List<Directory> directories = directoryController.searchDirectoryByPhone(phoneNumber);
            if (directories != null && !directories.isEmpty()) {
//                Directory directory = directories.get(0);
                System.out.println("Bạn có chắc muốn xóa danh bạ với số điện thoại " + phoneNumber + " không? (Nhập 'Y' để xác nhận): ");
                String confirm = scString.nextLine();
                if (confirm.equalsIgnoreCase("Y")) {
                    directoryController.delete(phoneNumber);
                    System.out.println("Danh bạ với số điện thoại " + phoneNumber + " đã được xóa.");
                } else {
                    System.out.println("Hủy thao tác xóa. Quay lại Menu.");
                }
                return;
            } else {
                System.out.println("Không tìm thấy danh bạ với số điện thoại này. Vui lòng nhập lại.");
            }
        } while (true);
    }

    private void updateDirectory() {
        System.out.println("====CẬP NHẬT DANH BẠ====");

        String phoneNumber;
        do {
            System.out.println("Nhập số điện thoại  cần sửa :");
            phoneNumber = scString.nextLine();
            if (phoneNumber.isEmpty() || !ExceptionHandler.isVaildPhoneNumber(phoneNumber)) {
                System.out.println("Số điện thoại không hợp lệ. Vui lòng nhập lại.");
            }
        } while (phoneNumber.isEmpty() || !ExceptionHandler.isVaildPhoneNumber(phoneNumber));

        String group;
        do {
            System.out.print("Nhập nhóm của danh bạ : ");
            group = scString.nextLine();
            if (group.isEmpty()) {
                System.out.println("Nhóm không được để trống. Vui lòng nhập lại.");
            }
        } while (group.isEmpty());

        String fullName;
        do {
            System.out.print("Nhập họ tên : ");
            fullName = scString.nextLine();
            if (fullName.isEmpty()) {
                System.out.println("Họ tên không được để trống. Vui lòng nhập lại.");
            }
        } while (fullName.isEmpty());

        String gender;
        do {
            System.out.print("Nhập giới tính : ");
            gender = scString.nextLine();
            if (gender.isEmpty()) {
                System.out.println("Giới tính không được để trống. Vui lòng nhập lại.");
            }
        } while (gender.isEmpty());

        String address;
        do {
            System.out.print("Nhập địa chỉ : ");
            address = scString.nextLine();
            if (address.isEmpty()) {
                System.out.println("Địa chỉ không được để trống. Vui lòng nhập lại.");
            }
        } while (address.isEmpty());

        LocalDate dob = null;
        while (dob == null) {
            System.out.print("Nhập ngày sinh (YYYY-MM-DD): ");
            String birthDayInput = scString.nextLine();
            try {
                dob = LocalDate.parse(birthDayInput);
            } catch (DateTimeParseException e) {
                System.out.println("Ngày sinh không hợp lệ. Vui lòng nhập lại theo định dạng YYYY-MM-DD.");
            }
        }

        String email;
        do {
            System.out.print("Nhập email : ");
            email = scString.nextLine();
            if (email.isEmpty() || !ExceptionHandler.isVaildEmail(email)) {
                System.out.println("Email không hợp lệ. Vui lòng nhập lại.");
            }
        } while (email.isEmpty() || !ExceptionHandler.isVaildEmail(email));

        Directory directory = new Directory(phoneNumber, group, fullName, gender, address, dob, email);
        directoryController.update(phoneNumber, directory);
    }

    private void showAll() {
        System.out.println("====DANH SÁCH DANH BẠ====");
        List<Directory> directories = directoryController.getAll();
        if (directories.isEmpty()) {
            System.out.println("Danh sách danh bạ trống.");
        } else {
            int total = directories.size();
            int count = 0;

            for (Directory directory : directories) {
                System.out.println("Lựa chọn" + " Thêm mới");
//                addDirectory();
                System.out.println("Số điện thoại: " + directory.getPhoneNumber());
                System.out.println("Nhóm: " + directory.getGroupPhone());
                System.out.println("Họ tên: " + directory.getUserName());
                System.out.println("Giới tính: " + directory.getGender());
                System.out.println("Địa chỉ: " + directory.getAddress() + "\n");

                count++;
                if (count % 5 == 0 && count < total) {
                    System.out.println("Nhấn enter để tiếp tục");
                    scString.nextLine();
                }
            }
        }
    }

    private void addDirectory() {
        System.out.println("====THÊM DANH BẠ====");

        String phoneNumber;
        do {
            System.out.println("Nhập số điện thoại  :");
            phoneNumber = scString.nextLine();
            if (phoneNumber.isEmpty() || !ExceptionHandler.isVaildPhoneNumber(phoneNumber)) {
                System.out.println("Số điện thoại không hợp lệ. Vui lòng nhập lại.");
            }
        } while (phoneNumber.isEmpty() || !ExceptionHandler.isVaildPhoneNumber(phoneNumber));

        String group;
        do {
            System.out.print("Nhập nhóm của danh bạ : ");
            group = scString.nextLine();
            if (group.isEmpty()) {
                System.out.println("Nhóm không được để trống. Vui lòng nhập lại.");
            }
        } while (group.isEmpty());

        String fullName;
        do {
            System.out.print("Nhập họ tên : ");
            fullName = scString.nextLine();
            if (fullName.isEmpty()) {
                System.out.println("Họ tên không được để trống. Vui lòng nhập lại.");
            }
        } while (fullName.isEmpty());

        String gender;
        do {
            System.out.print("Nhập giới tính (bắt buộc): ");
            gender = scString.nextLine();
            if (gender.isEmpty()) {
                System.out.println("Giới tính không được để trống. Vui lòng nhập lại.");
            }
        } while (gender.isEmpty());

        String address;
        do {
            System.out.print("Nhập địa chỉ: ");
            address = scString.nextLine();
            if (address.isEmpty()) {
                System.out.println("Địa chỉ không được để trống. Vui lòng nhập lại.");
            }
        } while (address.isEmpty());

        LocalDate dob = null;
        while (dob == null) {
            System.out.print("Nhập ngày sinh (YYYY-MM-DD): ");
            String birthDayInput = scString.nextLine();
            try {
                dob = LocalDate.parse(birthDayInput);
            } catch (DateTimeParseException e) {
                System.out.println("Ngày sinh không hợp lệ. Vui lòng nhập lại theo định dạng YYYY-MM-DD.");
            }
        }

        String email;
        do {
            System.out.print("Nhập email : ");
            email = scString.nextLine();
            if (email.isEmpty() || !ExceptionHandler.isVaildEmail(email)) {
                System.out.println("Email không hợp lệ. Vui lòng nhập lại.");
            }
        } while (email.isEmpty() || !ExceptionHandler.isVaildEmail(email));

        Directory directory = new Directory(phoneNumber, group, fullName, gender, address, dob, email);
        directoryController.add(directory);
        System.out.println("Danh bạ mới đã được thêm thành công.");
    }
}
