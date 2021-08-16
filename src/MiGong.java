/**
 * MiGong
 * <h5>描述</h5>
 **/
public class MiGong {
    public static void main(String[] args) {
        //先创建一个二维数组，模拟迷宫
        int[][] map = new int[8][7];

        //使用1表示墙
        //上下全部是1
        for(int i = 0;i<7;i++){
            map[0][i] = 1;
            map[7][i] = 1;
        }

        //左右全部是1
        for(int j = 0;j < 8;j++){
            map[j][0] = 1;
            map[j][6] = 1;
        }
        map[3][1] = 1;
        map[3][2] = 1;



        //输出地图
        System.out.println("地图情况：");
        for(int i = 0;i<8;i++){
            for(int j = 0;j<7;j++) {
                System.out.print("\t"+map[i][j]);
            }
            System.out.println();
        }

        //使用方法
        setWay(map,1,1);
        System.out.println("游戏开始，地图变化：");
        for(int i = 0;i<8;i++){
            for(int j = 0;j<7;j++) {
                System.out.print("\t"+map[i][j]);
            }
            System.out.println();
        }
    }

    //使用递归回溯来给小球找路

    /**
     *  游戏设定：行走路线为下，右，上，左，然后坐标 1 表示墙，2 表示通路可以走 ，3 表示该点已经走过了，但是走不通
     * @param map   表示地图
     * @param i     从哪个位置开始找
     * @param j
     * @return      如果找到通路，就返回true，否则返回false;
     */

    //修改2：改成 上 -> 右 -> 下 -> 左
    public static boolean setWay(int[][] map,int i,int j){
        if(map[6][5] == 2){
            return true;
        }else{
            if(map[i][j] == 0) {
                map[i][j] = 2;
//                if(setWay(map,i+1,j)){  //下
//                    return true;
//                }else if(setWay(map,i,j+1)){  //右
//                    return true;
//                }else if(setWay(map,i-1,j)){  //上
//                    return true;
//                }else if(setWay(map,i,j-1)){    //左
//                    return true;
//                }else{
//                    map[i][j] = 3;
//                    return false;
//                }
//            }else{
//                return false;
//            }

                //第二种路线：上 -> 右 -> 下 -> 左
                if (setWay(map, i - 1, j)) {
                    return true;
                } else if (setWay(map, i, j + 1)) {
                    return true;
                } else if (setWay(map, i + 1, j)) {
                    return true;
                } else if (setWay(map, i, j - 1)) {
                    return true;
                } else {
                    map[i][j] = 3;
                    return false;
                }
            }else{
                    return false;
                }
        }

    }
}
