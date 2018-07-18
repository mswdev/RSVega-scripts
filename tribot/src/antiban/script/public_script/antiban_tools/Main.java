package antiban.script.public_script.antiban_tools;

import org.tribot.api.General;
import org.tribot.api.input.Mouse;
import org.tribot.script.ScriptManifest;
import org.tribot.script.interfaces.Painting;
import antiban.api.AntibanScript;
import antiban.api.gui.GUI;
import antiban.priority_framework.PriorityEvent;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

@ScriptManifest(authors = "Antiban", category = "Tools", name = "Antiban Tools")
public class Main extends AntibanScript implements MouseListener, Painting {

    private int x;
    private int y;
    private int width;
    private int height;

    private Rectangle rectangle_creator;

    @Override
    protected GUI getGUI() {
        return null;
    }

    @Override
    public void run() {
        this.setLoginBotState(false);
        super.run();
    }

    @Override
    public void addEvents(PriorityEvent... events) {
        super.addEvents(events);
    }

    @Override
    public void onPaint(Graphics graphics) {
        graphics.drawRect((int)rectangle_creator.getX(), (int)rectangle_creator.getY(), (int)rectangle_creator.getWidth(), (int)rectangle_creator.getHeight());
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if (e.getButton() == 3) {
            if (x <= 0 || y <= 0) {
                General.println("Set x,y");
                x = Mouse.getPos().x;
                y = Mouse.getPos().y;
            } else if (width <= 0 || height <= 0) {
                General.println("Set width,height");
                width = Mouse.getPos().x - x;
                height = Mouse.getPos().y - y;
                General.println("-----------------------------------");
                rectangle_creator = new Rectangle(x, y, width, height);
                General.println(x + ", " + y + ", " + width + ", " + height);
            }
        }

        if (e.getButton() == 2) {
            General.println("RESET");
            x = 0;
            y = 0;
            width = 0;
            height = 0;
        }


    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
