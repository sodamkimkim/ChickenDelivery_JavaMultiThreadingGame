package ver1;

public interface Moveable {
	void left();
	void right();
	
	default public void up() {};
	default public void down() {};
	
	default public void jumpUpInKit() {};
	default public void jumpDownInKit() {};
	
	default public void jumpUpInDel() {};
	default public void jumpDownInDel() {};
}