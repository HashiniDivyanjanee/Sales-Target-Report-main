package SMS_System;

import java.awt.BorderLayout;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.swing.JRViewer;
import Connection.DatabaseConnection;
import Controller.Employee_Controller;
import Controller.JobType_Controller;
import Model.Employee;
import Model.JobType;
import com.formdev.flatlaf.FlatLightLaf;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;

public class Commission_Report extends javax.swing.JFrame {

    public Commission_Report() {
        try {
            initComponents();
            DatabaseConnection.getInstance().getConnection();
            showEmp();
            showJob();
        } catch (SQLException ex) {
            Logger.getLogger(Commission_Report.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Preview = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cmbEmp = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        cmbJob = new javax.swing.JComboBox<>();
        btnFind = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        Preview.setBackground(new java.awt.Color(255, 255, 255));
        Preview.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout PreviewLayout = new javax.swing.GroupLayout(Preview);
        Preview.setLayout(PreviewLayout);
        PreviewLayout.setHorizontalGroup(
            PreviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 750, Short.MAX_VALUE)
        );
        PreviewLayout.setVerticalGroup(
            PreviewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 407, Short.MAX_VALUE)
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("EMPLOYEE");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("JOB TYPE");

        btnFind.setBackground(new java.awt.Color(0, 102, 204));
        btnFind.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnFind.setForeground(new java.awt.Color(255, 255, 255));
        btnFind.setText("FIND");
        btnFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFindActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Preview, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(cmbEmp, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(cmbJob, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnFind, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cmbEmp, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(cmbJob, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFind, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(Preview, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFindActionPerformed
        try {
            Map<String, Object> parameters = new HashMap<>();
            String jasperFilePath = "JasperReport/Commision.jrxml";

            String cEmployee = cmbEmp.getSelectedItem().toString();
            String cJob_Type = cmbJob.getSelectedItem().toString();
            
            System.out.println(cEmployee);
            System.out.println(cJob_Type);
            
            parameters.put("Employee", cEmployee);
            parameters.put("Job_Type", cJob_Type);

            System.out.println("File exists: " + new File(jasperFilePath).exists());

            InputStream input = new FileInputStream(new File(jasperFilePath));
            JasperDesign myJasperDesign = JRXmlLoader.load(input);

            System.out.println("Connection is closed: " + DatabaseConnection.getInstance().getConnection().isClosed());

            JasperReport myJasperReport = JasperCompileManager.compileReport(myJasperDesign);
            JasperPrint myJasperPrint = JasperFillManager.fillReport(myJasperReport, parameters, DatabaseConnection.getInstance().getConnection());

            JRViewer jr = new JRViewer(myJasperPrint);

            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    Preview.removeAll();
                    Preview.setLayout(new BorderLayout());
                    Preview.add(jr, BorderLayout.CENTER);
                    Preview.revalidate();
                    Preview.repaint();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnFindActionPerformed
                
    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
//        showSupp();
        showJob();
    }//GEN-LAST:event_formWindowOpened

    public void showEmp() {
        try {
            Employee_Controller emp_Controller = new Employee_Controller();
            List<Employee> employees = emp_Controller.getEmployee();
            for (Employee employee : employees) {
                cmbEmp.addItem(employee.getFname());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showJob() {
        try {
            JobType_Controller controller = new JobType_Controller();
            List<JobType> job = controller.getJobType();
            for (JobType jobtype : job) {
                cmbJob.addItem(jobtype.getJobType());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        try {
            FlatLightLaf.setup();
        } catch (Exception e) {
            e.printStackTrace();
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Commission_Report().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Preview;
    private javax.swing.JButton btnFind;
    private javax.swing.JComboBox<String> cmbEmp;
    private javax.swing.JComboBox<String> cmbJob;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
