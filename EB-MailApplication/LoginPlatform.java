import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class LoginPlatform extends JPanel implements MouseListener{
   JFrame frame;
   JTextField name;
   JPasswordField password;
   LoginManager manager;
   boolean mouseOverLogin,mouseOverSignUp,userNotFoundError,passwordError,nameTaken;
   
   public LoginPlatform(Database data){
     manager = new LoginManager(data);
     frame = new JFrame();
     frame.setSize(825,533);
     addTextFields();
     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     frame.setResizable(false);
     frame.getContentPane().add(this);
     frame.addMouseListener(this);
     JLabel c = new JLabel("ERROR");
     frame.setVisible(true);
   }
   public void paintComponent(Graphics g){
          ImageIcon image = new ImageIcon("EB-MAIL_LOGIN.png");
         image.paintIcon(this,g,10,0);
         if(mouseOverLogin){
         ImageIcon loginClicked = new ImageIcon("LoginButton_Clicked.png");
         loginClicked.paintIcon(this,g,211,305);
        }
        if(mouseOverSignUp){
         ImageIcon signClicked = new ImageIcon("SignButton_Clicked.png");
         signClicked.paintIcon(this,g,431,304);
        }
        if(userNotFoundError){
        g.setColor(Color.red);
        Font errorFont = new Font("Small Fonts", Font.BOLD,16);
        g.setFont(errorFont);
        g.drawString("User not found",593,180);
        }
        if(nameTaken){
        g.setColor(Color.red);
        Font errorFont = new Font("Small Fonts", Font.BOLD,16);
        g.setFont(errorFont);
        g.drawString("Name Taken",593,180);
        }
        if(passwordError){
        g.setColor(Color.red);
        Font errorFont = new Font("Small Fonts", Font.BOLD,16);
        g.setFont(errorFont);
        g.drawString("Password incorrect",594,252);
        }
   }
   private void addTextFields(){
      name = new JTextField();
      password = new JPasswordField();
      name.setOpaque(false); password.setOpaque(false); 
      name.setFont(new Font("Small Fonts",Font.BOLD,20)); password.setFont(new Font("Small Fonts",Font.BOLD,20));
      frame.add(name); frame.add(password);
      name.setBounds(230, 163, 356, 27); password.setBounds(229, 234, 356, 27);
   }
   private void removeLogin(){
    frame.remove(this);
    frame.remove(name);
    frame.remove(password);
    frame.removeMouseListener(this);  
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
      if((e.getX()>= 220 && e.getX() <= 392) && (e.getY() >= 335 && e.getY() <= 400)){
       mouseOverLogin = true;
       if(manager.loginUser(name.getText(),password.getPassword())){
         removeLogin();
         frame.setVisible(false);
         new MailBox(manager.getUserLogged(),frame,manager.data);
         frame.repaint();
       }
     }
     else{
       mouseOverLogin = false;
       frame.repaint();
     }
     if((e.getX()>= 442 && e.getX() <= 614) && (e.getY() >= 335 && e.getY() <= 400)){
       mouseOverSignUp = true;
       nameTaken = manager.signUser(name.getText(),password.getPassword());
       frame.repaint();
     }
     else{
       mouseOverSignUp = false;
       frame.repaint();
     }
     
    }

}