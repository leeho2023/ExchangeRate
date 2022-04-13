package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.GridBagLayout;

import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Main extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setBounds(100, 100, 600, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "달러", "엔화" }));
		comboBox.setBounds(188, 98, 58, 30);
		contentPane.add(comboBox);

		textField = new JTextField();
		textField.setBounds(12, 103, 164, 21);
		contentPane.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setHorizontalAlignment(SwingConstants.RIGHT);
		

        DocumentListener dl = new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                updateFieldState();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateFieldState();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateFieldState();
            }

            protected void updateFieldState() {
            	Object item = comboBox.getSelectedItem();
                String text = textField.getText();
                if (text.equals("")) {
                	text = "0";
				}
                int reNum =Integer.parseInt(text);
                if (item.equals("달러")) {
					double won = (reNum * 1231.10);
					textField_1.setText(Integer.toString((int) Math.round(won)));
				} else if (item.equals("엔화")) {
					double won = (reNum * 9.79);
					textField_1.setText(Integer.toString((int) Math.round(won)));
				}
            }
        };

        textField.getDocument().addDocumentListener(dl);
        
        getContentPane().add(textField);
        getContentPane().add(textField_1);
        
		textField_1.setBounds(311, 103, 221, 21);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("원");
		lblNewLabel_1.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(535, 88, 40, 48);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("환율계산기");
		lblNewLabel_2.setFont(new Font("맑은 고딕", Font.BOLD, 30));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(52, 10, 480, 48);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel = new JLabel(">>>>>");
		lblNewLabel.setBounds(258, 106, 41, 15);
		contentPane.add(lblNewLabel);

	}
}
