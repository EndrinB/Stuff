

public class RunApplication{
 
   public static void main(String[] a){
    Database c = new Database();
   // c.addUser("daggdsss","dsa"); //adds user to the datatbase
    //c.getUser(3).setData();
   // c.getUser(3).addMessage("sd"); //adds message to the users log
     //System.out.println(c.getUser(1).getMessage(6)); // return message depending from the number(starts from 0);
     //System.out.println(c.getUser(1).getMsgCount()); //return the total messages from the user
     //System.out.println(c.getUser(1).getTotalUsers()); //gets the total users currently signed
    // c.getUser(1).removeMessage(1);
    //c.removeUser(1); //removes user from the database
    //c.getUser(3).sendMail("dddd",2);
     //System.out.println(c.database.keySet() + " " +c.key);
     
     new LoginPlatform(c);
  
     

  }
   
}
