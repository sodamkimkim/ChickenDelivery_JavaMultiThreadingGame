package ver1;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BackgroundKitchenServiceFrame implements Runnable {

	private BufferedImage kitchenServiceImg;

	private Player player;

	boolean kitchenServiceOn;

	public BackgroundKitchenServiceFrame(Player player) {
		this.player = player;
		kitchenServiceOn = true;
		try {
			kitchenServiceImg = ImageIO.read(new File("images/Map_kitService.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		System.out.println("키친 백그라운드 진행중");

		while (kitchenServiceOn) {
			Color leftColor = new Color(kitchenServiceImg.getRGB(player.getX() + 10, player.getY() + 40));
			int leftColorInt = kitchenServiceImg.getRGB(player.getX() + 10, player.getY() + 40);

			Color rightColor = new Color(
					kitchenServiceImg.getRGB(player.getX() + player.getWidth(), player.getY() + 40));
			int rightColorInt = kitchenServiceImg.getRGB(player.getX() + player.getWidth(), player.getY() + 40);

			int topColorInt = kitchenServiceImg.getRGB(player.getX() + 20, player.getY())
					+ kitchenServiceImg.getRGB(player.getX() + 55 - 20, player.getY());

			int bottomColorInt = kitchenServiceImg.getRGB(player.getX() + 20, player.getY() + player.getHeight())
					+ kitchenServiceImg.getRGB(player.getX() + 55 - 20, player.getY() + player.getHeight());

			if (bottomColorInt != -2) { // 바닥흰색배경이 아니면

				//System.out.println("바닥과 닿았어");
				player.setBottomCrash(true);

				player.setDown(false);
				player.setJumpDownInKit(false);
				player.setJumpDownInDel(false);

			} else { // 바닥이 흰색이면
				player.setBottomCrash(false);
		

			}

			if (leftColorInt != -1) {
				//System.out.println("왼쪽 벽 충돌");
				player.setLeftWallCrash(true);
				player.setLeft(false);
			} else {
				player.setLeftWallCrash(false);

			}
			if (rightColorInt != -1) {
				//System.out.println("오른쪽 벽 충돌");
				player.setRightWallCrash(true);
				player.setRight(false);

			} else {
				player.setRightWallCrash(false);
			}

			if (topColorInt != -2) { // 천장흰색아니면
				//System.out.println("천장 충돌");
				player.setTopCrash(true);
				player.setUp(false);
				player.setJumpUpInKit(false);
				player.setJumpUpInDel(false);

			} else {
				player.setTopCrash(false);

			}

			try {
				Thread.sleep(1);

			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		} // end of while

	}

} // end of class