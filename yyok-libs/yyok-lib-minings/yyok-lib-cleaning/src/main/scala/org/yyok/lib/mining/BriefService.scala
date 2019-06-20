package org.yyok.lib.mining

import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkContext
import org.apache.spark.ml.feature.LabeledPoint
import org.apache.spark.ml.linalg.Vectors
import org.apache.spark.rdd.RDD

object BriefService {


  Logger.getLogger("org").setLevel(Level.OFF)
  Logger.getLogger("com").setLevel(Level.OFF)
  System.setProperty("spark.ui.showConsoleProgress", "false")
  Logger.getRootLogger().setLevel(Level.OFF)

  /**
    * ETL过程：数据业务预处理
    *
    * @param sc
    * @return
    */
  def server2DF(sc: SparkContext): Map[String, RDD[LabeledPoint]] = {

    //定义返回目标
    var datas:Map[String, RDD[LabeledPoint]] = BriefTransform.transform2DF(sc)


    datas
  }

  def main(args: Array[String]): Unit = {

  }

}
