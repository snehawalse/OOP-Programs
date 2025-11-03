import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.util.Stack;

public class PolygonFillDemo extends JPanel implements ActionListener {
    int[] xPoints = {150, 250, 300, 200, 100};
    int[] yPoints = {200, 100, 200, 300, 300};
    int n = 5;
    String mode = "";
    BufferedImage canvas;

    public PolygonFillDemo() {
        JFrame frame = new JFrame("Polygon Fill Algorithms");
        JButton scanFillBtn = new JButton("Scan Fill");
        JButton floodFillBtn = new JButton("Flood Fill");
        JButton seedFillBtn = new JButton("Seed Fill");

        scanFillBtn.addActionListener(this);
        floodFillBtn.addActionListener(this);
        seedFillBtn.addActionListener(this);

        JPanel btnPanel = new JPanel();
        btnPanel.add(scanFillBtn);
        btnPanel.add(floodFillBtn);
        btnPanel.add(seedFillBtn);

        frame.add(this, BorderLayout.CENTER);
        frame.add(btnPanel, BorderLayout.SOUTH);
        frame.setSize(600, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (canvas == null) {
            // Initialize blank image
            canvas = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
            Graphics2D g2 = canvas.createGraphics();
            g2.setColor(Color.WHITE);
            g2.fillRect(0, 0, getWidth(), getHeight());
            g2.setColor(Color.BLACK);
            g2.drawPolygon(xPoints, yPoints, n);
            g2.dispose();
        }

        g.drawImage(canvas, 0, 0, null);

        if (mode.equals("SCAN")) {
            Graphics2D g2 = canvas.createGraphics();
            scanFill(g2, Color.CYAN);
            g2.dispose();
            g.drawImage(canvas, 0, 0, null);
        } else if (mode.equals("FLOOD")) {
            floodFill(200, 200, Color.WHITE, Color.YELLOW);
            g.drawImage(canvas, 0, 0, null);
        } else if (mode.equals("SEED")) {
            seedFill(200, 200, Color.BLACK, Color.PINK);
            g.drawImage(canvas, 0, 0, null);
        }
    }

    // ----------- SCAN FILL -----------
    void scanFill(Graphics g, Color c) {
        for (int y = 100; y <= 300; y++) {
            int[] nodeX = new int[n];
            int intersections = 0;
            int j = n - 1;
            for (int i = 0; i < n; i++) {
                if ((yPoints[i] < y && yPoints[j] >= y) || (yPoints[j] < y && yPoints[i] >= y)) {
                    nodeX[intersections++] = (int) (xPoints[i] + (float)(y - yPoints[i]) /
                            (yPoints[j] - yPoints[i]) * (xPoints[j] - xPoints[i]));
                }
                j = i;
            }
            java.util.Arrays.sort(nodeX, 0, intersections);
            g.setColor(c);
            for (int i = 0; i < intersections - 1; i += 2) {
                g.drawLine(nodeX[i], y, nodeX[i + 1], y);
            }
        }
    }

    // ----------- FLOOD FILL -----------
    void floodFill(int x, int y, Color targetColor, Color fillColor) {
        int targetRGB = targetColor.getRGB();
        int fillRGB = fillColor.getRGB();

        if (x < 0 || y < 0 || x >= canvas.getWidth() || y >= canvas.getHeight()) return;
        if (canvas.getRGB(x, y) != targetRGB) return;

        Stack<Point> stack = new Stack<>();
        stack.push(new Point(x, y));

        while (!stack.isEmpty()) {
            Point p = stack.pop();
            if (p.x < 0 || p.y < 0 || p.x >= canvas.getWidth() || p.y >= canvas.getHeight()) continue;
            if (canvas.getRGB(p.x, p.y) == targetRGB) {
                canvas.setRGB(p.x, p.y, fillRGB);
                stack.push(new Point(p.x + 1, p.y));
                stack.push(new Point(p.x - 1, p.y));
                stack.push(new Point(p.x, p.y + 1));
                stack.push(new Point(p.x, p.y - 1));
            }
        }
        repaint();
    }

    // ----------- SEED FILL -----------
    void seedFill(int x, int y, Color boundaryColor, Color fillColor) {
        int boundaryRGB = boundaryColor.getRGB();
        int fillRGB = fillColor.getRGB();
        Stack<Point> stack = new Stack<>();
        stack.push(new Point(x, y));

        while (!stack.isEmpty()) {
            Point p = stack.pop();
            if (p.x < 0 || p.y < 0 || p.x >= canvas.getWidth() || p.y >= canvas.getHeight()) continue;

            int current = canvas.getRGB(p.x, p.y);
            if (current != boundaryRGB && current != fillRGB) {
                canvas.setRGB(p.x, p.y, fillRGB);
                stack.push(new Point(p.x + 1, p.y));
                stack.push(new Point(p.x - 1, p.y));
                stack.push(new Point(p.x, p.y + 1));
                stack.push(new Point(p.x, p.y - 1));
            }
        }
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        mode = switch (cmd) {
            case "Scan Fill" -> "SCAN";
            case "Flood Fill" -> "FLOOD";
            case "Seed Fill" -> "SEED";
            default -> "";
        };

        // Reset canvas each time for clarity
        canvas = null;
        repaint();
    }

    public static void main(String[] args) {
        new PolygonFillDemo();
    }
}
