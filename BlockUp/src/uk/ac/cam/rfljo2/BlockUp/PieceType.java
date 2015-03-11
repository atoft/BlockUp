package uk.ac.cam.rfljo2.BlockUp;

import java.awt.Color;

public class PieceType {

	public static final byte OUT_OF_BOUNDS = -1;
	public static final byte EMPTY = 0;
	public static final byte CYAN = 1;
	public static final byte BLUE = 2;
	public static final byte ORANGE = 3;
	public static final byte YELLOW = 4;
	public static final byte GREEN = 5;
	public static final byte MAGENTA = 6;
	public static final byte DOUBLE_SCORE = 7;
	
	/**
	 * Statement which converts a byte to the correct color class
	 * @param b
	 * @return
	 */
	public static Color getColor(byte b) {
		switch(b) {
			case EMPTY:
				return Color.BLACK;
			case CYAN:
				return Color.CYAN;
			case BLUE:
				return Color.BLUE;
			case ORANGE:
				return Color.ORANGE;
			case YELLOW:
				return Color.YELLOW;
			case GREEN:
				return Color.GREEN;
			case MAGENTA:
				return Color.MAGENTA;
			case DOUBLE_SCORE:
				return Color.PINK;
		}
		return null;
	}
}
