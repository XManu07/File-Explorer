import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

public class FileExplorer {
    private JList<String> list1;
    private JButton buttonExit;
    File[] roots=File.listRoots();

    FileExplorer() {
        buttonExit = new JButton("Exit");
        buttonExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        buttonExit.setBounds(250, 1, 100, 30);

        JFrame fileExplorer=new JFrame();
        list1=new JList(roots);
        list1.setBounds(10,10,300,300);
        fileExplorer.add(list1);
        fileExplorer.setSize(400,400);
        fileExplorer.setLayout(null);
        fileExplorer.setVisible(true);

        fileExplorer.add(buttonExit);


        list1.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

            }
        });
    }

}


