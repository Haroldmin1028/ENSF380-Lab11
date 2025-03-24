package edu.ucalgary.oop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class SmartHomeGUI extends JFrame implements ActionListener, MouseListener {
    private SmartHome smartHome;
    private SmartLight light;
    private SmartLock lock;
    private SmartThermostat thermostat;

    private JLabel title, lightLabel, thermostatLabel, lockLabel, modeLabel;
    private JLabel lightStatusLabel, thermostatStatusLabel, lockStatusLabel, modeSelectLabel;
    private JTextField temperatureInput;
    private JButton lightOn, lightOff, setTemperature, lockLock, lockUnlock, sleepMode, vacationMode;

    public SmartHomeGUI() {
        super("Smart Home System");

        smartHome = new SmartHome();
        light = new SmartLight();
        lock = new SmartLock();
        thermostat = new SmartThermostat();

        setupGUI();
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void setupGUI() {

        smartHome.addDevice(light).addDevice(thermostat).addDevice(lock);
        smartHome.build();

        title = new JLabel("Smart Home System");
        lightLabel = new JLabel("Smart Light");
        thermostatLabel = new JLabel("Smart Thermostat");
        lockLabel = new JLabel("Smart Lock");
        modeLabel = new JLabel("Modes");

        JPanel lockPanel = new JPanel();
        JPanel thermostatPanel = new JPanel();
        JPanel modesPanel = new JPanel();



        lightStatusLabel = new JLabel("Light: ");
        thermostatStatusLabel = new JLabel("Thermostat: ");
        lockStatusLabel = new JLabel("Lock: ");
        modeSelectLabel = new JLabel("Select Mode");

        if (light.getState()) {
            lightStatusLabel.setText("Light: ON");
        }
        else {
            lightStatusLabel.setText("Light: OFF");
        }
        if (lock.getState()) {
            lockStatusLabel.setText("Lock: LOCKED");
        }
        else {
            lockStatusLabel.setText("Lock: UNLOCKED");
        }
        thermostatStatusLabel.setText("Thermostat: " + thermostat.getState() + "C");


        lightOn = new JButton("Turn ON");
        lightOn.addActionListener(this);
        lightOff = new JButton("Turn OFF");
        lightOff.addActionListener(this);
        temperatureInput = new JTextField(15); //what is columns?
        temperatureInput.addActionListener(this);
        setTemperature = new JButton("Set Temperature");
        setTemperature.addActionListener(this);
        lockLock = new JButton("Lock");
        lockLock.addActionListener(this);
        lockUnlock = new JButton("Unlock");
        lockUnlock.addActionListener(this);
        sleepMode = new JButton("Sleep Mode");
        sleepMode.addActionListener(this);
        vacationMode = new JButton("Vacation Mode");
        vacationMode.addActionListener(this);



        JPanel titlePanel = new JPanel();
        //titlePanel.setLayout(new BoxLayout());
        titlePanel.add(title);

        JPanel lightPanel = new JPanel();
        lightPanel.setLayout(new FlowLayout());
        lightPanel.add(lightLabel, BorderLayout.NORTH);
        lightPanel.add(lightStatusLabel);
        lightPanel.add(lightOn);
        lightPanel.add(lightOff);


        thermostatPanel.setLayout(new FlowLayout());
        thermostatPanel.add(thermostatLabel);
        thermostatPanel.add(thermostatStatusLabel);
        thermostatPanel.add(temperatureInput);
        thermostatPanel.add(setTemperature);


        lockPanel.add(lockLabel);
        lockPanel.add(lockStatusLabel);
        lockPanel.add(lockLock);
        lockPanel.add(lockUnlock);


        modesPanel.add(modeLabel, BorderLayout.NORTH);
        modesPanel.add(modeSelectLabel);
        modesPanel.add(sleepMode);
        modesPanel.add(vacationMode);

        this.setLayout(new FlowLayout());
        this.add(titlePanel);
        this.add(lightPanel);
        this.add(thermostatPanel);
        this.add(lockPanel);
        this.add(modesPanel);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == lightOn) {
            light.setState(true);
            lightStatusLabel.setText("Light: ON");
            JOptionPane.showMessageDialog(this, "Light is now ON");
        }
        if (e.getSource() == lightOff) {
            light.setState(false);
            lightStatusLabel.setText("Light: OFF");
            JOptionPane.showMessageDialog(this, "Light is now OFF");
        }

        if (e.getSource() == setTemperature) {
            thermostat.setState(Integer.parseInt(temperatureInput.getText()));
            thermostatStatusLabel.setText("Thermostat: " + thermostat.getState() + "C");
            JOptionPane.showMessageDialog(this, "Temperature set to " + thermostat.getState() + "C");

        }

        if (e.getSource() == lockLock) {
            lock.setState(true);
            lockStatusLabel.setText("Lock is now LOCKED");
            JOptionPane.showMessageDialog(this, "Lock is now LOCKED");
        }
        if (e.getSource() == lockUnlock) {
            lock.setState(false);
            lockStatusLabel.setText("Lock is now UNLOCKED");
            JOptionPane.showMessageDialog(this, "Lock is now UNLOCKED");
        }
        if (e.getSource() == sleepMode) {
            light.setState(false);
            lightStatusLabel.setText("Light: OFF");
            JOptionPane.showMessageDialog(this, "Light is now OFF");
            thermostat.setState(18);
            thermostatStatusLabel.setText("Thermostat: " + thermostat.getState() + "C");
            JOptionPane.showMessageDialog(this, "Temperature set to " + thermostat.getState() + "C");
            lock.setState(true);
            lockStatusLabel.setText("Lock is now LOCKED");
            JOptionPane.showMessageDialog(this, "Lock is now LOCKED");
        }
        if (e.getSource() == vacationMode) {
            light.setState(true);
            lightStatusLabel.setText("Light: ON");
            JOptionPane.showMessageDialog(this, "Light is now ON");
            thermostat.setState(20);
            thermostatStatusLabel.setText("Thermostat: " + thermostat.getState() + "C");
            JOptionPane.showMessageDialog(this, "Temperature set to " + thermostat.getState() + "C");
            lock.setState(true);
            lockStatusLabel.setText("Lock is now LOCKED");
            JOptionPane.showMessageDialog(this, "Lock is now LOCKED");
        }

        //sleep: light off, temp = 18, lock door
        //vacation: light on, temp = 20, lock door
    }

    public void mouseClicked(MouseEvent e) {

    }
    public void mouseEntered(MouseEvent event){

    }

    public void mouseExited(MouseEvent event){

    }

    public void mousePressed(MouseEvent event){

    }

    public void mouseReleased(MouseEvent event){

    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            new SmartHomeGUI().setVisible(true);
        });
    }

}
