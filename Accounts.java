
import java.sql.*;
import java.util.Random;
import java.util.Scanner;

public class Accounts {
    private Connection connection;
    private Scanner scanner;
    public Accounts(Connection connection, Scanner scanner){
        this.connection = connection;
        this.scanner = scanner;

    }




    public long open_account(String email){
        if(!account_exist(email)) {
            String open_account_query = "INSERT INTO Accounts(account_number, full_name, email, balance, security_pin) VALUES(?, ?, ?, ?, ?)";
            scanner.nextLine();
            System.out.print("Enter Full Name: ");
            String full_name = scanner.nextLine();
            System.out.print("Enter Initial Amount: ");
            double balance = scanner.nextDouble();
            scanner.nextLine();
            System.out.print("Enter Security Pin: ");
            String security_pin = scanner.nextLine();
            try {
                long account_number = generateAccountNumber();
                PreparedStatement preparedStatement = connection.prepareStatement(open_account_query);
                preparedStatement.setLong(1, account_number);
                preparedStatement.setString(2, full_name);
                preparedStatement.setString(3, email);
                preparedStatement.setDouble(4, balance);
                preparedStatement.setString(5, security_pin);
                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    return account_number;
                } else {
                    throw new RuntimeException("Account Creation failed!!");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        throw new RuntimeException("Account Already Exist");

    }

    public long getAccount_number(String email) {
        String query = "SELECT account_number from Accounts WHERE email = ?";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return resultSet.getLong("account_number");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        throw new RuntimeException("Account Number Doesn't Exist!");
    }



    private Long generateAccountNumber() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                "SELECT account_number FROM Accounts LIMIT 1"
            );

            if (resultSet.next()) {
                // Account(s) already exist — return null or handle elsewhere
                return null;
            } else {
                // No accounts exist — generate random new account number
                Random rd = new Random();
                int randomPart = rd.nextInt(9000) + 1000;  // 4-digit number (1000–9999)
                long accNo = 5000000000L + randomPart;     // Creates account like 5000006721
                return accNo;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    public boolean account_exist(String email){
        String query = "SELECT account_number from Accounts WHERE email = ?";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return true;
            }else{
                return false;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;

    }
}
