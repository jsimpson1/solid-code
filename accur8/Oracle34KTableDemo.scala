package playground

import m3.jdbc.Database
import m3.jdbc.managed_mapper.{ManagedConnection, ManagedDatabase, ManagedDatabaseFactoryInternal}
import m3.predef.inject
import m3.{DeveloperProperties, Txn}
import m3.predef._
import m3.json.JsonAssist.jsondsl
import m3.predef._
import com.ahsrcm.mobidash.scala.JsonAssist._
import jsondsl._
import m3.jdbc.managed_mapper.querydsl.QueryDsl

object Oracle34KTableDemo extends App {

  val maxNum = 34000

  run()

  def run(): Unit = Txn {

    implicit lazy val oracleManagedDb =
      inject[ManagedDatabaseFactoryInternal]
        .findOrCreate(
          Database
            .fromDeveloperProperties("oracle.database")
        )

    println("start")

    val tableNames = getTableNames(maxNum)
    createTables(tableNames)
  }
  
  def getTableNames(max: Int): Iterator[String] = {
    (1 to max).map{ i =>
      val maxSize = max.toString.size
      val zeros = {
        val iSize = i.toString.size
        val numOfZeros = maxSize - iSize
        (0 until numOfZeros)
          .map(j => "0")
          .mkString("")
      }
      val tableName = s"CUSTOMER_MASTER_$zeros$i"
      tableName
    }.toIterator
  }

  def createTables(tableNames: Iterator[String])(implicit managedDatabase: ManagedDatabase): Unit = {
    tableNames.foreach { name =>
      println(s"creating $name")
      Txn {
        val createTableSql = getCreateTableSql(name)
        managedDatabase.managedConnection.update(createTableSql)
      }
    }
  }

  def getCreateTableSql(tableName: String): String = {
    import m3.jdbc._
    import QueryDsl.sqlGeneratorFromManagedConnection
    s"""
CREATE TABLE $tableName (
  ID NUMBER NOT NULL,
  SALESFORCE_ID VARCHAR2(10),
  PRIMARY KEY (ID)
)"""
  }

}
