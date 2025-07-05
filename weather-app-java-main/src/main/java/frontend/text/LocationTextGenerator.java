package main.java.frontend.text;

import main.java.backend.model.WeatherConditions;

import javax.swing.*;
import java.awt.*;

public class LocationTextGenerator {
    public static JLabel createDescriptionLabel(String description, WeatherConditions cond)
    {
        description = createBreakPoint(description);

        JLabel locationLabel = new JLabel(description);
        setLabelAttributes(locationLabel, cond);
        return locationLabel;
    }

    private static void setLabelAttributes(JLabel locationLabel, WeatherConditions cond)
    {
        int x_axis = 65;
        int y_axis = (locationLabel.getText().contains("<br>")) ? 58 : 67;
        int width = 400;
        int height = calculateLabelHeight(locationLabel.getText());

        locationLabel.setBounds(x_axis, y_axis, width, height);
        locationLabel.setFont(new Font("Dialog", Font.PLAIN, 18));
        locationLabel.setForeground(
                switch (cond)
                {
                    case THUNDERSTORM, HAIL_THUNDERSTORM, DOWNPOUR -> Color.WHITE;
                    default -> Color.BLACK;
                }
        );
        locationLabel.setHorizontalAlignment(SwingConstants.LEFT);
    }

    private static String createBreakPoint(String description)
    {
        if (description.length() > 35) {
            int i = 35;

            while (i > 0 && description.charAt(i) != ' ') --i;

            if (i > 0) {
                StringBuilder stringBuilder = new StringBuilder(description);
                stringBuilder.setCharAt(i, '\n');
                return "<html>" + stringBuilder.toString().replace("\n", "<br>") + "</html>";
            }
        }

        return "<html>" + description + "</html>";
    }

    private static int calculateLabelHeight(String description) {
        int lines = description.split("<br>").length;
        return 45 + (lines - 1) * 20;
    }
}
