package algorithmClass;

import java.util.ArrayList;
import java.util.Scanner;

public class xiaoxiaoleXstep extends xiaoXiaoLe {
     exchangePoint root;
     int DNSMaxScore;
     long startTime;
     long endTime;
     exchangePoint max;
     ArrayList<exchangePoint> arrayList;
    public xiaoxiaoleXstep(){
        First();
        root =new exchangePoint(M,N);
        root.arrayList=new ArrayList<>();
        arrayList=new ArrayList<>();
        Scanner scanner=new Scanner(System.in);
        DNSMaxScore=0;
       while (true){
           System.out.print("请输入要查找的步数");
           DNSMaxScore=0;
           X=scanner.nextInt();
           startTime=System.currentTimeMillis();
           backtrackingXstep(map,0,0,root);
           findXstep(max);
           showXstep(arrayList);
           endTime=System.currentTimeMillis();
           System.out.println("运行时间："+(endTime-startTime)+" ms");
           System.out.println("最大得分为： "+DNSMaxScore);
           arrayList.clear();

       }
   }
   void  findXstep(exchangePoint root){
       if (root==null)
           return;
       arrayList.add(root);
       findXstep(root.parent);
       return ;
   }
   void showXstep(ArrayList<exchangePoint> arrayList){
        for (int i=arrayList.size()-2;i>=0;i--)
        {
            System.out.print("第"+(X-i)+"次");
            arrayList.get(i).showMessage();
        }
   }
    //回溯法
    public int backtrackingXstep(int map[][], int step, int scoreSum, exchangePoint root){
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
                        exchangePoint node = new exchangePoint(M, N);
                        node.setMessage(mapTemp, i, j, i, j + 1, score, scoreSum);
                        node.parent=root;
                        if (score + scoreSum > DNSMaxScore) {
                                DNSMaxScore = score + scoreSum;
                                max = node;
                            }
                        root.arrayList.add(node);
                        backtrackingXstep(mapTemp, step + 1, scoreSum + score, node);
                    }
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
                        exchangePoint node = new exchangePoint(M, N);
                        node.setMessage(mapTemp, i, j, i + 1, j, score, scoreSum);
                        if (score + scoreSum > DNSMaxScore) {
                            DNSMaxScore = score + scoreSum;
                            max=node;
                        }
                        node.parent=root;
                        root.arrayList.add(node);
                        backtrackingXstep(mapTemp, step + 1, scoreSum + score, node);
                    }
                }
            }
        }
        return 0;
    }
    public static void  main(String[]args) {
        new xiaoxiaoleXstep();
    }
}
