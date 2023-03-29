package ver1;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import lombok.Data;

@Data
public class Chicken extends JLabel implements Moveable {

	private Player player;

	// 위치 상태
	private int x;
	private int y;

	private Sales sales;

	// 움직임 상태
	private boolean left;
	private boolean right;
	private boolean up;
	private boolean down;

	private final int CHICKEN_PRICE = 19000;

	private ImageIcon raw; // 생닭
	private ImageIcon pos1; // 주문없음
	private ImageIcon pos2; // 주문들어옴
	private ImageIcon sauce; // 양념통
	private ImageIcon sauceP; // 양념방울
	private ImageIcon oilP; // 기름방울
	private ImageIcon plate; // 접시
	private ImageIcon box1; // 빈박스
	private ImageIcon box2; // 치킨담은 박스
	private ImageIcon box3; // 포장된 박스
	private ImageIcon chicDummy1; // 치킨반죽더미
	private ImageIcon chicDummy2; // 후라이드치킨더미
	private ImageIcon chicDummy3; // 양념치킨더미
	private ImageIcon chicP1; // 치킨반죽 1조각
	private ImageIcon chicP2; // 후라이드치킨 1조각
	private ImageIcon chicP3; // 양념치킨 1조각
	private ImageIcon frying1; // 튀겨지는 중
	private ImageIcon frying2; // 튀기기 완료
	private ImageIcon emptyNet; // 빈 튀김통
	private ImageIcon order; // 빈 튀김통

	private boolean order_area;
	private boolean fridge_area;
	private boolean chickDummy1_area;
	private boolean frying1_area;
	private boolean frying2_area;
	private boolean chicDummy3_area;
	private boolean box3_area;

	public Chicken(Player player) {
		this.player = player;
		initObject();
		initSetting();
		initThread();
	}

	private void initObject() {
		raw = new ImageIcon("images/raw.png");
		pos1 = new ImageIcon("images/pos1.png");
		pos2 = new ImageIcon("images/pos2.png");
		sauce = new ImageIcon("images/sauce.png");
		sauceP = new ImageIcon("images/sauceP.png");
		oilP = new ImageIcon("images/oilP.png");
		plate = new ImageIcon("images/plate.png");
		box1 = new ImageIcon("images/box1.png");
		box2 = new ImageIcon("images/box2.png");
		box3 = new ImageIcon("images/box3.png");
		chicDummy1 = new ImageIcon("images/chicDummy1.png");
		chicDummy2 = new ImageIcon("images/chicDummy2.png");
		chicDummy3 = new ImageIcon("images/chicDummy3.png");
		chicP1 = new ImageIcon("images/chicP1.png");
		chicP2 = new ImageIcon("images/chicP2.png");
		chicP3 = new ImageIcon("images/chicP3.png");
		frying1 = new ImageIcon("images/frying1.png");
		frying2 = new ImageIcon("images/frying2.png");
		emptyNet = new ImageIcon("images/emptyNet.png");
		order = new ImageIcon("images/order.png");

	}

	private void order() {
		setIcon(order);
		setSize(135, 27);
		System.out.println("주문을 받습니다");
		player.setFoodStep(0);
		// System.out.println(foodStep);
	}

	private void raw() {
		setIcon(raw);
		setSize(40, 44);
		System.out.println("냉장고에서 생닭을 꺼냅니다");
		player.setFoodStep(1);
		// System.out.println(foodStep);
	}

	private void chicDummy1() {
		setIcon(chicDummy1);
		setSize(100, 55);
		System.out.println("생닭을 반죽합니다");
		player.setFoodStep(2);
		// System.out.println(foodStep);
	}

	private void frying1() {
		setIcon(frying1);
		setSize(200, 98);
		System.out.println("반죽을 튀깁니다");
		player.setFoodStep(3);
		// System.out.println(foodStep);
	}

	private void frying2() {
		setIcon(frying2);
		setSize(200, 95);
		System.out.println("한 번 더 튀깁니다");
		player.setFoodStep(4);
		// System.out.println(foodStep);
	}

	private void chicDummy3() {
		setIcon(chicDummy3);
		setSize(80, 50);
		System.out.println("양념합니다");
		player.setFoodStep(5);
		// System.out.println(foodStep);
	}

	private void box3() {
		setIcon(box3);
		setSize(100, 87);
		System.out.println("포장합니다");
		player.setFoodStep(6);
		// System.out.println(foodStep);
	}

	private void initSetting() {
		left = false;
		right = false;
		up = false;
		down = false;

		x = player.getX();
		y = player.getY();

		order_area = (x <= 240 && (508 <= y && y <= 604));
		fridge_area = (x >= 700 && (400 <= y && y <= 504));
		chickDummy1_area = (x <= 280 && (332 <= y && y <= 420));
		frying1_area = (x <= 280 && (172 <= y && y <= 228));
		frying2_area = (x <= 280 && (24 <= y && y <= 72));
		chicDummy3_area = ((550 <= x && x <= 658) && (24 <= y && y <= 96));
		box3_area = ((550 <= x && x <= 658) && (212 <= y && y <= 308));

		if (player.getBackgroundKitchenService().kitchenServiceOn) {

			if (order_area) { // order
				order();
			} else if (fridge_area && player.getFoodStep() == 0) { // 냉장고
				raw();
			} else if (chickDummy1_area && player.getFoodStep() == 1) { // 반죽
				chicDummy1();
			} else if (frying1_area && player.getFoodStep() == 2) { // 튀김기1
				frying1();
			} else if (frying2_area && player.getFoodStep() == 3) { // 튀김기2
				frying2();
			} else if (chicDummy3_area && player.getFoodStep() == 4) { // 양념
				chicDummy3();
			} else if (box3_area && player.getFoodStep() == 5) { // 포장
				box3();
			}

		} else if (player.getBackgroundDeliveryService().deliveryServiceOn && player.getFoodStep() == 6) {

			if (sales.address == 1) {
				if ((0 <= x && x < 173) && (0 <= y && y < 181)) {

					System.out.println("1번집 배달");
					setIcon(box3);
					setSize(100, 87);
					System.out.println("1번집 배달완료");
					player.setCompleteDelivery(true);
				} else {
					System.out.println("잘못된 배달입니다.");
					player.setCompleteDelivery(false);
				}

			} else if (sales.address == 2) {
				if ((0 <= x && x < 141) && (182 <= y && y < 384)) {

					System.out.println("2번집 배달");
					setIcon(box3);
					setSize(100, 87);
					System.out.println("2번집 배달완료");
					player.setCompleteDelivery(true);
				} else {
					System.out.println("잘못된 배달입니다.");
					player.setCompleteDelivery(false);
				}

			} else if (sales.address == 3) {

				if ((400 <= x && x < 631) && (0 <= y && y < 167)) {
					System.out.println("3번집 배달");
					setIcon(box3);
					setSize(100, 87);
					System.out.println("3번집 배달완료");
					player.setCompleteDelivery(true);
				} else {
					System.out.println("잘못된 배달입니다.");
					player.setCompleteDelivery(false);
				}

			} else if (sales.address == 4) {

				if ((340 <= x && x < 487) && (240 <= y && y < 400)) {
					System.out.println("4번집 배달");
					setIcon(box3);
					setSize(100, 87);
					System.out.println("4번집 배달완료");
					player.setCompleteDelivery(true);
				} else {
					System.out.println("잘못된 배달입니다.");
					player.setCompleteDelivery(false);
				}

			} else if (sales.address == 5) {

				if ((356 <= x && x < 487) && (393 <= y && y < 570)) {
					System.out.println("5번집 배달");
					setIcon(box3);
					setSize(100, 87);
					System.out.println("5번집 배달완료");
					player.setCompleteDelivery(true);

				} else {
					System.out.println("잘못된 배달입니다.");
					player.setCompleteDelivery(false);
				}

			} else if (sales.address == 6) {

				if ((487 <= x && x < 626) && (240 <= y && y < 392)) {
					System.out.println("6번집 배달");
					setIcon(box3);
					setSize(100, 87);
					System.out.println("6번집 배달완료");
					player.setCompleteDelivery(true);
				} else {
					System.out.println("잘못된 배달입니다.");
					player.setCompleteDelivery(false);
				}

			} else if (sales.address == 7) {

				if ((858 <= x && x < 945) && (0 <= y && y < 112)) {
					System.out.println("7번집 배달");
					setIcon(box3);
					setSize(100, 87);
					System.out.println("7번집 배달완료");
					player.setCompleteDelivery(true);
				} else {
					System.out.println("잘못된 배달입니다.");
					player.setCompleteDelivery(false);
				}

			} else if (sales.address == 8) {

				if ((820 <= x && x < 945) && (408 <= y && y < 568)) {
					System.out.println("8번집 배달");
					setIcon(box3);
					setSize(100, 87);
					System.out.println("8번집 배달완료");
					player.setCompleteDelivery(true);
				} else {
					System.out.println("잘못된 배달입니다.");
					player.setCompleteDelivery(false);
				}
			}

		} else {
			System.out.println("맵 오류");
		}

	}

	private void initThread() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				if (player.getPlayerWay() == PlayerWay.LEFT) {
					left();
				} else {
					right();
				}
			}
		}).start();

	}

	@Override
	public void left() {
		left = true;
		if (player.getBackgroundKitchenService().kitchenServiceOn) {
			for (int i = 0; i < 180; i++) {
				x--;
				setLocation(x, y);
			}
		} else {
			for (int i = 0; i < 50; i++) {
				x--;
				setLocation(x, y);
			}
		}
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		left = false; // 상태변수 초기화
		removeChicken();
	}

	@Override
	public void right() {
		right = true;
		if (player.getBackgroundKitchenService().kitchenServiceOn) {
			for (int i = 0; i < 180; i++) {
				x++;
				setLocation(x, y);
			}
		} else {
			for (int i = 0; i < 50; i++) {
				x++;
				setLocation(x, y);
			}
		}
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		right = false;
		removeChicken();
	}

	private void removeChicken() {

		try {

			Thread.sleep(1000);
			setIcon(null);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}