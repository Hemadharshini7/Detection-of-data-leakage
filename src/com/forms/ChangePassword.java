/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * LoginForm.java
 *
 * Created on Dec 8, 2011, 10:46:36 AM
 */

package com.forms;

import com.dao.ConnectionManager;
import java.awt.HeadlessException;
import javax.swing.JOptionPane;
import org.netbeans.validation.api.Problem;
import org.netbeans.validation.api.builtin.Validators;
import org.netbeans.validation.api.ui.ValidationGroup;

import com.dao.UserDAO;
import com.helper.GetSetProperties;
import com.helper.SimpleCrypto;
import com.helper.UserSession;
import java.awt.event.KeyEvent;
import org.openide.util.Exceptions;

/**
 *
 * @author Admin
 */
public class ChangePassword extends javax.swing.JFrame {
 ValidationGroup vg;
    /** Creates new form LoginForm */
    public ChangePassword() {
        initComponents();
        swinghelper.SwingUtilities.setScreenCenter(this);

          vg= validationPanel1.getValidationGroup();
       //vg.add(jTextField1, ValidationStrategy.DEFAULT, null)
 vg.add(jPasswordField1, Validators.REQUIRE_NON_EMPTY_STRING,
          Validators.NO_WHITESPACE);

 vg.add(jPasswordField2, Validators.REQUIRE_NON_EMPTY_STRING,
          Validators.NO_WHITESPACE);
 vg.add(jPasswordField3, Validators.REQUIRE_NON_EMPTY_STRING,
          Validators.NO_WHITESPACE);
    }

    public boolean performLogin() throws HeadlessException {
        // TODO add your handling code here:
        Problem p = vg.validateAll();
        String msg = "";
        if (p != null) {
            msg = p.getMessage();
        }
        System.out.println("msg " + msg);
        if (msg.length() == 0) {
            jButton2.setEnabled(false);

            String UserPassword=jPasswordField1.getText();
            try {
                UserPassword = SimpleCrypto.encrypt(GetSetProperties.getProperty("SEED_KEY"), UserPassword);
            } catch (Exception ex) {
                Exceptions.printStackTrace(ex);
            }

            String oldUserPassword=jPasswordField3.getText();
            try {
                oldUserPassword = SimpleCrypto.encrypt(GetSetProperties.getProperty("SEED_KEY"), oldUserPassword);
            } catch (Exception ex) {
                Exceptions.printStackTrace(ex);
            }


           String query="update userinfo set UserPassword='%s',changepassword=CURRENT_TIMESTAMP where userid="+UserSession.loggedInUser.getUserId()+" and UserPassword='"+oldUserPassword+"'";
           query=String.format(query, UserPassword);

        int rows=ConnectionManager.executeUpdate(query, null);
            if (rows>0) {
                JOptionPane.showMessageDialog(this, "Password Updated Successfully!!!");
                this.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(this, "Incorrect Old password!!!");
                jPasswordField1.setText("");
                jPasswordField2.setText("");
                jPasswordField3.setText("");


                return true;
            }
        }
        return false;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jPasswordField1 = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jPasswordField2 = new javax.swing.JPasswordField();
        jPasswordField3 = new javax.swing.JPasswordField();
        validationPanel1 = new org.netbeans.validation.api.ui.ValidationPanel();

        jPanel1.setName("jPanel1"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance().getContext().getResourceMap(ChangePassword.class);
        setTitle(resourceMap.getString("title")); // NOI18N

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Change Password", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, resourceMap.getFont("jPanel2.border.titleFont"))); // NOI18N
        jPanel2.setToolTipText(resourceMap.getString("jPanel2.toolTipText")); // NOI18N
        jPanel2.setName("jPanel2"); // NOI18N

        jLabel2.setFont(new java.awt.Font("Verdana", 0, 12));
        jLabel2.setText("Old Password");
        jLabel2.setName("jLabel2"); // NOI18N

        jLabel3.setFont(new java.awt.Font("Verdana", 0, 12));
        jLabel3.setText("New Password");
        jLabel3.setName("jLabel3"); // NOI18N

        jButton2.setFont(new java.awt.Font("Verdana", 0, 12));
        jButton2.setText("Update");
        jButton2.setName("jButton2"); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jPasswordField1.setName("Password "); // NOI18N
        jPasswordField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPasswordField1KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jPasswordField1KeyTyped(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Verdana", 0, 12));
        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Verdana", 0, 12));
        jLabel4.setText("Confirm Pass");
        jLabel4.setName("jLabel4"); // NOI18N

        jPasswordField2.setName("jPasswordField2"); // NOI18N
        jPasswordField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPasswordField2KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jPasswordField2KeyTyped(evt);
            }
        });

        jPasswordField3.setName("jPasswordField3"); // NOI18N
        jPasswordField3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPasswordField3KeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jPasswordField3KeyTyped(evt);
            }
        });

        validationPanel1.setName("validationPanel1"); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPasswordField3, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPasswordField1, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPasswordField2, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(validationPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPasswordField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPasswordField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(validationPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton1)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.getAccessibleContext().setAccessibleName(resourceMap.getString("jPanel2.AccessibleContext.accessibleName")); // NOI18N
        jPanel2.getAccessibleContext().setAccessibleDescription(resourceMap.getString("jPanel2.AccessibleContext.accessibleDescription")); // NOI18N

        pack();
    }// </editor-fold>

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {                                         
      performLogin();
     jButton2.setEnabled(true);
    
    }                                        

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:

           this.setVisible(false);

    }                                        

    private void jPasswordField1KeyTyped(java.awt.event.KeyEvent evt) {                                         
        // TODO add your handling code here:
     


    }                                        

    private void jPasswordField1KeyPressed(java.awt.event.KeyEvent evt) {                                           
        // TODO add your handling code here:
           System.out.println(" evt.getKeyCode() "+evt.getKeyCode());
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
performLogin();
        }
    }                                          

    private void jPasswordField2KeyPressed(java.awt.event.KeyEvent evt) {                                           
        // TODO add your handling code here:
    }                                          

    private void jPasswordField2KeyTyped(java.awt.event.KeyEvent evt) {                                         
        // TODO add your handling code here:
    }                                        

    private void jPasswordField3KeyPressed(java.awt.event.KeyEvent evt) {                                           
        // TODO add your handling code here:
    }                                          

    private void jPasswordField3KeyTyped(java.awt.event.KeyEvent evt) {                                         
        // TODO add your handling code here:
    }                                        

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

                    //     UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
                //    UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
                //     UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");

                    // swinghelper.SwingUtilities.applyLiquidtheme();
                    new ChangePassword().setVisible(true);
             
             
            }
        });
    }

    // Variables declaration - do not modify
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JPasswordField jPasswordField2;
    private javax.swing.JPasswordField jPasswordField3;
    private org.netbeans.validation.api.ui.ValidationPanel validationPanel1;
    // End of variables declaration

}