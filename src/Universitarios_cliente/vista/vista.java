package Universitarios_cliente.vista;

import Universitarios_cliente.cliente.cliente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class vista extends JFrame {

    private JTextField txtId;
    private JTextArea txtResultado;
    private JButton btnConsultar;
    private  JButton btnLimpiarCampos;

    public vista() {
        setTitle("Consulta de Estudiantes");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lblId = new JLabel("ID del Estudiante:");
        lblId.setBounds(30, 30, 150, 25);
        add(lblId);

        txtId = new JTextField();
        txtId.setBounds(160, 30, 150, 25);
        add(txtId);

        btnConsultar = new JButton("Consultar");
        btnConsultar.setBounds(120, 70, 120, 30);
        add(btnConsultar);

        btnLimpiarCampos = new JButton("Limpiar");
        btnLimpiarCampos.setBounds(240, 70, 120, 30);
        add(btnLimpiarCampos);


        txtResultado = new JTextArea();
        txtResultado.setEditable(false);
        JScrollPane scroll = new JScrollPane(txtResultado);
        scroll.setBounds(30, 120, 320, 120);
        add(scroll);

        btnConsultar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(txtId.getText());
                    String resultado = cliente.consultarEstudiantePorId(id);
                    txtResultado.setText(resultado);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Ingrese un número válido.");
                }
            }
        });
        btnLimpiarCampos.addActionListener(e -> {
            txtResultado.setText("");
        });
    }
}
