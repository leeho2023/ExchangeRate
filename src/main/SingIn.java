package main;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import db.ExchangeRateDB;

public class SingIn extends JFrame {

	private JPanel contentPane;
	private JTextField userIDField;
	private JPasswordField userPWField;
	private JPasswordField userPWCheckField;

	public SingIn() {
		setBounds(100, 100, 500, 500);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("회원 가입");
		lblNewLabel.setFont(new Font("맑은 고딕", Font.BOLD, 35));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(138, 25, 176, 66);
		contentPane.add(lblNewLabel);
		
		userIDField = new JTextField();
		userIDField.setBounds(203, 128, 116, 21);
		contentPane.add(userIDField);
		userIDField.setColumns(10);
		
		userPWField = new JPasswordField();
		userPWField.setBounds(203, 178, 116, 21);
		contentPane.add(userPWField);
		
		userPWCheckField = new JPasswordField();
		userPWCheckField.setBounds(203, 228, 116, 21);
		contentPane.add(userPWCheckField);
		
		JLabel lblNewLabel_1 = new JLabel("ID");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(161, 131, 30, 15);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("PW");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(161, 181, 30, 15);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("PW확인");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(141, 231, 50, 15);
		contentPane.add(lblNewLabel_3);
		
		JButton btnNewButton = new JButton("가입하기");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String id = userIDField.getText().trim().replaceAll(" ", "");
				id = id.replaceAll("[^a-zA-Z0-9]", "");
				String pw = userPWField.getText().trim().replaceAll(" ", "");
				String pwCheck = userPWCheckField.getText().trim().replaceAll(" ", "");
				
				int idKey = 0;
				int pwKey = 0;
				
				System.out.println(id);
				System.out.println(pw);
				System.out.println(pwCheck);
				
				ExchangeRateDB db = new ExchangeRateDB();
				
				if(id.length() < 5) {
					System.out.println("아이디 5글자 이상 작성해라");
					JOptionPane.showMessageDialog(contentPane, "아이디를 5글자 이상 작성해주세요", "경고", JOptionPane.WARNING_MESSAGE);
				}else {
					System.out.println("아이디 5글자 이상 작성함");
					int check = db.checkUserID(id);
					if(check == 1) {
						System.out.println("아이디 중복");
						userIDField.setText("");
						userPWField.setText("");
						userPWCheckField.setText("");
						JOptionPane.showMessageDialog(contentPane, "아이디가 중복됩니다.", "경고", JOptionPane.WARNING_MESSAGE);
					}else {
						System.out.println("아이디 사용가능");
						int ans = JOptionPane.showConfirmDialog(contentPane, "이 아이디를 사용하시겠습니까?", "확인", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
						if(ans == 0) {
							idKey = 1;
						}
					}
					
					
				}
				
				if(pw.length() < 12) {
					System.out.println("비밀번호 12글자 이상 작성해라");
					userPWField.setText("");
					userPWCheckField.setText("");
					JOptionPane.showMessageDialog(contentPane, "비밀번호 12글자 이상 작성해주세요", "경고", JOptionPane.WARNING_MESSAGE);
				}else {
					System.out.println("비밀번호 12글자 이상 작성 완료");
					if(pw.equals(pwCheck)) {
						System.out.println("둘이 같음");
						pwKey = 1;
					}else {
						System.out.println("둘이 다름");
						userPWCheckField.setText("");
						JOptionPane.showMessageDialog(contentPane, "비밀번호가 일치하지 않습니다.", "경고", JOptionPane.WARNING_MESSAGE);
					}
				}
				
				if(idKey == 1 && pwKey == 1) {
					db.insert(id, pw);
					userIDField.setText("");
					userPWField.setText("");
					userPWCheckField.setText("");
					Login login = new Login();
					login.setVisible(true);
					setVisible(false);
					JOptionPane.showMessageDialog(contentPane, "가입이 완료되었습니다.", "확인", JOptionPane.PLAIN_MESSAGE);
				}
				
				
				
				
				
				
				
				
			}
		});
		btnNewButton.setBounds(104, 329, 97, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("뒤로가기");
		btnNewButton_1.setBounds(266, 329, 97, 23);
		contentPane.add(btnNewButton_1);
		
	}

}
