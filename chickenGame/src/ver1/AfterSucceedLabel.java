package ver1;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import lombok.Data;

@Data
public class AfterSucceedLabel extends JLabel implements ActionListener {

	private ImageIcon image;
	private JButton finishGameBtn;

	private Player player;

	BackgroundMapFrame mContext;

	public AfterSucceedLabel(BackgroundMapFrame mContext) {
		this.mContext = mContext;
		initObject();
		initSettings();
		initaddEventListener();
	}

	private void initObject() {

		image = new ImageIcon("images/Map_finishing.jpg");
		finishGameBtn = new JButton("게임종료");

		player = Player.getInstance();

	}

	private void initSettings() {

		setIcon(image);
		setSize(1000, 830);
		setLocation(-8, -15);
		setLayout(null);

		finishGameBtn.setBounds(430, 450, 100, 40);
		finishGameBtn.setFont(new Font("D2Coding", Font.BOLD, 15));
		finishGameBtn.setBackground(Color.LIGHT_GRAY);
		finishGameBtn.setBorder(null);

		add(finishGameBtn);
		mContext.setContentPane(this);
		this.requestFocusInWindow();

	}

	private void initaddEventListener() {
		finishGameBtn.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton targetBtn = (JButton) e.getSource();

		if (finishGameBtn == targetBtn) {

			System.out.println("게임 종료");
			System.exit(0);

		}
	}

}