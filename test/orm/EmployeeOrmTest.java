package orm;

import org.junit.Test;

import model._EmployeeModel;


public class EmployeeOrmTest {
    @Test
    public void isNotZero(){
        _EmployeeModel employee = (_EmployeeModel) new EmployeeOrm().find();

        //assertThat(valid.validate(), is(true));
    }
}
