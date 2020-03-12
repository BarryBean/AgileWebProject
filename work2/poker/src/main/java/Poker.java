
import java.util.*;

/**
 * @PackageName:com.company
 * @ClassName:Poker
 * @Description
 * @Author: yushengbi
 * @Date:2020/3/11 16:36
 */
public class Poker {

    static int[] pokerValue;
    static char[] blackChars;
    static char[] whiteChars;

    static int[] blackNums = new int[5];
    static int[] blackColors = new int[5];
    static int[] whiteNums = new int[5];
    static int[] whiteColors = new int[5];

    /**
     * 主函数
     *
     * @param black 按字符串输入
     * @param white
     * @return
     */
    public static String poker(String black, String white) {
        pokerValue = new int[10];
        //将字符串转数组
        blackChars = black.toCharArray();
        whiteChars = white.toCharArray();
        //把两个人的牌花色和数字分开
        //输入字符串带空格
        for (int i = 0, j = 0; i < blackChars.length; i += 3, j++) {
            blackNums[j] = turn2Int(blackChars[i]);
            whiteNums[j] = turn2Int(whiteChars[i]);
        }
        for (int i = 1, j = 0; i < blackChars.length; i += 3, j++) {
            blackColors[j] = turn2Int(blackChars[i]);
            whiteColors[j] = turn2Int(whiteChars[i]);
        }

        //计算牌型，每种牌型有一个基准数值
        //黑
        isSameColor(blackColors);
        isStraight(blackNums);
        dependNumType(blackNums);
        int blackType = pokerType();
        //清空下
        Arrays.fill(pokerValue, 0);
        //白
        isSameColor(whiteColors);
        isStraight(whiteNums);
        dependNumType(whiteNums);
        int whiteType = pokerType();

        //比较牌型
        if (blackType == whiteType) {
            return returnWhoWin(blackNums, whiteNums) == 0 ? "Tie" :
                    (returnWhoWin(blackNums, whiteNums) == 1 ? "Black wins" : "White wins");
        } else if (blackType > whiteType) {
            return "Black wins";
        } else if (blackType < whiteType) {
            return "White wins";
        }
        return null;
    }

    /*public static void main(String[] args) {
        String black = "2H 3D 5S 9C KD";
        String white = "2C 3H 4S 8C AH";
        String black1 = "2H 4S 4C 2D 4H";
        String white1 = "2S 8S AS QS 3S";
        String black2 = "2H 3D 5S 9C KD";
        String white2 = "2D 3H 5C 9S KH";
        System.out.println(poker(black, white));
        System.out.println(poker(black1, white1));
        System.out.println(poker(black2, white2));
    }*/

    /**
     * 牌型相同时比较大小
     * 黑赢返回1，白赢返回-1，平局返回0
     *
     * @param blackNums
     * @param whiteNums
     * @return
     */
    public static int returnWhoWin(int[] blackNums, int[] whiteNums) {
        //顺子和同花顺比较最大的牌
        if (pokerValue[9] == 1 || pokerValue[5] == 1) {
            return compareNum(maxInNums(blackNums), maxInNums(whiteNums));
        } else if (pokerValue[8] == 1 || pokerValue[7] == 1 || pokerValue[4] == 1) {
            //三条 葫芦 四条
            return compareNum(sameNum(blackNums), sameNum(whiteNums));
        } else if (pokerValue[3] == 1) {
            //俩对子
            return twoPairWin(blackNums, whiteNums);
        } else if (pokerValue[2] == 1) {
            //一个对子
            return pairWin(blackNums, whiteNums);
        } else {
            //高牌和同花
            return highCardWin(blackNums, whiteNums);
        }
    }

    /**
     * 黑赢返回1，白赢返回-1，平局返回0
     *
     * @param black
     * @param white
     * @return
     */
    public static int compareNum(int black, int white) {
        if (black == white) {
            return 0;
        } else {
            return black > white ? 1 : -1;
        }
    }

    /**
     * 高牌和同花，从大到小比较，相等就继续
     *
     * @param black
     * @param white
     * @return
     */
    public static int highCardWin(int[] black, int[] white) {
        Arrays.sort(black);
        Arrays.sort(white);
        int i = black.length - 1;
        while (i > 0 && black[i] == white[i]) {
            i--;
        }
        return i == 0 ? 0 : compareNum(black[i], white[i]);
    }

    /**
     * 单个对子比较
     *
     * @param black
     * @param white
     * @return
     */
    public static int pairWin(int[] black, int[] white) {
        if (compareNum(pairNum(black, true), pairNum(white, true)) == 0) {
            //对子相等，比较其他牌
            //可以调普通比较的方法，因为排序后谁大就是谁，不管对子
            return highCardWin(black, white);
        } else {
            return compareNum(pairNum(black, true), pairNum(white, true));
        }
    }

    /**
     * 两个对子比较
     *
     * @param black
     * @param white
     * @return
     */
    public static int twoPairWin(int[] black, int[] white) {
        //大对子比较相等
        if (compareNum(pairNum(black, true), pairNum(white, true)) == 0
                && compareNum(pairNum(black, false), pairNum(white, false)) == 0) {
            return highCardWin(black, white);
        } else if (compareNum(pairNum(black, true), pairNum(white, true)) == 0
                && compareNum(pairNum(black, false), pairNum(white, false)) != 0) {
            return compareNum(pairNum(black, false), pairNum(white, false));
        } else {
            return compareNum(pairNum(black, true), pairNum(white, true));
        }

    }

    /**
     * 返回较大/较小的对子
     *
     * @param nums
     * @return
     */
    public static int pairNum(int[] nums, boolean bigger) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int ret = 0;
        for (int num : nums) {
            if (map.get(num) == 2 && bigger) {
                ret = num > ret ? num : ret;
            } else if (map.get(num) == 2 && !bigger) {
                ret = num < ret ? num : ret;
            }
        }
        return ret;
    }

    /**
     * 返回数组中重复多次的元素
     *
     * @param nums
     * @return
     */
    public static int sameNum(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        for (int num : nums) {
            if (map.get(num) == 4) {
                return num;
            } else if (map.get(num) == 3) {
                return num;
            }
        }
        return -1;
    }

    /**
     * 返回数组中max
     *
     * @param nums
     * @return
     */
    public static int maxInNums(int[] nums) {
        int max = Integer.MIN_VALUE;
        for (int num : nums) {
            max = num > max ? num : max;
        }
        return max;
    }


    /**
     * 判断同花
     *
     * @param colors
     * @return
     */
    public static void isSameColor(int[] colors) {
        int tmp = colors[0];
        for (int color : colors) {
            if (color != tmp) {
                pokerValue[6] = 0;
                return;
            }
        }
        pokerValue[6] = 1;
    }

    /**
     * 判断顺子
     *
     * @param nums
     * @return
     */
    public static void isStraight(int[] nums) {
        int tmp = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != tmp + 1) {
                pokerValue[5] = 0;
                return;
            }
            tmp = nums[i];
        }
        pokerValue[5] = 1;
    }

    /**
     * 判断4条/3带2/三条/俩对子/一对子/单牌
     *
     * @param nums
     * @return
     */
    public static void dependNumType(int[] nums) {
        Set<Integer> numSet = new HashSet<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            numSet.add(nums[i]);
            Integer time = map.getOrDefault(nums[i], 0);
            map.put(nums[i], time + 1);
        }
        //11111 or 2111
        if (numSet.size() == 5) {
            pokerValue[1] = 1;
            return;
        } else if (numSet.size() == 4) {
            pokerValue[2] = 1;
            return;
        }
        //4+1 or 3+2
        if (numSet.size() == 2) {
            for (Integer num : numSet) {
                if (map.get(num) == 4) {
                    pokerValue[8] = 1;
                    return;
                } else if (map.get(num) == 3) {
                    pokerValue[7] = 1;
                    return;
                }
            }
        } else if (numSet.size() == 3) {
            //3+1+1 or 2+2+1
            for (Integer num : numSet) {
                if (map.get(num) == 3) {
                    pokerValue[4] = 1;
                    return;
                } else {
                    pokerValue[3] = 1;
                    return;
                }
            }
        }
    }

    /**
     * 根据bool值，返回对应牌型，根据牌型返回int值
     * 9种牌对应9个值
     *
     * @return
     */
    public static int pokerType() {
        if (pokerValue[5] == 1 && pokerValue[6] == 1) {
            pokerValue[9] = 1;
            pokerValue[5] = 0;
            pokerValue[6] = 0;
        }
        for (int i = 0; i < pokerValue.length; i++) {
            if (pokerValue[i] == 1) {
                return i;
            }
        }
        return -1;
    }

    public static int turn2Int(char c) {
        switch (c) {
            case 'T':
                return 10;
            case 'J':
                return 11;
            case 'Q':
                return 12;
            case 'K':
                return 13;
            case 'A':
                return 14;
            case 'C':
                return 1;
            case 'D':
                return 2;
            case 'H':
                return 3;
            case 'S':
                return 4;
            default:
                return c - '0';
        }

    }
}
