import data.DataReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import world.Camera;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MainWindow extends JFrame implements KeyListener, ActionListener {

    private static Logger log = LoggerFactory.getLogger(MainWindow.class);
    private final Camera camera;

    private JMenuBar menu;
    private JMenu file, help;
    private JMenuItem loadData, about, howToUse;


    public MainWindow() {
        super("Virtual camera");
        setSize(1300, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


//      == key operation support ==
        addKeyListener(this);

//      == components ==
//        menu
        menu = new JMenuBar();
        this.setJMenuBar(menu);

//      == main menu items ==
//      file
        file = new JMenu("File");
//        todo add shortcut
        menu.add(file);

//      help
        help = new JMenu("Help");
        menu.add(help);


//      == menu sub items ==
//      load data
        loadData = new JMenuItem("Load data");
        loadData.addActionListener(this);
        file.add(loadData);

//      about
        about = new JMenuItem("About");
        about.addActionListener(this);
        help.add(about);

//      howToUse
        howToUse =new JMenuItem("How to use");
        howToUse.addActionListener(this);
        help.add(howToUse);

//      == camera ==
        camera =new Camera();
        this.add(camera);

        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == about){
            log.info("in about");
            JOptionPane.showMessageDialog(this,"Virtual camera project","About",JOptionPane.INFORMATION_MESSAGE);
        }else if(e.getSource() == howToUse){
            JOptionPane.showMessageDialog(this ,"Instruction","How to use",JOptionPane.INFORMATION_MESSAGE);

        }else if(e.getSource() == loadData){
//            todo choose data file
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
