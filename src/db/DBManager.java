package db;

import model.Students;
import org.postgresql.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DBManager {
    private static Connection connection;

    static {
        try {

            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5434/myfirstjavaeeapp", "postgres", "postgres");

        }catch (Exception e){
            System.out.println("You have some error");
            e.printStackTrace();
        }
    }

    public static List<Students> getAllStudents(){
        List<Students> students = new ArrayList<>();

        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM students");
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                String birthdate = resultSet.getString("birthdate");
                String city = resultSet.getString("city");
                students.add(new Students(id, name, surname,birthdate,city));
            }
            statement.close();
        }catch (Exception exception){
            exception.printStackTrace();
        }

        return students;
    }

    public static void addStudent(Students student){
        try {

            PreparedStatement statement = connection.prepareStatement("" +
                    "INSERT INTO students (name,surname,birthdate,city) " +
                    "VALUES (?,?,?,?)");
            statement.setString(1, student.getName());
            statement.setString(2, student.getSurname());
            statement.setString(3, student.getBirthdate());
            statement.setString(4, student.getCity());
            int rows = statement.executeUpdate();
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
