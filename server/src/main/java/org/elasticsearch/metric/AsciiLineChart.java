/*
 * Copyright Elasticsearch B.V. and/or licensed to Elasticsearch B.V. under one
 * or more contributor license agreements. Licensed under the Elastic License
 * 2.0 and the Server Side Public License, v 1; you may not use this file except
 * in compliance with, at your election, the Elastic License 2.0 or the Server
 * Side Public License, v 1.
 */

package org.elasticsearch.metric;

public class AsciiLineChart {
    public static void main(String[] args) {
        double[] sers = {9, 100, 50, 1000, 200, 70, 500, 800, 100, 100};
        drawAsciiLineChart(sers);
    }

    private static void drawAsciiLineChart(double[] data) {
        int chartHeight = 20; // 图表高度（Y轴）
        int chartWidth = data.length; // 图表宽度（X轴）

        double minValue = getMinValue(data);
        double maxValue = getMaxValue(data);
        double range = maxValue - minValue;

        // 动态计算每个Y坐标对应的值
        double step = range / (chartHeight - 1);

        // 绘制图表
        for (int i = chartHeight - 1; i >= 0; i--) {
            double yValue = minValue + i * step;
            System.out.printf("%8.2f |", yValue); // Y轴刻度

            for (double point : data) {
                if (point >= yValue && point < yValue + step) {
                    System.out.print(" * "); // 在此刻度范围内的点
                } else {
                    System.out.print("   "); // 空格
                }
            }
            System.out.println();
        }

        // 绘制X轴
        System.out.print("         ");
        for (int i = 0; i < chartWidth; i++) {
            System.out.print("---");
        }
        System.out.println();

        // 绘制X轴数据索引
        System.out.print("         ");
        for (int i = 0; i < chartWidth; i++) {
            System.out.printf("%2d ", i);
        }
        System.out.println();
    }

    private static double getMinValue(double[] data) {
        double min = Double.MAX_VALUE;
        for (double v : data) {
            if (v < min) {
                min = v;
            }
        }
        return min;
    }

    private static double getMaxValue(double[] data) {
        double max = Double.MIN_VALUE;
        for (double v : data) {
            if (v > max) {
                max = v;
            }
        }
        return max;
    }
}

