import java.awt.*;
import java.util.ArrayList;

public class Box {
	protected String name;
	
	protected int type;
	private static final int TYPE_CLASS = 0;
	private static final int TYPE_INTERFACE = 1;
	
	private Point point;
	private int height, width;
	private ArrayList<Connections> connections;
	private ArrayList<String> methods;
	private ArrayList<String> variables;
	private boolean isSelected;
	private boolean isInterface;
	

	/**
	 * getter for selection status
	 * 
	 * @return 
	 */
	public boolean isSelected() {
		return isSelected;
	}
	
	/**
	 * setter for selection status
	 * 
	 * @param selected 
	 */
	public void setSelected(boolean selected) {
		isSelected = selected;
	}
	
	/**
	 * Checks if the passed coordinates are within the
	 * boundries of the Box
	 * 
	 * @param x int x axis
	 * @param y int y axis
	 * 
	 * @return if the passed coordinates are within the
	 * boundries of the Box
	 */
	public boolean contains(int x, int y) {
		if (x > point.getX() && x < point.getX() + width &&
			y > point.getY() && y < point.getY() + height)
			return true;
		return false;
	}
	
	/**
	 * Initializes the Box instance with a name and coordinates
	 * 
	 * @param name String
	 * @param x int
	 * @param y int
   * @param isInterface
	 */
	public Box(String name, int x, int y, boolean isInterface) {

		x = (x < 50) ? 50 : x - 50;
		y = (y < 25) ? 25 : y - 25;
		this.name = name;
		this.point = new Point(x, y);
		this.height = 50;
		this.width = 95 + 7*(name.length());
		this.variables = new ArrayList<>();
		this.methods = new ArrayList<>();
		this.connections = new ArrayList<>();
		this.isInterface = isInterface;
	}

	public boolean getInterface(){
		return isInterface;
	}
	
	/**
	 * Draws the box on the passed Graphics panel
	 * 
	 * @param g The Graphics panel 
	 */
	public void draw(Graphics g) {
		g.setColor(Color.GRAY);
		g.drawRect((int) getPoint().getX(), (int) getPoint().getY(), getWidth(), height);
		if (!isSelected) {
			g.setColor(Color.CYAN);
		} else {
			g.setColor(Color.WHITE);
		}
		g.fillRect((int) getPoint().getX(), (int) getPoint().getY(), getWidth(), height);
		g.setColor(Color.BLACK);
		int w = g.getFontMetrics().stringWidth(getName());
		int xx = (int) getPoint().getX() + (getWidth() / 2) - w / 2;
		int yy = (int) (getPoint().getY() + 20);
		if(isInterface){
			g.drawString("<<interface>>", (int) getPoint().getX() + (getWidth() / 2) - 50, yy - 10);
		}
		g.drawString(getName(), xx, yy);
	}
	
	/**
	 * Getter for box name
	 * 
	 * @return String box name
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Setter for box name
	 * 
	 * @param name
	 */
	public void setName(String name) {
		Blackboard.getInstance().renameBoxStatusBarUpdate();
		this.name = name;
	}
	
	/**
	 * getter for the coordinates
	 * 
	 * @return
	 */
	public Point getPoint() {
		return point;
	}
	
	/**
	 * Setter for the coordinates
	 * 
	 * @param x
	 * @param y
	 */
	public void setPoint(int x, int y) {
		point = new Point(x - 50, y - 25);
	}
	
	/**
	 * Getter for the width of the box
	 * 
	 * @return
	 */
	public int getWidth() {
		return width;
	}
	
	/**
	 * Getter for the box connections
	 * 
	 * @return ArrayList of box connections
	 */
	public ArrayList<Connections> getConnections() {
		return connections;
	}
	
	/**
	 * TODO
	 */
	public void resize() {
	}
}
