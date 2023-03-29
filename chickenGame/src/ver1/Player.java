package ver1;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import lombok.Data;

@Data
public class Player extends JLabel implements Moveable {

	private static Player instance = new Player();

	// 배달완료 상태
	private boolean completeDelivery;
	// 위치 상태
	private int x;
	private int y;

	// 플레이어의 방향
	private PlayerWay playerWay;
	
	// 플레이어의 조리 순서
	private int foodStep;

	// 움직임 상태
	private boolean left;
	private boolean right;
	private boolean up;
	private boolean down;
	private boolean jumpUpInKit;
	private boolean jumpDownInKit;
	private boolean jumpUpInDel;
	private boolean jumpDownInDel;

	// 플레이어 속도 상태
	private final int SPEED = 2;
	private final int JUMPSPEED = 2;

	// 벽 충돌 상태
	private boolean leftWallCrash;
	private boolean rightWallCrash;
	private boolean TopCrash;
	private boolean bottomCrash;

	private ImageIcon playerIconF;
	private ImageIcon playerIconL;
	private ImageIcon playerIconR;

	BackgroundDeliveryServiceFrame backgroundDeliveryService;
	BackgroundKitchenServiceFrame backgroundKitchenService;

	private Player() {
		initSetting();
		initBackgroundPlayerService();
	}

	public static Player getInstance() {
		if (instance == null) {
			instance = new Player();
		}
		return instance;
	}

	private void initSetting() {
		x = 450;
		y = 700;

		left = false;
		right = false;
		up = false;
		down = false;
		jumpUpInKit = false;
		jumpDownInKit = false;
		jumpUpInDel = false;
		jumpDownInDel = false;

		leftWallCrash = false;
		rightWallCrash = false;
		TopCrash = false;
		bottomCrash = false;

		completeDelivery = false;

		playerWay = PlayerWay.RIGHT;

		playerIconF = new ImageIcon("images/LoopyKit_front.png");
		playerIconL = new ImageIcon("images/LoopyKit_left.png");
		playerIconR = new ImageIcon("images/LoopyKit_right.png");

		setIcon(playerIconF);
		setSize(55, 80);
		setLocation(x, y);

		backgroundDeliveryService = new BackgroundDeliveryServiceFrame(this);
		backgroundKitchenService = new BackgroundKitchenServiceFrame(this);

	}

	private void initBackgroundPlayerService() {

		new Thread(backgroundKitchenService).start();

	}

	@Override
	public void left() {
		System.out.println("left");
		playerWay = PlayerWay.LEFT;
		left = true;
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (left) {
					setIcon(playerIconL);

					x -= SPEED;
					setLocation(x, y);
					try {
						Thread.sleep(5);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

			}
		}).start();
	}

	@Override
	public void right() {
		System.out.println("right");
		playerWay = playerWay.RIGHT;
		right = true;
		new Thread(new Runnable() {
			@Override
			public void run() {

				while (right) {
					setIcon(playerIconR);
					x += SPEED;
					setLocation(x, y);

					try {
						Thread.sleep(5);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

			}
		}).start();

	}

	@Override
	public void up() {
		System.out.println("up");
		up = true;
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (up) {
					y -= SPEED;
					setLocation(x, y);
					try {
						Thread.sleep(5);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}
			}
		}).start();

	}

	@Override
	public void down() {
		System.out.println("down");
		down = true;
		new Thread(new Runnable() {

			@Override
			public void run() {
				while (down) {
					setIcon(playerIconF);
					y += SPEED;
					setLocation(x, y);
					try {
						Thread.sleep(5);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

			}

		}).start();

	}

	@Override
	public void jumpUpInKit() {
		System.out.println("jumpUp");
		jumpUpInKit = true;
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 120 / JUMPSPEED; i++) {
					y -= JUMPSPEED;
					setLocation(x, y);
					try {
						Thread.sleep(5);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}
				jumpUpInKit = false;
				jumpDownInKit();

			}
		}).start();

	}

	@Override
	public void jumpDownInKit() {
		System.out.println("jumpDown");
		jumpDownInKit = true;
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 120 / JUMPSPEED; i++) {
					y += JUMPSPEED;
					setLocation(x, y);
					try {
						Thread.sleep(5);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}
				jumpDownInKit = false;
			}
		}).start();

	}

	@Override
	public void jumpUpInDel() {

		System.out.println("jumpUp");
		jumpUpInDel = true;
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 170 / JUMPSPEED; i++) {
					y -= JUMPSPEED;
					setLocation(x, y);
					try {
						Thread.sleep(5);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}
				jumpUpInDel = false;
				jumpDownInDel();

			}
		}).start();

	}

	@Override
	public void jumpDownInDel() {

		System.out.println("jumpDown");
		jumpDownInDel = true;
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (jumpDownInDel) {
					y += JUMPSPEED;
					setLocation(x, y);
					try {
						Thread.sleep(5);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}
				jumpDownInDel = false;
			}
		}).start();

	}

} // end of class