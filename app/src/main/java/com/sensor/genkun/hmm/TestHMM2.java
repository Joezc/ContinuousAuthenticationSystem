package com.sensor.genkun.hmm;

import java.util.List;

public class TestHMM2 {
    enum Algae {dry,damp,soggy };  // 可见状态（海藻的形态：干燥、微湿、湿透）
    enum Weather{sunny,cloudy,rainy}; //隐藏状态（天气状况：晴朗、多云、下雨）

    public static void main(String[] args)
    {
        // 测试前向算法
        CheckForward();
        System.out.println(" ");
        // 测试维特比算法
        CheckViterbi();
    }

    // 测试前向算法
    static void CheckForward()
    {
        // 状态转移矩阵
        double[][] A =
                {
                        {0.500, 0.375, 0.125},
                        {0.250, 0.125, 0.625},
                        {0.250, 0.375, 0.375}
                };

        // 混淆矩阵
        double[][] B =
                {
                        {0.60, 0.20, 0.15, 0.05},
                        {0.25, 0.25, 0.25, 0.25},
                        {0.05, 0.10, 0.35, 0.50}
                };

        // 初始概率向量
        double[] PI = {0.63,0.17,0.20};

        // 观察序列
        int[] OB = {Algae.dry.ordinal(), Algae.damp.ordinal(), Algae.soggy.ordinal()};
        System.out.println("--------------------------前向算法测试--------------------------");
        System.out.println("状态转移概率矩阵：");
        for(int i=0;i<A.length;i++)
        {
            for(int j=0;j<A[0].length;j++)
            {
                System.out.print(A[i][j]+"\t");
            }
            System.out.println();
        }
        System.out.println("符号观测概率矩阵：");
        for(int i=0;i<B.length;i++)
        {
            for(int j=0;j<B[0].length;j++)
            {
                System.out.print(B[i][j]+"\t");
            }
            System.out.println();
        }
        System.out.println("初始概率向量：{"+PI[0]+" "+PI[1]+" "+PI[2]+"}");
        System.out.println("隐藏状态序列：{"+Weather.sunny+" "+Weather.cloudy+" "+Weather.rainy+"}");
        System.out.println("观测序列：{"+Algae.dry+" "+Algae.damp+" "+Algae.soggy+"}");

        // 初始化HMM模型
        Forward forward = new Forward(A.length, B[0].length);
        forward.A=A;
        forward.B=B;
        forward.PI=PI;

        // 观察序列的概率
        double probability = forward.forward(OB);

    }

    // 测试维特比算法
    static void CheckViterbi()
    {
        // 状态转移矩阵
        double[][] A =
                {
                        {0.500, 0.250, 0.250},
                        {0.375, 0.125, 0.375},
                        {0.125, 0.675, 0.375}
                };

        // 混淆矩阵
        double[][] B =
                {
                        {0.60, 0.20, 0.15, 0.05},
                        {0.25, 0.25, 0.25, 0.25},
                        {0.05, 0.10, 0.35, 0.50}
                };

        // 初始概率向量
        double[] PI = { 0.63, 0.17, 0.20 };
        // 观察序列
        int[] OB = {Algae.dry.ordinal(), Algae.damp.ordinal(), Algae.soggy.ordinal()};
        System.out.println("--------------------------维特比算法测试--------------------------");
        System.out.println("状态转移概率矩阵：");
        for(int i=0;i<A.length;i++)
        {
            for(int j=0;j<A[0].length;j++)
            {
                System.out.print(A[i][j]+"\t");
            }
            System.out.println();
        }
        System.out.println("符号观测概率矩阵：");
        for(int i=0;i<B.length;i++)
        {
            for(int j=0;j<B[0].length;j++)
            {
                System.out.print(B[i][j]+"\t");
            }
            System.out.println();
        }
        System.out.println("初始概率向量：{"+PI[0]+" "+PI[1]+" "+PI[2]+"}");
        System.out.println("隐藏状态序列：{"+Weather.sunny+" "+Weather.cloudy+" "+Weather.rainy+"}");
        System.out.println("观测序列：{"+Algae.dry+" "+Algae.damp+" "+Algae.soggy+"}");

        // 初始化HMM模型
        Viterbi viterbi = new Viterbi(A.length, B[0].length);
        viterbi.A=A;
        viterbi.B=B;
        viterbi.PI=PI;

        // 找出最有可能的隐藏状态序列
        double probability = 0;

        List list=viterbi.viterbi(OB,probability);
        int[] Q = (int[]) list.get(0);//返回隐藏状态序列
        System.out.print("最可能的隐藏状态序列为：{");
        for(int value:Q)
        {
            System.out.print(Weather.values()[value]+" ");
        }
        System.out.println("}");
        System.out.println("最大可能性为："+list.get(1));
    }
}
