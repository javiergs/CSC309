import javax.swing.*;
import java.awt.event.*;

public class DrawPanelController implements MouseListener, MouseMotionListener, ActionListener {
    
    private DrawPanel drawPanel;
    private Box clickedBox;
    
    private Box checkCollisionWithBoxes(int x, int y) {
        for(Box c : Blackboard.getInstance().getBoxes())
            if (c.contains(x, y))
                return c;
        return null;
    }
    
    private void showPopUpMenu (int x, int y) {
        JPopupMenu pm = new JPopupMenu();
        JMenuItem l1 = new JMenuItem("delete");
        JMenuItem l2 = new JMenuItem("rename");
        pm.add(l1);
        pm.add(l2);
        l1.addActionListener(this);
        l2.addActionListener(this);
        pm.show(drawPanel, x, y);
    }
    
    private void createNewBox (int x, int y) {
        String name = JOptionPane.showInputDialog("Please input name");
        int i = JOptionPane.showConfirmDialog(null, "Do you want to make this an interface?");
        boolean isInterface = false;
        if(i == 0){
            isInterface = true;
        }
        else if(i == 2){
            return;
        }
        if(name != null){
            Box box = new Box(name, x, y, isInterface);
            Blackboard.getInstance().addBox(box);
        }
    }
    
    public DrawPanelController(DrawPanel drawPanel) {
        this.drawPanel = drawPanel;
    }
    
    public void mousePressed (MouseEvent e) {
        clickedBox = checkCollisionWithBoxes (e.getX(), e.getY());
        if (clickedBox==null) {
            createNewBox(e.getX(), e.getY());
        } else {
            clickedBox.setSelected(true);
            if (SwingUtilities.isRightMouseButton(e)){
                showPopUpMenu (e.getX(), e.getY());
            } else {
                // something will happen here ...
                
            }
        }
    }
    
    public void mouseDragged(MouseEvent e) {
        if(clickedBox != null){
            clickedBox.setPoint(e.getX(), e.getY());
        }
        Blackboard.getInstance().movedBoxStatusBarUpdate();
        drawPanel.repaint();
    }
    
    public void mouseReleased(MouseEvent e) {
        if (clickedBox!=null) {clickedBox.setSelected(false);
        drawPanel.repaint();}
    }
    
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("delete")) {
            Blackboard.getInstance().deleteBox(clickedBox);
            drawPanel.repaint();
        } else if (e.getActionCommand().equals("rename")) {
            Blackboard.getInstance().renameBox(clickedBox);
            drawPanel.repaint();
        }
    }
    
    public void mouseClicked(MouseEvent e) {}
    public void mouseMoved(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    
}
