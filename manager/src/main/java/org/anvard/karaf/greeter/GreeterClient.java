package org.anvard.karaf.greeter;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(immediate = true)
public class GreeterClient {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(GreeterClient.class);

	private GreeterManager manager;
	private JFrame frame;

	public GreeterClient() {
		LOGGER.info("Greeter Client starting up");
		this.manager = new GreeterManager();
	}

	@Activate
	public void activate() {
		LOGGER.info("Greeter Client activated");
		final JTextField greeting = new JTextField(20);
		frame = new JFrame("Greeter");
		JPanel panel = new JPanel(new BorderLayout());
		JPanel center = new JPanel(new FlowLayout());
		center.add(new JLabel("Greeting: "));
		center.add(greeting);

		JPanel top = new JPanel(new FlowLayout());
		final StringListComboBoxModel model = new StringListComboBoxModel();
		model.setItems(manager.getLanguages());
		final JComboBox<String> cb = new JComboBox<String>(model);
		top.add(new JLabel("Languages: "));
		top.add(cb);

		JPanel bottom = new JPanel(new FlowLayout());
		JButton button = new JButton("Be Greeted");
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String lang = (null != cb.getSelectedItem()) ? cb
						.getSelectedItem().toString() : "en-us";
				greeting.setText(manager.greet(lang));
			}
		});
		bottom.add(button);

		JButton button2 = new JButton("Refresh Languages");
		button2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				model.setItems(manager.getLanguages());
			}
		});
		bottom.add(button2);
		
		panel.add(top, BorderLayout.NORTH);
		panel.add(center, BorderLayout.CENTER);
		panel.add(bottom, BorderLayout.SOUTH);

		frame.add(panel);
		frame.setSize(400, 75);
		frame.pack();
		frame.setVisible(true);
	}

	public void deactivate() {
		if (null != frame) {
			frame.setVisible(false);
		}
	}

	private static class StringListComboBoxModel extends
			DefaultComboBoxModel<String> {

		private static final long serialVersionUID = -811279689153600782L;
		private List<String> items = new ArrayList<>();

		public void setItems(List<String> items) {
			this.items = items;
			fireContentsChanged(this, 0, items.size());
		}

		@Override
		public int getSize() {
			return items.size();
		}

		@Override
		public String getElementAt(int index) {
			return items.get(index);
		}

	}
}
