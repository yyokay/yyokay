package org.yyok.lib.mining

import org.apache.spark.SparkContext
import org.apache.spark.ml.feature.LabeledPoint
import org.apache.spark.ml.linalg.Vectors
import org.apache.spark.rdd.RDD

object BriefExtract {

  val webHdfsDPathApply = "webhdfs://gaiaa:50070/tao/ods/zfgjj/20190528112108/log_apply_20190528135703.txt"

  /**
    * ETL过程：数据预处理
    *
    * @param sc
    * @return
    */
  def extract2DF(sc: SparkContext): Map[String, RDD[LabeledPoint]] = {

    val dataa: RDD[String] = sc.textFile("D:\\data\\decisiontree\\decisiontreeu.txt")

    //xxx测试数据加载
    val datab: RDD[String] = sc.textFile("D:\\data\\decisiontree\\decisiontreeu1.txt")

    //定义返回目标
    var datas:Map[String, RDD[LabeledPoint]] = Map()

    //返回目标归档
    //datas += ("trainingData" -> dataa)
   // datas += ("testData" -> datab)

    //返回
    datas

  }



}
