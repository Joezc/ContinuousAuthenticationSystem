package com.biolab.node.nexTest.Jama;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * A utility class that provides some simple statistical functions.
 */
public abstract class Statistics {

    /**
     * Returns the mean of an array of numbers.
     *
     * @param values  the values (<code>null</code> permitted, returns
     *                <code>Double.NaN</code>).
     *
     * @return The mean.
     */
    public static double calculateMean(Number[] values) {
        double result = Double.NaN;
        if (values != null && values.length > 0) {
            double sum = 0.0;
            int counter = 0;
            for (; counter < values.length; counter++) {
                sum = sum + values[counter].doubleValue();
            }
            result = (sum / counter);
        }
        return result;
    }

    /**
    * Returns the mean of an array of numbers.
    *
    * @param values  the values

    * @return The mean.
       zhangweian   2006/03/08
    */
   public static double calculateMean(double[] values) {
       double result = Double.NaN;
       if (values != null && values.length > 0) {
           double sum = 0.0;
           int counter = 0;
           for (; counter < values.length; counter++) {
               sum = sum + values[counter];
           }
           result = (sum / counter);
       }
       return result;
   }

    /**
     * Returns the mean of a collection of <code>Number</code> objects.
     *
     * @param values  the values (<code>null</code> permitted, returns
     *                <code>Double.NaN</code>).
     *
     * @return The mean.
     */
    public static double calculateMean(Collection values) {

        double result = Double.NaN;
        int count = 0;
        double total = 0.0;
        Iterator iterator = values.iterator();
        while (iterator.hasNext()) {
            Object object = iterator.next();
            if (object != null && object instanceof Number) {
                Number number = (Number) object;
                total = total + number.doubleValue();
                count = count + 1;
            }
        }
        if (count > 0) {
            result = total / count;
        }
        return result;

    }

    /**
     * Calculates the median for a list of values (<code>Number</code> objects).
     * The list of values will be sorted first.
     *
     * @param values  the values.
     *
     * @return The median.
     */
    public static double calculateMedian(List values) {
        return calculateMedian(values, true);
    }

    /**
     * Calculates the median for a list of values (<code>Number</code> objects)
     * that are assumed to be in ascending order.
     *
     * @param values  the values.
     * @param copyAndSort  a flag that controls whether the list of values is
     *                     copied and sorted.
     *
     * @return The median.
     */
    public static double calculateMedian(List values, boolean copyAndSort) {

        double result = Double.NaN;
        if (values != null) {
            if (copyAndSort) {
                int itemCount = values.size();
                List copy = new ArrayList(itemCount);
                for (int i = 0; i < itemCount; i++) {
                    copy.add(i, values.get(i));
                }
                Collections.sort(copy);
                values = copy;
            }
            int count = values.size();
            if (count > 0) {
                if (count % 2 == 1) {
                    if (count > 1) {
                        Number value = (Number) values.get((count - 1) / 2);
                        result = value.doubleValue();
                    }
                    else {
                        Number value = (Number) values.get(0);
                        result = value.doubleValue();
                    }
                }
                else {
                    Number value1 = (Number) values.get(count / 2 - 1);
                    Number value2 = (Number) values.get(count / 2);
                    result = (value1.doubleValue() + value2.doubleValue())
                             / 2.0;
                }
            }
        }
        return result;
    }

    /**
     * Calculates the median for a sublist within a list of values
     * (<code>Number</code> objects).
     *
     * @param values  the values (in any order).
     * @param start  the start index.
     * @param end  the end index.
     *
     * @return The median.
     */
    public static double calculateMedian(List values, int start, int end) {
        return calculateMedian(values, start, end, true);
    }

    /**
     * Calculates the median for a sublist within a list of values
     * (<code>Number</code> objects).  The entire list will be sorted if the
     * <code>ascending</code< argument is <code>false</code>.
     *
     * @param values  the values.
     * @param start  the start index.
     * @param end  the end index.
     * @param copyAndSort  a flag that that controls whether the list of values
     *                     is copied and sorted.
     *
     * @return The median.
     */
    public static double calculateMedian(List values, int start, int end,
                                         boolean copyAndSort) {

        double result = Double.NaN;
        if (copyAndSort) {
            List working = new ArrayList(end - start + 1);
            for (int i = start; i <= end; i++) {
                working.add(values.get(i));
            }
            Collections.sort(working);
            result = calculateMedian(working, false);
        }
        else {
            int count = end - start + 1;
            if (count > 0) {
                if (count % 2 == 1) {
                    if (count > 1) {
                        Number value
                            = (Number) values.get(start + (count - 1) / 2);
                        result = value.doubleValue();
                    }
                    else {
                        Number value = (Number) values.get(start);
                        result = value.doubleValue();
                    }
                }
                else {
                    Number value1 = (Number) values.get(start + count / 2 - 1);
                    Number value2 = (Number) values.get(start + count / 2);
                    result
                        = (value1.doubleValue() + value2.doubleValue()) / 2.0;
                }
            }
        }
        return result;

    }

    /**
     * Returns the standard deviation of a set of numbers.
     *
     * @param data  the data.
     *
     * @return The standard deviation of a set of numbers.
     */
    public static double getStdDev(Number[] data) {
        double avg = calculateMean(data);
        double sum = 0.0;

        for (int counter = 0; counter < data.length; counter++) {
            double diff = data[counter].doubleValue() - avg;
            sum = sum + diff * diff;
        }
        return Math.sqrt(sum / (data.length - 1));
    }


    /**
     * Returns the standard deviation of a set of numbers.
     *
     * @param data  the data.
     *
     * @return The standard deviation of a set of numbers.
         zhangweian  2006/03/08

     */
    public static double getStdDev(double[] data) {
        double avg = calculateMean(data);
        double sum = 0.0;

        for (int counter = 0; counter < data.length; counter++) {
            double diff = data[counter] - avg;
            sum = sum + diff * diff;
        }
        return Math.sqrt(sum / (data.length - 1));
    }

    public static double getStdDev(double[] data, double mean) {
        double sum = 0.0;

        for (int counter = 0; counter < data.length; counter++) {
            double diff = data[counter] - mean;
            sum = sum + diff * diff;
        }
        return Math.sqrt(sum / (data.length - 1));
    }

    /**
     * Fits a straight line to a set of (x, y) data, returning the slope and
     * intercept.
     *
     * @param xData  the x-data.
     * @param yData  the y-data.
     *
     * @return A double array with the intercept in [0] and the slope in [1].
     */
    public static double[] getLinearFit(Number[] xData, Number[] yData) {

        // check arguments...
        if (xData.length != yData.length) {
            throw new IllegalArgumentException(
                "Statistics.getLinearFit(): array lengths must be equal.");
        }

        double[] result = new double[2];
        // slope
        result[1] = getSlope(xData, yData);
        // intercept
        result[0] = calculateMean(yData) - result[1] * calculateMean(xData);

        return result;

    }

    /**
     * Finds the slope of a regression line using least squares.
     *
     * @param xData  an array of Numbers (the x values).
     * @param yData  an array of Numbers (the y values).
     *
     * @return The slope.
     */
    public static double getSlope(Number[] xData, Number[] yData) {

        // check arguments...
        if (xData.length != yData.length) {
            throw new IllegalArgumentException("Array lengths must be equal.");
        }

        // ********* stat function for linear slope ********
        // y = a + bx
        // a = ybar - b * xbar
        //     sum(x * y) - (sum (x) * sum(y)) / n
        // b = ------------------------------------
        //     sum (x^2) - sum(x)^2 / n
        // *************************************************

        // sum of x, x^2, x * y, y
        double sx = 0.0, sxx = 0.0, sxy = 0.0, sy = 0.0;
        int counter;
        for (counter = 0; counter < xData.length; counter++) {
            sx = sx + xData[counter].doubleValue();
            sxx = sxx + Math.pow(xData[counter].doubleValue(), 2);
            sxy = sxy + yData[counter].doubleValue()
                      * xData[counter].doubleValue();
            sy = sy + yData[counter].doubleValue();
        }
        return (sxy - (sx * sy) / counter) / (sxx - (sx * sx) / counter);

    }

    /**
     * Calculates the correlation between two datasets.  Both arrays should
     * contain the same number of items.  Null values are treated as zero.
     * <P>
     * Information about the correlation calculation was obtained from:
     *
     * http://trochim.human.cornell.edu/kb/statcorr.htm
     *
     * @param data1  the first dataset.
     * @param data2  the second dataset.
     *
     * @return The correlation.
     *
     */
    public static double getCorrelation(Number[] data1, Number[] data2) {
        if (data1 == null) {
            throw new IllegalArgumentException("Null 'data1' argument.");
        }
        if (data2 == null) {
            throw new IllegalArgumentException("Null 'data2' argument.");
        }
        if (data1.length != data2.length) {
            throw new IllegalArgumentException(
                "'data1' and 'data2' arrays must have same length."
            );
        }
        int n = data1.length;
        double sumX = 0.0;
        double sumY = 0.0;
        double sumX2 = 0.0;
        double sumY2 = 0.0;
        double sumXY = 0.0;
        for (int i = 0; i < n; i++) {
            double x = 0.0;
            if (data1[i] != null) {
                x = data1[i].doubleValue();
            }
            double y = 0.0;
            if (data2[i] != null) {
                y = data2[i].doubleValue();
            }
            sumX = sumX + x;
            sumY = sumY + y;
            sumXY = sumXY + (x * y);
            sumX2 = sumX2 + (x * x);
            sumY2 = sumY2 + (y * y);
        }
        return (n * sumXY - sumX * sumY) / Math.pow((n * sumX2 - sumX * sumX)
                * (n * sumY2 - sumY * sumY), 0.5);
    }

    /**
     * Calculates the correlation between two datasets.
     *
     *@param data1  the first dataset.
     * @param data2  the second dataset.
     *
     * @return The correlation.
         zhangweian  2006/03/08
     */
    public static double getCorrelation(double[] data1, double[] data2) {
       if (data1 == null) {
           throw new IllegalArgumentException("Null 'data1' argument.");
       }
       if (data2 == null) {
           throw new IllegalArgumentException("Null 'data2' argument.");
       }
       if (data1.length != data2.length) {
           throw new IllegalArgumentException(
               "'data1' and 'data2' arrays must have same length."
           );
       }
       int n = data1.length;
       double sumX = 0.0;
       double sumY = 0.0;
       double sumX2 = 0.0;
       double sumY2 = 0.0;
       double sumXY = 0.0;
       double x = 0.0;
       double y = 0.0;
       for (int i = 0; i < n; i++) {
           x = data1[i];
           y = data2[i];
           sumX = sumX + x;
           sumY = sumY + y;
           sumXY = sumXY + (x * y);
           sumX2 = sumX2 + (x * x);
           sumY2 = sumY2 + (y * y);
       }
       return (n * sumXY - sumX * sumY) / Math.pow((n * sumX2 - sumX * sumX)
               * (n * sumY2 - sumY * sumY), 0.5);
   }


    /**
     * Returns a data set for a moving average on the data set passed in.
     *
     * @param xData  an array of the x data.
     * @param yData  an array of the y data.
     * @param period  the number of data points to average
     *
     * @return A double[][] the length of the data set in the first dimension,
     *         with two doubles for x and y in the second dimension
     */
    public static double[][] getMovingAverage(Number[] xData,
                                              Number[] yData,
                                              int period) {

        // check arguments...
        if (xData.length != yData.length) {
            throw new IllegalArgumentException("Array lengths must be equal.");
        }

        if (period > xData.length) {
            throw new IllegalArgumentException(
                "Period can't be longer than dataset."
            );
        }

        double[][] result = new double[xData.length - period][2];
        for (int i = 0; i < result.length; i++) {
            result[i][0] = xData[i + period].doubleValue();
            // holds the moving average sum
            double sum = 0.0;
            for (int j = 0; j < period; j++) {
                sum += yData[i + j].doubleValue();
            }
            sum = sum / period;
            result[i][1] = sum;
        }
        return result;

    }

    /**
     * Returns the square deviation of a set of numbers.
     *
     * @param data  the data.
     *
     * @return The square deviation of a set of numbers.
     *  zhangweian 2006/03/08
     */
    public static double getSquareDev(double[] data) {
        double avg = calculateMean(data);
        double sum = 0.0;

        for (int counter = 0; counter < data.length; counter++) {
            double diff = data[counter] - avg;
            sum = sum + diff * diff;
        }
        return sum;
    }

}