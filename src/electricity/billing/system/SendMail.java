package electricity.billing.system;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class SendMail {

    SendMail(String customerEmail, String customerName,
             String meterNo, String month, double amount) {

        try {
            send(customerEmail, customerName, meterNo, month, amount);
        } catch (Exception e) {
            System.out.println("Mail could not be sent, but payment is successful.");
        }
    }

    private void send(String customerEmail, String customerName,
                      String meterNo, String month, double amount) throws Exception {

        String from = "your@gmail.com";
        String password = "your app password";

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props,
            new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(from, password);
                }
            });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        message.setRecipients(
            Message.RecipientType.TO,
            InternetAddress.parse(customerEmail)
        );

        message.setSubject("Electricity Bill Payment Successful");

        message.setText(
            "Dear " + customerName + ",\n\n" +
            "Your electricity bill has been paid successfully.\n\n" +
            "Meter No: " + meterNo + "\n" +
            "Month: " + month + "\n" +
            "Amount Paid: Tk " + amount + "\n\n" +
            "Thank you.\nBangladesh Power Development Board"
        );

        Transport.send(message);
    }
}


