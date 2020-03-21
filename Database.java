import java.util.*;
import java.io.*;
/** Class Database manages the data of the users
  *
  * @author EndrinB
  * 
  */
public class Database{
   HashMap<Integer,User> database = new HashMap<Integer,User>();
   private LinkedList<String> userNames = new LinkedList();
   private LinkedList<String> userPass = new LinkedList();
   private MessageHandler msgHandler;
   static File server; 
   FileWriter writer;
   int key = 1;
   
   /** Database Constructors acquires the data from the database(txt file) */
   Database()
   {
      try{
         msgHandler = new MessageHandler();
         server = new File("database.txt");
         Scanner scan = new Scanner(server);
         scan.useDelimiter("[, \n]");
         while(scan.hasNextLine()){
            int keys = scan.nextInt();
            User user = new User(scan.next(),scan.next(),keys,msgHandler.containsMsgKey(keys));
            database.put(keys,user);
            userNames.add(user.getName());
            userPass.add(user.getPassword());
            key = user.getTotalUsers()+1;

         }
         scan.close();
          
      }catch(Exception e){}  
   
   }
   
   /** getUser method returns an user depending on the key it takes
     * @param key the key associated with the user */
   public User getUser(int key){
      User user = null;
      if(database.containsKey(key)){ 
         user = database.get(key);
      }else{
         System.out.println("No user identified with the current key");
      }
      return user;
   }
   
   /** addUser method adds an User ,if the name is taken the new user wont be added
     * to the database
     * @param name the name of the user
     * @param password the password the user has signed with */
   public void addUser(String name,String password){
      if(userNames.contains(name)){
         System.out.println("Name already taken");
      }else{
         try{
            writer = new FileWriter(server,true);
            BufferedWriter bw = new BufferedWriter(writer);
            PrintWriter pw = new PrintWriter(bw);
            User user = new User(name,password,key,false);
            pw.println(key +"," + name + "," + password);
            database.put(key,user);
            userNames.add(name);
            userPass.add(password);
            key++;
            pw.flush();
            pw.close();
         
         }catch(Exception e){}
      }
   } 
   
   /** removeUser method removes the user with the specified key
     * from the database,userNames,userPassword and database(txt file)
     * @param key the key associated with the user */
   public void removeUser(int key){
      if(!database.containsKey(key)){System.out.println("User not found");}
      else{
            int count = 0;
            database.remove(key);
            Set<Integer> keys = database.keySet();
            Integer[] array = keys.toArray(new Integer[keys.size()]);  
         try{
            FileWriter wr = new FileWriter(server,false);
            BufferedWriter bw = new BufferedWriter(wr);
            PrintWriter pw = new PrintWriter(bw);
            while(count <= keys.size()-1){
               wr.write(0+array[count] +","+ database.get(array[count]).getName() + "," + database.get(array[count]).getPassword());
               count++;
            }
            wr.flush();
            wr.close();
         
         
         }catch(Exception e){}
      }
   }
}

