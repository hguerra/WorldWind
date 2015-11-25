package dataimport.shapefile;

import java.awt.Color;

public class DataColors {
	public static final Color c1 = new Color(0, 100, 1);// Color.decode("#006401"); //-127
	public static final Color c2 = new Color(56, 130, 53);// 388237  //1 
	public static final Color c3 = new Color(113, 159, 113);// 719F71 //10
	public static final Color c4 = new Color(170, 189, 169);// AABDA9
	public static final Color c5 = new Color(213, 206, 196);// D6CDC4
	public static final Color c6 = new Color(225, 187, 140);// E1BB8C
	public static final Color c7 = new Color(236, 168, 83);// ECA853
	public static final Color c8 = new Color(249, 148, 30);// F9941E
	public static final Color c9 = new Color(240, 121, 1);// F07A00 //2
	public static final Color c10 = new Color(212, 84, 0);// D15400 //3
	public static final Color c11 = new Color(178, 48, 0);// B23000
	public static final Color c12 = new Color(147, 9, 0);// 930900
	public static final Color c13 = new Color(161, 51, 50);// A13332
	public static final Color c14 = new Color(193, 119, 120);// C17778
	public static final Color c15 = new Color(223, 187, 187);// DFBBBB
	//Pedro
	public static final Color p1 = new Color(27, 158, 119);
	public static final Color p2 = new Color(217, 95, 2);//
	public static final Color p3 = new Color(117, 112, 179);//
	public static final Color p9 = new Color(231, 41, 138);//
	public static final Color p10 = new Color(102, 166, 30);//
	public static final Color p12 = new Color(230, 171, 2);//
	
		
	public static Color[] getColors() {
		Color[] colors = { p1, p2, p9, p10, p12, p3, Color.black};
		return colors;
	}
	
	public static Color[] getOriginalColors() {
		Color[] colors = { c1, c2, c9, c10, c12, c3, Color.black};
		return colors;
	}
}
