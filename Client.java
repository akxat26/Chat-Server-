import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;
class Client
{
Frame f;
TextField t;
List l;
Button b;
Socket s;
Client()
{
f=new Frame("Client");
f.addWindowListener(new WindowAdapter()
{
@Override
public void windowClosing(WindowEvent ev)
{
f.dispose();
}
});
t=new TextField();
l=new List();
b=new Button("Send");
b.addActionListener(new ActionListener()
{
public void actionPerformed(ActionEvent e)
{
try
{
ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
String str=t.getText();
oos.writeObject(str);
l.add("Me:-"+str);
t.setText("");
}
catch(Exception ex)
{
System.out.println(ex.getMessage());
}
}
});
f.add(t,BorderLayout.NORTH);
t.setBackground(Color.gray);
l.setBackground(Color.pink);
b.setBackground(Color.lightGray);
t.setFont(new Font("Arial" , 1 , 24));
l.setFont(new Font("arial" , 2 , 15));
f.add(l);
f.add(b,BorderLayout.SOUTH);
f.setVisible(true);
f.setSize(800,800);
try
{
s=new Socket("localhost",2003);
while(true)
{
ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
String str=ois.readObject().toString();
l.add("Friend:-"+str);
}
}
catch(Exception ex)
{
System.out.println(ex.getMessage());
}
}
public static void main(String ar[])
{
Client client=new Client();
}
}