package entities;

public class IDGetter {

	public static int ID = 0;
	public static int getID() {
		ID++;
		return ID;
	}
}