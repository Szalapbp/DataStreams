import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.file.*;
import java.util.stream.Stream;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

        originalWordsScroll.setPreferredSize(new Dimension(300, 400));
        returnWordsScroll.setPreferredSize(new Dimension(300, 400));

        add(commandPnl, BorderLayout.NORTH);
        add(textPnl, BorderLayout.SOUTH);

        loadFileBtn.addActionListener(e -> loadFile());
        searchBtn.addActionListener(e -> searchFile());
        quitBtn.addActionListener(e -> System.exit(0));

    }

    private void loadFile()
    {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);
        if(result == JFileChooser.APPROVE_OPTION)
        {
            loadedFile = fileChooser.getSelectedFile();
            try(Stream<String> lines = Files.lines(Paths.get(loadedFile.getAbsolutePath())))
            {
                originalWords.setText("");
                lines.forEach(line -> originalWords.append(line + "\n"));
            } catch (IOException ex)
            {
                JOptionPane.showMessageDialog(this, "Error loading file: "
                        + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void searchFile()
    {
        if(loadedFile == null){
            JOptionPane.showMessageDialog(this, "No File Selected",
                    "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String searchString = searchWord.getText();
        if(searchString.isEmpty())
        {
            JOptionPane.showMessageDialog(this, "Search string is empty! Type a Word!",
                    "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try(Stream<String> lines = Files.lines(Paths.get(loadedFile.getAbsolutePath())))
        {
            returnWords.setText("");
            lines.filter(line -> line.contains(searchString))
                    .forEach(line -> returnWords.append(line + "\n"));

        }catch(IOException ex)
        {
            JOptionPane.showMessageDialog(this, "Error searching file: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }

    }
}
