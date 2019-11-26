import java.io.*; 
import java.net.*; 
import java.util.Scanner; 
import java.util.concurrent.*;
import java.util.concurrent.ExecutorService;
import java.util.logging.Level;
import java.util.logging.Logger;
  

public class Client  
{ 
    
    public static void main(String[] args) throws IOException  
    { 
        Scanner sc = new Scanner(System.in);
    InetAddress ip = InetAddress.getByName("localhost");      
        try(  
            Socket s = new Socket("localhost", 5056);
              DataInputStream dis = new DataInputStream(s.getInputStream()); 
            DataOutputStream dos = new DataOutputStream(s.getOutputStream()); 
            InputStream  fin = s.getInputStream();       
            ){        
            System.out.println(dis.readUTF());
            System.out.print("input filename : " );
            String namefile = sc.nextLine();
            dos.writeUTF(namefile);
            String ln = namefile.substring(namefile.lastIndexOf("."));
            //System.out.println(ln);
            System.out.println("ตั้งชื่อไฟล์");
            FileOutputStream fout =new FileOutputStream(new File("C:\\New folder\\jino\\"+sc.nextLine()+ln));  

            
           dis.close();
           dos.close();
        }
    catch(Exception e) {
        System.out.println("Something went wrong! Reason: " + e.getMessage());
    }     
    } 
} 
