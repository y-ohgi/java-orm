package model;

public class EmployeeModel extends Model {
    private int id_employee;
    private String nm_employee;
    private String kn_employee;
    private String mail_address;
    private String password;
    private int id_department;
    private int age;

    public EmployeeModel(int id_employee, String nm_employee, String kn_employee, String mail_address, String password,
            int id_department, int age) {
        this.id_employee = id_employee;
        this.nm_employee = nm_employee;
        this.kn_employee = kn_employee;
        this.mail_address = mail_address;
        this.password = password;
        this.id_department = id_department;
        this.age = age;
    }

    public int getIdEmployee() {
        return id_employee;
    }

    public void setIdEmployee(int id_employee) {
        this.id_employee = id_employee;
    }

    public String getNmEmployee() {
        return nm_employee;
    }

    public void setNmEmployee(String nm_employee) {
        this.nm_employee = nm_employee;
    }

    public String getKnEmployee() {
        return kn_employee;
    }

    public void setKnEmployee(String kn_employee) {
        this.kn_employee = kn_employee;
    }

    public String getMailAddress() {
        return mail_address;
    }

    public void setMailAddress(String mail_address) {
        this.mail_address = mail_address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIdDepartment() {
        return id_department;
    }

    public void setIdDepartment(int id_department) {
        this.id_department = id_department;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }



}
