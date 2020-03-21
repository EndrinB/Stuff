import java.util.*;

public class User{
   private String name;
   private String password;
   private final int KEY;
   private Message msg ;
   private List<String> messages;
   boolean created;
   static int users;
   
   User(String name,String password,int key,boolean created){
      this.name = name;
      this.password = password;
      KEY = key;
      msg = new Message(KEY,created);
      messages = msg.getMessages();
      users++;
   }
   
   public void addMessage(String message){
      messages.add(msg.addMessage(message));
   }
   
   public void removeMessage(int msgNum){
      if(messages.size() > msgNum && msgNum >= 0) { 
         messages.remove(msgNum);
      }
   }
   
   public String getMessage(int msgNum){
      if (messages.size() > msgNum && msgNum >= 0) {
           return messages.get(msgNum);
      }
      return "This index ("+msg+") exceeds the size of the messages list";
   }
   
   public int getMsgCount(){
      return messages.size();
   }
   
   public String getName(){
      return name;
   }
   
   public String getPassword(){
      return password;
   }
   
   public int getUserKey(){
    return KEY;
   }
   public int getTotalUsers(){
    return users;
   }
}
