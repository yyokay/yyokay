package org.yyok.lib.mining

import org.apache.spark.SparkContext
import org.apache.spark.ml.feature.LabeledPoint
import org.apache.spark.ml.linalg.Vectors
import org.apache.spark.rdd.RDD
import org.apache.log4j.{Level, Logger}

object BriefTransform {

  Logger.getLogger("org").setLevel(Level.DEBUG)
  //Logger.getLogger("com").setLevel(Level.OFF)
  System.setProperty("spark.ui.showConsoleProgress", "false")
  Logger.getRootLogger().setLevel(Level.OFF)

  /**
    * ETL过程：数据预处理
    *
    * @param sc
    * @return
    */
  def transform2DF(sc: SparkContext): Map[String, RDD[LabeledPoint]] = {

    //定义返回目标
    var datas:Map[String, RDD[LabeledPoint]] = BriefExtract.extract2DF(sc)

    //值检查---样本值正常（用肉眼观看）

    //值预处理--没有不正常

    /* data1 = data1.filter(x=>{
       println()
     })*/

    //值转换成向量
    val tree1 = null

    val tree2 = null

    //返回目标归档
    datas += ("trainingData" -> tree1)
    datas += ("testData" -> tree2)

    //返回
    datas

  }

  def main(args: Array[String]): Unit = {

  }

}
