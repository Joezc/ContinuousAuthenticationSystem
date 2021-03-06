
//Below is the syntax highlighted version of FFT.java from ��9.7 Data Analysis.

/*************************************************************************
 *  Compilation:  javac FFT.java
 *  Execution:    java FFT N
 *  Dependencies: Complex.java
 *
 *  Compute the FFT and inverse FFT of a length N complex sequence.
 *  Bare bones implementation that runs in O(N log N) time.
 *
 *  Limitations
 *  -----------
 *   * assumes N is a power of 2
 *   * not the most memory efficient algorithm
 *
 *************************************************************************/
package com.sensor.genkun.Jama;
//import com.biolab.node.nexTest.Jama.*;

public class FFT {

    // compute the FFT of x[], assuming its length is a power of 2
    public static Complex[] fft(Complex[] x) {

        int N = x.length;
        Complex[] y = new Complex[N];

        // base case
        if (N == 1) {
            y[0] = x[0];
            return y;
        }

        // radix 2 Cooley-Tukey FFT
        if (N % 2 != 0) {
            throw new RuntimeException("N is not a power of 2");
        }

        Complex[] even = new Complex[N / 2];
        Complex[] odd  = new Complex[N / 2];

        for (int k = 0; k < N / 2; ++k) {
            even[k] = x[2 * k];
        }
        for (int k = 0; k < N / 2; ++k) {
            odd[k]  = x[2 * k + 1];
        }

        Complex[] q = fft(even);
        Complex[] r = fft(odd);

        for (int k = 0; k < N / 2; ++k) {
            double kth = -2 * k * Math.PI / N;
            Complex wk = new Complex(Math.cos(kth), Math.sin(kth));
            y[k]       = q[k].plus(wk.times(r[k]));
            y[k + N / 2] = q[k].minus(wk.times(r[k]));
        }
        return y;
    }


    // compute the inverse FFT of x[], assuming its length is a power of 2
    public static Complex[] ifft(Complex[] x) {
        int N = x.length;
        Complex[] y = new Complex[N];

        // take conjugate
        for (int i = 0; i < N; i++)
            y[i] = x[i].conjugate();

        // compute forward FFT
        y = fft(y);

        // take conjugate again
        for (int i = 0; i < N; i++)
            y[i] = y[i].conjugate();

        // divide by N
        for (int i = 0; i < N; i++)
            // y[i] = y[i].times(1.0 / N);
            // modified by zhangweian ,2006/03/02,  may be some problem
             y[i] = y[i].times(new Complex(1.0/N, 0));
        return y;

    }

    // compute the circular convolution of x and y
    public static Complex[] cconvolve(Complex[] x, Complex[] y) {

        // should probably pad x and y with 0s so that they have same length
        // and are powers of 2
        if (x.length != y.length) throw new RuntimeException("Dimensions don't agree");

        int N = x.length;

        // compute FFT of each sequence
        Complex[] a = fft(x);
        Complex[] b = fft(y);

        // point-wise multiply
        Complex[] c = new Complex[N];
        for (int i = 0; i < N; i++)
            c[i] = a[i].times(b[i]);

        // compute inverse FFT
        return ifft(c);
    }


    // compute the linear convolution of x and y
    public static Complex[] convolve(Complex[] x, Complex[] y) {
        Complex ZERO = new Complex(0, 0);

        Complex[] a = new Complex[2*x.length];
        for (int i = 0; i < x.length; i++) a[i] = x[i];
        for (int i = x.length; i < 2*x.length; i++) a[i] = ZERO;

        Complex[] b = new Complex[2*y.length];
        for (int i = 0; i < y.length; i++) b[i] = y[i];
        for (int i = y.length; i < 2*y.length; i++) b[i] = ZERO;

        return cconvolve(a, b);
    }

}

