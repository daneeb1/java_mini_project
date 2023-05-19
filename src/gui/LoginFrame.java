package gui;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
// awt는 자바에서 gui를 지원해주는 것, java.awt.event 이벤트와 관련된 인터페이스 패키지
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.Border;

import client.MainClient;
import dao.UserDao;

public class LoginFrame extends JFrame { 
// JFrame 자바에서 '창'을 만들기 위하여 사용
// LoginFrame 이벤트를 받기 위해서 JFrame 인터페이스 구현	

	private final static String TAG = "SigninFrame : "; 

	public LoginFrame loginFrame = this;
	
	/* JPanel(판넬,도화지)
	 * 자바에서 그래픽을 표현하기 위해서 JPanel 클래스를 상속받아 
	 * paintComponent(Graphics g)을 오버라이딩하여 그 안에 표현하고자 하는 그래픽 명령어
	 * 
	 * JButton
	 * 사용자로부터 명령을 입력받기 위한 목적, 이미지와 문자열로 구성, 생성자를 이용하여 생성
	 * 
	 * JTextField
	 * 한 줄의 문자열을 입력받는 창을 만들 수 있음
	 * 
	 * JPasswordField
	 * 텍스트필드와 다르게 입력내용이 표시되지 않는다
	 *  
	 * ImageIcon
	 * 이미지를 로딩, 조작을 사용할 수 있는 클래스
	 * 이미지 파일의 경로를 넣어주면 이미지 로딩
	 * 로딩 후에 JLabel에 ImageIcon을 설정해주면 해당 이미지 그려짐*/
	public JPanel pLogin; 
	public JButton btID, btPW, btSign, btLogin;
	public JTextField tfID;
	public JPasswordField tfpw;
	public MainClient mainClient; //이건 클라이언트 관련인듯
	public ImageIcon icon;
//	public ArrayList<String> userName = new ArrayList<>();

	// 생성자, 필드 정의 ?
	public LoginFrame() {
		back();
		initObject();
		initData();
		initDesign();
		initListener();
		setVisible(true);	
	}

	private void back() { // private 메소드는 오버라이딩이 불가능한데 왜 가능?
		icon = new ImageIcon("src/images/loginFrame.png");
		pLogin = new JPanel() {
			@Override
			public void paintComponents(Graphics g) { //paintComponents메소드 -> 구성요소를 그려주는 메소드,오버라이딩
				/* paintComponents메소드 안에 있는 drawImage메소드, 
				 * draw이미지를 그려주는 메소드 사용, 매개변수를 이용해서 */
				g.drawImage(icon.getImage(), 0, 0, null);
				setOpaque(false); // 불투명성 설정?
			}
		};

	}
	
	// 객체생성
	private void initObject() {
		
//		mainClient = new MainClient(loginFrame);
//		pLogin = new JPanel();
//		pLogin.setBackground(Color.WHITE);

//		btID = new JButton(new ImageIcon("src/images/tbID.png"));
//		btPW = new JButton(new ImageIcon("src/images/tbPw.png"));
		btSign = new JButton(new ImageIcon("src/images/tbSignin.png")); // 싸인 이미지?
		btLogin = new JButton(new ImageIcon("src/images/tbLogin.png")); //로그인 이미지

		tfID = new JTextField();
		tfpw = new JPasswordField();
	}

	// 데이터 초기화
	private void initData() {

	}

	// 디자인
	private void initDesign() {
		// 1. 기본세팅
		loginFrame.setTitle("Login");
		
		loginFrame.setBounds(100, 100, 382, 489); //
		loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 윈도우 창 종료시 프로세스까지 종료
		loginFrame.getContentPane().setLayout(new GridLayout(0, 1, 0, 0));

		// 2. 패널세팅
		getContentPane().add(pLogin);
		pLogin.setLayout(null); // 레이아웃 설정

		// 3. 디자인
		tfID.setColumns(10);
		tfID.setBounds(38, 177, 285, 43);
		Border borderLine1 = BorderFactory.createLineBorder(Color.BLACK, 3);
		tfID.setBorder(borderLine1);
//		btID.setBounds(38, 145, 89, 27);
		tfpw.setBounds(38, 269, 285, 43);
		Border borderLine = BorderFactory.createLineBorder(Color.BLACK, 3);
		tfpw.setBorder(borderLine);
//		btPW.setBounds(38, 236, 100, 27);
		tfpw.setColumns(10);
		btSign.setBounds(49, 350, 124, 41);
		btLogin.setBounds(189, 350, 124, 41);

		// 4. 패널에 컴포넌트 추가
//		pLogin.add(btID);
		pLogin.add(tfID);
//		pLogin.add(btPW);
		pLogin.add(tfpw);
		pLogin.add(btSign);
		pLogin.add(btLogin);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(111, 281, 120, -1);
		pLogin.add(separator);
	}

	// 리스너 등록
	private void initListener() {

		btSign.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new SigninFrame(mainClient);
				loginFrame.setVisible(false); //setVisible 눈에 보여줄거!
			}
		});
		
		btLogin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// userdao에 셀렉트문으로 select from where username 이랑 qw가 같을경우 성공/ 리턴이 0개가되면 오류메시지
				UserDao userDao = UserDao.getInstance();
				int result = userDao.로그인(tfID.getText(), tfpw.getText());

				if (result == 1) {
					new GameRoomFrame(tfID.getText());
					loginFrame.setVisible(false);
					// 로그인 성공시 list 에 담아서 push
					String userName = tfID.getText() + ",";
//					System.out.println(TAG + "userN 확인 : " + userN);
//					userName.add(userN);
//					System.out.println(TAG + "userName 확인 : " + userName);
//					mainClient.userSend(userName);
				} else {
					JOptionPane.showMessageDialog(null, "로그인에 실패했습니다.");
					tfID.setText("");
					tfpw.setText("");
				}

			}
		});

	}
}