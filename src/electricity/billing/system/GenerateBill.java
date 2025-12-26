package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class GenerateBill extends JFrame implements ActionListener {

    String meter;
    JButton bill;
    Choice cmonth;
    JTextArea area;

    GenerateBill(String meter) {
        this.meter = meter;

        setSize(500, 800);
        setLocation(550, 30);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        JLabel heading = new JLabel("Generate Bill");
        heading.setFont(new Font("Tahoma", Font.BOLD, 18));
        heading.setBounds(180, 20, 200, 30);
        add(heading);

        JLabel lblMeter = new JLabel("Meter No:");
        lblMeter.setBounds(50, 70, 100, 20);
        add(lblMeter);

        JLabel meternumber = new JLabel(meter);
        meternumber.setBounds(150, 70, 150, 20);
        add(meternumber);

        JLabel lblMonth = new JLabel("Select Month:");
        lblMonth.setBounds(50, 100, 100, 20);
        add(lblMonth);

        cmonth = new Choice();
        cmonth.setBounds(150, 100, 150, 20);
        cmonth.add("January");
        cmonth.add("February");
        cmonth.add("March");
        cmonth.add("April");
        cmonth.add("May");
        cmonth.add("June");
        cmonth.add("July");
        cmonth.add("August");
        cmonth.add("September");
        cmonth.add("October");
        cmonth.add("November");
        cmonth.add("December");
        add(cmonth);

        area = new JTextArea();
        area.setEditable(false);
        area.setFont(new Font("SansSerif", Font.ITALIC, 16));
        area.setText("\n\n\t--------Click on the---------\n\t Generate Bill Button to get\n\tthe bill of the Selected Month");

        JScrollPane pane = new JScrollPane(area);
        pane.setBounds(30, 150, 420, 520);
        add(pane);

        bill = new JButton("Generate Bill");
        bill.setBounds(170, 690, 150, 30);
        bill.addActionListener(this);
        add(bill);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        try {
            Conn c = new Conn();
            String month = cmonth.getSelectedItem();

            area.setText("\tBangladesh Power Development Board\nELECTRICITY BILL GENERATED FOR THE MONTH\n\tOF "
                    + month + ", 2022\n\n");

            ResultSet rs = c.s.executeQuery(
                    "select * from customer where meter_no = '" + meter + "'");

            if (rs.next()) {
                area.append("\nCustomer Name   : " + rs.getString("name"));
                area.append("\nMeter Number    : " + rs.getString("meter_no"));
                area.append("\nAddress         : " + rs.getString("address"));
                area.append("\nCity            : " + rs.getString("city"));
                area.append("\nEmail           : " + rs.getString("email"));
                area.append("\nPhone           : " + rs.getString("phone"));
                area.append("\n------------------------------------------\n");
            }

            rs = c.s.executeQuery(
                    "select * from meter_info where meter_no = '" + meter + "'");

            if (rs.next()) {
                area.append("\nMeter Location  : " + rs.getString("meter_location"));
                area.append("\nMeter Type      : " + rs.getString("meter_type"));
                area.append("\nPhase Code      : " + rs.getString("phase_code"));
                area.append("\nBill Type       : " + rs.getString("bill_type"));
                area.append("\nDays            : " + rs.getString("days"));
                area.append("\n------------------------------------------\n");
            }

            rs = c.s.executeQuery("select * from tax");

            if (rs.next()) {
                area.append("\nCost Per Unit       : " + rs.getString("cost_per_unit"));
                area.append("\nMeter Rent          : " + rs.getString("meter_rent"));
                area.append("\nService Charge      : " + rs.getString("service_charge"));
                area.append("\nService Tax         : " + rs.getString("service_tax"));
                area.append("\nSwachh Bharat Cess  : " + rs.getString("swacch_bharat_cess"));
                area.append("\nFixed Tax           : " + rs.getString("fixed_tax"));
                area.append("\n");
            }

            rs = c.s.executeQuery(
                    "select * from bill where meter_no = '" + meter + "' and month='" + month + "'");

            if (rs.next()) {
                area.append("\nCurrent Month   : " + rs.getString("month"));
                area.append("\nUnits Consumed  : " + rs.getString("units"));
                area.append("\nTotal Charges   : " + rs.getString("totalbill"));
                area.append("\n------------------------------------------");
                area.append("\nTotal Payable   : " + rs.getString("totalbill"));
                String status = rs.getString("status");
                if ("Paid".equals(status)) {
                    area.append("\nBill has been already Paid");
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new GenerateBill("");
    }
}

