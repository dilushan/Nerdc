/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package coded.frames;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Dula
 */
public class Add_Employees_to_Project extends javax.swing.JFrame {
    
    String projectName,department;

    /**
     * Creates new form Fill_data
     * @param projectName
     * @param departmeent
     */
    public Add_Employees_to_Project(String projectName, String departmeent) {
        this.projectName=projectName;
        this.department=departmeent;

        
        initComponents();       
        sql_to_table( "nerdc" );//the db name has to be given
        
    }


    /*******************             SQL part begins here
     * @param database***************/
   
    public final void sql_to_table(String database) {
        
        setTable1(database, jTable1,"employee");
        setTable2("coded", jTable2,projectName+"_"+department);//show table 2 if it is already existing in the database
    }
 
    private void setTable1(String database, JTable jTable, String existing_table){
        
        Connection con;
        //PreparedStatement preparedStatement;
        ResultSet resultSet;
        String s[]=new String[2];//for hold the data in sql row
        DefaultTableModel mod = (DefaultTableModel) jTable.getModel();//get the existing table data

        try {
            
                Class.forName("com.mysql.jdbc.Driver");                                                                             // this will load the MySQL driver, each DB has its own driver
                // setup the connection with the DB.
                con = DriverManager.getConnection("jdbc:mysql://localhost/" + database+ "?" + "user=root");
                //result set filter by name annd no
                //sorted by no in ASCENDING order
                resultSet = con.createStatement().executeQuery("SELECT eid,name FROM "+existing_table+" ORDER BY eid");
                
                while (resultSet.next()) {

                    s[0] = resultSet.getString("eid"); //get the column called no
                    s[1] = resultSet.getString("name"); //get the column called name

                    mod.addRow(s);
                    jTable.setModel(mod);
            }       
        } catch (SQLException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage(), "Fatal Error", JOptionPane.ERROR_MESSAGE); 
        }       
    }
    
    private void setTable2(String database, JTable jTable, String existing_table) {
        
        Connection con;
        //PreparedStatement preparedStatement;
        ResultSet resultSet;
        String s[]=new String[3];//for hold the data in sql row
        DefaultTableModel mod = (DefaultTableModel) jTable.getModel();//get the existing table data

        try {
            
                Class.forName("com.mysql.jdbc.Driver");                                                                             // this will load the MySQL driver, each DB has its own driver
                // setup the connection with the DB.
                con = DriverManager.getConnection("jdbc:mysql://localhost/" + database+ "?" + "user=root");
                //result set filter by name annd no
                //sorted by no in ASCENDING order
                resultSet = con.createStatement().executeQuery("SELECT emp_code,name,direct_indirect FROM "+existing_table+" ORDER BY emp_code");
                JOptionPane.showMessageDialog(rootPane, "\t Project already exists!\nYou will Navigate to the Manage Employee window");
                this.dispose();
               /*
                
                
                
                
                
                
                
                
                
                this is not disposing
                
                
                
                
                */ System.err.println("what is happening");
                new intermediate(projectName,department).setVisible(true);
                while (resultSet.next()) {

                    s[0] = resultSet.getString("emp_code"); //get the column called no
                    s[1] = resultSet.getString("name"); //get the column called name
                    s[2] = resultSet.getString("direct_indirect");

                    mod.addRow(s);
                    jTable.setModel(mod);
            }       
        } catch (SQLException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(rootPane, "New database created!\n \t no existing in the same name");
        }   
    }
    
    
    /*********** export table2 to NEW SQL DB  ****************/
    
    /****NOT completed*********/
    
    private void table_to_SQL(String database) {
        
    Connection con;
    ResultSet resultSet;
    String s[]=new String[3];//for hold the data in sql row
    DefaultTableModel mod = (DefaultTableModel) jTable2.getModel();//get the existing table data
    String query;
    String newTableName = (projectName+"_"+department);//design the new table name

        try {
            
                Class.forName("com.mysql.jdbc.Driver");                                                                             // this will load the MySQL driver, each DB has its own driver
                // setup the connection with the DB.
                con = DriverManager.getConnection("jdbc:mysql://localhost/" + database+ "?" + "user=root");

                //create a  new table by newTableName in the db only if not exists
                //then wipe all the data in the table
                
                query = "CREATE TABLE IF NOT EXISTS "+newTableName+"("
                        + "emp_code varchar(10),"
                        + "name varchar(30),"
                        + "direct_indirect varchar(10)"
                        + ")" ;
                
                con.createStatement().execute(query);//creating table in db
                //wipe data
                con.createStatement().execute("TRUNCATE TABLE "+newTableName);
                
                //for each row in the table
                for (int i = 0; i < jTable2.getRowCount(); i++) {
                    
                    //get the rows data
                    s[0]=mod.getValueAt(i, 0).toString();
                    s[1]=mod.getValueAt(i, 1).toString(); 
                    s[2]=mod.getValueAt(i, 2).toString(); 

                    //prepare the query by using the values in the table
                    query="INSERT INTO  " +newTableName+ "(emp_code, name ,direct_indirect)" + " VALUES "
                            + "( '" +s[0]+ "','" +s[1]+  "','"  +s[2]+ "')";
  
                    con.createStatement().execute(query);//update the table in sql named newProjectName

                }
                //show the parent frame
                this.dispose();
                new Create_Project().setVisible(true);
                JOptionPane.showMessageDialog(null, "All the Employees in the table are added Succefully");

        } catch (SQLException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage(), "Fatal Error", JOptionPane.ERROR_MESSAGE);           
        }
   
    }

    /******************* SQL part ends her
     * @return e********************/
    
    public String getProjectName(){
        //this is title in the frame
        return "New Project : "+ (this.projectName+"_"+this.department).toUpperCase();
    }
   
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
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

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(getProjectName());
        setBackground(java.awt.Color.gray);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setLocationByPlatform(true);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "EMP_Code", "Name"
            }
        ));
        jTable1.setToolTipText("Double Click to Edit");
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jTable2.setBorder(new javax.swing.border.MatteBorder(null));
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "EMP_Code", "Name", "Direct/ Indirect"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(21, 129, 129));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Emploees to be Add");

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(21, 129, 129));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Employees Added/ Existing");

        jLabel4.setText("Click to Add");

        jLabel5.setText("Click to Remove");

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setSelected(true);
        jRadioButton1.setText("Direct");

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setText("Indirect");

        jLabel6.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(21, 129, 129));
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel2)
                .addGap(52, 754, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(160, 160, 160)
                                .addComponent(jLabel4)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(168, 168, 168)
                                .addComponent(jLabel5))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(49, 49, 49)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addComponent(jLabel6)
                                .addGap(34, 34, 34)
                                .addComponent(jRadioButton1)
                                .addGap(79, 79, 79)
                                .addComponent(jRadioButton2)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jRadioButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jRadioButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addGap(9, 9, 9))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        
        //the row selected
        int row=jTable1.getSelectedRow();
        String selected;
        //get direct / indirect
        if (jRadioButton1.isSelected()) {
            selected= "Direct";
        } else {
            selected= "Indirect";
        }
        
        //the values in the selected row
        String s[] = {jTable1.getModel().getValueAt(row,0).toString(), jTable1.getModel().getValueAt(row,1).toString(),selected};
        
        //remove the row
        ((DefaultTableModel)jTable1.getModel()).removeRow( jTable1.getSelectedRow() );
        
        //add to the other table
        ((DefaultTableModel)jTable2.getModel()).addRow(s);
        
        
    }//GEN-LAST:event_jTable1MouseClicked

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        
        /*
        * remove from the table 2 when clicked
        */
        
        //the row selected
        int row=jTable2.getSelectedRow();
        
        //the values in the selected row
        String s[] = {jTable2.getModel().getValueAt(row,0).toString(), jTable2.getModel().getValueAt(row,1).toString()};
        
        //remove the row
        ((DefaultTableModel)jTable2.getModel()).removeRow( jTable2.getSelectedRow() );
        
        //add to the other table
        ((DefaultTableModel)jTable1.getModel()).addRow(s);
           
    }//GEN-LAST:event_jTable2MouseClicked

    
    /************* will have to export data in the table to new sql DB *************/
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       table_to_SQL("coded");     
    }//GEN-LAST:event_jButton1ActionPerformed

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
                new Add_Employees_to_Project("project1","CS").setVisible(true);
            }
        }); 
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    // End of variables declaration//GEN-END:variables
 
}
