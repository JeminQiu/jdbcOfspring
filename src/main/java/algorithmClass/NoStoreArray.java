package algorithmClass;

import java.util.ArrayList;
import java.util.Scanner;
//不存储矩阵的形式回溯法求消消乐
public class NoStoreArray extends xiaoXiaoLe {
    exchangePoint root;
    int DNSMaxScore;
    long startTime,endTime;
    int avgScore;
    int avgTime;
    public NoStoreArray(){

    }
    public void control(int[][] map,int M,int N,int X){
        this.M=M;
        this.N=N;
        this.X=X;
        startTime=0;
        endTime=0;
        DNSMaxScore=0;
        startTime = System.currentTimeMillis();
        backtrackingXstep(map, 0, 0);
        endTime = System.currentTimeMillis();
        avgTime+=endTime-startTime;
        avgScore+=DNSMaxScore;
    }
    public void Automic(){
        root =new exchangePoint(M,N);
        root.arrayList=new ArrayList<>();
        Scanner scanner=new Scanner(System.in);
        DNSMaxScore=0;
        X=3; K=3;
        while (true){
            avgScore=0;
            avgTime =0;
            getInput();
            System.out.print("请输入要查找的步数");
            X=scanner.nextInt();
            for (int i=0;i<20;i++) {
                initialMap();
                inspectInitialMap(map);
                DNSMaxScore = 0;
                startTime = System.currentTimeMillis();
                backtrackingXstep(map, 0, 0);
                endTime = System.currentTimeMillis();
                avgScore += DNSMaxScore;
                avgTime += endTime - startTime;
                arrayList.clear();
            }
            System.out.println("平均时间为 "+ avgTime /20 +" ms");
            System.out.println("平均得分为 "+avgScore/20);
        }
    }

    public int backtrackingXstep(int map[][], int step, int scoreSum){
        if (step==X)
            return -1;
        for(int i=0;i<M;i++){
            for (int j=0;j<N;j++){
                //与右边交换 ,并且自己和右边都不能是0
                if (j < N - 1 && map[i][j] != 0 && map[i][j+1]!=0) {
                    //创建临时数组
                    int mapTemp[][]=new int[M][N];
                    setMapTemp(map,mapTemp);
                    changeTwoPoint(mapTemp,i,j,i,j+1); //交换两个点
                    //查找所得分数
                    int score=getGoal(mapTemp,i,j)+getGoal(mapTemp,i,j+1);
                    if(score!=0) {
                        if (score+scoreSum>DNSMaxScore)
                            DNSMaxScore=score+scoreSum;
                        backtrackingXstep(mapTemp, step + 1, scoreSum + score);
                    }
                    mapTemp=null;
                }
                //交换上下两个点
                if(i<M-1 && map[i][j]!=0 && map[i+1][j]!=0) {
                    //创建临时数组
                    int[][] mapTemp = new int[M][N];
                    setMapTemp(map, mapTemp);
                    changeTwoPoint(mapTemp, i, j, i + 1, j);
                    int score = getGoal(mapTemp, i, j);
                    score += getGoal(mapTemp, i + 1, j);
                    if (score != 0) {
                        if (score + scoreSum > DNSMaxScore)
                            DNSMaxScore = score + scoreSum;
                        backtrackingXstep(mapTemp, step + 1, scoreSum + score);
                        mapTemp = null;
                    }
                }
            }
        }
        return 0;
    }
    public static void  main(String[]args) {
        new NoStoreArray();
    }
}
