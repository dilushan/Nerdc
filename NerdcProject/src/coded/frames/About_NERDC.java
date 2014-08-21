/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package coded.frames;

/**
 *
 * @author  sira
 */
public class About_NERDC extends javax.swing.JFrame {

    /**
     * Creates new form About_NERDC
     */
    public About_NERDC() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        back = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("NERDC");

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel1.setText("     NERDC - National Engineering and Development Centre");

        jTextPane1.setBackground(new java.awt.Color(204, 204, 204));
        jTextPane1.setText(" \t\nVISION\n \nTo be the premier Engineering Research and Development Centre in the country and to be able to make major contributions to the economic and social development of the people of Sri Lanka.\n \nMISSION\n \nTo engage in research and development activities that would have a direct bearing on the industrial development of Sri Lanka and on the improvement of the living standards of the people, and thereby develop technologies that would help in the sustainable utilization of her human and material resources towards the economic development of the country.\n \n--------------------------------------------------------------------------------------------------------------------------\n \nOBJECTIVES\n \n\tTo promote direct and indirect mechanism of technology transfer and offer counsel to appropriate government and private institutions in Sri Lanka, when required to do so;\n\tTo promote the optimal exploitation of the country’s human and material resources, particularly labour and raw material resources by promoting the growth of suitable technology;\n\tTo design, manufacture and test prototype machinery, pilot plants as demanded by industrial, commercial and other end users in an economical manner;\n\tTo keep pace with global trends in engineering knowledge relating to research, design & development through cooperation with international & national programmes and agencies;\n\tTo offer engineering consultancy services to public and private sector enterprise;\n\tTo undertake research and promote indigenous technology;\n\tTo broaden the base of the country’s engineering and industrial design and research capabilities;\n\tTo promote grassroots innovations and bring them into the mainstream of National Innovation system.\n\tTo make provision for purpose connected with engineering, research and development related to matters aforesaid;\n\tTo develop a critical mass of highly talented human resource base to achieve the above objectives.\n \n--------------------------------------------------------------------------------------------------------------------------\n \nBRIEF HISTORY OF NERDC\n \nIn 1974 Eng. DLO Mendis , a planning ministry representative of the Industrial Development Board was request by Mr. Hector Abayawardana to plan out an Institution to bridge the gap between an Industrial extension service organization as IDB and scientific research institute as the CISIR. Eng. DLO Mendis wrote the cabinet paper, which was agreed by the Hon. Minister of Industries and scientific affairs T.B. Subasinghe . Thus the NERDC was in acted by an act of parliament under Special gazette Notification (No 124/6) was published under the Industrial Corporation Act No. 49 of 1957, on 14 August 1974. NERDC became operational during the latter part of 1974 , with as its first chairman. The centre was located from a Small section of the Industrial Development Board complex at Katubadda Moratuwa, with the intake of support staff as engineers and clerical grades, subsequently the office was locates at a more space building at Gall roar. Colombo-03. In September 1978 office was shifted to present location of IDB Industrial Estate Ekala Ja-Ela.\n \nDuring last 33 years following facilities added for increase research activities and industrial services\n       1986 - Setup Energy Management Centre with UNDP assistance\n       1998 - Established Technology Park\n       2002 - Established Centre of Manufacturing Excellence with ADB assistance\n       2003 - Materials Lab & Hostel for trainees established with ADB assistance\n       2004 - Solar PV & Lamp testing labs receive international accreditation\n       2006 - Setting up an Engineering Museum commenced\n       2005 - LAN in operation with dedicated lease line\n       2007 - Setting up a Mechatronic Lab & environmental laboratory\n\n");
        jScrollPane1.setViewportView(jTextPane1);

        back.setIcon(new javax.swing.ImageIcon(getClass().getResource("/coded/resources/back.jpg"))); // NOI18N
        back.setText("back");
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(back))
                .addContainerGap(84, Short.MAX_VALUE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(back)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
        dispose();
        new MainFrame().setVisible(true);
    }//GEN-LAST:event_backActionPerformed

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
            java.util.logging.Logger.getLogger(About_NERDC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(About_NERDC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(About_NERDC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(About_NERDC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new About_NERDC().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton back;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextPane jTextPane1;
    // End of variables declaration//GEN-END:variables
}
