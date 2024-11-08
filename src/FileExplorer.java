import javax.swing.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

public class FileExplorer {
    private JList<String> list1;
    private JButton exitButton;
    File[] roots=File.listRoots();
    //File[] rootc=roots[0].listFiles();

    FileExplorer() {
        JFrame fileExplorer=new JFrame();
        fileExplorer.setSize(400,400);
        fileExplorer.setLayout(null);
        fileExplorer.setVisible(true);
        fileExplorer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //exit button
        exitButton = new JButton("Exit");
        exitButton.setBounds(250, 1, 100, 30);
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        fileExplorer.add(exitButton);

        //        JList <String>list2 = new JList(rootc);
//        list2.setBounds(310,310,300,300);

        list1=new JList(roots);
        list1.setBounds(10,10,300,300);
        list1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    fileExplorer.remove(list1);
                    File[] rootc=roots[0].listFiles();
                    list1=new JList(rootc);
                    list1.setBounds(10,10,300,300);
                    fileExplorer.add(list1);
                    fileExplorer.repaint();
                }
            }
        });

        fileExplorer.add(list1);



    }

}


