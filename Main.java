import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EmployeeDAO employeeDAO = new EmployeeDAO();

        boolean running = true;

        while (running) {
            System.out.println("\n--- Employee Management System ---");
            System.out.println("1. Add employee");
            System.out.println("2. View employees");
            System.out.println("3. Search employee");
            System.out.println("4. Update employee");
            System.out.println("5. Delete employee");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Name: ");
                    String name = scanner.nextLine();

                    System.out.print("Surname: ");
                    String surname = scanner.nextLine();

                    System.out.print("Email: ");
                    String email = scanner.nextLine();

                    System.out.print("Department: ");
                    String department = scanner.nextLine();

                    Employee employee = new Employee(name, surname, email, department);
                    employeeDAO.addEmployee(employee);
                    break;

                case "2":
                    List<Employee> employees = employeeDAO.getEmployees();

                    if (employees.isEmpty()) {
                        System.out.println("There are no employees to display.");
                    } else {
                        for (Employee emp : employees) {
                            System.out.println(
                                    emp.getId() + " - " +
                                    emp.getName() + " " +
                                    emp.getSurname() +
                                    " | " + emp.getEmail() +
                                    " | " + emp.getDepartment()
                            );
                        }
                    }
                    break;

                case "3":
                    System.out.print("Enter the employee name to search: ");
                    String searchName = scanner.nextLine();
                    employeeDAO.searchEmployee(searchName);
                    break;

                case "4":
                    try {
                        System.out.print("Enter employee ID to update: ");
                        int id = Integer.parseInt(scanner.nextLine());

                        System.out.print("New name: ");
                        String newName = scanner.nextLine();

                        System.out.print("New surname: ");
                        String newSurname = scanner.nextLine();

                        System.out.print("New email: ");
                        String newEmail = scanner.nextLine();

                        System.out.print("New department: ");
                        String newDepartment = scanner.nextLine();

                        employeeDAO.updateEmployee(
                                id, newName, newSurname, newEmail, newDepartment
                        );
                    } catch (NumberFormatException e) {
                        System.out.println("Please enter a valid employee ID.");
                    }
                    break;

                case "5":
                    System.out.print("Enter employee ID to delete: ");

                    try {
                        int id = Integer.parseInt(scanner.nextLine());
                        employeeDAO.deleteEmployee(id);
                    } catch (NumberFormatException e) {
                        System.out.println("Please enter a valid number.");
                    }
                    break;

                case "6":
                    running = false;
                    System.out.println("Goodbye.");
                    break;

                default:
                    System.out.println("Please choose an option from 1 to 6.");
            }
        }

        scanner.close();
    }
}
