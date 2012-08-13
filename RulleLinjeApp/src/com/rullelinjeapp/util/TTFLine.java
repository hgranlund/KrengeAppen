package com.rullelinjeapp.util;

import android.graphics.Color;
import java.util.ArrayList;

import android.graphics.Bitmap;
import android.widget.Toast;

public class TTFLine {

	// Bitmap bitmap = BitmapFactory.decodeFile(filename)

	public double findAngle(Bitmap img) {
		LineDetector lineDetector = new LineDetector();

		int w = img.getWidth();
		int h = img.getHeight();

		int[][] pixels = new int[w][h];

		for (int i = 0; i < w; i++)
			for (int j = 0; j < h; j++)
				pixels[i][j] = img.getPixel(i, j);

		int[][] filteredPixels = filter(pixels);

		w = filteredPixels.length;
		h = filteredPixels[0].length;

		ArrayList<int[]> lines = lineDetector.getCandidateLines(filteredPixels,
				70);
		lines = lineDetector.reduceCandidates(lines);
		
		
		return lines.size()<2 ? 999:calcAngle(lines.get(0), lines.get(1));
	}

	public double calcAngle(int[] line1, int[] line2) {
		double angle1 = calcLineAngle(line1[0], line1[1], line1[2], line1[3]);
		double angle2 = calcLineAngle(line2[0], line2[1], line2[2], line2[3]);
		return Math.abs(angle2 - angle1);
	}

	public double calcLineAngle(int x1, int y1, int x2, int y2) {
		int dx = x2 - x1;
		int dy = y2 - y1;
		double angle = Math.atan((double) (dy) / (double) (dx));
		return angle;
	}

	public int[][] filter(int[][] img) {

		int step = 4;

		int w = img.length / step;
		int h = img[0].length / step;

		int[][] filteredImg = new int[w][h];

		for (int i = 0; i < w; i++) {
			for (int j = 0; j < h; j++) {
				int[][] section = {
						{ img[i * step][j * step],
								img[i * step][(j * step) + 1] },
						{ img[(i * step) + 1][j * step],
								img[(i * step) + 1][(j * step) + 1] } };
				filteredImg[i][j] = compressBool(section);
			}
		}

		return filteredImg;
	}

	public int compress(int[][] section) {
		int threshold = 1000;
		int delta = max(section) - min(section);
		if (delta < threshold) {
			return Color.BLACK;
		}
		return Color.WHITE;
	}

	public int compressBool(int[][] section) {
		int threshold = 1000;
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

}
