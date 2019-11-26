
import java.awt.*;
import java.io.*;
import java.text.*;
import java.util.*;
import java.net.*;
import java.nio.channels.FileChannel;
import java.util.logging.Level;
import java.util.logging.Logger;

// Server class 
public class Server {

    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(5056);
        while (true) {
            Socket s = null;

            try {
                s = ss.accept();

                System.out.println("A new client is connected : " + s);

                System.out.println("Assigning new thread for this client");
                DataInputStream dis = new DataInputStream(s.getInputStream());
                DataOutputStream dos = new DataOutputStream(s.getOutputStream());

                Thread t = new ClientHandler(s, dis, dos);

                t.start();

            } catch (Exception e) {
                s.close();
                e.printStackTrace();
            }
        }
    }
}

class ClientHandler extends Thread {

    public static String listFilesForFolder(final File folder, String sum) {
        for (final File fileEntry : folder.listFiles()) {

            if (fileEntry.isDirectory()) {
                sum = listFilesForFolder(fileEntry, sum);
                System.out.println("sum");
            } else {
                System.out.println(fileEntry.getName());
                sum += fileEntry.getName() + "\n";

            }
        }

        return sum;
    }
public void zeroCopy(FileInputStream from ,FileOutputStream to) throws IOException{
    FileChannel source = null;
    FileChannel destination = null;
    try{
        source = from.getChannel();
        destination=to.getChannel();
        System.out.println(to);
         System.out.println(source.size());
        source.transferTo(0,source.size(),destination);
    } finally{
        if(source != null){
            source.close();
        }
        if(destination != null){
            destination.close();
        }
    }
}
    public static String getpathh(final File folder, String name, String x) {
        for (final File fileEntry : folder.listFiles()) {

            if (fileEntry.isDirectory()) {

                x = getpathh(fileEntry, name, x);
            } else {
                if (name.equals(fileEntry.getName())) {
                    x = fileEntry.getPath();
                }
            }
        }

        return x;
    }
    final DataInputStream dis;
    final DataOutputStream dos;
    final Socket s;
    final int BUFFERSIZE = 4 * 1024;

    // Constructor 
    public ClientHandler(Socket s, DataInputStream dis, DataOutputStream dos) {
        this.s = s;
        this.dis = dis;
        this.dos = dos;

    }

    @Override
    public void run() {
        try {
            String path = "";
            final File folder = new File("C:\\Users\\Admin\\Desktop\\my server");
            System.out.println(listFilesForFolder(folder, path));
            dos.writeUTF(listFilesForFolder(folder, path));
            path = dis.readUTF();
            System.out.println(path);
            String p = getpathh(folder, path, "");
            System.out.println(p);
            FileInputStream fi = new FileInputStream(new File("C:\\Users\\Admin\\Desktop\\3 Idiots 2009.mkv"));
          FileOutputStream fout =new FileOutputStream(new File("C:\\New folder\\jino\\3 Idiots 2009.mkv"));  

            long startTime = System.currentTimeMillis();

            zeroCopy(fi, fout);
            
        } catch (Exception e) {
            System.out.println("Something went wrong! Reason: " + e.getMessage());
        }
        try {
            this.dis.close();
            this.dos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
