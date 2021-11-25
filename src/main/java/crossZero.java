import java.util.Random;
import java.util.Scanner;
import java.util.Objects;

public class crossZero {

    private static int SIZE = 3;
    private static int DOTS_TO_WIN = 3;
    private static char DOT_EMPTY = '.';
    private static char DOT_X = 'X';
    private static char DOT_0 = '0';
    private static char[][] MAP;
    private final static Scanner SCANNER = new Scanner(System.in);
    public static Random random = new Random();

    public static void main(String[] args) {
        initMap();
        printMap();

        while (true) {
            humanTurn();
            printMap();
            if (checkWin(DOT_X)) {
                System.out.println("Победил человек");
                break;
            }
            if (isMapFull()) {
                System.out.println("Ничья");
                break;
            }
            aiTurn();
            printMap();
            if (checkWin(DOT_0)) {
                System.out.println("Победил Искуственный Интеллект");
                break;
            }
            if (isMapFull()) {
                System.out.println("Ничья");
                break;
            }
        }
        System.out.println("Игра закончена");
    }

    private static boolean isMapFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (MAP[i][j] == DOT_EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void initMap() {
        MAP = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                MAP[i][j] = DOT_EMPTY;


            }
        }
    }

    public static void printMap() {
        for (int i = 0; i <= SIZE; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(MAP[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void humanTurn() {
        int x, y;
        do {
            System.out.println("Введите координаты в формате X Y");
            x = SCANNER.nextInt() - 1;
            y = SCANNER.nextInt() - 1;
        } while (!isCellValid(x, y));
        MAP[y][x] = DOT_X;
    }

    public static boolean isCellValid(int x, int y) {
        if (x < 0 || x >= SIZE || y < 0 || y >= SIZE) {
            return false;
        }
        if (MAP[y][x] == DOT_EMPTY) return true;

        return false;
    }

    public static void aiTurn() {
        int x, y;
        do {
            x = random.nextInt(SIZE);
            y = random.nextInt(SIZE);
        } while (!isCellValid(x, y));
        System.out.println("Компьютер походил в точку " + (x + 1) + " " + (y + 1));
        MAP[y][x] = DOT_0;
    }

    public static boolean checkWin(char symb) {
        int b = 0;
        int d = 0, r = 0, z = 0, e = 0, f = 0; //счетчики символов
        int w = 0, m = 0, n = 0;              //счетчики символов


        for (char[] chars : MAP) { // проверка вертикалей
            if (chars[b] == symb) w = w + 1;
            if (chars[b + 1] == symb) m = m + 1;
            if (chars[b + 2] == symb) n = n + 1;
            if (w == DOTS_TO_WIN || m == DOTS_TO_WIN ||n == DOTS_TO_WIN) return true;
        }

        for (int i = 0; i < MAP.length; i++) { // проверка горизонталей
            if (MAP[b][i] == symb) d = d + 1;
            if (MAP[b + 1][i] == symb) r = r + 1;
            if (MAP[b + 2][i] == symb) z = z + 1;
            if (d == DOTS_TO_WIN || r == DOTS_TO_WIN || z == DOTS_TO_WIN) return true;
        }

        for (int i = 0; i < MAP.length; i++) {  //проверка дигонали 1.1 2.2 3.3
            if (symb == MAP[i][i]) {
                f = f + 1;
                if (f == DOTS_TO_WIN) return true;
            }
        }

        for (int i = 0; i < MAP.length; i++) {        //проверка дигонали 3.1 2.2 1.3
            for (int j = 0; j < MAP.length - i; j++) {
                if (MAP[i][j] == symb) {
                    e = e + 1;
                }
                if (MAP[j][i] == symb) {
                    break;
                }
            }
            if (e == DOTS_TO_WIN) return true;
        }

        return false;
    }
}

