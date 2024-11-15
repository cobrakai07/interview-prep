import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;

public class MyClass {
  public static long fun(long x, long y) {
    long sq = Math.max(x, y);
    sq = sq * sq;
    if ((Math.max(y, x) % 2) != 0) {
      if (x >= y) {
        long preSq = (Math.max(x, y) - 1) * (Math.max(x, y) - 1);
        return preSq + y;
      } else {
        return sq + 1 - x;
      }
    } else {
      if (x <= y) {
        long preSq = (Math.max(x, y) - 1) * (Math.max(x, y) - 1);
        return preSq + x;
      } else {
        return sq + 1 - y;
      }
    }
  }

  public static void main(String args[]) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter writer = new PrintWriter(System.out);

    int t = Integer.parseInt(reader.readLine().trim());
    while (t-- > 0) {
      String[] inputs = reader.readLine().trim().split(" ");
      long x = Long.parseLong(inputs[0]);
      long y = Long.parseLong(inputs[1]);
      writer.println(fun(x, y));
    }

    writer.flush();
    writer.close();
  }
}
