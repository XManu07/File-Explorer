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
    private JButton diskRootButton;
    File[] roots=File.listRoots();


    FileExplorer() {
        //file explorer frame
        JFrame fileExplorer=new JFrame();
        fileExplorer.setSize(600,600);
        fileExplorer.setLayout(null);
        fileExplorer.setVisible(true);
        fileExplorer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //list component
        DefaultListModel<File> listModel=new DefaultListModel<>();
        listModel.addElement(new File(roots[0].getPath()));
        listModel.set(0,roots[0]);
        list1=new JList(listModel);
        fileExplorer.add(list1);
        list1.setBounds(10,10,300,300);

        //exit button
        diskRootButton = new JButton("Return to Disk Root");
        diskRootButton.setBounds(310, 1, 100, 30);
        diskRootButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                listModel.removeAllElements();
                int i=roots.length-1;
                System.out.println(i);
                while(i!=-1) {
                    listModel.addElement(roots[i]);
                    i--;
                }
            }
        });
        fileExplorer.add(diskRootButton);


        //list listeners
        list1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int index = list1.getSelectedIndex();
                    System.out.println(index);
                    File[] newroot = roots[index].listFiles();

                    listModel.removeAllElements();
                    int i =newroot.length-1;
                    System.out.println(i);
                    while(i!=-1) {
                        listModel.addElement(newroot[i]);
                        i--;
                    }

                }
            }
        });

        list1.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                int index=list1.getSelectedIndex();
                if(index!=-1)
                    System.out.println("index of Selected item is:"+index);
                else System.out.println("no item selected");
            }
        });
    }
}


