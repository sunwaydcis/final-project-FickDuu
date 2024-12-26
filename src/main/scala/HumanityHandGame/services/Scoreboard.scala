package HumanityHandGame.services

import java.io.{File, PrintWriter}
import scala.io.Source
import scala.collection.mutable.ListBuffer

object Scoreboard{
  private val scores: ListBuffer[(String, Int)] = ListBuffer()

  def addScore(playerName: String, score: Int): Unit = {
    scores += ((playerName, score))
    println(s"Score added: $playerName - $score")
  }

  def getScores:List[(String, Int)] = scores.sortBy(-_._2).toList

  def displayScores(): Unit ={
    println("\nHigh Scores:")
    scores.sortBy(-_._2).zipWithIndex.foreach{
      case ((name, score), index) => println(s"${index + 1}. $name - $score")
    }
  }

  def saveToFile(): Unit = {
    try{
      val writer = new PrintWriter(new File("scores.txt"))
      scores.foreach{
        case(name, score)=> writer.println(s"$name:$score")
      }
      writer.close()
      println("Scores saved successfully")
    }
    catch{
      case e: Exception=>
        println(s"Failed to save scores: ${e.getMessage}")
    }
  }
  
  def loadFromFile(): Unit ={
    var source: Option[Source] = None
    try{
      val file = new File("scores.txt")
      if(file.exists()){
        source = Some(Source.fromFile(file))
        for (line <- source.get.getLines()) {
          val parts = line.split(":")
          if (parts.length == 2) {
            val name = parts(0)
            val score = parts(1).toIntOption.getOrElse(0)
            scores += ((name, score))
          } else {
            println(s"Skipping malformed line: $line")
          }
        }
        println("Scores loaded successfully.")
      } else {
        println("No scores file found, starting fresh.")
      }
    } catch {
      case e: Exception =>
        println(s"Failed to load scores: ${e.getMessage}")
    } finally {
      source.foreach(_.close())
    }
  }
}