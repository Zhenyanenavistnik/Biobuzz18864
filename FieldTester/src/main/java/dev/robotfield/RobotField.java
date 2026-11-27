package dev.robotfield;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

final class RobotField extends JPanel {
    private static final int SIZE = 600;
    private static final int GRID_STEP = 100;
    private static final int MOVE_STEP = 20;
    private static final int ROBOT_RADIUS = 30;

    private static final Color FIELD_COLOR = new Color(30, 120, 30);
    private static final Color GRID_COLOR = new Color(60, 160, 60);
    private static final Color ROBOT_COLOR = new Color(200, 40, 40);

    private double robotX = 200;
    private double robotY = 200;

    RobotField() {
        setPreferredSize(new Dimension(320, 320));
        setFocusable(true);
        getAccessibleContext().setAccessibleName("Поле с роботом");

        bindArrow(KeyEvent.VK_LEFT, -MOVE_STEP, 0);
        bindArrow(KeyEvent.VK_RIGHT, MOVE_STEP, 0);
        bindArrow(KeyEvent.VK_UP, 0, -MOVE_STEP);
        bindArrow(KeyEvent.VK_DOWN, 0, MOVE_STEP);

        MouseAdapter mouse = new MouseAdapter() {
            private int lastX;
            private int lastY;
            private boolean dragging;

            @Override
            public void mousePressed(MouseEvent event) {
                if (SwingUtilities.isLeftMouseButton(event)) {
                    requestFocusInWindow();
                    lastX = event.getX();
                    lastY = event.getY();
                    dragging = true;
                }
            }

            @Override
            public void mouseDragged(MouseEvent event) {
                double scale = fieldScale();
                if (dragging && scale > 0) {
                    moveRobot((event.getX() - lastX) / scale, (event.getY() - lastY) / scale);
                    lastX = event.getX();
                    lastY = event.getY();
                }
            }

            @Override
            public void mouseReleased(MouseEvent event) {
                if (SwingUtilities.isLeftMouseButton(event)) {
                    dragging = false;
                }
            }
        };

        addMouseListener(mouse);
        addMouseMotionListener(mouse);
    }

    private void bindArrow(int key, int dx, int dy) {
        KeyStroke stroke = KeyStroke.getKeyStroke(key, 0);
        getInputMap(JComponent.WHEN_FOCUSED).put(stroke, stroke);
        getActionMap().put(stroke, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent event) {
                moveRobot(dx, dy);
            }
        });
    }

    private void moveRobot(double dx, double dy) {
        robotX = Math.clamp(robotX + dx, ROBOT_RADIUS, SIZE - ROBOT_RADIUS);
        robotY = Math.clamp(robotY + dy, ROBOT_RADIUS, SIZE - ROBOT_RADIUS);
        repaint();
    }

    private double fieldScale() {
        return Math.min(getWidth(), getHeight()) / (double) SIZE;
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        double scale = fieldScale();
        if (scale <= 0) {
            return;
        }

        Graphics2D field = (Graphics2D) graphics.create();
        try {
            field.translate((getWidth() - SIZE * scale) / 2, (getHeight() - SIZE * scale) / 2);
            field.scale(scale, scale);
            field.clipRect(0, 0, SIZE, SIZE);

            field.setColor(FIELD_COLOR);
            field.fillRect(0, 0, SIZE, SIZE);

            field.setColor(GRID_COLOR);
            for (int position = GRID_STEP; position < SIZE; position += GRID_STEP) {
                field.drawLine(position, 0, position, SIZE);
                field.drawLine(0, position, SIZE, position);
            }

            field.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            field.setColor(ROBOT_COLOR);
            field.fill(new Ellipse2D.Double(robotX - ROBOT_RADIUS, robotY - ROBOT_RADIUS,
                    2 * ROBOT_RADIUS, 2 * ROBOT_RADIUS));

            field.setColor(Color.WHITE);
            field.setStroke(new BasicStroke(4));
            field.draw(new Line2D.Double(robotX, robotY, robotX + ROBOT_RADIUS, robotY));
        } finally {
            field.dispose();
        }
    }
}