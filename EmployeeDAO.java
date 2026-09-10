import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {

    public void addEmployee(Employee employee) {
        String sql = "INSERT INTO employees (name, surname, email, department) VALUES (?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, employee.getName());
            statement.setString(2, employee.getSurname());
            statement.setString(3, employee.getEmail());
            statement.setString(4, employee.getDepartment());

            statement.executeUpdate();
            System.out.println("Employee added successfully.");

        } catch (SQLException e) {
            System.out.println("Could not add employee: " + e.getMessage());
        }
    }

    public List<Employee> getEmployees() {
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT * FROM employees";

        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                Employee employee = new Employee(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("surname"),
                        resultSet.getString("email"),
                        resultSet.getString("department")
                );

                employees.add(employee);
            }

        } catch (SQLException e) {
            System.out.println("Could not load employees: " + e.getMessage());
        }

        return employees;
    }

    public void searchEmployee(String name) {
        String sql = "SELECT * FROM employees WHERE name LIKE ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, "%" + name + "%");

            try (ResultSet resultSet = statement.executeQuery()) {
                boolean found = false;

                while (resultSet.next()) {
                    found = true;
                    System.out.println(
                            resultSet.getInt("id") + " - " +
                            resultSet.getString("name") + " " +
                            resultSet.getString("surname") + " - " +
                            resultSet.getString("department")
                    );
                }

                if (!found) {
                    System.out.println("No employee found.");
                }
            }

        } catch (SQLException e) {
            System.out.println("Could not search employees: " + e.getMessage());
        }
    }

    public void updateEmployee(int id, String name, String surname, String email, String department) {
        String sql = "UPDATE employees SET name = ?, surname = ?, email = ?, department = ? WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, name);
            statement.setString(2, surname);
            statement.setString(3, email);
            statement.setString(4, department);
            statement.setInt(5, id);

            int rows = statement.executeUpdate();

            if (rows > 0) {
                System.out.println("Employee updated successfully.");
            } else {
                System.out.println("Employee was not found.");
            }

        } catch (SQLException e) {
            System.out.println("Could not update employee: " + e.getMessage());
        }
    }

    public void deleteEmployee(int id) {
        String sql = "DELETE FROM employees WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            int rows = statement.executeUpdate();

            if (rows > 0) {
                System.out.println("Employee deleted.");
            } else {
                System.out.println("Employee was not found.");
            }

        } catch (SQLException e) {
            System.out.println("Could not delete employee: " + e.getMessage());
        }
    }
}
