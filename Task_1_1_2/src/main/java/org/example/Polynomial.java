package org.example;

import java.util.Arrays;

public class Polynomial
{
    private final int[] coefficients;

    private int[] coefficientsNormalize(int[] arr)
    {
        int tail = 0;
        for (int i = arr.length - 1; i >= 0; i--)
        {
            if (arr[i] == 0)
            {
                tail++;
            }
            else
            {
                break;
            }
        }
        return Arrays.copyOf(arr, arr.length - tail);
    }

    public int[] getCoefficients() {
        return Arrays.copyOf(coefficients, coefficients.length);
    }

    public Polynomial(int[] new_coefficients)
    {
        this.coefficients = Arrays.copyOf(new_coefficients, new_coefficients.length);
    }

    public Polynomial sum(Polynomial sum_polynomial) // sum of two polynomials
    {
        int max = Math.max(this.coefficients.length, sum_polynomial.coefficients.length);
        int[] result = new int[max];

        for (int i = 0; i < sum_polynomial.coefficients.length; i ++)
        {
            result[i] += sum_polynomial.coefficients[i];
        }
        for (int i = 0; i < this.coefficients.length; i ++)
        {
            result[i] += this.coefficients[i];
        }
        result = coefficientsNormalize(result);
        return new Polynomial(result);
    }

    public Polynomial sub(Polynomial sub_polynomial) // subtrahend two polynomials
    {
        int max = Math.max(this.coefficients.length, sub_polynomial.coefficients.length);
        int[] result = new int[max];

        for (int i = 0; i < sub_polynomial.coefficients.length; i ++)
        {
            result[i] -= sub_polynomial.coefficients[i];
        }
        for (int i = 0; i < this.coefficients.length; i ++)
        {
            result[i] += this.coefficients[i];
        }
        result = coefficientsNormalize(result);
        return new Polynomial(result);
    }

    public Polynomial mul(Polynomial mul_polynomial) // multiply two polynomials
    {
        int length_res = this.coefficients.length + mul_polynomial.coefficients.length - 1;
        int[] result = new int[length_res];

        for (int i = 0; i < this.coefficients.length; i ++)
        {
            for (int j = 0; j < mul_polynomial.coefficients.length; j ++)
            {
                result[i+j] += this.coefficients[i] * mul_polynomial.coefficients[j];
            }
        }
        result = coefficientsNormalize(result);
        return new Polynomial(result);
    }

    public int evaluate(int x)
    {
        int result = this.coefficients[0];
        for (int i = 1; i < this.coefficients.length; i++)
        {
            result += (int) (Math.round(Math.pow(x, i)) * this.coefficients[i]);
        }
        return result;
    }

    public Polynomial differentiate(int n)
    {
        int[] result = Arrays.copyOf(this.coefficients, this.coefficients.length);
        if (n == 0)
        {
            result = coefficientsNormalize(result);
            return new Polynomial(result);
        }
        result[this.coefficients.length - 1] = 0;
        for (int i = 0; i < this.coefficients.length - 1; i++)
        {
            result[i] = this.coefficients[i+1] * (i+1);
        }
        result = coefficientsNormalize(result);
        return new Polynomial(result).differentiate(n-1);
    }

    @Override
    public String toString()
    {
        var coeffs = coefficientsNormalize(this.coefficients);
        var result = "";
        boolean first = Boolean.TRUE;
        for (int i = coeffs.length - 1; i >= 0; i--)
        {
            if (coeffs[i] != 0)
            {
                if (first)
                {
                    first = Boolean.FALSE;
                }
                else
                {
                    if (coeffs[i] > 0)
                    {
                        result = result.concat(" + ");
                    }
                    else
                    {
                        result = result.concat(" - ");
                    }
                }

                if (coeffs[i] != 1)
                {
                    result = result.concat("" + Math.abs(coeffs[i]));
                }
                else
                {
                    if (i == 0)
                    {
                        result = result.concat("1");
                    }
                }
                if (i > 1)
                {
                    result = result.concat("x^" + i);
                }
                if (i == 1)
                {
                    result = result.concat("x");
                }
            }
            else {
                if (i == 0)
                {
                    result = result.concat(" + 0");
                }
            }
        }
        if (coeffs.length == 0)
        {
            result = "0";
        }
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        var check_poly = (Polynomial) obj;

        int[] arr1 = coefficientsNormalize(check_poly.coefficients);
        int[] arr2 = coefficientsNormalize(this.coefficients);

        if(Arrays.equals(arr1, arr2))
        {
            return Boolean.TRUE;
        }
        else
        {
            return Boolean.FALSE;
        }

    }

}
