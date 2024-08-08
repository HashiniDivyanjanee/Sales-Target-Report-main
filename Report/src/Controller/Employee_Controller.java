package Controller;


import Connection.DatabaseConnection;
import Model.Employee;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.Statement;

public class Employee_Controller {
    public List<Employee> getEmployee()throws SQLException{
        
        String sql = "SELECT DISTINCT `Fname` FROM `employee` ORDER BY `Fname` ASC;";
        List <Employee> employees = new ArrayList<>();
        
        try (Connection connection = DatabaseConnection.getInstance().getConnection(); Statement stmt = connection.createStatement(); ResultSet result = stmt.executeQuery(sql)){
            
            while(result.next()){
                Employee emp = new Employee();
                emp.setFname(result.getString("Fname"));
                employees.add(emp);
            }           
        } catch (Exception e) {
            
        }
        return employees;       
    };
}
