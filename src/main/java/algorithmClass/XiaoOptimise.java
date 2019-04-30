package algorithmClass;


import java.util.*;

public class XiaoOptimise extends xiaoXiaoLe {
    exchangePoint root;
    int DNSMaxScore;
    long startTime;
    long endTime;
    int avgTime;
    int avgScore;
    exchangePoint max;
    ArrayList<exchangePoint> arrayList;
    HashSet<Integer>hashCodes;
    HashMap<Integer,Integer> hashMap;
    NoStoreArray noStoreArray;
    public XiaoOptimise(){
        root =new exchangePoint(M,N);
        root.arrayList=new ArrayList<>();
        arrayList=new ArrayList<>();
        Scanner scanner=new Scanner(System.in);
        DNSMaxScore=0;
        hashCodes=new HashSet<>();
        noStoreArray=new NoStoreArray();
        hashMap=new HashMap<>();
        while (true){
            hashCodes.clear();
            noStoreArray.avgTime=0;
            noStoreArray.avgScore=0;
            getInput();
            avgScore=0;
            avgTime=0;
            int m=5;
            for (int i=0;i<m;i++) {
                initialMap();
                inspectInitialMap(map);
                DNSMaxScore=0;
                startTime = System.currentTimeMillis();
                backtrackingXstep(map, 0, 0, root);
                if(i==0) {
                    System.out.print("------初始矩阵----\n     ");
                    for (int k=0;k<M ;k++){
                        for (int l=0 ;l<N ;l++)
                            System.out.print(map[k][l]+" ");
                        System.out.print("\n     ");
                    }
                    findXstep(max);
                    showXstep(arrayList);
                }
                endTime = System.currentTimeMillis();
                avgScore += DNSMaxScore;
                avgTime += endTime - startTime;
                arrayList.clear();
                noStoreArray.control(map, M, N, X);
            }
            System.out.println("测试"+m+"组数据求平均后");
            System.out.println("优化后的算法，平均得分为   "+avgScore/m+" 平均耗时为"+avgTime/m+"ms");
           System.out.println("未优化的回溯法，平均得分为 "+noStoreArray.avgScore/m+
                   " 平均耗时为"+noStoreArray.avgTime/m+" ms");
            int d=noStoreArray.avgScore/m-avgScore/m;
           System.out.println("平均误差百分比为"+((double)d/(noStoreArray.avgScore/m)-0.0));
        }
    }
    void getInput(){
        System.out.println("分别请输入M ，N ,K ,X的值");
        Scanner in=new Scanner(System.in);
        M=in.nextInt();
        N=in.nextInt();
        K=in.nextInt();
        X=in.nextInt();
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
            arrayList.get(i).showMessage();
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
                        if (score+scoreSum>DNSMaxScore) {
                            DNSMaxScore = score + scoreSum;
                            max=node;
                        }
                        if (!hashMap.containsKey(node.hashCode())){
                            //hashCodes.add(node.hashCode());
                            hashMap.put(node.hashCode(),score+scoreSum);
                            root.arrayList.add(node);
                            backtrackingXstep(mapTemp, step + 1, scoreSum + score, node);
                        }
                       /*  else if (score + scoreSum > DNSMaxScore) {
                            DNSMaxScore = score + scoreSum;
                            max = node;
                            backtrackingXstep(mapTemp, step + 1, scoreSum + score, node);
                        }*/
                       else if (score+scoreSum>hashMap.get(node.hashCode())){
                            root.arrayList.add(node);
                            backtrackingXstep(mapTemp, step + 1, scoreSum + score, node);
                        }
                         else{
                             mapTemp=null;
                             node=null;
                        }
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
                        node.setMessage(mapTemp, i, j, i, j + 1, score, scoreSum);
                        node.parent=root;
                       /* if (!hashCodes.contains(node.hashCode())){
                            hashCodes.add(node.hashCode());
                            root.arrayList.add(node);
                            backtrackingXstep(mapTemp, step + 1, scoreSum + score, node);
                        }*/
                       if (score+scoreSum>DNSMaxScore) {
                           DNSMaxScore = score + scoreSum;
                           max=node;
                       }
                        if (!hashMap.containsKey(node.hashCode())){
                            //hashCodes.add(node.hashCode());
                            hashMap.put(node.hashCode(),score+scoreSum);
                            root.arrayList.add(node);
                            backtrackingXstep(mapTemp, step + 1, scoreSum + score, node);
                        }
                        else if (score+scoreSum>hashMap.get(node.hashCode())){
                            root.arrayList.add(node);
                            backtrackingXstep(mapTemp, step + 1, scoreSum + score, node);
                        }
                        else{
                            mapTemp=null;
                            node=null;
                        }
                    }
                }
            }
        }
        return 0;
    }
    public static void  main(String[]args) {
        new XiaoOptimise();
    }
}
