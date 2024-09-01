import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.*;

public class Server 
{
    JFrame f = new JFrame("SERVER");
    JTextField tf = new JTextField(" ");
    JButton b = new JButton("SEND");
    List l = new List();
    ServerSocket ss;
    Socket s ;
    
    Server()
    {
        f.addWindowListener( new WindowAdapter() 
        {
            @Override
            public void windowClosing(WindowEvent e )
            {
                f.dispose();
            }    
        });
        l.setFont(new Font("arial" , 2 , 15));
        f.add(tf,BorderLayout.NORTH);
        f.add(l);
        f.add(b , BorderLayout.SOUTH);
        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e )
            {
                try
                {
                    ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
                        String str = (String)tf.getText();
                        oos.writeObject(str);
                        l.add("me:--> " + str);
                        tf.setText(" ");

                }
                catch(Exception ex)
                {
                    System.out.println(ex.getMessage());
                }
            }
        });
        tf.setBackground(Color.gray);
        l.setBackground(Color.pink);
        b.setBackground(Color.lightGray);
        tf.setFont(new Font("Arial" , 1 , 24));
        f.setVisible(true);
        f.setSize(800,800);
        try
        {
            ss = new ServerSocket(2003);
            s=ss.accept();
            while (true) 
            {
                ObjectInputStream ois = new ObjectInputStream(s.getInputStream()); 
                String str = ois.readObject().toString();
                l.add("Frind :--> "+str);   
            }

        }
        catch(Exception ex )
        {
            System.out.println(ex.getMessage());
        }
    }
    public static void main(String[] args) {
        Server s = new Server();
    }
}
