package tool;

import java.util.Scanner;

public class Tool {

    private static Scanner sc = new Scanner(System.in);

    //Nhập vào một số nguyên kiển int
    public static int getAnInteger(String inforMessage, String errorMessage) {
        int n;
        while (true) {
            try {
                System.out.printf(inforMessage);
                n = Integer.parseInt(sc.nextLine());
                return n;
            } catch (Exception e) {
                System.out.println(errorMessage);
            }
        }
    }

    //Hàm lấy một số nguyên nằm trong khoảng nhất định
    public static int getAnInteger(String inforMessage, String errorMessage, int lowerBound, int upperBound) {
        //đổi chỗ upper và lower nếu upper < lower
        int n;
        if (upperBound < lowerBound) {
            int tmp = upperBound;
            upperBound = lowerBound;
            lowerBound = tmp;
        }

        while (true) {
            try {
                System.out.printf(inforMessage);
                n = Integer.parseInt(sc.nextLine());
                if (n < lowerBound || n > upperBound) {
                    throw new Exception();
                } else {
                    return n;
                }
            } catch (Exception e) {
                System.out.println(errorMessage);
            }
        }

    }

    //Nhập vào một chuỗi String khác rỗng
    public static String getString(String informessage, String errorMessage) {
        String str;
        while (true) {
            System.out.print(informessage);
            str = sc.nextLine();
            if (str.trim().isEmpty()) {
                System.out.println(errorMessage);
            } else {
                return str;
            }
        }
    }

}
