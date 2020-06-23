package com.yusufcancelik;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

public class CalcFrame extends JFrame {

    private JMenuBar menu = new JMenuBar();
    private JPanel panel = new JPanel(new GridLayout(2, 1));
    private JPanel secondPanel = new JPanel(new GridLayout(2, 1));
    private JPanel dataPanel = new JPanel(new GridLayout(3, 4));
    private JPanel howManyHostsPanel = new JPanel(new GridLayout(2, 2));
    private JPanel calculationsPanel = new JPanel(new GridLayout(5, 5));
    private JTextField hostAddress = new JTextField();
    private JTextField subnetMask = new JTextField();
    private JButton networkAddress = new JButton();
    private JButton broadcastAddress = new JButton();
    private JButton firstHost = new JButton();
    private JButton lastHost = new JButton();
    private Button calculateButton = new Button("Hesapla");
    private JLabel howManyHosts = new JLabel("0");
    private Calculations calculations = new Calculations(this);
    private FileOperations fileOperations = new FileOperations(this, calculations);

    public CalcFrame() {
        setJMenuBar(menu);
        addMenu();
        addDataPanel();
        addCalculationsPanel();

        add(panel);
    }

    private void addMenu() {
        JMenu file = new JMenu("Islemler");
        JMenuItem newFile = new JMenuItem("Yeni", 'N');
        JMenuItem exit = new JMenuItem("Cikis Yap");
        exit.addActionListener(ActionEvent -> System.exit(0));
        newFile.addActionListener(ActionEvent -> setEverythingEmpty());

        file.add(newFile);
        file.add(exit);

        menu.add(file);
    }

    private void addDataPanel() {
        networkAddress.setEnabled(false);
        broadcastAddress.setEnabled(false);
        firstHost.setEnabled(false);
        lastHost.setEnabled(false);
        howManyHosts.setEnabled(false);

        Border etched = BorderFactory.createEtchedBorder();
        Border titled = BorderFactory.createTitledBorder(etched, "Network Bilgileri");
        secondPanel.setBorder(titled);

        dataPanel.add(new JLabel("Host Address"));
        dataPanel.add(hostAddress);

        dataPanel.add(new JLabel("Subnet Mask"));
        dataPanel.add(subnetMask);

        dataPanel.add(new JLabel("Network Adresi/ID"));
        dataPanel.add(networkAddress);

        dataPanel.add(new JLabel("Broadcast Adresi/ID"));
        dataPanel.add(broadcastAddress);

        dataPanel.add(new JLabel("Birinci Host Adresi"));
        dataPanel.add(firstHost);

        dataPanel.add(new JLabel("Son Host Adresi"));
        dataPanel.add(lastHost);



        howManyHostsPanel.add(new JLabel("Kullanilabilir Host Sayisi"));
        howManyHostsPanel.add(howManyHosts);

        calculateButton.setSize(200,200);
        calculateButton.addActionListener(ActionEvent -> calculations.executeAllCalculations());
        howManyHostsPanel.add(calculateButton);

        secondPanel.add(dataPanel);
        secondPanel.add(howManyHostsPanel);

        panel.add(secondPanel);
    }

    private void addCalculationsPanel() {
        BoolArithmeticButtons.setAllDisabled();

        Border etched = BorderFactory.createEtchedBorder();
        Border titled = BorderFactory.createTitledBorder(etched, "Hesaplama");
        calculationsPanel.setBorder(titled);

        calculationsPanel.add(new JLabel("                     Host"));
        for (int i = 0; i < 4; i++)
            calculationsPanel.add(BoolArithmeticButtons.hostBinary[i]);

        calculationsPanel.add(new JLabel("              Subnet Mask"));
        for (int i = 0; i < 4; i++)
            calculationsPanel.add(BoolArithmeticButtons.subnetBinary[i]);

        calculationsPanel.add(new JLabel("                                           NOT"));
        for (int i = 0; i < 4; i++)
            calculationsPanel.add(BoolArithmeticButtons.subnetBinaryNegation[i]);

        calculationsPanel.add(new JLabel("Network Address         AND"));
        for (int i = 0; i < 4; i++)
            calculationsPanel.add(BoolArithmeticButtons.networkBinary[i]);

        calculationsPanel.add(new JLabel("Broadcast Address        OR"));
        for (int i = 0; i < 4; i++)
            calculationsPanel.add(BoolArithmeticButtons.broadcastBinary[i]);

        panel.add(calculationsPanel);
    }

    String setTextOfButton(JButton[] buttons) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < 4; i++) {
            builder.append(calculations.fromBinaryToInteger(buttons[i]));

            if (i < 3)
                builder.append('.');
        }

        return builder.toString();
    }

    void setEverythingEmpty() {
        hostAddress.setText("");
        subnetMask.setText("");
        howManyHosts.setText(String.valueOf(0));
        networkAddress.setText("");
        broadcastAddress.setText("");
        firstHost.setText("");
        lastHost.setText("");

        for (int i = 0; i < 4; i++) {
            BoolArithmeticButtons.hostBinary[i].setText("");
            BoolArithmeticButtons.subnetBinary[i].setText("");
            BoolArithmeticButtons.subnetBinaryNegation[i].setText("");
            BoolArithmeticButtons.networkBinary[i].setText("");
            BoolArithmeticButtons.broadcastBinary[i].setText("");
        }
    }

    String getSubnetMask() { return subnetMask.getText(); }
    String getHostAddress() { return hostAddress.getText(); }
    String getNetworkAddress() { return networkAddress.getText(); }
    String getBroadcastAddress() { return broadcastAddress.getText(); }
    String getFirstHostAddress() { return firstHost.getText(); }
    String getLastHostAddress() { return lastHost.getText(); }
    String getHowManyHosts() { return howManyHosts.getText(); }

    public void setValuesOfDataPanel(String host, String subnet, String network, String broadcast) {
        hostAddress.setText(host);
        subnetMask.setText(subnet);
        networkAddress.setText(network);
        broadcastAddress.setText(broadcast);
    }

    void setHowManyHosts(int value) { this.howManyHosts.setText(String.valueOf(value)); }
    void setNetworkAddress(String value) { this.networkAddress.setText(value); }
    void setBroadcastAddress(String value) { this.broadcastAddress.setText(value); }
    void setFirstHost(String value) { this.firstHost.setText(value); }
    void setLastHost(String value) { this.lastHost.setText(value); }
}
