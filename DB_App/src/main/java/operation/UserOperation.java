package operation;

import org.example.User;
import utility.ConsoleHelper;

public class UserOperation extends Operation {

    private static final String SUCCESS_MESSAGE = "Пользователь успешно создан.";

    @Override
    public void execute() {
        User user = createUser();
        db_writer.writeUserToDB(user);
        showSuccessfulOperationMessage();
        showUserID(user.getUser_id());
    }

    private User createUser() {
        String firstName = readFirstNameFromConsole();
        String lastName = readLastNameFromConsole();
        String address = readAddressFromConsole();
        return new User(firstName, lastName, address);
    }

    private String readFirstNameFromConsole() {
        while (true) {
            System.out.println("Введите имя пользователя... (макс. 25 символов)");
            String firstName = ConsoleHelper.readLine();
            if (firstName.isEmpty() || firstName.length() > 25) continue;
            return firstName;
        }
    }

    private String readLastNameFromConsole() {
        while (true) {
            System.out.println("Введите фамилию пользователя... (макс. 25 символов)");
            String lastName = ConsoleHelper.readLine();
            if (lastName.isEmpty() || lastName.length() > 25) continue;
            return lastName;
        }
    }

    private String readAddressFromConsole() {
        while (true) {
            System.out.println(
                    "Введите адрес пользователя (макс. 100 символов)\n" +
                            "Нажмите Enter, чтобы пропусить ввод адреса.");
            String address = ConsoleHelper.readLine();
            if (address.length() > 100) continue;
            return address;
        }
    }

    @Override
    void showSuccessfulOperationMessage() {
        System.out.println(SUCCESS_MESSAGE);
    }

    private void showUserID(long user_id) {
        System.out.println("ID пользователя: " + user_id);
    }
}