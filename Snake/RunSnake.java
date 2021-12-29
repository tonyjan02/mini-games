package org.cis120;

import java.awt.*;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class RunSnake implements Runnable {
    public void run() {

        final JFrame frame = new JFrame("Snake");
        frame.setLocation(300, 300);

        // Status panel
        final JPanel status_panel = new JPanel();
        frame.add(status_panel, BorderLayout.SOUTH);
        final JLabel status = new JLabel("Running...");
        status_panel.add(status);

        // Main playing area
        final GameCourt court = new GameCourt(status);
        frame.add(court, BorderLayout.CENTER);

        final JPanel control_panel = new JPanel();
        frame.add(control_panel, BorderLayout.NORTH);
        
        // Instructions button
        final JButton instructions = new JButton("Instructions");
        instructions.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String message = "Hello! Welcome to the Snake game. \n \n"
        				+ "This game is a timer-based game where your blue snake attempts to eat its red apples to grow. \n"
        				+ "Try your best to collect as many apples as you can without running into any walls or yourself! \n \n"
        				+ "Your score is at the bottom of the screen and the Instructions, Reset, Save, Load buttons are at the top of the screen. \n"
        				+ "You can save your game at any time by pressing the space bar to pause and pressing the Save button. \n"
        				+ "Using the load button at a later time will load the last save you had. \n \n"
        				+ "The snake is controlled by these keys: W,S,A,D,SPACE (W=UP, S=DOWN, A=LEFT, D=RIGHT, SPACE=STOP) \n"
        				+ "Have fun!";
        		JOptionPane.showMessageDialog(frame,  message);
        	}
        });
        control_panel.add(instructions);
        
        // Reset button
        final JButton reset = new JButton("Reset");
        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                court.reset();
            }
        });
        control_panel.add(reset);

        // Save button
        final JButton save = new JButton("Save");
        save.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String message = court.save();
                JOptionPane.showMessageDialog(frame, message);
        	}
        });
        control_panel.add(save);        
        
        // Load button
        final JButton load = new JButton("Load");
        load.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String message = court.load();
                JOptionPane.showMessageDialog(frame, message);
        	}
        });
        control_panel.add(load);
        
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        court.reset();
    }
    
}