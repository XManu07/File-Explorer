import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.nio.file.Path;

public class FileExplorer {
    private JList<String> list1;
    private JButton diskRootButton;
    private JButton backInRootButton;
    File[] roots=File.listRoots();
    static String current_path;
    String backInPath(String path){
        int last_backslah=path.lastIndexOf(File.separator);
        if (last_backslah!=2)
            path=path.substring(0,last_backslah);
        if (last_backslah==2)
            path=path.substring(0,3);
        return path;

    }

    FileExplorer() {

        //region FileExporer frame
        JFrame fileExplorer=new JFrame();
        fileExplorer.setSize(600,600);
        fileExplorer.setLayout(null);
        fileExplorer.setVisible(true);
        fileExplorer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //endregion

        //region list component
        DefaultListModel<String> listModel=new DefaultListModel<>();
        int number_of_roots=roots.length-1;
        while(number_of_roots>-1) {
            listModel.addElement(roots[number_of_roots].toString());
            number_of_roots--;
        }
        list1=new JList(listModel);
        fileExplorer.add(list1);
        list1.setBounds(10,10,500,500);
        //endregion

        //region diskRootButton component
        diskRootButton = new JButton("Return to Disk Root");
        diskRootButton.setBounds(510, 1, 150, 30);
        diskRootButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                listModel.removeAllElements();
                int i=roots.length-1;
                System.out.println(i);
                while(i!=-1) {
                    listModel.addElement(roots[i].toString());
                    i--;
                }
            }
        });
        fileExplorer.add(diskRootButton);
        //endregion

        //region list listeners
        list1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int index = list1.getSelectedIndex();
                    String value=list1.getSelectedValue();
                    File newpath=new File(value);
                    current_path=newpath.toString();
                    System.out.println("this is the current path "+ current_path);
                    File[] files = newpath.listFiles();
                    listModel.removeAllElements();

                    int i =files.length-1;
                    while(i!=-1) {
                        listModel.addElement(files[i].toString());
                        i--;
                    }
                }
            }
        });

//        list1.addListSelectionListener(new ListSelectionListener() {
//            public void valueChanged(ListSelectionEvent e) {
//                int index=list1.getSelectedIndex();
//                if(index!=-1)
//                    System.out.println("index of Selected item is:"+index);
//                else System.out.println("no item selected");
//            }
//        });
        //endregion

        //region backInRootButton
        backInRootButton = new JButton("Back in root");
        backInRootButton.setBounds(510, 35, 150, 30);
        fileExplorer.add(backInRootButton);
        backInRootButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //modify newpath
                String path=current_path;
                path=backInPath(path);
                current_path=path;
                System.out.println("current path is "+current_path);
                listModel.clear();
                File[] newroot = new File(path).listFiles();
                int i =newroot.length-1;
                while(i!=-1) {
                    listModel.addElement(newroot[i].toString());
                    i--;
                }
            }
            });

        //endregion

    }
}


