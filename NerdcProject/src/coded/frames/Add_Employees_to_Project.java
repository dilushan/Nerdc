/*
 Create a new table for each new project and include the person to it
 And able to modify them later (modfy employees in project).
 */
package coded.frames;

import coded.others.Employee;
import coded.others.MySQLConnectionClass;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Dula
 */
public class Add_Employees_to_Project extends javax.swing.JFrame {

    String projectName, department;
    private int numEmployee;
    private double avgIntensive;

    /**
     * Creates new form Fill_data
     *
     * @param projectName
     * @param departmeent
     */
    public Add_Employees_to_Project(String projectName, String departmeent) {
        this.projectName = projectName;
        this.department = departmeent;

        setContentPane(new JLabel(new ImageIcon("./images/add emp.jpg")));
        initComponents();
        setLocationRelativeTo(null);
        sql_to_table("nerdc");//the db name has to be given
        setLocationRelativeTo(null);

    }

    /**
     * ***************** SQL part begins here
     *
     * @param database**************
     */
    public final void sql_to_table(String database) {

        setTable1(database, jTable1, "employee");
        setTable2(jTable2, projectName + "_" + department);//show table 2 if it is already existing in the database
        setProfit(); //set Profit
    }

    //right hand side table
    private void setTable1(String database, JTable jTable, String existing_table) {

        ResultSet resultSet;

        try {
            resultSet = MySQLConnectionClass.getInstance().queryStatement("SELECT eid,name FROM " + existing_table + " ORDER BY eid");
            jTable1.setModel(DbUtils.resultSetToTableModel(resultSet));

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage(), "Fatal Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    //left hand side table
    private void setTable2(JTable jTable, String existing_table) {

        ResultSet resultSet;
        DefaultTableModel mod = (DefaultTableModel) jTable.getModel();//get the existing table data

        try {
            resultSet = MySQLConnectionClass.getInstance().queryStatement("SELECT emp_id, name, direct_indirect FROM " + existing_table + " ORDER BY emp_id");
            jTable2.setModel(DbUtils.resultSetToTableModel(resultSet));
            JOptionPane.showMessageDialog(rootPane, "\t Project already exists!\nYou will Navigate to the Manage Employee window");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, "New database will be created!\n \t no existing in the same name");
        }
    }

    private void setProfit() {
        ResultSet resultSet;

        try {
            resultSet = MySQLConnectionClass.getInstance().queryStatement("SELECT profit FROM projects WHERE name='" + projectName + "'");
            resultSet.next();
            jTextField1.setText(resultSet.getString("profit"));
        } catch (SQLException ex) {
            //ex.printStackTrace();
            jTextField1.setText("Enter Profit");
        }
    }

    /**
     * ********* export table2 to NEW SQL DB ***************
     */
    private void table_to_SQL() {

        ResultSet resultSet;
        String s[] = new String[3];//for hold the data in sql row
        DefaultTableModel mod = (DefaultTableModel) jTable2.getModel();//get the existing table data
        String query;
        String newTableName = (projectName + "_" + department);//design the new table name

        try {
            query = "CREATE TABLE IF NOT EXISTS " + newTableName + ""
                    + "(" + "emp_id varchar(10)," + "name varchar(30)," + "direct_indirect varchar(10)" + ")";

            MySQLConnectionClass.getInstance().updateStatement(query);//creating table in db
            //wipe data
            MySQLConnectionClass.getInstance().updateStatement("TRUNCATE TABLE " + newTableName);

            numEmployee = jTable2.getRowCount();
            //for each row in the table
            for (int i = 0; i < jTable2.getRowCount(); i++) {

                //get the rows data
                s[0] = mod.getValueAt(i, 0).toString();
                s[1] = mod.getValueAt(i, 1).toString();
                s[2] = mod.getValueAt(i, 2).toString();

                //prepare the query by using the values in the table
                query = "INSERT INTO  " + newTableName + "(emp_id, name ,direct_indirect)" + " VALUES " + "( '" + s[0] + "','" + s[1] + "','" + s[2] + "')";

                MySQLConnectionClass.getInstance().updateStatement(query);//update the table in sql named newProjectName
            }

            //update the employee table with this new columns
            //when modyfying existing project below entries give errors. So try catch blok added
            try {
                query = "ALTER TABLE employee ADD " + projectName + "_direct VARCHAR(63), ADD " + projectName + "_indirect VARCHAR(63), ADD " + projectName + "_management VARCHAR(63), ADD " + projectName + "_research VARCHAR(63), ADD " + projectName + "_pool VARCHAR(63) ";
                MySQLConnectionClass.getInstance().updateStatement(query);
//                
            } catch (SQLException e) {
            }

            //show the parent frame
            this.dispose();
            //new Add_Project().setVisible(true);
            JOptionPane.showMessageDialog(null, "All the Employees in the table are added Succefully");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage(), "Fatal Error", JOptionPane.ERROR_MESSAGE);
        }
        avgIntensive = Double.parseDouble(jTextField1.getText()) * 0.9 / numEmployee;

        try {
            MySQLConnectionClass.getInstance().updateStatement("UPDATE employee SET " + projectName + "_direct = NULL, " + projectName + "_indirect = NULL");
            for (int i = 0; i < jTable2.getRowCount(); i++) {

                s[0] = mod.getValueAt(i, 0).toString();        //get the rows data
                s[1] = mod.getValueAt(i, 2).toString();
                //update employee intensive with average value
                if (s[1].equalsIgnoreCase("direct")) {
                    query = "UPDATE employee SET " + projectName + "_direct = " + Double.toString(avgIntensive) + " WHERE eid=" + s[0];
                } else {
                    query = "UPDATE employee SET " + projectName + "_indirect = " + Double.toString(avgIntensive) + " WHERE eid=" + s[0];
                }
                MySQLConnectionClass.getInstance().updateStatement(query);

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void profit_to_SQL() {

        try {
            MySQLConnectionClass.getInstance().updateStatement("CREATE TABLE IF NOT EXISTS projects (project_id INT NOT NULL AUTO_INCREMENT, name varchar(30), profit DOUBLE, PRIMARY KEY (project_id), UNIQUE(name))");//creating projects table in db
            MySQLConnectionClass.getInstance().updateStatement("INSERT INTO  projects (name, profit) VALUES ('" + projectName + "'," + jTextField1.getText() + ") ON DUPLICATE KEY UPDATE profit = " + jTextField1.getText());

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage(), "Fatal Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * ***************** SQL part ends here
     */
    public String getProjectName() {
        //this is title in the frame`
        return "New Project : " + (this.projectName + "_" + this.department).toUpperCase();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jLabel6 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(getProjectName());
        setBackground(java.awt.Color.gray);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
            "EMP_Code", "Name"
        }));
        jTable1.setToolTipText("Double Click to Edit");
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jTable2.setBorder(new javax.swing.border.MatteBorder(null));
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
            "EMP_Code", "Name", "Direct/ Indirect"
        }) {
            boolean[] canEdit = new boolean[]{
                true, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Emploees to be Add");

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 102));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Employees Added/ Existing");

        jLabel4.setText("Click to Add");

        jLabel5.setText("Click to Remove");

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setForeground(new java.awt.Color(255, 255, 255));
        jRadioButton1.setSelected(true);
        jRadioButton1.setText("Direct");

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setForeground(new java.awt.Color(255, 255, 255));
        jRadioButton2.setText("Indirect");

        jLabel6.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Add As");

        jButton1.setBackground(new java.awt.Color(21, 129, 129));
        jButton1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 0, 0));
        jButton1.setText("Add To DataBase");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel7.setText("Profit      : ");

        jTextField1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField1FocusGained(evt);
            }
        });

        jLabel8.setText("Rs");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(158, 158, 158)
                .addComponent(jLabel4)
                .addGap(0, 0, Short.MAX_VALUE))
                .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel6)
                .addGap(55, 55, 55)
                .addComponent(jRadioButton1)
                .addGap(40, 40, 40)
                .addComponent(jRadioButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(111, 111, 111)))
                .addContainerGap()));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jRadioButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jRadioButton2))
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jButton1)
                .addComponent(jLabel7)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel8))
                .addGap(9, 9, 9))
                .addGroup(layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))));

        pack();
    }// </editor-fold>                        

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:

        //the row selected
        int row = jTable1.getSelectedRow();
        String selected;
        //get direct / indirect
        if (jRadioButton1.isSelected()) {
            selected = "Direct";
        } else {
            selected = "Indirect";
        }

        //the values in the selected row
        String s[] = {jTable1.getModel().getValueAt(row, 0).toString(), jTable1.getModel().getValueAt(row, 1).toString(), selected};

        //remove the row
        ((DefaultTableModel) jTable1.getModel()).removeRow(jTable1.getSelectedRow());

        //add to the other table
        ((DefaultTableModel) jTable2.getModel()).addRow(s);


    }

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {

        /*
         * remove from the table 2 when clicked
         */
        //the row selected
        int row = jTable2.getSelectedRow();

        //the values in the selected row
        String s[] = {jTable2.getModel().getValueAt(row, 0).toString(), jTable2.getModel().getValueAt(row, 1).toString()};

        //remove the row
        ((DefaultTableModel) jTable2.getModel()).removeRow(jTable2.getSelectedRow());

        //add to the other table
        ((DefaultTableModel) jTable1.getModel()).addRow(s);

    }

    /**
     * *********** will have to export data in the table to new sql DB
     * ************
     */
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        if (isNumeric(jTextField1.getText())) {
            try {
                profit_to_SQL();
                table_to_SQL();

                //Maximum number of employees 500
                Employee[] e = new Employee[500];
                ResultSet resultSet;
                resultSet = MySQLConnectionClass.getInstance().queryStatement("SELECT * FROM employee where (" + projectName + "_direct!='NULL')or(" + projectName + "_indirect!='NULL')");

                for (int i = 0; i < jTable2.getRowCount(); i++) {

                    resultSet.next();

                    e[i] = new Employee(resultSet.getString("name"), resultSet.getString("eid"), Double.parseDouble(resultSet.getString("base_salary")), resultSet.getString("cat"), resultSet.getString("designation"), resultSet.getString("department"), 1.0);

                    if (resultSet.getString(projectName + "_direct") != null) {
                        MainFrame.p1.getDstaff().addEmployee(e[i]);
                    } else {
                        MainFrame.p1.getInstaff().addEmployee(e[i]);
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(Add_Employees_to_Project.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            JOptionPane.showMessageDialog(null, "Please enter a valid input to profit field", "Fatal Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void jTextField1FocusGained(java.awt.event.FocusEvent evt) {
        jTextField1.setText("");
    }

    private boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Add_Employees_to_Project.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Add_Employees_to_Project("project1", "CS").setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify                     
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration                   
}
