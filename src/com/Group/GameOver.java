package com.Group;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameOver extends JFrame {

	public static Font Label = new Font("MonoSpaced", Font.BOLD, 20);
	Group g;

	public GameOver() {
		setTitle("Odyssey");
		setSize(310, 130);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setUndecorated(true);
		setResizable(false);
		JLabel label = new JLabel("You Win!");
		label.setFont(Label);
		JPanel panel = new JPanel();
		JPanel panel2 = new JPanel();
		Container p = getContentPane();
		FlowLayout layout = new FlowLayout();
		JButton play = new JButton("Play Again?");
		play.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Enjoy!");
				g = new Group();
				JFrame frame = new JFrame("Side Scroller");
				frame.setUndecorated(false);
				frame.requestFocus();
				frame.setSize(1000, 700);
				frame.setLocationRelativeTo(null);
				frame.add(g);
				frame.setResizable(false);
				frame.setVisible(true);
				g.init();
			}
		});
		JButton exit = new JButton("Exit");
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Goodbye :)");
				System.exit(0);
			}
		});
		panel.add(play);
		panel.add(exit);
		panel2.add(label);
		p.setBackground(Color.white);
		p.add(panel2, BorderLayout.CENTER);
		p.add(panel, BorderLayout.SOUTH);
		setVisible(true);
	}
}
