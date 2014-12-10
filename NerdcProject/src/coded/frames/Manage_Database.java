/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coded.frames;

import java.awt.event.KeyEvent;

/**
 *
 * @author sira
 */
public class Manage_Database extends javax.swing.JFrame {

    /**
     * Creates new form Manage_Database
     */
    public Manage_Database() {
        initComponents();
        setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        add_employee = new javax.swing.JButton();
        delete_employee = new javax.swing.JButton();
        Edit_employee = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Manage Employees");
        setLocationByPlatform(true);

        add_employee.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        add_employee.setText("Add Employee");
        add_employee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_employeeActionPerformed(evt);
            }
        });
        add_employee.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                add_employeeKeyPressed(evt);
            }
        });

        delete_employee.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        delete_employee.setText("Delete Employee");
        delete_employee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delete_employeeActionPerformed(evt);
            }
        });
        delete_employee.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                delete_employeeKeyPressed(evt);
            }
        });

        Edit_employee.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        Edit_employee.setText("Edit Employee");
        Edit_employee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Edit_employeeActionPerformed(evt);
            }
        });
        Edit_employee.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Edit_employeeKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(Edit_employee, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
                    .addComponent(add_employee, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(delete_employee, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE))
                .addContainerGap(133, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addComponent(add_employee, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(delete_employee, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Edit_employee, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(118, 118, 118))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void add_employeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_employeeActionPerformed
        dispose();
        new Add_Employee().setVisible(true);
    }//GEN-LAST:event_add_employeeActionPerformed

    private void delete_employeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delete_employeeActionPerformed
        dispose();
        new Delete_Employee().setVisible(true);
    }//GEN-LAST:event_delete_employeeActionPerformed

    private void add_employeeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_add_employeeKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            add_employee.doClick();
        }
    }//GEN-LAST:event_add_employeeKeyPressed

    private void delete_employeeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_delete_employeeKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            add_employee.doClick();
        }
    }//GEN-LAST:event_delete_employeeKeyPressed

    private void Edit_employeeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Edit_employeeKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            add_employee.doClick();
        }
    }//GEN-LAST:event_Edit_employeeKeyPressed

    private void Edit_employeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Edit_employeeActionPerformed

        dispose();
        new Edit_Employee().setVisible(true);
    }//GEN-LAST:event_Edit_employeeActionPerformed

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Manage_Database.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Manage_Database.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Manage_Database.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Manage_Database.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Manage_Database().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Edit_employee;
    private javax.swing.JButton add_employee;
    private javax.swing.JButton delete_employee;
    // End of variables declaration//GEN-END:variables
}
