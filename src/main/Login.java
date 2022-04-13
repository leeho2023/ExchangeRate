package main;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import db.ExchangeRateDB;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField userIDField;
	private JPasswordField userPasswordField;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("환율계산기");
		lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 35));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(80, 10, 320, 99);
		contentPane.add(lblNewLabel);
		
		userIDField = new JTextField();
		userIDField.setBounds(175, 167, 116, 21);
		contentPane.add(userIDField);
		userIDField.setColumns(10);
		
		userPasswordField = new JPasswordField();
		userPasswordField.setBounds(175, 216, 116, 21);
		contentPane.add(userPasswordField);
		
		JLabel lblNewLabel_1 = new JLabel("ID");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(133, 170, 30, 15);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("PW");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(133, 219, 30, 15);
		contentPane.add(lblNewLabel_2);
		
		JButton loginBtn = new JButton("로그인");
		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String id = userIDField.getText().trim().replaceAll(" ", "");
				String pw = userPasswordField.getText().trim().replaceAll(" ", "");
				
				ExchangeRateDB db = new ExchangeRateDB();
				int check = db.checkUser(id, pw);
				
				if(check == 1) {
					System.out.println("로그인 성공");
					setVisible(false);
					Main main = new Main();
					main.setVisible(true);
				}else {
					System.out.println("아이디 혹은 비밀번호를 확인하세요");
				}
				
			}
		});
		loginBtn.setBounds(106, 302, 97, 23);
		contentPane.add(loginBtn);
		
		JButton singInBtn = new JButton("회원가입");
		singInBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				SingIn si = new SingIn();
				si.setVisible(true);
			}
		});
		singInBtn.setBounds(261, 302, 97, 23);
		contentPane.add(singInBtn);
	}
}
