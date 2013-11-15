package com.krengeappen.util;

public class FourFilter {

	public int[][] filter(int[][] img) {

		int step = 4;

		int w = img.length / step;
		int h = img[0].length / step;

		int[][] filteredImg = new int[w][h];

		for (int i = 0; i < w; i++) {
			for (int j = 0; j < h; j++) {
				int sx = i * step;
				int sy = j * step;
				int[][] section = {{ img[sx][sy], img[sx][sy + 1] },
									{ img[sx + 1][sy], img[sx + 1][sy + 1] }};
				filteredImg[i][j] = compressBool(section);
			}
		}

		return filteredImg;
	}
	
	public int[][] filterHard(int[][] img) {

		int step = 8;

		int w = img.length / step;
		int h = img[0].length / step;

		int[][] filteredImg = new int[w][h];

		for (int i = 0; i < w; i++) {
			for (int j = 0; j < h; j++) {
				int sx = i * step;
				int sy = j * step;
				int ul = meanFour(img[sx][sy], img[sx][sy+1], img[sx+1][sy], img[sx+1][sy+1]);
				int ur = meanFour(img[sx][sy+2], img[sx][sy+3], img[sx+1][sy+2], img[sx+1][sy+3]);
				int ll = meanFour(img[sx+2][sy], img[sx+2][sy+1], img[sx+3][sy], img[sx+3][sy+1]);
				int lr = meanFour(img[sx+2][sy+2], img[sx+2][sy+3], img[sx+3][sy+2], img[sx+3][sy+3]);
				int[][] section = {{ul, ur},
									{ll, lr}};
				filteredImg[i][j] = compressBool(section);
			}
		}

		return filteredImg;
	}

	public int compressBool(int[][] section) {
		int threshold = 15;
		int delta = max(section) - min(section);
		if (delta < threshold) {
			return 0;
		}
		return 1;
	}

	public int min(int[][] section) {
		int min = section[0][0];
		for (int i = 0; i < section.length; i++) {
			for (int j = 0; j < section[0].length; j++) {
				if (section[i][j] < min) {
					min = section[i][j];
				}
			}
		}
		return min;
	}

	public int max(int[][] section) {
		int max = section[0][0];
		for (int i = 0; i < section.length; i++) {
			for (int j = 0; j < section[0].length; j++) {
				if (section[i][j] > max) {
					max = section[i][j];
				}
			}
		}
		return max;
	}
	
	public int meanFour(int a, int b, int c, int d) {
		return (int)((a + b + c + d)/4.0);
	}

}
