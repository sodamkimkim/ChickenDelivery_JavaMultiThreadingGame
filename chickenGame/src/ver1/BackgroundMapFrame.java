package ver1;

import java.awt.Color;
import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BackgroundMapFrame extends JFrame implements ActionListener {
	// 배달 맵 이미지
	JLabel deliveryMapImg;
	// 주방 맵 이미지
	JLabel kitchenMapImg;
	// 게임 인트로 이미지
	JLabel introImg;
	// 이미지 파일명
	private String introFileName = "images/kit_finalState.png";
	private String deliveryMapFileName = "images/Map_del.jpg";
	private String kitchenMapFileName = "images/Map_kit.jpg";

	public JButton changeDeliveryMapBtn;
	public JButton changeKitchenMapBtn;
	public JButton startBtn;

	private Sales sales;

	private JLabel totalSalesLabel;
	private JLabel goalSalesLabel;

	private Player player;

	private ImageIcon kitPlayerF;
	private ImageIcon kitPlayerL;
	private ImageIcon kitPlayerR;

	private ImageIcon delPlayerF;
	private ImageIcon delPlayerL;
	private ImageIcon delPlayerR;

	private JLabel deliveryAddressLabel;
	private AfterSucceedLabel afterSucceedLabel;

	public BackgroundMapFrame() {
		initData();
		setInitLayout();
		addEventListener();

	}

	private void initData() {
		setTitle("치킨배달 게임");
		setSize(1000, 830);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		kitchenMapImg = new JLabel(new ImageIcon(kitchenMapFileName));
		deliveryMapImg = new JLabel(new ImageIcon(deliveryMapFileName));

		introImg = new JLabel(new ImageIcon(introFileName));

		changeDeliveryMapBtn = new JButton("배달하기");
		changeKitchenMapBtn = new JButton("주방으로");
		startBtn = new JButton("게임 시작");

		changeDeliveryMapBtn.setFont(new Font("D2Coding", Font.BOLD, 15));
		changeKitchenMapBtn.setFont(new Font("D2Coding", Font.BOLD, 15));

		startBtn.setFont(new Font("D2Coding", Font.BOLD, 15));
		startBtn.setBackground(Color.LIGHT_GRAY);

		sales = new Sales(this);
		player = Player.getInstance();

		totalSalesLabel = new JLabel("총 매출 : " + sales.updateTotalSales());
		goalSalesLabel = new JLabel("목표 매출 : " + sales.getRandomGoalSales());
		deliveryAddressLabel = new JLabel("배달지 : " + sales.address + "번 집");

		player.backgroundDeliveryService.deliveryServiceOn = false;
		player.backgroundKitchenService.kitchenServiceOn = true;

		kitPlayerF = new ImageIcon("images/LoopyKit_front.png");
		kitPlayerL = new ImageIcon("images/LoopyKit_left.png");
		kitPlayerR = new ImageIcon("images/LoopyKit_right.png");

		delPlayerF = new ImageIcon("images/LoopyDel_front.png");
		delPlayerL = new ImageIcon("images/LoopyDel_left.png");
		delPlayerR = new ImageIcon("images/LoopyDel_right.png");

		afterSucceedLabel = new AfterSucceedLabel(this);
	}

	private void setInitLayout() {
		setLayout(null);
		setLocationRelativeTo(null);

		changeDeliveryMapBtn.setBounds(800, 650, 100, 40);
		changeKitchenMapBtn.setBounds(800, 650, 100, 40);

		changeDeliveryMapBtn.setFont(new Font("D2Coding", Font.BOLD, 15));
		changeKitchenMapBtn.setFont(new Font("D2Coding", Font.BOLD, 15));
		changeDeliveryMapBtn.setBackground(Color.LIGHT_GRAY);
		changeKitchenMapBtn.setBackground(Color.LIGHT_GRAY);
		changeDeliveryMapBtn.setBorder(null);
		changeKitchenMapBtn.setBorder(null);

		startBtn.setBounds(430, 600, 100, 40);
		startBtn.setBorderPainted(false);

		kitchenMapImg.add(changeDeliveryMapBtn);
		deliveryMapImg.add(changeKitchenMapBtn);
		introImg.add(startBtn);

		kitchenMapImg.add(totalSalesLabel);
		kitchenMapImg.add(goalSalesLabel);
		kitchenMapImg.add(deliveryAddressLabel);

		totalSalesLabel.setBounds(800, 700, 200, 50);
		goalSalesLabel.setBounds(800, 720, 200, 50);
		deliveryAddressLabel.setBounds(800, 740, 200, 50);

		kitchenMapImg.add(player);

		setContentPane(introImg);

		setVisible(true);
		setResizable(false);

	}

	private void addEventListener() {

		changeDeliveryMapBtn.addActionListener(this);
		changeKitchenMapBtn.addActionListener(this);
		startBtn.addActionListener(this);

		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				switch (e.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					System.out.println("키이벤트 left");
					player.setIcon(player.getPlayerIconL());

					if (getContentPane() == kitchenMapImg) { // 키친맵인경우
						if (!player.isLeft() && !player.isLeftWallCrash()) {
							player.left();
						}

					} else { // 배달맵인경우
						if (!player.isLeft() && !player.isLeftWallCrash()) {
							player.left();
						}

					}
					repaint();
					break;
				case KeyEvent.VK_RIGHT:
					player.setIcon(player.getPlayerIconR());
					System.out.println("키이벤트 right");

					if (getContentPane() == kitchenMapImg) { // 키친맵인경우
						if (!player.isRight() && !player.isRightWallCrash()) {
							System.out.println("키친맵 right");
							player.right();
						}

					} else { // 배달맵인경우
						if (!player.isRight() && !player.isRightWallCrash()) {
							player.right();

						}
					}
					repaint();
					break;

				case KeyEvent.VK_UP:
					System.out.println("키이벤트 up");
					if (getContentPane() == kitchenMapImg) {
						if (!player.isUp() && !player.isTopCrash()) {
							player.up();
						}
					}
					repaint();
					break;

				case KeyEvent.VK_DOWN:
					if (getContentPane() == kitchenMapImg) {
						if (!player.isDown() && !player.isBottomCrash()) {
							player.down();
						}
					}
					repaint();
					break;

				case KeyEvent.VK_SPACE:
					// 맵 따라 점프 기능 달라짐
					if (getContentPane() == kitchenMapImg) {
						if (!player.isJumpUpInKit() && !player.isJumpDownInKit()) {

							System.out.println("space 점프 in kit");
							player.jumpUpInKit();
						}

					} else {
						if (!player.isJumpUpInDel() && !player.isJumpDownInDel()) {

							System.out.println("space 점프 in del");
							player.jumpUpInDel();
						}
					}
					repaint();
					break;

				case KeyEvent.VK_G: // 상호작용 G키
					System.out.println("G 상호작용");
					System.out.println("x : " + player.getX() + ", " + "y: " + player.getY());
					add(new Chicken(player));
					if (player.isCompleteDelivery()) {
						totalSalesLabel.setText("총 매출 : " + sales.updateTotalSales());
						goalSalesLabel.setText("목표 매출 : " + sales.goalSales);
						deliveryAddressLabel.setText("배달지 : " + sales.address + "번 집");
					}
					repaint();

					break;
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {

				switch (e.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					System.out.println("keyReleased left");
					player.setLeft(false);
					break;
				case KeyEvent.VK_RIGHT:
					player.setRight(false);
					break;
				case KeyEvent.VK_UP:
					player.setUp(false);
					break;
				case KeyEvent.VK_DOWN:
					player.setDown(false);
					break;

				}
			}
		});

		this.requestFocusInWindow();
	} // end of addEventListener

	@Override
	public void actionPerformed(ActionEvent e) {

		JButton targetBtn = (JButton) e.getSource();

		if (changeDeliveryMapBtn == targetBtn) {

			System.out.println("신속배달");
			setContentPane(deliveryMapImg);
			deliveryMapImg.add(player);
			deliveryMapImg.updateUI();

			deliveryMapImg.add(totalSalesLabel);
			deliveryMapImg.add(goalSalesLabel);
			deliveryMapImg.add(deliveryAddressLabel);

			player.setX(28);
			player.setY(690);
			player.setIcon(delPlayerR);
			player.setPlayerIconF(delPlayerF);
			player.setPlayerIconL(delPlayerL);
			player.setPlayerIconR(delPlayerR);

			player.backgroundDeliveryService.deliveryServiceOn = true;
			player.backgroundKitchenService.kitchenServiceOn = false;
			new Thread(player.backgroundDeliveryService).start();

		} else if (changeKitchenMapBtn == targetBtn) {
			System.out.println("주방으로");
			setContentPane(kitchenMapImg);
			kitchenMapImg.add(player);
			kitchenMapImg.updateUI();

			kitchenMapImg.add(totalSalesLabel);
			kitchenMapImg.add(goalSalesLabel);
			kitchenMapImg.add(deliveryAddressLabel);

			player.setX(450);
			player.setY(700);
			player.setIcon(kitPlayerR);
			player.setPlayerIconF(kitPlayerF);
			player.setPlayerIconL(kitPlayerL);
			player.setPlayerIconR(kitPlayerR);

			player.backgroundKitchenService.kitchenServiceOn = true;
			player.backgroundDeliveryService.deliveryServiceOn = false;
			new Thread(player.backgroundKitchenService).start();

		} else if (startBtn == targetBtn) {
			setContentPane(kitchenMapImg);
		} else {
			System.out.println("버튼 오류");
		}

		setVisible(true);
		this.requestFocusInWindow();

	}

	public static void main(String[] args) {
		new BackgroundMapFrame();
	}

}