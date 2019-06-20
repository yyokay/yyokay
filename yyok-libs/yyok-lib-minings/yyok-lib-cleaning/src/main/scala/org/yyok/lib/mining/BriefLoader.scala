package org.yyok.lib.mining

import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkContext
import org.apache.spark.ml.feature.LabeledPoint
import org.apache.spark.ml.linalg.Vectors
import org.apache.spark.rdd.RDD

object BriefLoader {

  Logger.getLogger("org").setLevel(Level.OFF)
  Logger.getLogger("com").setLevel(Level.OFF)
  System.setProperty("spark.ui.showConsoleProgress", "false")
  Logger.getRootLogger().setLevel(Level.OFF)


  /**
    * ETL过程：数据预处理
    *
    * @param sc
    * @return
    */
  def briefLoad2DF(sc: SparkContext): Map[String, RDD[LabeledPoint]] = {

    val data1: RDD[String] = sc.textFile("D:\\data\\decisiontree\\decisiontreeu.txt")

    //xxx测试数据加载
    val data2: RDD[String] = sc.textFile("D:\\data\\decisiontree\\decisiontreeu1.txt")

    //定义返回目标
    var datas:Map[String, RDD[LabeledPoint]] = Map()

    //值检查---样本值正常（用肉眼观看）

    //值预处理--没有不正常

    /* data1 = data1.filter(x=>{
       println()
     })*/

    //值转换成向量
    val tree1 = data1.map { line =>
      val parts = line.split(',')
      LabeledPoint(parts(0).toDouble, Vectors.dense(parts(1).split(' ').map(_.toDouble)))
    }

    val tree2 = data2.map { line =>
      val parts = line.split(',')
      LabeledPoint(parts(0).toDouble, Vectors.dense(parts(1).split(' ').map(_.toDouble)))
    }

    //返回目标归档
    datas += ("trainingData" -> tree1)
    datas += ("testData" -> tree2)

    //返回
    datas

  }



  def main(args: Array[String]): Unit = {

  }

}
