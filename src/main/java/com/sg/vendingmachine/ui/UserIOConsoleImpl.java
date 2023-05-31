package com.sg.vendingmachine.ui;

import java.math.BigDecimal;
import java.util.Scanner;

public class UserIOConsoleImpl implements UserIO {
  final private Scanner console = new Scanner(System.in);

  @Override
  public void print(String msg) {
    System.out.println(msg);
  }

  @Override
  public String readString(String msgPrompt) {
    System.out.println(msgPrompt);
    return console.nextLine();
  }

  @Override
  public BigDecimal readBigDecimal(String msgPrompt) {
    while (true) {
      try {
        return new BigDecimal(msgPrompt);
      } catch (NumberFormatException e) {
        this.print("Input error. Please try again.");
      }
    }
  }


  @Override
  public int readInt(String msgPrompt) {
    boolean invalidInput = true;
    int num = 0;
    while (invalidInput) {
      try {
        // print the message msgPrompt (ex: asking for the # of cats!)
        String stringValue = this.readString(msgPrompt);
        // Get the input line, and try and parse
        num = Integer.parseInt(stringValue); // if it's 'bob' it'll break
        invalidInput = false; // or you can use 'break;'
      } catch (NumberFormatException e) {
        // If it explodes, it'll go here and do this.
        this.print("Input error. Please try again.");
      }
    }
    return num;
  }


  @Override
  public int readInt(String msgPrompt, int min, int max) {
    int result;
    do {
      result = readInt(msgPrompt);
    } while (result < min || result > max);

    return result;
  }


  @Override
  public long readLong(String msgPrompt) {
    while (true) {
      try {
        return Long.parseLong(this.readString(msgPrompt));
      } catch (NumberFormatException e) {
        this.print("Input error. Please try again.");
      }
    }
  }


  @Override
  public long readLong(String msgPrompt, long min, long max) {
    long result;
    do {
      result = readLong(msgPrompt);
    } while (result < min || result > max);

    return result;
  }


  @Override
  public float readFloat(String msgPrompt) {
    while (true) {
      try {
        return Float.parseFloat(this.readString(msgPrompt));
      } catch (NumberFormatException e) {
        this.print("Input error. Please try again.");
      }
    }
  }


  @Override
  public float readFloat(String msgPrompt, float min, float max) {
    float result;
    do {
      result = readFloat(msgPrompt);
    } while (result < min || result > max);

    return result;
  }


  @Override
  public double readDouble(String msgPrompt) {
    while (true) {
      try {
        return Double.parseDouble(this.readString(msgPrompt));
      } catch (NumberFormatException e) {
        this.print("Input error. Please try again.");
      }
    }
  }


  @Override
  public double readDouble(String msgPrompt, double min, double max) {
    double result;
    do {
      result = readDouble(msgPrompt);
    } while (result < min || result > max);
    return result;
  }
}
