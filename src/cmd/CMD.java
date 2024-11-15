package cmd;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class CMD extends JFrame {

    private Comandos files = new Comandos();
    private JTextField textfield = new JTextField();
    private JTextArea textarea = new JTextArea();
    private final JScrollPane scrollpane = new JScrollPane(textarea);

    public CMD() {
        setSize(800, 600);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("cmd");
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);
        textarea.setEditable(false);
        textarea.setBackground(Color.BLACK);
        textarea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        textarea.setForeground(Color.WHITE);
        textarea.setText("Microsoft Windows [Versión 10.0.19045.5011]\n"
                + "(c) Microsoft Corporation. Todos los derechos reservados.\n"
                + "\n"
                + "C:\\Users\\Dvaqued>");
        textarea.setLineWrap(true);
        textfield.setBackground(Color.BLACK);
        textfield.setForeground(Color.WHITE);
        textfield.setFont(new Font("Monospaced", Font.PLAIN, 12));

        scrollpane.setBackground(Color.BLACK);

        add(scrollpane, BorderLayout.CENTER);
        add(textfield, BorderLayout.SOUTH);

        textarea.setAlignmentX(LEFT_ALIGNMENT);
        textfield.setAlignmentX(LEFT_ALIGNMENT);
        scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        textfield.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String command = textfield.getText().trim();

                    textarea.append(command + "\n");
                    try {
                        if (command.startsWith("Mkdir ")) {
                            String folderName = command.substring(6).trim();
                            textarea.append("  Folder " + folderName + " Creado " + "\n\n");
                            files.setarchivo(folderName);
                            files.crearFolder();
                        } else if (command.startsWith("Mfile ")) {
                            String fileName = command.substring(6).trim();
                            try {
                                textarea.append("  File creado: " + fileName + "\n\n");
                                files.setarchivo(fileName);
                                files.crearArchivo();
                            } catch (IOException ex) {
                                textarea.append("  Error al crear el archivo : " + fileName + "\n\n");
                            }
                        } else if (command.startsWith("Rm ")) {
                            String name = command.substring(3).trim();
                            files.setarchivo(name);
                            textarea.append(files.borrar(files.getArchivo()));
                        } else if (command.startsWith("Cd ")) {
                            String folderName = command.substring(3).trim();
                            files.setarchivo(command);
                            textarea.append("  Se selecciono : " + folderName + "\n\n");
                        } else if (command.equals("...")) {
                            try {
                                files.setarchivo(null);
                                textarea.append("  Se ha deseleccionado \n\n");
                            } catch (Exception asd) {
                                textarea.append("  Ha habido un error al hacer esto \n\n");
                            }
                        } else if (command.startsWith("Dir ")) {
                            String name = command.substring(4).trim();
                            files.setarchivo(name);
                            textarea.append(files.dir() + " \n\n");
                        } else if (command.equals("Date")) {
                            //date
                            textarea.append("  " + files.obtenerFechaActual() + " \n\n");
                        } else if (command.equals("Time")) {
                            textarea.append("  " + files.obtenerHoraActual12Horas() + " \n\n");
                        } else if (command.startsWith("wr ")) {
                            String text = command.substring(3).trim();
                            try {
                                files.reescribirArchivo(text);
                                textarea.append("  Escribiendo en el archivo: " + files.getArchivo() + "\n\n");
                            } catch (Exception u) {
                                textarea.append("Error inesperado \n\n");
                            }

                        } else if (command.equals("rd")) {
                            try {
                                textarea.append("  " + files.leerArchivoComoString() + "\n\n");
                            } catch (Exception u) {
                                textarea.append("Error inesperado \n\n");
                            }
                        } else {
                            if (command.isBlank()) {
                                textarea.append(command);
                            } else {
                                textarea.append(" Comando no reconocido\n\n");
                            }
                        }

                        textarea.append("C:\\Users\\Dvaqued> ");
                        textfield.setText("");
                        textarea.setCaretPosition(textarea.getDocument().getLength());
                    } catch (Exception error) {
                        textarea.append(" Ha habido un error inesperado  \n\n");
                        textarea.append("C:\\Users\\Dvaqued> ");
                        textfield.setText("");
                        textarea.setCaretPosition(textarea.getDocument().getLength());
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e
            ) {
            }

            @Override
            public void keyTyped(KeyEvent e
            ) {
            }
        }
        );
        
        
        setVisible(true);
    }

}
