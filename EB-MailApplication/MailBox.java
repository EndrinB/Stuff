import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MailBox extends JPanel implements MouseListener,ActionListener{
   User user;
   Database data;
   JFrame frame;
   JTextArea box,typeBox;
   JTextField mailTo = new JTextField();
   JScrollPane scroll;
   JButton button;
   int composing;
   boolean saveClicked;
  
   public MailBox(User user,JFrame frame,Database data){
      this.data = data;
      this.user = user;
      this.frame = frame;    
      user.getUserData();
      user.setData();
      addMailBox();
      frame.getContentPane().add(this);
      frame.addMouseListener(this);
      
      
      frame.setVisible(true);
      
        
   }
   private void addMailBox(){
      box = new JTextArea();
      box.setBounds(221,98,100,210);  
      JScrollPane d = new JScrollPane(box);   
      d.setBounds(205,75,458,253); 
      box.setEditable(false);
      frame.add(d); 
   
   }
   public void paintComponent(Graphics g){
      ImageIcon image = new ImageIcon("EB-MAIL.png");
      image.paintIcon(this,g,-5,-10);  
      if(composing == 1){
         ImageIcon sender = new ImageIcon("Message_sender.png");
         sender.paintIcon(this,g,5,328);  
      }
      else if(composing == 2){
         ImageIcon saver = new ImageIcon("Memo_saver.png");
         saver.paintIcon(this,g,5,328);  
      }
   
      
   }   
   
   public void mousePressed(MouseEvent e) {
   }

   public void mouseReleased(MouseEvent e) {
   
   }

   public void mouseEntered(MouseEvent e) {
   
   }

   public void mouseExited(MouseEvent e) {
   
   }

   public void mouseClicked(MouseEvent e) {
      System.out.println(e.getX() + " " + e.getY());
      delete(e.getX(),e.getY());
      inbox(e.getX(),e.getY());
      sent(e.getX(),e.getY());
      memo(e.getX(),e.getY());
      compose(e.getX(),e.getY());
      composeMemo(e.getX(),e.getY());
      composeMail(e.getX(),e.getY());
   }
   public void actionPerformed(ActionEvent e){
      if(e.getSource() == button && composing == 2){
         String memos = typeBox.getText().trim();      
         if(memos.equals("")){
            JOptionPane.showMessageDialog(null,"Nothing to save");
         }else{
            if(user.getMessage(0).equals("")){
               user.removeMessage(1);
               user.addMessage(memos);
               JOptionPane.showMessageDialog(null,"Message saved");
               removeSaveMenu();
            }else{
               user.addMessage(memos);
               JOptionPane.showMessageDialog(null,"Message saved");
               removeSaveMenu();
            }
         }
      }
      if(e.getSource() == button && composing == 1){
         String mail = typeBox.getText().trim();      
         String receiver = mailTo.getText();
         if(!data.containsUserName(receiver)){
            JOptionPane.showMessageDialog(null,"User doesn't exist");
         }
         else{
            int keyTo = data.getKey(receiver);
            if(mail.equals("")){
               JOptionPane.showMessageDialog(null,"Nothing to save");
            }else{
            
               user.sendMail(mail,keyTo);
               JOptionPane.showMessageDialog(null,"Message sent");
               removeMailMenu();
            }
          
         }
      }
      
   }
   private void delete(int x , int y){
      if((x >= 674 && x <= 713) && (y >= 306 && y <= 355)){
         deleteCheckBox();
      }
   }
   private void inbox(int x , int y){
      if((x >= 31 && x <= 210) && (y >= 104 && y <= 140)){
         int count = 0;
         int mails = user.getMailCount();
         if(user.getMail(0).equals("") ) {
            box.setText("");
            box.append("No mails");}
         else{
            box.setText("");
            while(count < mails){
               box.append(count+1 + ". \"" + user.getMail(count)+"\" from " + data.getUser(user.getFromKey(count)).getName() + "\n");
               count++;
            }
         }
      }
   }
   private void sent(int x , int y){
      if((x >= 31 && x <= 206) && (y >= 148 && y <= 186)){
       int count = 0;
         int mails = user.getMailSCount();
         if(user.getMailSent(0).equals("") ) {
            box.setText("");
            box.append("No mails sent");}
         else{
            box.setText("");
            while(count < mails){
               box.append(count+1 + ". \"" + user.getMailSent(count)+"\" to " + data.getUser(user.getToKey(count)).getName() + "\n");
               count++;
            }
         }
      }
   }
   private void memo(int x , int y){
      if((x >= 31 && x <= 210) && (y >= 192 && y <= 230)){
         int count = 0;
         int messages = user.getMsgCount();
         System.out.println(user.getMsgCount());
         System.out.println("\""+user.getMessage(0)+ "\"");
         if(user.getMessage(0).equals("") ) {
            box.setText("");
            box.append("No memos saved");}
         else{
            box.setText("");
            while(count < user.getMsgCount()){          
               box.append(count+1 + "." + user.getMessage(count)+"\n") ;
               count++;
            }
         }
      }
   }
   
   private void compose(int x , int y){
      if((x >= 34 && x <= 194) && (y >= 256 && y <= 342)){
         composing = 2;
         createSaveMenu();
      }
   }
   private void composeMemo(int x , int y){
      if((x >= 171 && x <= 226) && (y >= 358 && y <= 382)){
         if(composing == 2){}
         else{
            removeMailMenu();
            composing = 2;
            createSaveMenu();
         }
      }
   }
   private void composeMail(int x , int y){
      
      if((x >= 171 && x <= 227) && (y >= 391 && y <= 414)){
         if(composing ==1){}
         else{
            removeSaveMenu();
            composing = 1;
            createMailMenu();
         }
      }  
   }    
    
   
   private void createSaveMenu(){
      frame.remove(this);
      typeBox = new JTextArea("Add memo");
      typeBox.setBounds(235,340,400,110); 
      button = new JButton("Save");
      button.setBounds(365,454,140,28);
      button.addActionListener(this);
      frame.add(button);
      scroll = new JScrollPane(typeBox);   
      scroll.setBounds(235,340,400,110); 
      frame.add(scroll);
      frame.getContentPane().add(this); 
      frame.repaint();
   }
   private void createMailMenu(){
      frame.remove(this);
      mailTo = new JTextField();
      mailTo.setBounds(285,453,215,28);
      typeBox = new JTextArea("Add message");
      typeBox.setBounds(235,340,400,110); 
      button = new JButton("Send");
      button.setBounds(505,453,135,28);
      button.addActionListener(this);
      mailTo.addActionListener(this);
      frame.add(button);
      frame.add(mailTo);
      scroll = new JScrollPane(typeBox);   
      scroll.setBounds(235,340,400,110); 
      frame.add(scroll);
      frame.getContentPane().add(this); 
      frame.repaint();
   }
   private void removeSaveMenu(){
      frame.remove(typeBox);
      frame.remove(button);
      frame.remove(scroll);
      composing = 0;
      frame.repaint();
   }
   private void removeMailMenu(){
      frame.remove(typeBox);
      frame.remove(button);
      frame.remove(scroll);
      frame.remove(mailTo);
      composing = 0;
      frame.repaint();
   }
   private void deleteCheckBox(){
      Deleter de = new Deleter(user);
   
   }
   
}