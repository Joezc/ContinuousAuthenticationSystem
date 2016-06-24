package com.sensor.genkun.hmm;

/**
 *
 *HMM类：
 *1.HMM模型的几个重要参数，两个状态，三个矩阵；
 *2.定义了两个构造方法；
 */

public class HMM {
	protected int N;//状态数
	protected int M;//观察符号数
	protected double[][] A;//状态转移概率矩阵，N*N矩阵
	protected double[][] B;//符号观测概率矩阵，即混淆矩阵，N*M矩阵
	protected double[] PI;//初始状态概率分布矩阵，N维向量

	public HMM(){}
	//参数1 状态数目 ；参数2 观察符号数目
	public HMM(int stateNum,int observationSymbolNum)
	{
		N=stateNum;
		M=observationSymbolNum;
		A=new double[N][N];
		B=new double[N][M];
		PI=new double[N];
	}
}