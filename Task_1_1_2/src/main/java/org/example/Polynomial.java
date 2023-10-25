package org.example;

import java.util.Arrays;

/**
 * This is class that create a polynomial system that can.
 * 1) sum of polynomials.
 * 2) subtract polynomials.
 * 3) multiply polynomials.
 * 4) evaluate polynomials for some X.
 * 5) differentiate polynomials.
 * 6) convert polynomials to string.
 * 7) can check equals polynomials or not.
 */
public class Polynomial {
    private final int[] coefficients;

    /**
     * method for delete last zeros from coefficients array.
     */
    private int[] coefficientsNormalize(int[] arr) {
        int tail = 0;
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] == 0) {
                tail++;
            } else {
                break;
            }
        }
        return Arrays.copyOf(arr, arr.length - tail);
    }

    public int[] getCoefficients() {
        return Arrays.copyOf(coefficients, coefficients.length);
    }

    public Polynomial(int[] newCoefficients) {
        this.coefficients = Arrays.copyOf(newCoefficients, newCoefficients.length);
    }

    /**
     * sum of two polynomials.
     */
    public Polynomial sum(Polynomial sumPolynomial) {
        int max = Math.max(this.coefficients.length, sumPolynomial.coefficients.length);
        int[] result = new int[max];

        for (int i = 0; i < sumPolynomial.coefficients.length; i++) {
            result[i] += sumPolynomial.coefficients[i];
        }
        for (int i = 0; i < this.coefficients.length; i++) {
            result[i] += this.coefficients[i];
        }
        result = coefficientsNormalize(result);
        return new Polynomial(result);
    }

    /**
     * subtract two polynomials.
     */
    public Polynomial sub(Polynomial subPolynomial) {
        int max = Math.max(this.coefficients.length, subPolynomial.coefficients.length);
        int[] result = new int[max];

        for (int i = 0; i < subPolynomial.coefficients.length; i++) {
            result[i] -= subPolynomial.coefficients[i];
        }
        for (int i = 0; i < this.coefficients.length; i++) {
            result[i] += this.coefficients[i];
        }
        result = coefficientsNormalize(result);
        return new Polynomial(result);
    }

    /**
     * multiply two polynomials.
     */
    public Polynomial mul(Polynomial mulPolynomial) {
        int lengthRes = this.coefficients.length + mulPolynomial.coefficients.length;
        int[] result = new int[lengthRes];

        for (int i = 0; i < this.coefficients.length; i++) {
            for (int j = 0; j < mulPolynomial.coefficients.length; j++) {
                result[i + j] += this.coefficients[i] * mulPolynomial.coefficients[j];
            }
        }
        result = coefficientsNormalize(result);
        return new Polynomial(result);
    }

    /**
     * eval with x.
     */
    public int evaluate(int x) {
        int result = this.coefficients[0];
        for (int i = 1; i < this.coefficients.length; i++) {
            result += (int) (Math.round(Math.pow(x, i)) * this.coefficients[i]);
        }
        return result;
    }

    /**
     * differentiate polynomials.
     */
    public Polynomial differentiate(int n) {
        int[] result = Arrays.copyOf(this.coefficients, this.coefficients.length);
        if (n == 0) {
            result = coefficientsNormalize(result);
            return new Polynomial(result);
        }
        if (this.coefficients.length == 0) {
            return new Polynomial(result);
        }
        result[this.coefficients.length - 1] = 0;
        for (int i = 0; i < this.coefficients.length - 1; i++) {
            result[i] = this.coefficients[i + 1] * (i + 1);
        }
        result = coefficientsNormalize(result);
        return new Polynomial(result).differentiate(n - 1);
    }

    /**
     * differentiate polynomials.
     */
    @Override
    public String toString() {
        var coefficientsNormalized = coefficientsNormalize(this.coefficients);
        var result = new StringBuilder();
        boolean first = Boolean.TRUE;
        for (int i = coefficientsNormalized.length - 1; i >= 0; i--) {
            if (coefficientsNormalized[i] != 0) {
                if (first) {
                    first = Boolean.FALSE;
                } else {
                    if (coefficientsNormalized[i] > 0) {
                        result.append(" + ");
                    } else {
                        result.append(" - ");
                    }
                }

                if (coefficientsNormalized[i] != 1) {
                    result.append(Math.abs(coefficientsNormalized[i]));
                } else {
                    if (i == 0) {
                        result.append("1");
                    }
                }
                if (i > 1) {
                    result.append("x^");
                    result.append(i);
                }
                if (i == 1) {
                    result.append("x");
                }
            } else {
                if (i == 0) {
                    result.append(" + 0");
                }
            }
        }
        if (coefficientsNormalized.length == 0) {
            result.append("0");
        }
        return result.toString();
    }

    /**
     * differentiate polynomials.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        var checkPoly = (Polynomial) obj;

        int[] arr1 = coefficientsNormalize(checkPoly.coefficients);
        int[] arr2 = coefficientsNormalize(this.coefficients);

        if (Arrays.equals(arr1, arr2)) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }

    }

}
