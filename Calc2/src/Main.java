import java.util.Arrays;
import java.util.Scanner;
public class Main {
    public static void main(String[] args)throws MyException
    {
        while (true)
        {
            Scanner scan = new Scanner(System.in);

            String input = scan.nextLine();
            input = input.toUpperCase();

            String output = calc(input);

            System.out.println(output);
        }
    }
    public static String calc(String input) throws MyException
    {
        String[] splitString = input.split(" ");
        String result;

        int firstValue = 0;
        int secondValue = 0;

        boolean isRoman = false;

        if (splitString.length > 3)
        {
            throw new MyException("Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }
        else if (splitString.length < 3)
        {
            throw new MyException("Cтрока не является математической операцией");
        }

        if (checkIsRoman(splitString[0]) != checkIsRoman(splitString[2]))
        {
            throw new MyException("Используются одновременно разные системы счисления");
        }
        else if (checkIsRoman(splitString[0]) == true)
        {
            isRoman = true;

            firstValue = romanToArab(splitString[0]);
            secondValue = romanToArab(splitString[2]);
        }
        else
        {
            firstValue = Integer.valueOf(splitString[0]);
            secondValue = Integer.valueOf(splitString[2]);
        }

        if (firstValue > 10 || secondValue > 10)
        {
            throw new MyException("Калькулятор не может работать с числами больше 10");
        }

        switch (splitString[1])
        {
            case "+":
                result = String.valueOf(firstValue + secondValue);
                break;
            case "-":
                result = String.valueOf(firstValue - secondValue);
                break;
            case "*":
                result = String.valueOf(firstValue * secondValue);
                break;
            case "/":
                result = String.valueOf(firstValue / secondValue);
                break;
            default:
                result =  "строка не является математической операцией";
        }

        if (isRoman && Integer.valueOf(result) < 1)
        {
            throw new MyException("в римской системе нет отрицательных чисел и 0");
        }
        else if (isRoman)
        {
            result = arabToRoman(result);
        }

        return result;
    }
    public static int romanToArab(String s)
    {
        String[] romNumbers = {"I","II","III","IV","V","VI","VII","VIII","IX","X"};

        for (int i = 0; i < romNumbers.length; i++)
        {
            if (s.equals(romNumbers[i]))
            {
                return i+1;
            }
        }

        return 0;
    }
    public static String arabToRoman(String s)
    {
        String strFirstValue = "";

        int value = Integer.valueOf(s);
        int firstValue = value % 10;
        int secondValue = (value / 10) % 10;

        if (value / 100 == 1)
        {
            strFirstValue = "C";
            return strFirstValue;
        }

        switch (secondValue)
        {
            case 1:
            case 2:
            case 3:
                for (int i = 0;i < secondValue;i++)
                {
                    strFirstValue += "X";
                }
                break;
            case 4:
                strFirstValue += "XL";
                break;
            case 5:
                strFirstValue += "L";
                break;
            case 6:
            case 7:
            case 8:
                strFirstValue += "L";
                for (int i = 0;i < secondValue - 5;i++)
                {
                    strFirstValue += "X";
                }
                break;
            case 9:
                strFirstValue += "XC";
                break;
        }

        switch (firstValue)
        {
            case 1:
            case 2:
            case 3:
                for (int i = 0;i < firstValue;i++)
                {
                    strFirstValue += "I";
                }
                break;
            case 4:
                strFirstValue += "IV";
                break;
            case 5:
                strFirstValue += "V";
                break;
            case 6:
            case 7:
            case 8:
                strFirstValue += "V";
                for (int i = 0;i < firstValue - 5;i++)
                {
                    strFirstValue += "I";
                }
                break;
            case 9:
                strFirstValue += "IX";
                break;
        }

        return strFirstValue;
    }
    public static boolean checkIsRoman(String s) throws MyException
    {
        String[] romNumbers = {"I","II","III","IV","V","VI","VII","VIII","IX","X"};
                ;
        for (int i = 0; i < romNumbers.length; i++)
        {
            if (s.equals(romNumbers[i]))
            {
                return  true;
            }
        }

        if (s.length() > 1 && !s.equals("10") || s.equals("C") || s.equals("L"))
        {
           throw new MyException("калькулятор не работает с числами больше 10");
        }

        return false;
    }
}

