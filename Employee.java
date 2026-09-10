public class Employee {
    private int id;
    private String name;
    private String surname;
    private String email;
    private String department;

    public Employee(int id, String name, String surname, String email, String department) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.department = department;
    }

    public Employee(String name, String surname, String email, String department) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.department = department;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getDepartment() {
        return department;
    }
}
