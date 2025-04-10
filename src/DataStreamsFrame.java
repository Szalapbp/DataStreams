import javax.swing.*;
import java.awt.*;
import java.io.File;

public class DataStreamsFrame extends JFrame
{
    JTextField searchWord;
    JTextArea originalWords, returnWords;
    JScrollPane originalWordsScroll, returnWordsScroll;
    JButton searchBtn, loadFileBtn, quitBtn;
    JPanel commandPnl, textPnl;
    File loadedFile;

    public DataStreamsFrame()
    {
        setTitle("Data Streams Processing");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        searchWord = new JTextField(20);
        originalWords = new JTextArea();
        returnWords = new JTextArea();
        loadFileBtn = new JButton("Load File");
        searchBtn = new JButton("Search");
        quitBtn = new JButton("Quit");
        originalWordsScroll = new JScrollPane(originalWords);
        returnWordsScroll = new JScrollPane(returnWords);

        textPnl = new JPanel(new GridLayout(1,2));
        textPnl.add(originalWordsScroll);
        textPnl.add(returnWordsScroll);

        commandPnl = new JPanel();
        commandPnl.add(searchWord);
        commandPnl.add(loadFileBtn);
        commandPnl.add(searchBtn);
        commandPnl.add(quitBtn);

        add(commandPnl, BorderLayout.NORTH);
        add(textPnl, BorderLayout.SOUTH);

        loadFileBtn.addActionListener(e -> loadFile());
        searchBtn.addActionListener(e -> searchFile());
        quitBtn.addActionListener(e -> System.exit(0));

    }

    private void loadFile()
    {

    }

    private void searchFile()
    {

    }
}
