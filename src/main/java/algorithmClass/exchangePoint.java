package algorithmClass;

import java.util.ArrayList;
import java.util.BitSet;

public class exchangePoint implements Comparable<exchangePoint> {
    public int M,N;
    public int x1,y1,x2,y2;
    public int maxScore;
    public int ScoreSum; //总得分
    public int ScorePresentStep; //当前一步的得分
    public int[][] map; //当前一步交换之后的数组
    public exchangePoint parent;
    ArrayList<exchangePoint> arrayList; //n叉树
    //最坏的情况是每个节点下有(M-1)*N+(N-1)*M个节点，即每个交换的两个点都不是0
    public exchangePoint(int M,int N){
        this.M=M;
        this.N=N;
        maxScore=0;
        ScorePresentStep=0;
        ScoreSum=0;

    }
    public void setMessage( int[][]map,int x1,int y1,int x2,int y2,int ScorePresentStep,int ScoreGain){
        arrayList=new ArrayList<>();
        this.x1=x1;
        this.x2=x2;
        this.y1=y1;
        this.y2=y2;
        this.map=map;
        this.ScorePresentStep=ScorePresentStep;
        ScoreSum=ScoreGain+ScorePresentStep;
    }
    @Override
    public int compareTo(exchangePoint o) {
        return o.ScoreSum>ScoreSum?1:-1;
    }
    @Override
    public int hashCode() {
        BitSet bitSet=new BitSet();
        for(int i=0;i<M;++i){
            for(int j=0;j<N;++j)
                if(map[i][j]!=0)
                    bitSet.set(i*M+j,true);
                else
                    bitSet.set(i*N+j,false);
        }
        return bitSet.hashCode();
    }
    public BitSet bitSet(){
        BitSet bitSet=new BitSet();
        for(int i=0;i<M;++i){
            for(int j=0;j<N;++j)
                if(map[i][j]!=0)
                    bitSet.set(i*M+j,true);
                else
                    bitSet.set(i*N+j,false);
        }
        return bitSet;
    }
    public void showMessage(){
        System.out.println(
                "交换 ("+x1+","+y1+"),("+x2+","+y2+") " +"得分 "+ScorePresentStep+
                "总得分为 "+(ScoreSum));
        System.out.println("交换后数组为 ");
        System.out.print("      ");
        for (int i=0;i<M;i++){
            for(int j=0;j<N;j++)
                System.out.print(map[i][j]+" ");
            System.out.print("\n      ");
        }
    }
    public static  void main(String[] args) {

    }
}