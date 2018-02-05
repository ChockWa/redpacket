package com.hdh.redpacket.core.utils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.util.Random;

/**
 * 图形成验证生成器,将验传递进来的验证码生成图片
 */
public class CaptchaGenerateor {

	private String code;
	private int fontSize = 18;
	private int topPadding = 5;
	private int rightPadding = 5;
	private int bottomPadding = 5;
	private int leftPadding = 5;
	private int fontSpacing = 0;
	private Color backgroundColor = Color.white;

	public CaptchaGenerateor(String code) {
		this.code = code;
	}

	public CaptchaGenerateor(String code, int fontSize) {
		this.code = code;
		this.fontSize = fontSize;
	}

	public RenderedImage generate() {
		char[] chars = codeToCharArray();
		int charCount = chars.length;
		int width = calcWidth(charCount);// 定义图片的width
		int height = calcHeight();// 定义图片的height

		// 定义图像buffer
		BufferedImage buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics gd = buffImg.getGraphics();

		// 填充背景颜色
		gd.setColor(backgroundColor);
		gd.fillRect(0, 0, width, height);

		drawBorder(width, height, gd); // 画边框
		drawRandomLine(gd, width, height); // 画随机干扰线

		// 画文字
		Font font = new Font("Fixedsys", Font.BOLD, fontSize);
		gd.setFont(font);
		for (int i = 0; i < charCount; i++) {
			int x = leftPadding + i * (fontSpacing + fontSize);
			int y = height - bottomPadding;
			drawOneChar(gd, chars[i], x, y);

		}
		return buffImg;
	}

	private int calcHeight() {
		return topPadding + fontSize + bottomPadding;
	}

	private int calcWidth(int charCount) {
		return leftPadding + charCount * fontSize + (charCount - 1) * fontSpacing + rightPadding;
	}

	private char[] codeToCharArray() {
		return code.toCharArray();
	}

	private void drawOneChar(Graphics gd, char oneChar, int x, int y) {
		Random rnd = new Random();
		int red = rnd.nextInt(150);
		int green = rnd.nextInt(150);
		int blue = rnd.nextInt(150);
		gd.setColor(new Color(red, green, blue));
		String charStr = new String(new char[] { oneChar });
		gd.drawString(charStr, x, y);

	}

	private void drawBorder(int width, int height, Graphics gd) {
		// gd.setColor(Color.BLACK);
		// gd.drawRect(0, 0, width - 1, height - 1);
	}

	private void drawRandomLine(Graphics gd, int width, int height) {
		Random random = new Random();
		gd.setColor(Color.lightGray);
		for (int i = 0; i < 40; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int xl = random.nextInt(12);
			int yl = random.nextInt(12);
			gd.drawLine(x, y, x + xl, y + yl);
		}
	}

	/**
	 * 设置上边距
	 * 
	 * @param topPadding
	 */
	public void setTopPadding(int topPadding) {
		this.topPadding = topPadding;
	}

	/**
	 * 设置右边距
	 * 
	 * @param rightPadding
	 */
	public void setRightPadding(int rightPadding) {
		this.rightPadding = rightPadding;
	}

	/**
	 * 设置下边距
	 * 
	 * @param bottomPadding
	 */
	public void setBottomPadding(int bottomPadding) {
		this.bottomPadding = bottomPadding;
	}

	/**
	 * 设置左边距
	 * 
	 * @param leftPadding
	 */
	public void setLeftPadding(int leftPadding) {
		this.leftPadding = leftPadding;
	}

	/**
	 * 设置文字间距
	 * 
	 * @param fontSpacing
	 */
	public void setFontSpacing(int fontSpacing) {
		this.fontSpacing = fontSpacing;
	}

	/**
	 * 设置文字大小
	 * 
	 * @param fontSize
	 */
	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}

}
