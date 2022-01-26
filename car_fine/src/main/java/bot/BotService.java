package bot;

import java.sql.*;

public class BotService implements BotUtils{
    public static String findUserByPassportNumber(String passportNumber) {
        StringBuilder text = new StringBuilder();
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from find_user_by_passport_number(?)");
            preparedStatement.setString(1, passportNumber);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                text.append("Id: ").append(resultSet.getString(1)).append("\n");
                text.append("Name: ").append(resultSet.getString(2)).append("\n");
                text.append("Passport number: ").append(resultSet.getString(3)).append("\n");
                text.append("Given date: ").append(resultSet.getString(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return text.isEmpty() ? NOT_FOUND : text.toString();
    }

    public static String findCarByNumber(String number) {
        StringBuilder text = new StringBuilder();
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from find_car_by_number(?)");
            preparedStatement.setString(1, number);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                text.append("Id: ").append(resultSet.getString(1)).append("\n");
                text.append("Name: ").append(resultSet.getString(2)).append("\n");
                text.append("Number: ").append(resultSet.getString(3)).append("\n");
                text.append("User id: ").append(resultSet.getString(4)).append("\n");
                text.append("Date: ").append(resultSet.getString(5)).append("\n\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return text.isEmpty() ? NOT_FOUND : text.toString();
    }

    public static String sendFinesByCarNumber(String carNumber) {
        StringBuilder text = new StringBuilder();
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from get_fines_by_car_number(?)");
            preparedStatement.setString(1, carNumber);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                text.append("Name: ").append(resultSet.getString(1)).append("\n");
                text.append("Number: ").append(resultSet.getString(2)).append("\n");
                text.append("Amount: ").append(resultSet.getString(3)).append("\n");
                text.append("Created date: ").append(resultSet.getString(4)).append("\n");
                text.append("Payed date: ").append(resultSet.getString(5)).append("\n");
                text.append("Is payed?: ").append(resultSet.getString(6)).append("\n\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return text.isEmpty() ? NOT_FOUND : text.toString();
    }

    public static String sendFinesByMonthAndCarNumber(String monthAndCarNumber) {
        StringBuilder text = new StringBuilder();
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from get_fines_by_month_and_car_number(?,?)");
            preparedStatement.setString(1, monthAndCarNumber.substring(0, monthAndCarNumber.indexOf(' ')));
            preparedStatement.setString(2, monthAndCarNumber.substring(monthAndCarNumber.indexOf(' ') + 1));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                text.append("Name: ").append(resultSet.getString(1)).append("\n");
                text.append("Number: ").append(resultSet.getString(2)).append("\n");
                text.append("Amount: ").append(resultSet.getString(3)).append("\n");
                text.append("Date: ").append(resultSet.getString(4)).append("\n");
                text.append("Payed date: ").append(resultSet.getString(5)).append("\n");
                text.append("Is payed?: ").append(resultSet.getString(6)).append("\n\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return text.isEmpty() ? NOT_FOUND : text.toString();
    }

    public static String sendFinesByUserId(String userId) {
        StringBuilder text = new StringBuilder();
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from get_fines_by_user_id(?)");
            preparedStatement.setString(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                text.append("Id: ").append(resultSet.getString(1)).append("\n");
                text.append("Name: ").append(resultSet.getString(2)).append("\n");
                text.append("Number: ").append(resultSet.getString(3)).append("\n");
                text.append("Amount: ").append(resultSet.getString(4)).append("\n");
                text.append("Date: ").append(resultSet.getString(5)).append("\n");
                text.append("Payed date: ").append(resultSet.getString(6)).append("\n");
                text.append("Is payed?: ").append(resultSet.getString(7)).append("\n\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return text.isEmpty() ? NOT_FOUND : text.toString();
    }
}
