package orm;

import org.junit.Test;

import model.EmployeeModel;


public class EmployeeOrmTest {
    @Test
    public void isNotZero(){
        EmployeeModel employee = (EmployeeModel) new EmployeeOrm().find();

        //assertThat(valid.validate(), is(true));
    }
}
