package algorithmClass;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class xiaoXiaoLe implements xiaoxiaoleModel {
    int K,M,N,X;
    int map[][],mapTemp[][],mapStore[][];
    Random random= new Random();
    exchangePoint exchangePoint;
    ArrayList<int[][]> arrayList;
    public xiaoXiaoLe() {
        arrayList = new ArrayList<>();
    }
    public void First(){
        M=8;
        N=4;
        map=new int[8][];
        map[0]=new int[]{3 ,3 ,4, 3 };
        map[1]=new int[]{3, 2, 3, 3 };
        map[2]=new int[]{2, 4 ,3 ,4 };
        map[3]=new int[]{1 ,3 ,4 ,3 };
        map[4]=new int[]{3, 3, 1 ,1};
        map[5]=new int[]{3 ,4 ,3 ,3 };
        map[6]=new int[]{1, 4, 4 ,3 };
        map[7]=new int[]{1, 2, 3 ,2};
        mapTemp=new int[8][4];
        mapStore=new int[8][4];

        //getInput();
        //initialMap(); //初始化矩阵
       // inspectInitialMap(map);//检查矩阵
        exchangePoint=new exchangePoint(M,N);
        System.out.println("------初始矩阵---------");
        outMap(map);//输出初始矩阵
        exchangePoint = MaxScoreOneStep(map);//一步操作可得最大得分
        showOnestepMaxScore(exchangePoint); //输出一步操作的最大得分
    }
    public void showOnestepMaxScore(exchangePoint exchangePoint){
        System.out.print("一步交换最大得分：");
        System.out.println(exchangePoint.maxScore +
                "\n交换坐标为(" + exchangePoint.x1 + " " + exchangePoint.y1 + ")( " + exchangePoint.x2 + " " + exchangePoint.y2+")");
        outMap(mapStore);
    }
    public void outMap(int map[][]){
        for (int i=0;i<M;i++)
            for (int j=0;j<N;j++){
                System.out.print(map[i][j]+" ");
            if (j==N-1)
                System.out.println();
        }
    }
    public void setMapTemp(int[][]map,int[][]mapTemp){
        //for (int k=0;k<M;k++)//创建临时数组
        // System.arraycopy(map[k],0,mapTemp[k],0,N);
        for (int k=0;k<M;k++)
            for(int m=0;m<N;m++)
                mapTemp[k][m]=map[k][m];
    }
    //一步操作可得最大得分
    public exchangePoint MaxScoreOneStep(int map[][]){
        for(int i=0;i<M;i++){
            for (int j=0;j<N;j++){
               //与右边交换 ,并且自己和右边都不能是0
                if (j < N - 1 && map[i][j] != 0 && map[i][j+1]!=0 && map[i][j]!=map[i][j]) {
                    //创建临时数组
                      setMapTemp(map,mapTemp);
                    changeTwoPoint(mapTemp,i,j,i,j+1); //交换两个点
                    //查找所得分数
                    int scole=getGoal(mapTemp,i,j)+getGoal(mapTemp,i,j+1);
                    if (scole>exchangePoint.maxScore){
                        setMapTemp(mapTemp,mapStore);
                        exchangePoint.maxScore=scole;
                        exchangePoint.x1=exchangePoint.x2=i;
                        exchangePoint.y1=j;
                        exchangePoint.y2=j+1;
                    }
                }
                //交换上下两个点
                if(i<M-1 && map[i][j]!=0 && map[i+1][j]!=0 && map[i+1][j]!=map[i][j]){
                   //创建临时数组
                    setMapTemp(map,mapTemp);
                    changeTwoPoint(mapTemp,i,j,i+1,j);
                    int scole=getGoal(mapTemp,i,j);
                        scole+=getGoal(mapTemp,i+1,j);
                    if (scole>exchangePoint.maxScore){
                        setMapTemp(mapTemp,mapStore);
                        exchangePoint.maxScore=scole;
                        exchangePoint.y1=exchangePoint.y2=j;
                        exchangePoint.x1=i;
                        exchangePoint.x2=i+1;
                    }
                }
            }
        }
        return exchangePoint;
    }
    //交换两个点
    public void changeTwoPoint(int map[][], int x1, int y1, int x2, int y2){
        int  temp=map[x1][y1];
        map[x1][y1]=map[x2][y2];
        map[x2][y2]=temp;
    }
    //找到交换后某点处的得分
    public int getGoal(int map[][], int x, int y) {
        if (map[x][y]==0)
            return 0;
        int maxScore;
        int colscore, rowscore;
        int left = y, right = y, up = x, down = x;
            //列                //行
        //找到左边相同的数量
        while (left > 0 && map[x][left - 1] == map[x][left] ) {
            left--;
        }
        //找到右边相同的数量
        while (right < N - 1 && map[x][right] == map[x][right + 1] ) {
            right++;
        }
        //找到上面相同的数量
        while (up > 0 && map[up - 1][y] == map[up][y]) {
            up--;
        }
        //找到下面相同的数量
        while (down < M - 1 && map[down + 1][y] == map[down][y]) {
            down++;
        }
        //计算行和列分别由多少个相同
        rowscore = right - left + 1;
        colscore = down-up + 1;
        //计算得分
        if (rowscore < 3) //没有连续三个相同
            rowscore = 0;
        else {
            if (rowscore == 3)
                rowscore = 1;
            else if (rowscore >=5)
                rowscore = 10;
            //三个以上改变矩阵
            chageMap(map,x,left,x,right,0);
        }
        if (colscore < 3)
            colscore = 0;
        else {
            if (colscore == 3)
                colscore = 1;
            else if (colscore >= 5)
                colscore = 10;
            chageMap(map,up,y,down,y,rowscore);
        }
        maxScore=rowscore+colscore;//求目前的得分
        //递归求坐标改变后是否继续存在连续三个的点
        if (rowscore>0){
            for (int i=1;i<=x;i++)
                for (int j=left;j<=right;j++)
                    if (map[i][j]!=0)
                        maxScore+=getGoal(map,i,j); //递归
        }
        if (colscore>0){
            for (int i=3;i<=down;i++)
                if (map[i][y]!=0)
                    maxScore+=getGoal(map,i,y); //递归
        }
        return maxScore;
    }
    void  show(int[][] map){
        for (int i=0;i<M;i++){
            for (int j=0;j<N;j++)
                System.out.print(map[i][j]+" ");
            System.out.println();
        }
    }
    //改变矩阵
    public void chageMap(int map[][], int x1, int y1, int x2, int y2, int row){
        //消除的是同一行
        if (x1==x2)
             for (int i=y1;i<=y2;i++){
                 for (int j=x1;j>0;j--)
                     map[j][i]=map[j-1][i];
                 map[0][i]=0;

            }

        //消除的是同一列
        else {
            if(row!=0)
                row=1;
            int colSanme=x2-x1+1;
                colSanme-=row;
            for (int i = x2; i >=3; i--){
                if (i-colSanme>=0) {
                    map[i][y1] = map[i - colSanme][y1];
                }
                else
                    break;
            }
            for (int i=0;i<colSanme+row;i++)
                map[i][y1]=0;
        }
    }
    //产生随机矩阵
    void getInput(){
        System.out.println("分别请输入M ，N ,K的值");
        Scanner in=new Scanner(System.in);
        M=in.nextInt();
        N=in.nextInt();
        K=in.nextInt();
    }
    public void initialMap(){
        map=new int[M][N];
        mapTemp=new int[M][N];
        mapStore=new int[M][N];
        for (int i = 0; i < M; i++)
            for (int j=0;j<N;j++){
                map[i][j]= random.nextInt(K)+1;
            }
    }
    //检查初始矩阵是否符合条件
    public void inspectInitialMap(int map[][]){
        //检查行是否有连续三个相同
        for (int i=0;i<M;i++)
            for (int j = 0; j < N; j++){
                //检查行行和列是否有连续的三个相等
                //若有，重新产生随机数并进行递归判断是否连续三个相等
                while( (j!=0 && j!=N-1 && map[i][j-1]==map[i][j] && map[i][j]==map[i][j+1])||
                        (i!=0 && i!= M-1 && map[i-1][j]==map[i][j] && map[i][j]==map[i+1][j])){
                    map[i][j]=random.nextInt(K);
                }

        }
    }
    public static void main(String[] args){
        new xiaoXiaoLe();
    }
}
