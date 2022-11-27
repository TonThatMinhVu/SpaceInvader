import java.awt.event.*;
import javax.swing.*;

/**
 * 
 * @author
 */
public class PushPanel extends JPanel {

	/**
	 *
	 */
	private static final long serialVersionUID = 8387668405974705371L;

	private JButton push, push2;

	/*
	 * Constructor
	 */
	public PushPanel() {
		push = new JButton("start");
		push.addActionListener(new ButtonListener());
		push.setBounds(800, 800, 200, 100);

		add(push);

		push2 = new JButton("New Level");
		push2.addActionListener(new Button());
		push2.setBounds(800, 800, 200, 100);

		add(push2);

	}

	private class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			new SpaceInvaders();

		}
	}

	private class Button implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent E) {
			new SpaceInvaders();
		}
	}
}
