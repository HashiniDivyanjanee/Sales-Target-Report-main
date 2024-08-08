package Controller;


import Connection.DatabaseConnection;
import Model.JobType;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.Statement;

public class JobType_Controller {
    public List<JobType> getJobType()throws SQLException{
        
        String sql = "SELECT DISTINCT `Job_Type` FROM `a_job_card` ORDER BY `Job_Type` ASC;";
        List <JobType> job = new ArrayList<>();
        
        try (Connection connection = DatabaseConnection.getInstance().getConnection(); Statement stmt = connection.createStatement(); ResultSet result = stmt.executeQuery(sql)){
            
            while(result.next()){
                JobType jobtypes = new JobType();
                jobtypes.setJobType(result.getString("Job_Type"));
                job.add(jobtypes);
            }           
        } catch (Exception e) {
            
        }
        return job;       
    };
}
