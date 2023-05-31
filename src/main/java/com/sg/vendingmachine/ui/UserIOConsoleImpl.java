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
    while (true) {
      try {
        String stringValue = this.readString(msgPrompt);
        return Integer.parseInt(stringValue);
      } catch (NumberFormatException e) {
        this.print("Invalid input. Please enter a valid integer.");
      }
    }
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
