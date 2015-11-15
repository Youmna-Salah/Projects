import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ebrahim
 */
public class Frame extends java.awt.Frame implements MouseListener {

    /**
     * Creates new form Frame
     */
    DataOutputStream out;
    DataInputStream in;
    Socket client;
    String loginName;
    StyledDocument doc;
    SimpleAttributeSet defaultText;
    SimpleAttributeSet blueBoldText;
    SimpleAttributeSet redBoldText;
    boolean loggedIn;
    
    public static int ni(String s, int n) {
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == '%') {
                n--;
                if(n == 0) return i;
            }
        }
        return -1;
    }
    
    public static void composeMessage2(DataOutputStream out, String type, String message) throws IOException {
        out.writeUTF(type+"%" + message);
    }
   
    public static void composeMessage(DataOutputStream out, String type, String sender, String receiver, String message) throws IOException {
       out.writeUTF(type+"%" + sender+"%" + receiver+"%" + 0+"%" + message);
    }
   
    public static void composeMessage(DataOutputStream out, String type, String sender, String receiver, int ttl, String message) throws IOException {
        out.writeUTF(type+"%" + sender+"%" + receiver+"%" + ttl+"%" + message);
    }
    
    
    public void inFromServer(DataInputStream in) throws IOException, BadLocationException {
        String s = "";
        while(true) {
	    s = in.readUTF();
            if(s.length() >= 3) {
                if (s.substring(0, 3).equals("MSG")) {
                    doc.insertString(doc.getLength(), s.substring(ni(s, 1) + 1, ni(s, 2)) + ": " + s.substring(ni(s, 4) + 1) + "\n", defaultText );
                }
                else if(s.substring(0, 3).equals("LST")) {
                    jList1.setListData(s.substring(ni(s, 1) + 1).split("%"));
                }
                else if(s.substring(0, 3).equals("NOT")) { // notification 
                    doc.insertString(doc.getLength(), s.substring(ni(s, 1) + 1) + "\n", blueBoldText);
                }
                else if(s.substring(0, 3).equals("ACP")) { // u can log in 
                    loggedIn = true;
                    jButton1.setEnabled(true);
                    doc.insertString(doc.getLength(), s.substring(ni(s, 1) + 1) + "\n", blueBoldText);
                }
            }
	}
    }
    
    public Frame(Socket client, final DataInputStream in, DataOutputStream out) throws IOException, BadLocationException {
        this.client = client;
        this.in = in;
        this.out = out;
        initComponents();
        setLocationRelativeTo(null); // el window f nos el shasha 
        jButton1.addMouseListener(this);
        setVisible(true);
        doc = jTextPane1.getStyledDocument(); 
        defaultText = new SimpleAttributeSet(); 
        redBoldText = new SimpleAttributeSet();
        blueBoldText = new SimpleAttributeSet();
        StyleConstants.setForeground(redBoldText, Color.RED);
        StyleConstants.setBold(redBoldText, true);
        StyleConstants.setForeground(blueBoldText, Color.BLUE);
        StyleConstants.setBold(blueBoldText, true);
        
        doc.insertString(doc.getLength(), "Just connected to Server with Address " + client.getRemoteSocketAddress() + "\n", blueBoldText );
        
        Runnable runA = new Runnable() {
            public void run() {
            try {
		inFromServer(in);
            } catch (IOException e) {}
            catch (BadLocationException ex) {}
	}};
	Thread threadA = new Thread(runA, "threadA");// why thread A
        threadA.start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();

        setLocationRelativeTo(null);
        setMinimumSize(new java.awt.Dimension(800, 600));
        setName(""); // NOI18N
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jList1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(jList1);

        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 470, 207, 130));

        jLabel1.setFont(new java.awt.Font("Lucida Calligraphy", 1, 11)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Online Users");
        jLabel1.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2), javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 2)), javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2)));
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 410, 207, 30));

        jTextField1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.cyan, java.awt.Color.blue, java.awt.Color.cyan, java.awt.Color.blue));
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1KeyPressed(evt);
            }
        });
        add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(3, 413, 587, 184));

        jButton1.setFont(new java.awt.Font("Lucida Calligraphy", 1, 11)); // NOI18N
        jButton1.setText("Refresh");
        jButton1.setEnabled(false);
        add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 440, 207, 30));

        jTextPane1.setMargin(new java.awt.Insets(30, 5, 30, 5));
        jScrollPane1.setViewportView(jTextPane1);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 1, 797, 410));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Exit the Application
     */
    private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
        if(out!=null) try {
            composeMessage2(out, "BYE", "");
        } catch (IOException ex) {}
        System.exit(0);
    }//GEN-LAST:event_exitForm

    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if(!loggedIn) {
                try {
                    composeMessage2(out, "JON",  jTextField1.getText()); //join server 
                    loginName = jTextField1.getText();
                } catch (IOException ex) {}
                jTextField1.setText("");
            }
            else if(jList1.getSelectedValue()==null) {
                try {
                    doc.insertString(doc.getLength(), "Error: You didn't choose a recipient to your message\n", redBoldText); //jTextPane1.append("Error: You didn't choose a recipient to your message\n");
                } catch (BadLocationException ex) {}
            }
            else {
                try {
                    String t = jTextField1.getText();
                    composeMessage(out, "MSG", loginName, (String)jList1.getSelectedValue(), 4, t);
                    if(t.equals("BYE")){
                        System.exit(0);
                    }
                } catch (IOException ex) {}
                jTextField1.setText("");
            }
        }
    }//GEN-LAST:event_jTextField1KeyPressed

    /**
     * @param args the command line arguments
     */
    /*public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Frame().setVisible(true);
            }
        });
    }*/
    
    public void mouseClicked(MouseEvent e) {
        try {
            composeMessage2(out, "LST", loginName);
            jButton1.setEnabled(false);
            Runnable run = new Runnable() {
            public void run() {
                try {
                    //reenable Refresh after 10 seconds
                    Thread.sleep(10000);
                    jButton1.setEnabled(true);
                } catch (Exception e) {}
            }};
            Thread thread = new Thread(run); thread.start();
        } catch (IOException ex) {}
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JList jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextPane jTextPane1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}