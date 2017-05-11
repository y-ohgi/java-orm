package model;

public class _EmployeeModel extends Model{
    private Integer id;
    private String name;
    private String kana;
    private String email;
    private String pass;
    private Integer idDep;

    public _EmployeeModel(Integer id, String name, String kana, String email, String pass, Integer idDep) {
        this.id = id;
        this.name = name;
        this.kana = kana;
        this.email = email;
        this.pass = pass;
        this.idDep = idDep;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKana() {
        return kana;
    }

    public void setKana(String kana) {
        this.kana = kana;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Integer getIdDep() {
        return idDep;
    }

    public void setIdDep(Integer idDep) {
        this.idDep = idDep;
    }
}
