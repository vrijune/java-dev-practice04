package ictgradschool.industry.q3;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.swing.*;

/**
 * Swing program that allows users to zip a directory full of files. The Zip file will be created in the
 * same location as the selected directory, and will have the same name, plus a "zip" file extension.
 */
public class FileZipperApp extends JPanel {

    private JButton startBtn;        // Button to start the thumbnail generation process.
    private JButton cancelBtn;          // Button to cancel thumbnail generation.
    private JTextArea outputLog;      // Component to display in-progress messages.

    private List<File> filesInDirectory;      // List of image files for which thumbnails should be generated.
    private FileZipperSwing worker;     // Output directory for storing thumbnails.

    private class FileZipperSwing extends SwingWorker<List<File>,File>{
        private File zipFile;
        public FileZipperSwing(File _zipFile){
            zipFile = _zipFile;
        }

        @Override
        protected void process(List<File> files) {
            for (int i = 0; i < files.size(); i++) {

                File file = files.get(i);
                outputLog.append(file+"\n");
            }
        }



        @Override
        protected List<File> doInBackground() throws Exception {
            // TODO Move this code to various methods within a SwingWorker, as appropriate.
            // **************************************************************************************
            // Open the zip file for writing
            try (FileOutputStream fos = new FileOutputStream(zipFile)) {

                // Prepare the file to be written as a Zip file
                try (ZipOutputStream zipStream = new ZipOutputStream(fos)) {

                    // Add all files in the selected folder to the Zip file.
                    for (File file : filesInDirectory) {
                        try {
                            addFileToZipStream(file, zipStream);

                            // Report progress
                            outputLog.append("Added " + file.getName() + "\n");

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                }

                // Report completion to user
                outputLog.append("All files written to " + zipFile.getName() + " successfully!\n");

            } catch (IOException e) {
                // Report error to the user
                outputLog.append("Error: " + e.getMessage() + "\n");
            }
            return filesInDirectory;
        }

            @Override
            protected void done() {

            // Set enabled state for buttons and restore cursor.
            startBtn.setEnabled(true);
            cancelBtn.setEnabled(false);
            setCursor(Cursor.getDefaultCursor());
         // *******************
        }
    }















    public FileZipperApp() {

        startBtn = new JButton("Process");
        cancelBtn = new JButton("Cancel");
        outputLog = new JTextArea();
        outputLog.setEditable(false);

        // Register a handler for Process buttons clicks.
        startBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {

                // Use a FileChooser Swing component to allow the user to
                // select a directory where images are stored.
                final JFileChooser fc = new JFileChooser();
                fc.setCurrentDirectory(new File("./question3"));
                fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int returnVal = fc.showDialog(FileZipperApp.this, "Select");

                // Whenever the user selects a particular directory ...
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File directory = fc.getSelectedFile();

                    // Create a Zip file in the same place, named the same thing
                    File zipFile = new File(directory.getParentFile(), directory.getName() + ".zip");

                    // Scan the selected directory for all files. Store these files in a List.
                    filesInDirectory = new ArrayList<>();
                    File[] contents = directory.listFiles();
                    for (int i = 0; i < contents.length; i++) {
                        File file = contents[i];
                        if (file.isFile()) {
                            filesInDirectory.add(file);
                        }
                    }

                    // Set enabled state for buttons.
                    startBtn.setEnabled(false);
                    cancelBtn.setEnabled(true);

                    // clear the output log.
                    outputLog.setText("Adding files in selected folder to " + zipFile.getName() + "...\n");

                    // Set cursor to busy.
                    setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

                  // *******************************************************************
                }
            }
        });

        // Register a handler for Cancel button clicks.
        cancelBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try{
//                actionEvent.setSource(false);
                    worker.cancel(false);

                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                // Disable the Start button until the result of the calculation is known.
                startBtn.setEnabled(true);

                cancelBtn.setEnabled(false);
                // Clear any text (prime factors) from the results area.
                outputLog.setText(null);

                // Set the cursor to busy.
                setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));




            }

                // If a SwingWorker were used, we could cancel it.

        });

        // Construct the GUI.
        JPanel controlPanel = new JPanel();
        controlPanel.add(startBtn);
        controlPanel.add(cancelBtn);
        cancelBtn.setEnabled(false);

        JScrollPane scrollPaneForOutput = new JScrollPane();
        scrollPaneForOutput.setViewportView(outputLog);

        setLayout(new BorderLayout());
        add(controlPanel, BorderLayout.NORTH);
        add(scrollPaneForOutput, BorderLayout.CENTER);
        setPreferredSize(new Dimension(400, 300));
    }

    /**
     * Helper method to display the GUI.
     */
    private static void createAndShowGUI() {
        // Create and set up the window.
        JFrame frame = new JFrame("File Zipper");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create and set up the content pane.
        JComponent newContentPane = new FileZipperApp();
        frame.add(newContentPane);

        // Display the window.
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }



    /**
     * ZIPs a single file
     *
     * @param inputFile       the file to add to the Zip stream
     * @param zipOutputStream the Zip stream
     * @throws IOException if there's an error reading the input file or writing to the Zip stream
     */
    public static void addFileToZipStream(File inputFile, ZipOutputStream zipOutputStream) throws IOException {

        // A ZipEntry represents a file entry in the zip archive
        // We name the ZipEntry after the original file's name
        ZipEntry zipEntry = new ZipEntry(inputFile.getName());
        zipOutputStream.putNextEntry(zipEntry);

        // Read the input file by chucks of 1024 bytes and write the read bytes to the zip stream
        try (FileInputStream fileInputStream = new FileInputStream(inputFile)) {

            byte[] buf = new byte[1024];
            int bytesRead;

            while ((bytesRead = fileInputStream.read(buf)) > 0) {
                zipOutputStream.write(buf, 0, bytesRead);
            }
        }

        // close ZipEntry to store the stream to the file
        zipOutputStream.closeEntry();

    }

    public static void main(String[] args) {
        // Schedule a job for the event-dispatching thread:
        // creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}

